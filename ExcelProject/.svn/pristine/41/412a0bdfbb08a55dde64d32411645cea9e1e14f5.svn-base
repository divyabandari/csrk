package com.cisco.excel.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.excel.dto.InventoryDTO;
import com.cisco.excel.dto.InventoryOtherSheetsDTO;
import com.cisco.excel.dto.InventorySummary;


public  class ExcelCompartorUtil {

	protected static final Logger logger = LoggerFactory.getLogger(ExcelCompartorUtil.class);
	
	public static boolean readExelFiles(String fileName, Object object) {
		

		boolean flag  = false;
		
		try {
		    System.out.println("Object class is :  " + object.getClass());
			
		    FileInputStream irFile = new FileInputStream(new File("IR_InventorySummaryReport_TME.xlsm"));
		    
		    FileInputStream sntcFile = new FileInputStream(new File("SNTC_IMS_INVENTORY_INSIGHT_TME.xlsm"));
			     
		    //Get the workbook instance for XLS file
		    XSSFWorkbook  irWorkbook = new XSSFWorkbook (irFile);
		    XSSFWorkbook  sntcWorkbook = new XSSFWorkbook (sntcFile);
		    
		    
		 
		    //Get first sheet from the workbook
		    XSSFSheet irSheet = irWorkbook.getSheet("Reported Chassis");
		    XSSFSheet sntcSheet = sntcWorkbook.getSheet("Reported Chassis");
		    
		    List<String> irHostName = new ArrayList<String>();
		    List<String> sntcHostName = new ArrayList<String>();
		    
		    //Iterate through each rows from first sheet
		    Iterator<Row> irRowIterator = irSheet.iterator();
		    while(irRowIterator.hasNext()) {
		        Row row = irRowIterator.next();
		     //   System.out.println("Row Num "+row.getRowNum());
		        if(row.getRowNum()>=6){
		        //For each row, iterate through each columns
		        Iterator<Cell> cellIterator = row.cellIterator();
		        while(cellIterator.hasNext()) {
		             
		            Cell cell = cellIterator.next();
		          /*  CellStyle cs = new C
		            cell.setCellStyle();*/
		            
		       //     System.out.println(" get column index for cell "+ cell.getColumnIndex() );
			          if  (cell.getColumnIndex() ==0){
		             
		            switch(cell.getCellType()) {
		                case Cell.CELL_TYPE_BOOLEAN:
		                //    System.out.print(cell.getBooleanCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		               //     System.out.print(cell.getNumericCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_STRING:
		             //   	System.out.print(cell.getStringCellValue() + "\t\t");
		                	irHostName.add(Util.getValue(cell.getStringCellValue()));
		                    break;
		            }
			          }
		        }
		    //    System.out.println("");
		        }
		    }
		    Iterator<Row> sntcRowIterator = sntcSheet.iterator();

		    while(sntcRowIterator.hasNext()) {
		        Row row = sntcRowIterator.next();
		     //   System.out.println("Row Num "+row.getRowNum());
		        if(row.getRowNum()>=6){
		        //For each row, iterate through each columns
		        Iterator<Cell> cellIterator = row.cellIterator();
		        while(cellIterator.hasNext()) {
		             
		            Cell cell = cellIterator.next();
		        //    System.out.println(" get column index for cell "+ cell.getColumnIndex() );
		          if  (cell.getColumnIndex() ==0){
		             
		            switch(cell.getCellType()) {
		                case Cell.CELL_TYPE_BOOLEAN:
		              //      System.out.print(cell.getBooleanCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		             //       System.out.print(cell.getNumericCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_STRING:
		              //  	System.out.print(cell.getStringCellValue() + "\t\t");
		                	sntcHostName.add(Util.getValue(cell.getStringCellValue()));
		                    break;
		            }
		            
		          }
		        }
		      //  System.out.println("");
		        }
		    }
		    irFile.close();
		    sntcFile.close();
		    
		    System.out.println("  Final results ");
		    System.out.println(" IR  host size "+ irHostName.size());
		    
		    System.out.println(" SNTC host size "+ sntcHostName.size());
		    System.out.println(" Host Names missing IR ");
		    
		    
		    List<String> irMissingHost  = new ArrayList<String>(irHostName);// irHostName;
		    List<String> sntcMissingHost  = new ArrayList<String>(sntcHostName);//  Collections.clon(sntcHostName);
		    
		  /*  Collections.copy(irMissingHost, irHostName);
		    Collections.copy(sntcMissingHost, sntcHostName);
*/
                 sntcMissingHost.removeAll(irHostName);
		
		    System.out.println("Common Host names in the both the systems");
	    irHostName.retainAll(sntcHostName);
	    
	    System.out.println(" *********** Common values ************" + irHostName.size());
	    for(String str : irHostName){
	    	System.out.println(str);
	    	
	    }
                 
		    System.out.println(" *********** missing in IR ************" + sntcMissingHost.size());
		    for(String str : sntcMissingHost){
		    	System.out.println(str);
		    	
		    }
		    
		  
		    
		    irMissingHost.removeAll(sntcHostName);
		    System.out.println(" ********** Host Names missing in SNTC *********"+irMissingHost.size() );
		    
		    for(String str : irMissingHost){
		    	System.out.println(str);
		    	
		    }
		    
		   /* FileOutputStream out =
		        new FileOutputStream(new File("test1.xls"));
		    workbook.write(out);
		    out.close();*/
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		return flag;
	}
	
	
	public static List<InventoryDTO> readExelFile(XSSFWorkbook irWorkbook,	String sheetName) {

		List<InventoryDTO> lisInventoryDTO = new ArrayList<InventoryDTO>();

		try {

	/*		FileInputStream irFile = new FileInputStream(new File(fileName));
			// Get the workbook instance for XLS file
			XSSFWorkbook irWorkbook = new XSSFWorkbook(irFile);
*/
			// Get first sheet from the workbook
			XSSFSheet irSheet = irWorkbook.getSheet(sheetName);

			// List<String> irHostName = new ArrayList<String>();

			// Iterate through each rows from first sheet
			Iterator<Row> irRowIterator = irSheet.iterator();
			boolean isMacFlag  = true;
			while (irRowIterator.hasNext()) {
				Row row = irRowIterator.next();

				// System.out.println("Row Num "+row.getRowNum());
		
				if (isMacFlag && (row.getRowNum() ==3 || row.getRowNum() ==4)){
					Iterator<Cell> cellIterator1 = row.cellIterator();
					while (cellIterator1.hasNext()){
						
					Cell cell =	cellIterator1.next();
					if(cell.getColumnIndex()==2) {
						String macValue = Util.getCellValue(cell).toLowerCase();
						
						if(macValue.contains("mac")){
							isMacFlag = false;
								
						}
						
				System.out.println("value of the String" +	macValue + "row count is : "+ row.getRowNum());
					}
					}
					
					System.out.println(" System is having MAC Value : "+ isMacFlag);
				}
				
				if (row.getRowNum() >= 6) {
					InventoryDTO inventoryDTO = new InventoryDTO();
					// For each row, iterate through each columns

					Iterator<Cell> cellIterator = row.cellIterator();
//sheetName.equalsIgnoreCase(ExcelConstants.REPORTED_CHASSIS_SHEET)
					if (!isMacFlag) {
                      
						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();

							if (cell.getColumnIndex() == 0) {
								inventoryDTO.setHostName(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 1) {
								inventoryDTO.setIpAddress(Util
										.getCellValue(cell));

							} /*
							 * else if (cell.getColumnIndex() ==2){
							 * inventoryDTO.setIpAddress(getCellValue(cell));
							 * 
							 * }
							 */else if (cell.getColumnIndex() == 3) {
								inventoryDTO.setCollectedSerialNumber(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 4) {
								inventoryDTO.setCollectedPID(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 5) {
								inventoryDTO.setSerialNumber(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 6) {
								inventoryDTO.setPID(Util.getCellValue(cell));

							} else if (cell.getColumnIndex() == 7) {
								inventoryDTO.setEquipmentType(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 8) {
								inventoryDTO.setProductFamily(Util
										.getCellValue(cell));

							}
						}
					} else  {
					
						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();

							if (cell.getColumnIndex() == 0) {
								inventoryDTO.setHostName(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 1) {
								inventoryDTO.setIpAddress(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 2) {
								inventoryDTO.setCollectedSerialNumber(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 3) {
								inventoryDTO.setCollectedPID(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 4) {
								inventoryDTO.setSerialNumber(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 5) {
								inventoryDTO.setPID(Util.getCellValue(cell));

							} else if (cell.getColumnIndex() == 6) {
								inventoryDTO.setEquipmentType(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 7) {
								inventoryDTO.setProductFamily(Util
										.getCellValue(cell));

							}

						}
					}

					// System.out.println("");
					if(inventoryDTO!=null && inventoryDTO.getHostName()!=null)
					if (!inventoryDTO.getHostName().equals("")
							|| !inventoryDTO.getIpAddress().equals("")
							|| !inventoryDTO.getCollectedSerialNumber().equals(
									"")) {
						lisInventoryDTO.add(inventoryDTO);

					} /*else {
					
					}*/
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return lisInventoryDTO;

	}
	
	public static List<InventoryOtherSheetsDTO> readInventoryOtherSheets(XSSFWorkbook irWorkbook) {

		List<InventoryOtherSheetsDTO> inventoryOtherSheetsDTOList = new ArrayList<InventoryOtherSheetsDTO>();
		
		List<String> sheetList = new ArrayList<String>();
		sheetList.add(ExcelConstants.DUPLICATES_SHEET);
		sheetList.add(ExcelConstants.NOT_RECOGNIZED_SHET);
		sheetList.add(ExcelConstants.NOT_FIELD_REPLACEBLE_SHEET);
		sheetList.add(ExcelConstants.REPORTED_OTHERS_SHEET);
		
		for(String sheetName : sheetList){
System.out.println(" Priting the Sheet Name : " + sheetName);
		try {

			// Get first sheet from the workbook
			XSSFSheet irSheet = irWorkbook.getSheet(sheetName);

			// List<String> irHostName = new ArrayList<String>();

			// Iterate through each rows from first sheet
			Iterator<Row> irRowIterator = irSheet.iterator();
			while (irRowIterator.hasNext()) {
				Row row = irRowIterator.next();

				// System.out.println("Row Num "+row.getRowNum());
				if (row.getRowNum() >= 6) {
					InventoryOtherSheetsDTO inventoryOtherSheetsDTO = new InventoryOtherSheetsDTO();
					// For each row, iterate through each columns

					Iterator<Cell> cellIterator = row.cellIterator();

					if (sheetName.equalsIgnoreCase(ExcelConstants.DUPLICATES_SHEET) || sheetName.equalsIgnoreCase(ExcelConstants.REPORTED_OTHERS_SHEET)  ) {

						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();

							if (cell.getColumnIndex() == 0) {
								inventoryOtherSheetsDTO.setHostName(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 1) {
								inventoryOtherSheetsDTO.setIpAddress(Util
										.getCellValue(cell));

							}
							 else if (cell.getColumnIndex() == 3) {
								inventoryOtherSheetsDTO.setCollectedSerialNumber(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 4) {
								inventoryOtherSheetsDTO.setCollectedPID(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 5) {
								inventoryOtherSheetsDTO.setEquipementType(Util
										.getCellValue(cell));

							}
							
						}
					} else  {
						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();

							if (cell.getColumnIndex() == 0) {
								inventoryOtherSheetsDTO.setHostName(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 1) {
								inventoryOtherSheetsDTO.setIpAddress(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 2) {
								inventoryOtherSheetsDTO.setCollectedSerialNumber(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 3) {
								inventoryOtherSheetsDTO.setCollectedPID(Util
										.getCellValue(cell));

							} else if (cell.getColumnIndex() == 4) {
								inventoryOtherSheetsDTO.setEquipementType(Util
										.getCellValue(cell));

							} 
						}
					}

					// System.out.println("");
					if (inventoryOtherSheetsDTO !=null)
						if (!StringUtils.isEmpty(inventoryOtherSheetsDTO.getHostName())
								|| !StringUtils.isEmpty(inventoryOtherSheetsDTO.getIpAddress())
								|| !StringUtils.isEmpty(inventoryOtherSheetsDTO.getCollectedSerialNumber())) {
							inventoryOtherSheetsDTO.setReportSheetName(sheetName);
							inventoryOtherSheetsDTOList.add(inventoryOtherSheetsDTO);

					} /*else {
					
					}*/
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		    final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    e.printStackTrace(printWriter);
		    logger.debug(result.toString());

		}
		
	}

		return inventoryOtherSheetsDTOList;

	}
	
	
	public static void readExelFileSummary(String fileNamedsd,HSSFWorkbook workbook) {

		try {
			String sheetName = ExcelConstants.SUMMARY_SHEET_NAME;
			String irFileName = ExcelConstants.IR_FILE_NAME;
			String sntcFileName = ExcelConstants.SNTC_FILE_NAME;
			
			OPCPackage IRpkg = OPCPackage.open(new File(irFileName));
			XSSFWorkbook irWorkbook = new XSSFWorkbook(IRpkg);
			OPCPackage SNTCpkg = OPCPackage.open(new File(sntcFileName));
			XSSFWorkbook sntcWorkbook = new XSSFWorkbook(SNTCpkg);
			
//		    FileInputStream irFile = new FileInputStream(new File(irFileName));
//		    FileInputStream sntcFile = new FileInputStream(new File(sntcFileName));
//		    //Get the workbook instance for XLS file
//		    XSSFWorkbook  irWorkbook = new XSSFWorkbook (irFile);
//		    SXSSFWorkbook newIRWorkBook = new SXSSFWorkbook(irWorkbook, 100);
//		    XSSFWorkbook  sntcWorkbook = new XSSFWorkbook (sntcFile); 
//		    SXSSFWorkbook newSntcWorkBook = new SXSSFWorkbook(sntcWorkbook, 100); 
		
		    //Get first sheet from the workbook
		    XSSFSheet irSheet = irWorkbook.getSheet(sheetName);
		    XSSFSheet sntcSheet = sntcWorkbook.getSheet(sheetName);
	    
		 //   List<String> irHostName = new ArrayList<String>();

		    //Iterate through each rows from first sheet
		    Iterator<Row> irRowIterator = irSheet.iterator();
		    Iterator<Row> sntcRowIterator = sntcSheet.iterator();  
		    InventorySummary irInventorySummary  = new InventorySummary();
		    while(irRowIterator.hasNext()) {
		        Row row = irRowIterator.next();
		    
		     //   System.out.println("Row Num "+row.getRowNum());
		        if(row.getRowNum()>10 && row.getRowNum()<=23){
		            InventoryDTO inventoryDTO  = new InventoryDTO();
		        //For each row, iterate through each columns
		        	
		        Iterator<Cell> cellIterator = row.cellIterator();
		      
		        while(cellIterator.hasNext()) {	
		             
		            Cell cell = cellIterator.next();
		          
		            if  (cell.getColumnIndex() ==1){
		            if(row.getRowNum()==11)
		            	irInventorySummary.setNoOfIps(Util.getCellValueInt(cell));
		            if(row.getRowNum()==12)
		            	irInventorySummary.setNoOfIpsNotCollected(Util.getCellValueInt(cell));
		            if(row.getRowNum()==13)
		            	irInventorySummary.setCollectedEquipment(Util.getCellValueInt(cell));
		            if(row.getRowNum()==14)
		            	irInventorySummary.setNoOfChassisCollectedReported(Util.getCellValueInt(cell));
		            if(row.getRowNum()==15)
		            	irInventorySummary.setNoOfCardsCollectedReported(Util.getCellValueInt(cell));
		            if(row.getRowNum()==17)
		            	irInventorySummary.setNoOfThirdParty(Util.getCellValueInt(cell));
		            if(row.getRowNum()==18)
		            	irInventorySummary.setNoOfDuplicates(Util.getCellValueInt(cell));
		            if(row.getRowNum()==22)
		            	irInventorySummary.setNoOfNotRecognized(Util.getCellValueInt(cell));
		            if(row.getRowNum()==23)
		            	irInventorySummary.setNoOfNonFieldReplaceble(Util.getCellValueInt(cell));
		            }
		            
		              if  (cell.getColumnIndex() ==0){
			        	  inventoryDTO.setHostName(Util.getCellValue(cell));
			        //	  System.out.println(" Cell Value is :"+ Util.getCellValue(cell) + "Row Num : "+ row.getRowNum());
			        	  
			          } else if  (cell.getColumnIndex() ==1){
			        	  inventoryDTO.setIpAddress(Util.getCellValue(cell));
			        	//  System.out.println(" Cell Value is :"+ Util.getCellValue(cell));
			        	  
			          } 
		              }
		    //    System.out.println("");
		       /* if(inventoryDTO.getHostName().equals("") && inventoryDTO.getIpAddress().equals("") && inventoryDTO.getCollectedSerialNumber().equals("")){
		        	
		        	
		        }else{
		        lisInventoryDTO.add(inventoryDTO);
		        }*/
		        }
		        
		       
		    }
		    InventorySummary sntcInventorySummary  = new InventorySummary();
		    
		    while(sntcRowIterator.hasNext()) {
		        Row row = sntcRowIterator.next();
		    
		     //   System.out.println("Row Num "+row.getRowNum());
		        if(row.getRowNum()>9 && row.getRowNum()<=23){
		            InventoryDTO inventoryDTO  = new InventoryDTO();
		        //For each row, iterate through each columns
		        	
		        Iterator<Cell> cellIterator = row.cellIterator();
		        while(cellIterator.hasNext()) {	
		             
		            Cell cell = cellIterator.next();
		            if  (cell.getColumnIndex() ==1){
			            if(row.getRowNum()==10)
			            	sntcInventorySummary.setNoOfIps(Util.getCellValueInt(cell));
			            if(row.getRowNum()==11)
			            	sntcInventorySummary.setNoOfIpsNotCollected(Util.getCellValueInt(cell));
			            if(row.getRowNum()==12)
			            	sntcInventorySummary.setCollectedEquipment(Util.getCellValueInt(cell));
			            if(row.getRowNum()==13)
			            	sntcInventorySummary.setNoOfChassisCollectedReported(Util.getCellValueInt(cell));
			            if(row.getRowNum()==14)
			            	sntcInventorySummary.setNoOfCardsCollectedReported(Util.getCellValueInt(cell));
			            if(row.getRowNum()==15)
			            	sntcInventorySummary.setNoOfThirdParty(Util.getCellValueInt(cell));
			            if(row.getRowNum()==16)
			            	sntcInventorySummary.setNoOfDuplicates(Util.getCellValueInt(cell));
			            if(row.getRowNum()==17)
			            	sntcInventorySummary.setNoOfNotRecognized(Util.getCellValueInt(cell));
			            if(row.getRowNum()==18)
			            	sntcInventorySummary.setNoOfNonFieldReplaceble(Util.getCellValueInt(cell));
			            }
		            
		              if  (cell.getColumnIndex() ==0){
			        	  inventoryDTO.setHostName(Util.getCellValue(cell));
			       // 	  System.out.println(" Cell Value is :"+ Util.getCellValue(cell) + "Row Num : "+ row.getRowNum());
			        	  
			          } else if  (cell.getColumnIndex() ==1){
			        	  inventoryDTO.setIpAddress(Util.getCellValue(cell));
			      //  	  System.out.println(" Cell Value is :"+ Util.getCellValue(cell));
			        	  
			          } 
		              }
		        }
		    }
		        HSSFSheet sheet = workbook.createSheet("SummaryDiff");
			    
			     CellStyle style = workbook.createCellStyle();
		       	    
		               
		       	    
			 //   int rownum = 1;
			     
			     
			     Row summaryRow = sheet.createRow(1);
				    Cell cell = summaryRow.createCell(1);
					   cell.setCellValue("Title");
					    cell = summaryRow.createCell(2);
					    cell.setCellValue("1.x");
					   cell = summaryRow.createCell(3);
					   cell.setCellValue("2.x");
					   cell = summaryRow.createCell(4);
					   cell.setCellValue("Diff 1.x - 2.x");
					   
					   
					   
			   summaryRow = sheet.createRow(2);
			     cell = summaryRow.createCell(1);
				   cell.setCellValue("Number of IP addresses in the Managed Device  List");
				    cell = summaryRow.createCell(2);
				    cell.setCellValue(irInventorySummary.getNoOfIps());
				   cell = summaryRow.createCell(3);
				   cell.setCellValue(sntcInventorySummary.getNoOfIps());
				   cell = summaryRow.createCell(4);
				   cell.setCellValue(irInventorySummary.getNoOfIps()-sntcInventorySummary.getNoOfIps());
				   
				    summaryRow = sheet.createRow(3);
				
				  cell = summaryRow.createCell(1);
				   cell.setCellValue("Number of IP addresses not collected");
				    cell = summaryRow.createCell(2);
				    cell.setCellValue(irInventorySummary.getNoOfIpsNotCollected());
				   cell = summaryRow.createCell(3);
				   cell.setCellValue(sntcInventorySummary.getNoOfIpsNotCollected());
				   cell = summaryRow.createCell(4);
				   cell.setCellValue(irInventorySummary.getNoOfIpsNotCollected()-sntcInventorySummary.getNoOfIpsNotCollected());
				  
				   
				   summaryRow = sheet.createRow(4);
					
					  cell = summaryRow.createCell(1);
					   cell.setCellValue("Collected Equipment Count");
					    cell = summaryRow.createCell(2);
					    cell.setCellValue(irInventorySummary.getCollectedEquipment());
					   cell = summaryRow.createCell(3);
					   cell.setCellValue(sntcInventorySummary.getCollectedEquipment());
					   cell = summaryRow.createCell(4);
					   cell.setCellValue(irInventorySummary.getCollectedEquipment()-sntcInventorySummary.getCollectedEquipment());
					  
					   
					   
					   summaryRow = sheet.createRow(5);
						
						  cell = summaryRow.createCell(1);
						   cell.setCellValue("Number of chassis collected and reported");
						    cell = summaryRow.createCell(2);
						    cell.setCellValue(irInventorySummary.getNoOfChassisCollectedReported());
						   cell = summaryRow.createCell(3);
						   cell.setCellValue(sntcInventorySummary.getNoOfChassisCollectedReported());
						   cell = summaryRow.createCell(4);
						   cell.setCellValue(irInventorySummary.getNoOfChassisCollectedReported()-sntcInventorySummary.getNoOfChassisCollectedReported());
						   
						   
						   
						   
						   
						   summaryRow = sheet.createRow(6);
							
							  cell = summaryRow.createCell(1);
							   cell.setCellValue("Number of cards collected and reported");
							    cell = summaryRow.createCell(2);
							    cell.setCellValue(irInventorySummary.getNoOfCardsCollectedReported());
							   cell = summaryRow.createCell(3);
							   cell.setCellValue(sntcInventorySummary.getNoOfCardsCollectedReported());
							   cell = summaryRow.createCell(4);
							   cell.setCellValue(irInventorySummary.getNoOfCardsCollectedReported()-sntcInventorySummary.getNoOfCardsCollectedReported());
							  
							   
							   
							   summaryRow = sheet.createRow(7);
								
								  cell = summaryRow.createCell(1);
								   cell.setCellValue("Number of 3rd party items");
								    cell = summaryRow.createCell(2);
								    cell.setCellValue(irInventorySummary.getNoOfThirdParty());
								   cell = summaryRow.createCell(3);
								   cell.setCellValue(sntcInventorySummary.getNoOfThirdParty());
								   cell = summaryRow.createCell(4);
								   cell.setCellValue(irInventorySummary.getNoOfThirdParty()-sntcInventorySummary.getNoOfThirdParty());
								  
								   
								   
								   summaryRow = sheet.createRow(7);
									
									  cell = summaryRow.createCell(1);
									   cell.setCellValue("Number of duplicate items");
									    cell = summaryRow.createCell(2);
									    cell.setCellValue(irInventorySummary.getNoOfDuplicates());
									   cell = summaryRow.createCell(3);
									   cell.setCellValue(sntcInventorySummary.getNoOfDuplicates());
									   cell = summaryRow.createCell(4);
									   cell.setCellValue(irInventorySummary.getNoOfDuplicates()-sntcInventorySummary.getNoOfDuplicates());
									  
									   
									   
									   summaryRow = sheet.createRow(7);
										
										  cell = summaryRow.createCell(1);
										   cell.setCellValue("Number of items not recognized as a Cisco device");
										    cell = summaryRow.createCell(2);
										    cell.setCellValue(irInventorySummary.getNoOfNotRecognized());
										   cell = summaryRow.createCell(3);
										   cell.setCellValue(sntcInventorySummary.getNoOfNotRecognized());
										   cell = summaryRow.createCell(4);
										   cell.setCellValue(irInventorySummary.getNoOfNotRecognized()-sntcInventorySummary.getNoOfNotRecognized());
										  
										   
										   
										   summaryRow = sheet.createRow(7);
											
											  cell = summaryRow.createCell(1);
											   cell.setCellValue("Number of non field replaceable items");
											    cell = summaryRow.createCell(2);
											    cell.setCellValue(irInventorySummary.getNoOfNonFieldReplaceble());
											   cell = summaryRow.createCell(3);
											   cell.setCellValue(sntcInventorySummary.getNoOfNonFieldReplaceble());
											   cell = summaryRow.createCell(4);
											   cell.setCellValue(irInventorySummary.getNoOfNonFieldReplaceble()-sntcInventorySummary.getNoOfNonFieldReplaceble());
	
		
		}catch(Exception e){
		e.printStackTrace();
		
	
	}
		
	
		
		
				
	}
	
	public static boolean wirteExcelFile(List<Object> chassisObjectList, String fileName, String sheetName, List<Object> cardObjectList , Map<String, String> map, List<Object> list){
		
		 HSSFWorkbook workbook = new HSSFWorkbook();
		  
			   
		    try {
		    	  HSSFSheet chassisSheet = workbook.createSheet("Chassis_Host_Diff");
		    	createSheet(workbook, chassisSheet, chassisObjectList);
		    	
		    	  HSSFSheet cardSheet = workbook.createSheet("Cards_Host_Diff");
		    	createSheet(workbook, cardSheet, cardObjectList);
		    	// Creating sheet with the missing cards in SNTC2.x with the vendor equipment TYpe from IR database..
		    	vepWirteExcelFile(workbook , map, list);
		    	
		    	readExelFileSummary("",workbook);
		    
			        FileOutputStream out = 
			                new FileOutputStream(new File("C:\\FORD_IR_SNTC_Diff_Summary_Final.xls"));
			        workbook.write(out);
			        out.close();
			        System.out.println("Excel written successfully..");
			         
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }catch (Exception e) {
					// TODO: handle exception
			    	e.printStackTrace();
				}
		
		
		return true;
	}

	public static void createSheetByRowTemplate(HSSFWorkbook workbook,  HSSFSheet sheet){
		
	       int rownum = 1;
		    Row row = sheet.createRow(rownum);
		
	    Cell cell = row.createCell(1);
		   cell.setCellValue("Common Collected Serial Number");
		 row.createCell(2).setCellValue("IR HostName");
		 row.createCell(3).setCellValue("SNTC HOSTNAME");
		    row.createCell(4).setCellValue("HOST MATCH");
		 row.createCell(5).setCellValue("COMMON STRING");
		  row.createCell(6).setCellValue("EXTRA IR HOSTNAME");
		  row.createCell(7).setCellValue("EXTRA SNTC HOSTNAME");	
		row.createCell(8).setCellValue("IR IP Address");
	   row.createCell(9).setCellValue("SNTC IP Address"); ///
	   row.createCell(10).setCellValue("IP Address Match");
	   row.createCell(11).setCellValue("IR Collected PID");
	   row.createCell(12).setCellValue("SNTC Collected PID");
	   row.createCell(13).setCellValue("Collected PID Match");
	   row.createCell(14).setCellValue("IR Serial Number");
	   row.createCell(15).setCellValue("SNTC Serial Number");
	   row.createCell(16).setCellValue("Serial Number Match");
	   row.createCell(17).setCellValue("IR PID");
	   row.createCell(18).setCellValue("SNTC PID");
	   row.createCell(19).setCellValue("PID Match");
	   row.createCell(20).setCellValue("IR Equipment Type");
	   row.createCell(21).setCellValue("SNTC Equipment Type");
	   row.createCell(22).setCellValue("Equipment Type Match");
	   row.createCell(23).setCellValue("IR Product Family");
	   row.createCell(24).setCellValue("SNTC Product Family");
	   row.createCell(25).setCellValue("Product Family Match");
	  	   System.out.println(" Created the sheet by Template ");
	}
	
	
	public static void createSheet(HSSFWorkbook workbook,  HSSFSheet sheet, List<Object> objectList){
		
	       int rownum = 1;
		    Row row = sheet.createRow(rownum++);
		
	    Cell cell = row.createCell(1);
		   cell.setCellValue("Common Collected Serial Number");
		 row.createCell(2).setCellValue("IR HostName");
		 row.createCell(3).setCellValue("SNTC HOSTNAME");
		    row.createCell(4).setCellValue("HOST MATCH");
		 row.createCell(5).setCellValue("COMMON STRING");
		  row.createCell(6).setCellValue("EXTRA IR HOSTNAME");
		  row.createCell(7).setCellValue("EXTRA SNTC HOSTNAME");	
		row.createCell(8).setCellValue("IR IP Address");
	   row.createCell(9).setCellValue("SNTC IP Address"); ///
	   row.createCell(10).setCellValue("IP Address Match");
	   row.createCell(11).setCellValue("IR Collected PID");
	   row.createCell(12).setCellValue("SNTC Collected PID");
	   row.createCell(13).setCellValue("Collected PID Match");
	   row.createCell(14).setCellValue("IR Serial Number");
	   row.createCell(15).setCellValue("SNTC Serial Number");
	   row.createCell(16).setCellValue("Serial Number Match");
	   row.createCell(17).setCellValue("IR PID");
	   row.createCell(18).setCellValue("SNTC PID");
	   row.createCell(19).setCellValue("PID Match");
	   row.createCell(20).setCellValue("IR Equipment Type");
	   row.createCell(21).setCellValue("SNTC Equipment Type");
	   row.createCell(22).setCellValue("Equipment Type Match");
	   row.createCell(23).setCellValue("IR Product Family");
	   row.createCell(24).setCellValue("SNTC Product Family");
	   row.createCell(25).setCellValue("Product Family Match");
	  	   System.out.println(" Object list size from Database "+ objectList.size());
	    for(Object obj : objectList){
	    	  row = sheet.createRow(rownum++);
		 
		   Object[] result = (Object[]) obj;
		   
		 if ( result.length>0) {   
		   
		 if(result[0]!=null)
		    row.createCell(1).setCellValue(result[0].toString());
		 else
			row.createCell(1).setCellValue("");
		 
		 if(result[1]!=null)
 		    row.createCell(2).setCellValue(result[1].toString());
		 else
			row.createCell(2).setCellValue("");
		 
		 if(result[2]!=null)
        	 row.createCell(3).setCellValue(result[2].toString());
		 else
			 row.createCell(3).setCellValue("");
		 
		 String stringArray[] = Util.getHostDiff(result[1].toString(), result[2].toString()); // passing IR and SNTC host names to get the diff
		 
		 if(result[3]!=null)
			 row.createCell(4).setCellValue(stringArray[3].toString());
		 else
			 row.createCell(4).setCellValue("");
		 
		 if(stringArray[0]!=null)
            row.createCell(5).setCellValue(stringArray[0].toString());
		 else
			row.createCell(5).setCellValue("");
		 
		 if(stringArray[1]!=null)
    	   row.createCell(6).setCellValue(stringArray[1].toString());
		 else
		   row.createCell(6).setCellValue("");
		 
		 if(stringArray[2]!=null)
		   row.createCell(7).setCellValue(stringArray[2].toString());
		 else
		   row.createCell(7).setCellValue("");	 
			  
		 if(result[3]!=null)
			  row.createCell(8).setCellValue(result[3].toString());
		 else
			 row.createCell(8).setCellValue("");
		 
		 if(result[4]!=null)
	 		  row.createCell(9).setCellValue(result[4].toString());
		 else
			 row.createCell(9).setCellValue("");
	 		  
		 if(result[3]!=null && result[4]!=null)
	 		  row.createCell(10).setCellValue(Util.getStringDiff(result[3].toString(), result[4].toString()));//
		 else{
			 String a="",b="";
			 if(result[3]!=null) a= result[3].toString();
			 if(result[4]!=null) b= result[4].toString();
			 row.createCell(10).setCellValue(Util.getStringDiff(a, b));//
		 }
	 		
		 if(result[5]!=null)
	 		  row.createCell(11).setCellValue(result[5].toString());
		 else
			 row.createCell(11).setCellValue("");
		 
		 if(result[6]!=null)
	 		  row.createCell(12).setCellValue(result[6].toString());
		 else
			 row.createCell(12).setCellValue("");
		 
		 if(result[5]!=null && result[6]!=null)
	 		  row.createCell(13).setCellValue(Util.getStringDiff(result[5].toString(), result[6].toString()));//
		 else{
			 String a="",b="";
			 if(result[5]!=null) a= result[5].toString();
			 if(result[6]!=null) b= result[6].toString();
			 row.createCell(13).setCellValue(Util.getStringDiff(a, b));//
		 }		  
		 
		 if(result[7]!=null)
	 		  row.createCell(14).setCellValue(result[7].toString());
		 else
			 row.createCell(14).setCellValue("");
		 
		 if(result[8]!=null)
	 		  row.createCell(15).setCellValue(result[8].toString());
		 else
			 row.createCell(15).setCellValue("");
		 
		 if(result[7]!=null && result[8]!=null)
	 		  row.createCell(16).setCellValue(Util.getStringDiff(result[7].toString(), result[8].toString()));//
		 else{
			 String a="",b="";
			 if(result[7]!=null) a= result[7].toString();
			 if(result[8]!=null) b= result[8].toString();
			 row.createCell(16).setCellValue(Util.getStringDiff(a, b));//
		 }	
	 		  
		 if(result[9]!=null)
	 		  row.createCell(17).setCellValue(result[9].toString());
		 else
			 row.createCell(17).setCellValue("");
		 
		 if(result[10]!=null)
	 		  row.createCell(18).setCellValue(result[10].toString());
		 else
			 row.createCell(18).setCellValue("");
		 
		 if(result[9]!=null && result[10]!=null)
	 		  row.createCell(19).setCellValue(Util.getStringDiff(result[9].toString(), result[10].toString()));//
		 else{
			 String a="",b="";
			 if(result[9]!=null) a= result[9].toString();
			 if(result[10]!=null) b= result[10].toString();
			 row.createCell(19).setCellValue(Util.getStringDiff(a, b));//
		 }
			 
	 	if(result[11]!=null)
	 		  row.createCell(20).setCellValue(result[11].toString());
	 	else
	 		  row.createCell(20).setCellValue("");
	 	
	 	if(result[12]!=null)
	 		  row.createCell(21).setCellValue(result[12].toString());
	 	else
	 		  row.createCell(21).setCellValue("");
	 	
	 	if(result[11]!=null && result[12]!=null)
	 		  row.createCell(22).setCellValue(Util.getStringDiff(result[11].toString(), result[12].toString())); //
	 	else{
			 String a="",b="";
			 if(result[11]!=null) a= result[11].toString();
			 if(result[12]!=null) b= result[12].toString();
			 row.createCell(22).setCellValue(Util.getStringDiff(a, b));//
		 }
	 	
	 	if(result[13]!=null)
	 		 row.createCell(23).setCellValue(result[13].toString());
	 	else
	 		 row.createCell(23).setCellValue("");
	 	
	 	if(result[14]!=null)
	 		  row.createCell(24).setCellValue(result[14].toString());
	 	else
	 		  row.createCell(24).setCellValue("");
	 	
	 	if(result[13]!=null && result[14]!=null)
	 		  row.createCell(25).setCellValue(Util.getStringDiff(result[13].toString(), result[14].toString())); //
	 	else{
			 String a="",b="";
			 if(result[13]!=null) a= result[13].toString();
			 if(result[14]!=null) b= result[14].toString();
			 row.createCell(25).setCellValue(Util.getStringDiff(a, b));//
		 }
		   
	   }
	    }
		
	}
	
	public static void createMissingItemsSheet(HSSFWorkbook workbook,  HSSFSheet sheet, List<Object> objectList,  Map<String,String> snMap){
		
	       int rownum = 1;
		    Row row = sheet.createRow(rownum++);
		
	    Cell cell = row.createCell(1);
		   cell.setCellValue("Collected Serial Number");
		 row.createCell(2).setCellValue("HostName");
		 row.createCell(3).setCellValue("IP Address");
		    row.createCell(4).setCellValue("Collected PID");
		 row.createCell(5).setCellValue("Equipment Type");
		  row.createCell(6).setCellValue("Product Family");
		  row.createCell(7).setCellValue("IR VEP ID");	
		System.out.println(" Object list size from Database "+ objectList.size());
	    for(Object obj : objectList){
	    	  row = sheet.createRow(rownum++);
		 
		   Object[] result = (Object[]) obj;
		   
		 for ( int i=0; i< result.length ;i++) {   
			if(result[i]!=null)
				row.createCell(i+1).setCellValue(result[i].toString());
			else
				row.createCell(i+1).setCellValue("");
		 //   row.createCell(2).setCellValue(result[1].toString());
     		 
		 }
		 row.createCell(7).setCellValue(snMap.get(result[0].toString()));
		   
	
	   }
		
	}
	
	public static void createMissingItemsTemplate(HSSFWorkbook workbook,  HSSFSheet sheet){
		
	       int rownum = 1;
		    Row row = sheet.createRow(rownum);
		
		    Cell cell = row.createCell(1);
			   cell.setCellValue("Collected Serial Number");
			 row.createCell(2).setCellValue("HostName");
			 row.createCell(3).setCellValue("IP Address");
			    row.createCell(4).setCellValue("Collected PID");
			 row.createCell(5).setCellValue("Equipment Type");
			  row.createCell(6).setCellValue("Product Family");
			  row.createCell(7).setCellValue("Validated PID");
			  row.createCell(8).setCellValue("Validated SerialNum");
			  row.createCell(9).setCellValue("IR VEP ID");	
		System.out.println("Created Missing Items template ");

	}
	
	
	public static void createMissingItemsReportedInOtherSheetTemplate(HSSFWorkbook workbook,  HSSFSheet sheet){
		
	       int rownum = 1;
		    Row row = sheet.createRow(rownum);
		
		    Cell cell = row.createCell(1);
			   cell.setCellValue("Collected Serial Number");
			 row.createCell(2).setCellValue("HostName");
			 row.createCell(3).setCellValue("IP Address");
			    row.createCell(4).setCellValue("Collected PID");
			 row.createCell(5).setCellValue("Equipment Type");
			  row.createCell(6).setCellValue("Product Family");
			  row.createCell(7).setCellValue("Validated PID");
			  row.createCell(8).setCellValue("Validated SerialNum");  
			  row.createCell(9).setCellValue("SNTC Sheet Name");	
		System.out.println(" Created the Missing Items Reported template ");
		
	}
	
	public static void createMissingItemsReportedInOtherSheet(HSSFWorkbook workbook,  HSSFSheet sheet, List<Object> objectList){
		
	       int rownum = 1;
		    Row row = sheet.createRow(rownum++);
		
	    Cell cell = row.createCell(1);
		   cell.setCellValue("Collected Serial Number");
		 row.createCell(2).setCellValue("HostName");
		 row.createCell(3).setCellValue("IP Address");
		    row.createCell(4).setCellValue("Collected PID");
		 row.createCell(5).setCellValue("Equipment Type");
		  row.createCell(6).setCellValue("Product Family");
		  row.createCell(7).setCellValue("SNTC Sheet Name");	
		System.out.println(" Object list size from Database "+ objectList.size());
	    for(Object obj : objectList){
	    	  row = sheet.createRow(rownum++);
		 
		   Object[] result = (Object[]) obj;
		   
		 for ( int i=0; i< result.length ;i++) {   
			 if(result[i]!=null)
				 row.createCell(i+1).setCellValue(result[i].toString());
			 else
				 row.createCell(i+1).setCellValue("");
		 }
     
		   
	
	   }
		
	}
	
	public static boolean vepWirteExcelFile(HSSFWorkbook workbook , Map<String, String> map, List<Object> list){
		
	
		    HSSFSheet sheet = workbook.createSheet("SerialNumberVEP");
		    
		     CellStyle style = workbook.createCellStyle();
	       	    
	               
	       	    
		    int rownum = 1;
		    Row row = sheet.createRow(rownum++);
		
		    Cell cell = row.createCell(1);
			   cell.setCellValue("IR Card Extra Collected Serial Number");
			   Util.applyHeaderColor(cell);
			  cell = row.createCell(2);
			   cell.setCellValue("IR Vendor Equipment Type ");
			   Util.applyHeaderColor(cell);
			   cell = row.createCell(3);
			   cell.setCellValue("IR Host Name ");
			   cell = row.createCell(4);
			   cell.setCellValue("IR IP Address ");
			   cell = row.createCell(5);
			   cell.setCellValue("IR Collected PID ");
			   cell = row.createCell(6);
			   cell.setCellValue("IR Equipment Type ");
			   cell = row.createCell(7);
			   cell.setCellValue("IR product Family ");
		
		    	  row = sheet.createRow(rownum++);
			   
			   
			   for(Object obj : list){
			    	  row = sheet.createRow(rownum++);
				   
				   Object[] result = (Object[]) obj;
				   
				 if ( result.length>0) {
					  
					  
				  }
				   
				    cell = row.createCell(1);
				   cell.setCellValue(result[0].toString());
		         
				    cell = row.createCell(2);
				   cell.setCellValue(map.get(result[0].toString()));
		          
				    cell = row.createCell(3);
				   cell.setCellValue(result[1].toString());
		           
				   cell = row.createCell(4);
				   cell.setCellValue(result[2].toString());
		          
				    cell = row.createCell(5);
				   cell.setCellValue(result[3].toString());
				   
				//   String stringArray[] = Util.getHostDiff(result[1].toString(), result[3].toString());

				   cell = row.createCell(6);
				cell.setCellValue(result[4].toString());
		     //      applyStyle(cell,style);
				  
		            cell = row.createCell(7);
		           cell.setCellValue(result[5].toString());
		 
		           
				   
			
			   }
		/*    for(String str : list){
		    	  row = sheet.createRow(rownum++);
			   
			
			   
			    cell = row.createCell(1);
			   cell.setCellValue(str);
             
			    cell = row.createCell(2);
			   cell.setCellValue(map.get(str));
             
			  		
		   
		    }*/
		
		return true;
	}
	

	
	public static void main (String ar[]){
		
		readExelFileSummary("dummy" ,null);
		
		
	}
	
}
