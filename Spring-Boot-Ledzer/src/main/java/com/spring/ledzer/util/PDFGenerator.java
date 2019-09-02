package com.spring.ledzer.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.spring.ledzer.model.dto.InvoiceHistoryDTO;
import com.spring.ledzer.model.dto.ItemWiseSaleHistoryDTO;

public class PDFGenerator {
	
	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
	
	public static ByteArrayInputStream customerPDFReport(List<InvoiceHistoryDTO> invoices) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 13, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Invoice History", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
        	PdfPTable table = new PdfPTable(9);
        	// Add PDF Table Header ->
			Stream.of("ID", "Invoice No", "Invoice Date","Customer Name","Invoice Amount","Amount Paid","Amount Due","Tax","Payment Mode")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (InvoiceHistoryDTO invoice : invoices) {
            	PdfPCell idCell = new PdfPCell(new Phrase(invoice.getId().toString()));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell invoice_no = new PdfPCell(new Phrase(invoice.getName()));
                invoice_no.setPaddingLeft(2);
                invoice_no.setVerticalAlignment(Element.ALIGN_MIDDLE);
                invoice_no.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(invoice_no);

                PdfPCell invoice_date = new PdfPCell(new Phrase(String.valueOf(invoice.getInvoiceDate())));
                invoice_date.setVerticalAlignment(Element.ALIGN_MIDDLE);
                invoice_date.setHorizontalAlignment(Element.ALIGN_RIGHT);
                invoice_date.setPaddingRight(1);
                table.addCell(invoice_date);
                
                PdfPCell customer_name = new PdfPCell(new Phrase(String.valueOf(invoice.getCustomerName())));
                customer_name.setVerticalAlignment(Element.ALIGN_MIDDLE);
                customer_name.setHorizontalAlignment(Element.ALIGN_RIGHT);
                customer_name.setPaddingRight(1);
                table.addCell(customer_name);
                
               
                
                PdfPCell invoice_amt = new PdfPCell(new Phrase(String.valueOf(invoice.getInvoiceAmt())));
                invoice_amt.setVerticalAlignment(Element.ALIGN_MIDDLE);
                invoice_amt.setHorizontalAlignment(Element.ALIGN_RIGHT);
                invoice_amt.setPaddingRight(1);
                table.addCell(invoice_amt);
                
                PdfPCell amount_paid = new PdfPCell(new Phrase(String.valueOf(invoice.getAmountPaid())));
                amount_paid.setVerticalAlignment(Element.ALIGN_MIDDLE);
                amount_paid.setHorizontalAlignment(Element.ALIGN_RIGHT);
                amount_paid.setPaddingRight(1);
                table.addCell(amount_paid);
                
                PdfPCell amount_due = new PdfPCell(new Phrase(String.valueOf(invoice.getAmountDue())));
                amount_due.setVerticalAlignment(Element.ALIGN_MIDDLE);
                amount_due.setHorizontalAlignment(Element.ALIGN_RIGHT);
                amount_due.setPaddingRight(1);
                table.addCell(amount_due);
                
                PdfPCell tax = new PdfPCell(new Phrase(String.valueOf(invoice.getTaxAmt())));
                tax.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tax.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tax.setPaddingRight(1);
                table.addCell(tax);
                
                PdfPCell payment_mode = new PdfPCell(new Phrase(String.valueOf(invoice.getPaymentModeName())));
                payment_mode.setVerticalAlignment(Element.ALIGN_MIDDLE);
                payment_mode.setHorizontalAlignment(Element.ALIGN_RIGHT);
                payment_mode.setPaddingRight(1);
                table.addCell(payment_mode);
                
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	logger.error(e.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	public static ByteArrayInputStream itemWiseSalePDFReport(List<ItemWiseSaleHistoryDTO> item_wise_sale_history_list) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 13, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Invoice History", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
        	PdfPTable table = new PdfPTable(8);
        	// Add PDF Table Header ->
			Stream.of("ID", "Item Name", "UOM","Rate","Quantity","Amount","Sale Date","Invoice Name")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (ItemWiseSaleHistoryDTO item_wise_sale_history_obj : item_wise_sale_history_list) {
            	PdfPCell idCell = new PdfPCell(new Phrase(item_wise_sale_history_obj.getId().toString()));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell item_name = new PdfPCell(new Phrase(item_wise_sale_history_obj.getItemName()));
                item_name.setPaddingLeft(2);
                item_name.setVerticalAlignment(Element.ALIGN_MIDDLE);
                item_name.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(item_name);

                PdfPCell uom = new PdfPCell(new Phrase(String.valueOf(item_wise_sale_history_obj.getUomName())));
                uom.setVerticalAlignment(Element.ALIGN_MIDDLE);
                uom.setHorizontalAlignment(Element.ALIGN_RIGHT);
                uom.setPaddingRight(1);
                table.addCell(uom);
                
                PdfPCell rate = new PdfPCell(new Phrase(String.valueOf(item_wise_sale_history_obj.getRate())));
                rate.setVerticalAlignment(Element.ALIGN_MIDDLE);
                rate.setHorizontalAlignment(Element.ALIGN_RIGHT);
                rate.setPaddingRight(1);
                table.addCell(rate);
                
                PdfPCell quantity = new PdfPCell(new Phrase(String.valueOf(item_wise_sale_history_obj.getQuantity())));
                quantity.setVerticalAlignment(Element.ALIGN_MIDDLE);
                quantity.setHorizontalAlignment(Element.ALIGN_RIGHT);
                quantity.setPaddingRight(1);
                table.addCell(quantity);
                
                PdfPCell amount = new PdfPCell(new Phrase(String.valueOf(item_wise_sale_history_obj.getAmount())));
                amount.setVerticalAlignment(Element.ALIGN_MIDDLE);
                amount.setHorizontalAlignment(Element.ALIGN_RIGHT);
                amount.setPaddingRight(1);
                table.addCell(amount);
                
                PdfPCell sale_date = new PdfPCell(new Phrase(String.valueOf(item_wise_sale_history_obj.getSaleDate())));
                sale_date.setVerticalAlignment(Element.ALIGN_MIDDLE);
                sale_date.setHorizontalAlignment(Element.ALIGN_RIGHT);
                sale_date.setPaddingRight(1);
                table.addCell(sale_date);
                
                PdfPCell invoice_name = new PdfPCell(new Phrase(String.valueOf(item_wise_sale_history_obj.getInvoiceName())));
                invoice_name.setVerticalAlignment(Element.ALIGN_MIDDLE);
                invoice_name.setHorizontalAlignment(Element.ALIGN_RIGHT);
                invoice_name.setPaddingRight(1);
                table.addCell(invoice_name);
                
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	logger.error(e.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
}