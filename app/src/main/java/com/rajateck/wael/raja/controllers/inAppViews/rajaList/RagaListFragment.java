package com.rajateck.wael.raja.controllers.inAppViews.rajaList;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.connection.Connection;
import com.rajateck.wael.raja.controllers.inAppViews.BaseActivity;
import com.rajateck.wael.raja.controllers.inAppViews.rajaList.holders.MobileViewHolder;
import com.rajateck.wael.raja.delegates.networkDelegates.GetAccessoriesListDelegate;
import com.rajateck.wael.raja.delegates.networkDelegates.GetAndroidAppsDelegate;
import com.rajateck.wael.raja.delegates.networkDelegates.GetHardWareDelegare;
import com.rajateck.wael.raja.delegates.networkDelegates.GetMobileListDelegate;
import com.rajateck.wael.raja.enums.FragmentTags;
import com.rajateck.wael.raja.models.AccessoryItem;
import com.rajateck.wael.raja.models.AndroidApplication;
import com.rajateck.wael.raja.models.ApplicationItem;
import com.rajateck.wael.raja.models.HardWare;
import com.rajateck.wael.raja.models.Mobile;
import com.rajateck.wael.raja.utils.ItemClickSupport;
import com.rajateck.wael.raja.utils.ScreenUtils;
import com.rajateck.wael.raja.utils.cacheUtils.RajaCacheUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.arnaudguyon.tabstacker.TabStacker;

public class RagaListFragment extends Fragment implements TabStacker.TabStackInterface, View.OnClickListener {
    RelativeLayout filterLayout;
    private ArrayList<ApplicationItem> _applicationItemList;
    private List<ApplicationInfo> applicationsInfos;
    private View inflatedView;
    private FragmentTags selectedFragmentTag;
    private RecyclerView listRecycler;
    private RelativeLayout loader;
    private TextView tap_to_retry;
    private ArrayList<Mobile> mobilesList;
    private ArrayList<Mobile> filteredMobilesList;
    private ArrayList<AccessoryItem> accessoryList;
    private ArrayList<HardWare> hardWaresList;
    private ArrayList<AndroidApplication> androidApplicationsList;
    private RecyclerRefreshLayout refresh_layout;
    private TextView lastupdate;
    private CardView allCard;
    private CardView iphoneCard;
    private CardView samsungCard;
    private CardView sonyCard;
    private CardView htcCard;
    private CardView lgCard;
    private CardView CATCard;
    private CardView HaierCard;

    public RagaListFragment() {
        // Required empty public constructor
    }

    public RagaListFragment(FragmentTags fragmentTags) {
        this.selectedFragmentTag = fragmentTags;
    }

    public static RagaListFragment newInstance(String param1, String param2, FragmentTags fragmentTag) {
        RagaListFragment fragment = new RagaListFragment(fragmentTag);
        return fragment;
    }

    private void findViews(View rootView) {
        tap_to_retry = (TextView) rootView.findViewById(R.id.tap_to_retry);
        loader = (RelativeLayout) rootView.findViewById(R.id.relative);
        listRecycler = (RecyclerView) rootView.findViewById(R.id.list_recycler);
        refresh_layout = (RecyclerRefreshLayout) rootView.findViewById(R.id.refresh_layout);
        lastupdate = (TextView) rootView.findViewById(R.id.lastupdate);
        filterLayout = rootView.findViewById(R.id.filterLayout);
        allCard = (CardView) rootView.findViewById(R.id.allCard);
        iphoneCard = (CardView) rootView.findViewById(R.id.iphoneCard);
        samsungCard = (CardView) rootView.findViewById(R.id.samsungCard);
        sonyCard = (CardView) rootView.findViewById(R.id.sonyCard);
        htcCard = (CardView) rootView.findViewById(R.id.htcCard);
        lgCard = (CardView) rootView.findViewById(R.id.lgCard);
        CATCard = (CardView) rootView.findViewById(R.id.CATCard);
        HaierCard = (CardView) rootView.findViewById(R.id.HaierCard);

        allCard.setOnClickListener(this);
        iphoneCard.setOnClickListener(this);
        samsungCard.setOnClickListener(this);
        sonyCard.setOnClickListener(this);
        htcCard.setOnClickListener(this);
        lgCard.setOnClickListener(this);
        CATCard.setOnClickListener(this);
        HaierCard.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_raga_list, container, false);
        setRetainInstance(true);
        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(inflatedView);
        setUpRecyclerData();

