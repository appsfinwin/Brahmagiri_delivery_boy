package com.finwintechnologies.deltracker.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseOrderDetails {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("bill_status")
    @Expose
    private String bill_status;


    @SerializedName("line_items")
    @Expose
    private List<LineItem> lineItems = null;
    @SerializedName("tax_amount")
    @Expose
    private Double taxAmount;
    @SerializedName("total_amount")
    @Expose
    private Double totalAmount;
    @SerializedName("Invoice_Number")
    @Expose
    private String invoiceNumber;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("subtotal")
    @Expose
    private Double subtotal;
    @SerializedName("payment_mode")
    @Expose
    private String  payment_mode;

    @SerializedName("delivery_charges")
    @Expose
    private Double  delivery_charges;
    @SerializedName("consumer_latiitude")
    @Expose
    private String  consumer_latiitude;

    @SerializedName("consumer_longitude")
    @Expose
    private String  consumer_longitude;




    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public Double getDelivery_charges() {
        return delivery_charges;
    }

    public void setDelivery_charges(Double delivery_charges) {
        this.delivery_charges = delivery_charges;
    }

    public String getBill_status() {
        return bill_status;
    }

    public void setBill_status(String bill_status) {
        this.bill_status = bill_status;
    }


    public String getConsumer_latiitude() {
        return consumer_latiitude;
    }

    public void setConsumer_latiitude(String consumer_latiitude) {
        this.consumer_latiitude = consumer_latiitude;
    }

    public String getConsumer_longitude() {
        return consumer_longitude;
    }

    public void setConsumer_longitude(String consumer_longitude) {
        this.consumer_longitude = consumer_longitude;
    }
}
