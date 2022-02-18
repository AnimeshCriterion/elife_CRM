package com.elifeindia.crm.presenter.activities;


import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.AddReportContract;
import com.elifeindia.crm.contract.activities.EditReportContract;
import com.elifeindia.crm.model.ComplaintStatusList;
import com.elifeindia.crm.model.ComplaintTypeList;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.PriorityList;
import com.elifeindia.crm.model.ProductList;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class EditReportPresenter implements EditReportContract.Presenter {
    EditReportContract.View mView;

    public EditReportPresenter(EditReportContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void getComplaintTypeList(Context context, String compId, String id) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getComplaintTypeList(compId, id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ComplaintTypeList>() {
                        @Override
                        public void onCompleted() {
                            progressBar.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(ComplaintTypeList complaintTypeList) {
                            mView.showComplaintTypeList(complaintTypeList);
                        }
                    });
        }else {
            mView.showError("Connection Error");
            progressBar.dismiss();
        }
    }

    @Override
    public void getProductList(Context context, String compId, String id) {
        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getProductList(compId,id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ProductList>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(ProductList productList) {
                            mView.showProductList(productList);
                        }
                    });
        }else {
            mView.showError("Connection Error");
        }
    }

    @Override
    public void getComplaintStatusList(Context context, String compId, String id ) {
        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getComplaintStatusList(compId, id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ComplaintStatusList>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(ComplaintStatusList complaintStatusList) {
                            mView.showComplaintStatusList(complaintStatusList);
                        }
                    });
        }else {
            mView.showError("Connection Error");
        }
    }

    @Override
    public void getPriorityList(Context context, String compId, String Priority_ID) {
        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getPriorityList(compId, Priority_ID)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PriorityList>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(PriorityList complaintStatusList) {
                            mView.showPriorityList(complaintStatusList);
                        }
                    });
        }else {
            mView.showError("Connection Error");
        }
    }

    @Override
    public void loadEmployeeList(Context context, String Company_ID, String id, String Role_type) {
        if(NetCheck.isInternetConnection(context)){
            NetworkUtils.getUserApiInstance()
                    .getEmployeeList(Company_ID, id, Role_type)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<EmployeeList>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }
                        @Override
                        public void onNext(EmployeeList employeeList) {
                            mView.showEmployeeList(employeeList);
                        }
                    });
        }else{
            mView.showError("Connection Error");
        }
    }

    @Override
    public void updateComplaint(Context context, String Complaint_ID, String Company_ID, String Customer_ID, String Complaint_Code, String Complaint_Date, String Complaint_Type_ID,
                                String Description, String Product_ID, String Assigned_Date, String Assigned_To, String Complaint_Status_ID, String Comment, String Priority_ID, String User_ID, String Date) {
        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .updateComplaint(Complaint_ID, Company_ID, Customer_ID, Complaint_Code, Complaint_Date, Complaint_Type_ID,
                            Description, Product_ID, Assigned_Date, Assigned_To, Complaint_Status_ID, Comment, Priority_ID, User_ID, Date)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<InsertPayment>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(InsertPayment complaintStatusList) {
                            mView.updateResponse(complaintStatusList);
                        }
                    });
        }else {
            mView.showError("Connection Error");
        }
    }


}
