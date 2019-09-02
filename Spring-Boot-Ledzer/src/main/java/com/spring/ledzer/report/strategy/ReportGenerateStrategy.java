package com.spring.ledzer.report.strategy;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ReportGenerateStrategy {
	
	public void setHeader(String type, String[] header);
	
	public ByteArrayInputStream buildReport(List<? extends Object> list);


}
