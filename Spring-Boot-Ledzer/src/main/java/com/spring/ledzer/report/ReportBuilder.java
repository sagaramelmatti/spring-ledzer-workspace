package com.spring.ledzer.report;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ReportBuilder {
	
	public void buildHeader(String[] header); 
	  
    public void bulidFooter(String footer); 
  
    public void buildType(String type); 
  
    public Report getReport();

	ByteArrayInputStream buildContent(List<? extends Object> list);


}
