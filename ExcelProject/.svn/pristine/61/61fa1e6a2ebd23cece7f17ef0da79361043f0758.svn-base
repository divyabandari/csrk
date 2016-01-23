package com.cisco.excel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the inventory_other_sheets database table.
 * 
 */
@Entity
@Table(name="inventory_other_sheets")
@NamedQuery(name="InventoryOtherSheets.findAll", query="SELECT e FROM InventoryOtherSheets e")
public class InventoryOtherSheets implements Serializable {
	private static final long serialVersionUID = 1L;

	private String collectedpid;

	private String collectedserialnum;

	private String equipmenttype;

	private String hostname;

	private String ipaddress;


	private String reportsheet;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	public InventoryOtherSheets() {
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

	
	public String getReportsheet() {
		return this.reportsheet;
	}

	public void setReportsheet(String reportsheet) {
		this.reportsheet = reportsheet;
	}
	
	public int getId(){
		return this.id;
	}
	

}