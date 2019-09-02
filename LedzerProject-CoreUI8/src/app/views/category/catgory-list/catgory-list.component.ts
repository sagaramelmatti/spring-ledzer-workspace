import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import  { CategoryService}  from '../../../shared_service/category.service';
import 	{	Category}  from '../category';


@Component({
  selector: 'app-catgory-list',
  templateUrl: './catgory-list.component.html',
  styleUrls: ['./catgory-list.component.css']
})
export class CatgoryListComponent implements OnInit {

  public category_list:Category[];
  public href: string = "";
  public url: string = "";

  constructor(private categoryService: CategoryService,private router: Router) { }

  ngOnInit() {

    this.href = this.router.url;
    console.log(this.href);
    if(this.href == '/category/sub-category-list')
    {
        this.url = "sub-category";
        this.categoryService.getAllSubCategory().subscribe((category_list)=>{
          console.log(category_list);
          this.category_list=category_list;
          },(error)=>{
            console.log(error);
          })
    }
    else if(this.href == '/category/category-list')
    {
      this.url = "category";
      this.categoryService.getAll().subscribe((category_list)=>{
        console.log(category_list);
        this.category_list=category_list;
        },(error)=>{
          console.log(error);
        })
    }
  }

  gotoList() {
    this.router.navigate(['category/category-list']);
    //this.router.navigate(['Uom-list'], {relativeTo: this.route});
  }
  
  remove(category : Category) {
  const directive = this;
  const result = confirm('Do you really want to delete Category ?');
      if (result) {
        this.categoryService.remove(category).subscribe( data => {
          this.category_list = this.category_list.filter(u => u !== category);
        })
      } else {
        return false;
      }
  }

}
