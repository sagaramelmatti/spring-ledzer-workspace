package com.spring.ledzer.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.Supplier;
import com.spring.ledzer.model.dto.PurchaseDTO;
import com.spring.ledzer.repository.PurchaseRepository;

@Component
public class PurchaseDTOToEntityMapperImpl implements PurchaseDTOToEntityMapper {
	
	@Autowired
	PurchaseRepository _purchaseRepository;

	@Override
	public Purchase setPurchaseDTOtoEnity(PurchaseDTO _PurDTO, long _purchaseId) throws ResourceNotFoundException {
		
		Purchase __purchase = null;
		
		if(_purchaseId != -1)
		{
			__purchase = _purchaseRepository.findById(_purchaseId).orElseThrow(
	                () -> new ResourceNotFoundException("Purchase", "id",_purchaseId));
		}
		else
		{
			__purchase = new Purchase();
		}
		__purchase.setId(_PurDTO.getId());
		__purchase.setPurchaseDate(_PurDTO.getPurchaseDate());
		__purchase.setName(_PurDTO.getName());
		__purchase.setPurchaseNo(_PurDTO.getPurchaseNo());
		if (_PurDTO.getSupplierId() != null) {
	    	
    		Supplier supplier = new Supplier();
	    	supplier.setId(_PurDTO.getSupplierId());
	    	__purchase.setSupplier(supplier);
	    }
		__purchase.setDiscountAmt(_PurDTO.getDiscountAmt());
		__purchase.setRoundAmt(_PurDTO.getRoundAmt());
		__purchase.setTaxAmt(_PurDTO.getTaxAmt());
		__purchase.setBillAmt(_PurDTO.getBillAmt());
		__purchase.setPurchaseAmt(_PurDTO.getPurchaseAmt());
		__purchase.setAmountDue(_PurDTO.getAmountDue());
		__purchase.setAmountPaid(_PurDTO.getAmountPaid());
		__purchase.setCreatedBy(new Long(1));
	    return __purchase;
	}

}
