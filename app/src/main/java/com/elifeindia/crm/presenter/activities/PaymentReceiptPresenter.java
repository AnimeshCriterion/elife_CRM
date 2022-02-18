package com.elifeindia.crm.presenter.activities;


import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.PaymentReceiptContract;
import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.HeaderModel;
import com.elifeindia.crm.model.PaymentReciept;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class PaymentReceiptPresenter implements PaymentReceiptContract.Presenter {
    PaymentReceiptContract.View mView;

    public PaymentReceiptPresenter(PaymentReceiptContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadPaymentReceipt(Context context, String paymentId) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getPaymentReciept(paymentId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PaymentReciept>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                            progressBar.dismiss();
                        }

                        @Override
                        public void onNext(PaymentReciept paymentReciept) {
                            mView.showReceipt(paymentReciept);
                            progressBar.dismiss();
                        }
                    });
        }else {
            mView.showError("Connection Error");
            progressBar.dismiss();
        }
    }

    @Override
    public void loadHeader(Context context, String compId) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getHeader(compId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<HeaderModel>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                            progressBar.dismiss();
                        }

                        @Override
                        public void onNext(HeaderModel headerModel) {
                            mView.showHeader(headerModel);
                            progressBar.dismiss();
                        }
                    });
        }else {
            mView.showError("Connection Error");
            progressBar.dismiss();
        }
    }

    @Override
    public void loadFooter(Context context, String compId) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        if(NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .getFooter(compId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<FooterModel>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                            progressBar.dismiss();
                        }

                        @Override
                        public void onNext(FooterModel footerModel) {
                            mView.showFooter(footerModel);
                            progressBar.dismiss();
                        }
                    });
        }else {
            mView.showError("Connection Error");
            progressBar.dismiss();
        }
    }


}
