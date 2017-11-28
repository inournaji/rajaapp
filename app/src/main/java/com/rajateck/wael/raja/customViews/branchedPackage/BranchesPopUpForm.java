package com.rajateck.wael.raja.customViews.branchedPackage;

/**
 * Created by wael on 11/17/17 at raja-tec.
 * at November
 */

public class BranchesPopUpForm {
    private String title;
    private String bodyMessage;
    private String positiveButton;
    private Boolean callEnable;
    private String phoneNumber;
    private String negativeButton;
    private BranchesPopUpInterface informationPopUpInterface;

    public BranchesPopUpForm(String title, String bodyMessage, String positiveButton, String negativeButton, Boolean callEnable, BranchesPopUpInterface informationPopUpInterface) {
        this.title = title;
        this.bodyMessage = bodyMessage;
        this.positiveButton = positiveButton;
        this.callEnable = callEnable;
        this.negativeButton = negativeButton;
        this.informationPopUpInterface = informationPopUpInterface;
    }
    public BranchesPopUpForm(String title, String bodyMessage, String positiveButton, String negativeButton) {

        this.title = title;
        this.bodyMessage = bodyMessage;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getCallEnable() {
        return callEnable;
    }

    public void setCallEnable(Boolean callEnable) {
        this.callEnable = callEnable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(String bodyMessage) {
        this.bodyMessage = bodyMessage;
    }

    public String getPositiveButton() {
        return positiveButton;
    }

    public void setPositiveButton(String positiveButton) {
        this.positiveButton = positiveButton;
    }

    public String getNegativeButton() {
        return negativeButton;
    }

    public void setNegativeButton(String negativeButton) {
        this.negativeButton = negativeButton;
    }

    public BranchesPopUpInterface getInformationPopUpInterface() {
        return informationPopUpInterface;
    }

    public void setInformationPopUpInterface(BranchesPopUpInterface informationPopUpInterface) {
        this.informationPopUpInterface = informationPopUpInterface;
    }

}
