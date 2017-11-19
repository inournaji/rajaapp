package com.rajateck.wael.raja.delegates.generalDelegates;

import com.rajateck.wael.raja.models.WarrentyCheckDetails;

/**
 * Created by wael on 5/20/17.
 */

public interface CheckWarrantyDelegate {

    public void getWarrantyDelegateSuccess(WarrentyCheckDetails warrentyCheckDetails);

    public void getWarrantyDelegateFailure(String error);

    public void getWarrantyConnectionErrorDelegate();

}
