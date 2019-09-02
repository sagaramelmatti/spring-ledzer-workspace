import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import  { SupplierService}  from '../../../shared_service/supplier.service';
import 	{	Supplier}  from '../supplier';

@Component({
  selector: 'app-supplier-list',
  templateUrl: './supplier-list.component.html',
  styleUrls: ['./supplier-list.component.css']
})
export class SupplierListComponent implements OnInit {

  public supplier_list:Supplier[];
  
  constructor(private supplierService: SupplierService,private router: Router) { }
  
  ngOnInit() {

	this.supplierService.getAll().subscribe((supplier_list)=>{
	  console.log(supplier_list);
	  this.supplier_list=supplier_list;
    },(error)=>{
      console.log(error);
    })
  }

  gotoList() {
    this.router.navigate(['supplier/supplier-list']);
    //this.router.navigate(['supplier-list'], {relativeTo: this.route});
  }
  
  remove(supplier : Supplier) {
  const directive = this;
  const result = confirm('Do you really want to delete Supplier?');
      if (result) {
        this.supplierService.remove(supplier).subscribe( data => {
          this.supplier_list = this.supplier_list.filter(u => u !== supplier);
        })
      } else {
        return false;
      }
  }

}
