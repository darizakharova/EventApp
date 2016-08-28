package com.wukker.sb.eventconnectionfortopmobile.model;

import com.wukker.sb.eventconnectionfortopmobile.model.EventType;
import com.wukker.sb.eventconnectionfortopmobile.model.Staff;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by sb on 02.11.15.
 */
public class Event extends Base implements Serializable {
    Long id;
    Long dateCreated;
    Long lastUpdated;
    Long conferenceID;
    Long dateStart;
    Long dateEnd;
    EventType type;
    Integer countedRating;
    String name;
    String brief;
    ArrayList<Staff> staffs;


    public Event(long id, long conferenceID, String name, EventType eventType) {
        this.id = id;
        this.conferenceID = conferenceID;
        this.name = name;
        this.type = eventType;
    }

    public Event(long id, long conferenceID, long dateStart, long dateEnd, EventType eventType, int countedRating, String name, String brief , ArrayList<Staff> allStaff) {
        this.id = id;
        this.conferenceID = conferenceID;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.type = eventType;
        this.countedRating = countedRating;
        this.name = name;
        this.brief = brief;
        this.staffs = allStaff;
    }

    public long getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(long conferenceID) {
        this.conferenceID = conferenceID;
    }

    public long getDateStart() {
        return dateStart;
    }

    public void setDateStart(long dateStart) {
        this.dateStart = dateStart;
    }

    public long getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(long dateEnd) {
        this.dateEnd = dateEnd;
    }

    public EventType getEventType() {
        return type;
    }

    public void setEventType(EventType eventType) {
        this.type = eventType;
    }

    public int getCountedRating() {
        return countedRating;
    }

    public void setCountedRating(int countedRating) {
        this.countedRating = countedRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public ArrayList<Staff> getAllStaff() {
        return staffs;
    }

    public void setAllStaff(ArrayList<Staff> allStaff) {
        this.staffs = allStaff;
    }

    public String toRString() {
        SimpleDateFormat formatter = new SimpleDateFormat("KK:mm", Locale.ENGLISH);
        String dateStartString = formatter.format(this.getDateStart()*1000+8*60*60*1000);
        String dateEndString = formatter.format(this.getDateEnd()*1000+8*60*60*1000);

        return "\n" + name + "\n" + brief + "\n" + dateStartString + " - " + dateEndString;
    }

    public String toTimeString(){
        SimpleDateFormat formatter = new SimpleDateFormat("KK:mm", Locale.ENGLISH);
        String dateStartString = formatter.format((this.getDateStart()*1000)+(8*60*60*1000));
        String dateEndString = formatter.format((this.getDateEnd()*1000)+(8*60*60*1000));
        return dateStartString + " - " + dateEndString;
    }

    public String toListName()
    {
        return "\n" + name + "\n";
    }
    public String toListBrief(){return  "\n" + brief + "\n";}

    public long getId() {
        return id;
    }
}
