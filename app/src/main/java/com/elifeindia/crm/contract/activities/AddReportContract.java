package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.ComplaintStatusList;
import com.elifeindia.crm.model.ComplaintTypeList;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.Login;
import com.elifeindia.crm.model.PriorityList;
import com.elifeindia.crm.model.ProductList;

public interface AddReportContract {
    interface View {
        void init();

        void showError(String message);

        void showComplaintTypeList(ComplaintTypeList complaintTypeList);
        void showProductList(ProductList productList);
        void showComplaintStatusList(ComplaintStatusList complaintStatusList);
        void showPriorityList(PriorityList priorityList);
        void showEmployeeList(EmployeeList employeeList);
        void insertResponse(InsertPayment insertPayment);

    }

    interface Presenter {
        void start();

        void getComplaintTypeList(Context context, String compId, String ComplaintTypeID);
        void getProductList(Context context, String compId, String ProductID );
        void getComplaintStatusList(Context context, String compId, String StatusID);
        void getPriorityList(Context context, String compId, String Priority_ID);
        void loadEmployeeList(Context context, String Company_ID, String AssignToID, String Role_type);
        void insertComplaint(Context context, String Complaint_ID, String Company_ID, String Customer_ID, String Complaint_Code, String Complaint_Date, String Complaint_Type_ID,
                             String Description, String Product_ID, String Assigned_Date, String Assigned_To, String Complaint_Status_ID, String Comment, String Priority_ID, String User_ID, String Date);

    }


}
