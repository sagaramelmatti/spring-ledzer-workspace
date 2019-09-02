import { Component, OnInit } from '@angular/core';
import  { Subscription } from 'rxjs';
import  { ActivatedRoute, Router } from '@angular/router';
import  { ProductService  }  from '../../../shared_service/product.service';
import 	{	Product}  from '../product';
import 	{	Tax}  from '../../tax/tax';
import  { CommonService  }  from '../../../shared_service/common.service';
import  { TaxService  }  from '../../../shared_service/tax.service';
import  { CategoryService  }  from '../../../shared_service/category.service';
import  { UomService  }  from '../../../shared_service/uom.service';
import  { Category } from '../../category/category';
import  { Uom } from '../../uom/uom';
import { DatePipe } from '@angular/common';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

  public sub: Subscription;
  public product: Product = new Product();
  public tax_list:Tax[];
  public category_list:Category[];
  public uom_list:Uom[];
  public saleTaxId: Tax;
  public purchaseTaxId : Tax;
  public category : Category;
  public uom : Uom;
  
  constructor(public activeModal: NgbActiveModal,
    private route: ActivatedRoute,
    private router: Router,
    private productService:ProductService,
    private commonService:CommonService,
    private taxService:TaxService,
    private categoryService:CategoryService,
    private uomService:UomService) { }

  ngOnInit() {

    this.taxService.getAll().subscribe((tax_list)=>{
      this.tax_list=tax_list;
      },(error)=>{
        console.log(error);
      }),

      this.categoryService.getAll().subscribe((category_list)=>{
        this.category_list=category_list;
        },(error)=>{
          console.log(error);
      }),
      this.uomService.getAll().subscribe((uom_list)=>{
        this.uom_list=uom_list;
        },(error)=>{
          console.log(error);
      }),


    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) 
      {
        this.productService.get(id).subscribe((product: Product) => {
          if (product) {
            this.product = product;
          } else {
            console.log(`product with id '${product.id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
      else
      {
        this.product = new Product(); 
  	    this.saleTaxId = new Tax();
	      this.saleTaxId.id = null;
        this.product.saleTaxId = this.saleTaxId; 
        
  	    this.purchaseTaxId = new Tax();
	      this.purchaseTaxId.id = null;
        this.product.purchaseTaxId = this.purchaseTaxId; 

        this.category = new Category();
	      this.category.id = null;
        this.product.category = this.category; 

        this.uom = new Uom();
	      this.uom.id = null;
        this.product.uom = this.uom; 
      }
    });
  }

ngOnDestroy() {
  this.sub.unsubscribe();
}

save() {
  this.productService.save(this.product).subscribe(result => {
    console.log(result);
    this.gotoList();
  }, error => console.error(error));
}

gotoList() {
  this.router.navigate(['product/product-list']);
  //this.router.navigate(['product-list'], {relativeTo: this.route});
}

}
