package com.spring.ledzer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * InvoiceDetail generated by hbm2java
 */
@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String hsnCode;
	private BigDecimal rate;
	private Uom uom;
	private BigDecimal quantity;
	private BigDecimal totalPrice;
	private BigDecimal discPer;
	private BigDecimal discAmt;
	private BigDecimal taxableAmt;
	private BigDecimal sgstPer;
	private BigDecimal sgstAmt;
	private BigDecimal cgstPer;
	private BigDecimal cgstAmt;
	private BigDecimal igstPer;
	private BigDecimal igstAmt;
	private Product product;
	private Invoice invoice;
	private BigDecimal remStock;
	
	public InvoiceDetail() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	@Column(name = "hsn_code", length = 20)
	public String getHsnCode() {
		return this.hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	
	@Column(name = "rate", nullable = false, precision = 15, scale = 3)
	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uom_id", nullable = false)
	@JsonIgnore
	public Uom getUom() {
		return uom;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}

	@Column(name = "quantity", nullable = false, precision = 7, scale = 3)
	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Column(name = "total_price", nullable = false, precision = 15, scale = 3)
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false)
	@JsonIgnore
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "disc_per", nullable = false, precision = 6, scale = 3)
	public BigDecimal getDiscPer() {
		return discPer;
	}

	public void setDiscPer(BigDecimal discPer) {
		this.discPer = discPer;
	}

	@Column(name = "disc_amt", nullable = false, precision = 15, scale = 3)
	public BigDecimal getDiscAmt() {
		return discAmt;
	}

	public void setDiscAmt(BigDecimal discAmt) {
		this.discAmt = discAmt;
	}

	@Column(name = "taxable_amt", nullable = false, precision = 15, scale = 3)
	public BigDecimal getTaxableAmt() {
		return this.taxableAmt;
	}

	public void setTaxableAmt(BigDecimal taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	@Column(name = "sgst_per", nullable = false, precision = 5, scale = 3)
	public BigDecimal getSgstPer() {
		return this.sgstPer;
	}

	public void setSgstPer(BigDecimal sgstPer) {
		this.sgstPer = sgstPer;
	}

	@Column(name = "sgst_amt", nullable = false, precision = 15, scale = 3)
	public BigDecimal getSgstAmt() {
		return this.sgstAmt;
	}

	public void setSgstAmt(BigDecimal sgstAmt) {
		this.sgstAmt = sgstAmt;
	}

	@Column(name = "cgst_per", nullable = false, precision = 5, scale = 3)
	public BigDecimal getCgstPer() {
		return cgstPer;
	}
	public void setCgstPer(BigDecimal cgstPer) {
		this.cgstPer = cgstPer;
	}

	@Column(name = "cgst_amt", nullable = false, precision = 15, scale = 3)
	public BigDecimal getCgstAmt() {
		return this.cgstAmt;
	}

	public void setCgstAmt(BigDecimal cgstAmt) {
		this.cgstAmt = cgstAmt;
	}

	@Column(name = "igst_per", nullable = false, precision = 5, scale = 3)
	public BigDecimal getIgstPer() {
		return this.igstPer;
	}

	public void setIgstPer(BigDecimal igstPer) {
		this.igstPer = igstPer;
	}

	@Column(name = "igst_amt", nullable = false, precision = 15, scale = 3)
	public BigDecimal getIgstAmt() {
		return this.igstAmt;
	}

	public void setIgstAmt(BigDecimal igstAmt) {
		this.igstAmt = igstAmt;
	}

	@Column(name = "rem_stock", nullable = false, precision = 8, scale = 3)
	public BigDecimal getRemStock() {
		return this.remStock;
	}

	public void setRemStock(BigDecimal remStock) {
		this.remStock = remStock;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id", nullable = false)
	@JsonIgnore
	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
