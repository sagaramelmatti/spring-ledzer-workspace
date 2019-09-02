package com.spring.ledzer.mapper;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoicePayments;
import com.spring.ledzer.model.dto.InvoiceDTO;

public interface InvoiceDTOtoInvoicePayments {
	
	public InvoicePayments setInvoicePayments(InvoiceDTO invoiceDTO, Invoice invoice);

}
