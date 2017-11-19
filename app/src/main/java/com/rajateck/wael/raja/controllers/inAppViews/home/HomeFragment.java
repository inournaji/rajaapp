package com.rajateck.wael.raja.controllers.inAppViews.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.connection.Connection;
import com.rajateck.wael.raja.controllers.inAppViews.BaseActivity;
import com.rajateck.wael.raja.customViews.ViewVideo;
import com.rajateck.wael.raja.delegates.networkDelegates.GetNewsListDelegate;
import com.rajateck.wael.raja.models.News;
import com.rajateck.wael.raja.utils.ItemClickSupport;
import com.rajateck.wael.raja.utils.cacheUtils.RajaCacheUtils;

import java.util.ArrayList;

import fr.arnaudguyon.tabstacker.TabStacker;

public class HomeFragment extends Fragment implements TabStacker.TabStackInterface {
    private LinearLayoutManager linearLayoutManager;
    private HomeListAdapter homeListAdapter;
    private View inflatedView;
    private RecyclerView listRecycler;
    private ArrayList<News> news;
    private RelativeLayout loader;
    private TextView tap_to_retry;

    //    tsh07yiP0iUKVXiRXlf5JDFzjii7UU6Ti2akKQ==

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private void findViews(View rootView) {
        tap_to_retry = (TextView) rootView.findViewById(R.id.tap_to_retry);
        loader = (RelativeLayout) rootView.findViewById(R.id.relative);
        listRecycler = (RecyclerView) rootView.findViewById(R.id.list_recycler);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_home, container, false);
        findViews(inflatedView);
        setUpRecyclerData();
        setRetainInstance(true);

        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(inflatedView);
    }

    @Override
    public void onTabFragmentPresented(TabStacker.PresentReason presentReason) {

    }

    @Override
    public void onTabFragmentDismissed(TabStacker.DismissReason dismissReason) {

    }

    @Override
    public View onSaveTabFragmentInstance(Bundle bundle) {
        return inflatedView;
    }

    @Override
    public void onRestoreTabFragmentInstance(Bundle bundle) {

    }

    private void setUpRecyclerData() {

        ArrayList<News> newsArrayList = RajaCacheUtils.getCachedNewsList();
        if (newsArrayList != null && newsArrayList.size() > 0) {
            news = newsArrayList;
            fillHomeListData(news);

            Connection.getNewsList(new GetNewsListDelegate() {
                @Override
                public void getNewsListSuccessDelegate(ArrayList<News> news, Boolean success) {
                    System.out.println("HomeFragment.getNewsListSuccessDelegate : here");

                    tap_to_retry.setVisibility(View.GONE);
                    loader.setVisibility(View.GONE);

                    if (news != null && news.size() > 0) {
                        RajaCacheUtils.cacheNewsList(news);
                        HomeFragment.this.news = news;
                        ((BaseActivity) getActivity()).validatePackage();
                    }

                    fillHomeListData(news);
                }

                @Override
                public void getNewsListFailureDelegate(String error) {
                    System.out.println("HomeFragment.getNewsListFailureDelegate");
                }

                @Override
                public void getNewsListConnectionErrorDelegate() {
                    System.out.println("HomeFragment.getNewsListConnectionErrorDelegate");
                }
            }, getActivity());
            tap_to_retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setUpRecyclerData();
                }
            });

        } else {
            tap_to_retry.setVisibility(View.GONE);
            loader.setVisibility(View.VISIBLE);

            Connection.getNewsList(new GetNewsListDelegate() {
                @Override
                public void getNewsListSuccessDelegate(ArrayList<News> news, Boolean success) {
                    System.out.println("HomeFragment.getNewsListSuccessDelegate : here");

                    loader.setVisibility(View.GONE);
                    tap_to_retry.setVisibility(View.GONE);

                    if (news != null && news.size() > 0) {
                        RajaCacheUtils.cacheNewsList(news);
                        HomeFragment.this.news = news;
                    }

                    fillHomeListData(news);
                }

                @Override
                public void getNewsListFailureDelegate(String error) {
                    System.out.println("HomeFragment.getNewsListFailureDelegate");
                    loader.setVisibility(View.GONE);
                    tap_to_retry.setVisibility(View.VISIBLE);
                }

                @Override
                public void getNewsListConnectionErrorDelegate() {
                    System.out.println("HomeFragment.getNewsListConnectionErrorDelegate");
                    loader.setVisibility(View.GONE);
                    tap_to_retry.setVisibility(View.VISIBLE);
                }
            }, getActivity());
            tap_to_retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setUpRecyclerData();
                }
            });
        }
    }

    private void fillHomeListData(ArrayList<News> news) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listRecycler.setLayoutManager(linearLayoutManager);
        homeListAdapter = new HomeListAdapter(getActivity(), news, getActivity());
        listRecycler.setAdapter(homeListAdapter);
        implementItemClickListener();
    }

    private void implementItemClickListener() {
        ItemClickSupport.addTo(listRecycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                System.out.println("HomeFragment.onItemClicked : clicked");

                if (news.get(position).getVideo() != null &&
                        news.get(position).getVideo().trim().length() > 0 &&
                        !news.get(position).getVideo().equalsIgnoreCase("[]")) {

                    String url = news.get(position).getVideo();
                    Intent intent = new Intent(getActivity(), ViewVideo.class);
                    intent.putExtra("videoUrl", url);
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.defff);

                } else {
                    try {
                        String url = news.get(position).getLink();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
