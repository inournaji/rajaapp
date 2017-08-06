package com.rajateck.wael.raja.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by wael on 5/30/17.
 */
 public class Notification_item {
    /**
     * The Id.
     */
    String id;
    /**
     * The Title.
     */
    String title;
    /**
     * The Sound.
     */
    String Sound;
    /**
     * The Content available.
     */
    String content_available;
    /**
     * The Message en.
     */
    String MessageEn;

    /**
     * The Alert.
     */
    Boolean Alert;
    /**
     * The Notification time.
     */
    Calendar notificationTime;
    /**
     * The Type.
     */
    Integer type;
    /**
     * The Shown.
     */
    boolean shown;
    Boolean closed = false;
    String FormData;

    /**
     * Instantiates a new Notification item.
     *
     * @param title             the title
     * @param sound             the sound
     * @param content_available the content available
     * @param messageEn         the message en
     * @param isAlert           the is alert
     */
    public Notification_item(String title, String sound, String content_available, String messageEn, Boolean isAlert) {
        this.title = title;
        this.Sound = sound;
        this.content_available = content_available;
        this.MessageEn = messageEn;
        this.Alert = isAlert;
        this.shown = false;
        this.type = Integer.valueOf(-1);
    }

    /**
     * Instantiates a new Notification item.
     *
     * @param response the response
     */
    public Notification_item(String response) {
        try {
            JSONObject e = new JSONObject(response);
            if (e.has("parsePushId")) {
                new Notification_item();
            }
        } catch (JSONException var4) {
            var4.printStackTrace();
        }
        shown = false;
    }
    /**
     * Instantiates a new Notification item.
     */
    public Notification_item() {
        shown = false;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Boolean isClosed() {
        return closed;
    }

    public Boolean getAlert() {
        return Alert;
    }

    /**
     * Sets alert.
     *
     * @param alert the alert
     */
    public void setAlert(Boolean alert) {
        this.Alert = alert;
    }

    public String getFormData() {
        return FormData;
    }

    public void setFormData(String formData) {
        FormData = formData;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Gets notification time.
     *
     * @return the notification time
     */
    public Calendar getNotificationTime() {
        return this.notificationTime;
    }

    /**
     * Sets notification time.
     *
     * @param notificationTime the notification time
     */
    public void setNotificationTime(Calendar notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String toString() {
        return "Notification_item{title=\'" + this.title + '\'' + ", Sound=\'" + this.Sound + '\'' + ", content_available=\'" + this.content_available + '\'' + ", MessageEn=\'" + this.MessageEn + '\'' + ", MessageAr=\'" + "" + '\'' + ", isAlert=" + this.Alert + '}';
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets sound.
     *
     * @return the sound
     */
    public String getSound() {
        return this.Sound;
    }

    /**
     * Sets sound.
     *
     * @param sound the sound
     */
    public void setSound(String sound) {
        this.Sound = sound;
    }

    /**
     * Gets content available.
     *
     * @return the content available
     */
    public String getContent_available() {
        return this.content_available;
    }

    /**
     * Sets content available.
     *
     * @param content_available the content available
     */
    public void setContent_available(String content_available) {
        this.content_available = content_available;
    }

    /**
     * Gets message en.
     *
     * @return the message en
     */
    public String getMessageEn() {
        return this.MessageEn;
    }

    /**
     * Sets message en.
     *
     * @param messageEn the message en
     */
    public void setMessageEn(String messageEn) {
        this.MessageEn = messageEn;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Is alert boolean.
     *
     * @return the boolean
     */
    public Boolean isAlert() {
        return this.Alert;
    }

    /**
     * Is shown boolean.
     *
     * @return the boolean
     */
    public boolean isShown() {
        return shown;
    }

    /**
     * Sets shown.
     *
     * @param shown the shown
     */
    public void setShown(boolean shown) {
        this.shown = shown;
    }
}
