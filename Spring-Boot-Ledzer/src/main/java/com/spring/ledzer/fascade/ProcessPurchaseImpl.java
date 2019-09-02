package com.spring.ledzer.fascade;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.PurchasePayment;
import com.spring.ledzer.service.PurchaseService;

@Component
public class ProcessPurchaseImpl implements ProcessPurchase {
	
	@Autowired
	PurchaseService purchaseService;

	@Override
	public Purchase createNewPurchase(Purchase purchase, Set<PurchaseDetail> purchaseDetails_set, PurchasePayment purchasePayments) {
		
		Purchase purchase_result = null;
		purchase.setPurchasePayment(purchasePayments);
		purchase.setPurchaseDetails(purchaseDetails_set);
		purchase_result = purchaseService.save(purchase);
		return purchase_result;
		
	}

}
