package com.spring.ledzer.report.strategy;

import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public abstract class  ReportGenerateStrategyTemplateClass  implements ReportGenerateStrategy{

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

}
