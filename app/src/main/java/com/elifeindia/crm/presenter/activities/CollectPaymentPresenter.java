package com.elifeindia.crm.presenter.activities;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.elifeindia.crm.contract.activities.CollectPaymentContract;
import com.elifeindia.crm.contract.activities.SubscriptionDetailsContract;
import com.elifeindia.crm.model.BoxAlacarteList;
import com.elifeindia.crm.model.BoxBouquetList;
import com.elifeindia.crm.model.CustomerInvoice;
import com.elifeindia.crm.model.CustomerSubscribeList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.InternetSubscriptionDetails;
import com.elifeindia.crm.model.PaymentTypeList;
import com.elifeindia.crm.networking.NetworkUtils;

import org.json.JSONObject;

import retrofit2.http.Field;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CollectPaymentPresenter implements CollectPaymentContract.Presenter {
    CollectPaymentContract.View mView;

    public CollectPaymentPresenter(CollectPaymentContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadCustomerSubscribeList(String Customer_ID) {
        NetworkUtils.getUserApiInstance()
                .getCustomerSubscribeList(Customer_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CustomerSubscribeList>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(CustomerSubscribeList customerSubscribeList) {
                        mView.showCustomerSubscribeList(customerSubscribeList);
                    }
                });
    }

    @Override
    public void loadPaymentTypeList(String Company_ID) {
        NetworkUtils.getUserApiInstance()
                .getPaymentTypeList(Company_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PaymentTypeList>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(PaymentTypeList boxBouquetList) {
                        mView.showPaymentTypeList(boxBouquetList);
                    }
                });
    }

    @Override
    public void loadInsertPayment(String Customer_ID, String paymentType_Id, String invoice_ID, String date, String total_amount, String paid_Amount, String balance, String transaction_No, String company_ID, String user_ID) {
        NetworkUtils.getUserApiInstance()
                .insertPayment(Customer_ID, paymentType_Id, invoice_ID, date, total_amount, paid_Amount, balance, transaction_No, company_ID, user_ID)
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
                    public void onNext(InsertPayment insertPayment) {
                        mView.showInsertPayment(insertPayment);
                    }
                });
    }

    @Override
    public void loadCustomerInvoice(String custId, String triplePlayId) {
        NetworkUtils.getUserApiInstance()
                .getCustomerInvoice(custId, triplePlayId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CustomerInvoice>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(CustomerInvoice customerInvoice) {
                        mView.showCustomerInvoice(customerInvoice);
                    }
                });
    }




}
