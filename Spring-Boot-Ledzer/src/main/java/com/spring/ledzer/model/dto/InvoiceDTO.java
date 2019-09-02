package com.spring.ledzer.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InvoiceDTO {
	
	private Long id;
	private Long invoiceNo;
	private String name;
	private BigDecimal billAmt;
	private BigDecimal billAmtHid;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date invoiceDate;
	
	private BigDecimal roundAmt;
	private BigDecimal invoiceAmt;
	private BigDecimal invoiceAmtHid;
	private BigDecimal discountAmt;
	private BigDecimal taxAmt;
	private BigDecimal taxAmtHid;
	private BigDecimal amountPaid;
	private BigDecimal amountPaidHid;
	private BigDecimal amountDue;
	private BigDecimal amountDueHid;
	private Long accountId;
	private Long paymentModeId;
	private String chequeNo;
	private String bankName;
	private Long customerId;
	private String customerName;
	
	private Set<InvoiceDetailDTO> invoiceDetails = new HashSet<InvoiceDetailDTO>(0);

	public InvoiceDTO()
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




	public Long getCustomerId() {
		return customerId;
	}




	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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




	public Long getAccountId() {
		return accountId;
	}




	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}




	public Long getPaymentModeId() {
		return paymentModeId;
	}




	public void setPaymentModeId(Long paymentModeId) {
		this.paymentModeId = paymentModeId;
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




	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public BigDecimal getBillAmtHid() {
		return billAmtHid;
	}


	public void setBillAmtHid(BigDecimal billAmtHid) {
		this.billAmtHid = billAmtHid;
	}


	public BigDecimal getInvoiceAmtHid() {
		return invoiceAmtHid;
	}


	public void setInvoiceAmtHid(BigDecimal invoiceAmtHid) {
		this.invoiceAmtHid = invoiceAmtHid;
	}


	public BigDecimal getAmountPaidHid() {
		return amountPaidHid;
	}


	public void setAmountPaidHid(BigDecimal amountPaidHid) {
		this.amountPaidHid = amountPaidHid;
	}


	public BigDecimal getAmountDueHid() {
		return amountDueHid;
	}


	public void setAmountDueHid(BigDecimal amountDueHid) {
		this.amountDueHid = amountDueHid;
	}


	public BigDecimal getTaxAmtHid() {
		return taxAmtHid;
	}


	public void setTaxAmtHid(BigDecimal taxAmtHid) {
		this.taxAmtHid = taxAmtHid;
	}


	public Set<InvoiceDetailDTO> getInvoiceDetails() {
		return invoiceDetails;
	}


	public void setInvoiceDetails(Set<InvoiceDetailDTO> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
