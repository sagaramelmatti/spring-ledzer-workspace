package com.spring.ledzer.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;
import com.spring.ledzer.repository.InvoiceDetailRepository;

@Component
public class InvoiceDetailDTOListtoInvoiceDetailEntityListMapperImpl implements InvoiceDetailDTOListtoInvoiceDetailEntityListMapper {

	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	
	@Autowired
	InvoiceDetailDTOtoInvoiceDetailEntityMapper invoiceDetailDTOtoInvoiceDetailEntityMapper;
	
	@Override
	public Set<InvoiceDetail> convertDtoToEntityMapper(Invoice invoice,Set<InvoiceDetailDTO> invoiceDetailDTOList) {
		
		Set<InvoiceDetail> invoiceDetail_list = new HashSet<InvoiceDetail>();
		
		for(InvoiceDetailDTO invoiceDetailDTO : invoiceDetailDTOList)
		{
			InvoiceDetail _invoiceDetail = invoiceDetailDTOtoInvoiceDetailEntityMapper.convertDtoToEntityMapper(invoiceDetailDTO,invoice);
			invoiceDetail_list.add(_invoiceDetail);
				
		}
		return invoiceDetail_list;
		
		
		//return invoiceDetailDTOList.stream().map(invoiceDetail -> convertToEntity(invoiceDetail))
		//          .collect(Collectors.toSet());
	}

}
