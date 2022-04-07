package com.example.haletest;

public class Businesses {
    private String businessName;
    private String description;
    private String imageURL;

    public Businesses(String businessName, String description, String imageURL) {

        this.businessName = businessName;
        this.description = description;
        this.imageURL = imageURL;
    }

    public String getBusinessName() {

        return this.businessName;
    }

    public String getDescription() {

        return this.description;
    }

    public String getImageURL() {

        return this.imageURL;
    }

}