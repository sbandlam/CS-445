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
public class ExportTest5 {
    
    public ExportTest5() {
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

      @Test
    public void testExportIcs() {
        System.out.println("exportIcs");
        String term = "export~/home/sankalpa/Desktop/calendarFiles/exp5.ics";
        String[] data = term.split("~");
        CalendarServiceImpl instance = new CalendarServiceImpl();
        String expResult = PropertyReaderUtil.getProperty("ics.export.ics.successMessage");
        String result = instance.exportIcs(data);
        assertEquals(expResult, result);
    }

       
}
