package com.example.haletest;

public class Businesses {
    private String businessName;
    private String description;

    public Businesses(String businessName, String description) {

        this.businessName = businessName;
        this.description = description;
    }

    public String getBusinessName() {

        return this.businessName;
    }

    public String getDescription() {

        return this.description;
    }

}