import  { InvoiceService}  from '../../shared_service/invoice.service';
import { Observable } from 'rxjs/Observable';
import 	{Invoice}  from './invoice';
import 	{InvoiceDetail}  from './invoiceDetail';
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import {catchError, finalize} from "rxjs/operators";
import {of} from "rxjs/observable/of";

export class InvoiceDetailDataSource extends DataSource<InvoiceDetail> {
   
    /*
    connect(): Observable<InvoiceDetail[]> {
      return this.invoiceService.findAllInvoicesDetails();
    }
    */
    
    private invoiceDetailsSubject = new BehaviorSubject<InvoiceDetail[]>([]);

    private loadingInvoiceDetails = new BehaviorSubject<boolean>(false);

    public loading$ = this.loadingInvoiceDetails.asObservable();

    constructor(private invoiceService: InvoiceService) {
        super();
      }

    loadLessons(invoiceId:number) 
    {

        this.loadingInvoiceDetails.next(true);
        this.invoiceService.findAllInvoicesDetails(invoiceId).pipe(
                catchError(() => of([])),
                finalize(() => this.loadingInvoiceDetails.next(false))
            )
            .subscribe(lessons => this.invoiceDetailsSubject.next(lessons));
    }

    connect(collectionViewer: CollectionViewer): Observable<InvoiceDetail[]> {
        console.log("Connecting data source");
        return this.invoiceDetailsSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.invoiceDetailsSubject.complete();
        this.loadingInvoiceDetails.complete();
    }
  }