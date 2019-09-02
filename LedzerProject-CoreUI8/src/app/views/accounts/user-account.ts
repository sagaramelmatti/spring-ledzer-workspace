import { AccountGroup } from './account-group';
import { AccountType } from './account-type.enum';
import { DefaultAccSts } from './default-acc-sts.enum';

export class UserAccount {

    id              : number;
    name            : string;
    accountGroup    : AccountGroup;
    effectiveDate   : Date;
    balance         : number;
    bankName        : string;
    accountNo       : string;
    accountType     : AccountType;
    micr            : string;
    rtgs            : string;
    ifsc            : string;
    city            : string;
    state           : string;
    address         : string;
    defaultAccSts   : DefaultAccSts;
    
    
}
