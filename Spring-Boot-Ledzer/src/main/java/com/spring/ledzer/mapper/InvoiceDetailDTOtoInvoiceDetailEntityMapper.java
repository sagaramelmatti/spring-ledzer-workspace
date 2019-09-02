package com.spring.ledzer.mapper;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;

public interface InvoiceDetailDTOtoInvoiceDetailEntityMapper {
	
	public InvoiceDetail convertDtoToEntityMapper(InvoiceDetailDTO invoiceDetailDTO, Invoice invoice);

}
