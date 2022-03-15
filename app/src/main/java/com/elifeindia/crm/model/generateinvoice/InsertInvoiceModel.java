
package com.elifeindia.crm.model.generateinvoice;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertInvoiceModel {

    @SerializedName("cableBoxwithSubscription")
    @Expose
    private List<CableBoxwithSubscription> cableBoxwithSubscription = null;
    @SerializedName("total_CableBox_amount")
    @Expose
    private String totalCableBoxAmount;
    @SerializedName("internetBoxwithSubscription")
    @Expose
    private List<Object> internetBoxwithSubscription = null;
    @SerializedName("company_ID")
    @Expose
    private String companyID;
    @SerializedName("invoice_Date")
    @Expose
    private String invoiceDate;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("previous_Balance")
    @Expose
    private String previousBalance;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("inv_Amount")
    @Expose
    private String invAmount;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("user_Id")
    @Expose
    private String userId;
    @SerializedName("customer_Id")
    @Expose
    private String customerId;
    @SerializedName("triple_play_ID")
    @Expose
    private String triplePlayID;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("triple_play")
    @Expose
    private String triplePlay;
    @SerializedName("paid_Amount")
    @Expose
    private String paidAmount;
    @SerializedName("employee_ID")
    @Expose
    private String employeeID;
    @SerializedName("paymentType_ID")
    @Expose
    private String paymentTypeID;
    @SerializedName("payment_Date")
    @Expose
    private String paymentDate;
    @SerializedName("transaction_No")
    @Expose
    private String transactionNo;

    public InsertInvoiceModel(List<CableBoxwithSubscription> cableBoxwithSubscription, String totalCableBoxAmount, List<Object> internetBoxwithSubscription, String companyID, String invoiceDate, String amount, String previousBalance, String discount, String invAmount, String balance, String userId, String customerId, String triplePlayID, String date, String triplePlay, String paidAmount, String employeeID, String paymentTypeID, String paymentDate, String transactionNo) {
        this.cableBoxwithSubscription = cableBoxwithSubscription;
        this.totalCableBoxAmount = totalCableBoxAmount;
        this.internetBoxwithSubscription = internetBoxwithSubscription;
        this.companyID = companyID;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
        this.previousBalance = previousBalance;
        this.discount = discount;
        this.invAmount = invAmount;
        this.balance = balance;
        this.userId = userId;
        this.customerId = customerId;
        this.triplePlayID = triplePlayID;
        this.date = date;
        this.triplePlay = triplePlay;
        this.paidAmount = paidAmount;
        this.employeeID = employeeID;
        this.paymentTypeID = paymentTypeID;
        this.paymentDate = paymentDate;
        this.transactionNo = transactionNo;
    }
}
