package com.finwintechnologies.deltracker.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFetchOrder {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Order_to_deliver")
    @Expose
    private List<OrderToDeliver> orderToDeliver = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderToDeliver> getOrderToDeliver() {
        return orderToDeliver;
    }

    public void setOrderToDeliver(List<OrderToDeliver> orderToDeliver) {
        this.orderToDeliver = orderToDeliver;
    }
}
