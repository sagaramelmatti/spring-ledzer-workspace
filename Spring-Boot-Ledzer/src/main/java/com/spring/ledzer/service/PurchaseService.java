package com.spring.ledzer.service;

import java.util.List;
import java.util.Set;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDTO;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;

public interface PurchaseService {
	
	public List<PurchaseDTO> getAllPurchases();
	
	//public List<PurchaseDTO> getAllPurchasesList();
	
	public Long getNextPurchaseId();

	public Set<PurchaseDetailDTO> getAllPurchaseDetails(Long invoiceId);
	
	public Purchase save(Purchase purchase);
	
	public PurchaseDTO getPurchase(Long invoiceId) throws ResourceNotFoundException;

	public PurchaseDetail updatePurchaseDetail(Purchase purchase,PurchaseDetailDTO purchaseDetailDTO);

	public Purchase updatePurchaseDetailEntry(long id, PurchaseDTO invoiceDTO);

	public PurchaseDetailDTO getPurchaseDetail(Long id) throws ResourceNotFoundException;

}
