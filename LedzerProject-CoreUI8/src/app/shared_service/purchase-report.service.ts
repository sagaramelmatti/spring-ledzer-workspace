import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import{PurchaseHistory}  from '../views/purchasereport/purchase-history';

@Injectable({
  providedIn: 'root'
})
export class PurchaseReportService {

  public API = 'http://localhost:8080/api';
	public PURCHASE_HISTORY_REPORTS_API = this.API + '/purchasehistory';

  private purchaseHistory : PurchaseHistory;
  
  constructor(private http: HttpClient) { }
  
  	getAll(): Observable<any> {
    	return this.http.get(this.PURCHASE_HISTORY_REPORTS_API);
  	}

  	get(id: number) {
    	return this.http.get(this.PURCHASE_HISTORY_REPORTS_API + '/' + id);
  	}
  	errorHandler(error:Response) {
    	return Observable.throw(error||"SERVER ERROR");
  	}
}
