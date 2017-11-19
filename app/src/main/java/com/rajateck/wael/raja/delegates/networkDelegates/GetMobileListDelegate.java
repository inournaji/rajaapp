package com.rajateck.wael.raja.delegates.networkDelegates;

import com.rajateck.wael.raja.models.Mobile;

import java.util.ArrayList;

/**
 * Created by wael on 4/1/17.
 */

public interface GetMobileListDelegate {

    public void getMobileListSuccessDelegate(ArrayList<Mobile> mobiles, Boolean success);

    public void getMobileListFailureDelegate(String error);

    public void getMobileListConnectionErrorDelegate();
}