        refresh_layout.setRefreshStyle(RecyclerRefreshLayout.RefreshStyle.PINNED);
        refresh_layout.setRefreshing(true);
        refresh_layout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("RagaListFragment.onRefresh : here ot refresh layout.");
                setUpRecyclerData();
            }
        });
    }

    private void setUpRecyclerData() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        if (selectedFragmentTag == null) {
            Intent refresh = getActivity().getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
            refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            getActivity().startActivity(refresh);
            getActivity().finish();

            return;
        }

        if (selectedFragmentTag.equals(FragmentTags.MobileFragment)) {
            filterLayout.getLayoutParams().height = (int) ScreenUtils.dpToPx(getActivity(), 60);

            ArrayList<Mobile> cachedMobileList = RajaCacheUtils.getCachedMobilesList();
            if (cachedMobileList != null &&
                    cachedMobileList.size() > 0) {
                mobilesList = cachedMobileList;
            }


            if (filteredMobilesList != null) {
                lastupdate.setVisibility(View.VISIBLE);

                System.out.println("RagaListFragment.setUpRecyclerData");

                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                listRecycler.setLayoutManager(gridLayoutManager);

                RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), filteredMobilesList, getActivity());
                listRecycler.setAdapter(rajaListAdapter);

                listRecycler.setRecyclerListener(new RecyclerView.RecyclerListener() {
                    @Override
                    public void onViewRecycled(RecyclerView.ViewHolder holder) {
                        if (selectedFragmentTag.equals(FragmentTags.MobileFragment)) {
                            ((MobileViewHolder) holder).onbind(null, getActivity());
                        }
                    }
                });

                implementItemClickListener();
                tap_to_retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setUpRecyclerData();
                    }
                });

            } else if (mobilesList != null) {
                lastupdate.setVisibility(View.VISIBLE);
                try {
                    if (mobilesList.size() > 0) {
                        lastupdate.setVisibility(View.VISIBLE);
                        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.CANADA);
                        lastupdate.setText(getString(R.string.last_update_seting) + simpleDateFormatter.format(mobilesList.get(0).getRefreshCalendar().getTime()));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    lastupdate.setVisibility(View.GONE);
                }
                System.out.println("RagaListFragment.setUpRecyclerData");

                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                listRecycler.setLayoutManager(gridLayoutManager);

                RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), mobilesList, getActivity());
                listRecycler.setAdapter(rajaListAdapter);

                listRecycler.setRecyclerListener(new RecyclerView.RecyclerListener() {
                    @Override
                    public void onViewRecycled(RecyclerView.ViewHolder holder) {
                        if (selectedFragmentTag.equals(FragmentTags.MobileFragment)) {
                            ((MobileViewHolder) holder).onbind(null, getActivity());
                        }
                    }
                });

                implementItemClickListener();
                Connection.getMobilesList(new GetMobileListDelegate() {
                    @Override
                    public void getMobileListSuccessDelegate(ArrayList<Mobile> mobiles, Boolean success) {
                        refresh_layout.setRefreshing(false);
                        if (mobiles == null) {
                            mobiles = new ArrayList<Mobile>();
                        }


                        try {
                            if (mobiles.size() > 0) {
                                lastupdate.setVisibility(View.VISIBLE);
                                SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.CANADA);
                                lastupdate.setText(getString(R.string.last_update_seting) + simpleDateFormatter.format(mobiles.get(0).getRefreshCalendar().getTime()));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            lastupdate.setVisibility(View.GONE);
                        }


                        if (mobiles.size() > 0) {
                            RajaCacheUtils.cacheMobilesList(mobiles);
                        }

                        try {
                            tap_to_retry.setVisibility(View.GONE);
                            loader.setVisibility(View.GONE);
                            mobilesList = mobiles;
                            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

                            listRecycler.setLayoutManager(gridLayoutManager);
                            tap_to_retry.setVisibility(View.GONE);

                            System.out.println("RagaListFragment.getMobileListSuccessDelegate : " + mobiles.size());

                            RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), mobiles, getActivity());
                            listRecycler.setAdapter(rajaListAdapter);

                            implementItemClickListener();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void getMobileListFailureDelegate(String error) {
                        refresh_layout.setRefreshing(false);
                        System.out.println("RagaListFragment.getMobileListFailureDelegate");
                    }

                    @Override
                    public void getMobileListConnectionErrorDelegate() {
                        refresh_layout.setRefreshing(false);
                        System.out.println("RagaListFragment.getMobileListConnectionErrorDelegate");
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

                Connection.getMobilesList(new GetMobileListDelegate() {
                    @Override
                    public void getMobileListSuccessDelegate(ArrayList<Mobile> mobiles, Boolean success) {
                        if (mobiles == null) {
                            mobiles = new ArrayList<Mobile>();
                        }
                        lastupdate.setVisibility(View.VISIBLE);

                        try {
                            if (mobiles.size() > 0) {
                                lastupdate.setVisibility(View.VISIBLE);
                                SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.CANADA);
                                lastupdate.setText(getString(R.string.last_update_seting) + simpleDateFormatter.format(mobiles.get(0).getRefreshCalendar().getTime()));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            lastupdate.setVisibility(View.GONE);
                        }

                        if (mobiles.size() > 0) {
                            RajaCacheUtils.cacheMobilesList(mobiles);
                        }
                        refresh_layout.setRefreshing(false);

                        tap_to_retry.setVisibility(View.GONE);
                        loader.setVisibility(View.GONE);
                        mobilesList = mobiles;
                        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                        try {


                            listRecycler.setLayoutManager(gridLayoutManager);
                            tap_to_retry.setVisibility(View.GONE);

                            System.out.println("RagaListFragment.getMobileListSuccessDelegate : " + mobiles.size());

                            RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), mobiles, getActivity());
                            listRecycler.setAdapter(rajaListAdapter);

                            implementItemClickListener();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void getMobileListFailureDelegate(String error) {
                        System.out.println("RagaListFragment.getMobileListFailureDelegate");
                        loader.setVisibility(View.GONE);
                        tap_to_retry.setVisibility(View.VISIBLE);
                        refresh_layout.setRefreshing(false);

                        lastupdate.setVisibility(View.GONE);

                    }


                    @Override
                    public void getMobileListConnectionErrorDelegate() {
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
        } else if (selectedFragmentTag.equals(FragmentTags.ExtensionsFragment)) {
            ArrayList<AccessoryItem> accessoryItems = RajaCacheUtils.getCachedAccessories();
            if (accessoryItems != null &&
                    accessoryItems.size() > 0) {
                accessoryList = accessoryItems;
            }

            if (accessoryList != null) {
                System.out.println("RagaListFragment.setUpRecyclerData");

                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                listRecycler.setLayoutManager(gridLayoutManager);

                RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), accessoryItems, getActivity(), false);
                listRecycler.setAdapter(rajaListAdapter);

//                implementItemClickListener();

                Connection.getAccessories(new GetAccessoriesListDelegate() {
                    @Override
                    public void getAccessoryListSuccessDelegate(ArrayList<AccessoryItem> accessoryItems, Boolean success) {
                        if (accessoryItems == null) {
                            accessoryItems = new ArrayList<>();
                        }
                        refresh_layout.setRefreshing(false);
                        if (accessoryItems.size() > 0) {
                            RajaCacheUtils.cacheAccessoriesList(accessoryItems);
                        }

                        tap_to_retry.setVisibility(View.GONE);
                        loader.setVisibility(View.GONE);
                        accessoryList = accessoryItems;
                        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                        listRecycler.setLayoutManager(gridLayoutManager);
                        tap_to_retry.setVisibility(View.GONE);

                        System.out.println("RagaListFragment.getMobileListSuccessDelegate : " + accessoryItems.size());

                        RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), accessoryItems, getActivity(), false);
                        listRecycler.setAdapter(rajaListAdapter);

//                        implementItemClickListener();
                    }

                    @Override
                    public void getAccessoryListFailureDelegate(String error) {
                        refresh_layout.setRefreshing(false);
                    }

                    @Override
                    public void getAccessoryListConnectionErrorDelegate() {
                        refresh_layout.setRefreshing(false);
                    }
                }, getActivity());

            } else {
                tap_to_retry.setVisibility(View.GONE);
                loader.setVisibility(View.VISIBLE);

                Connection.getAccessories(new GetAccessoriesListDelegate() {
                    @Override
                    public void getAccessoryListSuccessDelegate(ArrayList<AccessoryItem> accessoryItems, Boolean success) {
                        if (accessoryItems == null) {
                            accessoryItems = new ArrayList<>();
                        }
                        refresh_layout.setRefreshing(false);
                        if (accessoryItems.size() > 0) {
                            RajaCacheUtils.cacheAccessoriesList(accessoryItems);
                        }

                        tap_to_retry.setVisibility(View.GONE);
                        loader.setVisibility(View.GONE);
                        accessoryList = accessoryItems;
                        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                        try {


                            listRecycler.setLayoutManager(gridLayoutManager);
                            tap_to_retry.setVisibility(View.GONE);

                            System.out.println("RagaListFragment.getMobileListSuccessDelegate : " + accessoryItems.size());

                            RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), accessoryItems, getActivity(), false);
                            listRecycler.setAdapter(rajaListAdapter);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void getAccessoryListFailureDelegate(String error) {
                        System.out.println("RagaListFragment.getMobileListFailureDelegate");
                        loader.setVisibility(View.GONE);
                        tap_to_retry.setVisibility(View.VISIBLE);
                        refresh_layout.setRefreshing(false);
                    }

                    @Override
                    public void getAccessoryListConnectionErrorDelegate() {
                        System.out.println("RagaListFragment.getMobileListFailureDelegate");
                        loader.setVisibility(View.GONE);
                        tap_to_retry.setVisibility(View.VISIBLE);
                        refresh_layout.setRefreshing(false);
                    }
                }, getActivity());
                tap_to_retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setUpRecyclerData();
                    }
                });
            }

        } else if (selectedFragmentTag.equals(FragmentTags.ApplicationFragment)) {

            ArrayList<AndroidApplication> androidApplications = RajaCacheUtils.getCachedAndroidApplications();
            if (androidApplications != null &&
                    androidApplications.size() > 0) {
                androidApplicationsList = androidApplications;
            }

            final GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 3);
            gridLayoutManager1.setOrientation(GridLayoutManager.VERTICAL);
            listRecycler.setLayoutManager(gridLayoutManager1);

