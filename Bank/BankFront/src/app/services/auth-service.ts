import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../User';

@Injectable()
export class AuthHttpService
{
    constructor(private http: HttpClient){
      
    }

    check(user: User): Observable<any>
    {

        return this.http.post("https://localhost:8080/api/bank/check",user,{responseType: 'text'});

    }


}