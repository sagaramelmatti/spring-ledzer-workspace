import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import  { CustomerService}  from '../../../shared_service/customer.service';
import 	{	Customer}  from '../customer';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  public customer_list:Customer[];
   
  constructor(private customerService: CustomerService,private router: Router) { }
  
  ngOnInit() {

	this.customerService.getAll().subscribe((customer_list)=>{
	  console.log(customer_list);
	  this.customer_list=customer_list;
    },(error)=>{
      console.log(error);
    })
  }

  gotoList() {
    this.router.navigate(['customer/customer-list']);
    //this.router.navigate(['customer-list'], {relativeTo: this.route});
  }
  
  remove(customer : Customer) {
  const directive = this;
  const result = confirm('Do you really want to delete Customer?');
      if (result) {
        this.customerService.remove(customer).subscribe( data => {
          this.customer_list = this.customer_list.filter(u => u !== customer);
        })
      } else {
        return false;
      }
  }

}
