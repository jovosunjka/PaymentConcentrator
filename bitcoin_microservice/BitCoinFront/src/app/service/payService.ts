import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class payService{

    base_url = "https://localhost:8086/bitcoin";


    constructor(private http: HttpClient){ }

    pay(currency: string, amount: number): Observable<any>
    {
        
        //var ime = "mijat";

        console.log("Kreiranje transakcije");
        return this.http.get<any>(this.base_url + "/createPay?currency=" + currency + "&" + "amount=" + amount);
    }

    checkPay(transactionId: number, bitcoinTransactionId){
        return this.http.get<any>(this.base_url + "/checkPayment?transactionId=" + transactionId + "&" + "btId=" + bitcoinTransactionId);
    }

    cancelTransaction(transactionId: number){
        return this.http.get<any>(this.base_url + "/cancelTransaction?transactionId=" + transactionId);
    }

}