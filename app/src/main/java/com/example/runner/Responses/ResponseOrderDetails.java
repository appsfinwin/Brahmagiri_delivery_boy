package com.example.runner.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseOrderDetails {
    @SerializedName("status")
    @Expose
    private String status;
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
    private Boolean invoiceNumber;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("subtotal")
    @Expose
    private Double subtotal;

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

    public Boolean getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Boolean invoiceNumber) {
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
}
