package com.spring.ledzer.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Convert;

import com.spring.ledzer.converter.AccountTypeConverter;
import com.spring.ledzer.converter.DefaultAccStsConverter;
import com.spring.ledzer.model.AccountGroup;
import com.spring.ledzer.model.AccountType;
import com.spring.ledzer.model.DefaultAccSts;

public class AccountDTO {
	
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
	
	public AccountDTO()
	{
		
	}

	public AccountDTO(String name, AccountGroup accountGroup, Date effectiveDate, BigDecimal balance, String bankName,
			String accountNo, AccountType accountType, String micr, String rtgs, String ifsc, String city, String state,
			String address, DefaultAccSts defaultAccSts) {
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

	public AccountGroup getAccountGroup() {
		return accountGroup;
	}

	public void setAccountGroup(AccountGroup accountGroup) {
		this.accountGroup = accountGroup;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

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

	public String getMicr() {
		return micr;
	}

	public void setMicr(String micr) {
		this.micr = micr;
	}

	public String getRtgs() {
		return rtgs;
	}

	public void setRtgs(String rtgs) {
		this.rtgs = rtgs;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
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

}
