package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentRecieptList {

    @SerializedName("paymentReciept")
    @Expose
    private List<PaymentReciept> paymentReciept = null;
    @SerializedName("status")
    @Expose
    private Object status;

    public String getTotal_Paid_amount() {
        return total_Paid_amount;
    }

    public void setTotal_Paid_amount(String total_Paid_amount) {
        this.total_Paid_amount = total_Paid_amount;
    }

    public String getTotal_Balance() {
        return total_Balance;
    }

    public void setTotal_Balance(String total_Balance) {
        this.total_Balance = total_Balance;
    }

    @SerializedName("total_Paid_amount")
    @Expose
    private String total_Paid_amount;
    @SerializedName("total_Balance")
    @Expose
    private String total_Balance;

    public List<PaymentReciept> getPaymentReciept() {
        return paymentReciept;
    }

    public void setPaymentReciept(List<PaymentReciept> paymentReciept) {
        this.paymentReciept = paymentReciept;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
    public static class PaymentReciept {

        @SerializedName("customer_ID")
        @Expose
        private Integer customerID;
        @SerializedName("account_No")
        @Expose
        private Integer accountNo;
        @SerializedName("subscriber_ID")
        @Expose
        private String subscriberID;

private  String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @SerializedName("contact_No")
        @Expose
        private String contactNo;



        @SerializedName("whatsup_No")
        @Expose
        private String WhatsappNo;





        private  String payment_Id;

        public String getInvoice_ID() {
            return invoice_ID;
        }

        public void setInvoice_ID(String invoice_ID) {
            this.invoice_ID = invoice_ID;
        }

        private  String invoice_ID;

        public String getPayment_Id() {
            return payment_Id;
        }

        public void setPayment_Id(String payment_Id) {
            this.payment_Id = payment_Id;
        }

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


        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public String getWhatsappNo() {
            return WhatsappNo;
        }

        public void setWhatsappNo(String whatsappNo) {
            WhatsappNo = whatsappNo;
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
}


