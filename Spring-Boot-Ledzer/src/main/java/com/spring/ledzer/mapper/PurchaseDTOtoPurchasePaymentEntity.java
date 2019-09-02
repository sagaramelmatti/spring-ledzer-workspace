package com.spring.ledzer.mapper;

import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchasePayment;
import com.spring.ledzer.model.dto.PurchaseDTO;

public interface PurchaseDTOtoPurchasePaymentEntity {
	
	public PurchasePayment setPurchasePayments(PurchaseDTO PurDTO, Purchase purchase);

}
