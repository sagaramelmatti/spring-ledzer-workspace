import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import{Category}  from '../views/category/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  public API = 'http://localhost:8080/api';
	public CATEGORY_API = this.API + '/category';

  private category:Category;
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<any> {
    return this.http.get(this.CATEGORY_API);
  }

  getAllSubCategory(): Observable<any> {
    return this.http.get(this.CATEGORY_API+'/subcategory');
  }

  get(id: number) {
    return this.http.get(this.CATEGORY_API + '/' + id);
    
  }

  save(category: Category): Observable<any> {
    let result: Observable<Object>;
    if (category.id) {
      result = this.http.put(this.CATEGORY_API + '/' + category.id, category);
    } else {
      result = this.http.post(this.CATEGORY_API+'/create', category);
    }
    return result;
  }
  
  remove(category : Category) {
    return this.http.delete(this.CATEGORY_API + '/' + category.id);
  }

  errorHandler(error:Response) {
    return Observable.throw(error||"SERVER ERROR");
  }
}