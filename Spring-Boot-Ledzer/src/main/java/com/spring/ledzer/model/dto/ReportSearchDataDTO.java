package com.spring.ledzer.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by SagarMelmatti on 02/08/17.
 */
public class ReportSearchDataDTO {
	
    private Long customerId;
    
    private Date fromDate;
    
    private Date toDate;
    
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
