package com.eshraq.gosport.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "user")
public class User {

    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField
    private String name;

    @DatabaseField(columnName = "email")
    private String email;

    @DatabaseField(columnName = "avatar")
    private String avatar;

    @DatabaseField(columnName = "facebook_account_id")
    private String facebookAccountId;

    @DatabaseField(columnName = "google_plus_account_id")
    private String googlePlusAccountId;

    @DatabaseField(columnName = "facebook_access_token")
    private String facebookAccessToken;

    ////////////////////////////////////////////////////////////////////


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookAccountId() {
        return facebookAccountId;
    }

    public void setFacebookAccountId(String facebookAccountId) {
        this.facebookAccountId = facebookAccountId;
    }

    public String getGooglePlusAccountId() {
        return googlePlusAccountId;
    }

    public void setGooglePlusAccountId(String googlePlusAccountId) {
        this.googlePlusAccountId = googlePlusAccountId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFacebookAccessToken() {
        return facebookAccessToken;
    }

    public void setFacebookAccessToken(String facebookAccessToken) {
        this.facebookAccessToken = facebookAccessToken;
    }
}
