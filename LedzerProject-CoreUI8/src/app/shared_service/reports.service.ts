import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import{Invoice}  from '../views/invoice/invoice';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  public API = 'http://localhost:8080/api';
	public INVOICE_REPORTS_API = this.API + '/invoicereports';

	private invoice : Invoice;
  	constructor(private http: HttpClient) { }
  
  	getAll(): Observable<any> {
    	return this.http.get(this.INVOICE_REPORTS_API);
  	}

  	get(id: number) {
    	return this.http.get(this.INVOICE_REPORTS_API + '/' + id);
  	}

  

  errorHandler(error:Response) {
    return Observable.throw(error||"SERVER ERROR");
  }
}
