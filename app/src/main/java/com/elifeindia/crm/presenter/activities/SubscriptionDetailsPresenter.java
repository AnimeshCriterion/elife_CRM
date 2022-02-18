package com.elifeindia.crm.presenter.activities;


import android.content.Context;

import com.elifeindia.crm.contract.activities.CustomerDetailsContract;
import com.elifeindia.crm.contract.activities.SubscriptionDetailsContract;
import com.elifeindia.crm.model.BoxAlacarteList;
import com.elifeindia.crm.model.BoxBouquetList;
import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.model.InternetSubscriptionDetails;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SubscriptionDetailsPresenter implements SubscriptionDetailsContract.Presenter {
    SubscriptionDetailsContract.View mView;

    public SubscriptionDetailsPresenter(SubscriptionDetailsContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadBoxAlacarteList(String Cable_Box_ID) {

        NetworkUtils.getUserApiInstance()
                .getBoxalacarteList(Cable_Box_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BoxAlacarteList>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(BoxAlacarteList boxAlacarteList) {
                        mView.showAlacarte(boxAlacarteList);
                    }
                });
    }

    @Override
    public void loadBoxBouquetList(String Cable_Box_ID) {
        NetworkUtils.getUserApiInstance()
                .getBoxBouquetList(Cable_Box_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BoxBouquetList>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(BoxBouquetList boxBouquetList) {
                        mView.showBouquets(boxBouquetList);
                    }
                });
    }

    @Override
    public void loadInternetSubscriptionDetails(String Internet_Box_ID) {
        NetworkUtils.getUserApiInstance()
                .getInternetSubscriptiondetails(Internet_Box_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InternetSubscriptionDetails>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(InternetSubscriptionDetails internetSubscriptionDetails) {
                        mView.showInternetPkg(internetSubscriptionDetails);
                    }
                });
    }



}
