package com.spring.ledzer.report;

import org.springframework.stereotype.Component;


@Component
public class Report 
{
	
	public String[] header;
	public String footer;
	public String type;
	
	
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header2) {
		this.header = header2;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
