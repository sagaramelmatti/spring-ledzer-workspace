package com.spring.ledzer.model.dto;

public class StateDTO {
	
	private Long id;
	private String name;
	
	public StateDTO()
	{
		
	}
	
	public StateDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}
