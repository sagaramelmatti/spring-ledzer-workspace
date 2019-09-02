import { Component, OnInit } from '@angular/core';
import  { Subscription } from 'rxjs';
import  { ActivatedRoute, Router } from '@angular/router';
import  { AccountsService  }  from '../../../shared_service/accounts.service';
import 	{	UserAccount}  from '../user-account';
import 	{	AccountGroup}  from '../account-group';
import  { CommonService  }  from '../../../shared_service/common.service';
import { DatePipe } from '@angular/common';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';


export enum AccountType {
  SAVING = "SAVING", CURRENT = "CURRENT"
}

@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css']
})
export class AccountEditComponent implements OnInit {

  sub: Subscription;
  account: UserAccount = new UserAccount();
  account_group_list:AccountGroup[];
  accountGroup: AccountGroup;

  account_types = AccountType;
  keys: any[];
  
  constructor(public activeModal: NgbActiveModal,
    private route: ActivatedRoute,
    private router: Router,
    private accountsService:AccountsService,
    private commonService:CommonService) { 

    this.keys = Object.keys(this.account_types).filter(String)
    }

  ngOnInit() {

    this.accountsService.getAll().subscribe((account_group_list)=>{
      this.account_group_list=account_group_list;
      },(error)=>{
        console.log(error);
      }),

    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) 
      {
        this.accountsService.getAccount(id).subscribe((account: UserAccount) => {
          if (account) {
            console.log(account);
            this.account = account;
          } else {
            console.log(`account with id '${account.id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
      else
      {
        this.account = new UserAccount(); 
  	    this.accountGroup = new AccountGroup();
	      this.accountGroup.id = null;
        this.account.accountGroup = this.accountGroup; 
        this.account.accountType = null; 
        
      }
    });
  }

ngOnDestroy() {
  this.sub.unsubscribe();
}

save() {
  this.accountsService.saveAccount(this.account).subscribe(result => {
    console.log(result);
    this.gotoList();
  }, error => console.error(error));
}

gotoList() {
  this.router.navigate(['accounts/account-list']);
  //this.router.navigate(['account-list'], {relativeTo: this.route});
}

}
