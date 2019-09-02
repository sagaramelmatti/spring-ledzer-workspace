package com.spring.ledzer.report.strategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.spring.ledzer.model.dto.InvoiceHistoryDTO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class InvoiceHistoryReportStrategy implements ReportGenerateStrategy {

	private static Logger logger = LoggerFactory.getLogger(InvoiceHistoryReportStrategy.class);
	
	Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PdfPTable table =  null;
    String reportType = null;
    
	@Override
	public void setHeader(String type, String[] header_array) {
		try {
			reportType = type;
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 13, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Invoice History", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
        	table = new PdfPTable(header_array.length);
        	// Add PDF Table Header ->
			Stream.of(header_array)
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public ByteArrayInputStream buildReport(List<? extends Object> list) {
        try {
        	
        	document.open();
			for (Object invoice : list) {
            	PdfPCell idCell = new PdfPCell(new Phrase(((InvoiceHistoryDTO) invoice).getId().toString()));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell invoice_no = new PdfPCell(new Phrase(((InvoiceHistoryDTO) invoice).getName()));
                invoice_no.setPaddingLeft(2);
                invoice_no.setVerticalAlignment(Element.ALIGN_MIDDLE);
                invoice_no.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(invoice_no);

                PdfPCell invoice_date = new PdfPCell(new Phrase(String.valueOf(((InvoiceHistoryDTO) invoice).getInvoiceDate())));
                invoice_date.setVerticalAlignment(Element.ALIGN_MIDDLE);
                invoice_date.setHorizontalAlignment(Element.ALIGN_RIGHT);
                invoice_date.setPaddingRight(1);
                table.addCell(invoice_date);
                
                PdfPCell customer_name = new PdfPCell(new Phrase(String.valueOf(((InvoiceHistoryDTO) invoice).getCustomerName())));
                customer_name.setVerticalAlignment(Element.ALIGN_MIDDLE);
                customer_name.setHorizontalAlignment(Element.ALIGN_RIGHT);
                customer_name.setPaddingRight(1);
                table.addCell(customer_name);
                
               
                
                PdfPCell invoice_amt = new PdfPCell(new Phrase(String.valueOf(((InvoiceHistoryDTO) invoice).getInvoiceAmt())));
                invoice_amt.setVerticalAlignment(Element.ALIGN_MIDDLE);
                invoice_amt.setHorizontalAlignment(Element.ALIGN_RIGHT);
                invoice_amt.setPaddingRight(1);
                table.addCell(invoice_amt);
                
                PdfPCell amount_paid = new PdfPCell(new Phrase(String.valueOf(((InvoiceHistoryDTO) invoice).getAmountPaid())));
                amount_paid.setVerticalAlignment(Element.ALIGN_MIDDLE);
                amount_paid.setHorizontalAlignment(Element.ALIGN_RIGHT);
                amount_paid.setPaddingRight(1);
                table.addCell(amount_paid);
                
                PdfPCell amount_due = new PdfPCell(new Phrase(String.valueOf(((InvoiceHistoryDTO) invoice).getAmountDue())));
                amount_due.setVerticalAlignment(Element.ALIGN_MIDDLE);
                amount_due.setHorizontalAlignment(Element.ALIGN_RIGHT);
                amount_due.setPaddingRight(1);
                table.addCell(amount_due);
                
                PdfPCell tax = new PdfPCell(new Phrase(String.valueOf(((InvoiceHistoryDTO) invoice).getTaxAmt())));
                tax.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tax.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tax.setPaddingRight(1);
                table.addCell(tax);
                
                PdfPCell payment_mode = new PdfPCell(new Phrase(String.valueOf(((InvoiceHistoryDTO) invoice).getPaymentModeName())));
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
}
