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
public class SearchTest1 {
    
    public SearchTest1() {
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
//    @Test
//    public void testCreateIcs() {
//        System.out.println("createIcs");
//        String command = "add~requirements 6~2014-06-06-12:30:00~2014-06-06-13:30:00~test6@test.com~req desc 6";
//        Appointment detailsBean = CommonUtil.convertInputToBean(command);
//        CalendarServiceImpl instance = new CalendarServiceImpl();
//        String expResult = PropertyReaderUtil.getProperty("ics.create.ics.successMessage");
//        String result = instance.createIcs(detailsBean);
//        assertEquals(expResult, result);
//    }
 @Test
    public void testSearchIcs() {
        System.out.println("searchIcs");
        String term = "search~description~req desc 1";
        String[] inputArr = term.split("~");
        CalendarServiceImpl instance = new CalendarServiceImpl();
        int expResult = 1;
        int result = instance.searchIcs(inputArr);
        assertEquals(expResult, result);
    }
       
}
