package com.elifeindia.crm.contract.activities;

import android.content.Context;

import com.elifeindia.crm.model.AlacarteModel;

public interface BillShareActivityContract {
    interface View {
        void init();

        void showError(String message);

        void showInvoiceReport(AlacarteModel alacarteModel);

    }

    interface Presenter {
        void start();

        void loadInvoiceReport(Context context, String compId, String customerId);

    }


}
