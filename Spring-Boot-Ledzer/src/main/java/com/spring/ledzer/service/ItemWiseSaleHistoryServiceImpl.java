package com.spring.ledzer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.mapper.InvoiceHistoryEntityToInvoiceHistoryDTOMapper;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.ItemWiseSaleHistoryDTO;
import com.spring.ledzer.repository.InvoiceDetailRepository;

@Service
public class ItemWiseSaleHistoryServiceImpl implements ItemWiseSaleHistoryService
{

	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	
	@Autowired
	InvoiceHistoryEntityToInvoiceHistoryDTOMapper invoiceHistoryEntityToInvoiceHistoryDTOMapper;
	
    private static final Logger logger = LoggerFactory.getLogger(ItemWiseSaleHistoryServiceImpl.class);

	@Override
	public List<ItemWiseSaleHistoryDTO> getItemWiseSaleHistory() throws ResourceNotFoundException {
		List<InvoiceDetail> invoiceDetaillist = invoiceDetailRepository.findAll();
        return invoiceDetaillist.stream().map(invoiceDetail -> convertToItemWiseSaleHistoryDTO(invoiceDetail)).collect(Collectors.toList());
	}
	
	private ItemWiseSaleHistoryDTO convertToItemWiseSaleHistoryDTO(InvoiceDetail invoiceDetail) {
			ItemWiseSaleHistoryDTO itemWiseSaleHistoryDTO = new ItemWiseSaleHistoryDTO();
			
			itemWiseSaleHistoryDTO.setId(invoiceDetail.getId());
			itemWiseSaleHistoryDTO.setInvoiceName(invoiceDetail.getInvoice().getName());
			itemWiseSaleHistoryDTO.setItemName(invoiceDetail.getProduct().getName());
			itemWiseSaleHistoryDTO.setUomName(invoiceDetail.getUom().getName());
			itemWiseSaleHistoryDTO.setRate(invoiceDetail.getRate());
			itemWiseSaleHistoryDTO.setQuantity(invoiceDetail.getQuantity());
			itemWiseSaleHistoryDTO.setAmount(invoiceDetail.getTotalPrice());
			itemWiseSaleHistoryDTO.setSaleDate(invoiceDetail.getInvoice().getInvoiceDate());
			itemWiseSaleHistoryDTO.setInvoiceName(invoiceDetail.getInvoice().getName());
			
	    return itemWiseSaleHistoryDTO;
	}
	
}
