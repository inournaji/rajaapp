package com.rajateck.wael.raja.connection;

/**
 * Created by wael on 2/8/17.
 */

public enum ResponseCodes {

    Success("200"),
    NotFound("404"),
    Forbidden("403"),
    Failure("500"),
    Invalid("401"),
    BlockedOrDeleted("423"),
    NotActivated("412"),
    ServerViolation("422"),
    RewardRedeemedBefore("409"),
    NotEnoughPoints("412");


    private String code;

    ResponseCodes(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

}
