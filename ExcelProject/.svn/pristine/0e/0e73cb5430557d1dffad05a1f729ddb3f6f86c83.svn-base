package com.cisco.excel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.cisco.excel.service.ContractExcelService;
import com.cisco.excel.service.ExcelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
@SuppressWarnings("unchecked")
public class ExcelTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SessionFactory irSessionFactory;
	
	private Session session;
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private ContractExcelService contractExcelService;

	
	@Before
	public void before() {
		session = sessionFactory.openSession();

		session.beginTransaction();

		/*
		 * final FooFixtures fooData = new FooFixtures(sessionFactory);
		 * fooData.createBars();
		 */
	}

	@After
	public void after() {
		session.getTransaction().commit();
		session.close();
	}

/*	@Test
	public final void createTable() {
		final String hql = "create table arjuntestdummy (name varchar(100)) ";
		final Query query = session.createSQLQuery(hql);
		try {
			final int i = query.executeUpdate();
			System.out.println(" created successfully " + i);
		} catch (final Exception e) {

			System.out.println(" pringing exception ");
			e.printStackTrace();
		}
	}
	

	@Test
	public final void storeIrExceltest(){
		
		System.out.println( " Reading the excel files and storing in to DB ");
		excelService.storeIrExcel(session);
		
		
	}	
	

	@Test
	public final void storeSntcExceltest(){
		System.out.println( " Tes2");
		
		excelService.storeSntcExcel(session);
		
	}
	
	

	@Test
	public final void getHostNameDiffBySerialNum(){
		System.out.println( " getHostNameDiffBySerialNum Test");
		
		excelService.getHostNameDiff();
		
	}
	
	*/
	
	
	
	/*
	
/*	@Test
	public final void getVendorType(){
		System.out.println( " getHostNameDiffBySerialNum Test");
		
		excelService.getVendorEquipmentType();
		
		
	}*/
	
//******************************************************************
	
     //  ******************* INVENTORY REPORT **********************
	
//******************************************************************
	
	/**
	 * This test method to store all the Inventory(IIR and ISR) related data into data base for IR and SNTC
	 */
	@Test
	public final void storeInventoryExcel(){
		System.out.println(" *************************** START ************************");
		long startTime = System.currentTimeMillis();
		System.out.println( " Storting Inventory Excel sheet Data  Test");
		
		excelService.storeInventoryExcel();
		
		System.out.println(" Total time taken to store the Inventory Excel sheet :" + (System.currentTimeMillis()-startTime)/1000 +"Sec");
		
		System.out.println(" *************************** END ************************");

		
	}
	
	

	/**
	 * This test method to store all the Inventory related data into data base for IR and SNTC
	 */
	@Test
	public final void getInventoryDifferences(){
		System.out.println(" *************************** START ************************");
		long startTime = System.currentTimeMillis();
		System.out.println( " getInventoryDifferences Test");
		
		excelService.getInventoryDifferences();
		
		System.out.println(" Total time taken for the test :" + (System.currentTimeMillis()-startTime)/1000);
		
		System.out.println(" *************************** END ************************");

		
	}
	
//******************************************************************
	
    //  ******************* CMR REPORT **********************
	
//******************************************************************
	/**
	 * This test method to store all the CMR related data into data base for IR and SNTC
	 */
	@Test
	public final void storeContractExcelsheets(){
		System.out.println(" *************************** START ************************");
		long startTime = System.currentTimeMillis();
		System.out.println( " storeContractExcelsheets Test");
		
		contractExcelService.storeCMRAllContractsSheet();
		
		System.out.println(" Total time taken for the test :" + (System.currentTimeMillis()-startTime)/1000);
		
		System.out.println(" *************************** END ************************");

		
	}
	

	/**
	 * This test method to store all the CMR related data into data base for IR and SNTC
	 */
	@Test
	public final void getCMRDifferences(){
		System.out.println(" *************************** START ************************");
		long startTime = System.currentTimeMillis();
		System.out.println( " getCMRDifferences Test");
		
		contractExcelService.getCMRDifferences();
		
		System.out.println(" Total time taken for the test :" + (System.currentTimeMillis()-startTime)/1000);
		
		System.out.println(" *************************** END ************************");

		
	}
	

}
