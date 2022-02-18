package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.HeaderModel;
import com.elifeindia.crm.model.Login;
import com.elifeindia.crm.model.PaymentReciept;

public interface PaymentReceiptContract {
    interface View {
        void init();
        void showError(String message);
        void showReceipt(PaymentReciept paymentReciept);
        void showHeader(HeaderModel paymentReciept);
        void showFooter(FooterModel paymentReciept);

    }

    interface Presenter {
        void start();
        void loadPaymentReceipt(Context context, String paymentId);
        void loadHeader(Context context, String compId);
        void loadFooter(Context context, String compId);
    }


}
