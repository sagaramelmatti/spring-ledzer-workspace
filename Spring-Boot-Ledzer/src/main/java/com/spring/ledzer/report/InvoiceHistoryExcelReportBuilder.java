package com.spring.ledzer.report;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class InvoiceHistoryExcelReportBuilder implements ReportBuilder {
	
	@Autowired
	private Report report;
	
	@Override
	public void buildHeader(String[] header) {
		report.setHeader(header);
		
	}

	@Override
	public ByteArrayInputStream buildContent(List<? extends Object> object_list) {
		return null ; //report.setContent(object_list);
		
	}

	@Override
	public void bulidFooter(String footer) {
		report.setFooter(footer);
		
	}

	@Override
	public void buildType(String type) {
		report.setType(type);
		
	}

	@Override
	public Report getReport() {
		return this.report;
	}

	public InvoiceHistoryExcelReportBuilder(Report report) {
		this.report = report;
	}

	

}
