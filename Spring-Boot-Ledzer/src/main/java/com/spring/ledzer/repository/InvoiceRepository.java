package com.spring.ledzer.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoicePayments;

/**
 * Created by SagarMelmatti on 20/11/17.
 */
@RepositoryRestResource
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Page<Invoice> findByCreatedBy(Long userId, Pageable pageable);

    long countByCreatedBy(Long userId);

	@Query("SELECT (max(i.id) + 1 )FROM Invoice i")
	Long getNextInvoiceId();
	
	InvoicePayments findByInvoicePaymentsId(Long invoicePaymentsId);
	
	@Query("select i from Invoice i where  (:customerId is null or i.customer.id = :customerId)  AND " +
	        " (:fromDate is null or i.invoiceDate >= :fromDate) and (:toDate is null or i.invoiceDate < :toDate)" +
	        "order by invoiceDate DESC")
	List<Invoice> findByCustomerId(@Param("customerId") Long customerId, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);
	
}
