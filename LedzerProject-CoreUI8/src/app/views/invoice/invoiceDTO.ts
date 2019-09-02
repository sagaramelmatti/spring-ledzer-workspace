import { Business } from '../business/business';
import { Customer } from '../customer/customer';
import { InvoiceDetail } from './invoiceDetail';

export class InvoiceDTO {

    id : number;
    invoiceDate : Date;
    billAmt : number;
    roundAmt : number;
    invoiceAmt : number;
    discountAmt : number;
    taxAmt : number;
    amountPaid : number;
    amountDue : number;
    customerId : number;
    accountId : number;
    paymentModeId : number;
    chequeNo: string;
    bankName: string;
}
