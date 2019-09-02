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
@Table(name = "invoice")
public class Invoice extends DateAudit<Long>  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;
	private Long invoiceNo;
	private String name;
	private Date invoiceDate;
	private BigDecimal billAmt;
	private BigDecimal roundAmt;
	private BigDecimal invoiceAmt;
	private BigDecimal discountAmt;
	private BigDecimal taxAmt;
	private BigDecimal amountPaid;
	private BigDecimal amountDue;
	private Customer customer;
	
	
	private InvoicePayments invoicePayments;
	
	private Set<InvoiceDetail> invoiceDetails = new HashSet<InvoiceDetail>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="invoice_no")
	public Long getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(Long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date", length = 19)
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
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

	@Column(name = "invoice_amt", precision = 15, scale = 3)
	public BigDecimal getInvoiceAmt() {
		return invoiceAmt;
	}

	public void setInvoiceAmt(BigDecimal invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
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

	@Column(name = "amount_paid", precision = 15, scale = 3)
	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	@Column(name = "amount_due", precision = 15, scale = 3)
	public BigDecimal getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}

	@ManyToOne
	@JoinColumn(name="customer_id")
	@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy="invoice")
	public Set<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	@OneToOne(fetch = FetchType.EAGER,cascade =  CascadeType.ALL,mappedBy = "invoice")
	@JsonIgnore
	public InvoicePayments getInvoicePayments() {
		return invoicePayments;
	}

	public void setInvoicePayments(InvoicePayments invoicePayments) {
		this.invoicePayments = invoicePayments;
	}

}
