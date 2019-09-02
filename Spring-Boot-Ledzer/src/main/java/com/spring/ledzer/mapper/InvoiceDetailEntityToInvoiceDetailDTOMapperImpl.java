package com.spring.ledzer.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;

@Component
public class InvoiceDetailEntityToInvoiceDetailDTOMapperImpl implements InvoiceDetailEntityToInvoiceDetailDTOMapper {
	
	@Override
	public InvoiceDetailDTO setInvoiceDetailEntityToInvoiceDetailDTO(InvoiceDetail invoiceDetail) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		InvoiceDetailDTO invoiceDetailDTO = modelMapper.map(invoiceDetail, InvoiceDetailDTO.class);
		
		invoiceDetailDTO.setUomId(invoiceDetail.getUom().getId());
		invoiceDetailDTO.setProductId(invoiceDetail.getProduct().getId());
		invoiceDetailDTO.setPreProductId(invoiceDetail.getProduct().getId());
		invoiceDetailDTO.setPreQuantity(invoiceDetail.getQuantity());
		invoiceDetailDTO.setInvoiceId(invoiceDetail.getInvoice().getId());
		
		return invoiceDetailDTO;
		
	}
}
