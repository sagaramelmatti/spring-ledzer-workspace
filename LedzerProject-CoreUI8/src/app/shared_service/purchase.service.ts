import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import  {Purchase}  from '../views/purchase/purchase';
import 	{PurchaseDetail}  from '../views/purchase/purchaseDetail';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  public API = 'http://localhost:8080/api';
  public PURCHASE_API = this.API + '/purchases';

  private purchase:Purchase;
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<any> {
    return this.http.get(this.PURCHASE_API);
  }

  getAllPurchases(): Observable<any> {
    return this.http.get(this.PURCHASE_API+ '/list' );
  }

  get(id: number) {
    console.log("purchaseservice call="+id);
    return this.http.get(this.PURCHASE_API + '/' + id);
    
  }

  getNextPurchaseId() : Observable<any> {
    return this.http.get<any>(this.PURCHASE_API+ '/getMaxId');
    
  }

  save(purchase: Purchase): Observable<any> 
  {
    alert('save in purchase service called');
    console.log(purchase);
    let result: Observable<Object>;
    return this.http.post<any>(this.PURCHASE_API + '/create', purchase);
  }

  update(purchase: Purchase, purchaseId : number): Observable<any> 
  {
    let result: Observable<Object>;
    if (purchaseId) {
      result = this.http.put<any>(this.PURCHASE_API + '/' + purchaseId, purchase);
    } 
    return result;
  }

  updatePurchaseDetail(purchaseDetail: PurchaseDetail, purchaseId : number): Observable<any> 
  {
    let result: Observable<Object>;
    if (purchaseId) {
      result = this.http.put<any>(this.PURCHASE_API + '/' + purchaseId, purchaseDetail);
    } 
    return result;
  }

  updatePurchaseDetailEntry(purchase: Purchase, purchaseId : number): Observable<any> 
  {
    let result: Observable<Object>;
    if (purchaseId) {
      result = this.http.put<any>(this.PURCHASE_API + '/updatepurchasedetail/' + purchaseId, purchase);
    } 
    return result;
  }

  remove(purchase : Purchase) {
    return this.http.delete(this.PURCHASE_API + '/' + purchase.id);
  }

  findAllPurchasesDetails(purchaseId:number):  Observable<any> 
  {
      return this.http.get(this.PURCHASE_API + '/purchaseDetails/' + purchaseId);
}

getPurchaseDetail(id: number) {
  console.log("purchaseservice call="+id);
  return this.http.get(this.PURCHASE_API + '/purchasedetail/' + id);
  
}

  errorHandler(error:Response) {
    return Observable.throw(error||"SERVER ERROR");
  }

  setter(purchase : Purchase) {
    this.purchase=purchase;
  }
   getter() {
   return this.purchase;
  }
}
