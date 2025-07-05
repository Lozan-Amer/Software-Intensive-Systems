package com.example.citypulseportal;

public class User {
    private String username;
    private String password;
    private UserType userType;
    private BusinessData business; // מיועד לבעלי עסקים בלבד

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public User(String username, String password, UserType userType, BusinessData business) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.business = business;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public BusinessData getBusiness() {
        return business;
    }
}
