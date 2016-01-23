package com.cisco.excel.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cisco.excel.dao.ExcelDao;
import com.cisco.excel.dao.IRExcelDao;
import com.cisco.excel.dto.InventoryDTO;
import com.cisco.excel.dto.InventoryOtherSheetsDTO;
import com.cisco.excel.service.ExcelService;
import com.cisco.excel.util.ExcelCompartorUtil;
import com.cisco.excel.util.ExcelConstants;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelDao excelDAO;
    
    @Autowired
    private IRExcelDao  irExcelDAO;
    
    @Autowired
	private Environment env;
    
    protected static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
   

    public ExcelServiceImpl() {
        super();
    }
    
    @Override
    public boolean storeInventoryExcel(){
    	
        storeIrExcel(); // Storing IR Inventory sheets into DB
        
        storeSntcExcel(); // Storing SNTC Inventory sheets into DB
           
        
    	return true;
    }
    

	@Override
	public boolean getInventoryDifferences() {
		try {
			
			//Get the vendor equiment map populated
	    	 long startTime = System.currentTimeMillis();
	    	 Map<String,String> snMap = 	irExcelDAO.getVendorEquipmentType();
	    	 // missingChassisList =  irExcelDAO.getVendorEquipmentTypeBySerialNumber(missingChassisList); // adding IR VEP
        	 System.out.println(" Time taken for Chassis IR VEP query  " + (System.currentTimeMillis()-startTime)/1000);
	    	  
        	 HSSFWorkbook workbook = new HSSFWorkbook();
			
			 // Chassis Host and other attributes differences
        	 HSSFSheet chassisHostDiffSheet = workbook.createSheet("Chassis_Host_Diff");
        	 //	    	  List<Object> chassisObjectLis = excelDAO.getQUeryList("chassiHostNamediffNew");
	    	 ExcelCompartorUtil.createSheetByRowTemplate(workbook, chassisHostDiffSheet);
	    	 excelDAO.getQUeryListSheetBulk("chassiHostNamediffNew",workbook, chassisHostDiffSheet);
	    	 //	    	  ExcelCompartorUtil.createSheet(workbook, chassisHostDiffSheet, chassisObjectLis);
	    	 System.out.println("Completed the Chassis Host Sheet");
	    	 	 
			 
	    	 // Missing Chssis in SNTC which are even in other sheets 
	    	 HSSFSheet sntcMissingChassis = workbook.createSheet("SNTC_Missing_Chssis"); 
	    	 ExcelCompartorUtil.createMissingItemsTemplate(workbook, sntcMissingChassis);  
	    	 excelDAO.getQUeryListBulk("sntcMissingChassis",snMap, workbook, sntcMissingChassis);
	    	 //	    	  ExcelCompartorUtil.createMissingItemsSheet(workbook, sntcMissingChassis, missingChassisList ,snMap);
	    	 System.out.println("Completed the Missing Chassis Sheet");
	    	 
	    	 // Not Reported Chassis in SNTC and present in other sheets
	    	 HSSFSheet sntcMissingChassisReportedinOthers = workbook.createSheet("SNTC_Chassis_in_OtherSheets");
	    	 ExcelCompartorUtil.createMissingItemsReportedInOtherSheetTemplate(workbook, sntcMissingChassisReportedinOthers);
	    	 excelDAO.getQUeryListBulk("sntcMissingChassisReportedinOthers",null, workbook, sntcMissingChassisReportedinOthers);
	    	 //	    	 List<Object> missingChassisReportedinOthersList = excelDAO.getQUeryList("sntcMissingChassisReportedinOthers");
	    	 //	    	 ExcelCompartorUtil.createMissingItemsReportedInOtherSheet(workbook, sntcMissingChassisReportedinOthers, missingChassisReportedinOthersList);
	    	 System.out.println("Completed the Not reported in SNTC Chassis Sheet");

	    	 // Cards Host and other attributes differences
    		 HSSFSheet cardsHostDiffSheet = workbook.createSheet("Cards_Host_Diff");
	    	 ExcelCompartorUtil.createSheetByRowTemplate(workbook, cardsHostDiffSheet);
	    	 excelDAO.getQUeryListSheetBulk("cardsHostNamediffNew",workbook, cardsHostDiffSheet);
	    	 //    		 List<Object> cardsObjectLis = excelDAO.getQUeryList("cardsHostNamediffNew");
	    	 //		     ExcelCompartorUtil.createSheet(workbook, cardsHostDiffSheet, cardsObjectLis);
	    	 System.out.println("Completed the Cards Host Sheet");
		    	 	 
		    	 	 
		     // Missing Cards in SNTC which are even in other sheets 
			 HSSFSheet sntcMissingCards = workbook.createSheet("SNTC_Missing_Cards");
	    	 ExcelCompartorUtil.createMissingItemsTemplate(workbook, sntcMissingCards);  
	    	 excelDAO.getQUeryListBulk("sntcMissingCards",snMap, workbook, sntcMissingCards);
	    	 //			 List<Object> missingCardsList = excelDAO.getQUeryList("sntcMissingCards");
	    	 //         	 ExcelCompartorUtil.createMissingItemsSheet(workbook, sntcMissingCards, missingCardsList,snMap);
	    	 System.out.println("Completed the Missing Cards Sheet");
			    	 
	    	 // Not Reported Cards in SNTC and present in other sheets
			 HSSFSheet sntcMissingCardsReportedinOthers = workbook.createSheet("SNTC_Cards_in_OtherSheets");
	    	 ExcelCompartorUtil.createMissingItemsReportedInOtherSheetTemplate(workbook, sntcMissingCardsReportedinOthers);
	    	 excelDAO.getQUeryListBulk("sntcMissingCardsReportedinOthers",null, workbook, sntcMissingCardsReportedinOthers);
	    	 System.out.println("Completed the Not reported Cards in SNTC Sheet");
	    	 
	    	 //			 List<Object> missingCardsReportedinOthersList = excelDAO.getQUeryList("sntcMissingCardsReportedinOthers");
	    	 //			 ExcelCompartorUtil.createMissingItemsReportedInOtherSheet(workbook, sntcMissingCardsReportedinOthers, missingCardsReportedinOthersList);
			    	  
			 // reading the summary and writing into Summary sheet in the workbook
			 ExcelCompartorUtil.readExelFileSummary("", workbook);	    	 	 
	    
		     FileOutputStream out = 
		           new FileOutputStream(new File("C:\\Data\\"+env.getProperty("InventoryOutPutFileName")+env.getProperty("CompanyName")+".xls"));
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
	
	
	
    /**
     * Store the IR Excel sheet for the chassis and cards
     */
        private boolean storeIrExcel() {
		// TODO Auto-generated method stub
        	
        	try{
        	FileInputStream irFile = new FileInputStream(new File(ExcelConstants.IR_FILE_NAME));
			// Get the workbook instance for XLS file
			XSSFWorkbook irWorkbook = new XSSFWorkbook(irFile);
			System.out.println("Reading the IR Chassis Sheet");
		List<InventoryDTO> chassisLisInventoryDTO =  ExcelCompartorUtil.readExelFile(irWorkbook, ExcelConstants.REPORTED_CHASSIS_SHEET);

        boolean chassisFlag=		excelDAO.storeIrExelChassis(chassisLisInventoryDTO);
		System.out.println("Reading the IR Cards Sheet");

	    List<InventoryDTO> CardsLisInventoryDTO =  ExcelCompartorUtil.readExelFile(irWorkbook, ExcelConstants.REPORTED_CARD_SHEET);
		
        boolean cardsFlag=	 excelDAO.storeIrExelCards(CardsLisInventoryDTO);
		 
		 if (chassisFlag&cardsFlag)
			 System.out.println(" IR Chassis and Cards are inserted successfully in the DB");
		 irFile.close();

        	}catch(Exception e ){
        		e.printStackTrace();
        		
        	
        	}
        	
		 return true;
	}
/**
 * Store the SNTC Excel sheet for the chassis and cards
 */
	private boolean storeSntcExcel() {
		// TODO Auto-generated method stub
		try{
			FileInputStream sntcFile = new FileInputStream(new File(ExcelConstants.SNTC_FILE_NAME));
			// Get the workbook instance for XLS file
			XSSFWorkbook irWorkbook = new XSSFWorkbook(sntcFile);
			logger.debug("Reading the SNTC Chassis Sheet");
			List<InventoryDTO> chassisLisInventoryDTO =  ExcelCompartorUtil.readExelFile(irWorkbook, ExcelConstants.REPORTED_CHASSIS_SHEET);
			logger.debug("TOTAL Chassis records: "+chassisLisInventoryDTO.size());
			
			boolean chassisFlag=	excelDAO.storeSntcExelChassis(chassisLisInventoryDTO);
			
			logger.debug("Reading the SNTC Cards Sheet");
			List<InventoryDTO> moduleLisInventoryDTO =  ExcelCompartorUtil.readExelFile(irWorkbook, ExcelConstants.REPORTED_MODULE_SHEET);
			logger.debug("Total Card records: "+moduleLisInventoryDTO.size());
			// delete the existing data.
			excelDAO.deleteInventorySheetFromDB("delete from ExcelSntcReportedCard");
			boolean cardsFlag=	 excelDAO.storeSntcExelCards(moduleLisInventoryDTO);
			List<InventoryDTO> fanLisInventoryDTO =  ExcelCompartorUtil.readExelFile(irWorkbook, ExcelConstants.REPORTED_FAN);
			logger.debug("Total Card records: "+fanLisInventoryDTO.size());
			cardsFlag=	 excelDAO.storeSntcExelCards(fanLisInventoryDTO);
			List<InventoryDTO> psLisInventoryDTO =  ExcelCompartorUtil.readExelFile(irWorkbook, ExcelConstants.REPORTED_POWER_SUPPLY);
			logger.debug("Total Card records: "+psLisInventoryDTO.size());
			cardsFlag=	 excelDAO.storeSntcExelCards(psLisInventoryDTO);
		 
			logger.debug("Reading the SNTC Other Sheet");

			List<InventoryOtherSheetsDTO>  inventoryOtherSheetsDTOList = ExcelCompartorUtil.readInventoryOtherSheets(irWorkbook);
		 
			boolean inventoryOtherSheerFlag = excelDAO.storeSntcOtherSheets(inventoryOtherSheetsDTOList);
		 
			if (chassisFlag&cardsFlag&inventoryOtherSheerFlag)
			 System.out.println(" SNTC Chassis and Cards are inserted successfully in the DB");
		 
			sntcFile.close();
		}catch(Exception e ){
    		e.printStackTrace();
    		
        	
    	}
		return true;
		
	}
/***
 * Get all the differences and create final excel sheet 
 */
	
	private void getHostNameDiff() {
		
		List<Object> chassisObjectLis = excelDAO.getQUeryList("chassisHostNamediff");
		
		List<Object> cardObjectLis = excelDAO.getQUeryList("cardHostNamediff");
		
	
		List<Object> list = excelDAO.getIRCardExtraSerialNumbers();
		
		Map<String,String> snMap = 	irExcelDAO.getVendorEquipmentType( );
		
	//	boolean flag = ExcelCompartorUtil.vepWirteExcelFile(snMap, list);
		//passing all the parameters.. for all the sheets which we will be generating..
		ExcelCompartorUtil.wirteExcelFile(chassisObjectLis, "dummy", "dummy", cardObjectLis,snMap, list);
		
		
		
		// TODO Auto-generated method stub
		
	}
	
	
	

	
	private void getVendorEquipmentType() {
		// TODO Auto-generated method stub
//		Map<String,String> snMap = 	irExcelDAO.getVendorEquipmentType();
		List<Object> list = excelDAO.getIRCardExtraSerialNumbers();
		
	//\	boolean flag = ExcelCompartorUtil.vepWirteExcelFile(snMap, list);
	//	System.out.println(" Flag :" + flag);
			
	}


    // API
 

}
