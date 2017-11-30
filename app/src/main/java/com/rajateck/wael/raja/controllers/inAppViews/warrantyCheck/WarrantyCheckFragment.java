package com.rajateck.wael.raja.controllers.inAppViews.warrantyCheck;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.connection.Connection;
import com.rajateck.wael.raja.controllers.inAppViews.BaseActivity;
import com.rajateck.wael.raja.delegates.ActivateMobileNumberDelegate;
import com.rajateck.wael.raja.models.WarrentyCheckDetails;
import com.rajateck.wael.raja.utils.CheckPermissionUtils;
import com.rajateck.wael.raja.utils.ScreenUtils;
import com.rajateck.wael.raja.utils.cacheUtils.RajaCacheUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fr.arnaudguyon.tabstacker.TabStacker;

public class WarrantyCheckFragment extends Fragment implements TabStacker.TabStackInterface {
    private View inflatedView;
    private RelativeLayout headerRel;
    private ImageView close;
    private View seperator;
    private TextView enterYour;
    private EditText warrantyEditText;
    private EditText mobileEditText;
    private Button check;
    private TextView title;
    private TextView valid;
    private TextView endDate;
    private TextView startDate;
    private TextView note;
    private RelativeLayout detailsView;
    private Boolean removeHeader;
    private RelativeLayout top_bar_layout;

    public WarrantyCheckFragment() {
        // Required empty public constructor
    }

    public WarrantyCheckFragment(boolean b) {
        this.removeHeader = b;
    }

    public static WarrantyCheckFragment newInstance(String param1, String param2) {
        WarrantyCheckFragment fragment = new WarrantyCheckFragment();
        return fragment;
    }

