package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.HeaderModel;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.PaymentReciept;

public interface AddOnFragmentContract {
    interface View {
        void init();
        void showError(String message);
        void showAlacarte(AlacarteModel alacarteModel);

    }

    interface Presenter {
        void start();
        void loadAlacarte(Context context, String compId, String customerId);

    }


}
