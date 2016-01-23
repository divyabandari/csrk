package com.cisco.excel.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.cisco.excel.dao.ContractExcelDao;
import com.cisco.excel.dao.IRExcelDao;
import com.cisco.excel.dto.AllContractDTO;
import com.cisco.excel.dto.CoveredDevicesDTO;
import com.cisco.excel.dto.NotCoveredDevicesDTO;
import com.cisco.excel.service.ContractExcelService;
import com.cisco.excel.util.ExcelConstants;
import com.cisco.excel.util.ExcelContractsCompartorUtil;
import com.cisco.excel.util.IRAllContractsSheetHandler;
import com.cisco.excel.util.IRChassisCoveredCardsSheetHandler;
import com.cisco.excel.util.IRCoveredItemsSheetHandler;
import com.cisco.excel.util.IRNotCoveredSheetHandler;
import com.cisco.excel.util.SNTCAllContractsSheetHandler;
import com.cisco.excel.util.SNTCCoveredItemsSheetHandler;
import com.cisco.excel.util.SNTCNotCoveredSheetHandler;

@Service
public class ContractExcelServiceImpl implements ContractExcelService {

	@Autowired
	private ContractExcelDao contractExcelDAO;

	@Autowired
	private IRExcelDao irExcelDAO;
	
	@Autowired
	private Environment env;
	
	protected static final Logger logger = LoggerFactory.getLogger(ExcelContractsCompartorUtil.class);

	public ContractExcelServiceImpl() {
		super();
	}

