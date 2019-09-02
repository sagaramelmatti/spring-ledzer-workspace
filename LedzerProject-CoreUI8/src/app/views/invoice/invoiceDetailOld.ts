import { Product } from '../product/product';
import { Uom } from '../uom/uom';


export class InvoiceDetailOld {

    id : number;
    name: string;
    hsnCode : string;
    rate : number;
    ratepre : number;
    salerate : number;
    saleratepre : number;
    uom : Uom;
    quantity : number;
    quantitypre : number;
    stock : number;
    total : number;
    totalpre : number;
    billAmt : number;
    discPer : number;
    discPre : number;
    discAmt : number;
    taxableAmt : number;
    gst : number;
    gstold : number;
    gstamt : number;
    gstamtpre : number;
    sgstPer : number;
    sgstAmt : number;
    cgstPer : number;
    cgstAmt : number;
    igstPer : number;
    igstAmt : number;
    product : Product;
    countval : number;
}
