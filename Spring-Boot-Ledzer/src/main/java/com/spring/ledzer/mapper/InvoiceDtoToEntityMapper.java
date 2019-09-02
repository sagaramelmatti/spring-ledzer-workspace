package com.spring.ledzer.mapper;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.dto.InvoiceDTO;

public interface InvoiceDtoToEntityMapper {
	
	public Invoice setInvoiceDTOtoInvoiceEnity(InvoiceDTO invoiceDTO, long invoiceId) throws ResourceNotFoundException;

}
