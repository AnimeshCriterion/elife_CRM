package com.elifeindia.crm.presenter.fragment;


import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.AddOnFragmentContract;
import com.elifeindia.crm.contract.activities.PackageListFragContract;
import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.Login;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class PakcageListFragPresenter implements PackageListFragContract.Presenter {
    PackageListFragContract.View mView;
    ProgressDialog progressBar;

    public PakcageListFragPresenter(PackageListFragContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }



    @Override
    public void loadBouquet(Context context, String compId, String customerId) {

        if(NetCheck.isInternetConnection(context)){
            NetworkUtils.getUserApiInstance()
                    .getBouquetList(compId, customerId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BouquetModel>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onNext(BouquetModel bouquetModel) {
                            mView.showBouquet(bouquetModel);

                        }
                    });
        }else{

            mView.showError("Connection Error");
        }

    }


}
