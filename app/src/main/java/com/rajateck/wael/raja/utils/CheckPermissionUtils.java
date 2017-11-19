package com.rajateck.wael.raja.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.rajateck.wael.raja.controllers.inAppViews.BaseActivity;
import com.rajateck.wael.raja.delegates.generalDelegates.PermissionDelegate;

/**
 * Created by wael on 5/12/17.
 */

public class CheckPermissionUtils {

    public static void handleStatusPermission(final BaseActivity hostActivity, final PermessionResult permessionResult) {

        if (Build.VERSION.SDK_INT >= 23) {
            try {


                if (ContextCompat.checkSelfPermission(hostActivity,
                        Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {


                    hostActivity.setPermissionDelegate(new PermissionDelegate() {
                        @Override
                        public void onAllowPermission() {
                            System.out.println("HomeFragment.handlePermission.onAllowPermission : success here 2.");
                            permessionResult.PermissionAllowed();
                        }

                        @Override
                        public void onDenyPermission() {
                            permessionResult.PermissionDenient();
                            System.out.println("HomeFragment.handlePermission.onAllowPermission : fail here. 2");
                        }
                    });

                    ActivityCompat.requestPermissions(hostActivity,
                            new String[]{Manifest.permission.READ_PHONE_STATE},
                            60);


                } else {
                    permessionResult.PermissionAllowed();
                    System.out.println("HomeFragment.handlePermission");
                }


            } catch (Exception ex) {
                permessionResult.PermissionAllowed();
                ex.printStackTrace();
            }
        } else {
            permessionResult.PermissionAllowed();
            System.out.println("HomeFragment.handlePermission it's not 23 ");
        }

    }

    public interface PermessionResult {
        void PermissionAllowed();

        void PermissionDenient();
    }

}
