import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import{Business}  from '../views/business/business';

@Injectable({
  providedIn: 'root'
})
export class BusinessService {

  public API = 'http://localhost:8080/api';
  public BUSINESS_API = this.API + '/business';

  private uusiness:Business;
  constructor(private http: HttpClient) { }
  
  get(id: number) {
    return this.http.get(this.BUSINESS_API + '/' + id);
    
  }
}
