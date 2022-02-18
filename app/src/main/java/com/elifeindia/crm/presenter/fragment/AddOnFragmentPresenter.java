package com.elifeindia.crm.presenter.fragment;


import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.AddOnFragmentContract;
import com.elifeindia.crm.contract.activities.SubscriptionDetailsContract;
import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.BoxAlacarteList;
import com.elifeindia.crm.model.BoxBouquetList;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.InternetSubscriptionDetails;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AddOnFragmentPresenter implements AddOnFragmentContract.Presenter {
    AddOnFragmentContract.View mView;
    ProgressDialog progressBar;

    public AddOnFragmentPresenter(AddOnFragmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadAlacarte(Context context, String compId, String customerId) {


        if(NetCheck.isInternetConnection(context)){
            NetworkUtils.getUserApiInstance()
                    .getAlacarteList(compId, customerId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AlacarteModel>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(AlacarteModel alacarteModel) {
                            mView.showAlacarte(alacarteModel);
                        }
                    });
        }else{

            mView.showError("Connection Error");
        }
    }

}
