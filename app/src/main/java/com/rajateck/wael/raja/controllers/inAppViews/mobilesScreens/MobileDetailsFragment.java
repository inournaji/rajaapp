package com.rajateck.wael.raja.controllers.inAppViews.mobilesScreens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.models.Mobile;

import fr.arnaudguyon.tabstacker.TabStacker;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import ru.truba.touchgallery.GalleryWidget.UrlPagerAdapter;

public class MobileDetailsFragment extends Fragment implements TabStacker.TabStackInterface {
    private View inflatedView;
    private Mobile mobile;
    private RelativeLayout headerRel;
    private TextView mobileTitle;
    private GalleryViewPager viewer;
    private View seperator;
    private TextView titleField;
    private TextView BatteryField;
    private TextView CameraField;
    private TextView CompanyField;
    private TextView PriceField;
    private TextView ProcessorField;
    private TextView RamField;
    private TextView StorageField;
    private TextView ScreenField;

    private TextView titleField_value;
    private TextView BatteryField_value;
    private TextView CameraField_value;
    private TextView CompanyField_value;
    private TextView PriceField_value;
    private TextView ProcessorField_value;
    private TextView RamField_value;
    private TextView StorageField_value;
    private TextView ScreenField_value;

    private ImageView close;
    private ImageView right_arrow;
    private ImageView left_arrow;


    public MobileDetailsFragment(Mobile mobile) {
        this.mobile = mobile;

    }

    public MobileDetailsFragment() {
        // Required empty public constructor
    }

    public static MobileDetailsFragment newInstance(String param1, String param2) {
        MobileDetailsFragment fragment = new MobileDetailsFragment();
        return fragment;
    }

    private void findViews(View rootView) {
        right_arrow = (ImageView) rootView.findViewById(R.id.right_arrow);
        left_arrow = (ImageView) rootView.findViewById(R.id.left_arrow);
        headerRel = (RelativeLayout) rootView.findViewById(R.id.header_rel);
        mobileTitle = (TextView) rootView.findViewById(R.id.mobile_title);
        viewer = (GalleryViewPager) rootView.findViewById(R.id.viewer);
        seperator = (View) rootView.findViewById(R.id.seperator);
        titleField = (TextView) rootView.findViewById(R.id.title_field);
        BatteryField = (TextView) rootView.findViewById(R.id.Battery_field);
        CameraField = (TextView) rootView.findViewById(R.id.Camera_field);
        CompanyField = (TextView) rootView.findViewById(R.id.Company_field);
        PriceField = (TextView) rootView.findViewById(R.id.Price_field);
        ProcessorField = (TextView) rootView.findViewById(R.id.Processor_field);
        RamField = (TextView) rootView.findViewById(R.id.Ram_field);
        StorageField = (TextView) rootView.findViewById(R.id.Storage_field);
        ScreenField = (TextView) rootView.findViewById(R.id.Screen_field);

        titleField_value = (TextView) rootView.findViewById(R.id.title_field_value);
        BatteryField_value = (TextView) rootView.findViewById(R.id.Battery_field_value);
        CameraField_value = (TextView) rootView.findViewById(R.id.Camera_field_value);
        CompanyField_value = (TextView) rootView.findViewById(R.id.Company_field_value);
        PriceField_value = (TextView) rootView.findViewById(R.id.Price_field_value);
        ProcessorField_value = (TextView) rootView.findViewById(R.id.Processor_field_value);
        RamField_value = (TextView) rootView.findViewById(R.id.Ram_field_value);
        StorageField_value = (TextView) rootView.findViewById(R.id.Storage_field_value);
        ScreenField_value = (TextView) rootView.findViewById(R.id.Screen_field_value);


        close = (ImageView) rootView.findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        inflatedView = inflater.inflate(R.layout.fragment_mobile_details, container, false);
        findViews(inflatedView);
        final UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(getActivity(), mobile.getImage());
        final GalleryViewPager mViewPager =
                (GalleryViewPager) inflatedView.findViewById(R.id.viewer);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(pagerAdapter);

        right_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mViewPager.getCurrentItem() < pagerAdapter.getCount() - 1) {
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    } else {
                        YoYo.with(Techniques.Shake).playOn(right_arrow);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    YoYo.with(Techniques.Shake).playOn(right_arrow);
                }

            }
        });

        left_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mViewPager.getCurrentItem() > 0) {
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
                    } else {
                        YoYo.with(Techniques.Shake).playOn(left_arrow);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    YoYo.with(Techniques.Shake).playOn(left_arrow);
                }
            }
        });

        fillForm();


        return inflatedView;
    }

    private void fillForm() {

        titleField.setText("Title");
        BatteryField.setText("Battery");
        CameraField.setText("Camera");
        CompanyField.setText("Company");
        PriceField.setText("Price");
        ProcessorField.setText("Processor");
        RamField.setText("Ram");
        StorageField.setText("Storage");
        ScreenField.setText("Screen");

        titleField_value.setText("\t"+mobile.getTitle());
        BatteryField_value.setText("\t"+mobile.getBattery());
        CameraField_value.setText("\t"+mobile.getCamera());
        CompanyField_value.setText("\t"+mobile.getCompany());
        PriceField_value.setText("\t"+mobile.getPrice());
        ProcessorField_value.setText("\t"+mobile.getProcessor());
        RamField_value.setText("\t"+mobile.getRam());
        StorageField_value.setText("\t"+mobile.getStorage());
        ScreenField_value.setText("\t"+mobile.getScreensize());


        mobileTitle.setText(mobile.getTitle() + "");

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