//
//            if (applicationsInfos == null) {
//                applicationsInfos = getListOfUserApps();
//            }
//
//            if (_applicationItemList == null) {
//                _applicationItemList = ApplicationItem.getList(applicationsInfos, getActivity());
//            }
            if (_applicationItemList == null) {
                _applicationItemList = new ArrayList<>();
            }

//            if (androidApplicationsList != null) {
//                System.out.println("RagaListFragment.setUpRecyclerData");
//
//
//                RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), _applicationItemList, androidApplicationsList, getActivity(), selectedFragmentTag);
//                listRecycler.setAdapter(rajaListAdapter);
//
//
//                Connection.getAndroidApplicationsList(new GetAndroidAppsDelegate() {
//                    @Override
//                    public void getAppsListSuccessDelegate(ArrayList<AndroidApplication> applications, Boolean success) {
//                        if (applications == null) {
//                            applications = new ArrayList<>();
//                        }
//
//                        if (applications.size() > 0) {
//                            RajaCacheUtils.cacheApplicationsList(applications);
//                        }
//
//                        tap_to_retry.setVisibility(View.GONE);
//                        loader.setVisibility(View.GONE);
//                        androidApplicationsList = applications;
//                        gridLayoutManager1.setOrientation(GridLayoutManager.VERTICAL);
//                        listRecycler.setLayoutManager(gridLayoutManager1);
//                        tap_to_retry.setVisibility(View.GONE);
//
//                        System.out.println("RagaListFragment.getMobileListSuccessDelegate : " + androidApplicationsList.size());
//
//                        if (_applicationItemList == null) {
//                            _applicationItemList = ApplicationItem.getList(applicationsInfos, getActivity());
//                        }
//
//                        RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), _applicationItemList, androidApplicationsList, getActivity(), selectedFragmentTag);
//                        listRecycler.setAdapter(rajaListAdapter);
//
//
//                    }
//
//                    @Override
//                    public void getAppsListFailureDelegate(String error) {
//
//                    }
//
//                    @Override
//                    public void getAppsListConnectionErrorDelegate() {
//
//                    }
//                }, getActivity());
//
//            } else {
            tap_to_retry.setVisibility(View.GONE);
            loader.setVisibility(View.VISIBLE);

            tap_to_retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("RagaListFragment.onClick : Tap to retry clicked ");
                    setUpRecyclerData();
                }
            });

            Connection.getAndroidApplicationsList(new GetAndroidAppsDelegate() {
                @Override
                public void getAppsListSuccessDelegate(ArrayList<AndroidApplication> applications, Boolean success) {
                    if (applications == null) {
                        applications = new ArrayList<>();
                    }
                    refresh_layout.setRefreshing(false);
                    if (applications.size() > 0) {
                        RajaCacheUtils.cacheApplicationsList(applications);
                    }

                    tap_to_retry.setVisibility(View.GONE);
                    loader.setVisibility(View.GONE);
                    androidApplicationsList = applications;
                    gridLayoutManager1.setOrientation(GridLayoutManager.VERTICAL);
                    try {


                        listRecycler.setLayoutManager(gridLayoutManager1);
                        tap_to_retry.setVisibility(View.GONE);

                        System.out.println("RagaListFragment.getMobileListSuccessDelegate : " + androidApplicationsList.size());

//                    if (_applicationItemList == null) {
//                        _applicationItemList = ApplicationItem.getList(applicationsInfos, getActivity());
//                    }

                        RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), _applicationItemList, androidApplicationsList, getActivity(), selectedFragmentTag);
                        listRecycler.setAdapter(rajaListAdapter);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                }

                @Override
                public void getAppsListFailureDelegate(String error) {
                    System.out.println("RagaListFragment.getMobileListFailureDelegate");
                    loader.setVisibility(View.GONE);
                    tap_to_retry.setVisibility(View.VISIBLE);
                    tap_to_retry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("RagaListFragment.onClick : Tap to retry clicked 3");
                            setUpRecyclerData();
                        }
                    });
                    refresh_layout.setRefreshing(false);
                }

                @Override
                public void getAppsListConnectionErrorDelegate() {
                    System.out.println("RagaListFragment.getMobileListFailureDelegate");
                    loader.setVisibility(View.GONE);
                    tap_to_retry.setVisibility(View.VISIBLE);
                    tap_to_retry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("RagaListFragment.onClick : Tap to retry clicked 4");
                            setUpRecyclerData();
                        }
                    });
                    refresh_layout.setRefreshing(false);
                }
            }, getActivity());
            tap_to_retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("RagaListFragment.onClick : Tap to retry clicked 2");
                    setUpRecyclerData();
                }
            });
