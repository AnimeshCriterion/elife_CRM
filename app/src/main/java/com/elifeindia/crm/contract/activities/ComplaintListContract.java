package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.ComplaintList;
import com.elifeindia.crm.model.ComplaintStatusList;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.PaymentRecieptList;

import retrofit2.http.Query;

public interface ComplaintListContract {
    interface View {
        void init();
        void showError(String message);
        void showResult(ComplaintList complaintList);
        void showEmployeeList(EmployeeList employeeList);
        void showComplaintStatus(ComplaintStatusList complaintStatusList);
        void showOpencloseComplaint(InsertPayment insertPayment);
    }

    interface Presenter {
        void start();
        void  loadComplaintList(Context context, String Company_ID, String Employee_ID, String Customer_ID, String Complaint_Status_ID, String From_date, String To_Date, String Value);
        void loadComplaintListForSearch(Context context, String Company_ID, String Employee_ID, String Customer_ID, String Complaint_Status_ID, String From_date, String To_Date, String Value);
        void loadComplaintStatus(Context context, String Company_ID, String id);
        void loadEmployeeList(Context context, String Company_ID, String id, String Role_type);
        void loadOpencloseComplaint(
                Context context,
               String Company_ID,
               String open_Date,
               String open_By,
               String closed_Date,
               String complaint_Status_ID,
               String comment,
               String closed_By,
               String user_ID,
               String date
        );

    }


}
