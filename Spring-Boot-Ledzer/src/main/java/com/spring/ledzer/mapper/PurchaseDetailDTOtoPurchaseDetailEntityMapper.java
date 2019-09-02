package com.spring.ledzer.mapper;

import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;

public interface PurchaseDetailDTOtoPurchaseDetailEntityMapper {
	
	public PurchaseDetail convertDtoToEntityMapper(Purchase purchase,PurchaseDetailDTO purchaseDetailDTO);

}
