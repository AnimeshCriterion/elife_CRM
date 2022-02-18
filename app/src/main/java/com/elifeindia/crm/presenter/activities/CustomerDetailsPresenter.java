package com.elifeindia.crm.presenter.activities;


import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.CustomerDetailsContract;
import com.elifeindia.crm.contract.activities.CustomerListContract;
import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CustomerDetailsPresenter implements CustomerDetailsContract.Presenter {
    CustomerDetailsContract.View mView;

    public CustomerDetailsPresenter(CustomerDetailsContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadApi(Context context, String Customer_ID) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        if(NetCheck.isInternetConnection(context)){
            NetworkUtils.getUserApiInstance()
                    .getCustomerData(Customer_ID)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CustomerData>() {
                        @Override
                        public void onCompleted() {
                            progressBar.dismiss();
                        }
                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(CustomerData custemersList) {
                            mView.showResult(custemersList);
                        }
                    });
        }else{
            progressBar.dismiss();
            mView.showError("Connection Error");
        }
    }

    @Override
    public void loadCableBoxListApi(String Customer_ID) {

        NetworkUtils.getUserApiInstance()
                .getCustemersCableBoxData(Customer_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CustemersCableBoxData>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(CustemersCableBoxData custemersCableBoxData) {
                        mView.showCableBoxList(custemersCableBoxData);
                    }
                });
    }

    @Override
    public void loadInternetBoxListApi(String Customer_ID) {
        NetworkUtils.getUserApiInstance()
                .getCustemersinternetBoxData(Customer_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CustomersInternetBoxData>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(CustomersInternetBoxData custemersCableBoxData) {
                        mView.showInernetBoxList(custemersCableBoxData);
                    }
                });
    }


}
