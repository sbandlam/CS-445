/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.harshitha.calendar.operation;

import com.harshitha.calendar.data.Appointment;
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
public class AddTest24 {
    
    public AddTest24() {
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
        String command = "add~requirements 24~2014-06-24-12:30:00~2014-06-24-13:30:00~test24@test.com~req desc24";
        Appointment detailsBean = CommonUtil.convertInputToBean(command);
        CalendarServiceImpl instance = new CalendarServiceImpl();
        String expResult = PropertyReaderUtil.getProperty("ics.create.ics.successMessage");
        String result = instance.createIcs(detailsBean);
        assertEquals(expResult, result);
    }

       
}