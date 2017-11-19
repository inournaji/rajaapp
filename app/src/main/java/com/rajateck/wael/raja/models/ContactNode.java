package com.rajateck.wael.raja.models;//
//	ContactNode.java
//
//	Create by Wael on 6/6/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONException;
import org.json.JSONObject;


public class ContactNode {

    private String nid;
    private String uri;

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public ContactNode(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        nid = jsonObject.optString("nid");
        uri = jsonObject.optString("uri");
    }

    public String getNid() {
        return this.nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nid", nid);
            jsonObject.put("uri", uri);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}