package com.harshitha.calendar.operation;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.GregorianCalendar;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.UidGenerator;

import com.harshitha.calendar.data.Appointment;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import net.fortuna.ical4j.model.property.Description;

public class CalendarServiceImpl implements CalendarService {

    /**
     * Function to create .ics file. It will add into outlook. input format
     * should be in
     * "description~startDateTime~time~endDate_time~organizerEmailAddress format
     *
     * @param input
     * @return
     */
    @Override
    public String createIcs(Appointment detailsBean) {
        try {
            createIcsFile(detailsBean, false, null);
        } catch (Exception e) {
            e.printStackTrace();
            return PropertyReaderUtil.getProperty("ics.create.ics.errorMessage") + e.getMessage();
        }

        try {
            XMLServiceImpl xmlServiceImpl = new XMLServiceImpl();
            xmlServiceImpl.writeToXML(detailsBean);
        } catch (Exception e) {
            e.printStackTrace();
            return PropertyReaderUtil.getProperty("ics.add.xml.errorMessage") + e.getMessage();
        }
        return PropertyReaderUtil.getProperty("ics.create.ics.successMessage");
    }

    private void createIcsFile(Appointment detailsBean, boolean export, String exportFilePath) throws Exception {

        Calendar icsCalendar = new Calendar();
        File calFile = null;

        try {

            // Create a TimeZone
            TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
            String timez = detailsBean.getTimezone() == null ? PropertyReaderUtil.getProperty("ics.file.timeZone") : detailsBean.getTimezone();
            TimeZone timezone = registry.getTimeZone(timez);
            VTimeZone tz = ((net.fortuna.ical4j.model.TimeZone) timezone).getVTimeZone();

            //current date/time
            java.util.Calendar todaysDate = java.util.Calendar.getInstance();

            // Start Date
            java.util.Calendar startDateCal = new GregorianCalendar();
            startDateCal.setTimeZone(timezone);
            startDateCal = CalendarUtil.convertStringtoCalander(detailsBean.getStartDate());
            if (startDateCal.before(todaysDate)) {
                throw new Exception("Start Date can not be Past Date");
            }
            // End Date
            java.util.Calendar endDateCal = new GregorianCalendar();
            endDateCal.setTimeZone(timezone);
            endDateCal = CalendarUtil.convertStringtoCalander(detailsBean.getEndDate());
            if (endDateCal.before(todaysDate)) {
                throw new Exception("End Date can not be Past Date");
            }
            if (endDateCal.before(startDateCal)) {
                throw new Exception("End Date can not be Before Satrt Date");
            }

            // Create the event props
            DateTime start = new DateTime(startDateCal.getTime());
            DateTime end = new DateTime(endDateCal.getTime());

            // Create the event
            VEvent meeting = new VEvent(start, end, detailsBean.getSummery());

            String organizerEmailAddrs = detailsBean.getOrganizerEmailID();
            // create Organizer object and add it to vEvent
            if (StringUtil.isNull(organizerEmailAddrs)) {
                organizerEmailAddrs = PropertyReaderUtil.getProperty("ics.default.organizer.emailAddress");
            }
            Organizer organizer = new Organizer(URI.create("mailto:" + organizerEmailAddrs));
            meeting.getProperties().add(organizer);

            // add timezone to vEvent
            meeting.getProperties().add(tz.getTimeZoneId());

            // generate unique identifier and add it to vEvent
            UidGenerator ug;
            ug = new UidGenerator("uidGen");
            Uid uid = ug.generateUid();
            meeting.getProperties().add(uid);

            // add attendees..
/*			Attendee dev1 = new Attendee(URI.create("someone@something"));
             dev1.getParameters().add(Role.REQ_PARTICIPANT);
             dev1.getParameters().add(new Cn("Developer 1"));
             meeting.getProperties().add(dev1);
             */
            // assign props to calendar object
            icsCalendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 1.0//EN"));
            icsCalendar.getProperties().add(CalScale.GREGORIAN);
            Description desc = new Description();
            desc.setValue(detailsBean.getDescription());
            meeting.getProperties().add(desc);

            // Add the event and print
            icsCalendar.getComponents().add(meeting);

            CalendarOutputter outputter = new CalendarOutputter();
            outputter.setValidating(false);

            if (!export) {
                String fullFilePath = detailsBean.getSummery()+"_"+ CommonUtil.getFileName(startDateCal) + ".ics";
                File folder = new File(PropertyReaderUtil.getProperty("isc.file.location"));
                folder.mkdirs();
                calFile = new File(folder, fullFilePath);
                
                FileOutputStream fout = new FileOutputStream(calFile);
                outputter.output(icsCalendar, fout);
            }
            if (export) {
                calFile = new File(exportFilePath);
                FileOutputStream fout = new FileOutputStream(calFile, true);
                outputter.output(icsCalendar, fout);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Function to create .ics file. It will add into outlook. input format
     * should be in
     * "description~startDateTime~time~endDate_time~organizerEmailAddress format
     *
     * @param input
     * @return
     */
    @Override
    public String editIcs(Appointment detailsBean) {
        try {
            editIcsEntry(detailsBean);
        } catch (Exception e) {
            e.printStackTrace();
            return PropertyReaderUtil.getProperty("ics.edit.ics.errorMessage") + e.getMessage();
        }
        return PropertyReaderUtil.getProperty("ics.edit.ics.successMessage");
    }

    private void editIcsEntry(Appointment detailsBean) throws Exception {

        //Delete Existing .ics file and remove Entry from .xml file
        deleteIcsEntry(detailsBean);

        //Call createIcs to again create.ics file
        createIcs(detailsBean);
    }

    /**
     * Delete the .ics file and recreate it
     *
     * @param detailsBean
     * @throws Exception
     */
    public String deleteIcsEntry(Appointment detailsBean) {
        try {
            //Delete Existing .ics File		 
            String icsFilePath = PropertyReaderUtil.getProperty("isc.file.location") + detailsBean.getSummery()+"_"+CommonUtil.getFileName(CalendarUtil.convertStringtoCalander(detailsBean.getStartDate())) + ".ics";
            File file = new File(icsFilePath);
            if (file.exists()) {
                file.delete();
                //Remove Record from XML File
                XMLServiceImpl serviceImpl = new XMLServiceImpl();
                serviceImpl.deleteDataByStartDate(detailsBean.getStartDate());
                return PropertyReaderUtil.getProperty("ics.delete.ics.successMessage");
            } else {
                return PropertyReaderUtil.getProperty("ics.delete.ics.errorMessage");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PropertyReaderUtil.getProperty("ics.delete.ics.errorMessage") + e.getMessage();
        }

    }

    @Override
    public int searchIcs(String inputArr[]) {

        try {
            XMLServiceImpl serviceImpl = new XMLServiceImpl();
            serviceImpl.search(inputArr[1], inputArr[2], false, true);
            return 1;
        } catch (Exception e) {
            System.out.println("Problem with Searching " + e.getMessage());
            return 0;
        }
    }

    @Override
    public String exportIcs(String data[]) {

        try {
            String key = "";
            String value = "";
            String filePath = "";
            boolean all = false;
            if (data.length == 2) {
                all = true;
                filePath = data[1];
            } else if (data.length == 4) {
                key = data[1];
                value = data[2];
                filePath = data[3];
            } else {
                return "invalid format. Valid formats export~<filepath> (or) export~<field>~<searchterm>~<filepath>";
            }
            XMLServiceImpl serviceImpl = new XMLServiceImpl();
            List<Appointment> appointmentList = serviceImpl.search(key, value, all, false);
            for (Appointment appointment : appointmentList) {
                createIcsFile(appointment, true, filePath);
            }
            return PropertyReaderUtil.getProperty("ics.export.ics.successMessage");
        } catch (Exception e) {
            return PropertyReaderUtil.getProperty("ics.export.ics.errorMessage");
        }
    }

    @Override
    public String importIcs(String[] data) {
        try {
            String filePath = "";
            if (data.length == 2) {
                filePath = data[1];
            } else {
                return "invalid format. Valid formats import~<filepath>";
            }
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = null;

            while ((line = br.readLine()) != null) {
                if (line.toUpperCase().startsWith("BEGIN:VEVENT")) {
                    String startDate = null;
                    String endDate = null;
                    String summary = null;
                    String description = null;
                    String email = null;
                    String timeZone = null;
                    while ((line = br.readLine()) != null) {
                        if (line.toUpperCase().startsWith("END:VEVENT")) {
                            break;
                        }
                        if (line.toUpperCase().startsWith("DTSTART")) {
                            startDate = line.substring(line.indexOf(":") + 1);
                        }
                        if (line.toUpperCase().startsWith("DTEND")) {
                            endDate = line.substring(line.indexOf(":") + 1);
                        }
                        if (line.toUpperCase().startsWith("SUMMARY")) {
                            summary = line.substring(line.indexOf(":") + 1);
                        }
                        if (line.toUpperCase().startsWith("ORGANIZER")) {
                            email = line.substring(line.indexOf(":") + 1);
                        }
                        if (line.toUpperCase().startsWith("DESCRIPTION")) {
                            description = line.substring(line.indexOf(":") + 1);
                        }
                        if (line.toUpperCase().startsWith("TZID")) {
                            timeZone = line.substring(line.indexOf(":") + 1);
                        }
                    }

                    Appointment app = new Appointment();
                    app.setDescription(description);
                    app.setOrganizerEmailID(email);
                    app.setSummery(summary);
                    app.setStartDate(startDate);
                    app.setEndDate(endDate);
                    app.setTimezone(timeZone);
                    createIcs(app);
                }
            }
            br.close();
            return PropertyReaderUtil.getProperty("ics.import.ics.successMessage");
        } catch (Exception e) {
            return PropertyReaderUtil.getProperty("ics.import.ics.errorMessage");
        }
    }
}
/*
 DTSTAMP:20140526T151047Z
 DTSTART:20140529T103000
 DTEND:20140529T123000
 SUMMARY:Project Requirements
 ORGANIZER:mailto:9harshitha@gmail.com
 TZID:America/Chicago
 UID:20140526T151047Z-uidGen@fe80:0:0:0:227:eff:fe0c:82cf%2
 DESCRIPTION:Project requirements discussion
 */
