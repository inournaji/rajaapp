package com.rajateck.wael.raja.customViews.branchedPackage;

/**
 * Created by wael on 11/17/17 at raja-tec.
 * at November
 */

public class BranchesPopUpForm {
    String title;
    String bodyMessage;
    String positiveButton;
    String negativeButton;
    BranchesPopUpInterface informationPopUpInterface;

    public BranchesPopUpForm(String title, String bodyMessage, String positiveButton, String negativeButton, BranchesPopUpInterface informationPopUpInterface) {
        this.title = title;
        this.bodyMessage = bodyMessage;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.informationPopUpInterface = informationPopUpInterface;
    }

    public BranchesPopUpForm(String title, String bodyMessage, String positiveButton, String negativeButton) {

        this.title = title;
        this.bodyMessage = bodyMessage;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
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
