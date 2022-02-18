package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.InsertPayment;

public interface PackageListFragContract {
    interface View {
        void init();
        void showError(String message);
        void showBouquet(BouquetModel bouquetModel);

    }

    interface Presenter {
        void start();
        void loadBouquet(Context context, String compId, String customerId);

    }


}
