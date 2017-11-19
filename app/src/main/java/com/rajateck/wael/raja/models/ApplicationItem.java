package com.rajateck.wael.raja.models;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wael on 4/16/17.
 */

public class ApplicationItem {
    String title;
    String Versoin;
    String icon;
    String size;
    Drawable appIcon;

    public static ArrayList<ApplicationItem> getList(List<ApplicationInfo> applicationsInfos, Context context) {
        if (applicationsInfos == null) {
            applicationsInfos = new ArrayList<>();
        }
        PackageManager pm = context.getPackageManager();
        ArrayList<ApplicationItem> applicationItems = new ArrayList<>();
        for (int i = 0; i < applicationsInfos.size(); i++) {
            try {
                ApplicationItem item = new ApplicationItem();
                item.setTitle((String) pm.getApplicationLabel(applicationsInfos.get(i)));
                item.setAppIcon(pm.getApplicationIcon(applicationsInfos.get(i)));

                applicationItems.add(item);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return applicationItems;

    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersoin() {
        return Versoin;
    }

    public void setVersoin(String versoin) {
        Versoin = versoin;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
