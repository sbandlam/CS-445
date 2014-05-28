package com.harshitha.calendar.operation;

import com.harshitha.calendar.data.Appointment;
import java.util.List;

public interface XMLService {
	public void writeToXML(Appointment detailsBean)throws Exception;
	//public InputDetailsBean loadDataByStartDate(String startDate)throws Exception;
	public Appointment editDataByStartDate(Appointment newBean)throws Exception;
	public boolean deleteDataByStartDate(String startDate)throws Exception;
    public List<Appointment> search(String searchAttribute, String searchKeyword, boolean all, boolean print)throws Exception;
}
