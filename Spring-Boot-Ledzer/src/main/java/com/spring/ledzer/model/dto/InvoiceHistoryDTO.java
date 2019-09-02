package com.spring.ledzer.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InvoiceHistoryDTO {
	
	private Long id;
	private Long invoiceNo;
	private String name;
	private BigDecimal billAmt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date invoiceDate;
	private BigDecimal roundAmt;
	private BigDecimal invoiceAmt;
	private BigDecimal discountAmt;
	private BigDecimal taxAmt;
	private BigDecimal amountPaid;
	private BigDecimal amountDue;
	private String accountName;
	private String paymentModeName;
	private String chequeNo;
	private String bankName;
	private String customerName;
	
	public InvoiceHistoryDTO()
	{
		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(Long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(BigDecimal billAmt) {
		this.billAmt = billAmt;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	public BigDecimal getRoundAmt() {
		return roundAmt;
	}




	public void setRoundAmt(BigDecimal roundAmt) {
		this.roundAmt = roundAmt;
	}

	public BigDecimal getInvoiceAmt() {
		return invoiceAmt;
	}




	public void setInvoiceAmt(BigDecimal invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}




	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}




	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}




	public BigDecimal getTaxAmt() {
		return taxAmt;
	}




	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}




	public BigDecimal getAmountPaid() {
		return amountPaid;
	}




	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}




	public BigDecimal getAmountDue() {
		return amountDue;
	}




	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}


	public String getChequeNo() {
		return chequeNo;
	}




	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}




	public String getBankName() {
		return bankName;
	}




	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getPaymentModeName() {
		return paymentModeName;
	}


	public void setPaymentModeName(String paymentModeName) {
		this.paymentModeName = paymentModeName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
