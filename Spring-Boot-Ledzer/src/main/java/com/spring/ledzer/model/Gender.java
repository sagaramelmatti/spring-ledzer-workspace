package com.spring.ledzer.model;

public enum Gender {

	MALE("M"), FEMALE("F");
	
	private String shortName;
 	
	private Gender(String shortName) {
		this.shortName = shortName;
	}
	 
	public String getShortName() {
		return shortName;
	}

	public static Gender fromShortName(String shortName) {
        switch (shortName) {
        case "M":
            return Gender.MALE;
 
        case "F":
            return Gender.FEMALE;
 
        default:
            throw new IllegalArgumentException("ShortName [" + shortName
                    + "] not supported.");
        }
    }

}



   
 
    
