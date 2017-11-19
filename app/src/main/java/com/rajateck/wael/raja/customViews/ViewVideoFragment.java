package com.rajateck.wael.raja.customViews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rajateck.wael.raja.R;


public class ViewVideoFragment extends Fragment {

    int page;
    String videoUrl;

//    ImageView loaderImage;
    Animation animation;


    public static ViewVideoFragment newInstance(int page, String videoUrl) {
        ViewVideoFragment fragment = new ViewVideoFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("videoURL", videoUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("someInt", 0);
            videoUrl = getArguments().getString("videoURL");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = (View) inflater.inflate(R.layout.fragment_video_layout, container, false);

//        loaderImage = (ImageView) contentView.findViewById(R.id.loader_image);

//        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.loader_anim);
//        loaderImage.startAnimation(animation);

        final WebView webView = (WebView) contentView.findViewById(R.id.video_web_view);
        webView.loadUrl(videoUrl);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {

                webView.setVisibility(View.VISIBLE);

//                try {
//                    if (loaderImage.getVisibility() == View.VISIBLE) {
//                        animation.cancel();
//                        animation.reset();
//
//                        loaderImage.clearAnimation();
//                        loaderImage.setVisibility(View.GONE);
//                    }
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
            }

        });


        return contentView;
    }

}
