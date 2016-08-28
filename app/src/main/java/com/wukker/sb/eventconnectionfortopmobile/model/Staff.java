package com.wukker.sb.eventconnectionfortopmobile.model;

import com.wukker.sb.eventconnectionfortopmobile.model.StaffType;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;

import java.io.Serializable;

/**
 * Created by sb on 02.11.15.
 */
public class Staff extends Base implements Serializable {
    private long id;
    private long dateCreated;
    private long lastUpdated;
    private String firstname;
    private String lastname;
    private String middlename;
    private String photo;
    private String description;
    private int countedRating;
    private int countedVoters;
    private StaffType type;

    public Staff(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    public Staff(long id, String firstName, String lastName, String middleName, String description, StaffType staffType) {
        this.id = id;
        this.firstname = firstName;
        this.lastname = lastName;
        this.middlename = middleName;
        this.description = description;
        this.type = staffType;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getMiddleName() {
        return middlename;
    }

    public void setMiddleName(String middleName) {
        this.middlename = middleName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCountedRating() {
        return countedRating;
    }

    public void setCountedRating(int countedRating) {
        this.countedRating = countedRating;
    }

    public int getCountedVoters() {
        return countedVoters;
    }

    public void setCountedVoters(int countedVoters) {
        this.countedVoters = countedVoters;
    }

    public StaffType getStaffType() {
        return type;
    }

    public void setStaffType(StaffType staffType) {
        this.type = staffType;
    }

    public String toListString()
    {
        return ("\n" + firstname + " " + middlename + " " + lastname + "\n" + description + "\n" + type);
    }

    public long getId() {
        return id;
    }
}

