package com.cisco.excel.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the excel_sntc_all_contracts database table.
 * 
 */
@Entity
@Table(name="excel_sntc_all_contracts")
@NamedQuery(name="ExcelSntcAllContract.findAll", query="SELECT e FROM ExcelSntcAllContract e")
public class ExcelSntcAllContract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;

	private String contractbilltocountry;

	private String contractbilltocustomer;

	private String contractenddate;

	private String contractnumber;

	private String contractservicelevel;

	private String contractstartdate;

	private String contractstatus;

	private String covereddevicescount;

	public ExcelSntcAllContract() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContractbilltocountry() {
		return this.contractbilltocountry;
	}

	public void setContractbilltocountry(String contractbilltocountry) {
		this.contractbilltocountry = contractbilltocountry;
	}

	public String getContractbilltocustomer() {
		return this.contractbilltocustomer;
	}

	public void setContractbilltocustomer(String contractbilltocustomer) {
		this.contractbilltocustomer = contractbilltocustomer;
	}

	public String getContractenddate() {
		return this.contractenddate;
	}

	public void setContractenddate(String contractenddate) {
		this.contractenddate = contractenddate;
	}

	public String getContractnumber() {
		return this.contractnumber;
	}

	public void setContractnumber(String contractnumber) {
		this.contractnumber = contractnumber;
	}

	public String getContractservicelevel() {
		return this.contractservicelevel;
	}

	public void setContractservicelevel(String contractservicelevel) {
		this.contractservicelevel = contractservicelevel;
	}

	public String getContractstartdate() {
		return this.contractstartdate;
	}

	public void setContractstartdate(String contractstartdate) {
		this.contractstartdate = contractstartdate;
	}

	public String getContractstatus() {
		return this.contractstatus;
	}

	public void setContractstatus(String contractstatus) {
		this.contractstatus = contractstatus;
	}

	public String getCovereddevicescount() {
		return this.covereddevicescount;
	}

	public void setCovereddevicescount(String covereddevicescount) {
		this.covereddevicescount = covereddevicescount;
	}

}