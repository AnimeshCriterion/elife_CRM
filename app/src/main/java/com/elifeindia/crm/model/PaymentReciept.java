package com.elifeindia.crm.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentReciept {

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
    @SerializedName("invoice_Number")
    @Expose
    private String invoiceNumber;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("payment_Date")
    @Expose
    private String paymentDate;
    @SerializedName("total_amount")
    @Expose
    private Float totalAmount;
    @SerializedName("paid_Amount")
    @Expose
    private Float paidAmount;
    @SerializedName("balance")
    @Expose
    private Float balance;
    @SerializedName("triple_play_ID")
    @Expose
    private Integer triplePlayID;
    @SerializedName("triple_play")
    @Expose
    private String triplePlay;

    private String employee_Name;
    private String employee_Contact;

    public String getEmployee_Name() {
        return employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        this.employee_Name = employee_Name;
    }

    public String getEmployee_Contact() {
        return employee_Contact;
    }

    public void setEmployee_Contact(String employee_Contact) {
        this.employee_Contact = employee_Contact;
    }

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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Float paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getTriplePlayID() {
        return triplePlayID;
    }

    public void setTriplePlayID(Integer triplePlayID) {
        this.triplePlayID = triplePlayID;
    }

    public String getTriplePlay() {
        return triplePlay;
    }

    public void setTriplePlay(String triplePlay) {
        this.triplePlay = triplePlay;
    }

}
