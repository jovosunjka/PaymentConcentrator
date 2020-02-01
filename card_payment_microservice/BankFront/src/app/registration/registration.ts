import { Component, OnInit, NgZone, OnDestroy } from '@angular/core';
import { AuthHttpService } from '../services/auth-service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm, Validators, FormBuilder } from '@angular/forms';
import { filter } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import { WebSocketService } from '../services/web-socket/web-socket.service';


@Component(
    {
        selector: 'app-home',
        templateUrl: './registration.html',
        providers: [AuthHttpService]
    }
)
export class RegistrationComponent implements OnInit, OnDestroy {
    text:string;
    transactionId: number;

    amount: number;
    serverPort: number;


    constructor(private http: AuthHttpService, private router: Router, private ngZone: NgZone,
                private fb: FormBuilder, private route: ActivatedRoute, private toastr: ToastrService,
                private webSocketService: WebSocketService) {
                this.amount = -1;
                this.serverPort = -1;
         }

    userGroup = this.fb.group({
        cardNumber :  ['', Validators.required],
        securityCode :  ['', Validators.required],
        cardHolder: ['', Validators.required],
        expDate: ['',Validators.required]
        });

    ngOnInit() {
        if (this.route.snapshot.params.transactionId) {
            this.transactionId = this.route.snapshot.params.transactionId;
        }

        // https://alligator.io/angular/query-parameters/
        this.route.queryParams.pipe(
            filter(params => params.amount || params.serverPort)
        )
        .subscribe(params => {
                console.log(params);
                this.amount = params.amount;
                this.serverPort = params.serverPort;

                this.webSocketService.initializeWebSocketConnection(this.serverPort);
            }
        );
    }

    ngOnDestroy() {
        this.webSocketService.unsubscribe();
        this.webSocketService.disconnect();
    }

    register()
    {
        const user = this.userGroup.value;

        this.http.check(this.transactionId, user, this.serverPort).subscribe(
            () => this.toastr.success('Transaction submitted!'),
            err => alert(err)
        );
    }

}

