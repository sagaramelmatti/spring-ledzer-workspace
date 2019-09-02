package com.spring.ledzer.service;

import java.util.List;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.dto.PurchaseHistoryDTO;

public interface PurchaseHistoryService {
	
	public List<PurchaseHistoryDTO> getPurchasesSaleHistory() throws ResourceNotFoundException;

}
