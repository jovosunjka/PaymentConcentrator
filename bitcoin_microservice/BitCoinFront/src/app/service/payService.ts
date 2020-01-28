import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class payService{

    base_url = "https://localhost:8086/bitcoin";


    constructor(private http: HttpClient){ }

    pay(): Observable<any>
    {
        
        var ime = "mijat";

        console.log("Kreiranje transakcije");
        return this.http.get<any>(this.base_url + "/createPay?username=" + ime);
    }

    checkPay(transactionId: number){
        return this.http.post<any>(this.base_url + "/checkPayment", transactionId);
    }

}