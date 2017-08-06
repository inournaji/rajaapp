package com.rajateck.wael.raja.connection;

/**
 * Created by MhannaCloudAppers on 10/11/2016.
 */

public enum ErrorMessages {
    InvalidForm("Invalid form data"),
    SubmitFeedbackFormErrorMessage("Please write something in the comment field"),
    SubmitFeedbackFormNoErrorMessageFromServer("Not able to submit your feedback"),
    ConnectionError("There was a connection error, please check your internet connection then try again"),
    InvalidCredentials("Your request was made with invalid credentials");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
