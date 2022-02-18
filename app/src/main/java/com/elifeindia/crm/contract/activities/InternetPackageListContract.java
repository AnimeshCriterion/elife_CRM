package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.InternetPackageModel;

public interface InternetPackageListContract {
    interface View {
        void init();
        void showError(String message);
        void showPackage(InternetPackageModel internetPackageModel);

    }

    interface Presenter {
        void start();
        void loadPackage(Context context, String compId, String customerId);

    }


}
