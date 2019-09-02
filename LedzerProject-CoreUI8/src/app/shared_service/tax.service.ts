import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import 'rxjs/Rx';

import{Tax}  from '../views/tax/tax';

@Injectable({
  providedIn: 'root'
})
export class TaxService {
  
	public API = 'http://localhost:8080/api';
	public TAX_API = this.API + '/taxes';

	private tax:Tax;
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<any> {
    return this.http.get(this.TAX_API);
  }

  get(id: number) {
    return this.http.get(this.TAX_API + '/' + id);
  }

  save(tax: Tax): Observable<any> {
    let result: Observable<Object>;
    if (tax.id) {
      result = this.http.put(this.TAX_API + '/' + tax.id, tax);
    } else {
      result = this.http.post(this.TAX_API+'/create', tax);
    }
    return result;
  }
  
  remove(tax : Tax) {
    return this.http.delete(this.TAX_API + '/' + tax.id);
    
  }

  errorHandler(error:Response) {
    return Observable.throw(error||"SERVER ERROR");
  }
  
  setter(tax: Tax) {
    this.tax=tax;
  }
   getter() {
   return this.tax;
  }
  
}
