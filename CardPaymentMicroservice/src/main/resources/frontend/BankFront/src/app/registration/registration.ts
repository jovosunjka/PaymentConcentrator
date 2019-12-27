import { Component, OnInit } from '@angular/core';
import { AuthHttpService } from '../services/auth-service';
import { Router } from '@angular/router';
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

    constructor(private http: AuthHttpService, private router: Router, private fb: FormBuilder){}

    userGroup = this.fb.group({
        cardNumber :  ['', Validators.required],
        pin :  ['', Validators.required],
        });
        
    ngOnInit(){}

    register()
    {
        let user = this.userGroup.value;

        this.http.check(user).subscribe(Response => this.text = Response);
    }

}

