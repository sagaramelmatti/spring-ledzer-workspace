package com.spring.ledzer.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.dto.InvoiceDTO;

@Component
public class InvoiceEntityToEntityDTOImpl implements InvoiceEntityToEntityDTO {
	
	@Override
	public InvoiceDTO setInvoiceEntitytoInvoiceDTO(Invoice invoice) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		InvoiceDTO invDTO = modelMapper.map(invoice, InvoiceDTO.class);
		invDTO.setCustomerId(invoice.getCustomer().getId());
		invDTO.setCustomerName(invoice.getCustomer().getName());
		invDTO.setPaymentModeId(invoice.getInvoicePayments().getPaymentMode().getId());
		invDTO.setAccountId(invoice.getInvoicePayments().getAccount().getId());
		invDTO.setBillAmtHid(invoice.getBillAmt());
		invDTO.setInvoiceAmtHid(invoice.getInvoiceAmt());
		invDTO.setAmountPaidHid(invoice.getAmountPaid());
		invDTO.setAmountDueHid(invoice.getAmountDue());
		invDTO.setTaxAmtHid(invoice.getTaxAmt());
		
		/*
		 try { invDTO.setInvoiceDate(new
		 SimpleDateFormat("yyyy-MM-dd").parse(invoice.getInvoiceDate().toString())); }
		 catch (java.text.ParseException e) { // TODO Auto-generated catch block
		 e.printStackTrace(); }
		 */
		
		return invDTO;
		
	}
}
