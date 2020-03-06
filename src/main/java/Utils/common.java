package Utils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class common {
	int monthNum;
//take input from yyyy-mm-dd
	public boolean isHoliday(Calendar cal) {
		// check if weekend
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return true;
		}
		
	// check if New Year's Day
		if (cal.get(Calendar.MONTH) == Calendar.JANUARY
			&& cal.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		
		// check if Christmas
		if (cal.get(Calendar.MONTH) == Calendar.DECEMBER
			&& cal.get(Calendar.DAY_OF_MONTH) == 25) {
			return true;
		}
		return false;
	}
	
	public int getMonthNumber(String month) {
		
		switch(month) {
        case "january":
        case "jan":
        	monthNum=Calendar.JANUARY;
        break;

        case "febuary":
        case "feb":
        	monthNum= Calendar.FEBRUARY;
        break;

        case "march":
        case "mar":
        	monthNum= Calendar.MARCH;
        break;

        case "april":
        case "apr":
        	monthNum= Calendar.APRIL;
        break;
        case "may":
        	monthNum= Calendar.MAY;
        break;
        case "jun":
        case "june":
        	monthNum= Calendar.JUNE;
        break;
        
        case "jul":
        case "july":
        	monthNum= Calendar.JULY;
        break;
        
        case "aug":
        case "august":
        	monthNum= Calendar.AUGUST;
        break;
        
        case "sep":
        case "september":
        	monthNum= Calendar.SEPTEMBER;
        break;
        
        case "oct":
        case "october":
        	monthNum= Calendar.OCTOBER;
        break;
        
        case "nov":
        case "november":
        	monthNum= Calendar.NOVEMBER;
        break;
        
        default:
        	monthNum = 11;
	}
		return monthNum;
	}
	
	//This converts 16-04-2020 to 16 April 2020
	public String convertStringToDateFormat(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 Date date=new Date();
		 try {
			date = formatter.parse(readProperty("EventDate"));
	} catch (java.text.ParseException e) {
			 System.out.println("Please provide event date in dd-mm-yyyy format");
		}
	    SimpleDateFormat fo = new SimpleDateFormat("dd MMMM yyyy");
	return fo.format(date);	 
	}
	
	public String convertToText(String event,int start,int end) {
		
		int x=Integer.parseInt(readProperty(event).substring(start,end));
		return String.valueOf(x);
	}
	
	public boolean isAM(String prop) {
		if((readProperty(prop).substring(6)).equalsIgnoreCase("am")){
			return true;
		}
		else return false;
	}
	
public int getMonthDiff(String getMonthTxt) {
		
		int actualMonth=getMonthNumber(getMonthTxt.toLowerCase())+1;
		int expMonth=Integer.parseInt(readProperty("EventDate").substring(3,5));
		int diff=expMonth-actualMonth;
		return diff;
	}
	
	
	public String readProperty(String property) {
		Properties prop;
		String value = null;
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("/Users/madhurmidha/eclipse-workspace/AppiumHDB/src/config.properties")));

			value = prop.getProperty(property);

			if (value == null || value.isEmpty()) {
				throw new Exception("Value not set or empty");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
		
}
