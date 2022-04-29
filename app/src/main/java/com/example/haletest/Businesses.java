package com.example.haletest;

import java.io.Serializable;

// SearchView references: https://abhiandroid.com/ui/searchview
// https://github.com/codepath/android_guides/wiki/Book-Search-Tutorial
// https://www.javatpoint.com/android-searchview

public class Businesses implements Serializable {
    private String businessName;
    private String description;
    private String imageURL;
    private String location;
    private String hours;
    private String website;

    public Businesses(String businessName, String description, String imageURL, String location, String hours, String website) {

        this.businessName = businessName;
        this.description = description;
        this.imageURL = imageURL;
        this.location = location;
        this.hours = hours;
        this.website = website;
    }

    public String getBusinessName() {

        return this.businessName;
    }

    public String getDescription() {

        return this.description;
    }

    public String getLocation() {

        return this.location;
    }

    public String getImageURL() {

        return this.imageURL;
    }

    public String getHours() {

        return this.hours;
    }

    public String getWebsite() {

        return this.website;
    }
}