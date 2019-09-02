package com.spring.ledzer.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.dto.PurchaseHistoryDTO;

@Component
public class PurchaseHistoryEntityToPurchaseHistoryDTOMapperImpl implements PurchaseHistoryEntityToPurchaseHistoryDTOMapper {
	
	@Override
	public PurchaseHistoryDTO setPurchaseHistoryEntityToPurchaseHistoryDTOMapper(Purchase purchase) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		PurchaseHistoryDTO purchaseSalesHistoryDTO = modelMapper.map(purchase, PurchaseHistoryDTO.class);
		
		purchaseSalesHistoryDTO.setSupplierName(purchase.getSupplier().getName());
		purchaseSalesHistoryDTO.setPaymentModeName(purchase.getPurchasePayment().getPaymentMode().getName());
		purchaseSalesHistoryDTO.setAccountName(purchase.getPurchasePayment().getAccount().getName());
		
		return purchaseSalesHistoryDTO;
	}
}
