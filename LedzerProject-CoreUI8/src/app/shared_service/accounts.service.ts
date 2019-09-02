import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import{AccountGroup}  from '../views/accounts/account-group';
import{UserAccount}  from '../views/accounts/user-account';


@Injectable({
  providedIn: 'root'
})
export class AccountsService 
{

  public API = 'http://localhost:8080/api';
  public ACCOUNTGROUP_API = this.API + '/accountgroup';
  public ACCOUNT_API = this.API + '/account';

  private accountgroup:AccountGroup;
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<any> {
    return this.http.get(this.ACCOUNTGROUP_API);
  }

  get(id: number) {
    return this.http.get(this.ACCOUNTGROUP_API + '/' + id);
    
  }

  save(accountgroup: AccountGroup): Observable<any> {
    let result: Observable<Object>;
    if (accountgroup.id) {
      result = this.http.put(this.ACCOUNTGROUP_API + '/' + accountgroup.id, accountgroup);
    } else {
      result = this.http.post(this.ACCOUNTGROUP_API+'/create', accountgroup);
    }
    return result;
  }
  
  remove(accountgroup : AccountGroup) {
    return this.http.delete(this.ACCOUNTGROUP_API + '/' + accountgroup.id);
  }

  getAllAccounts(): Observable<any> {
    return this.http.get(this.ACCOUNT_API);
  }

  getAccount(id: number) {
    return this.http.get(this.ACCOUNT_API + '/' + id);
    
  }

  saveAccount(account: UserAccount): Observable<any> {
    let result: Observable<Object>;
    if (account.id) {
      result = this.http.put(this.ACCOUNT_API + '/' + account.id, account);
    } else {
      result = this.http.post(this.ACCOUNT_API+'/create', account);
    }
    return result;
  }
  
  removeAccount(account : UserAccount) {
    return this.http.delete(this.ACCOUNT_API + '/' + account.id);
  }

  getDefaultAccount() : Observable<any> {
    return this.http.get<any>(this.ACCOUNT_API+ '/getDefaultAccount');
    
  }

  errorHandler(error:Response) {
    return Observable.throw(error||"SERVER ERROR");
  }
}
