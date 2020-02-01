import { Component, OnInit, NgZone, HostListener } from '@angular/core';
import { payService } from '../service/payService';
import { ActivatedRoute, Router } from '@angular/router';
import { fromEvent } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  transactionId: number;
  paymentID: number;
  private amount: number;
  private currency: string;
  private sendedTransaction: boolean;

  constructor(private http: payService, private ngZone: NgZone, private route: ActivatedRoute) { 
    //RADI ZA BACK ALI UPADA U ISTI EVENT KAO I KADA SE GASI TAB
    fromEvent(window, 'popstate').subscribe((e) => {
      console.log(e, 'back button');
    });
  }

  ngOnInit() {
    if (this.route.snapshot.params.transactionId) {
      this.paymentID = this.route.snapshot.params.transactionId;
      this.amount = this.route.snapshot.params.amount;
      this.currency = this.route.snapshot.params.currency;
    }
    console.log(this.amount + this.currency);
    this.sendedTransaction = false;
  }

  

  pay(){
    var c = Math.floor(this.amount);
    this.http.pay(this.currency, c).subscribe((redirectUrlDto) => {
      this.sendedTransaction = true;
      //console.log(redirectUrlDto.idTransaction);
      this.ngZone.runOutsideAngular(() => {
        //window.location.href = redirectUrlDto.redirectUrl;
        window.open(redirectUrlDto.redirectUrl);
        this.transactionId = redirectUrlDto.idTransaction;
      });
    });
  }

  payComplite(){
    this.http.checkPay(this.paymentID, this.transactionId).subscribe((response) =>{
      window.location.href = response.redirectUrl;
    });
  }

  @HostListener('window:beforeunload', [ '$event' ])
  beforeUnloadHander(event) {
    console.log(JSON.stringify(event));
    if(!this.sendedTransaction){
      this.http.cancelTransaction(this.paymentID).subscribe();
    }
    //alert('window:beforeunload');
  }

  @HostListener('window:popstate', ['$event'])
  onPopState(event) {
    console.log('Back button clicked');
  }
  
}
