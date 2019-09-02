package com.spring.ledzer.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseDTO {
	
	private Long id;
	private Long purchaseNo;
	private String name;
	private BigDecimal billAmt;
	private BigDecimal billAmtHid;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date purchaseDate;
	
	private BigDecimal roundAmt;
	private BigDecimal purchaseAmt;
	private BigDecimal purchaseAmtHid;
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
	private Long supplierId;
	private String voucher;
	private String supplierName;
	
	private Set<PurchaseDetailDTO> purchaseDetails = new HashSet<PurchaseDetailDTO>(0);

	public PurchaseDTO()
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




	public Long getSupplierId() {
		return supplierId;
	}




	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
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


	public BigDecimal getPurchaseAmtHid() {
		return purchaseAmtHid;
	}


	public void setPurchaseAmtHid(BigDecimal purchaseAmtHid) {
		this.purchaseAmtHid = purchaseAmtHid;
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


	public String getVoucher() {
		return voucher;
	}


	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}


	public Set<PurchaseDetailDTO> getPurchaseDetails() {
		return purchaseDetails;
	}


	public void setPurchaseDetails(Set<PurchaseDetailDTO> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


}
