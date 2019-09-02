package com.spring.ledzer.report.strategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
import com.spring.ledzer.model.dto.CustomerWiseSaleHistoryDTO;

@Component
public class CustomerWiseSaleHistoryReportStrategy implements ReportGenerateStrategy {

	private static Logger logger = LoggerFactory.getLogger(ItemWiseSaleHistoryReportStrategy.class);
	
	Document document = new Document();
    ByteArrayOutputStream out = null;
    PdfPTable table =  null;
    String reportType = null;
    Workbook workbook = null;
    CreationHelper createHelper = null;
    Sheet sheet = null;
    
	@Override
	public void setHeader(String type, String[] header_array) {
		reportType = type;
        out = new ByteArrayOutputStream();
		try {
			if(reportType.equals("pdf"))
			{
				
	        	PdfWriter.getInstance(document, out);
	            document.open();
	        	
				// Add Text to PDF file ->
				Font font = FontFactory.getFont(FontFactory.COURIER, 13, BaseColor.BLACK);
				Paragraph para = new Paragraph("Customer Wise Sale History", font);
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
			else if(reportType.equals("excel"))
			{
				workbook = new XSSFWorkbook();
				createHelper = workbook.getCreationHelper();
				sheet = workbook.createSheet("CustomerWiseSaleHistory");
				org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
			    headerFont.setBold(true);
			    headerFont.setColor(IndexedColors.BLUE.getIndex());
			    CellStyle headerCellStyle = workbook.createCellStyle();
			    headerCellStyle.setFont(headerFont);
			    // Row for Header
			    Row headerRow = sheet.createRow(0);
		      // Header
		      for (int col = 0; col < header_array.length; col++) {
		        Cell cell = headerRow.createCell(col);
		        cell.setCellValue(header_array[col]);
		        cell.setCellStyle(headerCellStyle);
		      }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public ByteArrayInputStream buildReport(List<? extends Object> list) {
        try {
        	workbook = new XSSFWorkbook();
	        CreationHelper createHelper = workbook.getCreationHelper();
	        
        	if(reportType.equals("pdf"))
        	{
        		document.open();
    			for (Object customer_wise_sale_history_obj : list) {
    				PdfPCell idCell = new PdfPCell(new Phrase(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getId().toString()));
                	idCell.setPaddingLeft(4);
                	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(idCell);

                    PdfPCell invoice_name = new PdfPCell(new Phrase(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getInvoiceName()));
                    invoice_name.setPaddingLeft(2);
                    invoice_name.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    invoice_name.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(invoice_name);

                    PdfPCell customer_name = new PdfPCell(new Phrase(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getCustomerName()));
                    customer_name.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    customer_name.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    customer_name.setPaddingRight(1);
                    table.addCell(customer_name);
                    
                    PdfPCell invoice_date = new PdfPCell(new Phrase(String.valueOf(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getInvoiceDate())));
                    invoice_date.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    invoice_date.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    invoice_date.setPaddingRight(1);
                    table.addCell(invoice_date);
                    
                    PdfPCell invoice_amt = new PdfPCell(new Phrase(String.valueOf(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getInvoiceAmt())));
                    invoice_amt.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    invoice_amt.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    invoice_amt.setPaddingRight(1);
                    table.addCell(invoice_amt);
                    
                    PdfPCell discount_amt = new PdfPCell(new Phrase(String.valueOf(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getDiscountAmt())));
                    discount_amt.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    discount_amt.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    discount_amt.setPaddingRight(1);
                    table.addCell(discount_amt);
                    
                    PdfPCell tax = new PdfPCell(new Phrase(String.valueOf(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getTaxAmt())));
                    tax.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tax.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tax.setPaddingRight(1);
                    table.addCell(tax);
                    
                    PdfPCell amount_paid = new PdfPCell(new Phrase(String.valueOf(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getAmountPaid())));
                    amount_paid.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    amount_paid.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    amount_paid.setPaddingRight(1);
                    table.addCell(amount_paid);
                    
                    PdfPCell amount_due = new PdfPCell(new Phrase(String.valueOf(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getAmountDue())));
                    amount_due.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    amount_due.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    amount_due.setPaddingRight(1);
                    table.addCell(amount_due);
                    
                    PdfPCell account = new PdfPCell(new Phrase(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getAccountName()));
                    account.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    account.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    account.setPaddingRight(1);
                    table.addCell(account);
                    
                    PdfPCell payment_mode = new PdfPCell(new Phrase(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_obj).getPaymentModeName()));
                    payment_mode.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    payment_mode.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    payment_mode.setPaddingRight(1);
                    table.addCell(payment_mode);
                    
                }
                document.add(table);
                document.close();
        	}
        	else if(reportType.equals("excel"))
        	{
        		// CellStyle for Age
      	      CellStyle ageCellStyle = workbook.createCellStyle();
      	      ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
      	   
      	      int rowIdx = 1;
      	      for (Object customer_wise_sale_history_dto : list) {
      	        Row row = sheet.createRow(rowIdx++);
      	   
      	        row.createCell(0).setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getId());
      	        row.createCell(1).setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getInvoiceName());
      	        row.createCell(2).setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getCustomerName());
      	   
      	        Cell invoicedateCel = row.createCell(3);
      	        invoicedateCel.setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getInvoiceDate());
      	        invoicedateCel.setCellStyle(ageCellStyle);
      	        
      	        Cell invoiceAmtCell = row.createCell(4);
      	        invoiceAmtCell.setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getInvoiceAmt().doubleValue());
      	        invoiceAmtCell.setCellStyle(ageCellStyle);
      	        
      	        Cell discountAmtCell = row.createCell(5);
      	        discountAmtCell.setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getDiscountAmt().doubleValue());
      	        discountAmtCell.setCellStyle(ageCellStyle);
      	        
      	        Cell taxCell = row.createCell(6);
      	        taxCell.setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getTaxAmt().doubleValue());
      	        taxCell.setCellStyle(ageCellStyle);
    	        
    	        Cell amountPaidCell = row.createCell(7);
    	        amountPaidCell.setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getAmountPaid().doubleValue());
    	        amountPaidCell.setCellStyle(ageCellStyle);
    	        
    	        Cell amountDueCell = row.createCell(8);
    	        amountDueCell.setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getAmountDue().doubleValue());
    	        amountDueCell.setCellStyle(ageCellStyle);
      	        
      	        row.createCell(9).setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getAccountName());
      	        row.createCell(10).setCellValue(((CustomerWiseSaleHistoryDTO) customer_wise_sale_history_dto).getPaymentModeName());
      	        
      	      }
      	      workbook.write(out);
        	}
        	
        	
        }
        catch(DocumentException e) {
        	logger.error(e.toString());
        }
        catch(IOException io) {
        	logger.error(io.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
}
