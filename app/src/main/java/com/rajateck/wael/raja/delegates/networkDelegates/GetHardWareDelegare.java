package com.rajateck.wael.raja.delegates.networkDelegates;

import com.rajateck.wael.raja.models.HardWare;

import java.util.ArrayList;

/**
 * Created by wael on 5/22/17.
 */

public interface GetHardWareDelegare {

    public void getHardWareListSuccessDelegate(ArrayList<HardWare> hardWareArrayList, Boolean success);

    public void getHardWareListFailureDelegate(String error);

    public void getHardWareListConnectionErrorDelegate();
}