//            }
        } else {
            ArrayList<HardWare> hardWareItems = RajaCacheUtils.getCachedHardWare();
            if (hardWareItems != null &&
                    hardWareItems.size() > 0) {
                hardWaresList = hardWareItems;
            }

            if (hardWaresList != null) {
                System.out.println("RagaListFragment.setUpRecyclerData");

                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                listRecycler.setLayoutManager(gridLayoutManager);

                RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), hardWareItems, getActivity(), false, true);
                listRecycler.setAdapter(rajaListAdapter);

//                implementItemClickListener();

                Connection.getHardWare(new GetHardWareDelegare() {

                    @Override
                    public void getHardWareListSuccessDelegate(ArrayList<HardWare> hardWareItems, Boolean success) {
                        if (hardWareItems == null) {
                            hardWareItems = new ArrayList<>();
                        }
                        refresh_layout.setRefreshing(false);
                        if (hardWareItems.size() > 0) {
                            RajaCacheUtils.cacheHardWareList(hardWareItems);
                        }

                        tap_to_retry.setVisibility(View.GONE);
                        loader.setVisibility(View.GONE);
                        hardWaresList = hardWareItems;
                        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                        listRecycler.setLayoutManager(gridLayoutManager);
                        tap_to_retry.setVisibility(View.GONE);

                        System.out.println("RagaListFragment.getMobileListSuccessDelegate : " + hardWareItems.size());

                        RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), hardWareItems, getActivity(), false, true);
                        listRecycler.setAdapter(rajaListAdapter);

//                        implementItemClickListener();
                    }

                    @Override
                    public void getHardWareListFailureDelegate(String error) {
                        refresh_layout.setRefreshing(false);
                    }

                    @Override
                    public void getHardWareListConnectionErrorDelegate() {
                        refresh_layout.setRefreshing(false);
                    }
                }, getActivity());

            } else {
                tap_to_retry.setVisibility(View.GONE);
                loader.setVisibility(View.VISIBLE);

                Connection.getHardWare(new GetHardWareDelegare() {
                    @Override
                    public void getHardWareListSuccessDelegate(ArrayList<HardWare> hardWareItems, Boolean success) {
                        if (hardWareItems == null) {
                            hardWareItems = new ArrayList<>();
                        }
                        refresh_layout.setRefreshing(false);
                        if (hardWareItems.size() > 0) {
                            RajaCacheUtils.cacheHardWareList(hardWareItems);
                        }

                        tap_to_retry.setVisibility(View.GONE);
                        loader.setVisibility(View.GONE);
                        hardWaresList = hardWareItems;
                        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                        try {


                            listRecycler.setLayoutManager(gridLayoutManager);
                            tap_to_retry.setVisibility(View.GONE);

                            System.out.println("RagaListFragment.getMobileListSuccessDelegate : " + hardWareItems.size());

                            RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), hardWareItems, getActivity(), false, true);
                            listRecycler.setAdapter(rajaListAdapter);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void getHardWareListFailureDelegate(String error) {
                        System.out.println("RagaListFragment.getMobileListFailureDelegate");
                        loader.setVisibility(View.GONE);
                        tap_to_retry.setVisibility(View.VISIBLE);
                        refresh_layout.setRefreshing(false);
                    }

                    @Override
                    public void getHardWareListConnectionErrorDelegate() {
                        System.out.println("RagaListFragment.getMobileListFailureDelegate");
                        loader.setVisibility(View.GONE);
                        tap_to_retry.setVisibility(View.VISIBLE);
                        refresh_layout.setRefreshing(false);
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
    }

    private void implementItemClickListener() {
        ItemClickSupport.addTo(listRecycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if (filteredMobilesList != null) {
                    try {
                        ((BaseActivity) getActivity()).addMobileDetails(filteredMobilesList.get(position));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    ((BaseActivity) getActivity()).addMobileDetails(mobilesList.get(position));
                }
            }
        });
    }

    @Override
    public void onTabFragmentPresented(TabStacker.PresentReason presentReason) {

    }

    @Override
    public void onTabFragmentDismissed(TabStacker.DismissReason dismissReason) {
        filteredMobilesList = null;
    }

    @Override
    public View onSaveTabFragmentInstance(Bundle bundle) {
        return null;
    }

    @Override
    public void onRestoreTabFragmentInstance(Bundle bundle) {

    }

    public List<ApplicationInfo> getListOfUserApps() {
        System.out.println("RagaListFragment.getListOfUserApps : 1");
        PackageManager pm = getActivity().getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);


        List<ApplicationInfo> installedApps = new ArrayList<ApplicationInfo>();

        for (ApplicationInfo app : apps) {

            //checks for flags; if flagged, check if updated system app
            if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
//                installedApps.add(app);
//                System.out.println("RagaListFragment.getListOfUserApps : 2");
                //it's a system app, not interested
            } else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
//                System.out.println("RagaListFragment.getListOfUserApps : 3");
                //Discard this one
                //in this case, it should be a user-installed app
            } else {
                installedApps.add(app);
                System.out.println("RagaListFragment.getListOfUserApps :" + (String) pm.getApplicationLabel(app));
            }
        }

