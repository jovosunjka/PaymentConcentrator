import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../User';

@Injectable()
export class AuthHttpService
{
    constructor(private http: HttpClient){
      
    }

    check(transactionId: number, user: User, serverPort: number): Observable<any>
    {
        const headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
        console.log(user);

        return this.http.put("https://localhost:" +serverPort+ "/api/bank/pay/" + transactionId,user,{headers}) as Observable<any>;

    }


}
