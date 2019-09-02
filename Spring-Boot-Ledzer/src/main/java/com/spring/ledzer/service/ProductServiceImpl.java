package com.spring.ledzer.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Product;
import com.spring.ledzer.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product getProdct(Long productId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public int updateProductStock(Long productId, BigDecimal quantity, String action) {
		int result =0;
		BigDecimal new_stock = new BigDecimal(0.00);
		try
		{
			BigDecimal stock = productRepository.getProductStock(productId);
			
			if(action.equals("add"))
			{
				new_stock = stock.add(quantity);
			}
			else if(action.equals("subtract"))
			{
				new_stock = stock.subtract(quantity);
			}
			
			result =  productRepository.updateProductStock(productId, new_stock);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return result;
		
	}

}
