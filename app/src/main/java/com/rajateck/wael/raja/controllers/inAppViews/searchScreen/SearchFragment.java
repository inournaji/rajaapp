package com.rajateck.wael.raja.controllers.inAppViews.searchScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.controllers.inAppViews.BaseActivity;
import com.rajateck.wael.raja.controllers.inAppViews.rajaList.RajaListAdapter;
import com.rajateck.wael.raja.models.AccessoryItem;
import com.rajateck.wael.raja.models.Mobile;
import com.rajateck.wael.raja.utils.ItemClickSupport;
import com.rajateck.wael.raja.utils.cacheUtils.RajaCacheUtils;

import java.util.ArrayList;

import fr.arnaudguyon.tabstacker.TabStacker;

public class SearchFragment extends Fragment implements TabStacker.TabStackInterface {
    FloatingSearchView mSearchView;
    private View inflatedView;
//    private TextView mobileTitle;
    private View seperator;
    private ImageView close;
    private RecyclerView listRecycler;
    private ArrayList<Object> currentSearchList;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    private void findViews(View rootView) {
//        mobileTitle = (TextView) rootView.findViewById(R.id.mobile_title);
        seperator = (View) rootView.findViewById(R.id.seperator);
        close = (ImageView) rootView.findViewById(R.id.close);
        listRecycler = (RecyclerView) rootView.findViewById(R.id.list_recycler);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        mSearchView = (FloatingSearchView) rootView.findViewById(R.id.floating_search_view);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery

                //pass them on to the search view
//                mSearchView.swapSuggestions(newSuggestions);
                System.out.println("SearchFragment.onSearchTextChanged: here ");
                if (oldQuery != null
                        && newQuery != null
                        && oldQuery.equalsIgnoreCase(newQuery)) {
                    System.out.println("SearchFragment.onSearchTextChanged: it's the same result.");
                } else {
                    getSearchResultsForThisQuery(newQuery);
                }


            }
        });

    }

    private void getSearchResultsForThisQuery(String newQuery) {
        ArrayList<Mobile> cachedMobileList = RajaCacheUtils.getCachedMobilesList();
        if (cachedMobileList == null) {
            cachedMobileList = new ArrayList<>();
        }

        ArrayList<Object> searchResult = new ArrayList<>();
        for (int i = 0; i < cachedMobileList.size(); i++) {
            if (cachedMobileList.get(i).getTitle() != null &&
                    cachedMobileList.get(i).getTitle().toLowerCase().trim().contains(newQuery.trim().toLowerCase())) {
                searchResult.add(cachedMobileList.get(i));
                System.out.println("SearchFragment.getSearchResultsForThisQuery: added.");
            } else {
//                assert cachedMobileList.get(i).getTitle() != null;
//                System.out.println("SearchFragment.getSearchResultsForThisQuery, not found at the item =" + cachedMobileList.get(i).getTitle());
            }
        }

        ArrayList<AccessoryItem> accessoryItems = RajaCacheUtils.getCachedAccessories();
        if (accessoryItems == null) {
            accessoryItems = new ArrayList<>();
        }


        for (int i = 0; i < accessoryItems.size(); i++) {
            if (accessoryItems.get(i).getTitle() != null &&
                    accessoryItems.get(i).getTitle().toLowerCase().trim().contains(newQuery.trim().toLowerCase())) {
                searchResult.add(accessoryItems.get(i));
                System.out.println("SearchFragment.getSearchResultsForThisQuery accessory: added.");
            } else if (accessoryItems.get(i).getTitle() != null &&
                    accessoryItems.get(i).getMobile() != null &&
                    accessoryItems.get(i).getMobile().size() > 0) {

                for (int j = 0; j < accessoryItems.get(i).getMobile().size(); j++) {

                    if (accessoryItems.get(i).getMobile().get(j).equalsIgnoreCase("All Mobiles")) {
                        searchResult.add(accessoryItems.get(i));
                        System.out.println("SearchFragment.getSearchResultsForThisQuery All mobile accessory: added.");
                        break;
                    }

                    if (accessoryItems.get(i).getMobile().get(j).toLowerCase().trim().contains(newQuery.trim().toLowerCase())) {
                        searchResult.add(accessoryItems.get(i));
                        System.out.println("SearchFragment.getSearchResultsForThisQuery mobile accessory: added.");
                        break;
                    }

                }
            } else {
                System.out.println("SearchFragment.getSearchResultsForThisQuery: no thing to add here");
            }

        }


        initReyclerViewDetails(searchResult);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_search, container, false);
        findViews(inflatedView);


        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(inflatedView);
        initReyclerViewDetails(new ArrayList<Object>());
    }

    private void initReyclerViewDetails(ArrayList<Object> objects) {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        listRecycler.setLayoutManager(gridLayoutManager);

        if (objects == null) {
            objects = new ArrayList<>();
        }
        currentSearchList = objects;

        RajaListAdapter rajaListAdapter = new RajaListAdapter(getActivity(), getActivity(), objects);
        listRecycler.setAdapter(rajaListAdapter);

        implementItemClickListener();

    }

    private void implementItemClickListener() {
        ItemClickSupport.addTo(listRecycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if (currentSearchList.get(position) instanceof Mobile) {
                    ((BaseActivity) getActivity()).addMobileDetailsFromSearch(((Mobile) currentSearchList.get(position)));
                } else {
                    System.out.println("SearchFragment.onItemClicked : it's the mobile details");
                }
            }
        });
    }


    @Override
    public void onTabFragmentPresented(TabStacker.PresentReason presentReason) {

    }

    @Override
    public void onTabFragmentDismissed(TabStacker.DismissReason dismissReason) {

    }

    @Override
    public View onSaveTabFragmentInstance(Bundle bundle) {
        return null;
    }

    @Override
    public void onRestoreTabFragmentInstance(Bundle bundle) {

    }


}
