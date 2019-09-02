package com.spring.ledzer.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Account;
import com.spring.ledzer.model.PaymentMode;
import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchasePayment;
import com.spring.ledzer.model.Supplier;
import com.spring.ledzer.model.dto.PurchaseDTO;
import com.spring.ledzer.repository.PurchasePaymentsRepository;


@Component
public class PurchaseDTOtoPurchasePaymentEntityImpl implements PurchaseDTOtoPurchasePaymentEntity {

	@Autowired
	PurchasePaymentsRepository   purchasePaymentsRepository;
	
	PurchasePayment   purchasePayments = null;
	
	@Override
	public PurchasePayment setPurchasePayments(PurchaseDTO PurDTO, Purchase purchase) {
		
		try
		{
			purchasePayments = purchasePaymentsRepository.findByPurchaseId(purchase.getId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(purchasePayments == null)
    	{
    		purchasePayments = new PurchasePayment();
    		Long payment_number = purchasePaymentsRepository.getNextPaymentNumber();
    		purchasePayments.setPaymentNumber(payment_number);
	    	purchasePayments.setPaymentVoucher("PUR-PYMT"+ payment_number);
    	}
    	
    	Account account = new Account();
    	account.setId(PurDTO.getAccountId());
    	purchasePayments.setAccount(account);
    	
    	PaymentMode paymentMode = new PaymentMode();
    	paymentMode.setId(PurDTO.getPaymentModeId());
    	purchasePayments.setPaymentMode(paymentMode);
    	
    	purchasePayments.setChequeNo(PurDTO.getChequeNo());
    	purchasePayments.setBankName(PurDTO.getBankName());
    	
    	
    	purchasePayments.setPurchaseTotalAmt(PurDTO.getPurchaseAmt());
    	purchasePayments.setAmountPaid(PurDTO.getAmountPaid());
    	purchasePayments.setAmountDue(PurDTO.getAmountDue());
    	purchasePayments.setPaymentDate(PurDTO.getPurchaseDate());
    	
    	Supplier customer_new = new Supplier();
    	customer_new.setId(PurDTO.getSupplierId());
    	purchasePayments.setSupplier(customer_new);
    	purchasePayments.setPurchase(purchase);
		
		return purchasePayments;
	}
	
	

}
