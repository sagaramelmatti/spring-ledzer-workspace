package com.spring.ledzer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.spring.ledzer.model.Customer;
import com.spring.ledzer.model.dto.CustomerDTO;
import com.spring.ledzer.repository.CustomerRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerRepository repository;

	@GetMapping("/customers")
	public List<Customer> getAllcustomers() {
		System.out.println("Get all customers...");

		List<Customer> customers = new ArrayList<>();
		repository.findAll().forEach(customers::add);

		return customers;
	}

	@PostMapping(value = "/customers/create")
	public Customer postCustomer(@RequestBody CustomerDTO customerDTO) {
		System.out.println("customer id="+customerDTO.getState());
		
		  ModelMapper modelMapper = new ModelMapper();
		  Customer customer = modelMapper.map(customerDTO, Customer.class);
		
			Customer cust = repository.save(customer);
			return cust;
	}
	
	/**
	 * This method returns the items in store by ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long id) throws ResourceNotFoundException {
			Customer customer = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));

			ModelMapper modelMapper = new ModelMapper();
		  	CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

			return ResponseEntity.ok().body(customerDTO);
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerDTO customerDTO) {
		System.out.println("Update Customer with ID = " + id + "...");

		
		ModelMapper modelMapper = new ModelMapper();
		Customer customer_val = modelMapper.map(customerDTO, Customer.class);
		
		Optional<Customer> CustomerData = repository.findById(id);

		if (CustomerData.isPresent()) {
			Customer _customer = CustomerData.get();
			_customer.setName(customer_val.getName());
			_customer.setMobno(customer_val.getMobno());
			_customer.setGender(customer_val.getGender());
			_customer.setAddress(customer_val.getAddress());
			_customer.setCity(customer_val.getAddress());
			_customer.setState(customer_val.getState());
			_customer.setPin(customer_val.getPin());
			_customer.setEmail(customer_val.getEmail());
			_customer.setGstin(customer_val.getGstin());
			_customer.setTin(customer_val.getTin());
			_customer.setVat(customer_val.getVat());
			_customer.setPan(customer_val.getPan());
			_customer.setStn(customer_val.getStn());
			_customer.setOutBal(customer_val.getOutBal());
			
			return new ResponseEntity<>(repository.save(_customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) throws ResourceNotFoundException {
		
		Customer Customer = repository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));
        repository.delete(Customer);
        //return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
		return ResponseEntity.ok().body(Customer);
		
	}

	@DeleteMapping("/customers/delete")
	public ResponseEntity<String> deleteAllcustomers() {
		System.out.println("Delete All customers...");
		repository.deleteAll();
		return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
	}

	
}
