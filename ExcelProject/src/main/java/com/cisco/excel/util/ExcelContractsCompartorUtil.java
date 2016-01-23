package com.cisco.excel.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.excel.dto.AllContractDTO;
import com.cisco.excel.dto.CoveredDevicesDTO;
import com.cisco.excel.dto.NotCoveredDevicesDTO;

public class ExcelContractsCompartorUtil {

	/**
	 * Reads the CMR report for the all contracts sheet
	 * 
	 * @param fileName
	 * @param sheetName
	 * @param appName
	 * @return
	 */

	protected static final Logger logger = LoggerFactory
			.getLogger(ExcelContractsCompartorUtil.class);

	public static List<AllContractDTO> readExelFile(XSSFWorkbook irWorkbook,
			String sheetName, String appName) {

		List<AllContractDTO> contractDTOList = new ArrayList<AllContractDTO>();
		logger.debug("INSIDE THE READ FILE");
		try {

			/*
			 * FileInputStream irFile = new FileInputStream(new File(fileName));
			 * XSSFWorkbook irWorkbook = new XSSFWorkbook(irFile);
			 */

			XSSFSheet irSheet = irWorkbook.getSheet(sheetName);

			Iterator<Row> irRowIterator = irSheet.iterator();
			while (irRowIterator.hasNext()) {
				Row row = irRowIterator.next();

				// System.out.println("Row Num "+row.getRowNum());
				if (row.getRowNum() >= 6) {
					AllContractDTO contractDTO = new AllContractDTO();
					// For each row, iterate through each columns

					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();

						if (appName.equalsIgnoreCase("SNTC")) {

							if (cell.getColumnIndex() == 0) {
								contractDTO.setContractNumber(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 1) {
								contractDTO.setContractServiceLevel(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 2) {
								contractDTO.setContractStatus(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 3) {
								contractDTO.setContractBillTocustomer(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 4) {
								contractDTO.setContractBillToCountry(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 5) {
								contractDTO.setContractStartDate(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 6) {
								contractDTO.setContractEndDate(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 7) {
								contractDTO.setCoveredCount(Util
										.getCellValue(cell));

							}
							// IR sheet has one extra column for service level
							// description which is not there in the SNTC CMR
							// report..
						} else if (appName.equalsIgnoreCase("IR")) {

							if (cell.getColumnIndex() == 0) {
								contractDTO.setContractNumber(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 2) {
								contractDTO.setContractServiceLevel(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 3) {
								contractDTO.setContractStatus(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 4) {
								contractDTO.setContractBillTocustomer(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 5) {
								contractDTO.setContractBillToCountry(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 6) {
								contractDTO.setContractStartDate(Util
										.convertDate(Util.getCellValue(cell)));

							} else if (cell.getColumnIndex() == 7) {
								contractDTO.setContractEndDate(Util
										.convertDate(Util.getCellValue(cell)));

							} else if (cell.getColumnIndex() == 8) {
								contractDTO.setCoveredCount(Util
										.getCellValue(cell));

							}

						}

					}

					if (!contractDTO.getContractNumber().equals(""))
						contractDTOList.add(contractDTO);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return contractDTOList;

	}

	/**
	 * Reads the CMR report for the covered sheet
	 * 
	 * @param fileName
	 * @param sheetName
	 * @param appName
	 * @return
	 */
	public static List<CoveredDevicesDTO> readCoveredDevices(
			XSSFWorkbook irWorkbook, String sheetName, String appName) {

		List<CoveredDevicesDTO> coveredDevicesDTOList = new ArrayList<CoveredDevicesDTO>();

		try {
			System.out.println("Sheet name  : " + sheetName);
			XSSFSheet irSheet = irWorkbook.getSheet(sheetName);
			Iterator<Row> irRowIterator = irSheet.iterator();
			while (irRowIterator.hasNext()) {
				Row row = irRowIterator.next();

				// System.out.println("Row Num "+row.getRowNum());
				if (row.getRowNum() >= 6) {
					CoveredDevicesDTO coveredDevicesDTO = new CoveredDevicesDTO();
					// For each row, iterate through each columns

					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();

						if (cell.getColumnIndex() == 1) {
							coveredDevicesDTO.setHostname(Util
									.getCellValue(cell));

						} else if (cell.getColumnIndex() == 2) {
							coveredDevicesDTO.setIpaddress(Util
									.getCellValue(cell));

						} else if (cell.getColumnIndex() == 4) {
							coveredDevicesDTO.setSerialnum(Util
									.getCellValue(cell));

						} else if (cell.getColumnIndex() == 5) {
							coveredDevicesDTO.setProductid(Util
									.getCellValue(cell));

						} else if (cell.getColumnIndex() == 7) {
							coveredDevicesDTO.setEquipmenttype(Util
									.getCellValue(cell));

						} else if (cell.getColumnIndex() == 9) {
							coveredDevicesDTO.setContractnumber(Util
									.getCellValue(cell));

						} else if (cell.getColumnIndex() == 11) {
							coveredDevicesDTO.setCoveragestatus(Util
									.getCellValue(cell));

						} else if (cell.getColumnIndex() == 12) {
							coveredDevicesDTO.setServicelevel(Util
									.getCellValue(cell));

						} else if (cell.getColumnIndex() == 13) {
							coveredDevicesDTO.setInstallsiteid(Util
									.getCellValue(cell));

						} else if (cell.getColumnIndex() == 14) {
							coveredDevicesDTO.setInstallsitecustomer(Util
									.getCellValue(cell));

						}
						// IR sheet has one extra column for service level
						// description which is not there in the SNTC CMR
						// report..

					}

					if (!coveredDevicesDTO.getSerialnum().equals(""))
						coveredDevicesDTOList.add(coveredDevicesDTO);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return coveredDevicesDTOList;

	}

	/**
	 * Reads the CMR report for the covered sheet
	 * 
	 * @param fileName
	 * @param sheetName
	 * @param appName
	 * @return
	 */
	public static List<CoveredDevicesDTO> readIRCoveredDevices(
			XSSFWorkbook irWorkbook) {

		List<CoveredDevicesDTO> coveredDevicesDTOList = new ArrayList<CoveredDevicesDTO>();

		try {

			List<String> sheetList = new ArrayList<String>();
			sheetList.add(ExcelConstants.COVERED_ITEMS_SHEET);
			sheetList.add(ExcelConstants.CHASSIS_COVERED_CARDS_SHEET);

			for (String sheetName : sheetList) {
				System.out.println("Sheet name  : " + sheetName);
				XSSFSheet irSheet = irWorkbook.getSheet(sheetName);
				Iterator<Row> irRowIterator = irSheet.iterator();
				while (irRowIterator.hasNext()) {
					Row row = irRowIterator.next();

					// System.out.println("Row Num "+row.getRowNum());
					if (row.getRowNum() >= 6) {
						CoveredDevicesDTO coveredDevicesDTO = new CoveredDevicesDTO();
						// For each row, iterate through each columns

						Iterator<Cell> cellIterator = row.cellIterator();
						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();

							if (cell.getColumnIndex() == 1) {
								coveredDevicesDTO.setHostname(Util.getCellValue(cell));

							} else if (cell.getColumnIndex() == 2) {
								coveredDevicesDTO.setIpaddress(Util.getCellValue(cell));

							} else if (cell.getColumnIndex() == 4) {
								coveredDevicesDTO.setSerialnum(Util.getCellValue(cell));

							} else if (cell.getColumnIndex() == 5) {
								coveredDevicesDTO.setProductid(Util.getCellValue(cell));

							} else if (cell.getColumnIndex() == 7) {
								coveredDevicesDTO.setEquipmenttype(Util.getCellValue(cell));

							}

							if (sheetName
									.equals(ExcelConstants.COVERED_ITEMS_SHEET)) {
								if (cell.getColumnIndex() == 9) {
									coveredDevicesDTO.setContractnumber(Util
											.getCellValue(cell));

								} else if (cell.getColumnIndex() == 12) {
									coveredDevicesDTO.setCoveragestatus(Util
											.getCellValue(cell));

								} else if (cell.getColumnIndex() == 13) {
									coveredDevicesDTO.setServicelevel(Util
											.getCellValue(cell));

								} else if (cell.getColumnIndex() == 14) {
									coveredDevicesDTO.setInstallsiteid(Util
											.getCellValue(cell));

								} else if (cell.getColumnIndex() == 15) {
									coveredDevicesDTO
											.setInstallsitecustomer(Util
													.getCellValue(cell));

								}
							} else if (sheetName
									.equals(ExcelConstants.CHASSIS_COVERED_CARDS_SHEET)) {
								if (cell.getColumnIndex() == 8) {
									coveredDevicesDTO.setContractnumber(Util
											.getCellValue(cell));

								} else if (cell.getColumnIndex() == 13) {
									coveredDevicesDTO.setCoveragestatus(Util
											.getCellValue(cell));

								}
								// adding dummy vlaues
								coveredDevicesDTO
										.setServicelevel("No data in Chassis Covered cards");
								coveredDevicesDTO
										.setInstallsiteid("No data in Chassis Covered cards");
								coveredDevicesDTO
										.setInstallsitecustomer("No data in Chassis Covered cards");
							}

						}

						if (!coveredDevicesDTO.getSerialnum().equals(""))
							coveredDevicesDTOList.add(coveredDevicesDTO);

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return coveredDevicesDTOList;

	}

	/**
	 * Reads the CMR report for the Not Covered sheet
	 * 
	 * @param fileName
	 * @param sheetName
	 * @param appName
	 * @return
	 */
	public static List<NotCoveredDevicesDTO> readNotCoveredDevices(
			XSSFWorkbook irWorkbook, String sheetName, String appName) {

		List<NotCoveredDevicesDTO> notCoveredDevicesDTOList = new ArrayList<NotCoveredDevicesDTO>();

		try {

			XSSFSheet irSheet = irWorkbook.getSheet(sheetName);
			Iterator<Row> irRowIterator = irSheet.iterator();
			while (irRowIterator.hasNext()) {
				Row row = irRowIterator.next();

				// System.out.println("Row Num "+row.getRowNum());
				if (row.getRowNum() >= 6) {
					NotCoveredDevicesDTO notCoveredDevicesDTO = new NotCoveredDevicesDTO();
					// For each row, iterate through each columns

					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();

						if (appName.equalsIgnoreCase("SNTC")) {

							if (cell.getColumnIndex() == 1) {
								notCoveredDevicesDTO.setHostname(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 2) {
								notCoveredDevicesDTO.setIpaddress(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 4) {
								notCoveredDevicesDTO.setSerialnum(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 5) {
								notCoveredDevicesDTO.setProductid(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 7) {
								notCoveredDevicesDTO.setEquipmenttype(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 9) {
								notCoveredDevicesDTO.setInstallsiteid(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 10) {
								notCoveredDevicesDTO
										.setInstallsitecustomer(Util
												.getCellValue(cell));

							} else if (cell.getColumnIndex() == 17) {
								notCoveredDevicesDTO.setInstancenumber(Util
										.getCellValue(cell));

							}
							// IR sheet has one extra column for service level
							// description which is not there in the SNTC CMR
							// report..
						} else if (appName.equalsIgnoreCase("IR")) {

							if (cell.getColumnIndex() == 1) {
								notCoveredDevicesDTO.setHostname(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 2) {
								notCoveredDevicesDTO.setIpaddress(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 4) {
								notCoveredDevicesDTO.setSerialnum(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 5) {
								notCoveredDevicesDTO.setProductid(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 7) {
								notCoveredDevicesDTO.setEquipmenttype(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 9) {
								notCoveredDevicesDTO.setInstallsiteid(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 10) {
								notCoveredDevicesDTO
										.setInstallsitecustomer(Util
												.getCellValue(cell));

							} else if (cell.getColumnIndex() == 17) {
								notCoveredDevicesDTO.setInstancenumber(Util
										.getCellValue(cell));

							}
							// IR sheet has one extra column for service level
							// description which is not there in the SNTC CMR
							// report..
						}

					}

					if (!notCoveredDevicesDTO.getSerialnum().equals(""))
						notCoveredDevicesDTOList.add(notCoveredDevicesDTO);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return notCoveredDevicesDTOList;

	}

	/**
	 * Reads the CMR report for the IR Not Covered sheet
	 * 
	 * @param fileName
	 * @param sheetName
	 * @param appName
	 * @return
	 */
	public static List<NotCoveredDevicesDTO> readIRNotCoveredDevices(
			XSSFWorkbook irWorkbook) {

		List<NotCoveredDevicesDTO> notCoveredDevicesDTOList = new ArrayList<NotCoveredDevicesDTO>();

		try {
			List<String> sheetList = new ArrayList<String>();
			sheetList.add(ExcelConstants.NOT_COVERED_CHASSIS_CCM_PHONE);
			sheetList.add(ExcelConstants.NOT_COVERED_CARDS_SHEET);

			for (String sheetName : sheetList) {
				System.out.println("Sheet name  : " + sheetName);
				XSSFSheet irSheet = irWorkbook.getSheet(sheetName);
				Iterator<Row> irRowIterator = irSheet.iterator();
				while (irRowIterator.hasNext()) {
					Row row = irRowIterator.next();

					// System.out.println("Row Num "+row.getRowNum());
					if (row.getRowNum() >= 6) {
						NotCoveredDevicesDTO notCoveredDevicesDTO = new NotCoveredDevicesDTO();
						// For each row, iterate through each columns

						Iterator<Cell> cellIterator = row.cellIterator();
						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();

							if (cell.getColumnIndex() == 1) {
								notCoveredDevicesDTO.setHostname(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 2) {
								notCoveredDevicesDTO.setIpaddress(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 4) {
								notCoveredDevicesDTO.setSerialnum(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 5) {
								notCoveredDevicesDTO.setProductid(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 7) {
								notCoveredDevicesDTO.setEquipmenttype(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 9) {
								notCoveredDevicesDTO.setInstallsiteid(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 10) {
								notCoveredDevicesDTO
										.setInstallsitecustomer(Util
												.getCellValue(cell));

							} else if (cell.getColumnIndex() == 17) {
								notCoveredDevicesDTO.setInstancenumber(Util
										.getCellValue(cell));

							}

						}

						if (!notCoveredDevicesDTO.getSerialnum().equals(""))
							notCoveredDevicesDTOList.add(notCoveredDevicesDTO);

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return notCoveredDevicesDTOList;

	}

	/**
	 * This method used to create the workbook sheet using the given objectList
	 * 
	 * @param workbook
	 * @param sheet
	 * @param objectList
	 */
	public static void createSheet(HSSFWorkbook workbook, HSSFSheet sheet,
			List<Object> objectList) {

		CellStyle style = workbook.createCellStyle();
		int rownum = 1;
		Row row = sheet.createRow(rownum++);

		row.createCell(1).setCellValue("Common Contract Number");
		// Util.applyHeaderColor(cell);

		row.createCell(2).setCellValue("IR Service Level");

		row.createCell(3).setCellValue("SNTC Service Level");

		row.createCell(4).setCellValue("Service Level Match");

		row.createCell(5).setCellValue("IR Contract Status");

		row.createCell(6).setCellValue("SNTC Contract Status");

		row.createCell(7).setCellValue("Contract Status Match");

		row.createCell(8).setCellValue("IR Bill To Customer");

		row.createCell(9).setCellValue("SNTC Bill To Customer");

		row.createCell(10).setCellValue("Bill To Customer Match");

		row.createCell(11).setCellValue("IR Bill To Country");

		row.createCell(12).setCellValue("SNTC Bill To Country");

		row.createCell(13).setCellValue("Bill To Country Match");

		row.createCell(14).setCellValue("IR Contract Start Date");

		row.createCell(15).setCellValue("SNTC Contract Start Date");

		row.createCell(16).setCellValue("Contract Start Date Match");

		row.createCell(17).setCellValue("IR Contract End Date");

		row.createCell(18).setCellValue("SNTC Contract End Date");

		row.createCell(19).setCellValue("Contract End Date Match");

		row.createCell(20).setCellValue("IR Covered Device Count");

		row.createCell(21).setCellValue("SNTC Covered Device Count");

		row.createCell(22).setCellValue("Covered Device Count Diff");

		System.out.println(" Object list size from Database "
				+ objectList.size());
		for (Object obj : objectList) {
			row = sheet.createRow(rownum++);

			Object[] result = (Object[]) obj;

			if (result != null) {

				row.createCell(1).setCellValue(result[0].toString());

				row.createCell(2).setCellValue(result[1].toString());
				row.createCell(3).setCellValue(result[2].toString());
				row.createCell(4).setCellValue(
						Util.getStringDiff(result[1].toString(),
								result[2].toString()));

				row.createCell(5).setCellValue(result[3].toString());
				row.createCell(6).setCellValue(result[4].toString());
				row.createCell(7).setCellValue(
						Util.getStringDiff(result[3].toString(),
								result[4].toString()));

				row.createCell(8).setCellValue(result[5].toString());
				row.createCell(9).setCellValue(result[6].toString());
				row.createCell(10).setCellValue(
						Util.getStringDiff(result[5].toString(),
								result[6].toString()));

				row.createCell(11).setCellValue(result[7].toString());
				row.createCell(12).setCellValue(result[8].toString());
				row.createCell(13).setCellValue(
						Util.getStringDiff(result[7].toString(),
								result[8].toString()));

				row.createCell(14).setCellValue(result[9].toString());
				row.createCell(15).setCellValue(result[10].toString());
				row.createCell(16).setCellValue(
						Util.getStringDiff(result[9].toString(),
								result[10].toString()));

				row.createCell(17).setCellValue(result[11].toString());
				row.createCell(18).setCellValue(result[12].toString());
				row.createCell(19).setCellValue(
						Util.getStringDiff(result[11].toString(),
								result[12].toString()));

				row.createCell(20).setCellValue(result[13].toString());
				row.createCell(21).setCellValue(result[14].toString());
				row.createCell(22).setCellValue(
						Util.getCoveredDeviceCountDiff(result[13].toString(),
								result[14].toString()));

				/*
				 * int resultSize = result.length; for( int i = 0 ;
				 * i<resultSize-1 ;i++ ){
				 * 
				 * if(i==0){ cell = row.createCell(i+1);
				 * cell.setCellValue(result[i].toString()); } else
				 * if(i==resultSize-1){
				 * 
				 * cell = row.createCell(20);
				 * cell.setCellValue(result[i].toString());
				 * 
				 * cell = row.createCell(21);
				 * cell.setCellValue(result[i+1].toString());
				 * 
				 * cell = row.createCell(22);
				 * cell.setCellValue(Util.getCoveredDeviceCountDiff
				 * (result[i].toString(), result[i+1].toString()));
				 * 
				 * }else { int j =1;
				 * 
				 * cell = row.createCell(i);
				 * cell.setCellValue(result[i].toString());
				 * 
				 * cell = row.createCell(i+j++);
				 * cell.setCellValue(result[i+1].toString());
				 * 
				 * cell = row.createCell(i+j++);
				 * cell.setCellValue(Util.getStringDiff(result[i].toString(),
				 * result[i+1].toString()));
				 * 
				 * 
				 * }
				 * 
				 * }
				 */

			}

		}

	}
	
	
	public static void createXSSSheet(SXSSFWorkbook workbook, Sheet sheet,	List<Object> objectList) {

		CellStyle style = workbook.createCellStyle();
		int rownum = 1;
		Row row = sheet.createRow(rownum++);

		row.createCell(1).setCellValue("Common Contract Number");
		// Util.applyHeaderColor(cell);

		row.createCell(2).setCellValue("IR Service Level");

		row.createCell(3).setCellValue("SNTC Service Level");

		row.createCell(4).setCellValue("Service Level Match");

		row.createCell(5).setCellValue("IR Contract Status");

		row.createCell(6).setCellValue("SNTC Contract Status");

		row.createCell(7).setCellValue("Contract Status Match");

		row.createCell(8).setCellValue("IR Bill To Customer");

		row.createCell(9).setCellValue("SNTC Bill To Customer");

		row.createCell(10).setCellValue("Bill To Customer Match");

		row.createCell(11).setCellValue("IR Bill To Country");

		row.createCell(12).setCellValue("SNTC Bill To Country");

		row.createCell(13).setCellValue("Bill To Country Match");

		row.createCell(14).setCellValue("IR Contract Start Date");

		row.createCell(15).setCellValue("SNTC Contract Start Date");

		row.createCell(16).setCellValue("Contract Start Date Match");

		row.createCell(17).setCellValue("IR Contract End Date");

		row.createCell(18).setCellValue("SNTC Contract End Date");

		row.createCell(19).setCellValue("Contract End Date Match");

		row.createCell(20).setCellValue("IR Covered Device Count");

		row.createCell(21).setCellValue("SNTC Covered Device Count");

		row.createCell(22).setCellValue("Covered Device Count Diff");

		System.out.println(" Object list size from Database "
				+ objectList.size());
		for (Object obj : objectList) {
			row = sheet.createRow(rownum++);

			Object[] result = (Object[]) obj;

			if (result != null) {

				row.createCell(1).setCellValue(result[0].toString());

				row.createCell(2).setCellValue(result[1].toString());
				row.createCell(3).setCellValue(result[2].toString());
				row.createCell(4).setCellValue(
						Util.getStringDiff(result[1].toString(),
								result[2].toString()));

				row.createCell(5).setCellValue(result[3].toString());
				row.createCell(6).setCellValue(result[4].toString());
				row.createCell(7).setCellValue(
						Util.getStringDiff(result[3].toString(),
								result[4].toString()));

				row.createCell(8).setCellValue(result[5].toString());
				row.createCell(9).setCellValue(result[6].toString());
				row.createCell(10).setCellValue(
						Util.getStringDiff(result[5].toString(),
								result[6].toString()));

				row.createCell(11).setCellValue(result[7].toString());
				row.createCell(12).setCellValue(result[8].toString());
				row.createCell(13).setCellValue(
						Util.getStringDiff(result[7].toString(),
								result[8].toString()));

				row.createCell(14).setCellValue(result[9].toString());
				row.createCell(15).setCellValue(result[10].toString());
				row.createCell(16).setCellValue(
						Util.getStringDiff(result[9].toString(),
								result[10].toString()));

				row.createCell(17).setCellValue(result[11].toString());
				row.createCell(18).setCellValue(result[12].toString());
				row.createCell(19).setCellValue(
						Util.getStringDiff(result[11].toString(),
								result[12].toString()));

				row.createCell(20).setCellValue(result[13].toString());
				row.createCell(21).setCellValue(result[14].toString());
				row.createCell(22).setCellValue(
						Util.getCoveredDeviceCountDiff(result[13].toString(),
								result[14].toString()));

				/*
				 * int resultSize = result.length; for( int i = 0 ;
				 * i<resultSize-1 ;i++ ){
				 * 
				 * if(i==0){ cell = row.createCell(i+1);
				 * cell.setCellValue(result[i].toString()); } else
				 * if(i==resultSize-1){
				 * 
				 * cell = row.createCell(20);
				 * cell.setCellValue(result[i].toString());
				 * 
				 * cell = row.createCell(21);
				 * cell.setCellValue(result[i+1].toString());
				 * 
				 * cell = row.createCell(22);
				 * cell.setCellValue(Util.getCoveredDeviceCountDiff
				 * (result[i].toString(), result[i+1].toString()));
				 * 
				 * }else { int j =1;
				 * 
				 * cell = row.createCell(i);
				 * cell.setCellValue(result[i].toString());
				 * 
				 * cell = row.createCell(i+j++);
				 * cell.setCellValue(result[i+1].toString());
				 * 
				 * cell = row.createCell(i+j++);
				 * cell.setCellValue(Util.getStringDiff(result[i].toString(),
				 * result[i+1].toString()));
				 * 
				 * 
				 * }
				 * 
				 * }
				 */

			}

		}

	}

	/**
	 * This method used to create the workbook sheet using the given objectList
	 * 
	 * @param workbook
	 * @param sheet
	 * @param objectList
	 */
	public static void createMatchSheetbyQueryListValues(HSSFWorkbook workbook,
			HSSFSheet sheet, List<Object> objectList, String queryValues) {

		System.out.println(" Sheet Name Delete : " + sheet.getSheetName());
		System.out.println(" query value : " + queryValues);
		CellStyle style = workbook.createCellStyle();
		int rownum = 1;
		Row row = sheet.createRow(rownum++);
		StringTokenizer ss = new StringTokenizer(queryValues, ",");
		int i = 0;
		while (ss.hasMoreTokens()) {
			i++;
			row.createCell(i).setCellValue(ss.nextToken());

		}
		System.out.println(" Object list size from Database "
				+ objectList.size());
		for (Object obj : objectList) {
			row = sheet.createRow(rownum++);

			Object[] result = (Object[]) obj;

			if (result != null) {

				row.createCell(1).setCellValue(result[0].toString());
				int k = 0;
				for (int j = 1; j < result.length; j = j + 2) {
					// System.out.println("J value is "+ j);
					row.createCell(j + 1 + k).setCellValue(
							Util.nullCheck(result[j]));
					row.createCell(j + 2 + k).setCellValue(
							Util.nullCheck(result[j + 1]));
					row.createCell(j + 3 + k).setCellValue(
							Util.getStringDiff(Util.nullCheck(result[j]),
									Util.nullCheck(result[j + 1])));
					k++;
				}

			}

		}

	}
	
	public static void createMatchXSSSheetbyQueryListValues(SXSSFWorkbook workbook,
			Sheet sheet, List<Object> objectList, String queryValues) {

		System.out.println(" Sheet Name Delete : " + sheet.getSheetName());
		System.out.println(" query value : " + queryValues);
		CellStyle style = workbook.createCellStyle();
		int rownum = 1;
		Row row = sheet.createRow(rownum++);
		StringTokenizer ss = new StringTokenizer(queryValues, ",");
		int i = 0;
		while (ss.hasMoreTokens()) {
			i++;
			row.createCell(i).setCellValue(ss.nextToken());

		}
		System.out.println(" Object list size from Database "
				+ objectList.size());
		for (Object obj : objectList) {
			row = sheet.createRow(rownum++);

			Object[] result = (Object[]) obj;

			if (result != null) {

				row.createCell(1).setCellValue(result[0].toString());
				int k = 0;
				for (int j = 1; j < result.length; j = j + 2) {
					// System.out.println("J value is "+ j);
					row.createCell(j + 1 + k).setCellValue(
							Util.nullCheck(result[j]));
					row.createCell(j + 2 + k).setCellValue(
							Util.nullCheck(result[j + 1]));
					row.createCell(j + 3 + k).setCellValue(
							Util.getStringDiff(Util.nullCheck(result[j]),
									Util.nullCheck(result[j + 1])));
					k++;
				}

			}

		}

	}

	/**
	 * This method used to create the workbook sheet for extra contracts in IR
	 * and SNTC
	 * 
	 * @param workbook
	 * @param sheet
	 * @param objectList
	 */
	public static void createExtraContractsSheet(HSSFWorkbook workbook,
			HSSFSheet sheet, List<Object> objectList) {

		CellStyle style = workbook.createCellStyle();
		int rownum = 1;
		Row row = sheet.createRow(rownum++);

		row.createCell(1).setCellValue("Contract Number");
		// Util.applyHeaderColor(cell);
		row.createCell(2).setCellValue("Service Level");
		row.createCell(3).setCellValue("Contract Status");
		row.createCell(4).setCellValue("Bill To Customer");
		row.createCell(5).setCellValue("Bill To Country");
		row.createCell(6).setCellValue("Contract Start Date");
		row.createCell(7).setCellValue("Contract End Date");
		row.createCell(8).setCellValue("Covered Device Count");

		System.out.println(" Object list size from Database "
				+ objectList.size());
		for (Object obj : objectList) {
			row = sheet.createRow(rownum++);

			Object[] result = (Object[]) obj;

			if (result != null) {

				row.createCell(1).setCellValue(result[0].toString());
				row.createCell(2).setCellValue(result[1].toString());
				row.createCell(3).setCellValue(result[2].toString());
				row.createCell(4).setCellValue(result[3].toString());
				row.createCell(5).setCellValue(result[4].toString());
				row.createCell(6).setCellValue(result[5].toString());
				row.createCell(7).setCellValue(result[6].toString());
				row.createCell(8).setCellValue(result[7].toString());

			}
		}

	}
	
	public static void createExtraContractsXSSSheet(SXSSFWorkbook workbook, Sheet sheet, List<Object> objectList) {

		CellStyle style = workbook.createCellStyle();
		int rownum = 1;
		Row row = sheet.createRow(rownum++);

		row.createCell(1).setCellValue("Contract Number");
		// Util.applyHeaderColor(cell);
		row.createCell(2).setCellValue("Service Level");
		row.createCell(3).setCellValue("Contract Status");
		row.createCell(4).setCellValue("Bill To Customer");
		row.createCell(5).setCellValue("Bill To Country");
		row.createCell(6).setCellValue("Contract Start Date");
		row.createCell(7).setCellValue("Contract End Date");
		row.createCell(8).setCellValue("Covered Device Count");

		System.out.println(" Object list size from Database "
				+ objectList.size());
		for (Object obj : objectList) {
			row = sheet.createRow(rownum++);

			Object[] result = (Object[]) obj;

			if (result != null) {

				row.createCell(1).setCellValue(result[0].toString());
				row.createCell(2).setCellValue(result[1].toString());
				row.createCell(3).setCellValue(result[2].toString());
				row.createCell(4).setCellValue(result[3].toString());
				row.createCell(5).setCellValue(result[4].toString());
				row.createCell(6).setCellValue(result[5].toString());
				row.createCell(7).setCellValue(result[6].toString());
				row.createCell(8).setCellValue(result[7].toString());

			}
		}

	}

	/**
	 * This method used to create the workbook sheet with the given list of
	 * Objects and list of query values
	 * 
	 * @param workbook
	 * @param sheet
	 * @param objectList
	 */
	public static void createExcelSheetwithObjectList(HSSFWorkbook workbook,
			HSSFSheet sheet, List<Object> objectList, String queryValues) {

		CellStyle style = workbook.createCellStyle();
		int rownum = 1;
		Row row = sheet.createRow(rownum++);

		StringTokenizer ss = new StringTokenizer(queryValues, ",");
		int j = 0;
		while (ss.hasMoreTokens()) {
			j++;
			row.createCell(j).setCellValue(ss.nextToken());

		}

		System.out.println(" Object list size from Database "
				+ objectList.size());
		for (Object obj : objectList) {
			row = sheet.createRow(rownum++);

			Object[] result = (Object[]) obj;

			if (result != null) {
				for (int i = 0; i < result.length; i++) {
					row.createCell(i + 1).setCellValue(
							Util.nullCheck(result[i]));

				}
			}
		}

	}
	
	public static void createExcelXSSSheetwithObjectList(SXSSFWorkbook workbook,
			Sheet sheet, List<Object> objectList, String queryValues) {

		CellStyle style = workbook.createCellStyle();
		int rownum = 1;
		Row row = sheet.createRow(rownum++);

		StringTokenizer ss = new StringTokenizer(queryValues, ",");
		int j = 0;
		while (ss.hasMoreTokens()) {
			j++;
			row.createCell(j).setCellValue(ss.nextToken());

		}

		System.out.println(" Object list size from Database "
				+ objectList.size());
		for (Object obj : objectList) {
			row = sheet.createRow(rownum++);

			Object[] result = (Object[]) obj;

			if (result != null) {
				for (int i = 0; i < result.length; i++) {
					row.createCell(i + 1).setCellValue(
							Util.nullCheck(result[i]));

				}
			}
		}

	}
}

