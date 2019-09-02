package com.spring.ledzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoicePayments;

/**
 * Created by SagarMelmatti on 20/11/17.
 */
@RepositoryRestResource
public interface InvoicePaymentsRepository extends JpaRepository<InvoicePayments, Long> {

	@Query("SELECT (max(i.paymentNumber) + 1 ) FROM InvoicePayments i")
	Long getNextPaymentNumber();
	
	InvoicePayments findByInvoice(Invoice invoice);
	
	InvoicePayments findByInvoiceId(Long invoiceId);
}
