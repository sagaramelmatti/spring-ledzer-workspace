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
import com.spring.ledzer.model.Account;
import com.spring.ledzer.model.dto.AccountDTO;
import com.spring.ledzer.repository.AccountRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	AccountRepository repository;

	@GetMapping("/account")
	public List<AccountDTO> getAllaccounts() {
		List<Account> accounts = repository.findAll();
        return accounts.stream().map(account -> convertToDto(account)).collect(Collectors.toList());
	}

	@PostMapping(value = "/account/create")
	public Account postAccount(@RequestBody AccountDTO customerDTO) {
		  	ModelMapper modelMapper = new ModelMapper();
		  	Account customer = modelMapper.map(customerDTO, Account.class);
			Account cust = repository.save(customer);
			return cust;
	}
	
	/**
	 * This method returns the items in store by ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/account/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") Long id) throws ResourceNotFoundException {
			Account customer = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));

			ModelMapper modelMapper = new ModelMapper();
		  	AccountDTO customerDTO = modelMapper.map(customer, AccountDTO.class);

			return ResponseEntity.ok().body(customerDTO);
	}
	
	@PutMapping("/account/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody AccountDTO customerDTO) {
		System.out.println("Update Account with ID = " + id + "...");

		
		ModelMapper modelMapper = new ModelMapper();
		Account account_val = modelMapper.map(customerDTO, Account.class);
		
		Optional<Account> AccountData = repository.findById(id);

		if (AccountData.isPresent()) {
			
			Account _account = AccountData.get();
			_account.setName(account_val.getName());
			_account.setAccountGroup(account_val.getAccountGroup());
			_account.setEffectiveDate(account_val.getEffectiveDate());
			_account.setBalance(account_val.getBalance());
			_account.setAccountNo(account_val.getAccountNo());
			_account.setAccountType(account_val.getAccountType());
			_account.setIfsc(account_val.getIfsc());
			_account.setMicr(account_val.getMicr());
			_account.setRtgs(account_val.getRtgs());
			_account.setAddress(account_val.getAddress());
			_account.setCity(account_val.getCity());
			_account.setState(account_val.getState());
			_account.setDefaultAccSts(account_val.getDefaultAccSts());
			
			return new ResponseEntity<>(repository.save(_account), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("/account/{id}")
	public ResponseEntity<Account> deleteAccount(@PathVariable("id") Long id) throws ResourceNotFoundException {
		
		Account Account = repository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));
        repository.delete(Account);
        //return new ResponseEntity<>("Account has been deleted!", HttpStatus.OK);
		return ResponseEntity.ok().body(Account);
		
	}

	@DeleteMapping("/account/delete")
	public ResponseEntity<String> deleteAllAccounts() {
		System.out.println("Delete All accounts...");
		repository.deleteAll();
		return new ResponseEntity<>("All accounts have been deleted!", HttpStatus.OK);
	}
	
	@GetMapping("/account/getDefaultAccount")
	public Long getDefaultAccount() {
		Long account_id = repository.getDefaultAccount();
        return account_id;
	}

	private AccountDTO convertToDto(Account account) {
		ModelMapper modelMapper = new ModelMapper();
	    AccountDTO productDTO = modelMapper.map(account, AccountDTO.class);
	    return productDTO;
	}
	
}
