package com.elifeindia.crm.presenter.activities;


import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.ComplaintListContract;
import com.elifeindia.crm.contract.activities.PaymentListContract;
import com.elifeindia.crm.model.ComplaintList;
import com.elifeindia.crm.model.ComplaintStatusList;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.PaymentRecieptList;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ComplaintListPresenter implements ComplaintListContract.Presenter {
    ComplaintListContract.View mView;
    ProgressDialog progressBar;

    public ComplaintListPresenter(ComplaintListContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadComplaintList(Context context, String Company_ID, String Employee_ID, String Customer_ID, String Complaint_Status_ID,
                                  String From_date, String To_Date, String Value ){


        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getComplaintList(Company_ID, Employee_ID, Customer_ID, Complaint_Status_ID, From_date, To_Date, Value)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ComplaintList>() {
                        @Override
                        public void onCompleted() {
                            try {
                                progressBar.dismiss();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(ComplaintList complaintList) {
                            mView.showResult(complaintList);
                        }
                    });
        }else {
            mView.showError("Connection Error");
            try {
                progressBar.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void loadComplaintListForSearch(Context context, String Company_ID, String Employee_ID, String Customer_ID, String Complaint_Status_ID, String From_date, String To_Date, String Value) {
        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getComplaintList(Company_ID, Employee_ID, Customer_ID, Complaint_Status_ID, From_date, To_Date, Value)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ComplaintList>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(ComplaintList complaintList) {
                            mView.showResult(complaintList);
                        }
                    });
        }else {
            mView.showError("Connection Error");

        }


    }

    @Override
    public void loadComplaintStatus(Context context, String Company_ID, String id) {
        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getComplaintStatusList(Company_ID, id)
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
                            mView.showComplaintStatus(complaintStatusList);
                        }
                    });
        }else {
            mView.showError("Connection Error");
        }
    }

    @Override
    public void loadEmployeeList(Context context, String Company_ID, String id, String Role_type) {
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
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
    public void loadOpencloseComplaint(Context context, String Company_ID, String Employee_ID, String Customer_ID, String Complaint_Status_ID, String From_date, String To_Date, String closed_By, String user_ID, String date) {
        if(NetCheck.isInternetConnection(context)){
            NetworkUtils.getUserApiInstance()
                    .opencloseComplaint(Company_ID, Employee_ID,Customer_ID, Complaint_Status_ID,  From_date, To_Date, closed_By, user_ID, date)
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
                        public void onNext(InsertPayment employeeList) {
                            mView.showOpencloseComplaint(employeeList);
                        }
                    });
        }else{
            mView.showError("Connection Error");
        }
    }


}
