import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import  { AccountsService}  from '../../../shared_service/accounts.service';
import 	{	AccountGroup}  from '../account-group';

@Component({
  selector: 'app-accountgroup-list',
  templateUrl: './accountgroup-list.component.html',
  styleUrls: ['./accountgroup-list.component.css']
})
export class AccountgroupListComponent implements OnInit {

  public accountgroup_list:AccountGroup[];
  public href: string = "";
  public url: string = "";

  constructor(private accountsService: AccountsService,private router: Router) { }

  ngOnInit() {
    this.accountsService.getAll().subscribe((accountgroup_list)=>{
      console.log(accountgroup_list);
      this.accountgroup_list=accountgroup_list;
      },(error)=>{
        console.log(error);
      })
  }

  gotoList() {
    this.router.navigate(['accounts/accountgroup-list']);
    //this.router.navigate(['Uom-list'], {relativeTo: this.route});
  }
  
  remove(accountGroup : AccountGroup) {
  const directive = this;
  const result = confirm('Do you really want to delete AccountGroup ?');
      if (result) {
        this.accountsService.remove(accountGroup).subscribe( data => {
          this.accountgroup_list = this.accountgroup_list.filter(u => u !== accountGroup);
        })
      } else {
        return false;
      }
  }

}
