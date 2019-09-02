import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import  { TaxService}  from '../../../shared_service/tax.service';
import 	{	Tax}  from '../tax';

@Component({
  selector: 'app-taxlist',
  templateUrl: './taxlist.component.html',
  styleUrls: ['./taxlist.component.css']
})
export class TaxlistComponent implements OnInit {

  public tax_list:Tax[];
   
  constructor(private taxService: TaxService,private router: Router) { }
  
  ngOnInit() {

	this.taxService.getAll().subscribe((tax_list)=>{
	  console.log(tax_list);
	  this.tax_list=tax_list;
    },(error)=>{
      console.log(error);
    })
  }

  gotoList() {
    this.router.navigate(['taxes/tax-list']);
    //this.router.navigate(['tax-list'], {relativeTo: this.route});
  }
  
  remove(tax : Tax) {
  const directive = this;
  const result = confirm('Do you really want to delete Tax?');
      if (result) {
        this.taxService.remove(tax).subscribe( data => {
          this.tax_list = this.tax_list.filter(u => u !== tax);
        })
      } else {
        return false;
      }
  }
}
