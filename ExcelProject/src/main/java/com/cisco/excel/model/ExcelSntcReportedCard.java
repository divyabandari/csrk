package com.cisco.excel.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the EXCEL_SNTC_REPORTED_CARD database table.
 * 
 */
@Entity
@Table(name="EXCEL_SNTC_REPORTED_CARD")
@NamedQuery(name="ExcelSntcReportedCard.findAll", query="SELECT e FROM ExcelSntcReportedCard e")
public class ExcelSntcReportedCard implements Serializable {
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

	public ExcelSntcReportedCard() {
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