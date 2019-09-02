package com.spring.ledzer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.mapper.PurchaseHistoryEntityToPurchaseHistoryDTOMapper;
import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.dto.PurchaseHistoryDTO;
import com.spring.ledzer.repository.PurchaseRepository;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService
{

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	PurchaseHistoryEntityToPurchaseHistoryDTOMapper purchaseHistoryEntityToPurchaseHistoryDTOMapper;
	
    private static final Logger logger = LoggerFactory.getLogger(PollService.class);

	@Override
	public List<PurchaseHistoryDTO> getPurchasesSaleHistory() throws ResourceNotFoundException {
		List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream().map(purchase -> purchaseHistoryEntityToPurchaseHistoryDTOMapper.setPurchaseHistoryEntityToPurchaseHistoryDTOMapper(purchase)).collect(Collectors.toList());
	}
}
