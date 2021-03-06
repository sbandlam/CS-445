/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.harshitha.calendar.test;

import com.harshitha.calendar.data.Appointment;
import com.harshitha.calendar.operation.CalendarServiceImpl;
import com.harshitha.calendar.operation.CommonUtil;
import com.harshitha.calendar.operation.PropertyReaderUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apple
 */
public class CalendarServiceImplTest {
    
    public CalendarServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createIcs method, of class CalendarServiceImpl.
     */
    @Test
    public void testCreateIcs() {
        System.out.println("createIcs");
        String command = "add~requirements~2014-06-01-12:30:00~2014-06-01-13:30:00~test@test.com~req";
        Appointment detailsBean = CommonUtil.convertInputToBean(command);
        CalendarServiceImpl instance = new CalendarServiceImpl();
        String expResult = PropertyReaderUtil.getProperty("ics.create.ics.successMessage");
        String result = instance.createIcs(detailsBean);
        assertEquals(expResult, result);
    }

    /**
     * Test of editIcs method, of class CalendarServiceImpl.
     */

    @Test
    public void testEditIcs() {
        System.out.println("editIcs");
        String command = "edit~requirements~2014-06-01-12:30:00~2014-06-01-13:30:00~test@test.com~req1";
        Appointment detailsBean = CommonUtil.convertInputToBean(command);
        CalendarServiceImpl instance = new CalendarServiceImpl();
        String expResult = PropertyReaderUtil.getProperty("ics.edit.ics.successMessage");
        String result = instance.editIcs(detailsBean);
        assertEquals(expResult, result);
    }

    
    @Test
    public void testDeleteIcsEntry() {
        System.out.println("deleteIcsEntry");
        String command = "delete~requirements~2014-06-01-12:30:00";
        Appointment detailsBean = CommonUtil.convertInputToBean(command);
        CalendarServiceImpl instance = new CalendarServiceImpl();
        String expResult = PropertyReaderUtil.getProperty("ics.delete.ics.successMessage");
        String result = instance.deleteIcsEntry(detailsBean);
        assertEquals(expResult, result);
        
        
    }

    @Test
    public void testSearchIcs() {
        System.out.println("searchIcs");
        String term = "search~description~req";
        String[] inputArr = term.split("~");
        CalendarServiceImpl instance = new CalendarServiceImpl();
        int expResult = 1;
        int result = instance.searchIcs(inputArr);
        assertEquals(expResult, result);
    }

    
    @Test
    public void testExportIcs() {
        System.out.println("exportIcs");
        String term = "export~/home/sai/Calendar/Calendar/calendarFiles/myapp.ics";
        String[] data = term.split("~");
        CalendarServiceImpl instance = new CalendarServiceImpl();
        String expResult = PropertyReaderUtil.getProperty("ics.export.ics.successMessage");
        String result = instance.exportIcs(data);
        assertEquals(expResult, result);
    }

    @Test
    public void testImportIcs() {
        System.out.println("importIcs");
        String term = "import~/home/sai/Calendar/Calendar/calendarFiles/myapp.ics";
        String[] data = term.split("~");
        CalendarServiceImpl instance = new CalendarServiceImpl();
        String expResult = PropertyReaderUtil.getProperty("ics.import.ics.successMessage");
        String result = instance.importIcs(data);
        assertEquals(expResult, result);
    }

}
