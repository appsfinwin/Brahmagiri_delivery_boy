package com.finwintechnologies.deltracker.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderToDeliver {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("consumer_landmark")
    @Expose
    private String consumerLandmark;
    @SerializedName("invoice_no")
    @Expose
    private String invoiceNo;
    @SerializedName("tax_amount")
    @Expose
    private Double taxAmount;
    @SerializedName("pre_tax_amount")
    @Expose
    private Double preTaxAmount;
    @SerializedName("consumer_addr")
    @Expose
    private String consumerAddr;
    @SerializedName("invoice_id")
    @Expose
    private Integer invoiceId;
    @SerializedName("collecting_option")
    @Expose
    private String collectingOption;
    @SerializedName("invoice_date")
    @Expose
    private String invoiceDate;
    @SerializedName("delivery_charges")
    @Expose
    private Double deliveryCharges;
    @SerializedName("consumer_id")
    @Expose
    private Integer consumerId;
    @SerializedName("consumer_mobile")
    @Expose
    private String consumerMobile;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("consumer_street")
    @Expose
    private String consumerStreet;
    @SerializedName("consumer_name")
    @Expose
    private String consumerName;

    @SerializedName("outlet")
    @Expose
    private String outlet;





    @SerializedName("bill_id")
    @Expose
    private Integer billId;
    @SerializedName("total_amount")
    @Expose
    private Double totalAmount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConsumerLandmark() {
        return consumerLandmark;
    }

    public void setConsumerLandmark(String consumerLandmark) {
        this.consumerLandmark = consumerLandmark;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getPreTaxAmount() {
        return preTaxAmount;
    }

    public void setPreTaxAmount(Double preTaxAmount) {
        this.preTaxAmount = preTaxAmount;
    }

    public String getConsumerAddr() {
        return consumerAddr;
    }

    public void setConsumerAddr(String consumerAddr) {
        this.consumerAddr = consumerAddr;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCollectingOption() {
        return collectingOption;
    }

    public void setCollectingOption(String collectingOption) {
        this.collectingOption = collectingOption;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(Double deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerMobile() {
        return consumerMobile;
    }

    public void setConsumerMobile(String consumerMobile) {
        this.consumerMobile = consumerMobile;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getConsumerStreet() {
        return consumerStreet;
    }

    public void setConsumerStreet(String consumerStreet) {
        this.consumerStreet = consumerStreet;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOutlet() {
        return outlet;
    }

    public void setOutlet(String outlet) {
        this.outlet = outlet;
    }
}
