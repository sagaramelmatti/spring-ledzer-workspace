package com.spring.ledzer.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerWiseSaleHistoryDTO {
	
	private Long id;
	private String invoiceName;
	private String customerName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date invoiceDate;
	private BigDecimal invoiceAmt;
	private BigDecimal discountAmt;
	private BigDecimal taxAmt;
	private BigDecimal amountPaid;
	private BigDecimal amountDue;
	private String accountName;
	private String paymentModeName;
	private BigDecimal outAmtTillDate;
	
	public CustomerWiseSaleHistoryDTO()
	{
		
	}

	public Long getId() {
		return id;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
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

	public BigDecimal getOutAmtTillDate() {
		return outAmtTillDate;
	}

	public void setOutAmtTillDate(BigDecimal outAmtTillDate) {
		this.outAmtTillDate = outAmtTillDate;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
