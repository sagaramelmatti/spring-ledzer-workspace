package com.spring.ledzer.mapper;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.dto.InvoiceDTO;

public interface InvoiceEntityToEntityDTO {
	
	public InvoiceDTO setInvoiceEntitytoInvoiceDTO(Invoice invoice);

}
