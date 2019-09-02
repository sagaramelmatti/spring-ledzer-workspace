import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import  { UomService}  from '../../../shared_service/uom.service';
import 	{	Uom}  from '../uom';


@Component({
  selector: 'app-uom-list',
  templateUrl: './uom-list.component.html',
  styleUrls: ['./uom-list.component.css']
})
export class UomListComponent implements OnInit {
	
	public uom_list:Uom[];

  constructor(private uomService: UomService,private router: Router) { }

  ngOnInit() {
    this.uomService.getAll().subscribe((uom_list)=>{
      console.log(uom_list);
      this.uom_list=uom_list;
      },(error)=>{
        console.log(error);
      })
  }

  gotoList() {
    this.router.navigate(['uom/uom-list']);
    //this.router.navigate(['Uom-list'], {relativeTo: this.route});
  }
  
  remove(uom : Uom) {
  const directive = this;
  const result = confirm('Do you really want to delete Uom?');
      if (result) {
        this.uomService.remove(uom).subscribe( data => {
          this.uom_list = this.uom_list.filter(u => u !== uom);
        })
      } else {
        return false;
      }
  }

}
