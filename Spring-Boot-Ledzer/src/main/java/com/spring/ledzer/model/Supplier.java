package com.spring.ledzer.model;

// Generated Dec 16, 2018 1:43:16 PM by Hibernate Tools 5.2.10.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.ledzer.converter.StatusConverter;

/**
 * Supplier generated by hbm2java
 */
@Entity
@Table(name = "supplier")
public class Supplier extends DateAudit<Integer> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;
	private State state;
	private String name;
	private String address;
	private String city;
	private String pin;
	private String mobno;
	private String contactPerson;
	private String email;
	private String gstin;
	private BigDecimal outBal;
	private BigDecimal totalAmtPaid;
	
	@Convert(converter = StatusConverter.class)
	private Status status;
	
	private Set<Purchase> purchase = new HashSet<Purchase>(0);
	private Set<PurchasePayment> invoicePayments = new HashSet<PurchasePayment>(0);
	
	
	public Supplier() {
		
		 super();
	}
	

	public Supplier(State state, String name, String address, String city,
			String pin, String mobno, String contactPerson, String email, String gstin,
			BigDecimal outBal, BigDecimal totalAmtPaid,Status status) {
		this.state = state;
		this.name = name;
		this.address = address;
		this.city = city;
		this.pin = pin;
		this.mobno = mobno;
		this.email = email;
		this.gstin = gstin;
		this.outBal = outBal;
		this.totalAmtPaid = totalAmtPaid;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state_id")
	@JsonIgnore
	public State getState() {
		return this.state;
	}
	
	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "city", length = 30)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "pin", length = 6)
	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Column(name = "mobno", length = 13)
	public String getMobno() {
		return this.mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	@Column(name = "contact_person")
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}


	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "gstin", length = 20)
	public String getGstin() {
		return this.gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	@Column(name = "out_bal", precision = 15)
	public BigDecimal getOutBal() {
		return this.outBal;
	}

	public void setOutBal(BigDecimal outBal) {
		this.outBal = outBal;
	}

	@Column(name = "total_amt_paid", precision = 15, nullable= true)
	@JsonIgnore
	public BigDecimal getTotalAmtPaid() {
		return this.totalAmtPaid;
	}

	public void setTotalAmtPaid(BigDecimal totalAmtPaid) {
		this.totalAmtPaid = totalAmtPaid;
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@OneToMany(cascade=CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "supplier")
	public Set<Purchase> getPurchase() {
		return purchase;
	}


	public void setPurchase(Set<Purchase> purchase) {
		this.purchase = purchase;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
	public Set<PurchasePayment> getInvoicePayments() {
		return invoicePayments;
	}


	public void setInvoicePayments(Set<PurchasePayment> invoicePayments) {
		this.invoicePayments = invoicePayments;
	}


}
