package com.spring.ledzer.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.mapper.InvoiceDetailDTOtoInvoiceDetailEntityMapper;
import com.spring.ledzer.mapper.InvoiceDetailEntityListToEntityDTOListMapper;
import com.spring.ledzer.mapper.InvoiceDetailEntityToInvoiceDetailDTOMapper;
import com.spring.ledzer.mapper.InvoiceEntityToEntityDTO;
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDTO;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;
import com.spring.ledzer.repository.InvoiceDetailRepository;
import com.spring.ledzer.repository.InvoicePaymentsRepository;
import com.spring.ledzer.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	
	@Autowired
	InvoicePaymentsRepository invoicePaymentsRepository;
	
	@Autowired
	InvoiceEntityToEntityDTO invoiceEntityToEntityDTO;
	
	@Autowired
	InvoiceDetailEntityListToEntityDTOListMapper invoiceDetailEntityListToEntityDTOListMapper;
	
	@Autowired
	InvoiceDetailEntityToInvoiceDetailDTOMapper invoiceDetailEntityToInvoiceDetailDTOMapper;
	
	@Autowired
	InvoiceDetailDTOtoInvoiceDetailEntityMapper invoiceDetailDTOtoInvoiceDetailEntityMapper;

    private static final Logger logger = LoggerFactory.getLogger(PollService.class);

	@Override
	public List<InvoiceDTO> getAllInvoices() {
		List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(invoice -> invoiceEntityToEntityDTO.setInvoiceEntitytoInvoiceDTO(invoice)).collect(Collectors.toList());
	}
	
	@Override
	public Long getNextInvoiceId() {
		return invoiceRepository.getNextInvoiceId();
	}

	/*
	private InvoiceDTO convertToDto(Invoice invoice) 
	{
		//InvDTO invDTO = invoiceEntityToEntityDTO.setInvoiceEntitytoInvoiceDTO(invoice);
		
		ModelMapper modelMapper = new ModelMapper();
		InvoiceDTO invDTO = modelMapper.map(invoice, InvoiceDTO.class);
		
		
	    invDTO.setAccountId(invoice.getInvoicePayments().getAccount().getId());
		invDTO.setPaymentModeId(invoice.getInvoicePayments().getPaymentMode().getId());
		invDTO.setChequeNo(invoice.getInvoicePayments().getChequeNo());
		invDTO.setBankName(invoice.getInvoicePayments().getBankName());
	    return invDTO;
	}
	*/
	@Override
	public List<InvoiceDetailDTO> getAllInvoiceDetails(Long invoiceId) {
			List<InvoiceDetail> invoiceDetails = invoiceDetailRepository.findByInvoiceId(invoiceId);
			List<InvoiceDetailDTO> invoiceDetailDTO =  invoiceDetails.stream().map(invoiceDetail -> invoiceDetailEntityToInvoiceDetailDTOMapper.setInvoiceDetailEntityToInvoiceDetailDTO(invoiceDetail)).collect(Collectors.toList());
			return invoiceDetailDTO;
		
	}
	/*
	private InvoiceDetailDTO convertToInvoiceDetailDto(InvoiceDetail invoiceDetail) {
		ModelMapper modelMapper = new ModelMapper();
		InvoiceDetailDTO invoiceDetailDTO = modelMapper.map(invoiceDetail, InvoiceDetailDTO.class);
		invoiceDetailDTO.setUomId(invoiceDetail.getUom().getId());
		invoiceDetailDTO.setProductId(invoiceDetail.getProduct().getId());
		invoiceDetailDTO.setPreProductId(invoiceDetail.getProduct().getId());
		invoiceDetailDTO.setPreQuantity(invoiceDetail.getQuantity());
		invoiceDetailDTO.setInvoiceId(invoiceDetail.getInvoice().getId());
	    return invoiceDetailDTO;
	}
	*/
	@Override
	public InvoiceDTO getInvoice(Long id) throws ResourceNotFoundException {
		
		InvoiceDTO invDTO = new InvoiceDTO();
		Invoice invoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + id));
		
		invDTO = invoiceEntityToEntityDTO.setInvoiceEntitytoInvoiceDTO(invoice);
		Set<InvoiceDetailDTO>  invoiceDetailDTOSet = invoiceDetailEntityListToEntityDTOListMapper.setInvoiceDetailEntityToEntityDTO(invoice.getInvoiceDetails());
		invDTO.setInvoiceDetails(invoiceDetailDTOSet);
		return invDTO;
	}
	
	@Override
	public Invoice getInvoiceObject(Long id) throws ResourceNotFoundException {
		
		Invoice invoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + id));
		return invoice;
	}
	
	@SuppressWarnings("null")
	@Override
	@Transactional
	public Invoice save(Invoice invoice) {
		
		Invoice invoiceresult = null;
		invoiceresult = invoiceRepository.save(invoice);
		return invoiceresult;
	}
	
