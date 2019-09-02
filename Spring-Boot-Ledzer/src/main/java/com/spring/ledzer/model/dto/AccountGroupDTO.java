package com.spring.ledzer.model.dto;

import com.spring.ledzer.model.AccountGroup;

public class AccountGroupDTO {
	
	
	private Long id;
	private String name;
	private AccountGroup accountGroup;
	
	public AccountGroupDTO()
	{
		super();
	}
	
	public AccountGroupDTO(String name, AccountGroup accountGroup) {
		super();
		this.name = name;
		this.accountGroup = accountGroup;
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
	
}
