package com.example.runner.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderToDeliver {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("invoice_no")
    @Expose
    private String invoiceNo;
    @SerializedName("tax_amount")
    @Expose
    private Double taxAmount;
    @SerializedName("total_amount")
    @Expose
    private Double totalAmount;
    @SerializedName("pre_tax_amount")
    @Expose
    private Double preTaxAmount;
    @SerializedName("invoice_id")
    @Expose
    private Integer invoiceId;
    @SerializedName("consumer_name")
    @Expose
    private String consumerName;
    @SerializedName("invoice_date")
    @Expose
    private String invoiceDate;
    @SerializedName("bill_id")
    @Expose
    private Integer billId;
    @SerializedName("consumer_id")
    @Expose
    private Integer consumerId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPreTaxAmount() {
        return preTaxAmount;
    }

    public void setPreTaxAmount(Double preTaxAmount) {
        this.preTaxAmount = preTaxAmount;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }
}
