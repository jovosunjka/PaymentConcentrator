import { Injectable, Injector, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient, @Inject('BASE_API_URL') private baseUrl: string) {}


  getAll<T>(relativeUrl: string, token: string): Observable<T[]> {
    const headers: HttpHeaders = new HttpHeaders({'X-Auth-Token': token});
    return this.http.get<T[]>(this.baseUrl + relativeUrl, { headers });
  }

  get<T>(relativeUrl: string, token: string): Observable<T> {
    // const params: HttpParams = new HttpParams().set('_id',id);
    const headers: HttpHeaders = new HttpHeaders({'X-Auth-Token': token});
    return this.http.get<T>(this.baseUrl + relativeUrl, { headers });
  }

  getById<T>(relativeUrl: string, id: number): Observable<T> {
    // const params: HttpParams = new HttpParams().set('_id',id);
    return this.http.get<T>(this.baseUrl + relativeUrl + `/${id}`);
  }

  put<T>(relativeUrl: string, t: T): Observable<T> {
    const headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put<T>(this.baseUrl + relativeUrl, t, { headers });
  }

  save<T>(relativeUrl: string, t: T) {
    const headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.baseUrl + relativeUrl, t,  { headers });
  }

  post(relativeUrl: string, t: any) {
    const headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.baseUrl + relativeUrl, t, { headers });
  }

  delete(relativeUrl: string, id: number) {
    return this.http.delete(this.baseUrl + relativeUrl + `/${id}` + '/delete');
  }
}
