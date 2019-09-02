package com.spring.ledzer.report;

import java.io.ByteArrayInputStream;
import java.util.List;

public abstract class PdfReportBuilder implements ReportBuilder {
	
	
	private Report report;
	
	@Override
	public void buildHeader(String[] header) {
		report.setHeader(header);
		
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

	public PdfReportBuilder(Report report) {
		this.report = report;
	}


}
