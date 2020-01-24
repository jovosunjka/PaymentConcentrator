import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../User';

@Injectable()
export class AuthHttpService
{
    constructor(private http: HttpClient){
      
    }

    check(transactionId: number, user: User): Observable<any>
    {
        const headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

        return this.http.post("https://localhost:8084/payment/pay?transactionId=" + transactionId,user,{headers}) as Observable<any>

    }


}
