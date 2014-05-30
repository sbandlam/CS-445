package com.harshitha.calendar.data;
public class Appointment {
    String startDate;
    String endDate;
    String summery;
    String organizerEmailID;
    private String description;
    private String timezone;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getOrganizerEmailID() {
        return organizerEmailID;
    }

    public void setOrganizerEmailID(String organizerEmailID) {
        this.organizerEmailID = organizerEmailID;
    }

    public String getDescription() {
        return description;
    }

      public void setDescription(String description) {
        this.description = description;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
