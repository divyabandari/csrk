package com.cisco.excel.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.cisco.excel.dao.IRExcelDao;
import com.cisco.excel.util.ExcelConstants;
import com.cisco.excel.util.Util;

@Repository
public class IRExcelDaoImpl  implements IRExcelDao {
	
	@Autowired
	private SessionFactory irSessionFactory;
	
	private Session session;
	
	 @Autowired
	    private Environment env;

    public IRExcelDaoImpl() {
        super();

    }

   	@Override
	public Map<String,String> getVendorEquipmentType() {
		// TODO Auto-generated method stub
		
		String queryString = env.getProperty("irVendorEquipmentTypeQuery");
		
		try{
			 /*  List<String> serialNumList = new ArrayList<String>();
			   for(Object obj : cardlist){
				   
				   Object[] result = (Object[]) obj;
				   serialNumList.add(result[0].toString());
			   }*/
			
			
			session = irSessionFactory.openSession();
	    	session.beginTransaction();
			
	    	long startTime = System.currentTimeMillis();
	    	
	    	System.out.println( " Query value  is "+ queryString);
	    SQLQuery query=	session.createSQLQuery(queryString);
	    query.setParameter("collectionId", ExcelConstants.PRF_COLLECTION_OBJID);
	    
	//    query.setParameterList("serialNums", serialNumList);
 System.out.println(" complete time taken111111 for IR query "+ (System.currentTimeMillis()-startTime));
 
	long startTime1 = System.currentTimeMillis();
 
	   List<Object> lis=   query.list();
	   
	   System.out.println(" reading the list from hibernate queryObject is " +( System.currentTimeMillis()-startTime1));
	   long sessionTime = System.currentTimeMillis();
		
	   session.getTransaction().commit();
	
		  System.out.println( "Time taken for Session "+ (System.currentTimeMillis()-sessionTime));
	   Map<String,String> snVEPMap = new HashMap<String, String>();
	   
	   
	   for(Object obj : lis){
		   
		   Object[] result = (Object[]) obj;
		 /* System.out.println(" result[0] " + result[0].toString());
		 System.out.println(" result[1] " + result[1].toString());
		*/ 
	//	   if(result !=null & result[0]!=null & result[1]!=null){
		  // to get the exact serial number from Serial number strings 
		   snVEPMap.put(Util.VEPString(Util.nullCheck(result[0])), Util.nullCheck(result[1]));
		   
	//	   snVEPMap.put(result[0].toString(), result[1].toString());

		   
	//   }
	
		   
	   }
	   System.out.println(" complete time taken22222 for IR query "+ (System.currentTimeMillis()-startTime1));
	   	
	    
	    	
	   return snVEPMap;
		}catch(Exception e ){
			e.printStackTrace();
			
		}finally {
			session.close();
			
		}
		return null;
		
	}
   	
   	@Override
	public List<Object> getVendorEquipmentTypeBySerialNumber(List<Object> serialNumberList) {
		// TODO Auto-generated method stub
		
		String queryString = env.getProperty("irVendorEquipmentTypeQueryBySerilNum");
		
		try{
			session = irSessionFactory.openSession();
	    	session.beginTransaction();
	    	
	    	List<Object> newObjectList = new ArrayList<Object>();
	    	
	    	 for(Object obj : serialNumberList){
	    		  Object[] result = (Object[]) obj;
	    		 
	    		 Object [] newObjArray = new Object [6];
	    		 
	    		 newObjArray[0] = result[0];
	    		 newObjArray[1] = result[1];
	    		 newObjArray[2] = result[2];
	    		 newObjArray[3] = result[3];
	    		 newObjArray[4] = result[4];
	    		 newObjArray[5] = result[5];
	    		 
	  		    if(result!=null && result[0]!=null) {
	  		   String collectedSerialNum = result[0].toString();
	  		 System.out.println( " Query value  is "+ queryString);
	 	    SQLQuery query=	session.createSQLQuery(queryString);
	 	        query.setParameter("serialNum",collectedSerialNum);
	 	        
	 	        System.out.println(" print the query "+ query.toString());
	 	        System.out.println( "print the serial num "+ collectedSerialNum);
	 	        
	 	   List<Object> lis=   query.list();
	 	  if(lis!=null && lis.size()>0){
	 //	  Object[] resultVEP = (Object[]) lis.get(0);	  
	 	 newObjArray[6] =  ((Object[]) lis.get(0))[1];
	 	  }
	 	  
	  		    }
	  		   
	  		  newObjectList.add(newObjArray);
	    	}
	    	 
	   return newObjectList;
		}catch(Exception e ){
			e.printStackTrace();
			
		}finally {
			session.close();
			
		}
		return null;
		
	}

}
