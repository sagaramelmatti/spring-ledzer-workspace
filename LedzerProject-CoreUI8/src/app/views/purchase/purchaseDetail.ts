import { Product } from '../product/product';

export class PurchaseDetail {
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
    purchaseId      : number;
}
