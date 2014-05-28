package com.harshitha.calendar.operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

	public static Calendar convertStringtoCalander(String dateStr){		
		String pattern = "yyyy-MM-dd-HH:mm:ss";	
                String pattern1 = "yyyyMMdd-hhmmss";
		java.util.Calendar calendar = null;
                
		try {
			Date date = new SimpleDateFormat(dateStr == null || !dateStr.contains("T") ? pattern : pattern1).parse(dateStr == null ? dateStr : dateStr.replace("T","-"));
			calendar = java.util.Calendar.getInstance();
			calendar.setTime(date);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		return calendar;
	}
}
