import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class payService{

    base_url = "https://localhost:8086/api/bitcoin";


    constructor(private http: HttpClient){ }

    pay(): Observable<any>
    {
        var ime = "mijat";
        const headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
        console.log("Kreiranje transakcije");
        return this.http.get<any>(this.base_url + "/createPay?username=" + ime);
    }

}