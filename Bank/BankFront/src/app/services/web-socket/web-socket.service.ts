import { Injectable, Inject, NgZone } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ToastrService } from 'ngx-toastr';


@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private stompClient;
  private serverSocketRelativeUrl = '/socket';
  private subscription;
  private successfullyConnected: boolean;


  constructor(private toastr: ToastrService, private ngZone: NgZone ) {
  }

  initializeWebSocketConnection(serverPort: number) {
    const baseUrl = 'https://localhost:' + serverPort + '/api';
    const socket = new SockJS(baseUrl + this.serverSocketRelativeUrl);
    this.stompClient = Stomp.over(socket);
    this.connect();

  }

  connect() {
      // prva lambda funkcija je callback za uspesno konektovanje, a druga lambda fukcija za pucanje konekcije
    this.stompClient.connect({},
      (frame) =>  this.subscribe(),
      () => {
        this.toastr.error('The connection with the science-center-backend was interrupted!');
        this.toastr.info('We will try to reconnect.');
        this.connect();
      }
    );
  }

  subscribe() {
      this.subscription = this.stompClient.subscribe('/topic', (message) => {
        if (message.body) {
          const body = JSON.parse(message.body);
          alert(body.status);

          this.ngZone.runOutsideAngular(() => {
            window.location.href = body.redirectUrl;
          });

        } else {
          this.toastr.error('Empty body in a websocket message!');
        }
      });
  }

  unsubscribe() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  disconnect() {
    if (this.stompClient) {
      if (this.successfullyConnected) {
        this.stompClient.disconnect(
          () => this.toastr.info('The connection with the card payment microservice was interrupted!')
        );
      }
    }
  }


}
