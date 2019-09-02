package com.spring.ledzer.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Sagar Melmatti on 20/12/18.
 */

@Entity
@Table(name = "purchase")
public class Purchase extends DateAudit<Long>  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;
	private Long purchaseNo;
	private String name;
	private Date purchaseDate;
	private BigDecimal billAmt;
	private BigDecimal roundAmt;
	private BigDecimal purchaseAmt;
	private BigDecimal discountAmt;
	private BigDecimal taxAmt;
	private BigDecimal amountPaid;
	private BigDecimal amountDue;
	private Supplier supplier;
	private String voucher;
	private PurchasePayment purchasePayment;
	private Set<PurchaseDetail> purchaseDetails = new HashSet<PurchaseDetail>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="purchase_no")
	public Long getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(Long purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "purchase_date", length = 19)
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Column(name = "bill_amt", precision = 15, scale = 3)
	public BigDecimal getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(BigDecimal billAmt) {
		this.billAmt = billAmt;
	}
	
	@Column(name = "round_amt", precision = 15, scale = 3)
	public BigDecimal getRoundAmt() {
		return roundAmt;
	}

	public void setRoundAmt(BigDecimal roundAmt) {
		this.roundAmt = roundAmt;
	}

	@Column(name = "purchase_amt", precision = 15, scale = 3)
	public BigDecimal getPurchaseAmt() {
		return purchaseAmt;
	}

	public void setPurchaseAmt(BigDecimal purchaseAmt) {
		this.purchaseAmt = purchaseAmt;
	}

	@Column(name = "discount_amt", precision = 15, scale = 3)
	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}

	@Column(name = "tax_amt", precision = 15, scale = 3)
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

	@ManyToOne
	@JoinColumn(name="supplier_id")
	@JsonIgnore
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	@Column(name = "voucher", length = 30)
	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy="purchase")
	public Set<PurchaseDetail> getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(Set<PurchaseDetail> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	@OneToOne(fetch = FetchType.EAGER,cascade =  CascadeType.ALL,mappedBy = "purchase")
	@JsonIgnore
	public PurchasePayment getPurchasePayment() {
		return purchasePayment;
	}

	public void setPurchasePayment(PurchasePayment purchasePayment) {
		this.purchasePayment = purchasePayment;
	}

}
