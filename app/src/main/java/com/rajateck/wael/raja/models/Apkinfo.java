package com.rajateck.wael.raja.models;//
//	Apkinfo.java
//
//	Create by Wael on 29/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.*;


public class Apkinfo{

	private String description;
	private String display;
	private String fid;
	private String filemime;
	private String filename;
	private String filesize;
	private Object[] rdfMapping;
	private String status;
	private String timestamp;
	private String uid;
	private String uri;

	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDisplay(String display){
		this.display = display;
	}
	public String getDisplay(){
		return this.display;
	}
	public void setFid(String fid){
		this.fid = fid;
	}
	public String getFid(){
		return this.fid;
	}
	public void setFilemime(String filemime){
		this.filemime = filemime;
	}
	public String getFilemime(){
		return this.filemime;
	}
	public void setFilename(String filename){
		this.filename = filename;
	}
	public String getFilename(){
		return this.filename;
	}
	public void setFilesize(String filesize){
		this.filesize = filesize;
	}
	public String getFilesize(){
		return this.filesize;
	}
	public void setRdfMapping(Object[] rdfMapping){
		this.rdfMapping = rdfMapping;
	}
	public Object[] getRdfMapping(){
		return this.rdfMapping;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}
	public String getTimestamp(){
		return this.timestamp;
	}
	public void setUid(String uid){
		this.uid = uid;
	}
	public String getUid(){
		return this.uid;
	}
	public void setUri(String uri){
		this.uri = uri;
	}
	public String getUri(){
		return this.uri;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Apkinfo(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		description = jsonObject.optString("description");
		display = jsonObject.optString("display");
		fid = jsonObject.optString("fid");
		filemime = jsonObject.optString("filemime");
		filename = jsonObject.optString("filename");
		filesize = jsonObject.optString("filesize");
		JSONArray rdfMappingTmp = jsonObject.optJSONArray("rdf_mapping");
//		if(rdfMappingTmp != null){
//			rdfMapping = new Object[rdfMappingTmp.length()];
//			for(int i = 0; i < rdfMappingTmp.length(); i++){
//				rdfMapping[i] = rdfMappingTmp.get(i);
//			}
//		}
		status = jsonObject.optString("status");
		timestamp = jsonObject.optString("timestamp");
		uid = jsonObject.optString("uid");
		uri = jsonObject.optString("uri");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("description", description);
			jsonObject.put("display", display);
			jsonObject.put("fid", fid);
			jsonObject.put("filemime", filemime);
			jsonObject.put("filename", filename);
			jsonObject.put("filesize", filesize);
			jsonObject.put("rdf_mapping", rdfMapping);
			jsonObject.put("status", status);
			jsonObject.put("timestamp", timestamp);
			jsonObject.put("uid", uid);
			jsonObject.put("uri", uri);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}