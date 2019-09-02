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
import org.springframework.web.bind.annotation.ModelAttribute;
import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.AccountGroup;
import com.spring.ledzer.model.Product;
import com.spring.ledzer.model.Tax;
import com.spring.ledzer.model.dto.AccountGroupDTO;
import com.spring.ledzer.model.dto.ProductDTO;
import com.spring.ledzer.repository.AccountgroupRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AccountgroupController {

	@Autowired
	AccountgroupRepository repository;

	@GetMapping("/accountgroup")
	public List<AccountGroupDTO> getAllAccountgroup() {
		List<AccountGroup> products = repository.findAll();
        return products.stream().map(accountGroup -> convertToDto(accountGroup)).collect(Collectors.toList());
	}
	

	@PostMapping(value = "/accountgroup/create")
	public AccountGroup postAccountgroup(@RequestBody AccountGroup accountgroup) {

		AccountGroup _accountgroup = repository.save(new AccountGroup(accountgroup.getName(),accountgroup.getAccountGroup()));
		return _accountgroup;
	}
	
	/**
	 * This method returns the items in store by ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/accountgroup/{id}")
	public ResponseEntity<AccountGroup> getAccountgroup(@PathVariable("id") Long id) throws ResourceNotFoundException {
			AccountGroup accountgroup = repository.findById(id)
			  .orElseThrow(() -> new ResourceNotFoundException("AccountGroup not found for this id :: " + id));
			return ResponseEntity.ok().body(accountgroup);
	}
	
	@PutMapping("/accountgroup/{id}")
	public ResponseEntity<AccountGroup> updateAccountgroup(@PathVariable("id") long id, @RequestBody AccountGroup accountgroup) {
		System.out.println("Update AccountGroup with ID = " + id + "...");

		Optional<AccountGroup> accountgroupData = repository.findById(id);

		if (accountgroupData.isPresent()) {
			AccountGroup _accountgroup = accountgroupData.get();
			_accountgroup.setName(accountgroup.getName());
			_accountgroup.setAccountGroup(accountgroup.getAccountGroup());
			return new ResponseEntity<>(repository.save(_accountgroup), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/accountgroup/{id}")
	public ResponseEntity<AccountGroup> deleteAccountgroup(@PathVariable("id") Long id) throws ResourceNotFoundException {
		
		AccountGroup accountgroup = repository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("AccountGroup not found for this id :: " + id));
        repository.delete(accountgroup);
        //return new ResponseEntity<>("AccountGroup has been deleted!", HttpStatus.OK);
		return ResponseEntity.ok().body(accountgroup);
		
	}

	@DeleteMapping("/accountgroup/delete")
	public ResponseEntity<String> deleteAllAccountgroup() {
		System.out.println("Delete All accountgroup...");
		repository.deleteAll();
		return new ResponseEntity<>("All accountgroup have been deleted!", HttpStatus.OK);
	}
	
	private AccountGroupDTO convertToDto(AccountGroup accountGroup) {
		ModelMapper modelMapper = new ModelMapper();
		AccountGroupDTO accountGroupDTO = modelMapper.map(accountGroup, AccountGroupDTO.class);
	    return accountGroupDTO;
	}

}