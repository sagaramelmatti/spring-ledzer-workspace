package com.spring.ledzer.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.dto.InvoiceHistoryDTO;

@Component
public class InvoiceHistoryEntityToInvoiceHistoryDTOMapperImpl implements InvoiceHistoryEntityToInvoiceHistoryDTOMapper {
	
	@Override
	public InvoiceHistoryDTO setInvoiceHistoryEntityToInvoiceHistoryDTOMapper(Invoice invoice) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		InvoiceHistoryDTO invoiceSalesHistoryDTO = modelMapper.map(invoice, InvoiceHistoryDTO.class);
		
		invoiceSalesHistoryDTO.setCustomerName(invoice.getCustomer().getName());
		invoiceSalesHistoryDTO.setPaymentModeName(invoice.getInvoicePayments().getPaymentMode().getName());
		invoiceSalesHistoryDTO.setAccountName(invoice.getInvoicePayments().getAccount().getName());
		
		return invoiceSalesHistoryDTO;
	}
}
