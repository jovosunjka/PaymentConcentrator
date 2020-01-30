import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from '../model/Transaction';

@Injectable()
export class saveTransactionService{

    base_url = "https://localhost:8085/payment";


    constructor(private http: HttpClient){ }

    save(t: Transaction): Observable<any>
    {
        console.log("Prosledjena transakcija na cuvanje u bazu")
        return this.http.post<any>(this.base_url + "/saveTransaction", t);
    }

    cancel(t: Transaction): Observable<any>
    {
        console.log("Prosledjena transakcija na cuvanje u bazu")
        return this.http.post<any>(this.base_url + "/cancelTransaction", t);
    }

    allTransaction(): Observable<any>
    {
        console.log("Sve transakcije")
        return this.http.get(this.base_url + "/getAllTransaction");
    }

    getSubscribeToken(){
        const headers: HttpHeaders = new HttpHeaders(
            {
              "Content-Type": "application/x-www-form-urlencoded",
              "Authorization": "Basic QVN5OWZyYlUwN29PRkRObXltYTVTTUtiS0tLZWQ0dzNIQ3RFZ3MzLXRCQi1hUjhPUjVVZzFiaDN0aVFWdklGRWk1ajBDVFdzRVhVdjQxdEY6RUVrc2ZOZkpzU21BVWpXNDl3X05kdHBnNW1YQktiWUM3NHFyQ2REbkZDeWtoYXUxUUZkbkFoYjltbHdmTzhRRnhzcXM5M2V0T0tPU1F1MHE="
            }
          );
 
        const body = new HttpParams()
      .set("grant_type", "client_credentials");
        
        return this.http.post<any>("https://api.sandbox.paypal.com/v1/oauth2/token", body.toString(), {headers});
    }


}