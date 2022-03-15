
package com.elifeindia.crm.model.generateinvoice;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CableBox {

    @SerializedName("activation_Date")
    @Expose
    private String activationDate;
    @SerializedName("bill_Type")
    @Expose
    private String billType;
    @SerializedName("bill_Type_ID")
    @Expose
    private Integer billTypeID;
    @SerializedName("boxTypeName")
    @Expose
    private String boxTypeName;
    @SerializedName("boxType_ID")
    @Expose
    private Integer boxTypeID;
    @SerializedName("box_Amount")
    @Expose
    private Double boxAmount;
    @SerializedName("cable_Box_ID")
    @Expose
    private Integer cableBoxID;
    @SerializedName("cafno")
    @Expose
    private String cafno;
    @SerializedName("connection_Status_ID")
    @Expose
    private Integer connectionStatusID;
    @SerializedName("customer_ID")
    @Expose
    private Integer customerID;
    @SerializedName("expiry_Date")
    @Expose
    private String expiryDate;
    @SerializedName("iPtv")
    @Expose
    private String iPtv;
    @SerializedName("noofMonth")
    @Expose
    private Integer noofMonth;
    @SerializedName("payment_Date")
    @Expose
    private String paymentDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_Name")
    @Expose
    private String statusName;
    @SerializedName("stbno")
    @Expose
    private String stbno;
    @SerializedName("vcno")
    @Expose
    private String vcno;

    public CableBox(String activationDate, String billType, Integer billTypeID, String boxTypeName, Integer boxTypeID, Double boxAmount, Integer cableBoxID, String cafno, Integer connectionStatusID, Integer customerID, String expiryDate, String iPtv, Integer noofMonth, String paymentDate, String status, String statusName, String stbno, String vcno) {
        this.activationDate = activationDate;
        this.billType = billType;
        this.billTypeID = billTypeID;
        this.boxTypeName = boxTypeName;
        this.boxTypeID = boxTypeID;
        this.boxAmount = boxAmount;
        this.cableBoxID = cableBoxID;
        this.cafno = cafno;
        this.connectionStatusID = connectionStatusID;
        this.customerID = customerID;
        this.expiryDate = expiryDate;
        this.iPtv = iPtv;
        this.noofMonth = noofMonth;
        this.paymentDate = paymentDate;
        this.status = status;
        this.statusName = statusName;
        this.stbno = stbno;
        this.vcno = vcno;
    }
}
