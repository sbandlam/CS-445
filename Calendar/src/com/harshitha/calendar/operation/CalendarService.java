package com.harshitha.calendar.operation;

import com.harshitha.calendar.data.Appointment;

public interface CalendarService {

	public String createIcs(Appointment detailsBean);
	public String editIcs(Appointment detailsBean);
	public String deleteIcsEntry(Appointment detailsBean);
	public int searchIcs(String inputArr[]);
        
        public String exportIcs(String data[]);
        public String importIcs(String data[]);
}
