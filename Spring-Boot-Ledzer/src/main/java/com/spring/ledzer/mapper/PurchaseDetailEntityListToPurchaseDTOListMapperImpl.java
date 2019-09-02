package com.spring.ledzer.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;

@Component
public class PurchaseDetailEntityListToPurchaseDTOListMapperImpl implements PurchaseDetailEntityListToPurchaseDTOListMapper {
	
	@Autowired
	PurchaseDetailEntityToPurchaseDetailDTOMapper purchaseDetailEntityToPurchaseDetailDTOMapper;
	
	@Override
	public Set<PurchaseDetailDTO> setPurchaseDetailEntityListToPurchaseDetailDTOList(
			Set<PurchaseDetail> purchaseDetailSet) {
		Set<PurchaseDetailDTO>  purchaseDetailDTOSet = new HashSet<PurchaseDetailDTO>();
		
		for(PurchaseDetail purchaseDetailObj : purchaseDetailSet)
		{
			PurchaseDetailDTO purchaseDetailDTO = purchaseDetailEntityToPurchaseDetailDTOMapper.setPurchaseDetailEntityToPurchaseDetailDTO(purchaseDetailObj);
			purchaseDetailDTOSet.add(purchaseDetailDTO);
		}	
		return purchaseDetailDTOSet;
	}
}
