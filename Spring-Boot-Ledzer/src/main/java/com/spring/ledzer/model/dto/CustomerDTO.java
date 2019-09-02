package com.spring.ledzer.model.dto;

import java.math.BigDecimal;

import javax.persistence.Convert;

import com.spring.ledzer.converter.GenderConverter;
import com.spring.ledzer.model.Gender;
import com.spring.ledzer.model.State;

public class CustomerDTO {
	
	
	private Long id;
	private State state;
	private String name;
	private Gender gender;
	private String address;
	private String shipAdd;
	private String city;
	private String pin;
	private String mobno;
	private String email;
	private String gstin;
	private String tin;
	private String vat;
	private String pan;
	private String stn;
	private BigDecimal outBal;
	private BigDecimal totalAmtPaid;
	
	public CustomerDTO()
	{
		 super();
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShipAdd() {
		return shipAdd;
	}

	public void setShipAdd(String shipAdd) {
		this.shipAdd = shipAdd;
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

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getStn() {
		return stn;
	}

	public void setStn(String stn) {
		this.stn = stn;
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

}
