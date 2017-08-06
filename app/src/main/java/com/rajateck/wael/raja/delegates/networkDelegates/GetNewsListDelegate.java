package com.rajateck.wael.raja.delegates.networkDelegates;

import com.rajateck.wael.raja.models.News;

import java.util.ArrayList;

/**
 * Created by wael on 4/1/17.
 */

public interface GetNewsListDelegate {

    public void getNewsListSuccessDelegate(ArrayList<News> news, Boolean success);

    public void getNewsListFailureDelegate(String error);

    public void getNewsListConnectionErrorDelegate();
}
