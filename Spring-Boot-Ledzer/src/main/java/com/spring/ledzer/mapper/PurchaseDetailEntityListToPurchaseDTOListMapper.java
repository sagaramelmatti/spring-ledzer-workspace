package com.spring.ledzer.mapper;

import java.util.Set;

import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;

public interface PurchaseDetailEntityListToPurchaseDTOListMapper {
	
	public Set<PurchaseDetailDTO> setPurchaseDetailEntityListToPurchaseDetailDTOList(Set<PurchaseDetail> purchaseDetailSet);

}
