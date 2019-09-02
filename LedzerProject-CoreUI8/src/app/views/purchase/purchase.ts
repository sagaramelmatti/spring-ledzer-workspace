import { PurchaseDetail } from './purchaseDetail';

export class Purchase {

    id : number;
    name: string;
    purchaseNo : number;
    purchaseDate : String;
    discountAmt : number;
    taxAmt : number;
    taxAmtHid : number;
    amountPaid : number;
    amountPaidHid : number;
    amountDue : number;
    amountDueHid : number;
    supplierId : number;
    accountId : number;
    paymentModeId : number;
    chequeNo: string;
    bankName: string;
    purchaseDetails: Array<PurchaseDetail> = [];
    billAmt : number;
    billAmtHid : number;
    roundAmt : number;
    purchaseAmt : number;
    purchaseAmtHid : number;
    voucher : String;
    supplierName : String;
}
