import  { Component, OnInit } from '@angular/core';
import  { Subscription } from 'rxjs';
import  { ActivatedRoute, Router } from '@angular/router';
import  { CustomerService  }  from '../../../shared_service/customer.service';

import  { NgForm } from '@angular/forms';
import 	{	Customer}  from '../customer';
import 	{	State}  from '../state';
import  { CommonService  }  from '../../../shared_service/common.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.css']
})
export class CustomerEditComponent implements OnInit {

  sub: Subscription;
  customer: Customer = new Customer();
  public state_list:State[];
  state: State;
  
  constructor(public activeModal: NgbActiveModal,
    private route: ActivatedRoute,
    private router: Router,
    private customerService:CustomerService,
    private commonService:CommonService) { }

  ngOnInit() {

    this.commonService.getAll().subscribe((state_list)=>{
      console.log(state_list);
      this.state_list=state_list;
      },(error)=>{
        console.log(error);
      })


    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) 
      {
        this.customerService.get(id).subscribe((customer: Customer) => {
          if (customer) {
            this.customer = customer;
          } else {
            console.log(`customer with id '${customer.id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
      else
      {
        this.customer = new Customer(); 
  	   this.state = new State();
	     this.state.id = null;
	     this.customer.state = this.state;     
      }
    });
  }

ngOnDestroy() {
  this.sub.unsubscribe();
}

save() {
  this.customerService.save(this.customer).subscribe(result => {
    console.log(result);
    this.gotoList();
  }, error => console.error(error));
}

gotoList() {
  this.router.navigate(['customer/customer-list']);
  //this.router.navigate(['customer-list'], {relativeTo: this.route});
}

}
