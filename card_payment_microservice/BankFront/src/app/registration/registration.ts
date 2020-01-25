import { Component, OnInit, NgZone } from '@angular/core';
import { AuthHttpService } from '../services/auth-service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm, Validators, FormBuilder } from '@angular/forms';
import { format } from 'url';


@Component(
    {
        selector: 'app-home',
        templateUrl: './registration.html',
        providers: [AuthHttpService]
    }
)
export class RegistrationComponent implements OnInit{
    text:string;
    transactionId: number;


    constructor(private http: AuthHttpService, private router: Router, private ngZone: NgZone, private fb: FormBuilder, private route: ActivatedRoute){}

    userGroup = this.fb.group({
        cardNumber :  ['', Validators.required],
        securityCode :  ['', Validators.required],
        cardHolder: ['', Validators.required],
        expirationDate: ['',Validators.required]
        });
        
    ngOnInit() {
        if (this.route.snapshot.params.transactionId) {
            this.transactionId = this.route.snapshot.params.transactionId;
        }
    }

    register()
    {
        let user = this.userGroup.value;

        this.http.check(this.transactionId, user).subscribe(
            redirectUrlDto =>{
                this.ngZone.runOutsideAngular(() => {
                    window.location.href = redirectUrlDto.redirectUrl;
                  });
            },
            err => alert(err)
        );
    }

}