    private void findViews(View rootView) {
        headerRel = (RelativeLayout) rootView.findViewById(R.id.header_rel);
        close = (ImageView) rootView.findViewById(R.id.close);
        seperator = (View) rootView.findViewById(R.id.seperator);
        enterYour = (TextView) rootView.findViewById(R.id.enter_your_);
        warrantyEditText = (EditText) rootView.findViewById(R.id.warranty_edit_text);
        mobileEditText = (EditText) rootView.findViewById(R.id.mobile_edit_text);
        check = (Button) rootView.findViewById(R.id.check);
        seperator = (View) rootView.findViewById(R.id.seperator);
        close = (ImageView) rootView.findViewById(R.id.close);
        title = (TextView) rootView.findViewById(R.id.title);
        valid = (TextView) rootView.findViewById(R.id.valid);
        detailsView = (RelativeLayout) rootView.findViewById(R.id.detailsView);
        endDate = (TextView) rootView.findViewById(R.id.endDate);
        startDate = (TextView) rootView.findViewById(R.id.startDate);
        note = (TextView) rootView.findViewById(R.id.note);
        top_bar_layout = rootView.findViewById(R.id.top_bar_layout);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        try {

            RelativeLayout search_icon_container = (RelativeLayout) rootView.findViewById(R.id.search_icon_container);
            search_icon_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warrantyEditText.getText().toString().trim().length() >= 10) {
                    // &&mobileEditText.getText().toString().trim().length() > 9
                    ScreenUtils.showLoader(getActivity());
                    detailsView.setVisibility(View.GONE);
                    System.out.println("WarrantyCheckFragment.onClick");

                    Connection.activateWarrantyForIMEI(new ActivateMobileNumberDelegate() {
                                                           @Override
                                                           public void activateDeviceDelegateSuccess(WarrentyCheckDetails warrentyCheckDetails) {
                                                               System.out.println("WarrantyCheckFragment.getWarrantyDelegateSuccess");
                                                               try {
                                                                   System.out.println("warrentyCheckDetails = [" + warrentyCheckDetails.getEnd_date() + "]");
                                                                   System.out.println("warrentyCheckDetails = [" + warrentyCheckDetails.getStart_date() + "]");
                                                                   System.out.println("warrentyCheckDetails = [" + warrentyCheckDetails.getImei1() + "]");
                                                                   System.out.println("warrentyCheckDetails = [" + warrentyCheckDetails.getStatus() + "]");
                                                                   System.out.println("warrentyCheckDetails = [" + warrentyCheckDetails.getError() + "]");


                                                               } catch (Exception ex) {
                                                                   ex.printStackTrace();
                                                               }

                                                               ScreenUtils.dismissLoader();

                                                               if (warrentyCheckDetails != null &&
                                                                       warrentyCheckDetails.getStart_date() != null &&
                                                                       warrentyCheckDetails.getEnd_date() != null &&
                                                                       warrentyCheckDetails.getEnd_date().length() > 0 &&
                                                                       warrentyCheckDetails.getStart_date().length() > 0) {


                                                                   RajaCacheUtils.cacheThisWarrantyCheckData(getActivity(), warrentyCheckDetails);
                                                                   checkCachedData();
                                                               } else {
                                                                   System.out.println("WarrantyCheckFragment.activateDeviceDelegateSuccess: here");
                                                                   if (warrentyCheckDetails != null &&
                                                                           warrentyCheckDetails.getError() != null) {

                                                                       showErrorMessage(getString(R.string.sorry), warrentyCheckDetails.getError());
                                                                   } else {
                                                                       showErrorMessage(getString(R.string.sorry), getString(R.string.connectionError));

                                                                   }
                                                               }

                                                           }

                                                           @Override
                                                           public void activateDeviceDelegateFailure(String error) {
                                                               System.out.println("WarrantyCheckFragment.getWarrantyDelegateFailure");
                                                               ScreenUtils.dismissLoader();
                                                               detailsView.setVisibility(View.GONE);
                                                               showErrorMessage(getString(R.string.sorry), error);

                                                           }

                                                           @Override
                                                           public void activateDeviceDelegateParameterError() {
                                                               detailsView.setVisibility(View.GONE);
                                                               System.out.println("WarrantyCheckFragment.getWarrantyConnectionErrorDelegate");
                                                               ScreenUtils.dismissLoader();
                                                               showErrorMessage(getString(R.string.sorry), getString(R.string.connectionError));
//                                                               Toast.makeText(getActivity(), "Make sure you entered IMEI code.", Toast.LENGTH_LONG).show();
                                                           }

                                                           @Override
                                                           public void activateDeviceConnectionErrorDelegate() {
                                                               detailsView.setVisibility(View.GONE);
                                                               System.out.println("WarrantyCheckFragment.getWarrantyConnectionErrorDelegate");
                                                               ScreenUtils.dismissLoader();

                                                               showErrorMessage(getString(R.string.sorry), getString(R.string.connectionError));
                                                           }
                                                       }, warrantyEditText.getText().toString().trim(),
                            mobileEditText.getText().toString(),
                            getActivity());
                } else {
//                    if (mobileEditText.getText().toString().trim().length() < 10) {
//                        YoYo.with(Techniques.Shake)
//                                .duration(200)
//                                .playOn(mobileEditText);
//
//                    }

                    if (warrantyEditText.getText().toString().trim().length() < 10) {
                        YoYo.with(Techniques.Shake)
                                .duration(200)
                                .playOn(warrantyEditText);
                    }

                    Toast.makeText(getActivity(), R.string.warrantyError, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showErrorMessage(String title, String error) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());

        dialogBuilder
                .withTitle(title)
                .withMessage(error)
                .withTitleColor("#ffffff")           //def
                .withDividerColor("#11000000")       //def
                .withMessageColor("#666666")         //def  | withMessageColor(int resid)
                .withDialogColor("#EA9799")          //def  | withDialogColor(int resid) ContextCompat.getColor(getActivity(), R.color.app_red_color)
                .withDuration(400)                                          //def
                .withEffect(Effectstype.RotateBottom)                                         //def Effectstype.Slidetop
                .withButton1Text(getString(R.string.ok))                                      //def gone
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_warrenty_check, container, false);
        findViews(inflatedView);
        getDeviceIMEI();
        checkCachedData();
        validateHomeScreen();


        return inflatedView;
    }


    //this method to remove the close (call from home page)
    private void validateHomeScreen() {
        if (removeHeader != null &&
                removeHeader) {
            System.out.println("WarrantyCheckFragment.validateHomeScreen : here to remove the header");

            top_bar_layout.getLayoutParams().height = 0;
            seperator.getLayoutParams().height = 0;

        } else {
            System.out.println("WarrantyCheckFragment.validateHomeScreen : no need to remove the header");
        }
    }

