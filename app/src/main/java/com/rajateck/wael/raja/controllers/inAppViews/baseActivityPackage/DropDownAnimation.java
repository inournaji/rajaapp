package com.rajateck.wael.raja.controllers.inAppViews.baseActivityPackage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DropDownAnimation {

    LinearLayout mLinearLayout;
    LinearLayout mLinearLayoutHeader;
    ValueAnimator mAnimator;

    public DropDownAnimation(LinearLayout mLinearLayout) {

        this.mLinearLayout = mLinearLayout;

        inItAnimation();
    }

    @SuppressLint("NewApi")
    public void collapse() {
        int finalHeight = mLinearLayout.getHeight();

        ValueAnimator mAnimator = slideAnimator(finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                // Height=0, but it set visibility to GONE
                mLinearLayout.setVisibility(View.GONE);
            }

            @SuppressLint("NewApi")
            @Override
            public void onAnimationRepeat(Animator animator) {
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }
        });
        mAnimator.start();
    }

    @SuppressLint("NewApi")
    public void expand() {

        // set Visible
        mLinearLayout.setVisibility(View.VISIBLE);

		/*
         * Remove and used in preDrawListener final int widthSpec =
		 * View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		 * final int heightSpec = View.MeasureSpec.makeMeasureSpec(0,
		 * View.MeasureSpec.UNSPECIFIED); mLinearLayout.measure(widthSpec,
		 * heightSpec);
		 *
		 * mAnimator = slideAnimator(0, mLinearLayout.getMeasuredHeight());
		 */

        mAnimator.start();
    }

    private void inItAnimation() {

        mLinearLayout.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        mLinearLayout.getViewTreeObserver()
                                .removeOnPreDrawListener(this);
                        mLinearLayout.setVisibility(View.GONE);

                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(
                                0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec
                                .makeMeasureSpec(0,
                                        View.MeasureSpec.UNSPECIFIED);
                        mLinearLayout.measure(widthSpec, heightSpec);

                        mAnimator = slideAnimator(0,
                                mLinearLayout.getMeasuredHeight());
                        return true;
                    }
                });
    }

    private ValueAnimator slideAnimator(int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = mLinearLayout
                        .getLayoutParams();
                layoutParams.height = value;
                mLinearLayout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

}
