package com.spring.ledzer.model;
// Generated Dec 16, 2018 1:43:16 PM by Hibernate Tools 5.2.10.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.spring.ledzer.converter.TransactionFormConverter;

/**
 * AccountTransactionDetails generated by hbm2java
 */
@Entity
@Table(name = "account_transactions")
public class AccountTransactions extends DateAudit<Integer> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date transDate;
	private double transAmt;
	private Account account;
	
	@Convert(converter = TransactionFormConverter.class)
	private TransactionForm transactionForm;
	private String description;
	
	private CustomerPayment customerPayment;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "trans_date", length = 10)
	public Date getTransDate() {
		return this.transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	@Column(name = "trans_amt", nullable = false, precision = 15)
	public double getTransAmt() {
		return this.transAmt;
	}

	public void setTransAmt(double transAmt) {
		this.transAmt = transAmt;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public TransactionForm getTransactionForm() {
		return transactionForm;
	}

	public void setTransactionForm(TransactionForm transactionForm) {
		this.transactionForm = transactionForm;
	}

	@Column(name = "description", length = 50)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trans_payment_id")
	public CustomerPayment getCustomerPayment() {
		return customerPayment;
	}

	public void setCustomerPayment(CustomerPayment customerPayment) {
		this.customerPayment = customerPayment;
	}

}
