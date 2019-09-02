package com.spring.ledzer.fascade;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.mapper.PurchaseDTOToEntityMapper;
import com.spring.ledzer.mapper.PurchaseDTOtoPurchasePaymentEntity;
import com.spring.ledzer.mapper.PurchaseDetailDTOtoPurchaseDetailEntityListMapper;
import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.PurchasePayment;
import com.spring.ledzer.model.dto.PurchaseDTO;
import com.spring.ledzer.service.PurchaseService;

@Component
public class PurchaseFascadeImpl implements PurchaseFascade {

	@Autowired
	ProcessPurchase processPurchase;
	
	@Autowired
	PurchaseDTOToEntityMapper purchaseDTOToEntityMapper;
	
	@Autowired
	PurchaseDTOtoPurchasePaymentEntity PurDTOtoPurchasePaymentEntity;
	
	@Autowired
	ProcessAccount processAccount;
	
	@Autowired
	ProcessAccountTransaction processAccountTransaction;
	
	@Autowired
	ProcessPurchasePrductStock processPurchasePrductStock;
	
	
	@Autowired
	PurchaseDetailDTOtoPurchaseDetailEntityListMapper purchaseDetailDTOtoPurchaseDetailEntityListMapper;
	
	@Autowired
	PurchaseService purchaseService;
	
	@Override
	@Transactional
	public Purchase savePurchase(PurchaseDTO purchaseDTO, long purchaseId) throws ResourceNotFoundException {
		
		Purchase purchase = null; 
		Set<PurchaseDetail> purchaseDetails_set = null;
		
		if(purchaseId != -1)
		{
			purchase =  purchaseDTOToEntityMapper.setPurchaseDTOtoEnity(purchaseDTO, purchaseId);
			//purchaseDetails_set = purchaseDetailDTOtoPurchaseDetailEntityListMapper.convertDtoToEntityMapper(purchase,purchaseDTO.getPurchaseDetails());
		}
		else
		{
			purchase =  purchaseDTOToEntityMapper.setPurchaseDTOtoEnity(purchaseDTO, -1);
		}
		
		purchaseDetails_set = purchaseDetailDTOtoPurchaseDetailEntityListMapper.convertDtoToEntityMapper(purchase,purchaseDTO.getPurchaseDetails());
		
		PurchasePayment purchasePayments  = PurDTOtoPurchasePaymentEntity.setPurchasePayments(purchaseDTO, purchase);
		
		purchase = processPurchase.createNewPurchase(purchase, purchaseDetails_set, purchasePayments);
		
		processPurchasePrductStock.updateStock(purchaseDetails_set.stream().findFirst().get(),purchaseDTO.getPurchaseDetails().stream().findFirst().get());
		
		int no_account_updatd = processAccount.updateAccountDetails(purchaseDTO.getAccountId(),purchaseDTO.getPurchaseAmt(),"purchase");
		
		if(purchaseDTO.getId() != 0 || purchaseDTO.getId() != null)
		{
			Purchase new_purchase =  purchaseDTOToEntityMapper.setPurchaseDTOtoEnity(purchaseDTO, purchaseId);
			purchase = purchaseService.save(new_purchase);
		}
		if(purchaseDetails_set.stream().findFirst().get().getId() != null && purchaseDetails_set.stream().findFirst().get().getId() != 0)
		{
			purchaseService.updatePurchaseDetail(purchase,purchaseDTO.getPurchaseDetails().stream().findFirst().get());
		}
		
		return purchase;
		
	}

}
