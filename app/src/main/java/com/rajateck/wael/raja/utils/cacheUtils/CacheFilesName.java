package com.rajateck.wael.raja.utils.cacheUtils;

/**
 * Created by wael on 4/6/17.
 */

public enum CacheFilesName {

    MobilesFileName("MOBILES_FILE"),
    NewsFileName("NEWS_FILE"),
    AndroidApplicationsFileName("ANDROID_FILE"),
    AccessoriesFileName("ACCESSORY_LIST"),
    OffersFileName("OFFERS_LIST"),
    HardWareFile("HARDWARE_LIST"),
    Notification("Notification"),
    WarrantyItem("WarrantyItem"),
    MapPinesFileName("MAPPINS");

    private String fileName;

    CacheFilesName(String file) {
        this.fileName = file;
    }

    public String getFileName() {
        return fileName;
    }

}
