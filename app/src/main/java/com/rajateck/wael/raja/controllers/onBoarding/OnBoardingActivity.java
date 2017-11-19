package com.rajateck.wael.raja.controllers.onBoarding;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.controllers.inAppViews.BaseActivity;
import com.rajateck.wael.raja.utils.RajaApp;

public class OnBoardingActivity extends FragmentActivity {
    ImageView splash;
    ImageView app_title;
    private ImageView spIcon1;
    private ImageView spIcon2;
    private ImageView spIcon4;
    private ImageView spIcon7;
    private ImageView spIcon5;
    private ImageView spIcon8;
    private ImageView spIcon9;
    private ImageView spIcon10;
    private ImageView spIcon11;
    private ImageView spIcon12;
    private ImageView spIcon13;
    private ImageView spIcon14;
    private ImageView spIcon15;
    private ImageView spIcon16;
    private Boolean done = false;
    private ImageView appTitle;
    private RelativeLayout relative;

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-04-14 12:42:42 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        spIcon1 = (ImageView) findViewById(R.id.sp_icon_1);
        spIcon2 = (ImageView) findViewById(R.id.sp_icon_2);
        spIcon4 = (ImageView) findViewById(R.id.sp_icon_4);
        spIcon5 = (ImageView) findViewById(R.id.sp_icon_5);
        spIcon7 = (ImageView) findViewById(R.id.sp_icon_7);
        spIcon8 = (ImageView) findViewById(R.id.sp_icon_8);
        spIcon9 = (ImageView) findViewById(R.id.sp_icon_9);
        spIcon10 = (ImageView) findViewById(R.id.sp_icon_10);
        spIcon11 = (ImageView) findViewById(R.id.sp_icon_11);
        spIcon12 = (ImageView) findViewById(R.id.sp_icon_12);
        spIcon13 = (ImageView) findViewById(R.id.sp_icon_13);
        spIcon14 = (ImageView) findViewById(R.id.sp_icon_14);
        spIcon15 = (ImageView) findViewById(R.id.sp_icon_15);
        spIcon16 = (ImageView) findViewById(R.id.sp_icon_16);
        appTitle = (ImageView) findViewById(R.id.app_title);
        relative = (RelativeLayout) findViewById(R.id.relative);
        relative = (RelativeLayout) findViewById(R.id.relative);
        splash = (ImageView) findViewById(R.id.splash_img);
        app_title = (ImageView) findViewById(R.id.app_title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_on_boarding_2);
        //startOldAnimation();
        validateLocalizaiton();
        findViews();
        startNewAnimation();
        animateIcon();
        startPulse();

    }

    private void validateLocalizaiton() {
        String local = RajaApp.getSharePrefrenceLocale(OnBoardingActivity.this);
        if (!local.equalsIgnoreCase("ar")) {
            System.out.println("OnBoardingActivity.validateLocalizaiton : here to set language to english");
            RajaApp.setLocale(OnBoardingActivity.this, "en");
            RajaApp.setSharePrefrenceLocale(OnBoardingActivity.this, "en");
        } else {
            System.out.println("OnBoardingActivity.validateLocalizaiton : here to set language to english");
            RajaApp.setLocale(OnBoardingActivity.this, "ar");
            RajaApp.setSharePrefrenceLocale(OnBoardingActivity.this, "ar");
        }

    }

    private void animateIcon() {
//        YoYo.with(Techniques.Shake).withListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                animateIcon();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        }).duration(1100).playOn(app_title);

    }

    @Override
    protected void onPause() {
        super.onPause();
        StopAllAnimations();
    }

    private void StopAllAnimations() {

        StopPulse(spIcon1);
        StopPulse(spIcon2);
        StopPulse(spIcon13);
        StopPulse(spIcon4);
        StopPulse(spIcon5);
        StopPulse(spIcon7);
        StopPulse(spIcon8);
        StopPulse(spIcon9);
        StopPulse(spIcon10);
        StopPulse(spIcon11);
        StopPulse(spIcon12);
        StopPulse(spIcon14);
        StopPulse(spIcon15);
        StopPulse(spIcon16);

    }

    private void StopPulse(ImageView view) {
        try {
            view.clearAnimation();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void startNewAnimation() {
        int duration = 800;
        startPulse(spIcon1, 0, 700);
        startPulse(spIcon2, 100, 700);
        startPulse(spIcon13, 30, 700);
        startPulse(spIcon4, 200, 600);
        startPulse(spIcon5, 150, 700);
        startPulse(spIcon7, 110, 800);
        startPulse(spIcon8, 240, 900);
        startPulse(spIcon9, 300, 800);
        startPulse(spIcon10, 50, 700);
        startPulse(spIcon11, 180, 600);
        startPulse(spIcon12, 55, 500);
        startPulse(spIcon14, 270, 400);
        startPulse(spIcon15, 12, 300);
        startPulse(spIcon16, 200, 900);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                done = true;
                Intent intent = new Intent(OnBoardingActivity.this, BaseActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.no_anim);
                finish();
            }
        }, 5000);

    }

    private void startOldAnimation() {
        AnimationSet zoomIn = (AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
        zoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                relative.setVisibility(View.VISIBLE);
                Handler h = new Handler();
                startPulse();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(OnBoardingActivity.this, BaseActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.no_anim);
                        finish();
                    }
                }, 4000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        splash.startAnimation(zoomIn);


    }

    private void startPulse() {
//        YoYo.with(Techniques.Pulse).withListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                startPulse();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        }).duration(900).playOn(app_title);
    }

    private void startPulse(final View view, final int delay, final int duration) {
        final int duration_temp = 900;

        System.out.println("OnBoardingActivity.startPulse : here zoomin");
        view.animate().setListener(new android.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(android.animation.Animator animator) {

            }

            @Override
            public void onAnimationEnd(android.animation.Animator animator) {
                view.animate().setListener(new android.animation.Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(android.animation.Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(android.animation.Animator animator) {
                        if (done != null && !done) {

                            startPulse(view, 0, duration_temp);
                        }
                    }

                    @Override
                    public void onAnimationCancel(android.animation.Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(android.animation.Animator animator) {

                    }
                }).scaleXBy((float) -0.5).scaleYBy((float) -0.5).alpha((float) 0.1).setDuration(duration_temp).setInterpolator(new DecelerateInterpolator()).start();


            }

            @Override
            public void onAnimationCancel(android.animation.Animator animator) {

            }

            @Override
            public void onAnimationRepeat(android.animation.Animator animator) {

            }
        }).scaleXBy((float) 0.5).scaleYBy((float) 0.5).alpha((float) 0.4).setDuration(duration).setStartDelay(delay).setInterpolator(new AccelerateInterpolator()).start();


    }
}
