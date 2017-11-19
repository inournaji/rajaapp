package com.rajateck.wael.raja.customViews.informationpackage;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rajateck.wael.raja.R;


/**
 * Created by wael on 2/4/17.
 */

public class InformationPopup extends Dialog implements View.OnClickListener {
    private RelativeLayout dialogContent;
    private TextView informationTitleTextView;
    private View seperator;
    private RelativeLayout popupSecondSection;
    private TextView informationDetailsTextView;
    private TextView negativeButton;
    private TextView positiveButton;
    private Context context;
    private InformationPopUpForm informationPopUpForm;

    public InformationPopup(Context context) {
        super(context);
        init();
    }

    public InformationPopup(Context context, InformationPopUpForm informationPopUpForm) {
        super(context);
        this.context = context;
        this.informationPopUpForm = informationPopUpForm;
        init();
    }

    public static InformationPopup newInstance(Context context) {
        InformationPopup congratulationsPopup = new InformationPopup(context);
        return congratulationsPopup;
    }

    public static InformationPopup newInstance(Context context, InformationPopUpForm informationPopUpForm) {
        InformationPopup congratulationsPopup = new InformationPopup(context, informationPopUpForm);
        return congratulationsPopup;
    }

    public InformationPopUpForm getInformationPopUpForm() {
        return informationPopUpForm;
    }

    private void findViews() {
        dialogContent = (RelativeLayout) findViewById(R.id.dialog_content);
        informationTitleTextView = (TextView) findViewById(R.id.information_title_textview);
        seperator = findViewById(R.id.seperator);
        popupSecondSection = (RelativeLayout) findViewById(R.id.popup_second_section);
        informationDetailsTextView = (TextView) findViewById(R.id.information_details_textview);
        negativeButton = (TextView) findViewById(R.id.negative_textview);
        positiveButton = (TextView) findViewById(R.id.positive_textview);

        negativeButton.setOnClickListener(this);
        positiveButton.setOnClickListener(this);
    }

    private void init() {

        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(R.drawable.dialog_white_rounded_background);

        this.setContentView(R.layout.information_popup_layout);
        findViews();
        validateScreenData();

        this.show();
    }

    private void validateScreenData() {

        if (informationPopUpForm != null) {
            String title = informationPopUpForm.getTitle();
            String body = informationPopUpForm.getBodyMessage();
            String positiveStringIfEnabled = informationPopUpForm.getPositiveButton();
            String negativeStringIfEnabled = informationPopUpForm.getNegativeButton();

            if (title == null) {
                title = context.getString(R.string.sorry);
            }

            if (body == null) {
                body = context.getString(R.string.connectionError);
            }

            if (positiveStringIfEnabled == null) {
                positiveButton.setVisibility(View.GONE);
            } else {
                positiveButton.setVisibility(View.VISIBLE);
                positiveButton.setText(positiveStringIfEnabled);
                positiveButton.setOnClickListener(this);
            }

            if (negativeStringIfEnabled == null) {
                negativeButton.setVisibility(View.VISIBLE);
                if (positiveButton.getVisibility() == View.VISIBLE) {
                    negativeButton.setVisibility(View.GONE);
                } else {
                    negativeButton.setText(context.getString(R.string.ok));
                }
            } else {
                negativeButton.setVisibility(View.VISIBLE);
                negativeButton.setText(negativeStringIfEnabled);
                negativeButton.setOnClickListener(this);
            }

            informationTitleTextView.setText(title);
            informationDetailsTextView.setText(body);

        } else {

            // here to load all the default values
            informationTitleTextView.setText(context.getString(R.string.sorry));
            informationDetailsTextView.setText(context.getString(R.string.connectionError));
            positiveButton.setVisibility(View.GONE);
            negativeButton.setVisibility(View.VISIBLE);
            negativeButton.setText(context.getString(R.string.ok));
        }

    }


    @Override
    public void onClick(View view) {
        if (view == negativeButton) {
            implementNegativeProcess();
        } else if (view == positiveButton) {
            implementPositiveProcess();
        }
    }

    private void implementPositiveProcess() {
        if (informationPopUpForm != null &&
                informationPopUpForm.getInformationPopUpInterface() != null) {
            informationPopUpForm.getInformationPopUpInterface().onPositiveButtonClicked();
        }
        InformationPopup.this.dismiss();
    }

    private void implementNegativeProcess() {
        if (informationPopUpForm != null &&
                informationPopUpForm.getInformationPopUpInterface() != null) {
            informationPopUpForm.getInformationPopUpInterface().onNegativeButtonClicked();
        }
        InformationPopup.this.dismiss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (informationPopUpForm != null &&
                informationPopUpForm.getInformationPopUpInterface() != null) {
            informationPopUpForm.getInformationPopUpInterface().onDismiss();
        }
    }
}
