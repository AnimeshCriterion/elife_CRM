package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomersInternetBoxData;

public interface CustomerDetailsContract {
    interface View {
        void init();
        void showError(String message);
        void showResult(CustomerData customerData);
        void showCableBoxList(CustemersCableBoxData custemersCableBoxData);
        void showInernetBoxList(CustomersInternetBoxData customersInternetBoxData);
    }

    interface Presenter {
        void start();
        void loadApi(Context context, String Customer_ID);
        void loadCableBoxListApi(String Customer_ID);
        void loadInternetBoxListApi(String Customer_ID);
    }


}
