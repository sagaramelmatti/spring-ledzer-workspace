package com.spring.ledzer.mapper;

import java.util.Set;

import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;

public interface PurchaseDetailDTOtoPurchaseDetailEntityListMapper {
	
	public Set<PurchaseDetail> convertDtoToEntityMapper(Purchase purchase,Set<PurchaseDetailDTO> purchaseDetailDTOSet);

}
