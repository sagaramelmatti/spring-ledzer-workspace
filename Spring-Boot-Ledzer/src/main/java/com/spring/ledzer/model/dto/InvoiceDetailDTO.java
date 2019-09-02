package com.spring.ledzer.model.dto;

import java.math.BigDecimal;

public class InvoiceDetailDTO {
	
	private Long id;
	private String hsnCode;
	private String batchNo;
	private BigDecimal rate;
	private Long uomId;
	private BigDecimal quantity;
	private BigDecimal preQuantity;
	private BigDecimal totalPrice;
	private BigDecimal discPer;
	private BigDecimal discAmt;
	private BigDecimal taxableAmt;
	private Long productId;
	private Long preProductId;
	private BigDecimal sgstPer;
	private BigDecimal sgstAmt;
	private BigDecimal cgstPer;
	private BigDecimal cgstAmt;
	private BigDecimal igstPer;
	private BigDecimal igstAmt;
	private BigDecimal remStock;
	private Long invoiceId;
	

	public InvoiceDetailDTO()
	{
		super();
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getHsnCode() {
		return hsnCode;
	}


	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}


	public String getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public BigDecimal getRate() {
		return rate;
	}


	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Long getUomId() {
		return uomId;
	}


	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}


	


	public BigDecimal getQuantity() {
		return quantity;
	}


	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getPreQuantity() {
		return preQuantity;
	}


	public void setPreQuantity(BigDecimal preQuantity) {
		this.preQuantity = preQuantity;
	}


	public BigDecimal getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}


	public BigDecimal getDiscPer() {
		return discPer;
	}


	public void setDiscPer(BigDecimal discPer) {
		this.discPer = discPer;
	}


	public BigDecimal getDiscAmt() {
		return discAmt;
	}


	public void setDiscAmt(BigDecimal discAmt) {
		this.discAmt = discAmt;
	}


	public BigDecimal getTaxableAmt() {
		return taxableAmt;
	}


	public void setTaxableAmt(BigDecimal taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getPreProductId() {
		return preProductId;
	}


	public void setPreProductId(Long preProductId) {
		this.preProductId = preProductId;
	}


	public BigDecimal getSgstPer() {
		return sgstPer;
	}


	public void setSgstPer(BigDecimal sgstPer) {
		this.sgstPer = sgstPer;
	}


	public BigDecimal getSgstAmt() {
		return sgstAmt;
	}


	public void setSgstAmt(BigDecimal sgstAmt) {
		this.sgstAmt = sgstAmt;
	}


	public BigDecimal getCgstPer() {
		return cgstPer;
	}


	public void setCgstPer(BigDecimal cgstPer) {
		this.cgstPer = cgstPer;
	}


	public BigDecimal getCgstAmt() {
		return cgstAmt;
	}


	public void setCgstAmt(BigDecimal cgstAmt) {
		this.cgstAmt = cgstAmt;
	}


	public BigDecimal getIgstPer() {
		return igstPer;
	}


	public void setIgstPer(BigDecimal igstPer) {
		this.igstPer = igstPer;
	}


	public BigDecimal getIgstAmt() {
		return igstAmt;
	}


	public void setIgstAmt(BigDecimal igstAmt) {
		this.igstAmt = igstAmt;
	}


	public BigDecimal getRemStock() {
		return remStock;
	}


	public void setRemStock(BigDecimal remStock) {
		this.remStock = remStock;
	}


	public Long getInvoiceId() {
		return invoiceId;
	}


	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
}
