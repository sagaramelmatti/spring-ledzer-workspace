package com.spring.ledzer.fascade;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.dto.PurchaseDTO;

public interface PurchaseFascade {
	
	public Purchase savePurchase(PurchaseDTO PurDTO, long purchaseId) throws ResourceNotFoundException;
	
}
