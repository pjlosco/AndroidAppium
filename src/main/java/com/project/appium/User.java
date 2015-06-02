package com.project.appium;

public class User {

    private String email;
    private String fullName;
    private String phoneNumber;
    private String password;
    private String platform;
    private String userID;
    private String accessToken;

    public User(String email, String fullName, String phoneNumber, String password, String platform, String userID) {
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.platform = platform;
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getPlatform() {
        return platform;
    }

    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String toString() {
        return this.fullName;
    }
}
