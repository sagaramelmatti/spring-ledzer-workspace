package com.spring.ledzer.service;

import java.util.Date;
import java.util.List;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.dto.CustomerWiseSaleHistoryDTO;

public interface CustomerWiseSaleHistoryService {
	
	public List<CustomerWiseSaleHistoryDTO> getCustomerWiseSaleHistoryList(Long customerId, Date fromDate, Date toDate) throws ResourceNotFoundException;

}
