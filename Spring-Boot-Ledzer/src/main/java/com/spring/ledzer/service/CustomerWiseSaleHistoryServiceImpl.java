package com.spring.ledzer.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.mapper.InvoiceHistoryEntityToInvoiceHistoryDTOMapper;
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.dto.CustomerWiseSaleHistoryDTO;
import com.spring.ledzer.repository.InvoiceRepository;

@Service
public class CustomerWiseSaleHistoryServiceImpl implements CustomerWiseSaleHistoryService
{

	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	InvoiceHistoryEntityToInvoiceHistoryDTOMapper invoiceHistoryEntityToInvoiceHistoryDTOMapper;
	
    private static final Logger logger = LoggerFactory.getLogger(ItemWiseSaleHistoryServiceImpl.class);

	@Override
	public List<CustomerWiseSaleHistoryDTO> getCustomerWiseSaleHistoryList(Long customerId, Date fromDate, Date toDate)
			throws ResourceNotFoundException {
		
		List<Invoice> invoice_list = invoiceRepository.findByCustomerId(customerId,fromDate,toDate);
        return invoice_list.stream().map(invoice -> convertToCustomerWiseSaleHistoryDTO(invoice)).collect(Collectors.toList());
	}
	
	private CustomerWiseSaleHistoryDTO convertToCustomerWiseSaleHistoryDTO(Invoice invoice) {
		
		CustomerWiseSaleHistoryDTO customerWiseSaleHistoryDTO = new CustomerWiseSaleHistoryDTO();
			
			customerWiseSaleHistoryDTO.setId(invoice.getId());
			customerWiseSaleHistoryDTO.setInvoiceName(invoice.getName());
			customerWiseSaleHistoryDTO.setCustomerName(invoice.getCustomer().getName());
			customerWiseSaleHistoryDTO.setInvoiceDate(invoice.getInvoiceDate());
			customerWiseSaleHistoryDTO.setInvoiceAmt(invoice.getInvoiceAmt());
			customerWiseSaleHistoryDTO.setDiscountAmt(invoice.getDiscountAmt());
			customerWiseSaleHistoryDTO.setTaxAmt(invoice.getTaxAmt());
			customerWiseSaleHistoryDTO.setAmountPaid(invoice.getAmountPaid());
			customerWiseSaleHistoryDTO.setAmountDue(invoice.getAmountDue());
			customerWiseSaleHistoryDTO.setAccountName(invoice.getInvoicePayments().getAccount().getName());
			customerWiseSaleHistoryDTO.setPaymentModeName(invoice.getInvoicePayments().getPaymentMode().getName());
			//customerWiseSaleHistoryDTO.setOutAmtTillDate(invoice.get);
	    return customerWiseSaleHistoryDTO;
	}

	
	
}
