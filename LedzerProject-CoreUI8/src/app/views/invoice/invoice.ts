import { InvoiceDetail } from './invoiceDetail';

export class Invoice {

    id : number;
    name: string;
    invoiceNo : number;
    invoiceDate : String;
    discountAmt : number;
    taxAmt : number;
    taxAmtHid : number;
    amountPaid : number;
    amountPaidHid : number;
    amountDue : number;
    amountDueHid : number;
    customerId : number;
    accountId : number;
    paymentModeId : number;
    chequeNo: string;
    bankName: string;
    invoiceDetails: Array<InvoiceDetail> = [];
    billAmt : number;
    billAmtHid : number;
    roundAmt : number;
    invoiceAmt : number;
    invoiceAmtHid : number;
    customerName : String;
    
}
