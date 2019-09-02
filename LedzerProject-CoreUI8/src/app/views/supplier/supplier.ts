export class Supplier {

	id 		: number;
    name	: String;
    mobno 	: String;
    address : String;
    city 	: String;
    pin 	: String;
    state 	: State;
    email 	: String;
    contactPerson : String;
    gstin 	: String;
    outBal 	: number;
    status 	: String;
}

export class State {
    id : number;
    name: String;
}