package com.elifeindia.crm.networking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InternetBoxUpdateSubscription {

    @SerializedName("user_Id")
    @Expose
    private Integer userId;
    @SerializedName("customer_Id")
    @Expose
    private Integer customerId;
    @SerializedName("internet_Box_ID")
    @Expose
    private Integer internetBoxID;
    @SerializedName("box_ID")
    @Expose
    private Integer boxID;
    @SerializedName("boxType_ID")
    @Expose
    private Integer boxTypeID;
    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("mac")
    @Expose
    private String mac;
    @SerializedName("bill_Type_ID")
    @Expose
    private Integer billTypeID;
    @SerializedName("connection_Status_ID")
    @Expose
    private Integer connectionStatusID;
    @SerializedName("activation_Date")
    @Expose
    private String activationDate;
    @SerializedName("expiry_Date")
    @Expose
    private String expiryDate;
    @SerializedName("noofMonth")
    @Expose
    private Integer noofMonth;
    @SerializedName("noofDays")
    @Expose
    private Integer noofDays;
    @SerializedName("package_Amount")
    @Expose
    private Double packageAmount;
    @SerializedName("tax_Amount")
    @Expose
    private Double taxAmount;
    @SerializedName("box_Amount")
    @Expose
    private Double boxAmount;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("invoice_ID")
    @Expose
    private Integer invoiceID;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getInternetBoxID() {
        return internetBoxID;
    }

    public void setInternetBoxID(Integer internetBoxID) {
        this.internetBoxID = internetBoxID;
    }

    public Integer getBoxID() {
        return boxID;
    }

    public void setBoxID(Integer boxID) {
        this.boxID = boxID;
    }

    public Integer getBoxTypeID() {
        return boxTypeID;
    }

    public void setBoxTypeID(Integer boxTypeID) {
        this.boxTypeID = boxTypeID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getBillTypeID() {
        return billTypeID;
    }

    public void setBillTypeID(Integer billTypeID) {
        this.billTypeID = billTypeID;
    }

    public Integer getConnectionStatusID() {
        return connectionStatusID;
    }

    public void setConnectionStatusID(Integer connectionStatusID) {
        this.connectionStatusID = connectionStatusID;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getNoofMonth() {
        return noofMonth;
    }

    public void setNoofMonth(Integer noofMonth) {
        this.noofMonth = noofMonth;
    }

    public Integer getNoofDays() {
        return noofDays;
    }

    public void setNoofDays(Integer noofDays) {
        this.noofDays = noofDays;
    }

    public Double getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(Double packageAmount) {
        this.packageAmount = packageAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getBoxAmount() {
        return boxAmount;
    }

    public void setBoxAmount(Double boxAmount) {
        this.boxAmount = boxAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }
}
