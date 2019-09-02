package com.spring.ledzer.mapper;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.dto.PurchaseDTO;

public interface PurchaseDTOToEntityMapper {
	
	public Purchase setPurchaseDTOtoEnity(PurchaseDTO PurDTO, long invoiceId) throws ResourceNotFoundException;

}
