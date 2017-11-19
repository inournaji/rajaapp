package com.rajateck.wael.raja.utils.cacheUtils;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.lifeofcoding.cacheutlislibrary.CacheUtils;
import com.rajateck.wael.raja.models.AccessoryItem;
import com.rajateck.wael.raja.models.AndroidApplication;
import com.rajateck.wael.raja.models.HardWare;
import com.rajateck.wael.raja.models.Mobile;
import com.rajateck.wael.raja.models.News;
import com.rajateck.wael.raja.models.Notification_item;
import com.rajateck.wael.raja.models.OfferItem;
import com.rajateck.wael.raja.models.WarrentyCheckDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wael on 4/6/17.
 */

public class RajaCacheUtils {


    public static void cacheMobilesList(ArrayList<Mobile> smsList) {

        CacheUtils.writeObjectFile(CacheFilesName.MobilesFileName.getFileName(), smsList);
    }

    public static ArrayList<Mobile> getCachedMobilesList() {

        ArrayList<Mobile> cachedSmsList = CacheUtils.readObjectFile(CacheFilesName.MobilesFileName.getFileName(), new TypeToken<ArrayList<Mobile>>() {
        }.getType());
        if (cachedSmsList == null) {
            cachedSmsList = new ArrayList<>();
        }
        return cachedSmsList;
    }

    public static void cacheNewsList(ArrayList<News> smsList) {

        CacheUtils.writeObjectFile(CacheFilesName.NewsFileName.getFileName(), smsList);
    }

    public static ArrayList<News> getCachedNewsList() {

        ArrayList<News> newsArrayList = CacheUtils.readObjectFile(CacheFilesName.NewsFileName.getFileName(), new TypeToken<ArrayList<News>>() {
        }.getType());
        if (newsArrayList == null) {
            newsArrayList = new ArrayList<>();
        }
        return newsArrayList;
    }

    public static void cacheAccessoriesList(ArrayList<AccessoryItem> smsList) {

        CacheUtils.writeObjectFile(CacheFilesName.AccessoriesFileName.getFileName(), smsList);
    }

    public static ArrayList<AccessoryItem> getCachedAccessories() {

        ArrayList<AccessoryItem> newsArrayList = CacheUtils.readObjectFile(CacheFilesName.AccessoriesFileName.getFileName(), new TypeToken<ArrayList<AccessoryItem>>() {
        }.getType());
        if (newsArrayList == null) {
            newsArrayList = new ArrayList<>();
        }
        return newsArrayList;
    }

    public static void cacheApplicationsList(ArrayList<AndroidApplication> smsList) {

        CacheUtils.writeObjectFile(CacheFilesName.AndroidApplicationsFileName.getFileName(), smsList);
    }

    public static ArrayList<AndroidApplication> getCachedAndroidApplications() {

        ArrayList<AndroidApplication> ArrayList = CacheUtils.readObjectFile(CacheFilesName.AndroidApplicationsFileName.getFileName(), new TypeToken<ArrayList<AndroidApplication>>() {
        }.getType());
        if (ArrayList == null) {
            ArrayList = new ArrayList<>();
        }
        return ArrayList;
    }

    public static void cacheHardWareList(ArrayList<HardWare> List) {

        CacheUtils.writeObjectFile(CacheFilesName.HardWareFile.getFileName(), List);
    }

    public static ArrayList<HardWare> getCachedHardWare() {
        ArrayList<HardWare> newsArrayList = CacheUtils.readObjectFile(CacheFilesName.HardWareFile.getFileName(), new TypeToken<ArrayList<HardWare>>() {
        }.getType());
        if (newsArrayList == null) {
            newsArrayList = new ArrayList<>();
        }
        return newsArrayList;
    }

    public static void cacheOffersList(ArrayList<OfferItem> List) {

        CacheUtils.writeObjectFile(CacheFilesName.OffersFileName.getFileName(), List);
    }

    public static ArrayList<OfferItem> getCachedOffersList() {
        ArrayList<OfferItem> newsArrayList = CacheUtils.readObjectFile(CacheFilesName.OffersFileName.getFileName(), new TypeToken<ArrayList<OfferItem>>() {
        }.getType());
        if (newsArrayList == null) {
            newsArrayList = new ArrayList<>();
        }
        return newsArrayList;
    }


    public static void CachNotification(Notification_item notification_item) {
        System.out.println("RajaCacheUtils.CachNotification : cached ");
        List<Notification_item> notifications = CacheUtils.readObjectFile(CacheFilesName.Notification.getFileName(), new TypeToken<List<Notification_item>>() {
        }.getType());
        if (notifications == null) {
            notifications = new ArrayList<>();
        }

        boolean found = false;
        for (int i = 0; i < notifications.size(); i++) {
            if (notifications.get(i).getId().equalsIgnoreCase(notification_item.getId())) {
                found = true;
                break;
            }
        }

        if (!found)
            notifications.add(notification_item);

        //System.out.println("push , cash new notification cashed size : " + notifications.size());
        CacheUtils.writeObjectFile(CacheFilesName.Notification.getFileName(), notifications);

    }


    public static ArrayList<Notification_item> GetNotificatios() {
        List<Notification_item> notifications = CacheUtils.readObjectFile(CacheFilesName.Notification.getFileName(), new TypeToken<List<Notification_item>>() {
        }.getType());
        if (notifications == null) {
            notifications = new ArrayList<>();
        }
        ArrayList<Notification_item> newitems = new ArrayList<>();
        for (int i = 0; i < notifications.size(); i++) {
            try {
                if (!notifications.get(i).isShown()) {
                    newitems.add(notifications.get(i));
                }
            } catch (Exception ex) {
                newitems.add(notifications.get(i));
            }
        }
        System.out.println("push ,get New Notifications cashed size : " + newitems.size());
        return newitems;
    }

    public static WarrentyCheckDetails getWarrantyCheckData(Context context) {

        WarrentyCheckDetails warrentyCheckDetails = CacheUtils.readObjectFile(CacheFilesName.WarrantyItem.getFileName(), new TypeToken<WarrentyCheckDetails>() {
        }.getType());

        return warrentyCheckDetails;
    }

    public static void cacheThisWarrantyCheckData(Context context, WarrentyCheckDetails warrentyCheckDetails) {
        System.out.println("RajaCacheUtils.cache the warranty item");

        CacheUtils.writeObjectFile(CacheFilesName.WarrantyItem.getFileName(), warrentyCheckDetails);
    }
}
