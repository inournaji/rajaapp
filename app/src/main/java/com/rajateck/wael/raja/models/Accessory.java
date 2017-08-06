package com.rajateck.wael.raja.models;//
//	Accessory.java
//
//	Create by Wael on 8/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Accessory {

    private String accessorytype;
    private String image;
    private ArrayList<String> mobile;
    private String price;
    private String nid;
    private String title;

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
//    public Accessory(JSONObject jsonObject) {
//        if (jsonObject == null) {
//            return;
//        }
//        accessorytype = jsonObject.optString("Accessorytype");
//        image = jsonObject.opt("Image");
//        JSONArray mobileTmp = jsonObject.optJSONArray("Mobile");
//        if (mobileTmp != null) {
//            mobile = new String[mobileTmp.length()];
//            for (int i = 0; i < mobileTmp.length(); i++) {
//                mobile[i] = mobileTmp.get(i);
//            }
//        }
//        JSONArray priceTmp = jsonObject.optJSONArray("Price");
//        if (priceTmp != null) {
//            price = new Object[priceTmp.length()];
//            for (int i = 0; i < priceTmp.length(); i++) {
//                price[i] = priceTmp.get(i);
//            }
//        }
//        nid = jsonObject.opt("nid");
//        title = jsonObject.opt("title");
//    }
    public String getAccessorytype() {
        return this.accessorytype;
    }

    public void setAccessorytype(String accessorytype) {
        this.accessorytype = accessorytype;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
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
            jsonObject.put("Accessorytype", accessorytype);
            jsonObject.put("Image", image);
            jsonObject.put("Mobile", mobile);
            jsonObject.put("Price", price);
            jsonObject.put("nid", nid);
            jsonObject.put("title", title);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}