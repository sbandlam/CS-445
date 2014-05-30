/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harshitha.calendar.ui;

import com.harshitha.calendar.data.Appointment;
import com.harshitha.calendar.operation.CalendarService;
import com.harshitha.calendar.operation.CalendarServiceImpl;
import com.harshitha.calendar.operation.CommonUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalendarApllication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String appointment = null;
        CalendarService calendar = new CalendarServiceImpl();
        int choice = 0;
        do {
            printUI();
            System.out.println("Enter your choice:");
            try {
                choice = Integer.valueOf(br.readLine());
            } catch (NumberFormatException ex) {
                System.out.println("You have entered invalid input.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter operation command:");
                    appointment = br.readLine();
                    Appointment newApp = CommonUtil.convertInputToBean(appointment);
                    System.out.println(calendar.createIcs(newApp));
                    break;
                case 2:
                    System.out.println("Enter operation command:");
                    appointment = br.readLine();
                    Appointment newApp2 = CommonUtil.convertInputToBean(appointment);
                    System.out.println(calendar.editIcs(newApp2));
                    break;
                case 3:
                    System.out.println("Enter operation command:");
                    appointment = br.readLine();
                    Appointment newApp3 = CommonUtil.convertInputToBean(appointment);
                    System.out.println(calendar.deleteIcsEntry(newApp3));
                    break;
                case 4:
                    System.out.println("Enter operation command:");
                    appointment = br.readLine();
                    calendar.searchIcs(appointment.split("~"));
                    break;
                case 5:
                    System.out.println("Enter operation command:");
                    appointment = br.readLine();
                    System.out.println(calendar.importIcs(appointment.split("~")));
                    break;
                case 6:
                    System.out.println("Enter operation command:");
                    appointment = br.readLine();
                    System.out.println(calendar.exportIcs(appointment.split("~")));
                    break;
                case 7:
                    System.out.println("\n\nThank you");
                    System.out.println("Enter any key to exit...");
                    br.readLine();
                    System.exit(0);
                    break;


            }
        } while (choice != 7);
    }

    private static void printUI() {
        System.out.println("Calendar Operations");
        System.out.println("1.Add Appointment - add~name~starttime~endtime~email~desc");
        System.out.println("2.Edit Appointment - edit~name~starttime~endtime~email~desc");
        System.out.println("3.Delete Appointment - delete~name~starttime");
        System.out.println("4.Search Appointment - search~field~searchterm");
        System.out.println("5.Import Appointment - import~filename");
        System.out.println("6.Export Appointment - export~filename (or) export~field~searchterm~filename");
        System.out.println("7.Exit");
    }
}
