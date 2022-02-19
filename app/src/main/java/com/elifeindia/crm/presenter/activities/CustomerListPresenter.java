package com.elifeindia.crm.presenter.activities;


import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.CustomerListContract;
import com.elifeindia.crm.model.AreaResponse;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.model.PaymentStatusModel;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CustomerListPresenter implements CustomerListContract.Presenter {
    CustomerListContract.View mView;
    ProgressDialog progressBar;

    public CustomerListPresenter(CustomerListContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadApi(Context context, String Company_ID, String User_ID, String Employee_ID, String Value, String count, String pageNo) {

        if(NetCheck.isInternetConnection(context)){
            NetworkUtils.getUserApiInstance()
                    .getCustemersList(Company_ID, User_ID, Employee_ID, Value, count, pageNo)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CustemersList>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(CustemersList custemersList) {
                            mView.showResult(custemersList);
                            progressBar.dismiss();
                        }
                    });
        }else{
            mView.showError("Connection Error");
            progressBar.dismiss();
        }


    }

    @Override
    public void loadApiSearch(Context context, String Company_ID, String User_ID, String Employee_ID, String Value, String count, String pageNo) {
        if(NetCheck.isInternetConnection(context)){
            NetworkUtils.getUserApiInstance()
                    .getCustemersList(Company_ID, User_ID, Employee_ID, Value, count, pageNo)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CustemersList>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(CustemersList custemersList) {
                            mView.showResult(custemersList);

                        }
                    });
        }else{
            mView.showError("Connection Error");

        }

    }

    @Override
    public void loadArea(Context context, String CompanyId, String Employee_ID) {
        if(NetCheck.isInternetConnection(context)){
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
        }else{
            mView.showError("Connection Error");


        }
    }

    @Override
    public void getPaymentStatus(Context context, String Id) {
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        if(NetCheck.isInternetConnection(context)){
            NetworkUtils.getUserApiInstance()
                    .getPaymentStatus("0")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PaymentStatusModel>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(PaymentStatusModel paymentStatusModel) {
                            mView.showPaymentStatus(paymentStatusModel);

                        }
                    });
        }else{
            mView.showError("Connection Error");

        }
    }

    @Override
    public void loadCustomersDateWise(Context context, String Company_ID, String User_ID, String Employee_ID, String AreaId, String Date, String StatusId, String count, String pageNo, String Value) {
        if(NetCheck.isInternetConnection(context)){
            NetworkUtils.getUserApiInstance()
                    .getCustemersListDateWise(Company_ID, User_ID, Employee_ID, AreaId, Date, StatusId, count, pageNo,Value)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CustemersList>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                            progressBar.dismiss();
                        }

                        @Override
                        public void onNext(CustemersList custemersList) {
                            mView.showCustomersDateWise(custemersList);
                            progressBar.dismiss();

                        }
                    });
        }else{
            mView.showError("Connection Error");
            progressBar.dismiss();

        }
    }


}
