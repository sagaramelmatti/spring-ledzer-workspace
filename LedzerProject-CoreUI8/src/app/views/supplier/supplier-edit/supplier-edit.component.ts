import { Component, OnInit } from '@angular/core';
import  { Subscription } from 'rxjs';
import  { ActivatedRoute, Router } from '@angular/router';
import  { SupplierService  }  from '../../../shared_service/supplier.service';
import 	{	Supplier}  from '../supplier';
import 	{	State}  from '../../customer/state';
import  { CommonService  }  from '../../../shared_service/common.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-supplier-edit',
  templateUrl: './supplier-edit.component.html',
  styleUrls: ['./supplier-edit.component.css']
})
export class SupplierEditComponent implements OnInit {

  public sub: Subscription;
  public supplier: Supplier = new Supplier();
  public state_list:State[];
  public state: State;
  
  constructor(public activeModal: NgbActiveModal,
    private route: ActivatedRoute,
    private router: Router,
    private supplierService:SupplierService,
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
        this.supplierService.get(id).subscribe((supplier: Supplier) => {
          if (supplier) {
            this.supplier = supplier;
          } else {
            console.log(`supplier with id '${supplier.id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
      else
      {
        this.supplier = new Supplier(); 
  	    this.state = new State();
	      this.state.id = null;
	      this.supplier.state = this.state;     
      }
    });
  }

ngOnDestroy() {
  this.sub.unsubscribe();
}

save() {
  this.supplierService.save(this.supplier).subscribe(result => {
    console.log(result);
    this.gotoList();
  }, error => console.error(error));
}

gotoList() {
  this.router.navigate(['supplier/supplier-list']);
  //this.router.navigate(['supplier-list'], {relativeTo: this.route});
}

}
