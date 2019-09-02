package com.spring.ledzer.report.strategy;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class ReportGenerateContext {
	
	private ReportGenerateStrategy reportGenerateStrategy;
	
	public ReportGenerateContext() {
		super();
	}

	public ReportGenerateStrategy getReportGenerateStrategy() {
		return reportGenerateStrategy;
	}

	public void setReportGenerateStrategy(ReportGenerateStrategy reportGenerateStrategy) {
		this.reportGenerateStrategy = reportGenerateStrategy;
	}

	  public ByteArrayInputStream buildReport(String[] header_array, String type, List<? extends Object> list) {
		  
		 reportGenerateStrategy.setHeader(type,header_array); 
		  
	    return reportGenerateStrategy.buildReport(list);

	  }

}
