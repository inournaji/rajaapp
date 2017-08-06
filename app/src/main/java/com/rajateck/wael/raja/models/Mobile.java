package com.rajateck.wael.raja.models;//
//	Mobile.java
//
//	Create by Wael on 1/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


public class Mobile {

    private String battery;
    private String camera;
    private String company;
    private ArrayList<String> image;
    private String price;
    private String processor;
    private String ram;
    private String screensize;
    private String storage;
    private boolean isOffer;

    public Calendar getRefreshCalendar() {
        return refreshCalendar;
    }

    public void setRefreshCalendar(Calendar refreshCalendar) {
        this.refreshCalendar = refreshCalendar;
    }

    private String nid;
    private String title;
    private Calendar refreshCalendar;

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public Mobile(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        battery = jsonObject.optString("Battery");
        camera = jsonObject.optString("Camera");
        company = jsonObject.optString("Company");
        image = new ArrayList<>();
        JSONArray imageTmp = jsonObject.optJSONArray("Image");
        if (imageTmp != null) {
            for (int i = 0; i < imageTmp.length(); i++) {
                try {
                    image.add(imageTmp.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        price = jsonObject.optString("Price");
        processor = jsonObject.optString("Processor");
        ram = jsonObject.optString("Ram");
//        screensize = jsonObject.optString("Screensize");
        screensize = jsonObject.optString("Screen size");
        storage = jsonObject.optString("Storage");
        isOffer = jsonObject.optBoolean("is_offer");
        nid = jsonObject.optString("nid");
        title = jsonObject.optString("title");

        refreshCalendar = Calendar.getInstance();
    }

    public static ArrayList<Mobile> getMoblieList(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        } else {
            ArrayList<Mobile> mobiles = new ArrayList<>();

            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        Mobile temp = new Mobile(jsonArray.getJSONObject(i));
                        mobiles.add(temp);
                        System.out.println("Connection : Mobile.getMoblieList: added " + i);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return mobiles;
        }
    }

    public String getBattery() {
        return this.battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getCamera() {
        return this.camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public void setImage(ArrayList<String> image) {
        this.image = image;
    }

    public boolean isOffer() {
        return isOffer;
    }

    public void setOffer(boolean offer) {
        isOffer = offer;
    }

    public String getProcessor() {
        return this.processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return this.ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getScreensize() {
        return this.screensize;
    }

    public void setScreensize(String screensize) {
        this.screensize = screensize;
    }

    public String getStorage() {
        return this.storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public boolean isIsOffer() {
        return this.isOffer;
    }

    public void setIsOffer(boolean isOffer) {
        this.isOffer = isOffer;
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
            jsonObject.put("Battery", battery);
            jsonObject.put("Camera", camera);
            jsonObject.put("Company", company);
            jsonObject.put("Image", image);
            jsonObject.put("Price", price);
            jsonObject.put("Processor", processor);
            jsonObject.put("Ram", ram);
            jsonObject.put("Screensize", screensize);
            jsonObject.put("Storage", storage);
            jsonObject.put("is_offer", isOffer);
            jsonObject.put("nid", nid);
            jsonObject.put("title", title);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}