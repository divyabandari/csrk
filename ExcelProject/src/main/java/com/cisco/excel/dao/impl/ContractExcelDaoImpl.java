package com.cisco.excel.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.cisco.excel.dao.ContractExcelDao;
import com.cisco.excel.dto.AllContractDTO;
import com.cisco.excel.dto.CoveredDevicesDTO;
import com.cisco.excel.dto.NotCoveredDevicesDTO;
import com.cisco.excel.model.ExcelIrAllContract;
import com.cisco.excel.model.ExcelIrCoveredDevice;
import com.cisco.excel.model.ExcelIrNotCoveredDevice;
import com.cisco.excel.model.ExcelSntcAllContract;
import com.cisco.excel.model.ExcelSntcCoveredDevice;
import com.cisco.excel.model.ExcelSntcNotCoveredDevice;

@Repository
public class ContractExcelDaoImpl implements ContractExcelDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	@Autowired
	private Environment env;

	public ContractExcelDaoImpl() {
		super();

	}
/**
 * This DAO method will store the IR CMR report All contract sheet data into data base
 * 
 */
	public boolean storeIRAllContractInfo(
			List<AllContractDTO> allContractDTOList) {

		System.out.println(" in ContractExcelDaoImpl class storeIRAllContractInfo method ");
		long startTime = System.currentTimeMillis();

		try {
			int batchCounter = 0;
			deleteRecords(" delete from ExcelIrAllContract "); // deleting the existing records from DB.
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (AllContractDTO contractDTO : allContractDTOList) {
				ExcelIrAllContract irAllContracts = new ExcelIrAllContract();

				irAllContracts.setContractnumber(contractDTO
						.getContractNumber());
				irAllContracts.setContractservicelevel(contractDTO
						.getContractServiceLevel());
				irAllContracts.setContractstatus(contractDTO
						.getContractStatus());
				irAllContracts.setContractbilltocustomer(contractDTO
						.getContractBillTocustomer());
				irAllContracts.setContractbilltocountry(contractDTO
						.getContractBillToCountry());
				irAllContracts.setContractstartdate(contractDTO
						.getContractStartDate());
				irAllContracts.setContractenddate(contractDTO
						.getContractEndDate());
				irAllContracts.setCovereddevicescount(contractDTO
						.getCoveredCount());

		
				session.persist(irAllContracts);

				if (batchCounter % 500 == 0) {
					session.flush();
					session.clear();
				}
				batchCounter++;

			}
			System.out.println(" Successfully saved data");
			System.out.println(" total time taken for saving the data"
					+ (System.currentTimeMillis() - startTime));
			return true;
		}

		catch (Exception e) {
			System.out.println(" Exception while  saving data");
			e.printStackTrace();
			return false;

		} finally {

			session.getTransaction().commit();
			session.close();
		}

	}
	/**
	 * Deleting the existing IR All Contracts information  from the Database 
	 * @return
	 */
	private boolean deleteRecords( String sqlQuery) {
        System.out.println(" in deleteRecords  method ");
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session
					.createQuery(sqlQuery);

			int deletedCount = query.executeUpdate();
			System.out.println(" printing the deleteCount value "
					+ deletedCount);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			session.getTransaction().commit();
			session.close();
		}

		return true;

	}	

	/**
	 * This DAO method will store the SNTC CMR report All contract sheet data into data base
	 * 
	 */
	@Override
	public boolean storeSNTCAllContractInfo(
			List<AllContractDTO> allContractDTOList) {

		System.out.println(" in ContractExcelDaoImpl class storeSNTCAllContractInfo  method ");
		long startTime = System.currentTimeMillis();

		try {
			int batchCounter = 0;
			deleteRecords(" delete from ExcelSntcAllContract "); // delete the SNTC all contracts data from the DB.
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (AllContractDTO contractDTO : allContractDTOList) {
				ExcelSntcAllContract sntcAllContracts = new ExcelSntcAllContract();

				sntcAllContracts.setContractnumber(contractDTO
						.getContractNumber());
				sntcAllContracts.setContractservicelevel(contractDTO
						.getContractServiceLevel());
				sntcAllContracts.setContractstatus(contractDTO
						.getContractStatus());
				sntcAllContracts.setContractbilltocustomer(contractDTO
						.getContractBillTocustomer());
				sntcAllContracts.setContractbilltocountry(contractDTO
						.getContractBillToCountry());
				sntcAllContracts.setContractstartdate(contractDTO
						.getContractStartDate());
				sntcAllContracts.setContractenddate(contractDTO
						.getContractEndDate());
				sntcAllContracts.setCovereddevicescount(contractDTO
						.getCoveredCount());

			
				session.persist(sntcAllContracts);

				if (batchCounter % 500 == 0) {
					session.flush();
					session.clear();
				}
				
				batchCounter++;

			}
			System.out.println(" Successfully saved data");
			System.out.println(" total time taken for saving the data"
					+ (System.currentTimeMillis() - startTime));
			return true;
		}

		catch (Exception e) {
			System.out.println(" Exception while  saving data");
			e.printStackTrace();
			return false;

		} finally {

			session.getTransaction().commit();
			session.close();
		}

	}
	
	/**
	 * This DAO method will store the SNTC CMR report Covered Devices sheet data into data base
	 * 
	 */
	@Override
	public boolean storeSNTCCoveredDevicesInfo(
			List<CoveredDevicesDTO> coveredDevicesDTOList) {

		System.out.println(" in ContractExcelDaoImpl class storeSNTCCoveredDevicesInfo  method ");
		long startTime = System.currentTimeMillis();

		try {
			int batchCounter = 0;
			deleteRecords(" delete from ExcelSntcCoveredDevice "); // delete the SNTC all contracts data from the DB.
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (CoveredDevicesDTO contractDTO : coveredDevicesDTOList) {
				ExcelSntcCoveredDevice coveredDevice = new ExcelSntcCoveredDevice();

				coveredDevice.setHostname(contractDTO.getHostname());
				coveredDevice.setIpaddress(contractDTO
						.getIpaddress());
				coveredDevice.setSerialnum(contractDTO
						.getSerialnum());
				coveredDevice.setProductid(contractDTO
						.getProductid());
				coveredDevice.setEquipmenttype(contractDTO
						.getEquipmenttype());
				coveredDevice.setContractnumber(contractDTO
						.getContractnumber());
				coveredDevice.setCoveragestatus(contractDTO
						.getCoveragestatus());
				coveredDevice.setServicelevel(contractDTO
						.getServicelevel());
				
				coveredDevice.setInstallsiteid(contractDTO
						.getInstallsiteid());
				
				coveredDevice.setInstallsitecustomer(contractDTO
						.getInstallsitecustomer());
			

			
				session.persist(coveredDevice);

				if (batchCounter % 500 == 0) {
					session.flush();
					session.clear();
				}
				
				batchCounter++;

			}
			System.out.println(" Successfully saved data");
			System.out.println(" total time taken for saving the data"
					+ (System.currentTimeMillis() - startTime));
			return true;
		}

		catch (Exception e) {
			System.out.println(" Exception while  saving data");
			e.printStackTrace();
			return false;

		} finally {

			session.getTransaction().commit();
			session.close();
		}

	}
	
	/**
	 * This DAO method will store the IR CMR report Covered Devices sheet data into data base
	 * 
	 */
	@Override
	public boolean storeIRCoveredDevicesInfo(
			List<CoveredDevicesDTO> coveredDevicesDTOList) {

		System.out.println(" in ContractExcelDaoImpl class storeIRCoveredDevicesInfo  method ");
		long startTime = System.currentTimeMillis();

		try {
			int batchCounter = 0;
			deleteRecords(" delete from ExcelIrCoveredDevice "); // delete the SNTC all contracts data from the DB.
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (CoveredDevicesDTO contractDTO : coveredDevicesDTOList) {
				ExcelIrCoveredDevice coveredDevice = new ExcelIrCoveredDevice();

				coveredDevice.setHostname(contractDTO.getHostname());
				coveredDevice.setIpaddress(contractDTO
						.getIpaddress());
				coveredDevice.setSerialnum(contractDTO
						.getSerialnum());
				coveredDevice.setProductid(contractDTO
						.getProductid());
				coveredDevice.setEquipmenttype(contractDTO
						.getEquipmenttype());
				coveredDevice.setContractnumber(contractDTO
						.getContractnumber());
				coveredDevice.setCoveragestatus(contractDTO
						.getCoveragestatus());
				coveredDevice.setServicelevel(contractDTO
						.getServicelevel());
				
				coveredDevice.setInstallsiteid(contractDTO
						.getInstallsiteid());
				
				coveredDevice.setInstallsitecustomer(contractDTO
						.getInstallsitecustomer());
			

			
				session.persist(coveredDevice);

				if (batchCounter % 500 == 0) {
					session.flush();
					session.clear();
				}
				
				batchCounter++;

			}
			System.out.println(" Successfully saved data");
			System.out.println(" total time taken for saving the data"
					+ (System.currentTimeMillis() - startTime));
			return true;
		}

		catch (Exception e) {
			System.out.println(" Exception while  saving data");
			e.printStackTrace();
			return false;

		} finally {

			session.getTransaction().commit();
			session.close();
		}

	}
	
	/**
	 * This DAO method will store the IR CMR report Not Covered Devices sheet data into data base
	 * 
	 */
	@Override
	public boolean storeIRNotCoveredDevicesInfo(
			List<NotCoveredDevicesDTO> notCoveredDevicesDTOList) {

		System.out.println(" in ContractExcelDaoImpl class storeIRNotCoveredDevicesInfo  method ");
		long startTime = System.currentTimeMillis();

		try {
			int batchCounter = 0;
			deleteRecords(" delete from ExcelIrNotCoveredDevice "); // delete the SNTC all contracts data from the DB.
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (NotCoveredDevicesDTO notCoveredDevicesDTO : notCoveredDevicesDTOList) {
				ExcelIrNotCoveredDevice coveredDevice = new ExcelIrNotCoveredDevice();

				coveredDevice.setHostname(notCoveredDevicesDTO.getHostname());
				coveredDevice.setIpaddress(notCoveredDevicesDTO
						.getIpaddress());
				coveredDevice.setSerialnum(notCoveredDevicesDTO
						.getSerialnum());
				coveredDevice.setProductid(notCoveredDevicesDTO
						.getProductid());
				coveredDevice.setEquipmenttype(notCoveredDevicesDTO
						.getEquipmenttype());
							
				coveredDevice.setInstallsiteid(notCoveredDevicesDTO
						.getInstallsiteid());
				
				coveredDevice.setInstallsitecustomer(notCoveredDevicesDTO
						.getInstallsitecustomer());
				coveredDevice.setInstancenumber(notCoveredDevicesDTO
						.getInstancenumber());
					
				session.persist(coveredDevice);

				if (batchCounter % 500 == 0) {
					session.flush();
					session.clear();
				}
				
				batchCounter++;

			}
			System.out.println(" Successfully saved data");
			System.out.println(" total time taken for saving the data"
					+ (System.currentTimeMillis() - startTime));
			return true;
		}

		catch (Exception e) {
			System.out.println(" Exception while  saving data");
			e.printStackTrace();
			return false;

		} finally {

			session.getTransaction().commit();
			session.close();
		}

	}
	
	/**
	 * This DAO method will store the SNTC CMR report Not Covered Devices sheet data into data base
	 * 
	 */
	@Override
	public boolean storeSNTCNotCoveredDevicesInfo(
			List<NotCoveredDevicesDTO> notCoveredDevicesDTOList) {

		System.out.println(" in ContractExcelDaoImpl class storeSNTCNotCoveredDevicesInfo  method ");
		long startTime = System.currentTimeMillis();

		try {
			int batchCounter = 0;
			deleteRecords(" delete from ExcelSntcNotCoveredDevice "); // delete the SNTC all contracts data from the DB.
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (NotCoveredDevicesDTO notCoveredDevicesDTO : notCoveredDevicesDTOList) {
				ExcelSntcNotCoveredDevice coveredDevice = new ExcelSntcNotCoveredDevice();

				coveredDevice.setHostname(notCoveredDevicesDTO.getHostname());
				coveredDevice.setIpaddress(notCoveredDevicesDTO
						.getIpaddress());
				coveredDevice.setSerialnum(notCoveredDevicesDTO
						.getSerialnum());
				coveredDevice.setProductid(notCoveredDevicesDTO
						.getProductid());
				coveredDevice.setEquipmenttype(notCoveredDevicesDTO
						.getEquipmenttype());
							
				coveredDevice.setInstallsiteid(notCoveredDevicesDTO
						.getInstallsiteid());
				
				coveredDevice.setInstallsitecustomer(notCoveredDevicesDTO
						.getInstallsitecustomer());
				coveredDevice.setInstancenumber(notCoveredDevicesDTO
						.getInstancenumber());
					
				session.persist(coveredDevice);

				if (batchCounter % 500 == 0) {
					session.flush();
					session.clear();
				}
				
				batchCounter++;

			}
			System.out.println(" Successfully saved data");
			System.out.println(" total time taken for saving the data"
					+ (System.currentTimeMillis() - startTime));
			return true;
		}

		catch (Exception e) {
			System.out.println(" Exception while  saving data");
			e.printStackTrace();
			return false;

		} finally {

			session.getTransaction().commit();
			session.close();
		}

	}
	
