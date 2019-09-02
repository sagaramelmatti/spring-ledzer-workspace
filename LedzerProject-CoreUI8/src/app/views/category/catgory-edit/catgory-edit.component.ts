import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import  { CategoryService}  from '../../../shared_service/category.service';
import 	{	Category}  from '../category';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-catgory-edit',
  templateUrl: './catgory-edit.component.html',
  styleUrls: ['./catgory-edit.component.css']
})
export class CatgoryEditComponent implements OnInit {

  public sub: Subscription;
  public cat: Category = new Category();
  public childCat : Category;
  public category_list:Category[];
  public href: string = "";
  public url: string = "";
  public edithref: string = "";

  constructor(public activeModal: NgbActiveModal,
    private route: ActivatedRoute,
    private router: Router,
    private categoryService:CategoryService) { 
    }

  ngOnInit() {

    this.href   = this.router.url;
    this.edithref  = this.router.url.split("/")[2];

    console.log(this.edithref);

    if(this.href == '/subcategory/sub-category-add' || this.edithref == 'sub-category-edit')
    {
        this.url = "sub-category"
    }
    else if(this.href == '/category/category-add' || this.edithref == 'category-edit')
    {
      this.url = "category"
    }

    this.categoryService.getAll().subscribe((category_list)=>{
      console.log(category_list);
      this.category_list=category_list;
      },(error)=>{
        console.log(error);
      })

    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.categoryService.get(id).subscribe((category: Category) => {
          if (category) {
            this.cat = category;
          } else {
            console.log(`category with id '${category.id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
      else
      {
        this.cat = new Category();
        if(this.href == '/subcategory/sub-category-add')
        {
          this.childCat = new Category();
          this.childCat.id = null;
          this.cat.category = this.childCat;
        }
      }
    });
  }

ngOnDestroy() {
  this.sub.unsubscribe();
}

save() {
  this.categoryService.save(this.cat).subscribe(result => {
    console.log(result);
    this.gotoList();
  }, error => console.error(error));
}

gotoList() {

  if(this.href == '/subcategory/sub-category-add' || this.edithref == 'sub-category-edit')
    {
      this.router.navigate(['/subcategory/sub-category-list']);
    }
    else if(this.href == '/category/category-add' || this.edithref == 'category-edit')
    {
      this.router.navigate(['category/category-list']);
    }
  //this.router.navigate(['category-list'], {relativeTo: this.route});
}
}
