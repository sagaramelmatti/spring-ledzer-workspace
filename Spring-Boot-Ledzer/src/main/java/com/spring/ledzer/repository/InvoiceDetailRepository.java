package com.spring.ledzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;

/**
 * Created by SagarMelmatti on 20/11/17.
 */
@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {

    List<InvoiceDetail> findByInvoice(Invoice invoice);

	List<InvoiceDetail> findByInvoiceId(Long invoiceId);
	

}
