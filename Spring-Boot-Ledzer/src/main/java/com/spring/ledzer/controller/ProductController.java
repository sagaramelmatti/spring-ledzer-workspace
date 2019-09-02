package com.spring.ledzer.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Product;
import com.spring.ledzer.model.dto.ProductDTO;
import com.spring.ledzer.repository.ProductRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository repository;

	@GetMapping("/product")
	public List<ProductDTO> getAllproducts() {
		List<Product> products = repository.findAll();
		List<ProductDTO> productsDto =  products.stream().map(product -> convertToDto(product)).collect(Collectors.toList());
		System.out.println(productsDto);
		return productsDto;
		
	}

	@PostMapping(value = "/product/create")
	public Product postProduct(@RequestBody ProductDTO customerDTO) {
		  	ModelMapper modelMapper = new ModelMapper();
		  	Product customer = modelMapper.map(customerDTO, Product.class);
			Product cust = repository.save(customer);
			return cust;
	}
	
	/**
	 * This method returns the items in store by ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) throws ResourceNotFoundException {
			Product customer = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

			ModelMapper modelMapper = new ModelMapper();
		  	ProductDTO customerDTO = modelMapper.map(customer, ProductDTO.class);

			return ResponseEntity.ok().body(customerDTO);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody ProductDTO productDTO) {
		System.out.println("Update Product with ID = " + id + "...");

		
		ModelMapper modelMapper = new ModelMapper();
		Product product_val = modelMapper.map(productDTO, Product.class);
		
		Optional<Product> ProductData = repository.findById(id);

		if (ProductData.isPresent()) {
			
			Product _product = ProductData.get();
			_product.setName(product_val.getName());
			_product.setCode(product_val.getCode());
			_product.setSaleTaxId(product_val.getSaleTaxId());
			_product.setSalePrice(product_val.getSalePrice());
			_product.setPurchasePrice(product_val.getPurchasePrice());
			_product.setDiscountPer(product_val.getDiscountPer());
			_product.setUom(product_val.getUom());
			_product.setCategory(product_val.getCategory());
			_product.setPurchaseTaxId(product_val.getPurchaseTaxId());
			_product.setDescription(product_val.getDescription());
			_product.setStock(product_val.getStock());
			_product.setLastStockDate(product_val.getLastStockDate());
			
			return new ResponseEntity<>(repository.save(_product), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) throws ResourceNotFoundException {
		
		Product Product = repository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
        repository.delete(Product);
        //return new ResponseEntity<>("Product has been deleted!", HttpStatus.OK);
		return ResponseEntity.ok().body(Product);
		
	}

	@DeleteMapping("/product/delete")
	public ResponseEntity<String> deleteAllproducts() {
		System.out.println("Delete All product...");
		repository.deleteAll();
		return new ResponseEntity<>("All product have been deleted!", HttpStatus.OK);
	}

	private ProductDTO convertToDto(Product product) 
	{
		ModelMapper modelMapper = new ModelMapper();
	    ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
	    return productDTO;
	}
	
}
