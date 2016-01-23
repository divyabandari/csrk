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

import com.cisco.excel.dto.NotCoveredDevicesDTO;

/**
 * See org.xml.sax.helpers.DefaultHandler javadocs
 */
public class IRNotCoveredSheetHandler extends DefaultHandler {

//	protected static final Logger logger = LoggerFactory.getLogger(IRNotCoveredSheetHandler.class);

	private SharedStringsTable sst;
	private String lastContents;
	private boolean nextIsString;
	private boolean processCell;
	private int currentRowNumber;
	private String currentColumName;

	List<NotCoveredDevicesDTO> notCoveredDevicesDTOList = new ArrayList<NotCoveredDevicesDTO>();
	private NotCoveredDevicesDTO aNotCoveredDevicesDTO;

	public List<NotCoveredDevicesDTO> getNotCoveredDevicesDTOList() {
		return notCoveredDevicesDTOList;
	}

	public void setNotCoveredDevicesDTOList(
			List<NotCoveredDevicesDTO> notCoveredDevicesDTOList) {
		this.notCoveredDevicesDTOList = notCoveredDevicesDTOList;
	}

	public IRNotCoveredSheetHandler(SharedStringsTable sst) {
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
				matcher = Pattern.compile(ExcelConstants.NOT_COVERED_CHASSIS_CCM_PHONE_SHEET_DATA_COLUMN_REGEX)
						.matcher(attributes.getValue("r"));
				matcher.find();
				currentColumName = matcher.group();
//				logger.debug("THE COLUMN NAME IS: " + currentColumName
//						+ " ROW NUMBER IS: " + currentRowNumber);
				if (currentRowNumber >= ExcelConstants.NOT_COVERED_CHASSIS_CCM_PHONE_SHEET_DATA_START_ROW_NUMBER
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
//				logger.debug("NO MATCH FOUND:EMPTY CELL VALUE: "
//						+ attributes.getValue("r"));
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

			// v => contents of a cell
			// Output after we've seen the string contents
			if (name.equals("v") || name.equals("t")) {
//				logger.debug(lastContents);

				if (currentColumName.compareToIgnoreCase("B") == 0) {
					// first column create a new DTO
					aNotCoveredDevicesDTO = new NotCoveredDevicesDTO();
					aNotCoveredDevicesDTO.setHostname(lastContents);
				} else if (currentColumName.compareToIgnoreCase("C") == 0) {
					aNotCoveredDevicesDTO.setIpaddress(lastContents);
				} else if (currentColumName.compareToIgnoreCase("E") == 0) {
					aNotCoveredDevicesDTO.setSerialnum(lastContents);
				} else if (currentColumName.compareToIgnoreCase("F") == 0) {
					aNotCoveredDevicesDTO.setProductid(lastContents);
				} else if (currentColumName.compareToIgnoreCase("H") == 0) {
					aNotCoveredDevicesDTO.setEquipmenttype(lastContents);
				} else if (currentColumName.compareToIgnoreCase("J") == 0) {
					aNotCoveredDevicesDTO.setInstallsiteid(lastContents);
				} else if (currentColumName.compareToIgnoreCase("K") == 0) {
					aNotCoveredDevicesDTO.setInstallsitecustomer(lastContents);
				} else if (currentColumName.compareToIgnoreCase("R") == 0) {
					aNotCoveredDevicesDTO.setInstancenumber(lastContents);
					// last column update now add DTO to the list
					if (!aNotCoveredDevicesDTO.getSerialnum().equals(""))
						notCoveredDevicesDTOList.add(aNotCoveredDevicesDTO);
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
