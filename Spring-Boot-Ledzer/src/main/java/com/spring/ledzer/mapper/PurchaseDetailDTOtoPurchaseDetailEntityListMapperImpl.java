package com.spring.ledzer.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;
import com.spring.ledzer.repository.PurchaseDetailRepository;

@Component
public class PurchaseDetailDTOtoPurchaseDetailEntityListMapperImpl implements PurchaseDetailDTOtoPurchaseDetailEntityListMapper {

	@Autowired
	PurchaseDetailRepository purchaseDetailRepository;
	
	@Autowired
	PurchaseDetailDTOtoPurchaseDetailEntityMapper purchaseDetailDTOtoPurchaseDetailEntityMapper;
	
	@Override
	public Set<PurchaseDetail> convertDtoToEntityMapper(Purchase purchase,Set<PurchaseDetailDTO> purchaseDetailDTOSet) {
		
		Set<PurchaseDetail> purchaseDetail_list = new HashSet<PurchaseDetail>();
		
		for(PurchaseDetailDTO purchaseDetailDTO : purchaseDetailDTOSet)
		{
			PurchaseDetail purchaseDetail = purchaseDetailDTOtoPurchaseDetailEntityMapper.convertDtoToEntityMapper(purchase, purchaseDetailDTO);
			purchaseDetail_list.add(purchaseDetail);
		}
		return purchaseDetail_list;
		
	}

}
