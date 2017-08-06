package com.rajateck.wael.raja.connection;

/**
 * Created by wael on 2/8/17.
 */

public enum APIEndpoints {

    BaseURL("http://raja.webersapps.com/api"),
    MigratedBaseURL("http://rajatec.net/api"),
    GetMobileList(MigratedBaseURL.getLink() + "/mobile"),
    GetHomeList(MigratedBaseURL.getLink() + "/home"),
    GetAccessoriesLink(MigratedBaseURL.getLink() + "/mobile-accessories"),
    GetAndroiApplicationsLink(MigratedBaseURL.getLink() + "/android-apps"),
    GetHardWare(MigratedBaseURL.getLink() + "/hardware"),
    GetOffersList(MigratedBaseURL.getLink() + "/offers"),
    SubmitContactUs(MigratedBaseURL.getLink() + "/node");


    //    http://rajatec.net/api/offers
//    http://rajatec.net/api/node
    private String link;

    APIEndpoints(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

}
