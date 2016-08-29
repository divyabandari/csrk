package com.csrk.pokerapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csrk.pokerapp.common.CSVHeader;
import com.csrk.pokerapp.common.PokerAppConstant;
import com.csrk.pokerapp.parser.CSVParser;

/**
 * encapsulation of all the poker data in csv file
 * 
 * @author schituku
 *
 */
public class PokerData {

	public static final Logger LOG = LoggerFactory.getLogger(PokerData.class);

	/**
	 * get total profit-loss by month
	 * 
	 * @return profit-loss for month
	 */
	public void getMonthWiseStats(String pathToFile) throws FileNotFoundException, IOException, ParseException {

		Iterable<CSVRecord> records = new CSVParser().parse(pathToFile);

		List<MonthStats> stats = new ArrayList<MonthStats>();
		DateFormat format = new SimpleDateFormat(CSVHeader.DATE_FORMAT_IN_CSV);
		Calendar calendar = Calendar.getInstance();
		int currMonth = -1;
		int recordMonth = -1;
		MonthStats temp = null;
		
		for (CSVRecord record : records) {
			
//			LOG.debug("The record number: " + record.getRecordNumber() );
			
			calendar.setTime(format.parse(record.get(CSVHeader.START_TIME)));
			recordMonth = calendar.get(Calendar.MONTH);

			//first record
			if(currMonth == -1){
				temp = new MonthStats();
				currMonth = recordMonth;
				temp.setMonth(recordMonth);
				temp = updateMonthStat(record, temp);
			}else if(recordMonth == currMonth){
				// middle record
				temp.setMonth(recordMonth);
				temp = updateMonthStat(record, temp);
				
			}else{
				//last record for a month
				stats.add(temp);
				
				temp = new MonthStats();
				currMonth = recordMonth;
				temp.setMonth(recordMonth);
				temp = updateMonthStat(record, temp);
			}
		}
		stats.add(temp);
		
		File resultsFile = new File(PokerAppConstant.MONTHWISE_OUTPUT_FILE_NAME);
		List<String> resultLines = new ArrayList<>();
		resultLines.add(String.format(PokerAppConstant.SUMMARY_FILE_HEADER_FORMAT,
										PokerAppConstant.MONTHWISE_OUTPUT_FILE_HEADER_MONTH,
										PokerAppConstant.MONTHWISE_OUTPUT_FILE_HEADER_GROSS_PROFIT,
										PokerAppConstant.MONTHWISE_OUTPUT_FILE_HEADER_EXPENSES,
										PokerAppConstant.MONTHWISE_OUTPUT_FILE_HEADER_NET_PROFIT));
		int monthSummaryNetProfit=0;
		int monthSummaryProfit=0;
		int monthSummaryExpenses=0;
		
		for (MonthStats monthStats : stats) {
			LOG.debug(monthStats.toString());
			monthSummaryExpenses+=monthStats.getMonthExpenses();
			monthSummaryProfit+=monthStats.getMonthProfit();
			monthSummaryNetProfit+=monthStats.getMonthNetProfit();
			resultLines.add(String.format(PokerAppConstant.SUMMARY_FILE_LINE_FORMAT,"",
					monthStats.getMonth(),
					monthStats.getMonthProfit(),
					monthStats.getMonthExpenses(),
					monthStats.getMonthNetProfit()));
		}
		StringBuffer monthStatsSummaryString = new StringBuffer();
		monthStatsSummaryString.append("Net Profit: "+monthSummaryNetProfit);
		monthStatsSummaryString.append(" Profit: "+monthSummaryProfit);
		monthStatsSummaryString.append(" Expenses: "+monthSummaryExpenses);
		LOG.debug(monthStatsSummaryString.toString());
		resultLines.add(String.format(PokerAppConstant.SUMMARY_FILE_LINE_FORMAT,"--","--","--","--","--"));
		resultLines.add(String.format(PokerAppConstant.SUMMARY_FILE_LINE_FORMAT,"",
										"Summary",monthSummaryProfit,monthSummaryExpenses,monthSummaryNetProfit));
		try {
			FileUtils.writeLines(resultsFile, Charsets.UTF_8.toString(),
					resultLines	, true);
		} catch (IOException e) {
			LOG.debug("Error in writing to the file");
			e.printStackTrace();
		}
	}
	
	private MonthStats updateMonthStat(CSVRecord record, MonthStats temp){

		temp.setMonthBuyIn(temp.getMonthBuyIn() + Integer.parseInt(record.get(CSVHeader.BUY_IN)));
		temp.setMonthCashOut(temp.getMonthCashOut() +   Integer.parseInt(record.get(CSVHeader.CASH_OUT)));
		temp.setMonthExpenses(temp.getMonthExpenses() +  Integer.parseInt(record.get(CSVHeader.EXPENSES)));
		return temp;
	}
}
