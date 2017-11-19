package com.rajateck.wael.raja.customViews.branchedPackage;

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

public class BranchesPopup extends Dialog implements View.OnClickListener {
    private Context context;
    private BranchesPopUpForm branchesPopUpForm;

    public BranchesPopup(Context context) {
        super(context);
        init();
    }

    public BranchesPopup(Context context, BranchesPopUpForm branchesPopUpForm) {
        super(context);
        this.context = context;
        this.branchesPopUpForm = branchesPopUpForm;
        init();
    }

    public static BranchesPopup newInstance(Context context) {
        BranchesPopup congratulationsPopup = new BranchesPopup(context);
        return congratulationsPopup;
    }

    public static BranchesPopup newInstance(Context context, BranchesPopUpForm branchesPopUpForm) {
        BranchesPopup congratulationsPopup = new BranchesPopup(context, branchesPopUpForm);
        return congratulationsPopup;
    }

    public BranchesPopUpForm getbranchesPopUpForm() {
        return branchesPopUpForm;
    }
    private RelativeLayout dialogContent;
    private TextView branchesTitleTextview;
    private View seperator;
    private RelativeLayout popupSecondSection;
    private TextView branchesDetailsTextview;
    private View sep;
    private TextView positiveTextview;
    private TextView negativeTextview;
    
    private void findViews() {
        dialogContent = (RelativeLayout)findViewById( R.id.dialog_content );
        branchesTitleTextview = (TextView)findViewById( R.id.branches_title_textview );
        seperator = (View)findViewById( R.id.seperator );
        popupSecondSection = (RelativeLayout)findViewById( R.id.popup_second_section );
        branchesDetailsTextview = (TextView)findViewById( R.id.branches_details_textview );
        
        sep = (View)findViewById( R.id.sep );
        positiveTextview = (TextView)findViewById( R.id.positive_textview );
        negativeTextview = (TextView)findViewById( R.id.negative_textview );
    }


    private void init() {

        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setBackgroundDrawableResource(R.drawable.dialog_white_rounded_background);
        this.getWindow().setBackgroundDrawableResource(R.drawable.dialog_white_rounded_background2);

        this.setContentView(R.layout.branches_popup_layout);
        findViews();
        validateScreenData();

        this.show();
    }

    private void validateScreenData() {

        if (branchesPopUpForm != null) {
            String title = branchesPopUpForm.getTitle();
            String body = branchesPopUpForm.getBodyMessage();
            String positiveStringIfEnabled = branchesPopUpForm.getPositiveButton();
            String negativeStringIfEnabled = branchesPopUpForm.getNegativeButton();

            if (title == null) {
                title = context.getString(R.string.sorry);
            }

            if (body == null) {
                body = context.getString(R.string.connectionError);
            }

            if (positiveStringIfEnabled == null) {
                positiveTextview.setVisibility(View.GONE);
            } else {
                positiveTextview.setVisibility(View.VISIBLE);
                positiveTextview.setText(positiveStringIfEnabled);
                positiveTextview.setOnClickListener(this);
            }

            if (negativeStringIfEnabled == null) {
                negativeTextview.setVisibility(View.VISIBLE);
                if (positiveTextview.getVisibility() == View.VISIBLE) {
                    negativeTextview.setVisibility(View.GONE);
                } else {
                    negativeTextview.setText(context.getString(R.string.ok));
                }
            } else {
                negativeTextview.setVisibility(View.VISIBLE);
                negativeTextview.setText(negativeStringIfEnabled);
                negativeTextview.setOnClickListener(this);
            }

            branchesTitleTextview.setText(title);
            branchesDetailsTextview.setText(body);

        } else {

            // here to load all the default values
            branchesTitleTextview.setText(context.getString(R.string.sorry));
            branchesDetailsTextview.setText(context.getString(R.string.connectionError));
            positiveTextview.setVisibility(View.GONE);
            negativeTextview.setVisibility(View.VISIBLE);
            negativeTextview.setText(context.getString(R.string.ok));
        }

    }


    @Override
    public void onClick(View view) {
        if (view == negativeTextview) {
            implementNegativeProcess();
        } else if (view == positiveTextview) {
            implementPositiveProcess();
        }
    }

    private void implementPositiveProcess() {
        if (branchesPopUpForm != null &&
                branchesPopUpForm.getInformationPopUpInterface() != null) {
            branchesPopUpForm.getInformationPopUpInterface().onPositiveButtonClicked();
        }
        BranchesPopup.this.dismiss();
    }

    private void implementNegativeProcess() {
        if (branchesPopUpForm != null &&
                branchesPopUpForm.getInformationPopUpInterface() != null) {
            branchesPopUpForm.getInformationPopUpInterface().onNegativeButtonClicked();
        }
        BranchesPopup.this.dismiss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (branchesPopUpForm != null &&
                branchesPopUpForm.getInformationPopUpInterface() != null) {
            branchesPopUpForm.getInformationPopUpInterface().onDismiss();
        }
    }
}
