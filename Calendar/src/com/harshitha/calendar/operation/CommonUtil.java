package com.harshitha.calendar.operation;

import java.util.Calendar;

import com.harshitha.calendar.data.Appointment;

public class CommonUtil {

	public static String getFileName(Calendar startCal){
		String filePath = startCal.get(java.util.Calendar.YEAR)+"";
		filePath += (startCal.get(java.util.Calendar.MONTH)+1);
		filePath += startCal.get(java.util.Calendar.DATE);
		filePath += startCal.get(java.util.Calendar.HOUR);
		filePath += startCal.get(java.util.Calendar.MINUTE);
		filePath += startCal.get(java.util.Calendar.SECOND);
		
		return filePath;
	}

	public static Appointment convertInputToBean(String input){
		
		Appointment detailsBean = new Appointment();

		String inputArr[] = input.split("~");
                
//                        String inputArr[];
        
	for(String s : inputArr){
            System.out.println(s);
            
        }
                
		if(inputArr.length>1){
			detailsBean.setSummery(inputArr[1]);
		}
		if(inputArr.length>2){
			detailsBean.setStartDate(inputArr[2]);
		}
		if(inputArr.length>3){
			detailsBean.setEndDate(inputArr[3]);
		}
		if(inputArr.length>4){
			detailsBean.setOrganizerEmailID(inputArr[4]);
		}
                if(inputArr.length>5){
			detailsBean.setDescription(inputArr[5]);
		}
		return detailsBean;
	}
  
}
