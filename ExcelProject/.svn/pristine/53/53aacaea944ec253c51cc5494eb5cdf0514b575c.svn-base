package com.cisco.excel.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the excel_sntc_not_covered_devices database table.
 * 
 */
@Entity
@Table(name="excel_sntc_not_covered_devices")
@NamedQuery(name="ExcelSntcNotCoveredDevice.findAll", query="SELECT e FROM ExcelSntcNotCoveredDevice e")
public class ExcelSntcNotCoveredDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;

	private String equipmenttype;

	private String hostname;

	private String installsitecustomer;

	private String installsiteid;

	private String instancenumber;

	private String ipaddress;

	private String productid;

	private String serialnum;

	public ExcelSntcNotCoveredDevice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getInstallsitecustomer() {
		return this.installsitecustomer;
	}

	public void setInstallsitecustomer(String installsitecustomer) {
		this.installsitecustomer = installsitecustomer;
	}

	public String getInstallsiteid() {
		return this.installsiteid;
	}

	public void setInstallsiteid(String installsiteid) {
		this.installsiteid = installsiteid;
	}

	public String getInstancenumber() {
		return this.instancenumber;
	}

	public void setInstancenumber(String instancenumber) {
		this.instancenumber = instancenumber;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getProductid() {
		return this.productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getSerialnum() {
		return this.serialnum;
	}

	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}

}