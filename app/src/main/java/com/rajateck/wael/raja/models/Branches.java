package com.rajateck.wael.raja.models;//
//	RootClass.java
//
//	Create by Wael on 9/11/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Branches {

    @SerializedName("Address")
    private String address;
    @SerializedName("Phonenumber")
    private String phonenumber;
    @SerializedName("lat")
    private String lat;
    @SerializedName("longe")
    private String longe;
    @SerializedName("nid")
    private String nid;
    @SerializedName("title")
    private String title;

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public Branches(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        address = jsonObject.optString("Address");
        phonenumber = jsonObject.optString("Phone number");
        lat = jsonObject.optString("lat");
        longe = jsonObject.optString("longe");
        nid = jsonObject.optString("nid");
        title = jsonObject.optString("title");
    }

    public static ArrayList<Branches> getBranchesList(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        } else {
            ArrayList<Branches> branchesArrayList = new ArrayList<>();

            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        Branches temp = new Branches(jsonArray.getJSONObject(i));
                        branchesArrayList.add(temp);
                        System.out.println("Connection : Mobile.Branches: added " + i);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return branchesArrayList;
        }
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLonge() {
        return this.longe;
    }

    public void setLonge(String longe) {
        this.longe = longe;
    }

    public String getNid() {
        return this.nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Address", address);
            jsonObject.put("Phonenumber", phonenumber);
            jsonObject.put("lat", lat);
            jsonObject.put("longe", longe);
            jsonObject.put("nid", nid);
            jsonObject.put("title", title);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}