//        String label = (String)pm.getApplicationLabel(app);
//        Drawable icon = pm.getApplicationIcon(app);
        return installedApps;
    }

    @Override
    public void onClick(View v) {
        if (v == allCard) {
            filterMobilesOn(null);
        } else if (v == iphoneCard) {
            filterMobilesOn("iphone");

        } else if (v == samsungCard) {
            filterMobilesOn("samsung");

        } else if (v == sonyCard) {
            filterMobilesOn("sony");

        } else if (v == htcCard) {
            filterMobilesOn("htc");

        } else if (v == lgCard) {
            filterMobilesOn("lg");

        } else if (v == CATCard) {
            filterMobilesOn("cat");

        } else if (v == HaierCard) {
            filterMobilesOn("haier");

        }
    }

    private void filterMobilesOn(String type) {
        if (mobilesList == null) {
            return;
        }
        if (type == null) {
            filteredMobilesList = null;
            updateListFor(mobilesList);
            allCard.setCardBackgroundColor(Color.parseColor("#ee3c34"));
            iphoneCard.setCardBackgroundColor(Color.WHITE);
            samsungCard.setCardBackgroundColor(Color.WHITE);
            htcCard.setCardBackgroundColor(Color.WHITE);
            sonyCard.setCardBackgroundColor(Color.WHITE);
            lgCard.setCardBackgroundColor(Color.WHITE);
            CATCard.setCardBackgroundColor(Color.WHITE);
            HaierCard.setCardBackgroundColor(Color.WHITE);
        } else if (type.equalsIgnoreCase("iphone")) {
            filteredMobilesList = new ArrayList<>();
            for (int i = 0; i < mobilesList.size(); i++) {
                if (mobilesList.get(i).getCompany().toLowerCase().contains("apple")) {
                    filteredMobilesList.add(mobilesList.get(i));
                }
            }

            updateListFor(filteredMobilesList);
            allCard.setCardBackgroundColor(Color.WHITE);
            iphoneCard.setCardBackgroundColor(Color.parseColor("#ee3c34"));
            samsungCard.setCardBackgroundColor(Color.WHITE);
            htcCard.setCardBackgroundColor(Color.WHITE);
            sonyCard.setCardBackgroundColor(Color.WHITE);
            lgCard.setCardBackgroundColor(Color.WHITE);
            CATCard.setCardBackgroundColor(Color.WHITE);
            HaierCard.setCardBackgroundColor(Color.WHITE);
        } else if (type.equalsIgnoreCase("samsung")) {
            filteredMobilesList = new ArrayList<>();
            for (int i = 0; i < mobilesList.size(); i++) {
                if (mobilesList.get(i).getCompany().toLowerCase().contains("samsung")) {
                    filteredMobilesList.add(mobilesList.get(i));
                }
            }
            updateListFor(filteredMobilesList);
            allCard.setCardBackgroundColor(Color.WHITE);
            iphoneCard.setCardBackgroundColor(Color.WHITE);
            samsungCard.setCardBackgroundColor(Color.parseColor("#ee3c34"));
            htcCard.setCardBackgroundColor(Color.WHITE);
            sonyCard.setCardBackgroundColor(Color.WHITE);
            lgCard.setCardBackgroundColor(Color.WHITE);
            CATCard.setCardBackgroundColor(Color.WHITE);
            HaierCard.setCardBackgroundColor(Color.WHITE);
        } else if (type.equalsIgnoreCase("sony")) {
            filteredMobilesList = new ArrayList<>();
            for (int i = 0; i < mobilesList.size(); i++) {
                if (mobilesList.get(i).getCompany().toLowerCase().contains("sony")) {
                    filteredMobilesList.add(mobilesList.get(i));
                }
            }
            updateListFor(filteredMobilesList);
            allCard.setCardBackgroundColor(Color.WHITE);
            iphoneCard.setCardBackgroundColor(Color.WHITE);
            samsungCard.setCardBackgroundColor(Color.WHITE);
            htcCard.setCardBackgroundColor(Color.WHITE);
            sonyCard.setCardBackgroundColor(Color.parseColor("#ee3c34"));
            lgCard.setCardBackgroundColor(Color.WHITE);
            CATCard.setCardBackgroundColor(Color.WHITE);
            HaierCard.setCardBackgroundColor(Color.WHITE);
        } else if (type.equalsIgnoreCase("htc")) {
            filteredMobilesList = new ArrayList<>();
            for (int i = 0; i < mobilesList.size(); i++) {
                if (mobilesList.get(i).getCompany().toLowerCase().contains("htc")) {
                    filteredMobilesList.add(mobilesList.get(i));
                }
            }
            updateListFor(filteredMobilesList);
            allCard.setCardBackgroundColor(Color.WHITE);
            iphoneCard.setCardBackgroundColor(Color.WHITE);
            samsungCard.setCardBackgroundColor(Color.WHITE);
            htcCard.setCardBackgroundColor(Color.parseColor("#ee3c34"));
            sonyCard.setCardBackgroundColor(Color.WHITE);
            lgCard.setCardBackgroundColor(Color.WHITE);
            CATCard.setCardBackgroundColor(Color.WHITE);
            HaierCard.setCardBackgroundColor(Color.WHITE);
        } else if (type.equalsIgnoreCase("lg")) {
            filteredMobilesList = new ArrayList<>();
            for (int i = 0; i < mobilesList.size(); i++) {
                if (mobilesList.get(i).getCompany().toLowerCase().contains("lg")) {
                    filteredMobilesList.add(mobilesList.get(i));
                }
            }

            updateListFor(filteredMobilesList);
            allCard.setCardBackgroundColor(Color.WHITE);
            iphoneCard.setCardBackgroundColor(Color.WHITE);
            samsungCard.setCardBackgroundColor(Color.WHITE);
            htcCard.setCardBackgroundColor(Color.WHITE);
            sonyCard.setCardBackgroundColor(Color.WHITE);
            CATCard.setCardBackgroundColor(Color.WHITE);
            HaierCard.setCardBackgroundColor(Color.WHITE);
            lgCard.setCardBackgroundColor(Color.parseColor("#ee3c34"));
        }else if (type.equalsIgnoreCase("cat")) {
            filteredMobilesList = new ArrayList<>();
            for (int i = 0; i < mobilesList.size(); i++) {
                if (mobilesList.get(i).getCompany().toLowerCase().contains("cat")) {
                    filteredMobilesList.add(mobilesList.get(i));
                }
            }

            updateListFor(filteredMobilesList);
            allCard.setCardBackgroundColor(Color.WHITE);
            iphoneCard.setCardBackgroundColor(Color.WHITE);
            samsungCard.setCardBackgroundColor(Color.WHITE);
            htcCard.setCardBackgroundColor(Color.WHITE);
            sonyCard.setCardBackgroundColor(Color.WHITE);
            HaierCard.setCardBackgroundColor(Color.WHITE);
            CATCard.setCardBackgroundColor(Color.parseColor("#ee3c34"));
            lgCard.setCardBackgroundColor(Color.WHITE);
        } else if (type.equalsIgnoreCase("haier")) {
            filteredMobilesList = new ArrayList<>();
            for (int i = 0; i < mobilesList.size(); i++) {
                if (mobilesList.get(i).getCompany().toLowerCase().contains("haier")) {
                    filteredMobilesList.add(mobilesList.get(i));
                }
            }

            updateListFor(filteredMobilesList);
            allCard.setCardBackgroundColor(Color.WHITE);
            iphoneCard.setCardBackgroundColor(Color.WHITE);
            samsungCard.setCardBackgroundColor(Color.WHITE);
            htcCard.setCardBackgroundColor(Color.WHITE);
            sonyCard.setCardBackgroundColor(Color.WHITE);
            CATCard.setCardBackgroundColor(Color.WHITE);
            HaierCard.setCardBackgroundColor(Color.parseColor("#ee3c34"));
            lgCard.setCardBackgroundColor(Color.WHITE);
        }
    }

    private void updateListFor(ArrayList<Mobile> filteredMobilesList) {
        setUpRecyclerData();
    }
}
