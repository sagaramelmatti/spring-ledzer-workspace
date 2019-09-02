package com.spring.ledzer.model.dto;

import com.spring.ledzer.model.Category;

public class CategoryDTO {
	
	
	private Long id;
	private Category category;
	private String name;
	
	
	public CategoryDTO() {
		super();
	}


	public CategoryDTO(Long id, Category category, String name) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
