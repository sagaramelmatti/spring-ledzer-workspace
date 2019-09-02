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
import com.spring.ledzer.model.dto.ItemWiseSaleHistoryDTO;

@Component
public class ItemWiseSaleHistoryReportStrategy implements ReportGenerateStrategy {

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
			else if(reportType.equals("excel"))
			{
				workbook = new XSSFWorkbook();
				createHelper = workbook.getCreationHelper();
				sheet = workbook.createSheet("InvoiceHistory");
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
    			for (Object item_wise_sale_history_obj : list) {
    				PdfPCell idCell = new PdfPCell(new Phrase(((ItemWiseSaleHistoryDTO) item_wise_sale_history_obj).getId().toString()));
                	idCell.setPaddingLeft(4);
                	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(idCell);

                    PdfPCell item_name = new PdfPCell(new Phrase(String.valueOf(((ItemWiseSaleHistoryDTO) item_wise_sale_history_obj).getItemName())));
                    item_name.setPaddingLeft(2);
                    item_name.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    item_name.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(item_name);

                    PdfPCell uom = new PdfPCell(new Phrase(String.valueOf(((ItemWiseSaleHistoryDTO) item_wise_sale_history_obj).getUomName())));
                    uom.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    uom.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    uom.setPaddingRight(1);
                    table.addCell(uom);
                    
                    PdfPCell rate = new PdfPCell(new Phrase(String.valueOf(((ItemWiseSaleHistoryDTO) item_wise_sale_history_obj).getRate())));
                    rate.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    rate.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    rate.setPaddingRight(1);
                    table.addCell(rate);
                    
                    PdfPCell quantity = new PdfPCell(new Phrase(String.valueOf(((ItemWiseSaleHistoryDTO) item_wise_sale_history_obj).getQuantity())));
                    quantity.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    quantity.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    quantity.setPaddingRight(1);
                    table.addCell(quantity);
                    
                    PdfPCell amount = new PdfPCell(new Phrase(String.valueOf(((ItemWiseSaleHistoryDTO) item_wise_sale_history_obj).getAmount())));
                    amount.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    amount.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    amount.setPaddingRight(1);
                    table.addCell(amount);
                    
                    PdfPCell sale_date = new PdfPCell(new Phrase(String.valueOf(((ItemWiseSaleHistoryDTO) item_wise_sale_history_obj).getSaleDate())));
                    sale_date.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    sale_date.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    sale_date.setPaddingRight(1);
                    table.addCell(sale_date);
                    
                    PdfPCell invoice_name = new PdfPCell(new Phrase(String.valueOf(((ItemWiseSaleHistoryDTO) item_wise_sale_history_obj).getInvoiceName())));
                    invoice_name.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    invoice_name.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    invoice_name.setPaddingRight(1);
                    table.addCell(invoice_name);
                    
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
      	      for (Object invoice_history_dto : list) {
      	        Row row = sheet.createRow(rowIdx++);
      	   
      	        row.createCell(0).setCellValue(((ItemWiseSaleHistoryDTO) invoice_history_dto).getId());
      	        row.createCell(1).setCellValue(((ItemWiseSaleHistoryDTO) invoice_history_dto).getItemName());
      	        row.createCell(2).setCellValue(((ItemWiseSaleHistoryDTO) invoice_history_dto).getUomName());
      	   
      	        Cell rateCell = row.createCell(3);
      	        rateCell.setCellValue(((ItemWiseSaleHistoryDTO) invoice_history_dto).getRate().doubleValue());
      	        rateCell.setCellStyle(ageCellStyle);
      	        
      	        Cell quantityCell = row.createCell(4);
      	        quantityCell.setCellValue(((ItemWiseSaleHistoryDTO) invoice_history_dto).getQuantity().doubleValue());
      	        quantityCell.setCellStyle(ageCellStyle);
      	        
      	        Cell amountCell = row.createCell(5);
      	        amountCell.setCellValue(((ItemWiseSaleHistoryDTO) invoice_history_dto).getAmount().doubleValue());
      	        amountCell.setCellStyle(ageCellStyle);
      	        
      	        row.createCell(6).setCellValue(((ItemWiseSaleHistoryDTO) invoice_history_dto).getSaleDate());
      	        row.createCell(7).setCellValue(((ItemWiseSaleHistoryDTO) invoice_history_dto).getInvoiceName());
      	        
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
