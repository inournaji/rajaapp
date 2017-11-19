package com.rajateck.wael.raja.delegates.generalDelegates;

import com.rajateck.wael.raja.models.OfferItem;

import java.util.ArrayList;

/**
 * Created by wael on 5/27/17.
 */

public interface GetOffersListDelegate {

    public void getOffersListSuccessDelegate(ArrayList<OfferItem> offerItems, Boolean success);

    public void getOffersListFailureDelegate(String error);

    public void getOffersListConnectionErrorDelegate();
}
