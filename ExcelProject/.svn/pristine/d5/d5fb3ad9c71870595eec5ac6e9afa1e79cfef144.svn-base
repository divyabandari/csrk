package com.cisco.excel.dao;

import java.util.List;

import com.cisco.excel.dto.AllContractDTO;
import com.cisco.excel.dto.CoveredDevicesDTO;
import com.cisco.excel.dto.NotCoveredDevicesDTO;


public interface ContractExcelDao  {
	
    //
	
	 public boolean storeIRAllContractInfo(List<AllContractDTO> allContractDTOList);
	 
	 public boolean storeSNTCAllContractInfo(List<AllContractDTO> allContractDTOList);
	 
	 public boolean storeSNTCCoveredDevicesInfo(List<CoveredDevicesDTO> coveredDevicesDTOList);
	 
	 public boolean storeIRCoveredDevicesInfo(List<CoveredDevicesDTO> coveredDevicesDTOList);
	 
	 public boolean storeIRNotCoveredDevicesInfo(List<NotCoveredDevicesDTO> notCoveredDevicesDTOList);
	 
	 public boolean storeSNTCNotCoveredDevicesInfo(List<NotCoveredDevicesDTO> notCoveredDevicesDTOList);
	  
	 
	 
	 public List<Object> getCommonContracts();
	 
	 public List<Object> getQueryList(String queryString);
	 	 

} 
