import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import  {Invoice}  from '../views/invoice/invoice';
import 	{InvoiceDetail}  from '../views/invoice/invoiceDetail';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class InvoiceService 
{
  public API = 'http://localhost:8080/api';
  public INVOICE_API = this.API + '/invoices';

  private invoice:Invoice;
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<any> {
    return this.http.get(this.INVOICE_API);
  }

  getAllInvoices(): Observable<any> {
    return this.http.get(this.INVOICE_API+ '/list' );
  }

  get(id: number) {
    console.log("invoiceservice get method call="+id);
    return this.http.get(this.INVOICE_API + '/' + id);
    
  }

  getNextInvoiceId() : Observable<any> {
    return this.http.get<any>(this.INVOICE_API+ '/getMaxId');
    
  }

  save(invoice: Invoice): Observable<any> 
  {
    let result: Observable<Object>;
    return this.http.post<any>(this.INVOICE_API + '/create', invoice);
  }

  update(invoice: Invoice, invoiceId : number): Observable<any> 
  {
    let result: Observable<Object>;
    if (invoiceId) {
      result = this.http.put<any>(this.INVOICE_API + '/' + invoiceId, invoice);
    } 
    return result;
  }

  updateInvoiceDetail(invoiceDetail: InvoiceDetail, invoiceId : number): Observable<any> 
  {
    let result: Observable<Object>;
    if (invoiceId) {
      result = this.http.put<any>(this.INVOICE_API + '/' + invoiceId, invoiceDetail);
    } 
    return result;
  }

  updateInvoiceDetailEntry(invoice: Invoice, invoiceId : number): Observable<any> 
  {
    let result: Observable<Object>;
    if (invoiceId) {
      result = this.http.put<any>(this.INVOICE_API + '/updateinvoicedetail/' + invoiceId, invoice);
    } 
    return result;
  }

  remove(invoice : Invoice) {
    return this.http.delete(this.INVOICE_API + '/' + invoice.id);
  }

  findAllInvoicesDetails(invoiceId:number):  Observable<any> 
  {
      return this.http.get(this.INVOICE_API + '/invoiceDetails/' + invoiceId);

      /*
    return this.http.get(this.INVOICE_API + '/invoiceDetails', {
        params: new HttpParams()
            .set('invoiceId', invoiceId)
            //.set('filter', filter)
            //.set('sortOrder', sortOrder)
            //.set('pageNumber', pageNumber.toString())
            //.set('pageSize', pageSize.toString())
    }).pipe(
        map(res =>  res["payload"])
    );
    */
}

getInvoiceDetail(id: number) {
  console.log("invoiceservice call="+id);
  return this.http.get(this.INVOICE_API + '/invoicedetail/' + id);
  
}

  errorHandler(error:Response) {
    return Observable.throw(error||"SERVER ERROR");
  }

  setter(invoice : Invoice) {
    this.invoice=invoice;
  }
   getter() {
   return this.invoice;
  }
}
