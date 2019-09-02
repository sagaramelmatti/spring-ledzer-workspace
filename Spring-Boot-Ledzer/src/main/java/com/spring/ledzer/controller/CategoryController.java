package com.spring.ledzer.controller;

import java.util.ArrayList;
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
import com.spring.ledzer.model.Category;
import com.spring.ledzer.model.dto.CategoryDTO;
import com.spring.ledzer.repository.CategoryRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	CategoryRepository repository;

	@GetMapping("/category")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	
	public List<CategoryDTO> getAllCategory() {
		System.out.println("Get all category...");
		List<Category> categories = repository.findByCategoryIsNull();
        return categories.stream().map(category -> convertToDto(category)).collect(Collectors.toList());

	}
	
	@GetMapping("/category/subcategory")
	public List<CategoryDTO> getAllSubCategory() {
		System.out.println("Get all Sub category...");
		List<Category> categories = repository.findByCategoryIsNotNull();
        return categories.stream().map(category -> convertToDto(category)).collect(Collectors.toList());
		
	}

	@PostMapping(value = "/category/create")
	public Category postCategory(@RequestBody Category category) {

		Category _category = repository.save(new Category(category.getName(),category.getCategory()));
		return _category;
	}
	
	/**
	 * This method returns the items in store by ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) throws ResourceNotFoundException {
			Category category = repository.findById(id)
			  .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + id));
			return ResponseEntity.ok().body(category);
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
		System.out.println("Update Category with ID = " + id + "...");

		Optional<Category> categoryData = repository.findById(id);

		if (categoryData.isPresent()) {
			Category _category = categoryData.get();
			_category.setName(category.getName());
			_category.setCategory(category.getCategory());
			return new ResponseEntity<>(repository.save(_category), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id) throws ResourceNotFoundException {
		
		Category category = repository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + id));
        repository.delete(category);
        //return new ResponseEntity<>("Category has been deleted!", HttpStatus.OK);
		return ResponseEntity.ok().body(category);
		
	}

	@DeleteMapping("/category/delete")
	public ResponseEntity<String> deleteAllCategory() {
		System.out.println("Delete All category...");
		repository.deleteAll();
		return new ResponseEntity<>("All category have been deleted!", HttpStatus.OK);
	}
	
	private CategoryDTO convertToDto(Category category) {
		ModelMapper modelMapper = new ModelMapper();
	    CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
	    return categoryDTO;
	}

}