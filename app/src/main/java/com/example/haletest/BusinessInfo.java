package com.example.haletest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusinessInfo {
    private String name;
    private String description;
    private String location;
    private String businessImageURL;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getImageURL() {
        return businessImageURL;
    }

    public static BusinessInfo fromJson(JSONObject jsonObject) {
        BusinessInfo business = new BusinessInfo();
        try {
            business.name = jsonObject.has("name") ? jsonObject.getString("name") : "";
            business.description = jsonObject.has("description") ? jsonObject.getString("description") : "Description currently unavailable.";
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return business;
    }

    // Decodes array of book json results into business model objects
    public static ArrayList<BusinessInfo> fromJson(JSONArray jsonArray) {
        ArrayList<BusinessInfo> b_list = new ArrayList<BusinessInfo>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject businessJson = null;
            try {
                businessJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            BusinessInfo business = BusinessInfo.fromJson(businessJson);
            if (business != null) {
                b_list.add(business);
            }
        }
        return b_list;
    }

}

