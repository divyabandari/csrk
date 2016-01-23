package com.cisco.excel.dto;

public class InventoryDTO {
	
	private String hostName ;
	private String ipAddress ;
	private String collectedSerialNumber ;
	private String collectedPID ;
	private String serialNumber ;
	private String PID ;
	private String equipmentType ;
	private String productFamily ;
	
	public String getHostName() {
		return hostName;
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
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getProductFamily() {
		return productFamily;
	}
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	@Override
	public String toString() {
		return this.hostName+" "+this.ipAddress+" "+this.serialNumber;
	}
}