package com.rajateck.wael.raja.delegates.networkDelegates;

import com.rajateck.wael.raja.models.AccessoryItem;

import java.util.ArrayList;

/**
 * Created by wael on 4/1/17.
 */

public interface GetAccessoriesListDelegate {

    public void getAccessoryListSuccessDelegate(ArrayList<AccessoryItem> accessoryItems, Boolean success);

    public void getAccessoryListFailureDelegate(String error);

    public void getAccessoryListConnectionErrorDelegate();
}
