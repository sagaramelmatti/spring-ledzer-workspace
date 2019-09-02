package com.spring.ledzer.model;

public enum PaymentType {

	INVOICE_PAYMENT("IP"), PART_PAYMENT("PP");
	
	private String shortName;
 	
	private PaymentType(String shortName) {
		this.shortName = shortName;
	}
	 
	public String getShortName() {
		return shortName;
	}

	public static PaymentType fromShortName(String shortName) {
        switch (shortName) {
        case "IP":
            return PaymentType.INVOICE_PAYMENT;
 
        case "PP":
            return PaymentType.PART_PAYMENT;
 
        default:
            throw new IllegalArgumentException("ShortName [" + shortName
                    + "] not supported.");
        }
    }

}



   
 
    
