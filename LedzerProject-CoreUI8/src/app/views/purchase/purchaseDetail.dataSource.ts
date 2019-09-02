import  { PurchaseService}  from '../../shared_service/purchase.service';
import { Observable } from 'rxjs/Observable';
import 	{Purchase}  from './purchase';
import 	{PurchaseDetail}  from './purchaseDetail';
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import {catchError, finalize} from "rxjs/operators";
import {of} from "rxjs/observable/of";

export class PurchaseDetailDataSource extends DataSource<PurchaseDetail> {
   
    /*
    connect(): Observable<PurchaseDetail[]> {
      return this.purchaseService.findAllPurchasesDetails();
    }
    */
    
    private purchaseDetailsSubject = new BehaviorSubject<PurchaseDetail[]>([]);

    private loadingPurchaseDetails = new BehaviorSubject<boolean>(false);

    public loading$ = this.loadingPurchaseDetails.asObservable();

    constructor(private purchaseService: PurchaseService) {
        super();
      }

    loadLessons(purchaseId:number) 
    {

        this.loadingPurchaseDetails.next(true);
        this.purchaseService.findAllPurchasesDetails(purchaseId).pipe(
                catchError(() => of([])),
                finalize(() => this.loadingPurchaseDetails.next(false))
            )
            .subscribe(lessons => this.purchaseDetailsSubject.next(lessons));
    }

    connect(collectionViewer: CollectionViewer): Observable<PurchaseDetail[]> {
        console.log("Connecting data source");
        return this.purchaseDetailsSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.purchaseDetailsSubject.complete();
        this.loadingPurchaseDetails.complete();
    }
  }