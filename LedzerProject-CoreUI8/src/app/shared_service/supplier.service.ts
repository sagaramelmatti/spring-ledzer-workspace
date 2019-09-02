import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import{Supplier}  from '../views/supplier/supplier';


@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  	public API = 'http://localhost:8080/api';
	public SUPPLIER_API = this.API + '/supplier';

	private supplier:Supplier;
  	constructor(private http: HttpClient) { }
  
  	getAll(): Observable<any> {
    	return this.http.get(this.SUPPLIER_API);
  	}

  	get(id: number) {
    	return this.http.get(this.SUPPLIER_API + '/' + id);
  	}

  save(supplier: Supplier): Observable<any> {
    let result: Observable<Object>;
    if (supplier.id) {
      result = this.http.put(this.SUPPLIER_API + '/' + supplier.id, supplier);
    } else {
	//console.log(supplier.state);

      result = this.http.post(this.SUPPLIER_API+'/create', supplier);
    }
    return result;
  }
  
  remove(supplier : Supplier) {
    return this.http.delete(this.SUPPLIER_API + '/' + supplier.id);
    
  }

  errorHandler(error:Response) {
    return Observable.throw(error||"SERVER ERROR");
  }
  
  setter(supplier: Supplier) {
    this.supplier=supplier;
  }
   getter() {
   return this.supplier;
  }
}
