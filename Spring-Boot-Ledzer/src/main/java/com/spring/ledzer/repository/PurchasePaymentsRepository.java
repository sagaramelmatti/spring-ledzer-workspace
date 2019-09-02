package com.spring.ledzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.PurchasePayment;

/**
 * Created by SagarMelmatti on 20/11/17.
 */
@RepositoryRestResource
public interface PurchasePaymentsRepository extends JpaRepository<PurchasePayment, Long> {

	@Query("SELECT (max(i.paymentNumber) + 1 ) FROM PurchasePayment i")
	Long getNextPaymentNumber();
	
	PurchasePayment findByPurchase(Invoice invoice);
	
	PurchasePayment findByPurchaseId(Long invoiceId);
}
