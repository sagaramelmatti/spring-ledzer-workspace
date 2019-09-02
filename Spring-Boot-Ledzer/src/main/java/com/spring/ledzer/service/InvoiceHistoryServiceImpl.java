package com.spring.ledzer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.mapper.InvoiceHistoryEntityToInvoiceHistoryDTOMapper;
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.dto.InvoiceHistoryDTO;
import com.spring.ledzer.repository.InvoiceRepository;

@Service
public class InvoiceHistoryServiceImpl implements InvoiceHistoryService
{

	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	InvoiceHistoryEntityToInvoiceHistoryDTOMapper invoiceHistoryEntityToInvoiceHistoryDTOMapper;
	
    private static final Logger logger = LoggerFactory.getLogger(PollService.class);

	@Override
	public List<InvoiceHistoryDTO> getInvoicesSaleHistory() throws ResourceNotFoundException {
		List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(invoice -> invoiceHistoryEntityToInvoiceHistoryDTOMapper.setInvoiceHistoryEntityToInvoiceHistoryDTOMapper(invoice)).collect(Collectors.toList());
	}
}
