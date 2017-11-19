package com.rajateck.wael.raja.delegates.generalDelegates;

import com.rajateck.wael.raja.models.ContactNode;

/**
 * Created by wael on 6/6/17.
 */

public interface ContactUsDelegate {

    public void submitContactUsDelegateSuccess(ContactNode contactNode);

    public void submitContactUsDelegateFailure(String error);

    public void submitContactUsConnectionErrorDelegate();

}
