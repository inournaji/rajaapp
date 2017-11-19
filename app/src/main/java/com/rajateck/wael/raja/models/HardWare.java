package com.rajateck.wael.raja.models;//
//	HardWare.java
//
//	Create by Wael on 22/5/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.*;
import java.util.*;


public class HardWare {

	private String image;
	private ArrayList<String> mobile;
	private String price;
	private String title;
	private String type;

	public ArrayList<String> getMobile() {
		return mobile;
	}

	public void setMobile(ArrayList<String> mobile) {
		this.mobile = mobile;
	}

	private String nid;

	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public void setNid(String nid){
		this.nid = nid;
	}
	public String getNid(){
		return this.nid;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public HardWare(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		image = jsonObject.optString("Image");
		JSONArray mobileTmp = jsonObject.optJSONArray("Mobile");
		if(mobileTmp != null){
			mobile = new ArrayList<>();
			for(int i = 0; i < mobileTmp.length(); i++){
				try {
					mobile.add(mobileTmp.getString(i));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
//		price = jsonObject.optString("Price");
		price = "السعر غير متوفر";

		if (jsonObject.optString("Price") != null &&
				!jsonObject.optString("Price").trim().equalsIgnoreCase("") &&
				!jsonObject.optString("Price").trim().equalsIgnoreCase("[]")) {
			price = jsonObject.optString("Price");
		}

		title = jsonObject.optString("Title");
		type = jsonObject.optString("Type");
		nid = jsonObject.optString("nid");
	}


	public static ArrayList<HardWare> getHardWareList(JSONArray jsonArray) {
		if (jsonArray == null) {
			return null;
		} else {
			ArrayList<HardWare> hardWareArrayList = new ArrayList<>();

			try {
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						HardWare temp = new HardWare(jsonArray.getJSONObject(i));
						hardWareArrayList.add(temp);
						System.out.println("Connection : Mobile.getMoblieList: added " + i);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return hardWareArrayList;
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("Image", image);
			jsonObject.put("Mobile", mobile);
			jsonObject.put("Price", price);
			jsonObject.put("Title", title);
			jsonObject.put("Type", type);
			jsonObject.put("nid", nid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}