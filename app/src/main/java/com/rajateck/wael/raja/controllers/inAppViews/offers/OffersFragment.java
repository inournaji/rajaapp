package com.rajateck.wael.raja.controllers.inAppViews.offers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.connection.Connection;
import com.rajateck.wael.raja.controllers.inAppViews.rajaList.RajaListAdapter;
import com.rajateck.wael.raja.delegates.GetOffersListDelegate;
import com.rajateck.wael.raja.enums.FragmentTags;
import com.rajateck.wael.raja.models.Body;
import com.rajateck.wael.raja.models.Notification_item;
import com.rajateck.wael.raja.models.OfferItem;
import com.rajateck.wael.raja.utils.cacheUtils.RajaCacheUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import fr.arnaudguyon.tabstacker.TabStacker;

public class OffersFragment extends Fragment implements TabStacker.TabStackInterface {
    private View inflatedView;
    private FragmentTags selectedFragmentTag;
    private RecyclerView listRecycler;
    private RelativeLayout loader;
    private TextView tap_to_retry;
    private ArrayList<OfferItem> offersList;
    private RecyclerRefreshLayout refresh_layout;
    private TextView lastupdate;
    private RelativeLayout search_icon_container;

    public OffersFragment() {
        // Required empty public constructor
    }

    public OffersFragment(FragmentTags fragmentTags) {
        this.selectedFragmentTag = fragmentTags;
    }

    public static OffersFragment newInstance(String param1, String param2, FragmentTags fragmentTag) {
        OffersFragment fragment = new OffersFragment(fragmentTag);
        return fragment;
    }

    private void findViews(View rootView) {
        tap_to_retry = (TextView) rootView.findViewById(R.id.tap_to_retry);
        loader = (RelativeLayout) rootView.findViewById(R.id.relative);
        listRecycler = (RecyclerView) rootView.findViewById(R.id.list_recycler);
        refresh_layout = (RecyclerRefreshLayout) rootView.findViewById(R.id.refresh_layout);
        lastupdate = (TextView) rootView.findViewById(R.id.lastupdate);
        refresh_layout.setEnabled(false);

        search_icon_container = (RelativeLayout) rootView.findViewById(R.id.search_icon_container);
        search_icon_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_offers_list, container, false);
        setRetainInstance(true);
        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(inflatedView);
        setUpRecyclerData();

//        refresh_layout.setRefreshStyle(RecyclerRefreshLayout.RefreshStyle.PINNED);
//        refresh_layout.setRefreshing(true);
//        refresh_layout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                System.out.println("RagaListFragment.onRefresh : here ot refresh layout.");
//                setUpRecyclerData();
//            }
//        });
    }

    private void setUpRecyclerData() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        System.out.println("OffersFragment.setUpRecyclerData : here to setup the list");

        ArrayList<Notification_item> notification_items = RajaCacheUtils.GetNotificatios();
        if (notification_items == null) {
            System.out.println("OffersFragment.setUpRecyclerData: here it's null");
            notification_items = new ArrayList<>();
        }
        ArrayList<OfferItem> cachedMobileList = new ArrayList<>();

        for (int i = 0; i < notification_items.size(); i++) {
            OfferItem offerItem = new OfferItem();
            Body body = new Body();
            body.setSafeValue(notification_items.get(i).getMessageEn());
            body.setSummary(notification_items.get(i).getMessageEn());
            body.setValue(notification_items.get(i).getMessageEn());

            offerItem.setBody(body);
            try {
                SimpleDateFormat dater = new SimpleDateFormat("EEEE d MMM", Locale.US);//"+getDayOfMonthSuffix(model.getGroupCal())+"
                offerItem.setCreated(dater.format(notification_items.get(i).getNotificationTime().getTime()));
            } catch (Exception ex) {
                ex.printStackTrace();
                offerItem.setCreated("---");
            }

            cachedMobileList.add(offerItem);
        }

        offersList = cachedMobileList;

        if (offersList != null) {
            lastupdate.setVisibility(View.VISIBLE);
            try {
                if (offersList.size() > 0) {
                    lastupdate.setVisibility(View.VISIBLE);
                    SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.CANADA);
                    lastupdate.setVisibility(View.GONE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                lastupdate.setVisibility(View.GONE);
            }
            System.out.println("RagaListFragment.setUpRecyclerData");

            linearLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            listRecycler.setLayoutManager(linearLayoutManager);

            RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), offersList, getActivity());
            listRecycler.setAdapter(rajaListAdapter);
        } else {
            tap_to_retry.setVisibility(View.GONE);
            loader.setVisibility(View.VISIBLE);
            Connection.getOffersList(new GetOffersListDelegate() {
                @Override
                public void getOffersListSuccessDelegate(ArrayList<OfferItem> offerItems, Boolean success) {
                    if (offerItems == null) {
                        offerItems = new ArrayList<OfferItem>();
                    }
                    lastupdate.setVisibility(View.VISIBLE);

                    try {
                        if (offerItems.size() > 0) {
                            lastupdate.setVisibility(View.VISIBLE);
                            SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.CANADA);
                            lastupdate.setVisibility(View.GONE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        lastupdate.setVisibility(View.GONE);
                    }

                    if (offerItems.size() > 0) {
                        RajaCacheUtils.cacheOffersList(offerItems);
                    }
                    refresh_layout.setRefreshing(false);

                    tap_to_retry.setVisibility(View.GONE);
                    loader.setVisibility(View.GONE);
                    offersList = offerItems;
                    linearLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    try {

                        listRecycler.setLayoutManager(linearLayoutManager);
                        tap_to_retry.setVisibility(View.GONE);
                        System.out.println("RagaListFragment.getMobileListSuccessDelegate : " + offerItems.size());
                        RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), offerItems, getActivity());
                        listRecycler.setAdapter(rajaListAdapter);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void getOffersListFailureDelegate(String error) {
                    System.out.println("RagaListFragment.getMobileListFailureDelegate");
                    loader.setVisibility(View.GONE);
                    tap_to_retry.setVisibility(View.VISIBLE);
                    refresh_layout.setRefreshing(false);
                    lastupdate.setVisibility(View.GONE);
                }

                @Override
                public void getOffersListConnectionErrorDelegate() {
                    System.out.println("RagaListFragment.getMobileListConnectionErrorDelegate");
                    loader.setVisibility(View.GONE);
                    tap_to_retry.setVisibility(View.VISIBLE);
                    refresh_layout.setRefreshing(false);
                    lastupdate.setVisibility(View.GONE);
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

}
