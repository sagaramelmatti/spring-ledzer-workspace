package com.spring.ledzer.model;

// Generated Dec 16, 2018 1:43:16 PM by Hibernate Tools 5.2.10.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * PaymentModeList generated by hbm2java
 */
@Entity
@Table(name = "payment_mode")
public class PaymentMode implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private int addedBy;
	
	private Set<CustomerPayment> customerPayments = new HashSet<CustomerPayment>(0);
	private Set<InvoicePayments> invoicePayments = new HashSet<InvoicePayments>(0);

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "added_by", nullable = false)
	public int getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(int addedBy) {
		this.addedBy = addedBy;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "paymentMode")
	public Set<CustomerPayment> getCustomerPayments() {
		return customerPayments;
	}

	public void setCustomerPayments(Set<CustomerPayment> customerPayments) {
		this.customerPayments = customerPayments;
	}

	@OneToMany(cascade=CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "paymentMode")
	@JsonBackReference
	public Set<InvoicePayments> getInvoicePayments() {
		return this.invoicePayments;
	}

	public void setInvoicePayments(Set<InvoicePayments> invoicePayments) {
		this.invoicePayments = invoicePayments;
	}

}