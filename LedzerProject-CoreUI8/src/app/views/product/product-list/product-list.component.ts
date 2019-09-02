import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import  { ProductService}  from '../../../shared_service/product.service';
import 	{	Product}  from '../product';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  public product_list:Product[];
   
  constructor(private productService: ProductService,private router: Router) { }
  
  ngOnInit() {

	this.productService.getAll().subscribe((product_list)=>{
	  console.log(product_list);
	  this.product_list=product_list;
    },(error)=>{
      console.log(error);
    })
  }

  gotoList() {
    this.router.navigate(['product/product-list']);
    //this.router.navigate(['product-list'], {relativeTo: this.route});
  }
  
  remove(product : Product) {
  const directive = this;
  const result = confirm('Do you really want to delete Product?');
      if (result) {
        this.productService.remove(product).subscribe( data => {
          this.product_list = this.product_list.filter(u => u !== product);
        })
      } else {
        return false;
      }
  }

}
