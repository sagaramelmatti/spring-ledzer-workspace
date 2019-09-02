package com.spring.ledzer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.spring.ledzer.model.Tax;
import com.spring.ledzer.repository.TaxRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TaxController {

	@Autowired
	TaxRepository repository;

	
	@GetMapping("/taxes")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Tax> getAlltaxes() {
		System.out.println("Get all taxes...");

		List<Tax> taxes = new ArrayList<>();
		repository.findAll().forEach(taxes::add);

		return taxes;
	}

	@PostMapping(value = "/taxes/create")
	public Tax postTax(@RequestBody Tax tax) {

		Tax _Tax = repository.save(new Tax(tax.getName(),tax.getPercentage()));
		return _Tax;
	}
	
	/**
	 * This method returns the items in store by ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/taxes/{id}")
	public ResponseEntity<Tax> getTax(@PathVariable("id") Long id) throws ResourceNotFoundException {
			Tax tax = repository.findById(id)
			  .orElseThrow(() -> new ResourceNotFoundException("Tax not found for this id :: " + id));
			return ResponseEntity.ok().body(tax);
	}
	
	@PutMapping("/taxes/{id}")
	public ResponseEntity<Tax> updateTax(@PathVariable("id") long id, @RequestBody Tax Tax) {
		System.out.println("Update Tax with ID = " + id + "...");

		Optional<Tax> TaxData = repository.findById(id);

		if (TaxData.isPresent()) {
			Tax _Tax = TaxData.get();
			_Tax.setName(Tax.getName());
			_Tax.setPercentage(Tax.getPercentage());
			return new ResponseEntity<>(repository.save(_Tax), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/taxes/{id}")
	public ResponseEntity<Tax> deleteTax(@PathVariable("id") Long id) throws ResourceNotFoundException {
		
		Tax tax = repository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Tax not found for this id :: " + id));
        repository.delete(tax);
        //return new ResponseEntity<>("Tax has been deleted!", HttpStatus.OK);
		return ResponseEntity.ok().body(tax);
		
	}

	@DeleteMapping("/taxes/delete")
	public ResponseEntity<String> deleteAlltaxes() {
		System.out.println("Delete All taxes...");

		repository.deleteAll();

		return new ResponseEntity<>("All taxes have been deleted!", HttpStatus.OK);
	}

	
}
