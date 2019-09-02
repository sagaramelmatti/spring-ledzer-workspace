package com.spring.ledzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.Invoice;

/**
 * Created by SagarMelmatti on 20/11/17.
 */
@RepositoryRestResource
public interface InvoiceHistoryRepository extends JpaRepository<Invoice, Long> {

	@Query("SELECT (max(i.id) + 1 )FROM Invoice i")
	Long getNextInvoiceId();
	
}
