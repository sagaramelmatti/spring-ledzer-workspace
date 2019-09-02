import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import{Product}  from '../views/product/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  public API = 'http://localhost:8080/api';
	public PRODUCT_API = this.API + '/product';

  private product:Product;
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<any> {
    return this.http.get(this.PRODUCT_API);
  }

  get(id: number) {
    return this.http.get(this.PRODUCT_API + '/' + id);
    
  }

  save(product: Product): Observable<any> {
    let result: Observable<Object>;
    if (product.id) {
      result = this.http.put(this.PRODUCT_API + '/' + product.id, product);
    } else {
      result = this.http.post(this.PRODUCT_API+'/create', product);
    }
    return result;
  }
  
  remove(product : Product) {
    return this.http.delete(this.PRODUCT_API + '/' + product.id);
  }

  errorHandler(error:Response) {
    return Observable.throw(error||"SERVER ERROR");
  }
}
