package com.rajateck.wael.raja.models;//
//	Body.java
//
//	Create by Wael on 27/5/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.*;


public class Body{

	private String safeValue;
	private String summary;
	private String value;

	public void setSafeValue(String safeValue){
		this.safeValue = safeValue;
	}
	public String getSafeValue(){
		return this.safeValue;
	}
	public void setSummary(String summary){
		this.summary = summary;
	}
	public String getSummary(){
		return this.summary;
	}
	public void setValue(String value){
		this.value = value;
	}
	public String getValue(){
		return this.value;
	}

	public Body(){

	}
	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Body(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		safeValue = jsonObject.optString("safe_value");
		summary = jsonObject.optString("summary");
		value = jsonObject.optString("value");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("safe_value", safeValue);
			jsonObject.put("summary", summary);
			jsonObject.put("value", value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}