package com.rajateck.wael.raja.controllers;

import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.models.Notification_item;
import com.rajateck.wael.raja.utils.cacheUtils.RajaCacheUtils;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by wael on 5/30/17.
 */

public class NotificationServiceForOneSignal extends NotificationExtenderService {




    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
        Log.i("OneSignalExample", "onNotificationProcessing");

        JSONObject pushData = receivedResult.payload.additionalData;
        Log.i("OneSignalExample", pushData.toString());
        if (pushData == null || !pushData.has("title") || !pushData.has("message")) {
            return true;
        }

        final String title;
        final String alert;
        final String tickerText;
        String imageUrl;

        final int smallIcon = R.drawable.ic_alarm_bell;

        title = pushData.optString("title", null);
        alert = pushData.optString("message");


        tickerText = String.format(Locale.getDefault(), "%s: %s", title, alert);

        System.out.println("push , Dashboard ======= app is not in foreground. ");
        OverrideSettings overrideSettings = new OverrideSettings();
        overrideSettings.extender = new NotificationCompat.Extender() {
            @Override
            public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {

                builder.setSmallIcon(smallIcon)
                        .setLargeIcon(BitmapFactory.decodeResource(getApplication().getApplicationContext().getResources(), R.mipmap.ic_launcher));

                return builder;
            }

        };
        Log.i("OneSignalExample", "onNotificationProcessing");
        OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
        Log.d("OneSignalExample", "Notification displayed with id: " + displayedResult.androidNotificationId);
        getNotificationDataandCash(pushData, displayedResult.androidNotificationId);

        return false;
    }

    private void getNotificationDataandCash(JSONObject pushData, Integer androidNotificationId) {

        try {
            System.out.println("NotificationServiceForOneSignal.getNotificationDataandCash : the notification is = " + pushData.toString());
        } catch (Exception exe) {
            exe.printStackTrace();
        }
        Notification_item notification_item = new Notification_item();
        if (androidNotificationId != null) {
            notification_item.setId(androidNotificationId.toString());
        } else {
            notification_item.setId(pushData.optString("message"));
        }
        notification_item.setAlert(true);
        notification_item.setBigImage(pushData.optString("imageUrl"));
        notification_item.setMessageEn(pushData.optString("message"));
        notification_item.setNotificationTime(Calendar.getInstance());

        RajaCacheUtils.CachNotification(notification_item);
    }
}

