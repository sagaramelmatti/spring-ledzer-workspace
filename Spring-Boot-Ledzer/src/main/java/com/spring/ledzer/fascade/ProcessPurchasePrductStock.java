package com.spring.ledzer.fascade;

import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;

public interface ProcessPurchasePrductStock {
	
	public int updateStock(PurchaseDetail purchaseDetail, PurchaseDetailDTO purchaseDetailDTO);

}
