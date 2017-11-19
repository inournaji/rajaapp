package com.rajateck.wael.raja.models;//
//	AccessoryItem.java
//
//	Create by Wael on 8/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AccessoryItem {

    private String accessorytype;
    private ArrayList<String> image;
    private ArrayList<String> mobile;
    private String price;
    private String nid;
    private String title;

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public AccessoryItem(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        accessorytype = jsonObject.optString("Accessorytype");
        String imageTmp = jsonObject.optString("Image");
        image = new ArrayList<>();
        if (imageTmp != null)
            image.add(imageTmp);
//        if (imageTmp != null) {
//            image = new ArrayList<>();
//            for (int i = 0; i < imageTmp.length(); i++) {
//                try {
//                    image.add(imageTmp.getString(i));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        JSONArray mobileTmp = jsonObject.optJSONArray("Mobile");
        if (mobileTmp != null) {
            mobile = new ArrayList<>();
            for (int i = 0; i < mobileTmp.length(); i++) {
                try {
                    mobile.add(mobileTmp.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        price = "السعر غير متوفر";

        if (jsonObject.optString("Price") != null &&
                !jsonObject.optString("Price").trim().equalsIgnoreCase("") &&
                !jsonObject.optString("Price").trim().equalsIgnoreCase("[]")) {
            price = jsonObject.optString("Price");
        }

        nid = jsonObject.optString("nid");
        title = jsonObject.optString("title");
    }

    public static ArrayList<AccessoryItem> getAccessoriesList(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        } else {
            ArrayList<AccessoryItem> accessoryItems = new ArrayList<>();

            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        AccessoryItem temp = new AccessoryItem(jsonArray.getJSONObject(i));
                        accessoryItems.add(temp);
                        System.out.println("Connection : Mobile.getMoblieList: added " + i);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return accessoryItems;
        }
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public void setImage(ArrayList<String> image) {
        this.image = image;
    }

    public ArrayList<String> getMobile() {
        return mobile;
    }

    public void setMobile(ArrayList<String> mobile) {
        this.mobile = mobile;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAccessorytype() {
        return this.accessorytype;
    }

    public void setAccessorytype(String accessorytype) {
        this.accessorytype = accessorytype;
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