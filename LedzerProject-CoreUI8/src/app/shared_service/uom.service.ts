import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import 'rxjs/Rx';

import{Uom}  from '../views/uom/uom';

@Injectable({
  providedIn: 'root'
})
export class UomService {

  public Uom_API = 'http://localhost:8080/uom';


  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.Uom_API+ '/uom_list');
  }

  get(id: number) {
    return this.http.get(this.Uom_API + '/' + id);
  }

  save(uom: Uom): Observable<any> {
    let result: Observable<Object>;
    if (uom.id) {
      result = this.http.put(this.Uom_API + '/' + uom.id, uom);
    } else {
      result = this.http.post(this.Uom_API+'/create', uom);
    }
    return result;
  }
  
  remove(uom : Uom) {
    return this.http.delete(this.Uom_API + '/' + uom.id);
    
  }
}