    private void checkCachedData() {
        WarrentyCheckDetails warrentyCheckDetails = RajaCacheUtils.getWarrantyCheckData(getActivity());

        try {

            if (warrentyCheckDetails != null &&
                    warrentyCheckDetails.getStart_date() != null &&
                    warrentyCheckDetails.getEnd_date() != null &&
                    warrentyCheckDetails.getEnd_date().length() > 0 &&
                    warrentyCheckDetails.getStart_date().length() > 0) {

                System.out.println("WarrantyCheckFragment.checkCachedData : here to hide the check warrenty items");
                warrantyEditText.getLayoutParams().height = 0;
                check.getLayoutParams().height = 0;
                mobileEditText.getLayoutParams().height = 0;
                enterYour.getLayoutParams().height = 0;

                detailsView.setVisibility(View.VISIBLE);
                if (warrentyCheckDetails.getImei1() != null) {
                    title.setText(getString(R.string.imeiString) + "  " + warrentyCheckDetails.getImei1());
                } else {
                    title.setText("");
                    title.setVisibility(View.GONE);
                }

                if (warrentyCheckDetails.getStatus().equalsIgnoreCase("0")) {
                    valid.setText(R.string.valid1);
                } else {
                    valid.setText(getString(R.string.validity) + "  " + warrentyCheckDetails.getStatus());
                }

                if (warrentyCheckDetails.getEnd_date() != null) {
//                    Toast.makeText(getActivity(), warrentyCheckDetails.getEnd_date(), Toast.LENGTH_SHORT).show();

                    try {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                        calendar.setTime(simpleDateFormatter.parse(warrentyCheckDetails.getEnd_date()));

                        endDate.setText(String.format("%s  %s", getString(R.string.endDate), simpleDateFormatter.format(calendar.getTime())));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Toast.makeText(getActivity(), "Exception happened here: " + ex.toString(), Toast.LENGTH_SHORT).show();
//                        endDate.setText(String.format("%s  %s", getString(R.string.endDate), warrentyCheckDetails.getEnd_date()));
                        endDate.setText(String.format("%s  %s", getString(R.string.endDate), "Error"));
                    }

                } else {
                    endDate.setText("");
                    endDate.setVisibility(View.GONE);
                }


                if (warrentyCheckDetails.getStart_date() != null) {
//                    try {
//                        Calendar calendar = Calendar.getInstance();
//                        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//                        calendar.setTime(simpleDateFormatter.parse(warrentyCheckDetails.getStart_date()));
//
//                        startDate.setText(String.format("%s  %s", getString(R.string.startDate), simpleDateFormatter.format(calendar.getTime())));
//
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
                    startDate.setText(String.format("%s  %s", getString(R.string.startDate), warrentyCheckDetails.getStart_date().replace("\\", "")));
//                    }

                } else {
                    startDate.setText("");
                    startDate.setVisibility(View.GONE);
                }
                if (warrentyCheckDetails.getNotes() != null &&
                        !warrentyCheckDetails.getNotes().trim().equalsIgnoreCase("null") &&
                        warrentyCheckDetails.getNotes().trim().length() != 0) {
                    note.setText(getString(R.string.noteString) + "  " + warrentyCheckDetails.getNotes());
                } else {
                    note.setText("");
                    note.setVisibility(View.GONE);
                }

            } else {
                System.out.println("WarrantyCheckFragment.checkCachedData : the data is missed, do nothing here");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void getDeviceIMEI() {
        try {
            CheckPermissionUtils.handleStatusPermission(((BaseActivity) getActivity()), new CheckPermissionUtils.PermessionResult() {
                @Override
                public void PermissionAllowed() {
                    System.out.println("WarrantyCheckFragment.PermissionDenient : here");
                    TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    String IMEI_number = telephonyManager.getDeviceId();


                    System.out.println("WarrantyCheckFragment.getDeviceIMEI: the IMEI = " + IMEI_number);
                    warrantyEditText.setText(IMEI_number);
                }

                @Override
                public void PermissionDenient() {
                    System.out.println("WarrantyCheckFragment.PermissionDenient : here");
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
