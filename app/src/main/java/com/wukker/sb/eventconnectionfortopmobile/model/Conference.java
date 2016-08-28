package com.wukker.sb.eventconnectionfortopmobile.model;

import com.wukker.sb.eventconnectionfortopmobile.model.Event;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sb on 02.11.15.
 */
public class Conference<T extends Base> extends Base implements Serializable {
    private long id;
    private long dateCreated;
    private long lastUpdated;
    private long dateStart;
    private long dateEnd;
    private boolean visible;
    private String name;
    private String brief;
    private String url;
    private ArrayList<Event> events;

    public Conference(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Conference(long id, long dateStart, long dateEnd, boolean visible,
                      String name, String brief, String url, ArrayList<Event> events) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.visible = visible;
        this.name = name;
        this.brief = brief;
        this.url = url;
        this.events = events;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Event> getAllEvents() {
        return events;
    }

    public void setAllEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
