package com.elifeindia.crm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerInvoice {

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
    @SerializedName("areaName")
    @Expose
    private String areaName;
    @SerializedName("area_Customer_ID")
    @Expose
    private String areaCustomerID;
    @SerializedName("triple_play_ID")
    @Expose
    private Integer triplePlayID;
    @SerializedName("invoice_ID")
    @Expose
    private Integer invoiceID;
    @SerializedName("invoice_Number")
    @Expose
    private String invoiceNumber;
    @SerializedName("invoice_Date")
    @Expose
    private String invoiceDate;
    @SerializedName("amount")
    @Expose
    private Float amount;
    @SerializedName("discount")
    @Expose
    private Float discount;
    @SerializedName("previous_Balance")
    @Expose
    private Object previousBalance;
    @SerializedName("inv_Amount")
    @Expose
    private Float invAmount;
    @SerializedName("balance")
    @Expose
    private Float balance;

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

    public Integer getTriplePlayID() {
        return triplePlayID;
    }

    public void setTriplePlayID(Integer triplePlayID) {
        this.triplePlayID = triplePlayID;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Object getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(Object previousBalance) {
        this.previousBalance = previousBalance;
    }

    public Float getInvAmount() {
        return invAmount;
    }

    public void setInvAmount(Float invAmount) {
        this.invAmount = invAmount;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

}
