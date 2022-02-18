package com.elifeindia.crm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerData {

    @SerializedName("customer_ID")
    @Expose
    private Integer customerID;
    @SerializedName("account_No")
    @Expose
    private Integer accountNo;
    @SerializedName("subscriber_ID")
    @Expose
    private String subscriberID;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("soDoWo")
    @Expose
    private String soDoWo;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("contact_No")
    @Expose
    private String contactNo;
    @SerializedName("whatsup_No")
    @Expose
    private String whatsupNo;
    @SerializedName("area_ID")
    @Expose
    private Object areaID;
    @SerializedName("areaName")
    @Expose
    private String areaName;
    @SerializedName("area_Customer_ID")
    @Expose
    private String areaCustomerID;
    @SerializedName("subarea_ID")
    @Expose
    private Object subareaID;
    @SerializedName("subareaName")
    @Expose
    private String subareaName;
    @SerializedName("connection_Type_ID")
    @Expose
    private Object connectionTypeID;
    @SerializedName("is_Auto_Renew")
    @Expose
    private Object isAutoRenew;
    @SerializedName("is_Bill_Generated")
    @Expose
    private Object isBillGenerated;
    @SerializedName("paid_Amount")
    @Expose
    private String paidAmount;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("status_Name")
    @Expose
    private Object statusName;
    @SerializedName("payment_Date")
    @Expose
    private String paymentDate;
    @SerializedName("invoice_Type")
    @Expose
    private String invoiceType;
    @SerializedName("invoice_Number")
    @Expose
    private Object invoiceNumber;
    @SerializedName("payment_Status")
    @Expose
    private Object paymentStatus;
    @SerializedName("triple_play")
    @Expose
    private Object triplePlay;
    @SerializedName("triple_play_ID")
    @Expose
    private Object triplePlayID;
    @SerializedName("invoice_ID")
    @Expose
    private Object invoiceID;

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }

    public String getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(String subscriberID) {
        this.subscriberID = subscriberID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoDoWo() {
        return soDoWo;
    }

    public void setSoDoWo(String soDoWo) {
        this.soDoWo = soDoWo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getWhatsupNo() {
        return whatsupNo;
    }

    public void setWhatsupNo(String whatsupNo) {
        this.whatsupNo = whatsupNo;
    }

    public Object getAreaID() {
        return areaID;
    }

    public void setAreaID(Object areaID) {
        this.areaID = areaID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCustomerID() {
        return areaCustomerID;
    }

    public void setAreaCustomerID(String areaCustomerID) {
        this.areaCustomerID = areaCustomerID;
    }

    public Object getSubareaID() {
        return subareaID;
    }

    public void setSubareaID(Object subareaID) {
        this.subareaID = subareaID;
    }

    public String getSubareaName() {
        return subareaName;
    }

    public void setSubareaName(String subareaName) {
        this.subareaName = subareaName;
    }

    public Object getConnectionTypeID() {
        return connectionTypeID;
    }

    public void setConnectionTypeID(Object connectionTypeID) {
        this.connectionTypeID = connectionTypeID;
    }

    public Object getIsAutoRenew() {
        return isAutoRenew;
    }

    public void setIsAutoRenew(Object isAutoRenew) {
        this.isAutoRenew = isAutoRenew;
    }

    public Object getIsBillGenerated() {
        return isBillGenerated;
    }

    public void setIsBillGenerated(Object isBillGenerated) {
        this.isBillGenerated = isBillGenerated;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Object getStatusName() {
        return statusName;
    }

    public void setStatusName(Object statusName) {
        this.statusName = statusName;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Object getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Object invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Object getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Object paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Object getTriplePlay() {
        return triplePlay;
    }

    public void setTriplePlay(Object triplePlay) {
        this.triplePlay = triplePlay;
    }

    public Object getTriplePlayID() {
        return triplePlayID;
    }

    public void setTriplePlayID(Object triplePlayID) {
        this.triplePlayID = triplePlayID;
    }

    public Object getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Object invoiceID) {
        this.invoiceID = invoiceID;
    }

}
