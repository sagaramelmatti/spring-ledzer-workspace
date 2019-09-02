package com.spring.ledzer.model.dto;

import java.math.BigDecimal;

import com.spring.ledzer.model.State;

public class SupplierDTO {
	
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
	private String status;
	
	public SupplierDTO()
	{
		
	}
	
	public SupplierDTO(Long id, State state, String name, String address, String city, String pin, String mobno,
			String contactPerson, String email, String gstin, BigDecimal outBal, BigDecimal totalAmtPaid,
			String status) {
		super();
		this.id = id;
		this.state = state;
		this.name = name;
		this.address = address;
		this.city = city;
		this.pin = pin;
		this.mobno = mobno;
		this.contactPerson = contactPerson;
		this.email = email;
		this.gstin = gstin;
		this.outBal = outBal;
		this.totalAmtPaid = totalAmtPaid;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getMobno() {
		return mobno;
	}
	public void setMobno(String mobno) {
		this.mobno = mobno;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public BigDecimal getOutBal() {
		return outBal;
	}
	public void setOutBal(BigDecimal outBal) {
		this.outBal = outBal;
	}
	public BigDecimal getTotalAmtPaid() {
		return totalAmtPaid;
	}
	public void setTotalAmtPaid(BigDecimal totalAmtPaid) {
		this.totalAmtPaid = totalAmtPaid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