	public boolean storeCMRAllContractsSheet(){
		// storing the IR All Contract sheet information 
		logger.debug("INSIDE STORE CONTRACTS METHOD");
//		storeIrExcel();
		parseAndStoreIrExcel();
		// storing the SNTC All Contract sheet information 
//		storeSntcExcel();
		parseAndStoreSntcExcel();
		
		return true;
		
	}
/**
 * This method is used to get all the proposed differences of CMR report b.w IR and SNTC..	
 * @return
 */
	public boolean getCMRDifferences(){

		try {
//			 HSSFWorkbook workbook = new HSSFWorkbook();
			 SXSSFWorkbook workbook = new SXSSFWorkbook(50000);
//	    	  HSSFSheet allContractSheet = workbook.createSheet("All_Contracts_Diff");
			 Sheet allContractSheet = workbook.createSheet("All_Contracts_Diff");
	    	  
	    	List<Object>  contractsList =  contractExcelDAO.getQueryList(env.getProperty("CMRCommonContracts"));
	    	  	    	  
//	  	 ExcelContractsCompartorUtil.createSheet(workbook, allContractSheet, contractsList);
	  	ExcelContractsCompartorUtil.createXSSSheet(workbook, allContractSheet, contractsList);
	  	 
	  	 System.out.println(" Missing Contracts in 2.x Sheet creating : " );
//	  	 HSSFSheet missingContractsSheet = workbook.createSheet("Missing Contracts in 2.x");
	  	 Sheet missingContractsSheet = workbook.createSheet("Missing Contracts in 2.x");
	  	List<Object>  missingSNTCContractsList = contractExcelDAO.getQueryList(env.getProperty("missingContractsinSNTC"));
	  	
//	  	ExcelContractsCompartorUtil.createExtraContractsSheet(workbook,missingContractsSheet,missingSNTCContractsList);
	  	ExcelContractsCompartorUtil.createExtraContractsXSSSheet(workbook,missingContractsSheet,missingSNTCContractsList);
	  	
	  	 System.out.println(" Extra Contracts in 2.x Sheet creating : " );

//	 	 HSSFSheet extraContractsSheet = workbook.createSheet("Extra Contracts in 2.x");
	  	 Sheet extraContractsSheet = workbook.createSheet("Extra Contracts in 2.x");
	  	List<Object>  extraSNTCContractsList = contractExcelDAO.getQueryList(env.getProperty("extraContractsinSNTC"));
	  	
//	 	ExcelContractsCompartorUtil.createExtraContractsSheet(workbook,extraContractsSheet,extraSNTCContractsList);
	 	ExcelContractsCompartorUtil.createExtraContractsXSSSheet(workbook,extraContractsSheet,extraSNTCContractsList);
	//  *********************************** COVEREd DEVICES **************************************  	
	  	// get common devices in 1.x and 2.x 
	 	
	  	 System.out.println(" Common_Covered_Devices Sheet creating : " );
//	 	  HSSFSheet commonDevicesSheet = workbook.createSheet("Common_Covered_Devices");
	 	 Sheet commonDevicesSheet = workbook.createSheet("Common_Covered_Devices");
	   	List<Object>  coveredDevicesList =  contractExcelDAO.getQueryList(env.getProperty("commonCoveredDevicesinCMR"));
    	  
//	  	 ExcelContractsCompartorUtil.createMatchSheetbyQueryListValues(workbook, commonDevicesSheet, coveredDevicesList,env.getProperty("commonCoveredDevicesinCMRValues"));
	  	ExcelContractsCompartorUtil.createMatchXSSSheetbyQueryListValues(workbook, commonDevicesSheet, coveredDevicesList,env.getProperty("commonCoveredDevicesinCMRValues"));
	  	 
	 	// get Missing Covered Devices in  2.x 
	 	 System.out.println(" Missing_Covered_Devices_SNTC Sheet creating : " );
//	 	  HSSFSheet missingCoveredDevicesinSNTCSheet = workbook.createSheet("Missing_Covered_Devices_SNTC");
	 	  Sheet missingCoveredDevicesinSNTCSheet = workbook.createSheet("Missing_Covered_Devices_SNTC");
	   	List<Object>  sntcMissingcoveredDevicesList =  contractExcelDAO.getQueryList(env.getProperty("missingCoveredDevicesinCMRSNTC"));
   	  
//	  	 ExcelContractsCompartorUtil.createExcelSheetwithObjectList(workbook, missingCoveredDevicesinSNTCSheet, sntcMissingcoveredDevicesList , env.getProperty("missingCoveredDevicesinCMRSNTCValues"));
	   	  ExcelContractsCompartorUtil.createExcelXSSSheetwithObjectList(workbook, missingCoveredDevicesinSNTCSheet, sntcMissingcoveredDevicesList , env.getProperty("missingCoveredDevicesinCMRSNTCValues"));
	  	 
	  	 
	  // get Extra Covered Devices in  2.x 
	  	 System.out.println(" Extra_Covered_Devices_SNTC Sheet creating : " );
//	 	  HSSFSheet extraCoveredDevicesinSNTCSheet = workbook.createSheet("Extra_Covered_Devices_SNTC");
	 	  Sheet extraCoveredDevicesinSNTCSheet = workbook.createSheet("Extra_Covered_Devices_SNTC");
	   	List<Object>  sntcExtraCoveredDevicesList =  contractExcelDAO.getQueryList(env.getProperty("extraCoveredDevicesinCMRSNTC"));
  	  
//	  	 ExcelContractsCompartorUtil.createExcelSheetwithObjectList(workbook, extraCoveredDevicesinSNTCSheet, sntcExtraCoveredDevicesList , env.getProperty("extraCoveredDevicesinCMRSNTCValues"));
	   	 ExcelContractsCompartorUtil.createExcelXSSSheetwithObjectList(workbook, extraCoveredDevicesinSNTCSheet, sntcExtraCoveredDevicesList , env.getProperty("extraCoveredDevicesinCMRSNTCValues"));
	  	 
	 // ****************************** NOT COVERED DEVICES **************************************
	  	 
		  	// get common devices in 1.x and 2.x 
	  	 System.out.println("Common_Not_Covered_Devices Sheet creating : " );
//	 	  HSSFSheet commonNotCoveredDevicesSheet = workbook.createSheet("Common_Not_Covered_Devices");
	 	  Sheet commonNotCoveredDevicesSheet = workbook.createSheet("Common_Not_Covered_Devices");
	   	List<Object>  notCoveredDevicesList =  contractExcelDAO.getQueryList(env.getProperty("commonNotCoveredDevicesinCMR"));
   	  
//	  	 ExcelContractsCompartorUtil.createMatchSheetbyQueryListValues(workbook, commonNotCoveredDevicesSheet, notCoveredDevicesList , env.getProperty("commonNotCoveredDevicesinCMRValues"));
	   	 ExcelContractsCompartorUtil.createMatchXSSSheetbyQueryListValues(workbook, commonNotCoveredDevicesSheet, notCoveredDevicesList , env.getProperty("commonNotCoveredDevicesinCMRValues"));
	  	 
	 	// get Missing Covered Devices in  2.x 
	  	 System.out.println("Missing_Not_Covered_Devices_SNTC Sheet creating : " );
//	 	  HSSFSheet missingNotCoveredDevicesinSNTCSheet = workbook.createSheet("Missing_Not_Covered_Devices_SNTC");
	 	  Sheet missingNotCoveredDevicesinSNTCSheet = workbook.createSheet("Missing_Not_Covered_Devices_SNTC");
	   	List<Object>  sntcMissingNotCoveredDevicesList =  contractExcelDAO.getQueryList(env.getProperty("missingNotCoveredDevicesinCMRSNTC"));
  	  
//	  	 ExcelContractsCompartorUtil.createExcelSheetwithObjectList(workbook, missingNotCoveredDevicesinSNTCSheet, sntcMissingNotCoveredDevicesList , env.getProperty("missingNotCoveredDevicesinCMRSNTCValues"));
	  	 ExcelContractsCompartorUtil.createExcelXSSSheetwithObjectList(workbook, missingNotCoveredDevicesinSNTCSheet, sntcMissingNotCoveredDevicesList , env.getProperty("missingNotCoveredDevicesinCMRSNTCValues"));
	  	 
	  	 
	  // get Extra Covered Devices in  2.x 
	  	 System.out.println("Extra_Not_Covered_Devices_SNTC Sheet creating : " );
//	 	  HSSFSheet extraNotCoveredDevicesinSNTCSheet = workbook.createSheet("Extra_Not_Covered_Devices_SNTC");
	 	  Sheet extraNotCoveredDevicesinSNTCSheet = workbook.createSheet("Extra_Not_Covered_Devices_SNTC");
	   	List<Object>  sntcExtraNotCoveredDevicesList =  contractExcelDAO.getQueryList(env.getProperty("extraNotCoveredDevicesinCMRSNTC"));
 	  
//	  	 ExcelContractsCompartorUtil.createExcelSheetwithObjectList(workbook, extraNotCoveredDevicesinSNTCSheet, sntcExtraNotCoveredDevicesList , env.getProperty("extraNotCoveredDevicesinCMRSNTCValues"));
	  	 ExcelContractsCompartorUtil.createExcelXSSSheetwithObjectList(workbook, extraNotCoveredDevicesinSNTCSheet, sntcExtraNotCoveredDevicesList , env.getProperty("extraNotCoveredDevicesinCMRSNTCValues"));
	  	 
        FileOutputStream out = 
                new FileOutputStream(new File("C:\\Data\\"+env.getProperty("CMROutPutFileName")+env.getProperty("CompanyName")+".xlsx"));
		        workbook.write(out);
		        out.close();
		        System.out.println("Excel written successfully..");
		        workbook.dispose();
		         
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
	/**
	 * This method is used to store all the IR CMR contracts related Sheets
	 * information into the database
	 
	private boolean storeIrExcel() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		
		try{
		//	FileInputStream irFile = new FileInputStream(new File(ExcelConstants.IR_CMR_FILE_NAME));
		//	XSSFWorkbook irWorkbook = new XSSFWorkbook(fis);
			logger.debug("TRYING TO OPEN THE FILE");
			OPCPackage IRpkg = OPCPackage.open(new File(ExcelConstants.IR_CMR_FILE_NAME));
			logger.debug("PACKAGE CREATED");
			XSSFWorkbook irWorkbook = new XSSFWorkbook(IRpkg);
//			XSSFReader r = new XSSFReader( IRpkg );
			logger.debug("READER CREATED");
//			SXSSFWorkbook newIRWorkBook = new SXSSFWorkbook(irWorkbook, 100);
			
			logger.debug("OPEN THE FILE");
			List<AllContractDTO> irAllContractsDTOList = ExcelContractsCompartorUtil.readExelFile(irWorkbook,ExcelConstants.All_CONTRACT_SHEET,
					ExcelConstants.IR_APP_NAME);
		
		   System.out.println(" total time taken for excel IR all contracts and DTO "+ (System.currentTimeMillis() - startTime) / 1000);

		boolean allContractFlag = contractExcelDAO.storeIRAllContractInfo(irAllContractsDTOList);
		System.out.println(" All contracts Data Saved to DB :"+ allContractFlag);

		
		// Reading the Covered items sheet
		
		List<CoveredDevicesDTO> irCoveredDevicesDTOList = ExcelContractsCompartorUtil.readIRCoveredDevices(irWorkbook);
		System.out.println(" total time taken for excel IR Covered and DTO "+ (System.currentTimeMillis() - startTime) / 1000);

		boolean coveredDevicesFlag = contractExcelDAO.storeIRCoveredDevicesInfo(irCoveredDevicesDTOList);
		System.out.println(" Covered Devices Data Saved to DB :"
				+ coveredDevicesFlag);
		
		// Reading the NOT Covered items sheet
		
				List<NotCoveredDevicesDTO> irNotCoveredDevicesDTOList = ExcelContractsCompartorUtil
						.readIRNotCoveredDevices(irWorkbook);
				System.out
						.println(" total time taken for excel IR Not Covered and DTO "
								+ (System.currentTimeMillis() - startTime) / 1000);

				boolean notCoveredDevicesFlag = contractExcelDAO
						.storeIRNotCoveredDevicesInfo(irNotCoveredDevicesDTOList);
				System.out.println(" Covered Devices Data Saved to DB :"
						+ notCoveredDevicesFlag);
		}catch(Exception e){
			
			e.printStackTrace();
		}
		finally{
			
		}
		
		return true;
	}
	 */
	/**
	 * This method is used to store all the IR CMR contracts related Sheets
	 * information into the database
	 
	private boolean storeSntcExcel() {
		long startTime = System.currentTimeMillis();
		try{
//			FileInputStream irFile = new FileInputStream(new File(ExcelConstants.SNTC_CMR_FILE_NAME));
			OPCPackage SNTCpkg = OPCPackage.open(new File(ExcelConstants.SNTC_CMR_FILE_NAME));
			XSSFWorkbook sntcWorkbook = new XSSFWorkbook(SNTCpkg);
//			XSSFWorkbook irWorkbook = new XSSFWorkbook(irFile);
			
			for(int i =0 ;i<=6 ;i++){
			System.out.println("sheet Names in Excel file" + sntcWorkbook.getSheetName(i) );
			}
		 List<AllContractDTO> sntcAllContractsDTOList = ExcelContractsCompartorUtil.readExelFile(sntcWorkbook,ExcelConstants.All_CONTRACT_SHEET,
						ExcelConstants.SNTC_APP_NAME);
		System.out.println(" total time taken for excel SNTC all contracts  and DTO "+ (System.currentTimeMillis() - startTime) / 1000);

		boolean allContractFlag = contractExcelDAO.storeSNTCAllContractInfo(sntcAllContractsDTOList);
		System.out.println(" All contracts Data Saved to DB :"+ allContractFlag);
		
		// Reading the Covered  sheet
		
		List<CoveredDevicesDTO> sntcCoveredDevicesDTOList = ExcelContractsCompartorUtil.readCoveredDevices(sntcWorkbook,ExcelConstants.COVERED_SHEET,
						ExcelConstants.SNTC_APP_NAME);
		System.out.println(" total time taken for excel SNTC Covered and DTO "+ (System.currentTimeMillis() - startTime) / 1000);

		boolean coveredDevicesFlag = contractExcelDAO.storeSNTCCoveredDevicesInfo(sntcCoveredDevicesDTOList);
		System.out.println(" Covered Devices Data Saved to DB :"
				+ coveredDevicesFlag);
		
		
		// Reading the  Not Covered  sheet
		
		List<NotCoveredDevicesDTO> sntcNotCoveredDevicesDTOList = ExcelContractsCompartorUtil.readNotCoveredDevices(sntcWorkbook,ExcelConstants.NOT_COVERED_SHEET,
								ExcelConstants.SNTC_APP_NAME);
				System.out.println(" total time taken for excel SNTC Not Covered  and DTO "+ (System.currentTimeMillis() - startTime) / 1000);

		boolean notCoveredDevicesFlag = contractExcelDAO.storeSNTCNotCoveredDevicesInfo(sntcNotCoveredDevicesDTOList);
				System.out.println(" All contracts Data Saved to DB :"+ notCoveredDevicesFlag);
				
//		irFile.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	 */
	/***
	 * Get all the differences and create final excel sheet for CMR related
	 * stuff..
	 */

	private boolean parseAndStoreIrExcel() {
		long startTime = System.currentTimeMillis();
		
		try{
			OPCPackage irPkg = OPCPackage.open(new File(ExcelConstants.IR_CMR_FILE_NAME));
			XSSFReader reader = new XSSFReader( irPkg );
			SharedStringsTable sst = reader.getSharedStringsTable();

			//Reading the All contracts sheet
			IRAllContractsSheetHandler irContractsSheetHandler = new IRAllContractsSheetHandler(sst);
			XMLReader excelXMLParser = irContractsSheetHandler.fetchSheetParser(sst);
			InputStream aSheetStream = reader.getSheet(ExcelConstants.All_CONTRACT_SHEET_RELATIONAL_ID);
			InputSource asheetSource = new InputSource(aSheetStream);
			
			excelXMLParser.parse(asheetSource);
			aSheetStream.close();
			List<AllContractDTO> irAllContractsDTOList = irContractsSheetHandler.getContractDTOList();
			logger.debug("TOTAL NUMBER OF DTOs: "+irAllContractsDTOList.size());
			logger.debug(" total time taken for excel IR all contracts and DTO "+ (System.currentTimeMillis() - startTime) / 1000);
			contractExcelDAO.storeIRAllContractInfo(irAllContractsDTOList);
//			logger.debug(" All contracts Data Saved to DB :"+ allContractFlag);

			// Reading the Covered items sheet is a combination of Covered sheet and Chassis Covered cards.
			IRCoveredItemsSheetHandler irCoveredItemsSheetHandler = new IRCoveredItemsSheetHandler(sst);
			excelXMLParser = irCoveredItemsSheetHandler.fetchSheetParser(sst);
			aSheetStream = reader.getSheet(ExcelConstants.COVERED_ITEMS_SHEET_RELATIONAL_ID);
			asheetSource = new InputSource(aSheetStream);
			excelXMLParser.parse(asheetSource);
			aSheetStream.close();
			List<CoveredDevicesDTO> irCoveredDevicesDTOList = irCoveredItemsSheetHandler.getCoveredDevicesDTOList();
			logger.debug("TOTAL NUMBER OF DTOs: "+irCoveredDevicesDTOList.size());
			
			IRChassisCoveredCardsSheetHandler irChassisCardsSheetHandler = new IRChassisCoveredCardsSheetHandler(sst);
			excelXMLParser = irChassisCardsSheetHandler.fetchSheetParser(sst);
			aSheetStream = reader.getSheet(ExcelConstants.CHASSIS_COVERED_CARDS_SHEET_RELATIONAL_ID);
			asheetSource = new InputSource(aSheetStream);
			excelXMLParser.parse(asheetSource);
			aSheetStream.close();
			irCoveredDevicesDTOList.addAll(irChassisCardsSheetHandler.getCoveredDevicesDTOList());
			logger.debug("TOTAL NUMBER OF DTOs: "+irCoveredDevicesDTOList.size());
			logger.debug(" total time taken for excel IR Covered and DTO "+ (System.currentTimeMillis() - startTime) / 1000);

			contractExcelDAO.storeIRCoveredDevicesInfo(irCoveredDevicesDTOList);
//			logger.debug("Covered Devices Data Saved to DB :"+ coveredDevicesFlag);
			 
			// 	Reading the NOT Covered items sheet
			IRNotCoveredSheetHandler irNotCoveredChassisCCMPhoneSheetHandler = new IRNotCoveredSheetHandler(sst);
			excelXMLParser = irNotCoveredChassisCCMPhoneSheetHandler.fetchSheetParser(sst);
			aSheetStream = reader.getSheet(ExcelConstants.NOT_COVERED_CHASSIS_CCM_PHONE_SHEET_RELATIONAL_ID);
			asheetSource = new InputSource(aSheetStream);
			excelXMLParser.parse(asheetSource);
			aSheetStream.close();
			List<NotCoveredDevicesDTO> irNotCoveredDevicesDTOList = irNotCoveredChassisCCMPhoneSheetHandler.getNotCoveredDevicesDTOList();
			logger.debug("TOTAL NUMBER OF DTOs: "+irNotCoveredDevicesDTOList.size());
			
			IRNotCoveredSheetHandler irNotCoveredSheetHandler = new IRNotCoveredSheetHandler(sst);
			excelXMLParser = irNotCoveredSheetHandler.fetchSheetParser(sst);
			aSheetStream = reader.getSheet(ExcelConstants.NOT_COVERED_CARDS_SHEET_RELATIONAL_ID);
			asheetSource = new InputSource(aSheetStream);
			excelXMLParser.parse(asheetSource);
			aSheetStream.close();
			irNotCoveredDevicesDTOList.addAll(irNotCoveredSheetHandler.getNotCoveredDevicesDTOList());
			logger.debug("TOTAL NUMBER OF DTOs: "+irNotCoveredDevicesDTOList.size());
			logger.debug(" total time taken for excel IR Covered and DTO "+ (System.currentTimeMillis() - startTime) / 1000);

			contractExcelDAO.storeIRNotCoveredDevicesInfo(irNotCoveredDevicesDTOList);
//			logger.debug("Covered Devices Data Saved to DB :"+ notCoveredDevicesFlag);
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		finally{
			
		}
		
		return true;
	}
	
	private boolean parseAndStoreSntcExcel() {
		long startTime = System.currentTimeMillis();
		try{
			OPCPackage sntcPkg = OPCPackage.open(new File(ExcelConstants.SNTC_CMR_FILE_NAME));
			XSSFReader reader = new XSSFReader( sntcPkg );
			SharedStringsTable sst = reader.getSharedStringsTable();
			
            // Reading All contracts sheets
			SNTCAllContractsSheetHandler sntcContractsSheetHandler = new SNTCAllContractsSheetHandler(sst);
			XMLReader excelXMLParser = sntcContractsSheetHandler.fetchSheetParser(sst);
			InputStream aSheetStream = reader.getSheet(ExcelConstants.SNTC_All_CONTRACT_SHEET_RELATIONAL_ID);
			InputSource asheetSource = new InputSource(aSheetStream);
		
			excelXMLParser.parse(asheetSource);
			aSheetStream.close();
			List<AllContractDTO> sntcAllContractsDTOList = sntcContractsSheetHandler.getContractDTOList();
			logger.debug("TOTAL NUMBER OF DTOs: "+sntcAllContractsDTOList.size());
			
//			List<AllContractDTO> sntcAllContractsDTOList = ExcelContractsCompartorUtil.readExelFile(sntcWorkbook,ExcelConstants.All_CONTRACT_SHEET,
//						ExcelConstants.SNTC_APP_NAME);
			logger.debug(" total time taken for excel SNTC all contracts  and DTO "+ (System.currentTimeMillis() - startTime) / 1000);

			boolean allContractFlag = contractExcelDAO.storeSNTCAllContractInfo(sntcAllContractsDTOList);
			logger.debug(" All contracts Data Saved to DB :"+ allContractFlag);
		
		// Reading the Covered  sheet
			SNTCCoveredItemsSheetHandler sntcCoveredItemsSheetHandler = new SNTCCoveredItemsSheetHandler(sst);
			excelXMLParser = sntcCoveredItemsSheetHandler.fetchSheetParser(sst);
			aSheetStream = reader.getSheet(ExcelConstants.COVERED_ITEMS_SHEET_RELATIONAL_ID);
			asheetSource = new InputSource(aSheetStream);
			
			excelXMLParser.parse(asheetSource);
			aSheetStream.close();
			List<CoveredDevicesDTO> sntcCoveredDevicesDTOList = sntcCoveredItemsSheetHandler.getCoveredDevicesDTOList();
			logger.debug("TOTAL NUMBER OF DTOs: "+sntcCoveredDevicesDTOList.size());
		
//		List<CoveredDevicesDTO> sntcCoveredDevicesDTOList = ExcelContractsCompartorUtil.readCoveredDevices(sntcWorkbook,ExcelConstants.COVERED_SHEET,
//						ExcelConstants.SNTC_APP_NAME);
			logger.debug(" total time taken for excel SNTC Covered and DTO "+ (System.currentTimeMillis() - startTime) / 1000);

			boolean coveredDevicesFlag = contractExcelDAO.storeSNTCCoveredDevicesInfo(sntcCoveredDevicesDTOList);
			logger.debug(" Covered Devices Data Saved to DB :"+ coveredDevicesFlag);
		
		// Reading the  Not Covered  sheet
			SNTCNotCoveredSheetHandler sntcNotCoveredSheetHandler = new SNTCNotCoveredSheetHandler(sst);
			excelXMLParser = sntcNotCoveredSheetHandler.fetchSheetParser(sst);
			aSheetStream = reader.getSheet(ExcelConstants.NOT_COVERED_RELATIONAL_ID);
			asheetSource = new InputSource(aSheetStream);
			
			excelXMLParser.parse(asheetSource);
			aSheetStream.close();
			List<NotCoveredDevicesDTO> sntcNotCoveredDevicesDTOList = sntcNotCoveredSheetHandler.getNotCoveredDevicesDTOList();
			logger.debug("TOTAL NUMBER OF DTOs: "+sntcNotCoveredDevicesDTOList.size());
//		List<NotCoveredDevicesDTO> sntcNotCoveredDevicesDTOList = ExcelContractsCompartorUtil.readNotCoveredDevices(sntcWorkbook,ExcelConstants.NOT_COVERED_SHEET,
//								ExcelConstants.SNTC_APP_NAME);
			logger.debug(" total time taken for excel SNTC Not Covered  and DTO "+ (System.currentTimeMillis() - startTime) / 1000);

			boolean notCoveredDevicesFlag = contractExcelDAO.storeSNTCNotCoveredDevicesInfo(sntcNotCoveredDevicesDTOList);
			logger.debug(" All contracts Data Saved to DB :"+ notCoveredDevicesFlag);
				
		} catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
}