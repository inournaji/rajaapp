package com.rajateck.wael.raja.controllers.inAppViews.otherServices;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rajateck.wael.raja.R;

import fr.arnaudguyon.tabstacker.TabStacker;

public class OtherServiceFragment extends Fragment implements TabStacker.TabStackInterface {
    private View inflatedView;


    public OtherServiceFragment() {
        // Required empty public constructor
    }

    public static OtherServiceFragment newInstance(String param1, String param2) {
        OtherServiceFragment fragment = new OtherServiceFragment();
        return fragment;
    }

    private void findViews(View rootView) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_other_service, container, false);
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
        return null;
    }

    @Override
    public void onRestoreTabFragmentInstance(Bundle bundle) {

    }
}
