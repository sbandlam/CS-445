package com.harshitha.calendar.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.harshitha.calendar.operation.XMLService;
import com.harshitha.calendar.data.XMLConstants;
import com.harshitha.calendar.operation.CalendarService;
import com.harshitha.calendar.data.Appointment;
import com.harshitha.calendar.operation.CalendarUtil;
import com.harshitha.calendar.operation.CommonUtil;
import com.harshitha.calendar.operation.PropertyReaderUtil;
import com.harshitha.calendar.operation.StringUtil;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XMLServiceImpl implements XMLService {

    public void writeToXML(Appointment detailsBean) throws Exception {

        java.util.Calendar startDateCal = new GregorianCalendar();
        startDateCal = CalendarUtil.convertStringtoCalander(detailsBean.getStartDate());
        String fileName = CommonUtil.getFileName(startDateCal);

        Document document = null;
        Element root = null;

        File xmlFile = new File(PropertyReaderUtil.getProperty("ics.xml.file.absolute.path"));
        if (xmlFile.exists()) {
            FileInputStream fis = new FileInputStream(xmlFile);
            SAXBuilder sb = new SAXBuilder();
            document = sb.build(fis);
            root = document.getRootElement();
            fis.close();
        } else {
            // if it does not exist create a new document and new root
            document = new Document();
            root = new Element(XMLConstants.ICS_DETAILS);
        }
        Element staff = new Element(XMLConstants.FILE_DETAIL);
        staff.setAttribute(new Attribute("id", fileName));
        staff.addContent(new Element(XMLConstants.SUMMARY).setText(detailsBean.getSummery()));
        staff.addContent(new Element(XMLConstants.ORGANIZER_EMAIL_ID).setText(detailsBean.getOrganizerEmailID()));
        staff.addContent(new Element(XMLConstants.START_DATE).setText(detailsBean.getStartDate()));
        staff.addContent(new Element(XMLConstants.END_DATE).setText(detailsBean.getEndDate()));
        staff.addContent(new Element(XMLConstants.DESCRIPTION).setText(detailsBean.getDescription()));
        root.addContent(staff);
        document.setContent(root);
        FileWriter writer = null;
        try {
            writer = new FileWriter(PropertyReaderUtil.getProperty("ics.xml.file.absolute.path"));
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, writer);
            //outputter.output(document, System.out);
        } catch (Exception e) {
            throw e;
        } finally {
            writer.close();
        }

    }

    @Override
    public Appointment editDataByStartDate(Appointment newBean) throws Exception {

        File xmlFile = new File(PropertyReaderUtil.getProperty("ics.xml.file.absolute.path"));
        Document document = null;
        Element root = null;
        Appointment existingBean = null;
        if (xmlFile.exists()) {
            FileInputStream fis = new FileInputStream(xmlFile);
            SAXBuilder sb = new SAXBuilder();
            document = sb.build(fis);
            root = document.getRootElement();
            List<Element> elementList = root.getChildren(XMLConstants.FILE_DETAIL);
            for (Element element : elementList) {
                //System.out.println(element.getAttributeValue("id") +"   "+CommonUtil.getFileName(CalanderUtil.convertStringtoCalander(newBean.getStartDate())));
                if (element.getAttributeValue("id").equalsIgnoreCase(CommonUtil.getFileName(CalendarUtil.convertStringtoCalander(newBean.getStartDate())))) {
                    existingBean = new Appointment();

                    existingBean.setStartDate(element.getChildText(XMLConstants.START_DATE));
                    existingBean.setEndDate(element.getChildText(XMLConstants.END_DATE));
                    existingBean.setSummery(element.getChildText(XMLConstants.SUMMARY));
                    existingBean.setOrganizerEmailID(element.getChildText(XMLConstants.ORGANIZER_EMAIL_ID));

                    if (!StringUtil.isNull(newBean.getEndDate())) {
                        existingBean.setEndDate(newBean.getEndDate());
                        element.getChild(XMLConstants.END_DATE).setText(newBean.getEndDate());
                    }
                    if (!StringUtil.isNull(newBean.getSummery())) {
                        existingBean.setSummery(newBean.getSummery());
                        element.getChild(XMLConstants.SUMMARY).setText(newBean.getSummery());
                    }
                    if (!StringUtil.isNull(newBean.getOrganizerEmailID())) {
                        existingBean.setOrganizerEmailID(newBean.getOrganizerEmailID());
                        element.getChild(XMLConstants.ORGANIZER_EMAIL_ID).setText(newBean.getOrganizerEmailID());
                    }

                    if (!StringUtil.isNull(newBean.getDescription())) {
                        existingBean.setDescription(newBean.getDescription());
                        element.getChild(XMLConstants.DESCRIPTION).setText(newBean.getDescription());
                    }

                    XMLOutputter xmlOutput = new XMLOutputter();

                    // display nice nice
                    xmlOutput.setFormat(Format.getPrettyFormat());
                    xmlOutput.output(document, new FileWriter(xmlFile));

                    break;
                }
            }
            fis.close();
        }
        return existingBean;
    }

    @Override
    public boolean deleteDataByStartDate(String startDate) throws Exception {
        File xmlFile = new File(PropertyReaderUtil.getProperty("ics.xml.file.absolute.path"));
        Document document = null;
        Element root = null;
        boolean isDeleted = false;
        if (xmlFile.exists()) {
            FileInputStream fis = new FileInputStream(xmlFile);
            SAXBuilder sb = new SAXBuilder();
            document = sb.build(fis);
            root = document.getRootElement();
            List<Element> elementList = root.getChildren(XMLConstants.FILE_DETAIL);
            int i = 0;
            for (Element element : elementList) {
                i++;
                //System.out.println(element.getAttributeValue("id") +"   "+CommonUtil.getFileName(CalanderUtil.convertStringtoCalander(startDate)));
                if (element.getAttributeValue("id").equalsIgnoreCase(CommonUtil.getFileName(CalendarUtil.convertStringtoCalander(startDate)))) {
                    isDeleted = root.removeContent(element);
                    XMLOutputter xmlOutput = new XMLOutputter();
                    xmlOutput.setFormat(Format.getPrettyFormat());
                    xmlOutput.output(document, new FileWriter(xmlFile));
                    break;
                }
            }
            fis.close();
        }
        return isDeleted;
    }

    @Override
    public List<Appointment> search(String searchAttribute, String searchKeyword, boolean all, boolean print) throws Exception {
        File xmlFile = new File(PropertyReaderUtil.getProperty("ics.xml.file.absolute.path"));
        Document document = null;
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        Element root = null;
        if (xmlFile.exists()) {
            FileInputStream fis = new FileInputStream(xmlFile);
            SAXBuilder sb = new SAXBuilder();
            document = sb.build(fis);
            root = document.getRootElement();
            List<Element> elementList = root.getChildren(XMLConstants.FILE_DETAIL);
            
            boolean isSearchFound = false;
            for (Element element : elementList) {
                if (all || element.getChildText(searchAttribute).toLowerCase().contains(searchKeyword.toLowerCase())) {
                    isSearchFound = true;

                    Appointment appointment = new Appointment();
                    appointment.setDescription(element.getChildText(XMLConstants.DESCRIPTION));
                    appointment.setSummery(element.getChildText(XMLConstants.SUMMARY));
                    appointment.setStartDate(element.getChildText(XMLConstants.START_DATE));
                    appointment.setEndDate(element.getChildText(XMLConstants.END_DATE));
                    appointment.setOrganizerEmailID(element.getChildText(XMLConstants.ORGANIZER_EMAIL_ID));
                    appointmentList.add(appointment);
                }
            }
            if (print) {
                System.out.println("Total appointments found: " + appointmentList.size());
                int no =0;
                for (Appointment appointment : appointmentList) {
                    System.out.println("\n\nAppoinment Details " + (++no));
                    System.out.println("Summery :: " + appointment.getSummery());
                    System.out.println("Start Date :: " + appointment.getStartDate());
                    System.out.println("End Date :: " + appointment.getEndDate());
                    System.out.println("Email ID :: " + appointment.getOrganizerEmailID());
                    System.out.println("Description :: " + appointment.getDescription() + "\n");
                }
            }
            if (!isSearchFound) {
                System.out.println("No Search Found.");
            }
            fis.close();
        }
        return appointmentList;
    }

    public static void main(String args[]) throws Exception {
        XMLServiceImpl impl = new XMLServiceImpl();
        /*	Appointment newBean = new InputDetailsBean();
        newBean.setStartDate("2014-04-06-14:56:34");
        newBean.setEndDate("2015-04-07-15:56:34");
        newBean.setSummery("This is MAA TML Descriptions");
        impl.editDataByStartDate(newBean);*/
        //newBean.setOrganizerEmailID("");
        //impl.deleteDataByStartDate("2014-05-13-14:56:34");
        impl.search("summery", "TML", false, true);
    }
}
