package com.spring.ledzer.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.mapper.PurchaseDetailDTOtoPurchaseDetailEntityMapper;
import com.spring.ledzer.mapper.PurchaseDetailEntityListToPurchaseDTOListMapper;
import com.spring.ledzer.mapper.PurchaseDetailEntityToPurchaseDetailDTOMapper;
import com.spring.ledzer.mapper.PurchaseEntityToPurchaseDTO;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDTO;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;
import com.spring.ledzer.repository.PurchaseDetailRepository;
import com.spring.ledzer.repository.PurchasePaymentRepository;
import com.spring.ledzer.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	PurchaseDetailRepository purchaseDetailRepository;
	
	@Autowired
	PurchasePaymentRepository purchasePaymentsRepository;
	
	@Autowired
	PurchaseEntityToPurchaseDTO purchaseEntityToPurchaseDTO;
	
	@Autowired
	PurchaseDetailEntityToPurchaseDetailDTOMapper purchaseDetailEntityToPurchaseDetailDTOMapper;
	
	@Autowired
	PurchaseDetailEntityListToPurchaseDTOListMapper purchaseDetailEntityListToPurchaseDTOListMapper;
	
	@Autowired
	PurchaseDetailDTOtoPurchaseDetailEntityMapper purchaseDetailDTOtoPurchaseDetailEntityMapper;
	

    private static final Logger logger = LoggerFactory.getLogger(PurchaseService.class);

	@Override
	public List<PurchaseDTO> getAllPurchases() {
		List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream().map(   purchase -> purchaseEntityToPurchaseDTO.setPurchaseEntitytoPurchaseDTO(purchase)).collect(Collectors.toList());
	}
	
	@Override
	public Long getNextPurchaseId() {
		return purchaseRepository.getNextPurchaseId();
	}
	
	@Override
	public PurchaseDetailDTO getPurchaseDetail(Long id) throws ResourceNotFoundException {
		
		PurchaseDetail purchaseDetail = purchaseDetailRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Purchase not found for this id :: " + id));
		
		PurchaseDetailDTO purchaseDetailDTO  = purchaseDetailEntityToPurchaseDetailDTOMapper.setPurchaseDetailEntityToPurchaseDetailDTO(purchaseDetail);
		return purchaseDetailDTO;
	}
	
	@Override
	public Purchase updatePurchaseDetailEntry(long id, PurchaseDTO PurDTO) {
		Purchase purchaseresult = null;
		try
		{
			//	Purchase purchase = convertToPurchaseEntity(PurDTO);
			//	purchaseresult = purchaseRepository.save(purchase);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return purchaseresult;
	}

	/*
	private PurchaseDTO convertToDto(Purchase purchase) 
	{
		
		ModelMapper modelMapper = new ModelMapper();
		PurchaseDTO PurDTO = modelMapper.map(purchase, PurchaseDTO.class);
		
		PurDTO.setSupplier(purchase.getSupplier());
	    PurDTO.setAccountId(purchase.getPurchasePayment().getAccount().getId());
		PurDTO.setPaymentModeId(purchase.getPurchasePayment().getPaymentMode().getId());
		PurDTO.setChequeNo(purchase.getPurchasePayment().getChequeNo());
		PurDTO.setBankName(purchase.getPurchasePayment().getBankName());
	    return PurDTO;
	}
	*/
	@Override
	public Set<PurchaseDetailDTO> getAllPurchaseDetails(Long purchaseId) {
			Set<PurchaseDetail> purchaseDetails = purchaseDetailRepository.findByPurchaseId(purchaseId);
			Set<PurchaseDetailDTO> purchase_detail_list = purchaseDetailEntityListToPurchaseDTOListMapper.setPurchaseDetailEntityListToPurchaseDetailDTOList(purchaseDetails);
			return purchase_detail_list;
		
	}
	/*
	private PurchaseDetailDTO convertToPurchaseDetailDto(PurchaseDetail purchaseDetail) {
		ModelMapper modelMapper = new ModelMapper();
		PurchaseDetailDTO purchaseDetailDTO = modelMapper.map(purchaseDetail, PurchaseDetailDTO.class);
		purchaseDetailDTO.setUomId(purchaseDetail.getUom().getId());
		purchaseDetailDTO.setProductId(purchaseDetail.getProduct().getId());
		purchaseDetailDTO.setPurchaseId(purchaseDetail.getPurchase().getId());
	    return purchaseDetailDTO;
	}
	*/
	
	@Override
	public PurchaseDTO getPurchase(Long id) throws ResourceNotFoundException {
		
		PurchaseDTO purDTO = new PurchaseDTO();
		Purchase purchase = purchaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Purchase not found for this id :: " + id));
		purDTO = purchaseEntityToPurchaseDTO.setPurchaseEntitytoPurchaseDTO(purchase);
		Set<PurchaseDetailDTO>  purchaseDetailDTOSet = purchaseDetailEntityListToPurchaseDTOListMapper.setPurchaseDetailEntityListToPurchaseDetailDTOList(purchase.getPurchaseDetails());
		purDTO.setPurchaseDetails(purchaseDetailDTOSet);
		return purDTO;
		
	}
	
	@SuppressWarnings("null")
	@Override
	public Purchase save(Purchase purchase) {
		
		Purchase purchaseresult = null;
		try
		{
			purchaseresult = purchaseRepository.save(purchase);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return purchaseresult;
	}
	
	/*
	private Purchase convertToEntity(PurDTO PurDTO) throws ParseException {
		
		Purchase purchase = new Purchase();
		Set<PurchaseDetail> purchaseDetail_list = new HashSet<PurchaseDetail>();
		PurchasePayment   purchasePayments = null;
		
		BigDecimal totalPrice = new BigDecimal(0.00);
		try
	    {
			
			purchase.setPurchaseNo(PurDTO.getPurchaseNo());
			purchase.setName(PurDTO.getName());
			if (PurDTO.getSupplierId() != null) {
		    	
	    		Supplier supplier = new Supplier();
		    	supplier.setId(PurDTO.getSupplierId());
		    	purchase.setSupplier(supplier);
		    }
			
			purchase.setDiscountAmt(PurDTO.getDiscountAmt());
			purchase.setId(PurDTO.getId());
			
			purchase.setPurchaseDate(PurDTO.getPurchaseDate());
			
			purchase.setPurchaseNo(PurDTO.getPurchaseNo());
		    
		    if (PurDTO.getPaymentModeId() != null) 
		    {
		    	purchasePayments = purchasePaymentsRepository.findByPurchase(purchase);
		    	
		    	if(purchasePayments == null)
		    	{
		    		purchasePayments = new PurchasePayment();
		    		Long payment_number = purchasePaymentsRepository.getNextPaymentNumber();
		    		purchasePayments.setPaymentNumber(payment_number);
			    	purchasePayments.setPaymentVoucher("PUR-PYMT"+ payment_number);
		    	}
		    	
		    	Account account = new Account();
		    	account.setId(PurDTO.getAccountId());
		    	purchasePayments.setAccount(account);
		    	
		    	PaymentMode paymentMode = new PaymentMode();
		    	paymentMode.setId(PurDTO.getPaymentModeId());
		    	purchasePayments.setPaymentMode(paymentMode);
		    	
		    	purchasePayments.setChequeNo(PurDTO.getChequeNo());
		    	purchasePayments.setBankName(PurDTO.getBankName());
		    	
		    	
		    	purchasePayments.setPurchaseTotalAmt(PurDTO.getPurchaseAmt());
		    	purchasePayments.setAmountPaid(PurDTO.getAmountPaid());
		    	purchasePayments.setAmountDue(PurDTO.getAmountDue());
		    	purchasePayments.setPaymentDate(PurDTO.getPurchaseDate());
		    	
		    	Supplier customer_new = new Supplier();
		    	customer_new.setId(PurDTO.getSupplierId());
		    	purchasePayments.setSupplier(customer_new);
		    	
		    	purchasePayments.setPurchase(purchase);
		    	
		    	purchase.setPurchasePayment(purchasePayments);
		    }
		    
		    purchase.setName(PurDTO.getName());
		    purchase.setRoundAmt(PurDTO.getRoundAmt());
		    purchase.setTaxAmt(PurDTO.getTaxAmt());
		    
		    Set<PurchaseDetailDTO> purchaseDetailDTOs = PurDTO.getPurchaseDetailDTOs();
			
			for(PurchaseDetailDTO purchaseDetailDTO : purchaseDetailDTOs)
			{
				PurchaseDetail purchaseDetail = new PurchaseDetail();
				
				purchaseDetail.setTotalPrice(purchaseDetailDTO.getTotalPrice());
				purchaseDetail.setCgstAmt(purchaseDetailDTO.getCgstAmt());
				purchaseDetail.setCgstPer(purchaseDetailDTO.getCgstPer());
				purchaseDetail.setDiscAmt(purchaseDetailDTO.getDiscAmt());
				purchaseDetail.setDiscPer(purchaseDetailDTO.getDiscPer());
				purchaseDetail.setHsnCode(purchaseDetailDTO.getHsnCode());
				purchaseDetail.setIgstAmt(purchaseDetailDTO.getIgstAmt());
				purchaseDetail.setIgstPer(purchaseDetailDTO.getIgstPer());
				purchaseDetail.setQuantity(purchaseDetailDTO.getQuantity());
				purchaseDetail.setRate(purchaseDetailDTO.getRate());
				purchaseDetail.setRemStock(purchaseDetailDTO.getRemStock());
				purchaseDetail.setSgstAmt(purchaseDetailDTO.getSgstAmt());
				purchaseDetail.setSgstPer(purchaseDetailDTO.getSgstPer());
				purchaseDetail.setTaxableAmt(purchaseDetailDTO.getTaxableAmt());
				purchaseDetail.setRemStock(new BigDecimal(0.00));
				totalPrice = purchaseDetailDTO.getTotalPrice();
				
				Uom uom = new Uom();
				uom.setId(purchaseDetailDTO.getUomId());
				purchaseDetail.setUom(uom);
				
				Product product = new Product();
				product.setId(purchaseDetailDTO.getProductId());
				product.setStock(product.getStock().subtract(purchaseDetail.getQuantity()));
				purchaseDetail.setProduct(product);
				
				purchaseDetail.setPurchase(purchase);
				purchaseDetail_list.add(purchaseDetail);
			}
			
			purchase.setPurchaseDetails(purchaseDetail_list);
			
			purchase.setBillAmt(PurDTO.getBillAmt());
			purchase.setPurchaseAmt(PurDTO.getPurchaseAmt());
			purchase.setAmountDue(PurDTO.getAmountDue());
			purchase.setAmountPaid(PurDTO.getAmountPaid());
		    
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    }
	    
	    return purchase;
	};
	
	private PurchaseDetail convertToPurchasedetailDto(PurchaseDetailDTO purchaseDetailDTO) {
		
		ModelMapper modelMapper = new ModelMapper();
		PurchaseDetail purchaseDetail = modelMapper.map(purchaseDetailDTO, PurchaseDetail.class);
		
		Uom uom = new Uom();
		uom.setId(purchaseDetailDTO.getUomId());
		purchaseDetail.setUom(uom);
		
		Product product = new Product();
		product.setId(purchaseDetailDTO.getProductId());
		purchaseDetail.setProduct(product);
		
		Purchase purchase = new Purchase();
		purchase.setId(purchaseDetailDTO.getPurchaseId());
		purchaseDetail.setPurchase(purchase);
		
	    return purchaseDetail;
	}
*/
	/*
	@Override
	public List<PurchaseDTO> getAllPurchasesList() {
		List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream().map(purchase -> convertToPurDTO(purchase)).collect(Collectors.toList());
	}
	*/
	/*
	private PurchaseDTO convertToPurDTO(Purchase purchase) 
	{
		ModelMapper modelMapper = new ModelMapper();
		PurchaseDTO PurDTO = modelMapper.map(purchase, PurchaseDTO.class);
	    return PurDTO;
	}
	*/

	@Override
	public PurchaseDetail updatePurchaseDetail(Purchase purchase,PurchaseDetailDTO purchaseDetailDTO) {
			
		PurchaseDetail purchaseDetail = purchaseDetailDTOtoPurchaseDetailEntityMapper.convertDtoToEntityMapper(purchase, purchaseDetailDTO);
		return purchaseDetailRepository.save(purchaseDetail);
		/*
		
		PurchaseDetail purchaseDetail_val = convertToPurchaseDetailEntity(purchaseDetailDTO);
		
		Optional<PurchaseDetail> purchaseDetail_data = purchaseDetailRepository.findById(id);
		
		PurchaseDetail _purchaseDetail = purchaseDetail_data.get();
		
		_purchaseDetail.setTotalPrice(purchaseDetail_val.getTotalPrice());
		_purchaseDetail.setCgstAmt(purchaseDetail_val.getCgstAmt());
		_purchaseDetail.setCgstPer(purchaseDetail_val.getCgstPer());
		_purchaseDetail.setDiscAmt(purchaseDetail_val.getDiscAmt());
		_purchaseDetail.setDiscPer(purchaseDetail_val.getDiscPer());
		_purchaseDetail.setHsnCode(purchaseDetail_val.getHsnCode());
		_purchaseDetail.setIgstAmt(purchaseDetail_val.getIgstAmt());
		_purchaseDetail.setIgstPer(purchaseDetail_val.getIgstPer());
		_purchaseDetail.setQuantity(purchaseDetail_val.getQuantity());
		_purchaseDetail.setRate(purchaseDetail_val.getRate());
		_purchaseDetail.setRemStock(purchaseDetail_val.getRemStock());
		_purchaseDetail.setSgstAmt(purchaseDetail_val.getSgstAmt());
		_purchaseDetail.setSgstPer(purchaseDetail_val.getSgstPer());
		_purchaseDetail.setTaxableAmt(purchaseDetail_val.getTaxableAmt());
		_purchaseDetail.setRemStock(new BigDecimal(0.00));
		
		Uom uom = new Uom();
		uom.setId(purchaseDetail_val.getUom().getId());
		_purchaseDetail.setUom(uom);
		
		Product product = new Product();
		product.setId(purchaseDetail_val.getProduct().getId());
		_purchaseDetail.setProduct(product);
		
		Purchase purchase = new Purchase();
		purchase.setId(purchaseDetail_val.getPurchase().getId());
		_purchaseDetail.setPurchase(purchase);
		
		return purchaseDetailRepository.save(_purchaseDetail);
		*/
		
	}
	
	/*
	private PurchaseDetail convertToPurchaseDetailEntity(PurchaseDetail purchaseDetailDTO) throws ParseException {
		
		PurchaseDetail purchaseDetail = new PurchaseDetail();
		
		purchaseDetail.setTotalPrice(purchaseDetailDTO.getTotalPrice());
		purchaseDetail.setCgstAmt(purchaseDetailDTO.getCgstAmt());
		purchaseDetail.setCgstPer(purchaseDetailDTO.getCgstPer());
		purchaseDetail.setDiscAmt(purchaseDetailDTO.getDiscAmt());
		purchaseDetail.setDiscPer(purchaseDetailDTO.getDiscPer());
		purchaseDetail.setHsnCode(purchaseDetailDTO.getHsnCode());
		purchaseDetail.setIgstAmt(purchaseDetailDTO.getIgstAmt());
		purchaseDetail.setIgstPer(purchaseDetailDTO.getIgstPer());
		purchaseDetail.setQuantity(purchaseDetailDTO.getQuantity());
		purchaseDetail.setRate(purchaseDetailDTO.getRate());
		purchaseDetail.setRemStock(purchaseDetailDTO.getRemStock());
		purchaseDetail.setSgstAmt(purchaseDetailDTO.getSgstAmt());
		purchaseDetail.setSgstPer(purchaseDetailDTO.getSgstPer());
		purchaseDetail.setTaxableAmt(purchaseDetailDTO.getTaxableAmt());
		purchaseDetail.setRemStock(new BigDecimal(0.00));
		
		Uom uom = new Uom();
		uom.setId(purchaseDetailDTO.getUomId());
		purchaseDetail.setUom(uom);
		
		Product product = new Product();
		product.setId(purchaseDetailDTO.getProductId());
		purchaseDetail.setProduct(product);
		
		Purchase purchase = new Purchase();
		purchase.setId(purchaseDetailDTO.getPurchaseId());
		purchaseDetail.setPurchase(purchase);
			
	    return purchaseDetail;
	}
*/
	
	
	/*
	
	private Purchase convertToPurchaseEntity(PurDTO PurDTO) throws ParseException {
		
		Purchase purchase = new Purchase();
		Set<PurchaseDetail> purchaseDetail_list = new HashSet<PurchaseDetail>();
		try
	    {
			purchase.setPurchaseNo(PurDTO.getPurchaseNo());
			purchase.setName(PurDTO.getName());
			purchase.setAmountDue(PurDTO.getAmountDue());
			purchase.setAmountPaid(PurDTO.getAmountPaid());
			purchase.setBillAmt(PurDTO.getBillAmt());
			if (PurDTO.getSupplierId() != null) {
		    	
	    		Supplier supplier = new Supplier();
		    	supplier.setId(PurDTO.getSupplierId());
		    	purchase.setSupplier(supplier);
		    }
			
			purchase.setDiscountAmt(PurDTO.getDiscountAmt());
			purchase.setId(PurDTO.getId());
			purchase.setPurchaseAmt(PurDTO.getPurchaseAmt());
			purchase.setPurchaseDate(PurDTO.getPurchaseDate());
			
		    if (PurDTO.getPaymentModeId() != null) 
		    {
		    	PurchasePayment   purchasePayments = purchasePaymentsRepository.findByPurchase(purchase);
		    	
		    	Account account = new Account();
		    	account.setId(PurDTO.getAccountId());
		    	purchasePayments.setAccount(account);
		    	
		    	PaymentMode paymentMode = new PaymentMode();
		    	paymentMode.setId(PurDTO.getPaymentModeId());
		    	purchasePayments.setPaymentMode(paymentMode);
		    	
		    	purchasePayments.setChequeNo(PurDTO.getChequeNo());
		    	purchasePayments.setBankName(PurDTO.getBankName());
		    	
		    	purchasePayments.setPurchaseTotalAmt(PurDTO.getPurchaseAmt());
		    	purchasePayments.setAmountPaid(PurDTO.getAmountPaid());
		    	purchasePayments.setAmountDue(PurDTO.getAmountDue());
		    	purchasePayments.setPaymentDate(PurDTO.getPurchaseDate());
		    	
		    	Supplier customer_new = new Supplier();
		    	customer_new.setId(PurDTO.getSupplierId());
		    	purchasePayments.setSupplier(customer_new);
		    	
		    	purchase.setPurchasePayment(purchasePayments);
		    }
		    
		    purchase.setRoundAmt(PurDTO.getRoundAmt());
		    purchase.setTaxAmt(PurDTO.getTaxAmt());
		    purchase.setCreatedBy(new Long(1));
		    
		    Set<PurchaseDetailDTO> purchaseDetailDTOs = PurDTO.getPurchaseDetailDTOs();
			
			for(PurchaseDetailDTO purchaseDetailDTO : purchaseDetailDTOs)
			{
				Optional<PurchaseDetail> purchaseData = purchaseDetailRepository.findById(purchaseDetailDTO.getId());
				if (purchaseData.isPresent()) {
					
					PurchaseDetail _purchaseDetail = purchaseData.get();
					_purchaseDetail.setTotalPrice(purchaseDetailDTO.getTotalPrice());
					_purchaseDetail.setCgstAmt(purchaseDetailDTO.getCgstAmt());
					_purchaseDetail.setCgstPer(purchaseDetailDTO.getCgstPer());
					_purchaseDetail.setDiscAmt(purchaseDetailDTO.getDiscAmt());
					_purchaseDetail.setDiscPer(purchaseDetailDTO.getDiscPer());
					_purchaseDetail.setHsnCode(purchaseDetailDTO.getHsnCode());
					_purchaseDetail.setIgstAmt(purchaseDetailDTO.getIgstAmt());
					_purchaseDetail.setIgstPer(purchaseDetailDTO.getIgstPer());
					_purchaseDetail.setQuantity(purchaseDetailDTO.getQuantity());
					_purchaseDetail.setRate(purchaseDetailDTO.getRate());
					_purchaseDetail.setRemStock(purchaseDetailDTO.getRemStock());
					_purchaseDetail.setSgstAmt(purchaseDetailDTO.getSgstAmt());
					_purchaseDetail.setSgstPer(purchaseDetailDTO.getSgstPer());
					_purchaseDetail.setTaxableAmt(purchaseDetailDTO.getTaxableAmt());
					_purchaseDetail.setRemStock(new BigDecimal(0.00));
					
					Uom uom = new Uom();
					uom.setId(purchaseDetailDTO.getUomId());
					_purchaseDetail.setUom(uom);
					
					Product product = new Product();
					product.setId(purchaseDetailDTO.getProductId());
					_purchaseDetail.setProduct(product);
					
					_purchaseDetail.setPurchase(purchase);
					purchaseDetail_list.add(_purchaseDetail);
					
				}
				
			}
			
			purchase.setPurchaseDetails(purchaseDetail_list);
		    
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    }
	    
	    return purchase;
	}
	*/
	
}
