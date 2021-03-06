package com.cisco.excel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the EXCEL_IR_REPORTED_CARD database table.
 * 
 */
@Entity
@Table(name="EXCEL_IR_REPORTED_CARD")
@NamedQuery(name="ExcelIrReportedCard.findAll", query="SELECT e FROM ExcelIrReportedCard e")
public class ExcelIrReportedCard implements Serializable {
	private static final long serialVersionUID = 1L;

	private String collectedpid;

	private String collectedserialnum;

	private String equipmenttype;

	private String hostname;

	private String ipaddress;

	private String pid;

	private String productfamily;

	private String serialnum;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	public ExcelIrReportedCard() {
	}

	public String getCollectedpid() {
		return this.collectedpid;
	}

	public void setCollectedpid(String collectedpid) {
		this.collectedpid = collectedpid;
	}

	public String getCollectedserialnum() {
		return this.collectedserialnum;
	}

	public void setCollectedserialnum(String collectedserialnum) {
		this.collectedserialnum = collectedserialnum;
	}

	public String getEquipmenttype() {
		return this.equipmenttype;
	}

	public void setEquipmenttype(String equipmenttype) {
		this.equipmenttype = equipmenttype;
	}

	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getProductfamily() {
		return this.productfamily;
	}

	public void setProductfamily(String productfamily) {
		this.productfamily = productfamily;
	}

	public String getSerialnum() {
		return this.serialnum;
	}

	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}
	
	public int getId(){
		return this.id;
	}
	

}