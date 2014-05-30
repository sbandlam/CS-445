package com.harshitha.calendar.operation;

public class StringUtil {

	public static boolean isNull(String value){
		if(value==null || value.trim().equals("")){
			return true;
		}else{
			return false;
		}
	}
}
