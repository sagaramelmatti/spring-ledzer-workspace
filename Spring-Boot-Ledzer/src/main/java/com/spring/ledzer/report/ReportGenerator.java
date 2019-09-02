package com.spring.ledzer.report;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ReportGenerator {
	
	@Autowired
	private ReportBuilder reportBuilder;

	public ReportGenerator() {
	}
	
	public ReportGenerator(ReportBuilder reportBuilder) {
		this.reportBuilder = reportBuilder;
	}
	
	public Report getReport()
	{
		return reportBuilder.getReport();
	}
	
	public ByteArrayInputStream generateReport(String[] header,String footer,String type,List<? extends Object> list)
	{
		this.reportBuilder.buildType(type);
		this.reportBuilder.buildHeader(header);
		this.reportBuilder.bulidFooter(footer);
		return this.reportBuilder.buildContent(list);
	}

}
