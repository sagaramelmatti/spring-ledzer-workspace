import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import{TaxService}  from '../../../shared_service/tax.service';
import { NgForm } from '@angular/forms';
import 	{	Tax}  from '../tax';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-taxedit',
  templateUrl: './taxedit.component.html',
  styleUrls: ['./taxedit.component.css']
})
export class TaxeditComponent implements OnInit {

  sub: Subscription;
  tax: Tax = new Tax();

  constructor(public activeModal: NgbActiveModal,
    private route: ActivatedRoute,
    private router: Router,
    private taxService:TaxService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.taxService.get(id).subscribe((tax: Tax) => {
          if (tax) {
            this.tax = tax;
          } else {
            console.log(`tax with id '${tax.id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
      else
      {
        this.tax = new Tax();
      }
    });
  }

ngOnDestroy() {
  this.sub.unsubscribe();
}

save() {
  this.taxService.save(this.tax).subscribe(result => {
    console.log(result);
    this.gotoList();
  }, error => console.error(error));
}

gotoList() {
  this.router.navigate(['taxes/tax-list']);
  //this.router.navigate(['tax-list'], {relativeTo: this.route});
}

}