/*	*//**
	 * Deleting the existing SNTC All Contracts information  from the Database 
	 * @return
	 *//*
	private boolean deleteSNTCAllContractInfo() {
		
	    System.out.println(" in deleteSNTCAllContractInfo method ");

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session
					.createQuery();

			int deletedCount = query.executeUpdate();
			System.out.println(" printing the deleteCount value "
					+ deletedCount);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			session.getTransaction().commit();
			session.close();
		}

		return true;

	}
	*/
	/**
	 * This method used to get all the contracts which are same in the IR and SNTC 
	 */
	@Override
	public List<Object> getCommonContracts() {
		// TODO Auto-generated method stub
		
		String queryString = env.getProperty("CMRCommonContracts");
		try{
			session = sessionFactory.openSession();
	    	session.beginTransaction();
			

	    	System.out.println( " Query value  is "+ queryString);
	    SQLQuery query=	session.createSQLQuery(queryString);
	   List<Object> lis=  query.list();
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
	public List<Object> getQueryList(String queryString) {
		// TODO Auto-generated method stub
		try{
			session = sessionFactory.openSession();
	    	session.beginTransaction();
	    System.out.println( " Query value  is "+ queryString);
	    SQLQuery query=	session.createSQLQuery(queryString);
	    List<Object> lis=  query.list();
      	session.getTransaction().commit();
	    	
	    return lis;
		}catch(Exception e ){
			e.printStackTrace();
			
		}finally {
			session.close();
			
		}
		return null;
	}	
	
	
	

}
