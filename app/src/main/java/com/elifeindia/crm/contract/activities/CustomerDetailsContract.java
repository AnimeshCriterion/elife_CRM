package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.model.GetInvoiceModel;

public interface CustomerDetailsContract {
    interface View {
        void init();
        void showError(String message);
        void showResult(CustomerData customerData);
        void showCableBoxList(CustemersCableBoxData custemersCableBoxData);
        void showInernetBoxList(CustomersInternetBoxData customersInternetBoxData);

        void showInvoice(GetInvoiceModel getInvoiceModel);
    }

    interface Presenter {
        void start();
        void loadApi(Context context, String Customer_ID);
        void loadCableBoxListApi(String Customer_ID);
        void loadInternetBoxListApi(String Customer_ID);
        void getInvoice(Context context, String invId);
    }


}
