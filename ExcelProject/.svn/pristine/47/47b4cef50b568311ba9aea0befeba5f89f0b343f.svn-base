package com.cisco.excel.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the excel_sntc_covered_devices database table.
 * 
 */
@Entity
@Table(name="excel_sntc_covered_devices")
@NamedQuery(name="ExcelSntcCoveredDevice.findAll", query="SELECT e FROM ExcelSntcCoveredDevice e")
public class ExcelSntcCoveredDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;

	private String contractnumber;

	private String coveragestatus;

	private String equipmenttype;

	private String hostname;

	private String installsitecustomer;

	private String installsiteid;

	private String ipaddress;

	private String productid;

	private String serialnum;

	private String servicelevel;

	public ExcelSntcCoveredDevice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContractnumber() {
		return this.contractnumber;
	}

	public void setContractnumber(String contractnumber) {
		this.contractnumber = contractnumber;
	}

	public String getCoveragestatus() {
		return this.coveragestatus;
	}

	public void setCoveragestatus(String coveragestatus) {
		this.coveragestatus = coveragestatus;
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

	public String getServicelevel() {
		return this.servicelevel;
	}

	public void setServicelevel(String servicelevel) {
		this.servicelevel = servicelevel;
	}

}