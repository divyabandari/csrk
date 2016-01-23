package com.cisco.excel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.cisco.excel.dto.CoveredDevicesDTO;

/**
 * See org.xml.sax.helpers.DefaultHandler javadocs
 */
public class SNTCCoveredItemsSheetHandler extends DefaultHandler {
	
//	protected static final Logger logger = LoggerFactory.getLogger(SNTCCoveredItemsSheetHandler.class);

	private SharedStringsTable sst;
	private String lastContents;
	private boolean nextIsString;
	private boolean processCell;
	private int currentRowNumber;
	private String currentColumName;

	private List<CoveredDevicesDTO> coveredDevicesDTOList = new ArrayList<CoveredDevicesDTO>();
	private CoveredDevicesDTO aCoveredDevicesDTO;

	public List<CoveredDevicesDTO> getCoveredDevicesDTOList() {
		return coveredDevicesDTOList;
	}

	public void setCoveredDevicesDTOList(
			List<CoveredDevicesDTO> coveredDevicesDTOList) {
		this.coveredDevicesDTOList = coveredDevicesDTOList;
	}

	public SNTCCoveredItemsSheetHandler(SharedStringsTable sst) {
		this.sst = sst;
	}

	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		// reset the flag
		// c => cell
		if (name.equals("c")) {
			// Figure out if the Cell is a data cell for this sheet.
			try {
				Matcher matcher = Pattern.compile(ExcelConstants.FIND_NUMBER_IN_STRING_REGEX).matcher(attributes.getValue("r"));
				matcher.find();
				currentRowNumber = Integer.parseInt(matcher.group());
				matcher = Pattern.compile(ExcelConstants.COVERED_SHEET_DATA_COLUMN_REGEX)
						.matcher(attributes.getValue("r"));
				matcher.find();
				currentColumName = matcher.group();
//				logger.debug("THE COLUMN NAME IS: " + currentColumName	+ " ROW NUMBER IS: " + currentRowNumber);
				if (currentRowNumber >= ExcelConstants.COVERED_SHEET_DATA_START_ROW_NUMBER
						&& (!currentColumName.isEmpty())) {
					processCell = true;
					// Figure out if the value is an index in the SST
					String cellType = attributes.getValue("t");
					if (cellType != null && cellType.equals("s")) {
						nextIsString = true;
					} else {
						nextIsString = false;
					}
				}
			} catch (IllegalStateException exp) {
//				logger.debug("NO MATCH FOUND:EMPTY CELL VALUE: "+ attributes.getValue("r"));
				processCell=false;
			}
			// Clear contents cache
			lastContents = "";
		}
	}

	public void endElement(String uri, String localName, String name)
			throws SAXException {

		// Process the last contents as required only if it is determined to be
		// data cell
//		logger.debug("INSIDE THE END ELEMENT");
//		logger.debug("contents: " + lastContents);
		if (processCell) {
			// Do now, as characters() may be called more than once
			if (nextIsString) {
				int idx = Integer.parseInt(lastContents);
				lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
						.toString();
				nextIsString = false;
			}

			// v => contents of a cell and t implies text in an inline string
			// Output after we've seen the string contents
			if (name.equals("v") || name.equals("t")) { 
//				logger.debug(lastContents);

				if (currentColumName.compareToIgnoreCase("B") == 0) {
					// first column create a new DTO
					aCoveredDevicesDTO = new CoveredDevicesDTO();
					aCoveredDevicesDTO.setHostname(lastContents);
				} else if (currentColumName.compareToIgnoreCase("C") == 0) {
					aCoveredDevicesDTO.setIpaddress(lastContents);
				} else if (currentColumName.compareToIgnoreCase("E") == 0) {
					aCoveredDevicesDTO.setSerialnum(lastContents);
				} else if (currentColumName.compareToIgnoreCase("F") == 0) {
					aCoveredDevicesDTO.setProductid(lastContents);
				} else if (currentColumName.compareToIgnoreCase("H") == 0) {
					aCoveredDevicesDTO.setEquipmenttype(lastContents);
				} else if (currentColumName.compareToIgnoreCase("J") == 0) {
					aCoveredDevicesDTO.setContractnumber(lastContents);
				} else if (currentColumName.compareToIgnoreCase("L") == 0) {
					aCoveredDevicesDTO.setCoveragestatus(lastContents);
				} else if (currentColumName.compareToIgnoreCase("M") == 0) {
					aCoveredDevicesDTO.setServicelevel(lastContents);
				} else if (currentColumName.compareToIgnoreCase("N") == 0) {
					aCoveredDevicesDTO.setInstallsiteid(lastContents);
				} else if (currentColumName.compareToIgnoreCase("O") == 0) {
					aCoveredDevicesDTO.setInstallsitecustomer(lastContents);
					// last column update now add DTO to the list
					if (!aCoveredDevicesDTO.getSerialnum().equals(""))
						coveredDevicesDTOList.add(aCoveredDevicesDTO);
				}

			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		lastContents += new String(ch, start, length);
	}

	/**
	 * Get a parser for XML inside an XLSX sheet.
	 * 
	 * @param sst
	 * @return
	 * @throws SAXException
	 */
	public XMLReader fetchSheetParser(SharedStringsTable sst)
			throws SAXException {
		XMLReader parser = XMLReaderFactory
				.createXMLReader(ExcelConstants.SAX_PARSER);
		parser.setContentHandler(this);
		return parser;
	}
}
