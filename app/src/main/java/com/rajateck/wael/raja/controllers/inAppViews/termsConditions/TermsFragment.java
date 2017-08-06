package com.rajateck.wael.raja.controllers.inAppViews.termsConditions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.controllers.inAppViews.BaseActivity;
import com.rajateck.wael.raja.enums.FragmentTags;

import fr.arnaudguyon.tabstacker.TabStacker;

public class TermsFragment extends Fragment implements TabStacker.TabStackInterface {
    private View inflatedView;

    public TermsFragment() {
        // Required empty public constructor
    }

    public TermsFragment(FragmentTags fragmentTags) {

    }

    public static TermsFragment newInstance(String param1, String param2, FragmentTags fragmentTag) {
        TermsFragment fragment = new TermsFragment(fragmentTag);
        return fragment;
    }

    private void findViews(View rootView) {
        RelativeLayout close = (RelativeLayout) rootView.findViewById(R.id.search_icon_container);
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        close.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("TermsFragment.onClick : the close is clicked ");
                ((BaseActivity) getActivity()).closeLayout ();
                return false;
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
        inflatedView = inflater.inflate(R.layout.fragment_terms_fragment, container, false);
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

}
