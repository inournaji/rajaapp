package com.rajateck.wael.raja.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import com.lifeofcoding.cacheutlislibrary.CacheUtils;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import com.onesignal.OneSignal;

import java.net.Proxy;
import java.util.Locale;


/**
 * Created by wael on 4/6/17.
 */

public class RajaApp extends Application {
    private final static String TAG = "FileDownloadApplication";
    public static Context CONTEXT;

    public static void setLocale(Context context, String local) {
        Locale locale = new Locale(local);
        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }

    public static String getSharePrefrenceLocale(Context context) {
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("locale", "ar");
        } catch (Exception ex) {
            return "en";
        }
    }

    public static void setSharePrefrenceLocale(Context context, String locale) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("locale", locale);
        editor.commit();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        CacheUtils.configureCache(this);
        CONTEXT = this;


        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        FileDownloader.init(getApplicationContext(), new DownloadMgrInitialParams.InitCustomMaker()
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000)
                        .readTimeout(15_000)
                        .proxy(Proxy.NO_PROXY)
                )));
    }
}
