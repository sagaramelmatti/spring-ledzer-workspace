package com.spring.ledzer.model;

public enum DefaultAccSts {
	
	YES("Y"), NO("N");
	
	private String shortName;
 	
	private DefaultAccSts(String shortName) {
		this.shortName = shortName;
	}
	 
	public String getShortName() {
		return shortName;
	}

	public static DefaultAccSts fromShortName(String shortName) {
        switch (shortName) {
        case "Y":
            return DefaultAccSts.YES;
 
        case "N":
            return DefaultAccSts.NO;
 
        default:
            throw new IllegalArgumentException("ShortName [" + shortName
                    + "] not supported.");
        }
    }

}
