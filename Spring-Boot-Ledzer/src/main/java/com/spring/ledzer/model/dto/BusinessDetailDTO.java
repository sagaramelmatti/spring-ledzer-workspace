package com.spring.ledzer.model.dto;

import com.spring.ledzer.model.State;

public class BusinessDetailDTO {
	
	private Long id;
	private String name;
	private String owner;
	private String address;
	private String city;
	private State state;
	private String phone;
	private String email;
	private String website;
	private String pan;
	private String tin;
	private String gstin;
	private String logo;
	private int createdBy;
	
	
	public BusinessDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BusinessDetailDTO(String name, String owner, String address, String city, State state, String phone,
			String email, String website, String pan, String tin, String gstin, String logo) {
		super();
		this.name = name;
		this.owner = owner;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phone = phone;
		this.email = email;
		this.website = website;
		this.pan = pan;
		this.tin = tin;
		this.gstin = gstin;
		this.logo = logo;
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	

}
