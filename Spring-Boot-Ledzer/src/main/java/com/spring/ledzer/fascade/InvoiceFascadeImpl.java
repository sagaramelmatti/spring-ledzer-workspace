package com.spring.ledzer.fascade;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.mapper.InvoiceDTOtoInvoicePayments;
import com.spring.ledzer.mapper.InvoiceDetailDTOListtoInvoiceDetailEntityListMapper;
import com.spring.ledzer.mapper.InvoiceDetailDTOtoInvoiceDetailEntityMapper;
import com.spring.ledzer.mapper.InvoiceDtoToEntityMapper;
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.InvoicePayments;
import com.spring.ledzer.model.dto.InvoiceDTO;
import com.spring.ledzer.service.InvoiceService;

@Component
public class InvoiceFascadeImpl implements InvoiceFascade {

	@Autowired
	ProcessInvoice processInvoice;
	
	@Autowired
	InvoiceDtoToEntityMapper invoiceDtoToEntityMapper;
	
	@Autowired
	InvoiceDTOtoInvoicePayments invoiceDTOtoInvoicePayments;
	
	@Autowired
	ProcessAccount processAccount;
	
	@Autowired
	ProcessAccountTransaction processAccountTransaction;
	
	@Autowired
	ProcessProductStockImpl processProductStockImpl;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	InvoiceDetailDTOListtoInvoiceDetailEntityListMapper invoiceDetailDTOListtoInvoiceDetailEntityListMapper;
	
	@Autowired
	InvoiceDetailDTOtoInvoiceDetailEntityMapper invoiceDetailDTOtoInvoiceDetailEntityMapper;
	
	@Override
	@Transactional
	public Invoice saveInvoice(InvoiceDTO invoiceDTO, long invoiceId) throws ResourceNotFoundException {
		
		Invoice invoice = null; 
		
		Set<InvoiceDetail> invoiceDetails_set = null;
		
		if(invoiceId != -1)
		{
			invoice =  invoiceDtoToEntityMapper.setInvoiceDTOtoInvoiceEnity(invoiceDTO, invoiceId);
			//invoiceDetails_set = invoiceDetailDTOtoInvoiceDetailEntityListMapper.convertDtoToEntityMapper(invoice,invoiceDTO.getInvoiceDetails());
		}
		else
		{
			invoice =  invoiceDtoToEntityMapper.setInvoiceDTOtoInvoiceEnity(invoiceDTO, -1);
		}
		
		invoiceDetails_set = invoiceDetailDTOListtoInvoiceDetailEntityListMapper.convertDtoToEntityMapper(invoice,invoiceDTO.getInvoiceDetails());
		
		InvoicePayments invoicePayments  = invoiceDTOtoInvoicePayments.setInvoicePayments(invoiceDTO, invoice);
		
		invoice = processInvoice.createNewInvoice(invoice, invoiceDetails_set, invoicePayments);
		
		processProductStockImpl.updateStock(invoiceDetails_set.stream().findFirst().get(),invoiceDTO.getInvoiceDetails().stream().findFirst().get() );
		
		int no_account_updatd = processAccount.updateAccountDetails(invoiceDTO.getAccountId(),invoiceDTO.getInvoiceAmt(),"invoice");
		
		if(invoiceDTO.getId() != 0 || invoiceDTO.getId() != null)
		{
			Invoice new_invoice = invoiceDtoToEntityMapper.setInvoiceDTOtoInvoiceEnity(invoiceDTO,invoice.getId());
			invoice = invoiceService.save(new_invoice);
		}
		if(invoiceDetails_set.stream().findFirst().get().getId() != null && invoiceDetails_set.stream().findFirst().get().getId() != 0)
		{
			invoiceService.updateInvoiceDetail(invoiceDTO.getInvoiceDetails().stream().findFirst().get(), invoice);
		}
		
		return invoice;
		
	}

}
