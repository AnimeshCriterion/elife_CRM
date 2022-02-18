package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomersInternetBoxData {

    @SerializedName("internetBox")
    @Expose
    private List<InternetBox> internetBox = null;
    @SerializedName("status")
    @Expose
    private Object status;

    public List<InternetBox> getInternetBox() {
        return internetBox;
    }

    public void setInternetBox(List<InternetBox> internetBox) {
        this.internetBox = internetBox;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
    public class InternetBox {

        @SerializedName("internet_Box_ID")
        @Expose
        private Integer internetBoxID;
        @SerializedName("customer_ID")
        @Expose
        private Integer customerID;
        @SerializedName("box_ID")
        @Expose
        private Object boxID;
        @SerializedName("boxType_ID")
        @Expose
        private Object boxTypeID;
        @SerializedName("ip")
        @Expose
        private String ip;
        @SerializedName("mac")
        @Expose
        private String mac;
        @SerializedName("bill_Type_ID")
        @Expose
        private Object billTypeID;
        @SerializedName("noofMonth")
        @Expose
        private Integer noofMonth;
        @SerializedName("activation_Date")
        @Expose
        private String activationDate;
        @SerializedName("expiry_Date")
        @Expose
        private String expiryDate;
        @SerializedName("package_Amount")
        @Expose
        private Object packageAmount;
        @SerializedName("box_Amount")
        @Expose
        private Float boxAmount;
        @SerializedName("connection_Status_ID")
        @Expose
        private Object connectionStatusID;
        @SerializedName("bill_Type")
        @Expose
        private String billType;
        @SerializedName("status_Name")
        @Expose
        private String statusName;
        @SerializedName("boxTypeName")
        @Expose
        private Object boxTypeName;
        @SerializedName("payment_Date")
        @Expose
        private String paymentDate;
        @SerializedName("paid_Amount")
        @Expose
        private Float paidAmount;
        @SerializedName("status")
        @Expose
        private String status;

        public Integer getInternetBoxID() {
            return internetBoxID;
        }

        public void setInternetBoxID(Integer internetBoxID) {
            this.internetBoxID = internetBoxID;
        }

        public Integer getCustomerID() {
            return customerID;
        }

        public void setCustomerID(Integer customerID) {
            this.customerID = customerID;
        }

        public Object getBoxID() {
            return boxID;
        }

        public void setBoxID(Object boxID) {
            this.boxID = boxID;
        }

        public Object getBoxTypeID() {
            return boxTypeID;
        }

        public void setBoxTypeID(Object boxTypeID) {
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

        public Object getBillTypeID() {
            return billTypeID;
        }

        public void setBillTypeID(Object billTypeID) {
            this.billTypeID = billTypeID;
        }

        public Integer getNoofMonth() {
            return noofMonth;
        }

        public void setNoofMonth(Integer noofMonth) {
            this.noofMonth = noofMonth;
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

        public Object getPackageAmount() {
            return packageAmount;
        }

        public void setPackageAmount(Object packageAmount) {
            this.packageAmount = packageAmount;
        }

        public Float getBoxAmount() {
            return boxAmount;
        }

        public void setBoxAmount(Float boxAmount) {
            this.boxAmount = boxAmount;
        }

        public Object getConnectionStatusID() {
            return connectionStatusID;
        }

        public void setConnectionStatusID(Object connectionStatusID) {
            this.connectionStatusID = connectionStatusID;
        }

        public String getBillType() {
            return billType;
        }

        public void setBillType(String billType) {
            this.billType = billType;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public Object getBoxTypeName() {
            return boxTypeName;
        }

        public void setBoxTypeName(Object boxTypeName) {
            this.boxTypeName = boxTypeName;
        }

        public String getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
        }

        public Float getPaidAmount() {
            return paidAmount;
        }

        public void setPaidAmount(Float paidAmount) {
            this.paidAmount = paidAmount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}


