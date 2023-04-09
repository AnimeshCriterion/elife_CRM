package com.elifeindia.crm;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCustomerPojo {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("whatsup_No")
    @Expose
    private String whatsupNo;
    @SerializedName("is_Auto_Renew")
    @Expose
    private String isAutoRenew;
    @SerializedName("company_ID")
    @Expose
    private String companyID;
    @SerializedName("user_ID")
    @Expose
    private String userID;
    @SerializedName("contact_No")
    @Expose
    private String contactNo;
    @SerializedName("connection_Status_ID")
    @Expose
    private String connectionStatusID;
    @SerializedName("customer_ID")
    @Expose
    private String customerID;
    @SerializedName("soDoWo")
    @Expose
    private String soDoWo;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("account_No")
    @Expose
    private String accountNo;
    @SerializedName("subscriber_ID")
    @Expose
    private String subscriberID;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("aadhar_No")
    @Expose
    private String aadharNo;
    @SerializedName("gsT_Number")
    @Expose
    private String gsTNumber;
    @SerializedName("area_Customer_ID")
    @Expose
    private String areaCustomerID;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWhatsupNo() {
        return whatsupNo;
    }

    public void setWhatsupNo(String whatsupNo) {
        this.whatsupNo = whatsupNo;
    }

    public String getIsAutoRenew() {
        return isAutoRenew;
    }

    public void setIsAutoRenew(String isAutoRenew) {
        this.isAutoRenew = isAutoRenew;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getConnectionStatusID() {
        return connectionStatusID;
    }

    public void setConnectionStatusID(String connectionStatusID) {
        this.connectionStatusID = connectionStatusID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(String subscriberID) {
        this.subscriberID = subscriberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getGsTNumber() {
        return gsTNumber;
    }

    public void setGsTNumber(String gsTNumber) {
        this.gsTNumber = gsTNumber;
    }

    public String getAreaCustomerID() {
        return areaCustomerID;
    }

    public void setAreaCustomerID(String areaCustomerID) {
        this.areaCustomerID = areaCustomerID;
    }

}