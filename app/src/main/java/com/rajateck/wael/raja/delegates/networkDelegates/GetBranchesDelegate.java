package com.rajateck.wael.raja.delegates.networkDelegates;

import com.rajateck.wael.raja.models.Branches;
import com.rajateck.wael.raja.models.Mobile;

import java.util.ArrayList;

/**
 * Created by wael on 4/1/17.
 */

public interface GetBranchesDelegate {

    public void GetBranchesSuccessDelegate(ArrayList<Branches> branches, Boolean success);

    public void GetBranchesFailureDelegate(String error);

    public void GetBranchesConnectionErrorDelegate();
}
