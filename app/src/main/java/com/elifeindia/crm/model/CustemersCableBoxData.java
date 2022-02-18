package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CustemersCableBoxData {

    @SerializedName("cableBox")
    @Expose
    private List<CableBox> cableBox = null;
    @SerializedName("status")
    @Expose
    private Object status;

    public List<CableBox> getCableBox() {
        return cableBox;
    }

    public void setCableBox(List<CableBox> cableBox) {
        this.cableBox = cableBox;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
    public class CableBox {

        @SerializedName("cable_Box_ID")
        @Expose
        private Integer cableBoxID;
        @SerializedName("customer_ID")
        @Expose
        private Integer customerID;
        @SerializedName("box_ID")
        @Expose
        private Object boxID;
        @SerializedName("boxType_ID")
        @Expose
        private Integer boxTypeID;
        @SerializedName("vcno")
        @Expose
        private String vcno;
        @SerializedName("stbno")
        @Expose
        private String stbno;
        @SerializedName("cafno")
        @Expose
        private String cafno;
        @SerializedName("iPtv")
        @Expose
        private String iPtv;
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
        @SerializedName("alacarte_Amount")
        @Expose
        private Object alacarteAmount;
        @SerializedName("bouquet_Amount")
        @Expose
        private Object bouquetAmount;
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
        private String boxTypeName;
        @SerializedName("payment_Date")
        @Expose
        private String paymentDate;
        @SerializedName("paid_Amount")
        @Expose
        private Float paidAmount;
        @SerializedName("status")
        @Expose
        private String status;

        public Integer getCableBoxID() {
            return cableBoxID;
        }

        public void setCableBoxID(Integer cableBoxID) {
            this.cableBoxID = cableBoxID;
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

        public Integer getBoxTypeID() {
            return boxTypeID;
        }

        public void setBoxTypeID(Integer boxTypeID) {
            this.boxTypeID = boxTypeID;
        }

        public String getVcno() {
            return vcno;
        }

        public void setVcno(String vcno) {
            this.vcno = vcno;
        }

        public String getStbno() {
            return stbno;
        }

        public void setStbno(String stbno) {
            this.stbno = stbno;
        }

        public String getCafno() {
            return cafno;
        }

        public void setCafno(String cafno) {
            this.cafno = cafno;
        }

        public String getIPtv() {
            return iPtv;
        }

        public void setIPtv(String iPtv) {
            this.iPtv = iPtv;
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

        public Object getAlacarteAmount() {
            return alacarteAmount;
        }

        public void setAlacarteAmount(Object alacarteAmount) {
            this.alacarteAmount = alacarteAmount;
        }

        public Object getBouquetAmount() {
            return bouquetAmount;
        }

        public void setBouquetAmount(Object bouquetAmount) {
            this.bouquetAmount = bouquetAmount;
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

        public String getBoxTypeName() {
            return boxTypeName;
        }

        public void setBoxTypeName(String boxTypeName) {
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
