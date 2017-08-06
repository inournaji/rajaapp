package com.rajateck.wael.raja.controllers.inAppViews.warrantyCheck;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.connection.Connection;
import com.rajateck.wael.raja.controllers.inAppViews.BaseActivity;
import com.rajateck.wael.raja.delegates.CheckWarrantyDelegate;
import com.rajateck.wael.raja.models.WarrentyCheckDetails;
import com.rajateck.wael.raja.utils.CheckPermissionUtils;
import com.rajateck.wael.raja.utils.ScreenUtils;

import fr.arnaudguyon.tabstacker.TabStacker;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.SlideBottom;

public class WarrantyCheckFragment extends Fragment implements TabStacker.TabStackInterface {
    private View inflatedView;
    private RelativeLayout headerRel;
    private ImageView close;
    private View seperator;
    private TextView enterYour;
    private EditText warrantyEditText;
    private Button check;
    private TextView title;
    private TextView valid;
    private TextView endDate;
    private TextView note;
    private RelativeLayout detailsView;

    public WarrantyCheckFragment() {
        // Required empty public constructor
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
        check = (Button) rootView.findViewById(R.id.check);
        seperator = (View) rootView.findViewById(R.id.seperator);
        close = (ImageView) rootView.findViewById(R.id.close);
        title = (TextView) rootView.findViewById(R.id.title);
        valid = (TextView) rootView.findViewById(R.id.valid);
        detailsView = (RelativeLayout) rootView.findViewById(R.id.detailsView);
        endDate = (TextView) rootView.findViewById(R.id.endDate);
        note = (TextView) rootView.findViewById(R.id.note);
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

                    ScreenUtils.showLoader(getActivity());
                    detailsView.setVisibility(View.GONE);
                    System.out.println("WarrantyCheckFragment.onClick");
                    Connection.checkWarrnty(new CheckWarrantyDelegate() {
                        @Override
                        public void getWarrantyDelegateSuccess(WarrentyCheckDetails warrentyCheckDetails) {
                            System.out.println("WarrantyCheckFragment.getWarrantyDelegateSuccess");
                            System.out.println("warrentyCheckDetails = [" + warrentyCheckDetails.getFieldEndDate() + "]");
                            System.out.println("warrentyCheckDetails = [" + warrentyCheckDetails.getFieldNotes() + "]");
                            System.out.println("warrentyCheckDetails = [" + warrentyCheckDetails.getFieldCustomsDeclaration() + "]");
                            System.out.println("warrentyCheckDetails = [" + warrentyCheckDetails.getStatus() + "]");
                            System.out.println("warrentyCheckDetails = [" + warrentyCheckDetails.getTitle() + "]");

                            ScreenUtils.dismissLoader();

                            try {

                                detailsView.setVisibility(View.VISIBLE);
                                if (warrentyCheckDetails.getTitle() != null) {
                                    title.setText("Title: " + warrentyCheckDetails.getTitle());
                                } else {
                                    title.setText("");
                                }


                                if (warrentyCheckDetails.getStatus().equalsIgnoreCase("0")) {
                                    valid.setText("Validity: Not Valid");
                                } else {
                                    valid.setText("Validity: Valid");
                                }

                                if (warrentyCheckDetails.getFieldEndDate() != null) {
                                    endDate.setText("End Date: " + warrentyCheckDetails.getFieldEndDate());
                                } else {
                                    endDate.setText("");
                                }

                                if (warrentyCheckDetails.getFieldNotes() != null &&
                                        warrentyCheckDetails.getFieldNotes().trim().length() != 0) {
                                    note.setText("Note: " + warrentyCheckDetails.getFieldNotes());
                                } else {
                                    note.setText("");
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }

                        @Override
                        public void getWarrantyDelegateFailure(String error) {
                            System.out.println("WarrantyCheckFragment.getWarrantyDelegateFailure");
                            ScreenUtils.dismissLoader();
                            detailsView.setVisibility(View.GONE);
//                            Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
                            final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());

                            dialogBuilder
                                    .withTitle("Sorry!")
                                    .withMessage(error)
                                    .withTitleColor("#000000")                                  //def
                                    .withDividerColor("#11000000")                              //def
                                    .withMessageColor("#666666")                              //def  | withMessageColor(int resid)
                                    .withDialogColor("#ffffff")                               //def  | withDialogColor(int resid)
                                    .withDuration(400)                                          //def
                                    .withEffect(SlideBottom)                                         //def Effectstype.Slidetop
                                    .withButton1Text("OK")                                      //def gone
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
                        public void getWarrantyConnectionErrorDelegate() {
                            detailsView.setVisibility(View.GONE);
                            System.out.println("WarrantyCheckFragment.getWarrantyConnectionErrorDelegate");
                            ScreenUtils.dismissLoader();
//                            Toast.makeText(getActivity(), "Make sure you have internet connection", Toast.LENGTH_LONG).show();
                            final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
                            dialogBuilder
                                    .withTitle("Sorry!")
                                    .withMessage("Make sure you have internet connection")
                                    .withTitleColor("#000000")                                  //def
                                    .withDividerColor("#11000000")                              //def
                                    .withMessageColor("#666666")                              //def  | withMessageColor(int resid)
                                    .withDialogColor("#ffffff")                               //def  | withDialogColor(int resid)
                                    .withDuration(400)                                          //def
                                    .withEffect(SlideBottom)                                         //def Effectstype.Slidetop
                                    .withButton1Text("OK")                                      //def gone
                                    .setButton1Click(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialogBuilder.dismiss();
                                        }
                                    })
                                    .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                                    .show();
                        }
                    }, getActivity(), warrantyEditText.getText().toString().trim());
                } else {
                    Toast.makeText(getActivity(), "Make sure you entered IMEI code.", Toast.LENGTH_LONG).show();
                }
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
        inflatedView = inflater.inflate(R.layout.fragment_warrenty_check, container, false);
        findViews(inflatedView);
        getDeviceIMEI();


        return inflatedView;
    }

    private void getDeviceIMEI() {
        try {
            CheckPermissionUtils.handleStatusPermission(((BaseActivity) getActivity()), new CheckPermissionUtils.PermessionResult() {
                @Override
                public void PermissionAllowed() {
                    System.out.println("WarrantyCheckFragment.PermissionDenient : here");
                    TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
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
