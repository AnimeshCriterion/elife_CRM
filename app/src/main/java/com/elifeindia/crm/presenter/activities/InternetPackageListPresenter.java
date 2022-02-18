package com.elifeindia.crm.presenter.activities;


import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.InternetPackageListContract;
import com.elifeindia.crm.contract.activities.LoginContract;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.InternetPackageModel;
import com.elifeindia.crm.model.Login;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class InternetPackageListPresenter implements InternetPackageListContract.Presenter {
    InternetPackageListContract.View mView;


    public InternetPackageListPresenter(InternetPackageListContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadPackage(Context context, String compId, String customerId) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();

        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getInternetPackageList(compId, customerId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<InternetPackageModel>() {
                        @Override
                        public void onCompleted() {
                            progressBar.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                            progressBar.dismiss();
                        }

                        @Override
                        public void onNext(InternetPackageModel login) {
                            mView.showPackage(login);
                            progressBar.dismiss();
                        }
                    });
        }else {
            mView.showError("Connection Error");
            progressBar.dismiss();
        }
    }






}
