package com.example.citypulse;

public class PointOfInterest {
    private final String name;
    private final String location;
    private final String hours;
    private final String phone;
    private final String imagePath;

    public PointOfInterest(String name, String location, String hours, String phone, String imagePath) {
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.phone = phone;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getHours() { return hours; }
    public String getPhone() { return phone; }
    public String getImagePath() { return imagePath; }
}
