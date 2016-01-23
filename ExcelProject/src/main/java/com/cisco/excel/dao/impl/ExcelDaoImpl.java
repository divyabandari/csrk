package com.cisco.excel.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.cisco.excel.dao.ExcelDao;
import com.cisco.excel.dao.IRExcelDao;
import com.cisco.excel.dto.InventoryDTO;
import com.cisco.excel.dto.InventoryOtherSheetsDTO;
import com.cisco.excel.model.ExcelIrReportedCard;
import com.cisco.excel.model.ExcelIrReportedChassi;
import com.cisco.excel.model.ExcelSntcReportedCard;
import com.cisco.excel.model.ExcelSntcReportedChassi;
import com.cisco.excel.model.InventoryOtherSheets;
import com.cisco.excel.util.Util;

@Repository
public class ExcelDaoImpl  implements ExcelDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	 @Autowired
	    private Environment env;
	 
	 @Autowired
	 private IRExcelDao irExcelDao;
	 
	 protected static final Logger logger = LoggerFactory.getLogger(ExcelDaoImpl.class);

    public ExcelDaoImpl() {
        super();

    }

    public boolean storeIrExelChassis( List<InventoryDTO> lisInventoryDTO){
    	
    	logger.debug(" in DAO impl class ");
    	long startTime = System.currentTimeMillis();
    	
    	try{
    		int batchCounter = 0;
    		deleteInventorySheetFromDB("delete from ExcelIrReportedChassi");
    		  	session = sessionFactory.openSession();
        	session.beginTransaction();
   
        
    	for(InventoryDTO inventoryDTO : lisInventoryDTO) {
		    	ExcelIrReportedChassi irReportedChassi = new ExcelIrReportedChassi();
		    	
		    	irReportedChassi.setHostname(inventoryDTO.getHostName());
		    	irReportedChassi.setIpaddress(inventoryDTO.getIpAddress());
		    	irReportedChassi.setCollectedserialnum(inventoryDTO.getCollectedSerialNumber());
		    	irReportedChassi.setCollectedpid(inventoryDTO.getPID());
		    	irReportedChassi.setSerialnum(inventoryDTO.getSerialNumber());
		       	irReportedChassi.setPid(inventoryDTO.getPID());
		    	irReportedChassi.setEquipmenttype(inventoryDTO.getEquipmentType());
		    	irReportedChassi.setProductfamily(inventoryDTO.getProductFamily());
		    
		    	session.persist(irReportedChassi);


	           if(batchCounter%500==0){
	        	   session.flush();
	        	   session.clear();
	           }
	    	
           batchCounter ++;
    	}
    	session.getTransaction().commit();
    	logger.debug(" IR Chassis are successfully saved ");
    	logger.debug(" Total time taken for saving the data" + ( System.currentTimeMillis()-startTime));
    	return true;
    	}catch(Exception e ){
    		logger.debug(" Exception while  saving  IR Chassis data");
    		e.printStackTrace();
    		return false;
    		
    	}
    	finally {
    		
    		session.close();
    	}
    	
    	
    }
    // API
    
 public boolean storeIrExelCards(List<InventoryDTO> lisInventoryDTO){
    	
    	logger.debug(" in DAO impl class IR Excel Cards");
    	long startTime = System.currentTimeMillis();
    	int batchCounter = 0;
    	try{
    		deleteInventorySheetFromDB("delete from ExcelIrReportedCard");
    		 	session = sessionFactory.openSession();
        	session.beginTransaction();
        
   
    	for(InventoryDTO inventoryDTO : lisInventoryDTO) {
    	ExcelIrReportedCard irReportedCard = new ExcelIrReportedCard();
    	
    	irReportedCard.setHostname(inventoryDTO.getHostName());
    	irReportedCard.setIpaddress(inventoryDTO.getIpAddress());
    	irReportedCard.setCollectedserialnum(inventoryDTO.getCollectedSerialNumber());
    	irReportedCard.setCollectedpid(inventoryDTO.getPID());
    	irReportedCard.setSerialnum(inventoryDTO.getSerialNumber());
    	irReportedCard.setPid(inventoryDTO.getPID());
    	irReportedCard.setEquipmenttype(inventoryDTO.getEquipmentType());
    	irReportedCard.setProductfamily(inventoryDTO.getProductFamily());
    
    	session.persist(irReportedCard);

           if(batchCounter%500==0){
        	   session.flush();
        	   session.clear();
           }
    	batchCounter++;
    	
    	}
    	  session.getTransaction().commit();
    	logger.debug(" IR Cards successfully saved ");
    	logger.debug(" total time taken for saving the IR Cards data" + ( System.currentTimeMillis()-startTime));
    	return true;
    	}
    	
    
    	catch(Exception e ){
    		logger.debug(" print device batchCounter "+batchCounter);
    		logger.debug(" Exception while  saving data");
    		e.printStackTrace();
    		return false;
    		
    	}
    	finally {
    		
    		
    		session.close();
    	}
    	
    	
    }

	public boolean storeSntcExelChassis( List<InventoryDTO> lisInventoryDTO) {
    	
    	logger.debug(" IN storeSntcExelChassis METHOD: ");
    	
	    	try{
		    	deleteInventorySheetFromDB("delete from ExcelSntcReportedChassi");
		    	
		    	int batchCounter = 0;
		    	session = sessionFactory.openSession();
		    	session.beginTransaction();
		    	 
		    	for(InventoryDTO inventoryDTO : lisInventoryDTO) {
		    		ExcelSntcReportedChassi sntcReportedChassi = new ExcelSntcReportedChassi();
		    	
			    	sntcReportedChassi.setHostname(inventoryDTO.getHostName());
			    	sntcReportedChassi.setIpaddress(inventoryDTO.getIpAddress());
			    	sntcReportedChassi.setCollectedserialnum(inventoryDTO.getCollectedSerialNumber());
			    	sntcReportedChassi.setCollectedpid(inventoryDTO.getPID());
			    	sntcReportedChassi.setSerialnum(inventoryDTO.getSerialNumber());
			    	sntcReportedChassi.setPid(inventoryDTO.getPID());
			    	sntcReportedChassi.setEquipmenttype(inventoryDTO.getEquipmentType());
			    	sntcReportedChassi.setProductfamily(inventoryDTO.getProductFamily());
		    	
			    	session.persist(sntcReportedChassi);
		    	
			           if(batchCounter%500==0){
			        	   session.flush();
			        	   session.clear();
			           }
			         batchCounter++;
		    	}
		    	 session.getTransaction().commit();
		    	logger.debug(" SNTC Chassis are successfully saved");
		    	
		    	return true;
	    	}catch(Exception e ){
    		logger.debug(" Exception while  saving SNTC Chassis data");
    		e.printStackTrace();
    		return false;
    		
    	}
    	finally {
    		
    		
    	session.close();
    	}
    	
    	
    }

	public boolean storeSntcExelCards(List<InventoryDTO> lisInventoryDTO) {
    	
    	logger.debug(" in DAO impl class ");
    	
    	
    	try{
    		
    		int batchCounter = 0;
    			session = sessionFactory.openSession();
        	session.beginTransaction();
       
    	for(InventoryDTO inventoryDTO : lisInventoryDTO) {
    	ExcelSntcReportedCard sntcReportedCard = new ExcelSntcReportedCard();
    	
    	sntcReportedCard.setHostname(inventoryDTO.getHostName());
    	sntcReportedCard.setIpaddress(inventoryDTO.getIpAddress());
    	sntcReportedCard.setCollectedserialnum(inventoryDTO.getCollectedSerialNumber());
    	sntcReportedCard.setCollectedpid(inventoryDTO.getPID());
    	sntcReportedCard.setSerialnum(inventoryDTO.getSerialNumber());
    	sntcReportedCard.setPid(inventoryDTO.getPID());
    	sntcReportedCard.setEquipmenttype(inventoryDTO.getEquipmentType());
    	sntcReportedCard.setProductfamily(inventoryDTO.getProductFamily());
    
    	session.persist(sntcReportedCard);
    	
           if(batchCounter%500==0){
        	   
        	   session.flush();
        	   session.clear();
           }
    	
           batchCounter++;
    	
    	}
      session.getTransaction().commit();
    	logger.debug(" Successfully saved SNTC cards data");
    	
    	return true;
    	}
    	
    
    	catch(Exception e ){
    		logger.debug(" Exception while  saving SNTC Cards data");
    		e.printStackTrace();
    		return false;
    		
    	}
    	finally {
    		
    		
    		session.close();
    	}
    	
    	
    }
	@Override
	public List<Object> getQUeryList(String queryStringProp) {
		// TODO Auto-generated method stub
		
		try{
			session = sessionFactory.openSession();
	    	session.beginTransaction();
	    	String queryString = env.getProperty(queryStringProp);
	    	logger.debug( " Query value  is "+ queryString);
	    	SQLQuery query=	session.createSQLQuery(queryString);
	    
	    	List<Object> lis=   query.list();
	    
	    	session.getTransaction().commit();
	    	
	    	return lis;
		}catch(Exception e ){
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}finally {
			session.close();
			
		}
		return null;
	}
	
	
	@Override
	public List<Object> getQUeryListWithIRVEP(String queryStringProp) {
		// TODO Auto-generated method stub
		
		try{
			session = sessionFactory.openSession();
	    	session.beginTransaction();
			
	    	String queryString = env.getProperty(queryStringProp);
	    	logger.debug( " Query value  is "+ queryString);
	    SQLQuery query=	session.createSQLQuery(queryString);
	   List<Object> lis=   query.list();
	   
	 session.getTransaction().commit();
	    	
	    return lis;
		}catch(Exception e ){
			e.printStackTrace();
			
		}finally {
			session.close();
			
		}
		return null;
	}

	@Override
	public List<Object> getIRCardExtraSerialNumbers() {
		
		try{
			session = sessionFactory.openSession();
	    	session.beginTransaction();
			
	    //	long startTime = System.currentTimeMillis();
	    	
	    	String queryString = env.getProperty("irExtraCardsCollectedSerialNumWithFullDetails");
	    	logger.debug( " Query value  is "+ queryString);
	    SQLQuery query=	session.createSQLQuery(queryString);
	    
	                        
	   List<Object> lis=   query.list();

	   return lis;
	
		}catch(Exception e ){
			e.printStackTrace();
			
		}finally {
		  	session.getTransaction().commit();
			session.close();
			
		}
		return null;
	}
	
	/**
	 * Deleting the existing SNTC All Contracts information  from the Database 
	 * @return
	 */
	public boolean deleteInventorySheetFromDB(String queryString) {
		
	    logger.debug(" in deleteInventorySheetFromDB method ");

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session
					.createQuery(queryString);

			int deletedCount = query.executeUpdate();
			logger.debug(" printing the deleteCount value "
					+ deletedCount);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			session.getTransaction().commit();
			session.close();
		}

		return true;

	}
	
	public boolean storeSntcOtherSheets(List<InventoryOtherSheetsDTO> inventoryOthersheetDTOList) {
    	
    	logger.debug(" in DAO impl class ");
    	
    	
    	try{
    		deleteInventorySheetFromDB("delete from InventoryOtherSheets");
    		int batchCounter = 0;
    			session = sessionFactory.openSession();
        	session.beginTransaction();
       
    	for(InventoryOtherSheetsDTO inventoryOtherSheetDTO : inventoryOthersheetDTOList) {
    	InventoryOtherSheets inventoryOtherSheet = new InventoryOtherSheets();
    	
    	inventoryOtherSheet.setHostname(inventoryOtherSheetDTO.getHostName());
    	inventoryOtherSheet.setIpaddress(inventoryOtherSheetDTO.getIpAddress());
    	inventoryOtherSheet.setCollectedserialnum(inventoryOtherSheetDTO.getCollectedSerialNumber());
    	inventoryOtherSheet.setCollectedpid(inventoryOtherSheetDTO.getCollectedPID());
 
    	inventoryOtherSheet.setEquipmenttype(inventoryOtherSheetDTO.getEquipementType());
    	inventoryOtherSheet.setReportsheet(inventoryOtherSheetDTO.getReportSheetName());
    
   	session.persist(inventoryOtherSheet);
    	
           if(batchCounter%500==0){
        	   session.flush();
        	   session.clear();
           }
    	
           batchCounter++;
    	
    	}
      session.getTransaction().commit();
    	logger.debug(" SNTC other sheets are successfully saved ");
    	
    	return true;
    	}
    	
    
    	catch(Exception e ){
    		logger.debug(" Exception while  saving the SNTC other sheet data");
    		e.printStackTrace();
    		return false;
    		
    	}
    	finally {
    		
    		
    		session.close();
    	}
    	
    	
    }
	
	@Override
	public boolean getQUeryListSheetBulk(String queryStringProp,HSSFWorkbook workbook,  HSSFSheet sheet) {
		 boolean returnresult=false;
		try{
			session = sessionFactory.openSession();
	    	session.beginTransaction();
	    	String queryString = env.getProperty(queryStringProp);
	    	logger.debug( " Query value  is "+ queryString);
	    	SQLQuery query=	session.createSQLQuery(queryString);
	    	query.setReadOnly(true);
	    	query.setFetchSize(10);
	    	ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
	    	 int rownum = 2;     // template creates the first row (header) so the number starts from 2.
	    	 Row row;
	    	 //counter
  	    	 int count = 0;
	    	// iterate over results
	        while (results.next()) {
	  	    	  row = sheet.createRow(rownum++);
	  	    	  
  	    	   if (++count > 0 && count % 1000 == 0) {
	  	    	    logger.debug("Fetched " + count + " entities");
	  	    	   }

  	    	 Object[] result = (Object[]) results.get();
			   
			 if ( result.length>0) { 
		    
			try{
			   
			 if(result[0]!=null)
			    row.createCell(1).setCellValue(result[0].toString());
			 else
				row.createCell(1).setCellValue("");
			 
			 if(result[1]!=null)
	 		    row.createCell(2).setCellValue(result[1].toString());
			 else{
				row.createCell(2).setCellValue("");
				result[1]="";
			 }
			 
			 if(result[2]!=null)
	        	 row.createCell(3).setCellValue(result[2].toString());
			 else{
				 row.createCell(3).setCellValue("");
				 result[2]="";
			 }
			 
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
			 
			 String stringArray1[] = Util.getHostDiff(result[3].toString(), result[4].toString()); // passing IR and SNTC host names to get the diff
		 		
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
		 	
		 	 if(stringArray1[0]!=null)
		            row.createCell(26).setCellValue(stringArray1[0].toString());
				 else
					row.createCell(26).setCellValue("");
				 
				 if(stringArray1[1]!=null)
		    	   row.createCell(27).setCellValue(stringArray1[1].toString());
				 else
				   row.createCell(27).setCellValue("");
				 
				 if(stringArray1[2]!=null)
				   row.createCell(28).setCellValue(stringArray1[2].toString());
				 else
				   row.createCell(28).setCellValue("");	 
	
				
			}catch(NullPointerException exp){
				logger.debug("NULL POINTER EXCEPTION FOR ONE RECORD: "+(new Integer(count)));
			}
			   
		   }
	  		session.flush();
	  		session.clear();
	        }
	        results.close();
	    
	    	session.getTransaction().commit();
	    	returnresult=true;
	    	
		}catch(Exception e ){
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}finally {
			session.close();
			
		}
		return returnresult;
	}
	
	@Override
	public boolean getQUeryListBulk(String queryStringProp, Map<String,String> snMap, HSSFWorkbook workbook,  HSSFSheet sheet) {
		 boolean returnresult=false;
		try{
			session = sessionFactory.openSession();
	    	session.beginTransaction();
	    	String queryString = env.getProperty(queryStringProp);
	    	logger.debug( " Query value  is "+ queryString);
	    	SQLQuery query=	session.createSQLQuery(queryString);
	    	query.setReadOnly(true);
	    	query.setFetchSize(10);
	    	ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
	    	 int rownum = 2;     // template creates the first row (header) so the number starts from 2.
	    	 Row row;
	    	 
	    	 //counter
	    	 int count=0;
	    	// iterate over results
	        while (results.next()) {
	  	    	  row = sheet.createRow(rownum++);
	  	   
	  	    	  if (++count > 0 && count % 1000 == 0) {
		  	    	    logger.debug("Fetched " + count + " entities");
		  	    	   }
	  	    	  
	  		 Object[] result = (Object[]) results.get();
	  		   
	  		 for ( int i=0; i< result.length ;i++) {   
	  			if(result[i]!=null)
	  				row.createCell(i+1).setCellValue(result[i].toString());
	  			else
	  				row.createCell(i+1).setCellValue("");
	  		 }
	  		 if(snMap!=null) row.createCell(9).setCellValue(snMap.get(result[0].toString()));
	  		 
	  		session.flush();
	  		session.clear();
	        }
	        results.close();
	    
	    	session.getTransaction().commit();
	    	returnresult=true;
	    	
		}catch(Exception e ){
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}finally {
			session.close();
			
		}
		return returnresult;
	}
   }


