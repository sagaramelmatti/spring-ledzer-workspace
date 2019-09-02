import { Component, ViewChild, TemplateRef, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import  { InvoiceService}  from '../../../shared_service/invoice.service';
import 	{	Invoice}  from '../invoice';

import { UserService } from '../user.service';
import { User } from '../user';
import { MatSort, MatTableDataSource, MatPaginator, MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar, MatIconModule } from '@angular/material';
import { Alert } from '../../../../../node_modules/@types/selenium-webdriver';

@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.css'],
  
})
export class InvoiceListComponent implements OnInit {

  @ViewChild('paginator', { static: false, }) paginator: MatPaginator;

  public invoice_list:Invoice[];

    showTable: boolean;
    statusMessage: string;
    isLoaded: boolean = true;
    displayedColumnsInvoices: string[] = ['id', 'name', 'invoiceDate', 'customerName', 'invoiceAmt', 'taxAmt', 'discountAmt', 'amountPaid', 'amountDue', 'Edit'];
    dataSourceInvoicesList: any;

    constructor(private invoiceService: InvoiceService, public dialog: MatDialog) {
    }

    @ViewChild('sort', { static: false, }) sort: MatSort;

    ngOnInit() {
      this.isLoaded = true;
      this.invoiceService.getAllInvoices().subscribe((data)=>{
        console.log(data);
        this.invoice_list=data;

        this.isLoaded = false;
        this.dataSourceInvoicesList = new MatTableDataSource(this.invoice_list);
        this.dataSourceInvoicesList.sort = this.sort;
        this.dataSourceInvoicesList.paginator = this.paginator;

        },(error)=>{
          this.isLoaded = false;
          console.log(error);
        })
        
    }

    applyFilter(filterValue: string) {
        this.dataSourceInvoicesList.filter = filterValue.trim().toLowerCase();

        if (this.dataSourceInvoicesList.paginator) {
            this.dataSourceInvoicesList.paginator.firstPage();
        }
    }

    show() {
        this.showTable = true;

    }
    cancel() {
        this.showTable = false;
    }
}
