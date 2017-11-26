package com.rajateck.wael.raja.utils.notificationUtils;

import android.content.Context;
import android.content.Intent;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.controllers.inAppViews.BaseActivity;

/**
 * Created by wael on 11/25/17 at raja-tec.
 * at November
 */

public class RajaNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    Context context;

    public RajaNotificationOpenedHandler(Context context) {
        this.context = context;
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        System.out.println("RajaNotificationOpenedHandler.notificationOpened : here to open the offers ");

        if (context != null) {
            System.out.println("RajaNotificationOpenedHandler.notificationOpened : here to open it ");

            Intent intent = new Intent(context, BaseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(context.getString(R.string.notificationExtra), true);

            context.startActivity(intent);
        } else {
            System.out.println("RajaNotificationOpenedHandler.notificationOpened : context is null");
        }


    }


}
