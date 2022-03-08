package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.AreaResponse;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.model.Login;
import com.elifeindia.crm.model.PaymentStatusModel;
import com.elifeindia.crm.sharedpref.Constants;

public interface CustomerListContract {
    interface View {
        void init();

        void showError(String message);

        void showResult(CustemersList custemersList);

        void showSearchResult(CustemersList custemersList);

        void showArea(AreaResponse areaResponse);

        void showPaymentStatus(PaymentStatusModel areaResponse);

        void showCustomersDateWise(CustemersList custemersList);
    }

    interface Presenter {
        void start();

        void loadApi(Context context, String Company_ID, String User_ID, String Employee_ID, String Value, String count, String pageNo);
        void loadApiSearch(Context context, String Company_ID, String User_ID, String Employee_ID, String Value, String count, String pageNo);
        void loadArea(Context context, String CompanyId, String Employee_ID);
        void getPaymentStatus(Context context, String Id);
        void loadCustomersDateWise(Context context, String Company_ID, String User_ID, String Employee_ID, String AreaId, String Date, String StatusId, String count, String pageNo, String Value,String field_value,String field_name);
    }
    
}
