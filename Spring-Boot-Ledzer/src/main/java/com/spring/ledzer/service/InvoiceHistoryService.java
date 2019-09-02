package com.spring.ledzer.service;

import java.util.List;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.dto.InvoiceHistoryDTO;

public interface InvoiceHistoryService {
	
	public List<InvoiceHistoryDTO> getInvoicesSaleHistory() throws ResourceNotFoundException;

}
