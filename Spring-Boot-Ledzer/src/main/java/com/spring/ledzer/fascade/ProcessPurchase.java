package com.spring.ledzer.fascade;

import java.util.Set;

import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.PurchasePayment;

public interface ProcessPurchase {
	
	public Purchase createNewPurchase(Purchase purchase, Set<PurchaseDetail> purchaseDetails_set, PurchasePayment purchasePayments);

}
