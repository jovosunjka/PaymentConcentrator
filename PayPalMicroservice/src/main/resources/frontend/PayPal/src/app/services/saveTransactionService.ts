import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from '../model/Transaction';

@Injectable()
export class saveTransactionService{

    base_url = "https://localhost:8085/api/payment";


    constructor(private http: HttpClient){ }

    save(t: Transaction): Observable<any>
    {
        console.log("Prosledjena transakcija na cuvanje u bazu")
        return this.http.post(this.base_url + "/saveTransaction", t);
    }

    allTransaction(): Observable<any>
    {
        console.log("Sve transakcije")
        return this.http.get(this.base_url + "/getAllTransaction");
    }



}