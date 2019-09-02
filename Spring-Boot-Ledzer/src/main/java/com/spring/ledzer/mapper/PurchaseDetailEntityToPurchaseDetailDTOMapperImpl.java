package com.spring.ledzer.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;

@Component
public class PurchaseDetailEntityToPurchaseDetailDTOMapperImpl implements PurchaseDetailEntityToPurchaseDetailDTOMapper {
	
	@Override
	public PurchaseDetailDTO setPurchaseDetailEntityToPurchaseDetailDTO(PurchaseDetail purchaseDetail) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		PurchaseDetailDTO purchaseDetailDTO = modelMapper.map(purchaseDetail, PurchaseDetailDTO.class);
		
		purchaseDetailDTO.setUomId(purchaseDetail.getUom().getId());
		purchaseDetailDTO.setProductId(purchaseDetail.getProduct().getId());
		purchaseDetailDTO.setPreProductId(purchaseDetail.getProduct().getId());
		purchaseDetailDTO.setPreQuantity(purchaseDetail.getQuantity());
		purchaseDetailDTO.setPurchaseId(purchaseDetail.getPurchase().getId());
		
		return purchaseDetailDTO;
		
	}
}
