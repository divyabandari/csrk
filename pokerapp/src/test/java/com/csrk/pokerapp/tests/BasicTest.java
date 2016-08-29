package com.csrk.pokerapp.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.csrk.pokerapp.PokerData;
import com.csrk.pokerapp.common.CSVHeader;
import com.csrk.pokerapp.common.PokerAppConstant;
import com.csrk.pokerapp.parser.CSVParser;

public class BasicTest {
	public static final Logger LOG = LoggerFactory.getLogger(BasicTest.class);
	
	@BeforeClass
	public void oneTimeSetup(){
		//create or clear the results folder.
		try {
			File resultsDirectory = new File(PokerAppConstant.OUTPUT_DIRECTORY);
			if( ! resultsDirectory.isDirectory()){
				FileUtils.forceMkdir(resultsDirectory);
			}else{
				FileUtils.cleanDirectory(resultsDirectory);
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOG.debug("Error cleaning the results directory");
		}
	}

//	@Test
	public void parseTest() {
		CSVParser parser = new CSVParser();

		try {
			Iterable<CSVRecord> records = parser.parse("csv2016.csv");

			for (CSVRecord record : records) {
				LOG.debug("The record number and size is: " + record.size() + " " + record.getRecordNumber());
				LOG.debug("===START==");
				for (int i = 0; i < record.size(); i++) {
					LOG.debug("Record Contents: " + record.get(i));
				}
				LOG.debug("===END==");
			}
		} catch (FileNotFoundException e) {
			LOG.error("FileNotFound Exception", e);
		} catch (IOException e) {
			LOG.error("IO Exception", e);
		}
	}
	
//	@Test
	public void parseTestWithHeaders() {
		CSVParser parser = new CSVParser();

		try {
			Iterable<CSVRecord> records = parser.parse("csv2016.csv");

			for (CSVRecord record : records) {
				LOG.debug("The record number: " + record.getRecordNumber()+"Expenses: " + record.get(CSVHeader.EXPENSES)+"Buy In: " 
						+ record.get(CSVHeader.BUY_IN)+"Cash Out: " + record.get(CSVHeader.CASH_OUT));
			}
		} catch (FileNotFoundException e) {
			LOG.error("FileNotFound Exception", e);
		} catch (IOException e) {
			LOG.error("IO Exception", e);
		}
	}
	
//	@Test
	public void testDateFromCSV() {
		CSVParser parser = new CSVParser();

		try {
			Iterable<CSVRecord> records = parser.parse("csv2016.csv");

			for (CSVRecord record : records) {
				LOG.debug("The record number: " + record.getRecordNumber()+" Start Date: " + record.get(CSVHeader.START_TIME));
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss +hhmm");
				// 2016-04-11 01:55:00 +0000
				Date date = format.parse(record.get(CSVHeader.START_TIME));
				LOG.debug("Date is: "+date); 

			}
		} catch (FileNotFoundException e) {
			LOG.error("FileNotFound Exception", e);
		} catch (IOException e) {
			LOG.error("IO Exception", e);
		} catch (ParseException e) {
			LOG.error("Parse Exception", e);
		}
	}
	
	@Test
	public void testMonthWise(){
		PokerData data = new PokerData();
		try{
			data.getMonthWiseStats("csv2016.csv");
		} catch (FileNotFoundException e) {
			LOG.error("FileNotFound Exception", e);
		} catch (IOException e) {
			LOG.error("IO Exception", e);
		} catch (ParseException e) {
			LOG.error("Parse Exception", e);
		}
	}
}