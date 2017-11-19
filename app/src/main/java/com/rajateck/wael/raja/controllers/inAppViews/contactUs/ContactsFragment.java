package com.rajateck.wael.raja.controllers.inAppViews.contactUs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.connection.Connection;
import com.rajateck.wael.raja.controllers.inAppViews.BaseActivity;
import com.rajateck.wael.raja.delegates.generalDelegates.ContactUsDelegate;
import com.rajateck.wael.raja.enums.FragmentTags;
import com.rajateck.wael.raja.models.ContactNode;
import com.rajateck.wael.raja.utils.ScreenUtils;

import fr.arnaudguyon.tabstacker.TabStacker;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.SlideBottom;

public class ContactsFragment extends Fragment implements TabStacker.TabStackInterface, View.OnClickListener {
    private View inflatedView;
    private RelativeLayout headerRel;
    private RelativeLayout topBarLayout;
    private RelativeLayout menuIconContainer;
    private RelativeLayout searchIconContainer;
    private ImageView close;
    private RelativeLayout titleLayout;
    private TextView enterYour;
    private EditText nameEditText;
    private TextView enterYourMobile;
    private EditText mobileEditText;
    private TextView enterYourMessage;
    private EditText messageEditText;
    private Button submit;

    public ContactsFragment() {
        // Required empty public constructor
    }

    public ContactsFragment(FragmentTags fragmentTags) {

    }

    public static ContactsFragment newInstance(String param1, String param2, FragmentTags fragmentTag) {
        ContactsFragment fragment = new ContactsFragment(fragmentTag);
        return fragment;
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-06-06 22:14:37 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View rootView) {
        headerRel = (RelativeLayout) rootView.findViewById(R.id.header_rel);
        topBarLayout = (RelativeLayout) rootView.findViewById(R.id.top_bar_layout);
        menuIconContainer = (RelativeLayout) rootView.findViewById(R.id.menu_icon_container);
        searchIconContainer = (RelativeLayout) rootView.findViewById(R.id.search_icon_container);
        close = (ImageView) rootView.findViewById(R.id.close);
        titleLayout = (RelativeLayout) rootView.findViewById(R.id.title_layout);
        enterYour = (TextView) rootView.findViewById(R.id.enter_your_);
        nameEditText = (EditText) rootView.findViewById(R.id.name_edit_text);
        enterYourMobile = (TextView) rootView.findViewById(R.id.enter_your_mobile);
        mobileEditText = (EditText) rootView.findViewById(R.id.mobile_edit_text);
        enterYourMessage = (TextView) rootView.findViewById(R.id.enter_your_message);
        messageEditText = (EditText) rootView.findViewById(R.id.message_edit_text);
        submit = (Button) rootView.findViewById(R.id.submit);

        submit.setOnClickListener(this);

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
    public void onClick(View v) {
        if (v == submit) {
            messageEditText.setError(null);
            nameEditText.setError(null);
            mobileEditText.setError(null);
            if (messageEditText.getText().toString().length() < 10 ||
                    nameEditText.getText().toString().length() == 0 ||
                    mobileEditText.getText().toString().length() < 10) {
                System.out.println("ContactsFragment.onClick: invalid input");

                if (messageEditText.getText().toString().length() < 10) {
                    messageEditText.setError(getString(R.string.cannotBeLessThan10));
                }

                if (nameEditText.getText().toString().length() == 0) {
                    nameEditText.setError(getString(R.string.cannotBeEmpty));
                }

                if (mobileEditText.getText().toString().length() < 10) {
                    mobileEditText.setError(getString(R.string.cannotBeLessThan10));
                }
            } else {
                System.out.println("ContactsFragment.onClick: call the procedure");
                ScreenUtils.showLoader(getActivity());
                Connection.submitContactUs(new ContactUsDelegate() {
                                               @Override
                                               public void submitContactUsDelegateSuccess(ContactNode contactNode) {
                                                   ScreenUtils.dismissLoader();

                                                   final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());

                                                   dialogBuilder
                                                           .withTitle(getString(R.string.thanks))
                                                           .withMessage(getString(R.string.feedback_successfully))
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
                                                                   getActivity().onBackPressed();
                                                               }
                                                           })
                                                           .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                                                           .show();

                                                   nameEditText.setText("");
                                                   mobileEditText.setText("");
                                                   messageEditText.setText("");
                                               }

                                               @Override
                                               public void submitContactUsDelegateFailure(String error) {
                                                   ScreenUtils.dismissLoader();
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
                                               public void submitContactUsConnectionErrorDelegate() {
                                                   ScreenUtils.dismissLoader();
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
                                           }, getActivity(),
                        nameEditText.getText().toString(),
                        messageEditText.getText().toString(),
                        mobileEditText.getText().toString());
            }
            // Handle clicks for submit
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_contact, container, false);
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
