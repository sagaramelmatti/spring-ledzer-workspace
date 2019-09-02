package com.spring.ledzer.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.Category;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

@RepositoryRestResource
public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	List<Category> findByCategoryIsNull();
	
	List<Category> findByCategoryIsNotNull();
}
