package com.cisco.excel.dto;

public class InventoryOtherSheetsDTO {
	
	private String hostName ;
	private String ipAddress ;
	private String collectedSerialNumber ;
	private String collectedPID ;
	private String reportSheetName ;
	private String equipementType;
	
	public String getHostName() {
		return hostName;
	}
	public String getEquipementType() {
		return equipementType;
	}
	public void setEquipementType(String equipementType) {
		this.equipementType = equipementType;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getCollectedSerialNumber() {
		return collectedSerialNumber;
	}
	public void setCollectedSerialNumber(String collectedSerialNumber) {
		this.collectedSerialNumber = collectedSerialNumber;
	}
	public String getCollectedPID() {
		return collectedPID;
	}
	public void setCollectedPID(String collectedPID) {
		this.collectedPID = collectedPID;
	}
	public String getReportSheetName() {
		return reportSheetName;
	}
	public void setReportSheetName(String reportSheetName) {
		this.reportSheetName = reportSheetName;
	}

	

}
