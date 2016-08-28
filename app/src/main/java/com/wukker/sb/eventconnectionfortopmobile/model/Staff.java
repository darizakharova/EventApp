package com.wukker.sb.eventconnectionfortopmobile.model;

import com.wukker.sb.eventconnectionfortopmobile.model.StaffType;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;

import java.io.Serializable;

/**
 * Created by sb on 02.11.15.
 */
public class Staff extends Base implements Serializable {
    Long id;
    Long dateCreated;
    Long lastUpdated;
    String firstname;
    String lastname;
    String middlename;
    String photo;
    String description;
    Integer countedRating;
    Integer countedVoters;
    StaffType type;

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
        return ("\n" + firstname + Constants.space + middlename + Constants.space + lastname + "\n" + description + "\n" + type);
    }

    public Long getId() {
        return id;
    }
}

