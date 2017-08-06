package com.rajateck.wael.raja.models;//
//	AndroidApplication.java
//
//	Create by Wael on 29/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AndroidApplication {

    private String apk;
    private Apkinfo apkinfo;
    private String image;
    private String packageName;
    private String version;
    private String nid;
    private String title;
    private String Apk_Size;

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public AndroidApplication(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        apk = jsonObject.optString("Apk");
        apkinfo = new Apkinfo(jsonObject.optJSONObject("Apkinfo"));
        image = jsonObject.optString("Image");
        packageName = jsonObject.optString("PackageName");
        version = jsonObject.optString("Version");
        nid = jsonObject.optString("nid");
        title = jsonObject.optString("title");
        Apk_Size = jsonObject.optString("Apk_Size");
    }

    public static ArrayList<AndroidApplication> getAppsList(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        } else {
            ArrayList<AndroidApplication> androidApplications = new ArrayList<>();

            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        AndroidApplication temp = new AndroidApplication(jsonArray.getJSONObject(i));
                        androidApplications.add(temp);
                        System.out.println("Connection : Mobile.getMoblieList: added " + i);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return androidApplications;
        }
    }

    public String getApk() {
        return this.apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    public Apkinfo getApkinfo() {
        return this.apkinfo;
    }

    public void setApkinfo(Apkinfo apkinfo) {
        this.apkinfo = apkinfo;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String getApk_Size() {
        return Apk_Size;
    }

    public void setApk_Size(String apk_Size) {
        Apk_Size = apk_Size;
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Apk", apk);
            jsonObject.put("Apkinfo", apkinfo.toJsonObject());
            jsonObject.put("Image", image);
            jsonObject.put("PackageName", packageName);
            jsonObject.put("Version", version);
            jsonObject.put("nid", nid);
            jsonObject.put("title", title);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}