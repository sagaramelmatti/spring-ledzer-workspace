package com.spring.ledzer.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.Supplier;
import com.spring.ledzer.model.dto.PurchaseDTO;

@Component
public class PurchaseEntityToPurchaseDTOImpl implements PurchaseEntityToPurchaseDTO {
	
	@Override
	public PurchaseDTO setPurchaseEntitytoPurchaseDTO(Purchase purchase) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		Supplier supplier = new Supplier();
		PurchaseDTO purchaseDTO = modelMapper.map(purchase, PurchaseDTO.class);
		supplier.setId(purchase.getSupplier().getId());
		purchaseDTO.setSupplierId(purchase.getSupplier().getId());
		purchaseDTO.setSupplierName(purchase.getSupplier().getName());
		purchaseDTO.setPaymentModeId(purchase.getPurchasePayment().getPaymentMode().getId());
		purchaseDTO.setAccountId(purchase.getPurchasePayment().getAccount().getId());
		purchaseDTO.setBillAmtHid(purchase.getBillAmt());
		purchaseDTO.setPurchaseAmtHid(purchase.getPurchaseAmt());
		purchaseDTO.setAmountPaidHid(purchase.getAmountPaid());
		purchaseDTO.setAmountDueHid(purchase.getAmountDue());
		purchaseDTO.setTaxAmtHid(purchase.getTaxAmt());
		
		return purchaseDTO;
		
	}
}
