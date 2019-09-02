package com.spring.ledzer.mapper;

import java.util.Set;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;

public interface InvoiceDetailDTOListtoInvoiceDetailEntityListMapper {
	
	public Set<InvoiceDetail> convertDtoToEntityMapper(Invoice invoice, Set<InvoiceDetailDTO> invoiceDetailDTOList);

}
