package com.spring.ledzer.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.BusinessDetail;
import com.spring.ledzer.model.dto.BusinessDetailDTO;
import com.spring.ledzer.repository.BusinessRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BusinessController {

	@Autowired
	BusinessRepository repository;

	@GetMapping("/business")
	public List<BusinessDetailDTO> getAllbusiness() {
		List<BusinessDetail> businesses = repository.findAll();
        return businesses.stream().map(business -> convertToDto(business)).collect(Collectors.toList());
	}

	@PostMapping(value = "/business/create")
	public BusinessDetail postBusiness(@RequestBody BusinessDetailDTO customerDTO) {
		  	ModelMapper modelMapper = new ModelMapper();
		  	BusinessDetail customer = modelMapper.map(customerDTO, BusinessDetail.class);
			BusinessDetail cust = repository.save(customer);
			return cust;
	}
	
	/**
	 * This method returns the items in store by ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/business/{id}")
	public ResponseEntity<BusinessDetailDTO> getBusiness(@PathVariable("id") Long id) throws ResourceNotFoundException {
			BusinessDetail customer = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("BusinessDetail not found for this id :: " + id));

			ModelMapper modelMapper = new ModelMapper();
		  	BusinessDetailDTO customerDTO = modelMapper.map(customer, BusinessDetailDTO.class);

			return ResponseEntity.ok().body(customerDTO);
	}
	
	@PutMapping("/business/{id}")
	public ResponseEntity<BusinessDetail> updateBusiness(@PathVariable("id") long id, @RequestBody BusinessDetailDTO customerDTO) {
		System.out.println("Update BusinessDetail with ID = " + id + "...");

		
		ModelMapper modelMapper = new ModelMapper();
		BusinessDetail business_val = modelMapper.map(customerDTO, BusinessDetail.class);
		
		Optional<BusinessDetail> BusinessData = repository.findById(id);

		if (BusinessData.isPresent()) {
			
			BusinessDetail _business = BusinessData.get();
			_business.setName(business_val.getName());
			/*
			_business.setBusinessGroup(business_val.getBusinessGroup());
			_business.setEffectiveDate(business_val.getEffectiveDate());
			_business.setBalance(business_val.getBalance());
			_business.setBusinessNo(business_val.getBusinessNo());
			_business.setBusinessType(business_val.getBusinessType());
			_business.setIfsc(business_val.getIfsc());
			_business.setMicr(business_val.getMicr());
			_business.setRtgs(business_val.getRtgs());
			_business.setAddress(business_val.getAddress());
			_business.setCity(business_val.getCity());
			_business.setState(business_val.getState());
			_business.setDefaultAccSts(business_val.getDefaultAccSts());
			*/
			
			return new ResponseEntity<>(repository.save(_business), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	private BusinessDetailDTO convertToDto(BusinessDetail business) {
		ModelMapper modelMapper = new ModelMapper();
	    BusinessDetailDTO productDTO = modelMapper.map(business, BusinessDetailDTO.class);
	    return productDTO;
	}
	
}
