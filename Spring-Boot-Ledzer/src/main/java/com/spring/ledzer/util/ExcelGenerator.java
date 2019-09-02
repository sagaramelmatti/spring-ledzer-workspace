package com.spring.ledzer.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spring.ledzer.model.dto.InvoiceHistoryDTO;
import com.spring.ledzer.model.dto.ItemWiseSaleHistoryDTO;
 
 
public class ExcelGenerator {
  
  public static ByteArrayInputStream customersToExcel(List<InvoiceHistoryDTO> invoice_history_dto_list) throws IOException {
    String[] COLUMNs = {"Id", "Invoice Name", "Date", "Amount"};
    try(
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
    )
    {
      CreationHelper createHelper = workbook.getCreationHelper();
   
      Sheet sheet = workbook.createSheet("InvoiceHistory");
   
      Font headerFont = workbook.createFont();
      headerFont.setBold(true);
      headerFont.setColor(IndexedColors.BLUE.getIndex());
   
      CellStyle headerCellStyle = workbook.createCellStyle();
      headerCellStyle.setFont(headerFont);
   
      // Row for Header
      Row headerRow = sheet.createRow(0);
   
      // Header
      for (int col = 0; col < COLUMNs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(COLUMNs[col]);
        cell.setCellStyle(headerCellStyle);
      }
   
      // CellStyle for Age
      CellStyle ageCellStyle = workbook.createCellStyle();
      ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
   
      int rowIdx = 1;
      for (InvoiceHistoryDTO invoice_history_dto : invoice_history_dto_list) {
        Row row = sheet.createRow(rowIdx++);
   
        row.createCell(0).setCellValue(invoice_history_dto.getId());
        row.createCell(1).setCellValue(invoice_history_dto.getName());
        row.createCell(2).setCellValue(invoice_history_dto.getInvoiceDate());
   
        Cell ageCell = row.createCell(3);
        ageCell.setCellValue(invoice_history_dto.getInvoiceAmt().doubleValue());
        ageCell.setCellStyle(ageCellStyle);
      }
   
      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    }
  }
  
  public static ByteArrayInputStream item_wise_sale_history_Excel(List<ItemWiseSaleHistoryDTO> itemWiseSaleHistoryDTO_list) throws IOException {
	    String[] COLUMNs = {"Id", "Item Name", "UOM", "Rate","Quantity", "Total Amount", "Sale Date", "Invoice Name"};
	    try(
	        Workbook workbook = new XSSFWorkbook();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ){
	      CreationHelper createHelper = workbook.getCreationHelper();
	   
	      Sheet sheet = workbook.createSheet("InvoiceHistory");
	   
	      Font headerFont = workbook.createFont();
	      headerFont.setBold(true);
	      headerFont.setColor(IndexedColors.BLUE.getIndex());
	   
	      CellStyle headerCellStyle = workbook.createCellStyle();
	      headerCellStyle.setFont(headerFont);
	   
	      // Row for Header
	      Row headerRow = sheet.createRow(0);
	   
	      // Header
	      for (int col = 0; col < COLUMNs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(COLUMNs[col]);
	        cell.setCellStyle(headerCellStyle);
	      }
	   
	      // CellStyle for Age
	      CellStyle ageCellStyle = workbook.createCellStyle();
	      ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
	   
	      int rowIdx = 1;
	      for (ItemWiseSaleHistoryDTO invoice_history_dto : itemWiseSaleHistoryDTO_list) {
	        Row row = sheet.createRow(rowIdx++);
	   
	        row.createCell(0).setCellValue(invoice_history_dto.getId());
	        row.createCell(1).setCellValue(invoice_history_dto.getItemName());
	        row.createCell(2).setCellValue(invoice_history_dto.getUomName());
	   
	        Cell rateCell = row.createCell(3);
	        rateCell.setCellValue(invoice_history_dto.getRate().doubleValue());
	        rateCell.setCellStyle(ageCellStyle);
	        
	        Cell quantityCell = row.createCell(4);
	        quantityCell.setCellValue(invoice_history_dto.getQuantity().doubleValue());
	        quantityCell.setCellStyle(ageCellStyle);
	        
	        Cell amountCell = row.createCell(5);
	        amountCell.setCellValue(invoice_history_dto.getAmount().doubleValue());
	        amountCell.setCellStyle(ageCellStyle);
	        
	        row.createCell(6).setCellValue(invoice_history_dto.getSaleDate());
	        row.createCell(7).setCellValue(invoice_history_dto.getInvoiceName());
	        
	      }
	   
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    }
	  }
}
