package com.rajateck.wael.raja.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;

import com.rajateck.wael.raja.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by MhannaCloudAppers on 10/11/2016.
 */

public class ScreenUtils {

    private static Dialog loadingDialog;

    public static float dpToPx(final Context context, final float dp) {
        if (context != null) {
            return dp * context.getResources().getDisplayMetrics().density;
        }
        return 0;
    }

    public static int getScreenHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        return height;
    }

    public static int getScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        return width;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static Pair<Boolean, Integer> hasSoftwareNavigationBar(Activity activity) {

        Display d = activity.getWindowManager().getDefaultDisplay();

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        d.getRealMetrics(realDisplayMetrics);

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);

        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;

        return new Pair<>((realWidth > displayWidth) || (realHeight > displayHeight), realHeight - displayHeight);
    }

    public static void showLoader(Context context) {

        loadingDialog = new Dialog(context);
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        loadingDialog.setContentView(R.layout.loading_dialog);

        AVLoadingIndicatorView loader = (AVLoadingIndicatorView) loadingDialog.findViewById(R.id.loader_view);
        loader.show();

        loadingDialog.setCancelable(false);
        loadingDialog.show();

    }

    public static void showCancelableLoader(Context context) {

        loadingDialog = new Dialog(context);
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        loadingDialog.setContentView(R.layout.loading_dialog);

        AVLoadingIndicatorView loader = (AVLoadingIndicatorView) loadingDialog.findViewById(R.id.loader_view);
        loader.show();

        loadingDialog.setCancelable(true);
        loadingDialog.show();

    }

    public static void dismissLoader() {

        if (loadingDialog != null) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        }

    }

    public static void vibrateDevice(Context context) {
        try {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(50);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
