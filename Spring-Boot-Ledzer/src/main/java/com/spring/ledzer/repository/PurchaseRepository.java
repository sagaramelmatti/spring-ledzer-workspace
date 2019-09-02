package com.spring.ledzer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchasePayment;

/**
 * Created by SagarMelmatti on 20/11/17.
 */
@RepositoryRestResource
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Page<Purchase> findByCreatedBy(Long userId, Pageable pageable);

    long countByCreatedBy(Long userId);

	@Query("SELECT (max(i.id) + 1 )FROM Purchase i")
	Long getNextPurchaseId();
	
	PurchasePayment findByPurchasePaymentId(Long purchasePaymentId);
	
}
