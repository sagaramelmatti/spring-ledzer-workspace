import { Component, ViewChild, TemplateRef, OnInit, Inject } from '@angular/core';
import  { PurchaseService}  from '../../../shared_service/purchase.service';
import 	{	Purchase}  from '../purchase';

import { MatSort, MatTableDataSource, MatPaginator, MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar, MatIconModule } from '@angular/material';

@Component({
  selector: 'app-purchase-list',
  templateUrl: './purchase-list.component.html',
  styleUrls: ['./purchase-list.component.scss']
})
export class PurchaseListComponent implements OnInit {

  @ViewChild('paginator', {static: false}) paginator: MatPaginator;

  public purchase_list:Purchase[];
  public  showTable: boolean;
  public  statusMessage: string;
  public  isLoaded: boolean = true;
  public  displayedColumnsPurchases: string[] = ['id', 'name', 'purchaseDate', 'supplierName', 'purchaseAmt', 'taxAmt', 'discountAmt', 'amountPaid', 'amountDue', 'Edit'];
  public  dataSourcePurchasesList: any;
  
  constructor(private purchaseService: PurchaseService, public dialog: MatDialog) {
  }

    @ViewChild('sort', { static: false, }) sort: MatSort;

    ngOnInit() {
      this.isLoaded = true;
      this.purchaseService.getAllPurchases().subscribe((data)=>{
        console.log(data);
        this.purchase_list=data;

        this.isLoaded = false;
        this.dataSourcePurchasesList = new MatTableDataSource(this.purchase_list);
        this.dataSourcePurchasesList.sort = this.sort;
        this.dataSourcePurchasesList.paginator = this.paginator;

        },(error)=>{
          this.isLoaded = false;
          console.log(error);
        })
        
    }

    applyFilter(filterValue: string) {
        this.dataSourcePurchasesList.filter = filterValue.trim().toLowerCase();

        if (this.dataSourcePurchasesList.paginator) {
            this.dataSourcePurchasesList.paginator.firstPage();
        }
    }

    show() {
        this.showTable = true;

    }
    cancel() {
        this.showTable = false;
    }
}
