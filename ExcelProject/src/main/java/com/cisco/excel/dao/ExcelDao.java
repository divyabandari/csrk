package com.cisco.excel.dao;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cisco.excel.dto.InventoryDTO;
import com.cisco.excel.dto.InventoryOtherSheetsDTO;


public interface ExcelDao  {
	
    //
	
	 public boolean storeIrExelChassis(List<InventoryDTO> lisInventoryDTO);
	 
	 public boolean storeSntcExelChassis(List<InventoryDTO> lisInventoryDTO);
	 
     public boolean storeIrExelCards(List<InventoryDTO> lisInventoryDTO);
	 
	 public boolean storeSntcExelCards(List<InventoryDTO> lisInventoryDTO);
	 
	 public List<Object> getQUeryList(String queryStringProp);
	 
	 public boolean getQUeryListBulk(String queryStringProp, Map<String,String> snMap, HSSFWorkbook workbook,  HSSFSheet sheet);
	 
	 public boolean getQUeryListSheetBulk(String queryStringProp,HSSFWorkbook workbook,  HSSFSheet sheet);
	
	 public List<Object> getQUeryListWithIRVEP(String queryStringProp);
	 
	 
	 public List<Object> getIRCardExtraSerialNumbers();
	 
	public boolean storeSntcOtherSheets(List<InventoryOtherSheetsDTO> inventoryOthersheetDTOList);
	
	public boolean deleteInventorySheetFromDB(String queryString);
	
} 
