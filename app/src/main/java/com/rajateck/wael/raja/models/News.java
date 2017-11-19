package com.rajateck.wael.raja.models;//
//	News.java
//
//	Create by Wael on 3/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class News {

    private String image;
    private String link;
    private String title;
    private String Video;
    private Boolean max_width;

    public Boolean getMax_width() {
        return max_width;
    }

    public void setMax_width(Boolean max_width) {
        this.max_width = max_width;
    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public News(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }

        image = jsonObject.optString("Image");
        link = jsonObject.optString("Link");
        title = jsonObject.optString("Title");
        Video = jsonObject.optString("Video");
        max_width = jsonObject.optBoolean("fixed_width");
    }

    public static ArrayList<News> getNewsList(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        } else {
            ArrayList<News> news = new ArrayList<>();

            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        News temp = new News(jsonArray.getJSONObject(i));
                        news.add(temp);
                        System.out.println("Connection : New.getNewsList: added " + i);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return news;
        }

    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
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
            jsonObject.put("Image", image);
            jsonObject.put("Link", link);
            jsonObject.put("Title", title);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}