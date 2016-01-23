package com.cisco.excel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.cisco.excel.model.ExcelIrReportedChassi;

public class Util {

	public static String getStringDiff(String str1, String str2) {

		return str1.equals(str2) ? "YES" : "NO";

	}

	public static String getCoveredDeviceCountDiff(String str1, String str2) {
		// if(str1!=null && str2!=null)
		return String.valueOf((Integer.valueOf(str1) - Integer.valueOf(str2)));
		// return "0";
	}

	public static String[] getHostDiff(String str1, String str2) {

		// 3 - YES/NO 0- diffString 1 -ir missing string 2 - sntc missing string
		/*
		 * System.out.println(" String 1 value "+ str1);
		 * System.out.println(" String 2 value "+ str2);
		 */
		String strArray[] = new String[4];

		String s1, s2;
		int counter = 0;

		if (str1 != null && str2 != null) {
			if (str1.equalsIgnoreCase(str2)) {

				strArray[0] = "SAME";
				strArray[1] = "NOTHING";
				strArray[2] = "NOTHING";
				strArray[3] = "YES";

			} else {
				int strMinLen = str1.length() > str2.length() ? str2.length()
						: str1.length();

				for (int j = 0; j < strMinLen; j++) {
					s1 = str1.substring(j, j + 1);
					s2 = str2.substring(j, j + 1);
					if (s1.equalsIgnoreCase(s2)) {
						counter++;
					} else {
						break;
					}

				}

				strArray[0] = counter == 0 ? "NOTHING" : str1.substring(0,
						counter);
				strArray[1] = str1.substring(counter);
				strArray[2] = str2.substring(counter);
				strArray[3] = "NO";
			}
		} else {

			strArray[0] = "NOTHING";
			strArray[1] = str1 == null ? "EMPTY" : str1;
			strArray[2] = str2 == null ? "EMPTY" : str2;
			strArray[3] = "EMPTY";
		}

		return strArray;

	}

	public static String convertDate(String dateString) {

		try {

			Date date = new SimpleDateFormat("yyyy-MMM-dd").parse(dateString);
			return new SimpleDateFormat("yyyy-MM-dd").format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}
	
	public static String convertDate(String dateString, String format) {

		try {

			Date date = new SimpleDateFormat(format).parse(dateString);
			return new SimpleDateFormat(ExcelConstants.REQUIRED_DATE_FORMAT_FOR_DTO).format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}

/*	public static String main(String a[]) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		String dateInString = "2012-MAY-14";

		try {

			Date date = new SimpleDateFormat("yyyy-MMM-dd").parse(dateInString);
			return formatter.format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
		
		 * 
		 * String s[]= getHostDiff("sSCI_pucallpa", "sSCI_pucallpa.corp.com");
		 * 
		 * System.out.println(" s [0]"+ s[0]); System.out.println(" s [1]"+
		 * s[1]); System.out.println(" s [2]"+ s[2]);
		 * 
		 * String str = "[(FNS14021BKF)[[CENT]]]";
		 * 
		 * 
		 * str = str.substring(str.indexOf("(")+1, str.indexOf(")"));
		 * 
		 * System.out.println(" printing str " + str); long startTime =
		 * System.currentTimeMillis(); for(int i=0 ;i<=27000 ;i++){
		 * 
		 * VEPString("[(FNS14021BLF)[[CENT]]]"); }
		 * System.out.println(" totak time taken  "+
		 * (System.currentTimeMillis()-startTime));
		 * 
		 * 
		 * 
		 * double d = 9.3757139E7;
		 * 
		 * System.out.println( "int value is " + (int)d);
		 }
*/


	public static void applyHeaderColor(Cell cell) {

		CellStyle cs = cell.getCellStyle();

		cs.setFillBackgroundColor(HSSFColor.BROWN.index);
		cell.setCellStyle(cs);

	}

	public static String getValue(String str) {

		if (str != null) {
			if (str.contains(".cisco.com")) {
				str = str.substring(0, str.indexOf(".cisco.com")).trim();
			}
			return str.toUpperCase();

		} else {

			return "";
		}

	}

	public static void test(Object obj) {

		if (obj instanceof ExcelIrReportedChassi) {
			System.out.println("in IF block Util class");

		}
		System.out.println(" test method ...");
		System.out.println("Class Name " + obj.getClass().getSimpleName());
	}

	public static int getCellValueInt(Cell cell) {

		int cellValue = 0;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				// System.out.print(cell.getBooleanCellValue() + "\t\t");
				break;
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = (int) cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING:
				// cellValue = cell.getStringCellValue().trim();

				break;
			}
		}

		return cellValue;
	}

	public static String getCellValue(Cell cell) {

		String cellValue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				// System.out.print(cell.getBooleanCellValue() + "\t\t");
				break;
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = Integer.toString((int) cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue().trim();

				break;
			}
		}

		return cellValue;
	}

	public static void applyStyle(Cell cell, CellStyle style) {

		if (cell.getStringCellValue().equalsIgnoreCase("YES")) {
			// System.out.println(" *************YES************");
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(CellStyle.ALIGN_FILL);
			cell.setCellStyle(style);
		}
		if (cell.getStringCellValue().equalsIgnoreCase("NO")) {
			// System.out.println(" *************NO************");
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(CellStyle.ALIGN_FILL);
			cell.setCellStyle(style);
		}
	}
	
	public static void main(String sr[]) {
		
		String input = "[(FOC15474WEJ)[[ISP, OCCM]], (FTX1550U056)[[CENT, SHTS]], (FTX1550U056)[[CENT, SHTS]]]";
		
		String input1="[(DCA12308EQ9)[[ISVP, CENT]]]";
		
	//System.out.println("  string  " +	VEPString(input));
	int k= 0;
		 for(int j=1 ; j< 18 ; j =j+2){
			 
			 System.out.println("J+1 value " + (j+1+k) + " j value " + j);
			 
			 System.out.println("J+2 value " + (j+2+k) + " j value " + (j+1));
			 
			 System.out.println("J+1 value " + (j+3+k) + " j value " + j +" j value " + (j+1));
			  System.out.println(" ****** ");
			 k++;
		 }
		 
	
	String bigString  = "Arjun,Reddy,Donala,shanker,shiva";
	
	     StringTokenizer ss = new StringTokenizer(bigString , ",");
	     while(ss.hasMoreTokens()){
	    	 
	    	 System.out.println(" print token " + ss.nextToken());
	     }
	    
		
	}
	
	public static String nullCheck(Object result){
		
		
		return result!=null?result.toString():"";
	}
	
	public static String VEPString(String str) {

		String[] strArray = str.split("],");
		
		StringBuffer sb = new StringBuffer();
		 
		for(int i=0; i<strArray.length ;i++){			
			str = strArray[i];
				
		if (str == null & str.trim().equals(""))
			return "";
		
		if (str.contains("(") & str.contains("")) {
			str = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
		}
		
		if(i>0)
			sb.append(",");
	     sb.append(str);
		
		}
		return sb.toString();
	}
	
	
	
}
