
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import{InvoiceHistory}  from '../views/invoicereport/invoice-history';
import{ItemWiseSaleHistory}  from '../views/invoicereport/item-wise-sale-history';
import { Http, ResponseContentType } from "@angular/http";
import * as FileSaver from 'file-saver';

@Injectable({
  providedIn: 'root'
})
export class InvoiceReportService {

  public API = 'http://localhost:8080/api';
	public INVOICE_HISTORY_API = this.API + '/invoicesaleshistory';
	public INVOICE_HISTORY_REPORT_API = this.API + '/reports/invoicesaleshistory';
	public ITEM_WISE_SALE_HISTORY_REPORT_API = this.API + '/reports/itemwisesalehistory';
	public CUSTOMER_WISE_SALE_HISTORY_REPORT_API = this.API + '/reports/customerwisesalehistory';

	private salesHistoryInvoiceReport : InvoiceHistory;
	private itemWiseSaleHistory : ItemWiseSaleHistory;
	
  	constructor(private http: HttpClient) { }
  
  	getAll(): Observable<any> {
    	return this.http.get(this.INVOICE_HISTORY_API);
	}
	  

	getAllInvoiceHistoryReport() {
        this.http.get(this.INVOICE_HISTORY_REPORT_API+'/pdf', { responseType: 'blob' })
            .subscribe((file: Blob) => {
               FileSaver.saveAs(file, "invoice-history.pdf");
        });    
	}
	
	getAllInvoiceHistoryExcelReport() {
        this.http.get(this.INVOICE_HISTORY_REPORT_API+'/excel', { responseType: 'blob' })
            .subscribe((file: Blob) => {
               FileSaver.saveAs(file, "invoice-history.xlsx");
        });    
	}


	getAllItemWiseSaleHistoryReport(): Observable<any> {
		return this.http.get(this.ITEM_WISE_SALE_HISTORY_REPORT_API);
	}
	
	getAllItemWiseSaleHistoryReportPdf() {
        this.http.get(this.ITEM_WISE_SALE_HISTORY_REPORT_API+'/pdf', { responseType: 'blob' })
            .subscribe((file: Blob) => {
               FileSaver.saveAs(file, "item-wise-sale-history.pdf");
        });    
	}


	getAllItemWiseSaleHistoryReportExcel() {
        this.http.get(this.ITEM_WISE_SALE_HISTORY_REPORT_API+'/excel', { responseType: 'blob' })
            .subscribe((file: Blob) => {
               FileSaver.saveAs(file, "item-wise-sale-history.xlsx");
        });    
	}

	getAllCustomerWiseSaleHistoryReport(customerId: number, fromdate: Date, toDate : Date): Observable<any> {
		return this.http.post<any>(this.CUSTOMER_WISE_SALE_HISTORY_REPORT_API, {"customerId": customerId, "fromDate": fromdate, "toDate": toDate})
	}
	
	getAllCustomerWiseSaleHistoryReportPdf(customerId: number, fromdate: Date, toDate : Date) {
		this.http.post<Blob>(this.CUSTOMER_WISE_SALE_HISTORY_REPORT_API+`/pdf`,{"customerId": customerId, "fromDate": fromdate, "toDate": toDate},{responseType: 'blob' as 'json'})
            .subscribe((file: Blob) => {
               FileSaver.saveAs(file, "customer-wise-sale-history.pdf");
        });    
	}


	getAllCustomerWiseSaleHistoryReportExcel(customerId: number, fromdate: Date, toDate : Date) {
        this.http.get(this.CUSTOMER_WISE_SALE_HISTORY_REPORT_API+'/excel', { responseType: 'blob' })
            .subscribe((file: Blob) => {
               FileSaver.saveAs(file, "customer-wise-sale-history.xlsx");
        });    
	}


  	get(id: number) {
    	return this.http.get(this.INVOICE_HISTORY_API + '/' + id);
  	}
  	errorHandler(error:Response) {
    	return Observable.throw(error||"SERVER ERROR");
  	}
}
