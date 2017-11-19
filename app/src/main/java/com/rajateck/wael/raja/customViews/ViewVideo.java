package com.rajateck.wael.raja.customViews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rajateck.wael.raja.R;

/**
 * Created by wael on 7/27/2015.
 */
public class ViewVideo extends FragmentActivity {
    WebView video_web_view;
    RelativeLayout pager_relative;
    ImageView xbuttom;
    WebView webView;
    RelativeLayout relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);

        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("videoUrl");


        video_web_view = (WebView) findViewById(R.id.video_web_view);

        pager_relative = (RelativeLayout) findViewById(R.id.pager_relative);
        RelativeLayout rel = (RelativeLayout) findViewById(R.id.rel);
        xbuttom = (ImageView) findViewById(R.id.xxbutom);
        relative = (RelativeLayout) findViewById(R.id.relative);

        xbuttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.clearFormData();
                webView.destroy();
                overridePendingTransition(R.anim.fade_out, R.anim.defff);
                finish();
            }
        });

        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xbuttom.performClick();
            }
        });


        webView = (WebView) findViewById(R.id.video_web_view);
        webView.loadUrl(videoUrl);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                relative.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }

        });

    }

}
