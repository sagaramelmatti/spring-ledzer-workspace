import { Product } from '../product/product';
import { Uom } from '../uom/uom';
import { Invoice } from '../invoice/invoice';
import { Tax } from '../tax/tax';


export class InvoiceDetail {

    id              : number;
    preProductId    : number;
    productId       : number;
    hsnCode         : string;
    rate            : number;
    uomId           : number;
    quantity        : number;
    preQuantity     : number;
    totalPrice      : number;
    discPer         : number;
    discAmt         : number;
    taxableAmt      : number;
    sgstPer         : number;
    sgstAmt         : number;
    cgstPer         : number;
    cgstAmt         : number;
    igstPer         : number;
    igstAmt         : number;
    invoiceId       : number;
}
