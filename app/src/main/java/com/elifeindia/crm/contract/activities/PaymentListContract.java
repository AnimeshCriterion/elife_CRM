package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.AreaResponse;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.PaymentRecieptList;

public interface PaymentListContract {
    interface View {
        void init();
        void showError(String message);
        void showResult(PaymentRecieptList paymentRecieptList);
        void showEmployeeList(EmployeeList employeeList);
        void showArea(AreaResponse areaResponse);


    }

    interface Presenter {
        void start();
        void loadPaymentListForSearch(Context context, String Company_ID, String Customer_ID, String From_Date, String To_Date, String Triple_play_ID, String Value, String Employee_ID);
        void loadPaymentList(Context context, String Company_ID, String Customer_ID, String From_Date, String To_Date, String Triple_play_ID, String Value, String Employee_ID);
        void loadEmployeeList(Context context, String Company_ID, String id, String Role_type);
        void loadArea(Context context, String CompanyId, String Employee_ID);
        void loadCustomersDateWise(Context context, String Company_ID, String User_ID, String Employee_ID, String AreaId, String Date);

    }


}
