package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.BoxAlacarteList;
import com.elifeindia.crm.model.BoxBouquetList;
import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.model.InternetSubscriptionDetails;

public interface SubscriptionDetailsContract {
    interface View {
        void init();

        void showError(String message);

        void showAlacarte(BoxAlacarteList boxAlacarteList);
        void showBouquets(BoxBouquetList boxBouquetList);
        void showInternetPkg(InternetSubscriptionDetails internetSubscriptionDetails);
    }

    interface Presenter {
        void start();

        void loadBoxAlacarteList(String _Box_ID);
        void loadBoxBouquetList(String Cable_Box_ID);
        void loadInternetSubscriptionDetails(String Internet_Box_ID);
    }


}
