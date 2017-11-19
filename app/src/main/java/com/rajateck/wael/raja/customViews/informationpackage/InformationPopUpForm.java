package com.rajateck.wael.raja.customViews.informationpackage;

/**
 * Created by wael on 11/17/17 at raja-tec.
 * at November
 */

public class InformationPopUpForm {
    String title;
    String bodyMessage;
    String positiveButton;
    String negativeButton;
    InformationPopUpInterface informationPopUpInterface;

    public InformationPopUpForm(String title, String bodyMessage, String positiveButton, String negativeButton, InformationPopUpInterface informationPopUpInterface) {
        this.title = title;
        this.bodyMessage = bodyMessage;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.informationPopUpInterface = informationPopUpInterface;
    }

    public InformationPopUpForm(String title, String bodyMessage, String positiveButton, String negativeButton) {

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

    public InformationPopUpInterface getInformationPopUpInterface() {
        return informationPopUpInterface;
    }

    public void setInformationPopUpInterface(InformationPopUpInterface informationPopUpInterface) {
        this.informationPopUpInterface = informationPopUpInterface;
    }

}
