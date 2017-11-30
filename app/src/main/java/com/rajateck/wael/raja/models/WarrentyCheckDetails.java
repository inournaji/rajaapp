package com.rajateck.wael.raja.models;//

import org.json.JSONObject;


public class WarrentyCheckDetails {

    private String imei1;
    private String imei2;
    private String sell_date;
    private String start_date;
    private String end_date;
    private String status;
    private String notes;
    private String mobile;
    private String model;
    private String error;


    public WarrentyCheckDetails(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }

        imei1 = jsonObject.optString("imei1");
        imei2 = jsonObject.optString("imei2");
        sell_date = jsonObject.optString("sell_date");
        start_date = jsonObject.optString("start_date");
        end_date = jsonObject.optString("end_date");
        status = jsonObject.optString("status");
        notes = jsonObject.optString("notes");
        mobile = jsonObject.optString("mobile");
        model = jsonObject.optString("model");
        error = jsonObject.optString("error");

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getImei1() {
        return imei1;
    }

    public void setImei1(String imei1) {
        this.imei1 = imei1;
    }

    public String getImei2() {
        return imei2;
    }

    public void setImei2(String imei2) {
        this.imei2 = imei2;
    }

    public String getSell_date() {
        return sell_date;
    }

    public void setSell_date(String sell_date) {
        this.sell_date = sell_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


}