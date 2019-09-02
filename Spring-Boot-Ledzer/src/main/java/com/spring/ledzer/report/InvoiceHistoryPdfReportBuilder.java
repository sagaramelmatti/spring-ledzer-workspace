package com.spring.ledzer.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import org.springframework.beans.factory.annotation.Autowired;
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
import com.spring.ledzer.model.dto.ItemWiseSaleHistoryDTO;

@Component
public class InvoiceHistoryPdfReportBuilder implements ReportBuilder {
	
	@Autowired
	private Report report;
	
	Document document = new Document();
	PdfPTable table = new PdfPTable(9);
	
	Workbook workbook = new XSSFWorkbook();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
	
	@Override
	public void buildHeader(String[] headerarray) {
		
		if(report.getType() == "pdf")
		{
			try {
				// Add Text to PDF file ->
				Font font = FontFactory.getFont(FontFactory.COURIER, 13, BaseColor.BLACK);
				Paragraph para = new Paragraph( "Invoice History", font);
				para.setAlignment(Element.ALIGN_CENTER);
				document.add(para);
				document.add(Chunk.NEWLINE);
	        	
	        	
	        	// Add PDF Table Header ->
				Stream.of(headerarray)
				    .forEach(headerTitle -> {
				          PdfPCell header = new PdfPCell();
				          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				          header.setHorizontalAlignment(Element.ALIGN_CENTER);
				          header.setBorderWidth(2);
				          header.setPhrase(new Phrase(headerTitle, headFont));
				          table.addCell(header);
				    });
			report.setHeader(headerarray);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(report.getType() == "excel")
		{
			
			      CreationHelper createHelper = workbook.getCreationHelper();
			      Sheet sheet = workbook.createSheet("InvoiceHistory");
			      Font headerFont = (Font) workbook.createFont();
			      ((org.apache.poi.ss.usermodel.Font) headerFont).setBold(true);
			      headerFont.setColor(IndexedColors.BLUE.getIndex(), 0, 0);
			      CellStyle headerCellStyle = workbook.createCellStyle();
			      headerCellStyle.setFont((org.apache.poi.ss.usermodel.Font) headerFont);
			      // Row for Header
			      Row headerRow = sheet.createRow(0);
			      
			   // Header
			      for (int col = 0; col < headerarray.length; col++) {
			        Cell cell = headerRow.createCell(col);
			        cell.setCellValue(headerarray[col]);
			        cell.setCellStyle(headerCellStyle);
			      }
			   
			      // CellStyle for Age
			      CellStyle ageCellStyle = workbook.createCellStyle();
			      ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
		}
		
	}

	@Override
	public ByteArrayInputStream buildContent(List<? extends Object> list) {
		
		if(report.getType() == "pdf")
		{
			try
			{
				for (Object item_wise_sale_history_obj : list) {
		        	PdfPCell idCell = new PdfPCell(new Phrase(((ItemWiseSaleHistoryDTO) item_wise_sale_history_obj).getId().toString()));
		        	idCell.setPaddingLeft(4);
		        	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            table.addCell(idCell);
	
		            PdfPCell item_name = new PdfPCell(new Phrase(((ItemWiseSaleHistoryDTO) item_wise_sale_history_obj).getItemName()));
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
		            document.add(table);
		            
		        }
			}
			catch(Exception e)
			{
				
			}
		}	
        document.close();
		//report.setContent(list);
        return new ByteArrayInputStream(out.toByteArray());
		
	}

	@Override
	public void bulidFooter(String footer) {
		report.setFooter(footer);
		
	}

	@Override
	public void buildType(String type) {
		report.setType(type);
		
	}

	@Override
	public Report getReport() {
		return this.report;
	}

	public InvoiceHistoryPdfReportBuilder(Report report) {
		this.report = report;
	}

	public InvoiceHistoryPdfReportBuilder() {
		// TODO Auto-generated constructor stub
	}

	

}
