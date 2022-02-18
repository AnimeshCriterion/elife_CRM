package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.HeaderModel;
import com.elifeindia.crm.model.PaymentReciept;

public interface ComplaintReceiptContract {
    interface View {

        void showError(String message);
        void showHeader(HeaderModel paymentReciept);
        void showFooter(FooterModel paymentReciept);

    }

    interface Presenter {

        void loadHeader(Context context, String compId);
        void loadFooter(Context context, String compId);
    }


}
