package com.spring.ledzer.model;

public enum Status {

	ACTIVE("A"), DEACTIVE("D");
	
	private String shortName;
 	
	private Status(String shortName) {
		this.shortName = shortName;
	}
	 
	public String getShortName() {
		return shortName;
	}

	public static Status fromShortName(String shortName) {
        switch (shortName) {
        case "A":
            return Status.ACTIVE;
 
        case "D":
            return Status.DEACTIVE;
 
        default:
            throw new IllegalArgumentException("ShortName [" + shortName
                    + "] not supported.");
        }
    }

}



   
 
    
