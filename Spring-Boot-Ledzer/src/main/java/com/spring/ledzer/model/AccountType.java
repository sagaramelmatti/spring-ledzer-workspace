package com.spring.ledzer.model;

public enum AccountType {
	
	SAVING("1"), CURRENT("2");
	
	private String shortName;
 	
	private AccountType(String shortName) {
		this.shortName = shortName;
	}
	 
	public String getShortName() {
		return shortName;
	}

	public static AccountType fromShortName(String shortName) {
        switch (shortName) {
        case "1":
            return AccountType.SAVING;
 
        case "2":
            return AccountType.CURRENT;
 
        default:
            throw new IllegalArgumentException("ShortName [" + shortName
                    + "] not supported.");
        }
    }
	

}
