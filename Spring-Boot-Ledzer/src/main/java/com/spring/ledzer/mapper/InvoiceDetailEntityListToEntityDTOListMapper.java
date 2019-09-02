package com.spring.ledzer.mapper;

import java.util.Set;

import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;

public interface InvoiceDetailEntityListToEntityDTOListMapper {
	
	public Set<InvoiceDetailDTO> setInvoiceDetailEntityToEntityDTO(Set<InvoiceDetail> invoiceDetailSet);

}
