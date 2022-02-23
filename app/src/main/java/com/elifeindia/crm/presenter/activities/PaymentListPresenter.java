package com.elifeindia.crm.presenter.activities;


import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.PaymentListContract;
import com.elifeindia.crm.model.AreaResponse;

import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.PaymentRecieptList;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

import rx.schedulers.Schedulers;


public class PaymentListPresenter implements PaymentListContract.Presenter {
    PaymentListContract.View mView;
    ProgressDialog progressBar;

    public PaymentListPresenter(PaymentListContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadPaymentListForSearch(Context context, String Company_ID, String Customer_ID, String From_Date, String To_Date, String Triple_play_ID, String Value, String Employee_ID, String Area_ID) {
        if (NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getPaymentRecieptList(Company_ID, Customer_ID, From_Date, To_Date, Triple_play_ID, Value, Employee_ID, Area_ID)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PaymentRecieptList>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(PaymentRecieptList paymentRecieptList) {
                            mView.showResult(paymentRecieptList);
                        }
                    });
        } else {
            mView.showError("Connection Error");

        }

    }

    @Override
    public void loadPaymentList(Context context, String Company_ID, String Customer_ID, String From_Date, String To_Date, String Triple_play_ID, String Value, String Employee_ID, String Area_ID) {
        if (NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getPaymentRecieptList(Company_ID, Customer_ID, From_Date, To_Date, Triple_play_ID, Value, Employee_ID, Area_ID)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PaymentRecieptList>() {
                        @Override
                        public void onCompleted() {
                          //  progressBar.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                            //progressBar.dismiss();
                        }

                        @Override
                        public void onNext(PaymentRecieptList paymentRecieptList) {
                            mView.showResult(paymentRecieptList);

                        }
                    });
        } else {
            mView.showError("Connection Error");
        }

    }

    @Override
    public void loadArea(Context context, String CompanyId, String Employee_ID) {
        if (NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getArea(CompanyId, Employee_ID)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AreaResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(AreaResponse areaResponse) {
                            mView.showArea(areaResponse);

                        }
                    });
        } else {
            mView.showError("Connection Error");

        }


    }

    @Override
    public void loadPaymentListDateWise(Context context, String Company_ID, String Customer_ID, String From_Date, String To_Date, String Triple_play_ID, String Value, String Employee_ID, String Area_ID) {

        if (NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getPaymentRecieptList(Company_ID, Customer_ID, From_Date, To_Date, Triple_play_ID, Value, Employee_ID, Area_ID)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PaymentRecieptList>() {
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
                        public void onNext(PaymentRecieptList paymentRecieptList) {
                            mView.showResult(paymentRecieptList);
                        }
                    });
        } else {
            mView.showError("Connection Error");
            try {
                progressBar.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void loadEmployeeList(Context context, String Company_ID, String Employee_ID, String Role_Type) {
        if (NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance().getEmployeeList(Company_ID, Employee_ID, Role_Type)
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
        }
    }
}


