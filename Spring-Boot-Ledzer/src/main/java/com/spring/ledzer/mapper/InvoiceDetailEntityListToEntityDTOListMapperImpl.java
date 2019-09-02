package com.spring.ledzer.mapper;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;

@Component
public class InvoiceDetailEntityListToEntityDTOListMapperImpl implements InvoiceDetailEntityListToEntityDTOListMapper {
	
	@Autowired
	InvoiceDetailEntityToInvoiceDetailDTOMapper invoiceDetailEntityToInvoiceDetailDTOMapper;
	
	@Override
	public Set<InvoiceDetailDTO> setInvoiceDetailEntityToEntityDTO(Set<InvoiceDetail> invoiceDetailSet) {
		
		Set<InvoiceDetailDTO>  InvoiceDetailDTOSet = new HashSet<InvoiceDetailDTO>();
		
		for(InvoiceDetail invoiceDetailObj : invoiceDetailSet)
		{
			InvoiceDetailDTO invoiceDetailDTO = invoiceDetailEntityToInvoiceDetailDTOMapper.setInvoiceDetailEntityToInvoiceDetailDTO(invoiceDetailObj);
			InvoiceDetailDTOSet.add(invoiceDetailDTO);
		}	
		return InvoiceDetailDTOSet;
		
	}
}
