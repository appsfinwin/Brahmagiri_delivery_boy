package com.finwintechnologies.runner.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ledger {
    @SerializedName("credit")
    @Expose
    private Double credit;
    @SerializedName("cl_balance")
    @Expose
    private Double clBalance;
    @SerializedName("outlet")
    @Expose
    private String outlet;
    @SerializedName("op_balance")
    @Expose
    private Double opBalance;
    @SerializedName("debit")
    @Expose
    private Double debit;

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getClBalance() {
        return clBalance;
    }

    public void setClBalance(Double clBalance) {
        this.clBalance = clBalance;
    }

    public String getOutlet() {
        return outlet;
    }

    public void setOutlet(String outlet) {
        this.outlet = outlet;
    }

    public Double getOpBalance() {
        return opBalance;
    }

    public void setOpBalance(Double opBalance) {
        this.opBalance = opBalance;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }
}
