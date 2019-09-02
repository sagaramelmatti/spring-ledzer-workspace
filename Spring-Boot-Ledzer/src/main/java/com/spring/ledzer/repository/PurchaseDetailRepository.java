package com.spring.ledzer.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;

/**
 * Created by SagarMelmatti on 20/11/17.
 */
@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {

    List<PurchaseDetail> findByPurchase(Purchase invoice);

	Set<PurchaseDetail> findByPurchaseId(Long invoiceId);
	

}
