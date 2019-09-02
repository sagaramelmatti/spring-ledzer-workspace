package com.spring.ledzer.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.spring.ledzer.model.Category;
import com.spring.ledzer.model.Tax;
import com.spring.ledzer.model.Uom;

public class ProductDTO {
	
	private Long id;
	private String name;
	private String code;
	private Tax saleTaxId;
	private BigDecimal salePrice;
	private BigDecimal purchasePrice;
	private BigDecimal discountPer;
	private Uom uom;
	private Category category;
	private Tax purchaseTaxId;
	private String description;
	private BigDecimal stock;
	private Date lastStockDate;
	
	public ProductDTO()
	{
		 super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public Tax getSaleTaxId() {
		return saleTaxId;
	}

	public void setSaleTaxId(Tax saleTaxId) {
		this.saleTaxId = saleTaxId;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(BigDecimal discountPer) {
		this.discountPer = discountPer;
	}


	public Uom getUom() {
		return uom;
	}


	public void setUom(Uom uom) {
		this.uom = uom;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Tax getPurchaseTaxId() {
		return purchaseTaxId;
	}

	public void setPurchaseTaxId(Tax purchaseTaxId) {
		this.purchaseTaxId = purchaseTaxId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getStock() {
		return stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public Date getLastStockDate() {
		return lastStockDate;
	}

	public void setLastStockDate(Date lastStockDate) {
		this.lastStockDate = lastStockDate;
	}
}
