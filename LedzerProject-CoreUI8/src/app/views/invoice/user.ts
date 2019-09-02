import { Product } from '../product/product';
import { Uom } from '../uom/uom';
import { Invoice } from '../invoice/invoice';

export class User{

    id          : number;
    product     : Product;
    hsnCode     : string;
    rate        : number;
    uom         : Uom;
    quantity    : number;
    billAmt     : number;
    discPer     : number;
    discAmt     : number;
    taxableAmt  : number;
    sgstPer     : number;
    sgstAmt     : number;
    cgstPer     : number;
    cgstAmt     : number;
    igstPer     : number;
    igstAmt     : number;
    invoice     : Invoice;
    
}