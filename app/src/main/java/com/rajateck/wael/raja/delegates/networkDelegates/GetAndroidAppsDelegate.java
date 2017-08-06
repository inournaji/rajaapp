package com.rajateck.wael.raja.delegates.networkDelegates;

import com.rajateck.wael.raja.models.AndroidApplication;

import java.util.ArrayList;

/**
 * Created by wael on 4/1/17.
 */

public interface GetAndroidAppsDelegate {

    public void getAppsListSuccessDelegate(ArrayList<AndroidApplication> applications, Boolean success);

    public void getAppsListFailureDelegate(String error);

    public void getAppsListConnectionErrorDelegate();
}
