package com.rajateck.wael.raja.models;//
//	OfferItem.java
//
//	Create by Wael on 27/5/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class OfferItem {

    private Body body;
    private String created;
    private String nid;
    private String bigImage;

    public OfferItem() {

    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public OfferItem(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        body = new Body(jsonObject.optJSONObject("body"));
        created = jsonObject.optString("created");
        nid = jsonObject.optString("nid");
    }

    public static ArrayList<OfferItem> getOffersList(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        } else {
            ArrayList<OfferItem> offerItems = new ArrayList<>();

            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        OfferItem temp = new OfferItem(jsonArray.getJSONObject(i));
                        offerItems.add(temp);
                        System.out.println("Connection : Mobile.getOffersItems: added " + i);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return offerItems;
        }
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getNid() {
        return this.nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("body", body.toJsonObject());
            jsonObject.put("created", created);
            jsonObject.put("nid", nid);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }
}