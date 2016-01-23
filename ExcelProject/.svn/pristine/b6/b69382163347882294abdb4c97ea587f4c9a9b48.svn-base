package com.cisco.excel.util;

import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/** 
 * See org.xml.sax.helpers.DefaultHandler javadocs 
 */
public class SheetHandler extends DefaultHandler {
	
	protected static final Logger logger = LoggerFactory.getLogger(SheetHandler.class);
	
	private SharedStringsTable sst;
	private String lastContents;
	private boolean nextIsString;
	
	public SheetHandler(SharedStringsTable sst) {
		this.sst = sst;
	}
	
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		// c => cell
		if(name.equals("c")) {
			// Print the cell reference
			logger.debug(attributes.getValue("r") + " - " + "CONTENT IS");
			// Figure out if the value is an index in the SST
			String cellType = attributes.getValue("t");
			if(cellType != null && cellType.equals("s")) {
				nextIsString = true;
			} else {
				nextIsString = false;
			}
		}
		// Clear contents cache
		lastContents = "";
	}
	
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// Process the last contents as required.
		// Do now, as characters() may be called more than once
		if(nextIsString) {
			int idx = Integer.parseInt(lastContents);
			lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
        nextIsString = false;
		}

		// v => contents of a cell
		// Output after we've seen the string contents
		if(name.equals("v")) {
			logger.debug(lastContents);
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		lastContents += new String(ch, start, length);
	}
	
	/**
	 * Get a parser for XML inside an XLSX sheet.
	 * @param sst
	 * @return
	 * @throws SAXException
	 */
	public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
		XMLReader parser =
			XMLReaderFactory.createXMLReader(ExcelConstants.SAX_PARSER);
		ContentHandler handler = new SheetHandler(sst);
		parser.setContentHandler(handler);
		return parser;
	}
}
