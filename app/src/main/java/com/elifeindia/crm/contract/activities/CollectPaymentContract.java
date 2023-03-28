package com.elifeindia.crm.contract.activities;


import com.elifeindia.crm.model.CustomerInvoice;
import com.elifeindia.crm.model.CustomerSubscribeList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.PaymentTypeList;

public interface CollectPaymentContract {
    interface View {
        void init();

        void showError(String message);

        void showCustomerSubscribeList(CustomerSubscribeList customerSubscribeList);
        void showPaymentTypeList(PaymentTypeList paymentTypeList);
        void showCustomerInvoice(CustomerInvoice customerInvoice);
        void showInsertPayment(InsertPayment insertPayment);
    }

    interface Presenter {
        void start();

        void loadCustomerSubscribeList(String Customer_ID);
        void loadPaymentTypeList(String Company_ID);
        void loadCustomerInvoice(String custId, String triplePlayId);
        void loadInsertPayment(String Customer_ID, String paymentType_Id, String invoice_ID, String date,
                               String total_amount, String paid_Amount, String balance, String transaction_No,
                               String company_ID, String user_ID, String s);
    }


}
