package com.spring.ledzer.service;

import java.math.BigDecimal;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Product;

public interface ProductService {
	
	public Product getProdct(Long productId) throws ResourceNotFoundException;

	public int updateProductStock(Long productId, BigDecimal quantity, String action);

}
