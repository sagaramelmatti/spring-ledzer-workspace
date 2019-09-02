package com.spring.ledzer.controller;

import java.util.List;
import java.util.Set;

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
import com.spring.ledzer.fascade.PurchaseFascade;
import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDTO;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;
import com.spring.ledzer.repository.PurchaseRepository;
import com.spring.ledzer.service.PurchaseService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	PurchaseRepository repository;
	
	@Autowired
	PurchaseFascade purchaseFascade;

	@GetMapping("/purchases")
	public List<PurchaseDTO> getAllPurchases() {
		List<PurchaseDTO> purchases = purchaseService.getAllPurchases();
        return purchases;
	}
	
	@GetMapping("/purchases/list")
	public List<PurchaseDTO> getAllPurchasesList() {
		List<PurchaseDTO> purchases = purchaseService.getAllPurchases();
        return purchases;
	}
	
	@GetMapping("/purchases/getMaxId")
	public Long getMaxPurchaseId() {
		Long purchases_id = purchaseService.getNextPurchaseId();
        return purchases_id;
	}
	
	@GetMapping("/purchases/purchaseDetails/{id}")
	public Set<PurchaseDetailDTO> getAllPurchaseDetails(@PathVariable("id") Long id) 
	{
		Set<PurchaseDetailDTO> purchaseDetails = purchaseService.getAllPurchaseDetails(id);
		
        return purchaseDetails;
	}
	
	@GetMapping("/purchases/{id}")
	public ResponseEntity<PurchaseDTO> getPurchase(@PathVariable("id") Long id) throws ResourceNotFoundException {
		PurchaseDTO PurDTO = purchaseService.getPurchase(id);
		return ResponseEntity.ok().body(PurDTO);
	}
	
	@PostMapping(value = "/purchases/create")
	public Purchase postPurchase(@RequestBody PurchaseDTO purchaseDTO) throws ResourceNotFoundException {
		
		System.out.println("post purchase called");
		//invoiceFascade invoiceFascade = new SaveInvoiceDetailsFascadeImpl();
		long id = -1;
		Purchase purchase = purchaseFascade.savePurchase(purchaseDTO,id);
		return purchase;
		
	}
	
	@PutMapping("/purchases/{id}")
	public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") long id, @RequestBody PurchaseDTO PurDTO) throws ResourceNotFoundException {
		
		System.out.println("Update Purchase with ID = " + id + "...");
		System.out.println("Update Purchase called");
		//invoiceFascade invoiceFascade = new SaveInvoiceDetailsFascadeImpl();
		return new ResponseEntity<>(purchaseFascade.savePurchase(PurDTO,id), HttpStatus.OK);
		
		/*
		
		
		ModelMapper modelMapper = new ModelMapper();
		Purchase purchase_val = modelMapper.map(PurDTO, Purchase.class);
		
		Optional<Purchase> purchase_data = repository.findById(id);

		if (purchase_data.isPresent()) {
			
			Supplier supplier = new Supplier();
			
			Purchase _purchase = purchase_data.get();
			_purchase.setPurchaseNo(purchase_val.getPurchaseNo());
			_purchase.setName(purchase_val.getName());
			_purchase.setPurchaseDate(purchase_val.getPurchaseDate());
			_purchase.setBillAmt(purchase_val.getBillAmt());
			_purchase.setPurchaseAmt(purchase_val.getPurchaseAmt());
			_purchase.setDiscountAmt(purchase_val.getDiscountAmt());
			_purchase.setTaxAmt(purchase_val.getTaxAmt());
			_purchase.setTaxAmt(purchase_val.getTaxAmt());
			_purchase.setAmountPaid(purchase_val.getAmountPaid());
			_purchase.setAmountDue(purchase_val.getAmountDue());
			supplier.setId(_purchase.getSupplier().getId());
			_purchase.setSupplier(supplier);
			
			return new ResponseEntity<>(repository.save(_purchase), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		*/
	}
	
	@PutMapping("/purchases/purchasedetails/{id}")
	public ResponseEntity<PurchaseDetail> updatePurchaseDetails(@PathVariable("id") long id, @RequestBody PurchaseDetail purchaseDetailDTO) {
		System.out.println("Update PurchaseDetail with ID = " + id + "...");
		//return new ResponseEntity<>(purchaseService.updatePurchaseDetail(id,purchaseDetailDTO), HttpStatus.OK);
		return null;
		
	}
	
	@PutMapping("/purchases/updatepurchasedetail/{id}")
	public ResponseEntity<Purchase> updatePurchaseDetailEntry(@PathVariable("id") long id, @RequestBody PurchaseDTO purchaseDTO) {
		System.out.println("Update PurchaseDetail with ID = " + id + "...");
		return new ResponseEntity<>(purchaseService.updatePurchaseDetailEntry(id,purchaseDTO), HttpStatus.OK);
	}
	
	@GetMapping("/purchases/purchasedetail/{id}")
	public ResponseEntity<PurchaseDetailDTO> getPurchaseDetail(@PathVariable("id") Long id) throws ResourceNotFoundException {
		PurchaseDetailDTO purchaseDetailDTO = purchaseService.getPurchaseDetail(id);
		return ResponseEntity.ok().body(purchaseDetailDTO);
	}
	
}
