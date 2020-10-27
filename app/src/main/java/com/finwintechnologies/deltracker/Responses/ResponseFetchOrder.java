package com.finwintechnologies.deltracker.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFetchOrder {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("total_page")
    @Expose
    private Integer totalPage;
    @SerializedName("Order_to_deliver")
    @Expose
    private List<OrderToDeliver> orderToDeliver = null;
    @SerializedName("no_of_orders")
    @Expose
    private Integer noOfOrders;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<OrderToDeliver> getOrderToDeliver() {
        return orderToDeliver;
    }

    public void setOrderToDeliver(List<OrderToDeliver> orderToDeliver) {
        this.orderToDeliver = orderToDeliver;
    }

    public Integer getNoOfOrders() {
        return noOfOrders;
    }

    public void setNoOfOrders(Integer noOfOrders) {
        this.noOfOrders = noOfOrders;
    }

}