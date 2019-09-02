import { Tax } from '../tax/tax';
import { Uom } from '../uom/uom';
import { Category } from '../category/category';

export class Product {

    id : number;
    name: string;
    code : string;
    saleTaxId : Tax;
    salePrice : number;
    purchasePrice : number;
    discountPer : number;
    uom : Uom;
    category : Category;
    purchaseTaxId : Tax;
    description : string;
    stock : number;
    lastStockDate : Date;
}
