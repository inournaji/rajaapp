package com.rajateck.wael.raja.connection;

/**
 * Created by wael on 2/8/17.
 */

public enum APIEndpoints {

    BaseURL("http://raja.webersapps.com/api"),
    MigratedBaseURL("https://rajatec.net/en/api"),
    GetMobileList(MigratedBaseURL.getLink() + "/mobile"),
    GetHomeList(MigratedBaseURL.getLink() + "/home"),
    GetAccessoriesLink(MigratedBaseURL.getLink() + "/mobile-accessories"),
    GetAndroiApplicationsLink(MigratedBaseURL.getLink() + "/android-apps"),
    GetHardWare(MigratedBaseURL.getLink() + "/hardware"),
    GetBranches(MigratedBaseURL.getLink() + "/branches-api"),
    GetOffersList(MigratedBaseURL.getLink() + "/offers"),
    PostWarrantyActivation("http://backoffice.rajatec.net/api/web/v1/waranties/activate"),
    SubmitContactUs(MigratedBaseURL.getLink() + "/node");


    private String link;

    APIEndpoints(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

}
