package com.rajateck.wael.raja.delegates;

import com.rajateck.wael.raja.models.WarrentyCheckDetails;

/**
 * Created by wael on 11/18/17 at raja-tec.
 * at November
 */

public interface ActivateMobileNumberDelegate {

    public void activateDeviceDelegateSuccess(WarrentyCheckDetails warrentyCheckDetails);

    public void activateDeviceDelegateFailure(String error);

    public void activateDeviceDelegateParameterError();

    public void activateDeviceConnectionErrorDelegate();
}
