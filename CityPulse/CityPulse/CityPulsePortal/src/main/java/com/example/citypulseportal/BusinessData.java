package com.example.citypulseportal;

public class BusinessData {

    private String name;
    private String address;
    private String phone;
    private String hours;
    private String description;
    private String imageFileName;
    private String password; // ← שדה חדש

    public BusinessData(String name, String address, String phone, String hours, String description, String imageFileName, String password) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.hours = hours;
        this.description = description;
        this.imageFileName = imageFileName;
        this.password = password;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getHours() {
        return hours;
    }

    public String getDescription() {
        return description;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