/*
	private InvDTO convertToInvoiceDto(Invoice invoice) 
	{
		ModelMapper modelMapper = new ModelMapper();
		InvDTO invDTO = modelMapper.map(invoice, InvDTO.class);
	    return invDTO;
	}
*/
	@Override
	public InvoiceDetail updateInvoiceDetail(InvoiceDetailDTO invoiceDetailDTO, Invoice invoice) {
		
		InvoiceDetail _invoiceDetail = invoiceDetailDTOtoInvoiceDetailEntityMapper.convertDtoToEntityMapper(invoiceDetailDTO,invoice);
		return invoiceDetailRepository.save(_invoiceDetail);
		/*
		
		InvoiceDetail invoiceDetail_val = null;//= convertToInvoiceDetailEntity(invoiceDetailDTO);
		
		Optional<InvoiceDetail> invoiceDetail_data = invoiceDetailRepository.findById(id);
		
		InvoiceDetail _invoiceDetail = invoiceDetail_data.get();
		
		_invoiceDetail.setTotalPrice(invoiceDetail_val.getTotalPrice());
		_invoiceDetail.setCgstAmt(invoiceDetail_val.getCgstAmt());
		_invoiceDetail.setCgstPer(invoiceDetail_val.getCgstPer());
		_invoiceDetail.setDiscAmt(invoiceDetail_val.getDiscAmt());
		_invoiceDetail.setDiscPer(invoiceDetail_val.getDiscPer());
		_invoiceDetail.setHsnCode(invoiceDetail_val.getHsnCode());
		_invoiceDetail.setIgstAmt(invoiceDetail_val.getIgstAmt());
		_invoiceDetail.setIgstPer(invoiceDetail_val.getIgstPer());
		_invoiceDetail.setQuantity(invoiceDetail_val.getQuantity());
		_invoiceDetail.setRate(invoiceDetail_val.getRate());
		_invoiceDetail.setRemStock(invoiceDetail_val.getRemStock());
		_invoiceDetail.setSgstAmt(invoiceDetail_val.getSgstAmt());
		_invoiceDetail.setSgstPer(invoiceDetail_val.getSgstPer());
		_invoiceDetail.setTaxableAmt(invoiceDetail_val.getTaxableAmt());
		_invoiceDetail.setRemStock(new BigDecimal(0.00));
		
		Uom uom = new Uom();
		uom.setId(invoiceDetail_val.getUom().getId());
		_invoiceDetail.setUom(uom);
		
		Product product = new Product();
		product.setId(invoiceDetail_val.getProduct().getId());
		_invoiceDetail.setProduct(product);
		
		Invoice invoice = new Invoice();
		invoice.setId(invoiceDetail_val.getInvoice().getId());
		_invoiceDetail.setInvoice(invoice);
		
		*/
		
		
	}
	
	/*
	@Override
	public Invoice updateInvoiceDetailEntry(long id, InvDTO invDTO) {
		Invoice invoiceresult = null;
		try
		{
			Invoice invoice = convertToInvoiceEntity(invDTO);
			invoiceresult = invoiceRepository.save(invoice);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return invoiceresult;
	}
	*/
	
	@Override
	public InvoiceDetailDTO getInvoiceDetail(Long invoiceDetailId) throws ResourceNotFoundException {
		
		InvoiceDetail invoiceDetail = invoiceDetailRepository.findById(invoiceDetailId)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceDetailId));
		
		InvoiceDetailDTO  invoiceDetailDTO = invoiceDetailEntityToInvoiceDetailDTOMapper.setInvoiceDetailEntityToInvoiceDetailDTO(invoiceDetail);
		return invoiceDetailDTO;
	}

}
