import  { Component, OnInit } from '@angular/core';
import  { Subscription } from 'rxjs';
import  { ActivatedRoute, Router } from '@angular/router';
import  { UomService}  from '../../../shared_service/uom.service';
import 	{	Uom}  from '../uom';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-uom-edit',
  templateUrl: './uom-edit.component.html',
  styleUrls: ['./uom-edit.component.css']
})
export class UomEditComponent implements OnInit {

  sub: Subscription;
  uom: Uom = new Uom();

  constructor(public activeModal: NgbActiveModal,
    private route: ActivatedRoute,
    private router: Router,
    private uomService:UomService) { }

    ngOnInit() {
      this.sub = this.route.params.subscribe(params => {
        const id = params['id'];
        if (id) {
          this.uomService.get(id).subscribe((uom: Uom) => {
            if (uom) {
              this.uom = uom;
            } else {
              console.log(`uom with id '${uom.id}' not found, returning to list`);
              this.gotoList();
            }
          });
        }
        else
        {
          this.uom = new Uom();
        }
      });
    }
  
  ngOnDestroy() {
    this.sub.unsubscribe();
  }
  
  save() {
    this.uomService.save(this.uom).subscribe(result => {
      console.log(result);
      this.gotoList();
    }, error => console.error(error));
  }
  
  gotoList() {
    this.router.navigate(['uom/uom-list']);
    //this.router.navigate(['uom-list'], {relativeTo: this.route});
  }

}
