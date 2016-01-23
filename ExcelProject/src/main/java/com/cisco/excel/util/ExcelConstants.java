package com.cisco.excel.util;

public class ExcelConstants {
	
	//APP Name constants
	public static final String IR_APP_NAME = "IR";
	public static final String SNTC_APP_NAME = "SNTC";
	
	//Inventory File Names for Scotia  
	
	public static final String IR_FILE_NAME = "Eircom_IR_InventoryDataParity_06162015.xlsm";
	public static final String SNTC_FILE_NAME = "Eircom_SNTC_Inventory_DataParity_06162015.xlsm";
	
	public static final int PRF_COLLECTION_OBJID = 136590 ;
	
	//CMR  Scotia Bank Contracts File Names 
	public static final String IR_CMR_FILE_NAME = "ChevronFeb4_IR_ContractManagementReport.xlsm";
	public static final String SNTC_CMR_FILE_NAME = "ChevronFeb4_SNTC_Contracts_Management_Report.xlsm";
	
	//Inventory Sheet Names 
	public static final String REPORTED_CHASSIS_SHEET ="Reported Chassis";
	public static final String REPORTED_CARD_SHEET ="Reported Cards";
	public static final String REPORTED_MODULE_SHEET ="Reported Module";
	public static final String REPORTED_POWER_SUPPLY ="Reported Power Supply";
	public static final String REPORTED_FAN ="Reported Fan";
	public static final String REPORTED_OTHERS_SHEET="Reported Other";
	public static final String SUMMARY_SHEET_NAME= "Summary";
	public static final String DUPLICATES_SHEET="Duplicates";
	public static final String NOT_RECOGNIZED_SHET="Not Recognized";
	public static final String NOT_FIELD_REPLACEBLE_SHEET="Not Field Replaceable";
	
	
	public static final String FIND_NUMBER_IN_STRING_REGEX ="[0-9]+";
	//Contract Sheet Names 
	
	// IR All contracts
	public static final String All_CONTRACT_SHEET ="All Contracts";
	public static final String All_CONTRACT_SHEET_RELATIONAL_ID ="rId3";
	public static final int All_CONTRACT_SHEET_DATA_START_ROW_NUMBER =7;
	public static final String All_CONTRACT_SHEET_DATA_COLUMN_REGEX ="[A-I]+";
	
	// SNTC All contracts
	public static final String SNTC_All_CONTRACT_SHEET ="All Contracts";
	public static final String SNTC_All_CONTRACT_SHEET_RELATIONAL_ID ="rId3";
	public static final int SNTC_All_CONTRACT_SHEET_DATA_START_ROW_NUMBER =6;
	public static final String SNTC_All_CONTRACT_SHEET_DATA_COLUMN_REGEX ="[A-H]+";
	
	// IR Covered Items 
	public static final String COVERED_ITEMS_SHEET ="Covered Items";
	public static final String COVERED_ITEMS_SHEET_RELATIONAL_ID ="rId7";
	public static final int COVERED_ITEMS_SHEET_DATA_START_ROW_NUMBER =7;
	public static final String COVERED_ITEMS_SHEET_DATA_COLUMN_REGEX ="[A-P]+";

	public static final String CHASSIS_COVERED_CARDS_SHEET ="Chassis Covered Cards";
	public static final String CHASSIS_COVERED_CARDS_SHEET_RELATIONAL_ID ="rId8";
	public static final int CHASSIS_COVERED_CARDS_SHEET_DATA_START_ROW_NUMBER =6;
	public static final String CHASSIS_COVERED_CARDS_SHEET_DATA_COLUMN_REGEX ="[A-N]+";
	
	//IR Not Covered ; both sheet are identical.
	public static final String NOT_COVERED_CHASSIS_CCM_PHONE = "Not Covered Chassis-CCM-IPPHONE";
	public static final String NOT_COVERED_CHASSIS_CCM_PHONE_SHEET_RELATIONAL_ID ="rId9";
	public static final int NOT_COVERED_CHASSIS_CCM_PHONE_SHEET_DATA_START_ROW_NUMBER =6;
	public static final String NOT_COVERED_CHASSIS_CCM_PHONE_SHEET_DATA_COLUMN_REGEX ="[A-R]+";
	
	public static final String NOT_COVERED_CARDS_SHEET= "Not Covered - Cards";
	public static final String NOT_COVERED_CARDS_SHEET_RELATIONAL_ID ="rId10";
	public static final int NOT_COVERED_CARDS_SHEET_DATA_START_ROW_NUMBER =6;
	public static final String NOT_COVERED_CARDS_SHEET_DATA_COLUMN_REGEX ="[A-R]+";
	
	//SNTC Covered Items
	public static final String COVERED_SHEET ="Covered";
	public static final String COVERED_SHEET_RELATIONAL_ID ="rId7";
	public static final int COVERED_SHEET_DATA_START_ROW_NUMBER =6;
	public static final String COVERED_SHEET_DATA_COLUMN_REGEX ="[A-O]+";

	// SNTC not covered
	public static final String NOT_COVERED_SHEET ="Not Covered";
	public static final String NOT_COVERED_RELATIONAL_ID ="rId8";
	public static final int NOT_COVERED_DATA_START_ROW_NUMBER =6;
	public static final String NOT_COVERED_DATA_COLUMN_REGEX ="[A-R]+";
	
	//SAX parser implementation
	public static final String SAX_PARSER = "org.apache.xerces.parsers.SAXParser";
	public static final String REQUIRED_DATE_FORMAT_FOR_DTO = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYY_MMM_DD = "yyyy-MMM-dd";
	
}
