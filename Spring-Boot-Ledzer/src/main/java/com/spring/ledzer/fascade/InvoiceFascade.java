package com.spring.ledzer.fascade;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.dto.InvoiceDTO;

public interface InvoiceFascade {
	
	public Invoice saveInvoice(InvoiceDTO invoiceDTO, long invoiceId) throws ResourceNotFoundException;
	
}
