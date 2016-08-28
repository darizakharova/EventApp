package com.wukker.sb.eventconnectionfortopmobile.model;

import java.io.Serializable;

/**
 * Created by sb on 29.10.15.
 */
public class User extends Base implements Serializable
{
    private Long id;
    private String firstname;
    private String lastname;
    private String middlename;
    private String companyName;
    private String email;
    private String phone;
    private String oauthToken;
    private Boolean visible;
    private Long oauthTokenExpireDate;
    private String photo;
    private String socialID;
    private String position;
    private Long dateCreated;
    private Long lastUpdated;


    public User()
    {

    }

    public User(String firstname, String lastname, String oauthToken, long oauthTokenExpireDate, String socialID) {
        this.id = null;
        this.lastname = lastname;
        this.firstname = firstname;
        this.oauthToken = oauthToken;
        this.oauthTokenExpireDate = oauthTokenExpireDate;
        this.socialID = socialID;
        this.visible = true;
        this.companyName = "";
        this.email = "";
        this.middlename = "";
        this.position = "";
        this.photo = "";
        this.phone = "";


    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getPosition() {
        return position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public String getPhoto() {
        return photo;
    }

    public String getSocialID() {
        return socialID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setOauthTokenExpireDate(long oauthTokenExpireDate) {
        this.oauthTokenExpireDate = oauthTokenExpireDate;
    }

    public void setSocialID(String socialID) {
        this.socialID = socialID;
    }
}
