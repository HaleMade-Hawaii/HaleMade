package com.example.haletest;

import java.io.Serializable;

public class Businesses implements Serializable {
    private String businessName;
    private String description;
    private String imageURL;
    private String location;

    public Businesses(String businessName, String description, String imageURL, String location) {

        this.businessName = businessName;
        this.description = description;
        this.imageURL = imageURL;
        this.location = location;
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

}