import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import{Customer}  from '../views/customer/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  public API = 'http://localhost:8080/api';
	public CUSTOMER_API = this.API + '/customers';

	private customer:Customer;
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<any> {
    return this.http.get(this.CUSTOMER_API);
  }

  get(id: number) {
    return this.http.get(this.CUSTOMER_API + '/' + id);
  }

  search(name: String) {
    return this.http.get(this.CUSTOMER_API + '/' + name);
  }

  save(customer: Customer): Observable<any> {
    let result: Observable<Object>;
    if (customer.id) {
      result = this.http.put(this.CUSTOMER_API + '/' + customer.id, customer);
    } else {
	//console.log(customer.state);

      result = this.http.post(this.CUSTOMER_API+'/create', customer);
    }
    return result;
  }
  
  remove(customer : Customer) {
    return this.http.delete(this.CUSTOMER_API + '/' + customer.id);
    
  }

  errorHandler(error:Response) {
    return Observable.throw(error||"SERVER ERROR");
  }
  
  setter(customer: Customer) {
    this.customer=customer;
  }
   getter() {
   return this.customer;
  }
}
