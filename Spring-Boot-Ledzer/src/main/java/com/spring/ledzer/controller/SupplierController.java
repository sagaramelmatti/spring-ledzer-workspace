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
import com.spring.ledzer.model.Supplier;
import com.spring.ledzer.model.dto.SupplierDTO;
import com.spring.ledzer.repository.SupplierRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SupplierController {

	@Autowired
	SupplierRepository repository;
	
	@GetMapping("/supplier")
	public List<SupplierDTO> getAllSupplier() {
		/*
		System.out.println("Get all supplier...");

		List<Supplier> supplier = new ArrayList<>();
		repository.findAll().forEach(supplier::add);
		*/

		 List<Supplier> suppliers = repository.findAll();
	        return suppliers.stream().map(supplier -> convertToDto(supplier)).collect(Collectors.toList());
		
		
	}

	@PostMapping(value = "/supplier/create")
	public Supplier postSupplier(@RequestBody SupplierDTO supplierDTO) {
		
		  ModelMapper modelMapper = new ModelMapper();
		  Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
		
		  return repository.save(supplier);
	}
	
	/**
	 * This method returns the items in store by ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/supplier/{id}")
	public ResponseEntity<SupplierDTO> getSupplier(@PathVariable("id") Long id) throws ResourceNotFoundException {
			Supplier supplier = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Supplier not found for this id :: " + id));

			ModelMapper modelMapper = new ModelMapper();
		  	SupplierDTO SupplierDTO = modelMapper.map(supplier, SupplierDTO.class);

			return ResponseEntity.ok().body(SupplierDTO);
	}
	
	@PutMapping("/supplier/{id}")
	public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") long id, @RequestBody SupplierDTO SupplierDTO) {
		System.out.println("Update Supplier with ID = " + id + "...");

		
		ModelMapper modelMapper = new ModelMapper();
		Supplier supplier_val = modelMapper.map(SupplierDTO, Supplier.class);
		
		Optional<Supplier> supplierData = repository.findById(id);

		if (supplierData.isPresent()) {
			Supplier _supplier = supplierData.get();
			_supplier.setName(supplier_val.getName());
			_supplier.setContactPerson(supplier_val.getContactPerson());
			_supplier.setMobno(supplier_val.getMobno());
			_supplier.setAddress(supplier_val.getAddress());
			_supplier.setCity(supplier_val.getAddress());
			_supplier.setState(supplier_val.getState());
			_supplier.setPin(supplier_val.getPin());
			_supplier.setEmail(supplier_val.getEmail());
			_supplier.setGstin(supplier_val.getGstin());
			_supplier.setOutBal(supplier_val.getOutBal());
			_supplier.setStatus(supplier_val.getStatus());
			
			return new ResponseEntity<>(repository.save(_supplier), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("/supplier/{id}")
	public ResponseEntity<Supplier> deleteSupplier(@PathVariable("id") Long id) throws ResourceNotFoundException {
		
		Supplier Supplier = repository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Supplier not found for this id :: " + id));
        repository.delete(Supplier);
        //return new ResponseEntity<>("Supplier has been deleted!", HttpStatus.OK);
		return ResponseEntity.ok().body(Supplier);
		
	}

	@DeleteMapping("/supplier/delete")
	public ResponseEntity<String> deleteAllsupplier() {
		System.out.println("Delete All supplier...");
		repository.deleteAll();
		return new ResponseEntity<>("All supplier have been deleted!", HttpStatus.OK);
	}

	private SupplierDTO convertToDto(Supplier supplier) {
		ModelMapper modelMapper = new ModelMapper();
	    SupplierDTO supplierDTO = modelMapper.map(supplier, SupplierDTO.class);
	    return supplierDTO;
	}
	
}
