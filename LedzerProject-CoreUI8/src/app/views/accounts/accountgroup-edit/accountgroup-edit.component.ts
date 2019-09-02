import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import 	{	AccountGroup}  from '../account-group';
import  { AccountsService}  from '../../../shared_service/accounts.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-accountgroup-edit',
  templateUrl: './accountgroup-edit.component.html',
  styleUrls: ['./accountgroup-edit.component.css']
})
export class AccountgroupEditComponent implements OnInit {

    sub: Subscription;
    accGroup: AccountGroup = new AccountGroup();
    public accountgroup_list:AccountGroup[];
    parentAccountGroup : AccountGroup;

    constructor(public activeModal: NgbActiveModal,
    private route: ActivatedRoute,
    private router: Router,
    private accountsService: AccountsService) 
    { }
    
  ngOnInit() {

    this.accountsService.getAll().subscribe((accountgroup_list)=>{
      this.accountgroup_list=accountgroup_list;
      },(error)=>{
        console.log(error);
      }),

    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) 
      {
        this.accountsService.get(id).subscribe((accountGroup: AccountGroup) => {
          if (accountGroup) {
            this.accGroup = accountGroup;
          } else {
            console.log(`accountGroup with id '${accountGroup.id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
      else
      {
          this.accGroup = new AccountGroup(); 
          this.parentAccountGroup = new AccountGroup();
          this.parentAccountGroup.id = null;
          this.accGroup.accountGroup = this.parentAccountGroup;
      }
    });
  }

ngOnDestroy() {
  this.sub.unsubscribe();
}

save() {
  this.accountsService.save(this.accGroup).subscribe(result => {
    console.log(result);
    this.gotoList();
  }, error => console.error(error));
}

gotoList() {
  this.router.navigate(['accounts/accountgroup-list']);
  //this.router.navigate(['accountGroup-list'], {relativeTo: this.route});
}

}
