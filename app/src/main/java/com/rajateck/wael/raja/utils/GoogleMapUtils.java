package com.rajateck.wael.raja.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * Created by wael on 10/4/17 at MyAzkar.
 * at October
 */

public class GoogleMapUtils {

    public static Bitmap resizeMapIconFromAssets(Context context, String iconName, int width, int height) {
        try {
            Bitmap imageBitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier(iconName, "drawable", context.getPackageName()));
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
            return resizedBitmap;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Bitmap resizeMapIcon(Context context, Bitmap bitmap, int width, int height) {
        try {
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
            return resizedBitmap;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Bitmap JoinTwoIconsNextToEachOther(Bitmap c, Bitmap s) {
        Bitmap cs = null;

        int width, height = 0;

        if (c.getWidth() > s.getWidth()) {
            width = c.getWidth() + s.getWidth();
            height = c.getHeight();
        } else {
            width = s.getWidth() + s.getWidth();
            height = c.getHeight();
        }

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(cs);

        comboImage.drawBitmap(c, 0f, 0f, null);
        comboImage.drawBitmap(s, c.getWidth(), 0f, null);

        // this is an extra bit I added, just incase you want to save the new image somewhere and then return the location
    /*String tmpImg = String.valueOf(System.currentTimeMillis()) + ".png";

    OutputStream os = null;
    try {
      os = new FileOutputStream(loc + tmpImg);
      cs.compress(CompressFormat.PNG, 100, os);
    } catch(IOException e) {
      Log.e("combineImages", "problem combining images", e);
    }*/

        return cs;
    }

    public static Bitmap JoinTwoIconsAboveEachOther(Context context, Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, ScreenUtils.dpToPx(context,2), ScreenUtils.dpToPx(context,2), null);
        return bmOverlay;
    }
}
