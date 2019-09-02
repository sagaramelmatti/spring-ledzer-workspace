import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import{State}  from '../views/customer/state';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  public API = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.API+ '/states');
  }

  getAllPaymentModes(): Observable<any> {
    return this.http.get(this.API+ '/paymentmodes');
  }

}
