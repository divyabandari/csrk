package com.csrk.pokerapp;

public class MonthStats {
	
	/** 
	 * int representation of month. 0 for january and 11 for december
	 */
	int month=-1;
	int monthBuyIn=0;
	int monthExpenses=0;
	int monthCashOut=0;
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getMonthBuyIn() {
		return monthBuyIn;
	}

	public void setMonthBuyIn(int monthBuyIn) {
		this.monthBuyIn = monthBuyIn;
	}

	public int getMonthExpenses() {
		return monthExpenses;
	}

	public void setMonthExpenses(int monthExpenses) {
		this.monthExpenses = monthExpenses;
	}

	public int getMonthCashOut() {
		return monthCashOut;
	}

	public void setMonthCashOut(int monthCashOut) {
		this.monthCashOut = monthCashOut;
	}

	public int getMonthNetProfit(){
		return monthCashOut - monthBuyIn - monthExpenses;
	}
	public int getMonthProfit(){
		return monthCashOut - monthBuyIn;
	}

	@Override
	public String toString() {
		StringBuffer monthStatsString = new StringBuffer();
		monthStatsString.append("Month: "+month+" ");
		monthStatsString.append("Net Profit: "+getMonthNetProfit()+" ");
		monthStatsString.append(" Profit: "+getMonthProfit()+" ");
		monthStatsString.append(" Expenses: "+getMonthExpenses()+" ");
		return monthStatsString.toString();
	}
	
	
}
