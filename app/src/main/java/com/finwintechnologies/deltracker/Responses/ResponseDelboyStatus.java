package com.finwintechnologies.deltracker.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDelboyStatus {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("current_status")
    @Expose
    private Boolean currentStatus;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Boolean getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Boolean currentStatus) {
        this.currentStatus = currentStatus;
    }
}
