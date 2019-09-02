package com.spring.ledzer.model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.ledzer.converter.AccountTypeConverter;
import com.spring.ledzer.converter.DefaultAccStsConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "account")
public class Account extends DateAudit<Integer> implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private AccountGroup accountGroup;
	private Date effectiveDate;
	private BigDecimal balance;
	private String bankName;
	private String accountNo;
	
	@Convert(converter = AccountTypeConverter.class)
	private AccountType accountType;
	private String micr;
	private String rtgs;
	private String ifsc;
	private String city;
	private String state;
	private String address;
	
	@Convert(converter = DefaultAccStsConverter.class)
	private DefaultAccSts defaultAccSts;
	
	private Set<InvoicePayments> invoicePayments = new HashSet<InvoicePayments>(0);
	private Set<CustomerPayment> customerPayments = new HashSet<CustomerPayment>(0);
	private Set<AccountTransactions> accountTransactions = new HashSet<AccountTransactions>(0);
	
	
	public Account()
	{
		super();
	}


	public Account(String name, AccountGroup accountGroup, Date effectiveDate, BigDecimal balance, String bankName,
			String accountNo, AccountType accountType, String micr, String rtgs, String ifsc, String city, String state,
			String address, DefaultAccSts defaultAccSts, Set<InvoicePayments> invoicePayments,
			Set<CustomerPayment> customerPayments, Set<AccountTransactions> accountTransactions) {
		super();
		this.name = name;
		this.accountGroup = accountGroup;
		this.effectiveDate = effectiveDate;
		this.balance = balance;
		this.bankName = bankName;
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.micr = micr;
		this.rtgs = rtgs;
		this.ifsc = ifsc;
		this.city = city;
		this.state = state;
		this.address = address;
		this.defaultAccSts = defaultAccSts;
		this.invoicePayments = invoicePayments;
		this.customerPayments = customerPayments;
		this.accountTransactions = accountTransactions;
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

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id")
	@JsonIgnore
	public AccountGroup getAccountGroup() {
		return accountGroup;
	}

	public void setAccountGroup(AccountGroup accountGroup) {
		this.accountGroup = accountGroup;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "effective_date", length = 10)
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name = "balance", precision = 15)
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Column(name = "bank_name", length = 100)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "account_no", length = 15)
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Column(name = "micr", length = 20)
	public String getMicr() {
		return micr;
	}

	public void setMicr(String micr) {
		this.micr = micr;
	}

	@Column(name = "rtgs", length = 20)
	public String getRtgs() {
		return rtgs;
	}

	public void setRtgs(String rtgs) {
		this.rtgs = rtgs;
	}

	@Column(name = "ifsc", length = 20)
	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	@Column(name = "city", length = 30)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state", length = 30)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DefaultAccSts getDefaultAccSts() {
		return defaultAccSts;
	}

	public void setDefaultAccSts(DefaultAccSts defaultAccSts) {
		this.defaultAccSts = defaultAccSts;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
	@JsonBackReference
	public Set<InvoicePayments> getInvoicePayments() {
		return invoicePayments;
	}

	public void setInvoicePayments(Set<InvoicePayments> invoicePayments) {
		this.invoicePayments = invoicePayments;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
	public Set<CustomerPayment> getCustomerPayments() {
		return customerPayments;
	}

	public void setCustomerPayments(Set<CustomerPayment> customerPayments) {
		this.customerPayments = customerPayments;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
	public Set<AccountTransactions> getAccountTransactions() {
		return accountTransactions;
	}

	public void setAccountTransactions(Set<AccountTransactions> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}
}
