package com.spring.ledzer.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseHistoryDTO {
	
	private Long id;
	private Long purchaseNo;
	private String name;
	private BigDecimal billAmt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date purchaseDate;
	private BigDecimal roundAmt;
	private BigDecimal purchaseAmt;
	private BigDecimal discountAmt;
	private BigDecimal taxAmt;
	private BigDecimal amountPaid;
	private BigDecimal amountDue;
	private String accountName;
	private String paymentModeName;
	private String chequeNo;
	private String bankName;
	private String supplierName;
	
	public PurchaseHistoryDTO()
	{
		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(Long purchaseNo) {
		this.purchaseNo = purchaseNo;
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

	public Date getPurchaseDate() {
		return purchaseDate;
	}


	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}


	public BigDecimal getRoundAmt() {
		return roundAmt;
	}




	public void setRoundAmt(BigDecimal roundAmt) {
		this.roundAmt = roundAmt;
	}

	public BigDecimal getPurchaseAmt() {
		return purchaseAmt;
	}




	public void setPurchaseAmt(BigDecimal purchaseAmt) {
		this.purchaseAmt = purchaseAmt;
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



	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}
