package com.spring.ledzer.model;

public enum TransactionForm {
	
	DEBIT("D"), CREDIT("C");
	
	private String shortName;
 	
	private TransactionForm(String shortName) {
		this.shortName = shortName;
	}
	 
	public String getShortName() {
		return shortName;
	}

	public static TransactionForm fromShortName(String shortName) {
        switch (shortName) {
        case "D":
            return TransactionForm.DEBIT;
 
        case "C":
            return TransactionForm.CREDIT;
 
        default:
            throw new IllegalArgumentException("ShortName [" + shortName
                    + "] not supported.");
        }
    }
	

}
