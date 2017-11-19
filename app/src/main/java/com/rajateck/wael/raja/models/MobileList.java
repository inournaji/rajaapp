package com.rajateck.wael.raja.models;//
//	Mobile.java
//
//	Create by Wael on 3/3/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MobileList {

    private String battery;
    private String[] color;
    private String[] image;
    private String price;
    private String screen;
    private String fieldProcessor;
    private String nid;
    private String title;

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public MobileList(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        battery = jsonObject.optString("Battery");
        JSONArray colorTmp = jsonObject.optJSONArray("Color");
        if (colorTmp != null) {
            color = new String[colorTmp.length()];
            for (int i = 0; i < colorTmp.length(); i++) {
                try {
                    color[i] = colorTmp.getString(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONArray imageTmp = jsonObject.optJSONArray("Image");
        if (imageTmp != null) {
            image = new String[imageTmp.length()];
            for (int i = 0; i < imageTmp.length(); i++) {
                try {
                    image[i] = imageTmp.getString(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        price = jsonObject.optString("Price");
        screen = jsonObject.optString("Screen");
        fieldProcessor = jsonObject.optString("field_processor");
        nid = jsonObject.optString("nid");
        title = jsonObject.optString("title");
    }

    public String getBattery() {
        return this.battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String[] getColor() {
        return this.color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public String[] getImage() {
        return this.image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getScreen() {
        return this.screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getFieldProcessor() {
        return this.fieldProcessor;
    }

    public void setFieldProcessor(String fieldProcessor) {
        this.fieldProcessor = fieldProcessor;
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
            jsonObject.put("Color", color);
            jsonObject.put("Image", image);
            jsonObject.put("Price", price);
            jsonObject.put("Screen", screen);
            jsonObject.put("field_processor", fieldProcessor);
            jsonObject.put("nid", nid);
            jsonObject.put("title", title);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}