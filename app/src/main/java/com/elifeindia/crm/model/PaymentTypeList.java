package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PaymentTypeList {

    @SerializedName("payment_Type")
    @Expose
    private List<PaymentType> paymentType = null;
    @SerializedName("status")
    @Expose
    private Object status;

    public List<PaymentType> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(List<PaymentType> paymentType) {
        this.paymentType = paymentType;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
    public class PaymentType {

        @SerializedName("paymentType_ID")
        @Expose
        private Integer paymentTypeID;
        @SerializedName("paymentType")
        @Expose
        private String paymentType;
        @SerializedName("company_ID")
        @Expose
        private Object companyID;
        @SerializedName("creation_Date")
        @Expose
        private Object creationDate;
        @SerializedName("created_By")
        @Expose
        private Object createdBy;
        @SerializedName("last_Updated_Date")
        @Expose
        private Object lastUpdatedDate;
        @SerializedName("last_Updated_By")
        @Expose
        private Object lastUpdatedBy;
        @SerializedName("delete_Date")
        @Expose
        private Object deleteDate;
        @SerializedName("user_ID")
        @Expose
        private Object userID;
        @SerializedName("date")
        @Expose
        private Object date;
        @SerializedName("delete_Flag")
        @Expose
        private Object deleteFlag;
        @SerializedName("status")
        @Expose
        private Object status;

        public Integer getPaymentTypeID() {
            return paymentTypeID;
        }

        public void setPaymentTypeID(Integer paymentTypeID) {
            this.paymentTypeID = paymentTypeID;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public Object getCompanyID() {
            return companyID;
        }

        public void setCompanyID(Object companyID) {
            this.companyID = companyID;
        }

        public Object getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(Object creationDate) {
            this.creationDate = creationDate;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
        }

        public Object getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(Object lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public Object getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(Object lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public Object getDeleteDate() {
            return deleteDate;
        }

        public void setDeleteDate(Object deleteDate) {
            this.deleteDate = deleteDate;
        }

        public Object getUserID() {
            return userID;
        }

        public void setUserID(Object userID) {
            this.userID = userID;
        }

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

        public Object getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(Object deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

    }
}


