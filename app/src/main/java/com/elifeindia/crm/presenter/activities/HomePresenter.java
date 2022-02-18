package com.elifeindia.crm.presenter.activities;


import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.HomeContract;
import com.elifeindia.crm.contract.activities.LoginContract;
import com.elifeindia.crm.model.Login;
import com.elifeindia.crm.model.RolewiseAccess;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View mView;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadApi(Context context, String roleId) {

        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getAccessList(roleId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<RolewiseAccess>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());

                        }

                        @Override
                        public void onNext(RolewiseAccess login) {
                            mView.showResult(login);

                        }
                    });
        }else {
            mView.showError("Connection Error");

        }
    }


}
