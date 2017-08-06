package com.rajateck.wael.raja.models;//

import org.json.JSONException;
import org.json.JSONObject;


public class WarrentyCheckDetails {

    private String startdate;
    private String status;
    private String fieldCustomsDeclaration;
    private String fieldEndDate;
    private String fieldNotes;
    private String nid;
    private String title;

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public WarrentyCheckDetails(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        startdate = jsonObject.optString("Start_date");
        status = jsonObject.optString("Status");
        fieldCustomsDeclaration = jsonObject.optString("field_customs_declaration_");
        fieldEndDate = jsonObject.optString("field_end_date");
        fieldNotes = jsonObject.optString("field_notes");
        nid = jsonObject.optString("nid");
        title = jsonObject.optString("title");
    }

    public String getStartdate() {
        return this.startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFieldCustomsDeclaration() {
        return this.fieldCustomsDeclaration;
    }

    public void setFieldCustomsDeclaration(String fieldCustomsDeclaration) {
        this.fieldCustomsDeclaration = fieldCustomsDeclaration;
    }

    public String getFieldEndDate() {
        return this.fieldEndDate;
    }

    public void setFieldEndDate(String fieldEndDate) {
        this.fieldEndDate = fieldEndDate;
    }

    public String getFieldNotes() {
        return this.fieldNotes;
    }

    public void setFieldNotes(String fieldNotes) {
        this.fieldNotes = fieldNotes;
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
            jsonObject.put("Startdate", startdate);
            jsonObject.put("Status", status);
            jsonObject.put("field_customs_declaration_", fieldCustomsDeclaration);
            jsonObject.put("field_end_date", fieldEndDate);
            jsonObject.put("field_notes", fieldNotes);
            jsonObject.put("nid", nid);
            jsonObject.put("title", title);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}