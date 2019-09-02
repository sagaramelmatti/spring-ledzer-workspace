import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import  { AccountsService}  from '../../../shared_service/accounts.service';
import 	{	UserAccount}  from '../user-account';

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {

  account_list:UserAccount[];
   
  constructor(private accountsService: AccountsService,private router: Router) { }
  
  ngOnInit() {

	this.accountsService.getAllAccounts().subscribe((account_list)=>{
	  console.log(account_list);
	  this.account_list=account_list;
    },(error)=>{
      console.log(error);
    })
  }

  gotoList() {
    this.router.navigate(['accounts/account-list']);
    //this.router.navigate(['account-list'], {relativeTo: this.route});
  }
  
  remove(account : UserAccount) {
  const directive = this;
  const result = confirm('Do you really want to delete UserAccount?');
      if (result) {
        this.accountsService.remove(account).subscribe( data => {
          this.account_list = this.account_list.filter(u => u !== account);
        })
      } else {
        return false;
      }
  }

}
