package com.rajateck.wael.raja.enums;

/**
 * Created by wael on 3/3/17.
 */
public enum FragmentTags {

    HomeFragment("HOME_FRAGMENT"),
    MobileFragment("MOBILE"),
    WarrantyFragment("Warrenty"),
    OTHERFragment("OTHER"),
    SearchFragment("SEARCH_FRAGMENT"),
    OffersFragment("OFFERS_FRAGMENT"),
    ApplicationFragment("APPLICATION"),
    ExtensionsFragment("EXTENSIONS");

    String fragmentName;

    FragmentTags(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public String getFragmentName() {
        return fragmentName;
    }

}