package com.spring.ledzer.mapper;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.Product;
import com.spring.ledzer.model.Uom;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;
import com.spring.ledzer.repository.InvoiceDetailRepository;

@Component
public class InvoiceDetailDTOtoInvoiceDetailEntityMapperImpl implements InvoiceDetailDTOtoInvoiceDetailEntityMapper {

	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	
	@Override
	public InvoiceDetail convertDtoToEntityMapper(InvoiceDetailDTO invoiceDetailDTO, Invoice invoice) {
		
		InvoiceDetail _invoiceDetail = new InvoiceDetail();
		Optional<InvoiceDetail> invoice_details_Data;
		
			if(invoiceDetailDTO.getId() != null && invoiceDetailDTO.getId() != 0)
			{
				invoice_details_Data = invoiceDetailRepository.findById(invoiceDetailDTO.getId());
				if (invoice_details_Data.isPresent()) {
					_invoiceDetail = invoice_details_Data.get();
				}
			}
			else
			{
				_invoiceDetail = new InvoiceDetail();
				_invoiceDetail.setInvoice(invoice);
			}
			_invoiceDetail.setTotalPrice(invoiceDetailDTO.getTotalPrice());
			_invoiceDetail.setCgstAmt(invoiceDetailDTO.getCgstAmt());
			_invoiceDetail.setCgstPer(invoiceDetailDTO.getCgstPer());
			_invoiceDetail.setDiscAmt(invoiceDetailDTO.getDiscAmt());
			_invoiceDetail.setDiscPer(invoiceDetailDTO.getDiscPer());
			_invoiceDetail.setHsnCode(invoiceDetailDTO.getHsnCode());
			_invoiceDetail.setIgstAmt(invoiceDetailDTO.getIgstAmt());
			_invoiceDetail.setIgstPer(invoiceDetailDTO.getIgstPer());
			_invoiceDetail.setQuantity(invoiceDetailDTO.getQuantity());
			_invoiceDetail.setRate(invoiceDetailDTO.getRate());
			_invoiceDetail.setRemStock(invoiceDetailDTO.getRemStock());
			_invoiceDetail.setSgstAmt(invoiceDetailDTO.getSgstAmt());
			_invoiceDetail.setSgstPer(invoiceDetailDTO.getSgstPer());
			_invoiceDetail.setTaxableAmt(invoiceDetailDTO.getTaxableAmt());
			_invoiceDetail.setRemStock(new BigDecimal(0.00));
				
			Uom uom = new Uom();
			uom.setId(invoiceDetailDTO.getUomId());
			_invoiceDetail.setUom(uom);
			
			Product product = new Product();
			product.setId(invoiceDetailDTO.getProductId());
			_invoiceDetail.setProduct(product);
			
			
				
		return _invoiceDetail;
	}

}
