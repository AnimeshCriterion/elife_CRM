package com.elifeindia.crm.presenter.activities;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.elifeindia.crm.contract.activities.GenerateInvoiceContract;
import com.elifeindia.crm.model.BillTypeModel;
import com.elifeindia.crm.model.BoxTypeModel;
import com.elifeindia.crm.model.CableBoxWithSubscription;
import com.elifeindia.crm.model.CustomerSubscribeList;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.InternetBoxWithSubscription;
import com.elifeindia.crm.model.PaymentTypeList;
import com.elifeindia.crm.model.UpdateBox;
import com.elifeindia.crm.model.generateinvoice.InsertInvoiceModel;
import com.elifeindia.crm.networking.CableBoxSubscription;
import com.elifeindia.crm.networking.InternetBoxUpdateSubscription;
import com.elifeindia.crm.networking.NetworkUtils;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GenerateInvoicePresenter implements GenerateInvoiceContract.Presenter {
    GenerateInvoiceContract.View mView;

    public GenerateInvoicePresenter(GenerateInvoiceContract.View mView) {
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
    public void loadCableBoxListApi(String Customer_ID) {

        NetworkUtils.getUserApiInstance()
                .getCableBoxwithSubscriptionList(Customer_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CableBoxWithSubscription>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(CableBoxWithSubscription cableBoxWithSubscription) {
                        mView.showCableBoxList(cableBoxWithSubscription);
                    }
                });
    }

    @Override
    public void loadInternetBoxListApi(String Customer_ID) {
        NetworkUtils.getUserApiInstance()
                .getInternetBoxwithSubscriptionList(Customer_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InternetBoxWithSubscription>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(InternetBoxWithSubscription internetBoxWithSubscription) {
                        mView.showInernetBoxList(internetBoxWithSubscription);
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
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(PaymentTypeList boxBouquetList) {
                        mView.showPaymentTypeList(boxBouquetList);
                    }
                });
    }

    @Override
    public void updateCableBox(Context context, final TextView view, final TextView view1, String user_Id, String customer_Id, String cable_Box_ID, String box_ID, String boxType_ID, String vcno, String stbno, String cafno, String bill_Type_ID, String connection_Status_ID, String activation_Date, String expiry_Date, String noofMonth, String noofDays, String alacarte_Amount, String bouquet_Amount, String tax_Amount, String box_Amount, String date) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        Log.d("TAG", "updateCableBox: "+box_Amount);
        CableBoxSubscription cableBoxSubscription=new CableBoxSubscription();
        cableBoxSubscription.setUserId(Integer.valueOf(user_Id));
        cableBoxSubscription.setCustomerId(Integer.valueOf(customer_Id));
        cableBoxSubscription.setCableBoxID(Integer.valueOf(cable_Box_ID));
        cableBoxSubscription.setBoxID(0);
        cableBoxSubscription.setVcno(vcno);
        cableBoxSubscription.setStbno(stbno);
        cableBoxSubscription.setiPtv("");
        cableBoxSubscription.setCafno(cafno);
        cableBoxSubscription.setBillTypeID(Integer.valueOf(bill_Type_ID));
        cableBoxSubscription.setConnectionStatusID(Integer.valueOf(connection_Status_ID));
        cableBoxSubscription.setActivationDate(activation_Date);
        cableBoxSubscription.setExpiryDate(expiry_Date);
        cableBoxSubscription.setNoofMonth(Integer.valueOf(noofMonth));
        cableBoxSubscription.setNoofDays(0);
        cableBoxSubscription.setBoxAmount(Double.valueOf(box_Amount));
//        cableBoxSubscription.setAlacarteAmount(Integer.valueOf(alacarte_Amount));
//        cableBoxSubscription.setBouquetAmount(Integer.valueOf(bouquet_Amount));
//        cableBoxSubscription.setTaxAmount(Integer.valueOf(tax_Amount));
        cableBoxSubscription.setBoxAmount(Double.valueOf(box_Amount));
        cableBoxSubscription.setDate(date);
        //cableBoxSubscription.setInvoiceID(0);
        NetworkUtils.getUserApiInstance()
                .updateCableBox(cableBoxSubscription)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateBox>() {
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
                    public void onNext(UpdateBox boxBouquetList) {
                        mView.showCableBox(boxBouquetList, view, view1);
                    }
                });
    }

    @Override
    public void updateCableBoxSubscription(Context context,  TextView view, TextView view1,String user_Id, String customer_Id, String cable_Box_ID, String box_ID, String boxType_ID, String vcno, String stbno, String cafno, String bill_Type_ID, String connection_Status_ID, String activation_Date, String expiry_Date, String noofMonth, String noofDays, String alacarte_Amount, String bouquet_Amount, String tax_Amount, String box_Amount, String date) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        CableBoxSubscription cableBoxSubscription=new CableBoxSubscription();
        cableBoxSubscription.setUserId(Integer.valueOf(user_Id));
        cableBoxSubscription.setCustomerId(Integer.valueOf(customer_Id));
        cableBoxSubscription.setCableBoxID(Integer.valueOf(cable_Box_ID));
        cableBoxSubscription.setBoxID(0);
        cableBoxSubscription.setBoxTypeID(Integer.valueOf(boxType_ID));
        cableBoxSubscription.setVcno(vcno);
        cableBoxSubscription.setStbno(stbno);
        cableBoxSubscription.setiPtv("");
        cableBoxSubscription.setCafno(cafno);
        cableBoxSubscription.setBillTypeID(Integer.valueOf(bill_Type_ID));
        cableBoxSubscription.setConnectionStatusID(Integer.valueOf(connection_Status_ID));
        cableBoxSubscription.setActivationDate(activation_Date);
        cableBoxSubscription.setExpiryDate(expiry_Date);
        cableBoxSubscription.setNoofMonth(Integer.valueOf(noofMonth));
        cableBoxSubscription.setNoofDays(0);
//        cableBoxSubscription.setAlacarteAmount(Integer.valueOf(alacarte_Amount));
//        cableBoxSubscription.setBouquetAmount(Integer.valueOf(bouquet_Amount));
//        cableBoxSubscription.setTaxAmount(Integer.valueOf(tax_Amount));
        cableBoxSubscription.setBoxAmount(Double.valueOf(box_Amount));
        cableBoxSubscription.setDate(date);
        cableBoxSubscription.setInvoiceID(0);
        NetworkUtils.getUserApiInstance()
                .updateCableBoxUpdateSubscription(cableBoxSubscription)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateBox>() {
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
                    public void onNext(UpdateBox boxBouquetList) {
                        mView.showCableBox(boxBouquetList, view, view1);
                      //  mView.showCableBox(boxBouquetList, view, view1);
                    }
                });
    }

    @Override
    public void updateInternetBox(Context context, final TextView view, final TextView view1, String user_Id, String customer_Id, String internet_Box_ID, String box_ID, String boxType_ID, String ip, String mac, String bill_Type_ID, String connection_Status_ID, String activation_Date, String expiry_Date, String noofMonth, String noofDays, String package_Amount, String tax_Amount, String box_Amount, String date) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        InternetBoxUpdateSubscription internetBoxUpdateSubscription=new InternetBoxUpdateSubscription();
        internetBoxUpdateSubscription.setUserId(Integer.valueOf(user_Id));
        internetBoxUpdateSubscription.setCustomerId(Integer.valueOf(customer_Id));
        internetBoxUpdateSubscription.setInternetBoxID(Integer.valueOf(internet_Box_ID));
        internetBoxUpdateSubscription.setBoxID(0);
        internetBoxUpdateSubscription.setBoxTypeID(2);
        internetBoxUpdateSubscription.setIp(ip);
        internetBoxUpdateSubscription.setBoxAmount(Double.valueOf(box_Amount));
        internetBoxUpdateSubscription.setMac(mac);
        internetBoxUpdateSubscription.setBillTypeID(Integer.valueOf(bill_Type_ID));
        internetBoxUpdateSubscription.setConnectionStatusID(Integer.valueOf(connection_Status_ID));
        internetBoxUpdateSubscription.setNoofMonth(Integer.valueOf(noofMonth));
        internetBoxUpdateSubscription.setActivationDate(activation_Date);
        internetBoxUpdateSubscription.setExpiryDate(expiry_Date);
        internetBoxUpdateSubscription.setNoofDays(0);
//        internetBoxUpdateSubscription.setPackageAmount(Double.valueOf(package_Amount));
//        internetBoxUpdateSubscription.setTaxAmount(Double.valueOf(tax_Amount));
//        internetBoxUpdateSubscription.setBoxAmount(Double.valueOf(box_Amount));
        internetBoxUpdateSubscription.setDate(date);
        internetBoxUpdateSubscription.setInvoiceID(0);
        NetworkUtils.getUserApiInstance()
                .getInternetExpiry(internetBoxUpdateSubscription)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateBox>() {
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
                    public void onNext(UpdateBox boxBouquetList) {
                        mView.showInternetBox(boxBouquetList, view, view1);
                    }
                });
    }

    @Override
    public void updateInternetBoxForSubscription(Context context, String user_Id, String customer_Id, String internet_Box_ID, String box_ID, String boxType_ID, String ip, String mac, String bill_Type_ID, String connection_Status_ID, String activation_Date, String expiry_Date, String noofMonth, String noofDays, String package_Amount, String tax_Amount, String box_Amount, String date) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        InternetBoxUpdateSubscription internetBoxUpdateSubscription=new InternetBoxUpdateSubscription();
        internetBoxUpdateSubscription.setUserId(Integer.valueOf(user_Id));
        internetBoxUpdateSubscription.setCustomerId(Integer.valueOf(customer_Id));
        internetBoxUpdateSubscription.setInternetBoxID(Integer.valueOf(internet_Box_ID));
        internetBoxUpdateSubscription.setBoxID(0);
        internetBoxUpdateSubscription.setBoxTypeID(Integer.valueOf(boxType_ID));
        internetBoxUpdateSubscription.setIp(ip);
        internetBoxUpdateSubscription.setMac(mac);
        internetBoxUpdateSubscription.setConnectionStatusID(Integer.valueOf(connection_Status_ID));
        internetBoxUpdateSubscription.setActivationDate(activation_Date);
        internetBoxUpdateSubscription.setNoofMonth(Integer.valueOf(noofMonth));
        internetBoxUpdateSubscription.setExpiryDate(expiry_Date);
        internetBoxUpdateSubscription.setNoofDays(Integer.valueOf(noofDays));
        internetBoxUpdateSubscription.setPackageAmount(Double.valueOf(package_Amount));
        internetBoxUpdateSubscription.setTaxAmount(Double.valueOf(tax_Amount));
       // internetBoxUpdateSubscription.setBoxAmount(Double.valueOf(box_Amount));
        internetBoxUpdateSubscription.setDate(date);
        NetworkUtils.getUserApiInstance()
                .updateInternetBoxSubscription(internetBoxUpdateSubscription)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateBox>() {
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
                    public void onNext(UpdateBox boxBouquetList) {
                        mView.showInternetBox(boxBouquetList, null, null);

                    }
                });
    }


    @Override
    public void getBillType(String ID) {
        NetworkUtils.getUserApiInstance()
                .getBillType(ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BillTypeModel>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BillTypeModel billTypeModel) {
                        mView.showBillType(billTypeModel);
                    }
                });
    }

    @Override
    public void getBoxTypeList(String Company_ID, String ID) {
        NetworkUtils.getUserApiInstance()
                .getBoxTypeList(Company_ID, ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BoxTypeModel>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BoxTypeModel boxTypeModel) {
                        mView.showBoxTypeList(boxTypeModel);
                    }
                });
    }

    @Override
    public void generate_Invoice(Context context, String user_Id, String customer_Id, String triple_play_ID, String date, String triple_play, String paid_Amount, String employee_ID, String paymentType_ID, String payment_Date, String transaction_No) {

        NetworkUtils.getUserApiInstance()
                .generate_Invoice(user_Id, customer_Id, triple_play_ID, date, triple_play, paid_Amount, employee_ID, paymentType_ID, payment_Date,  transaction_No)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InsertPayment>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(InsertPayment insertPayment) {
                        mView.generate_Invoice(insertPayment);
                    }
                });
    }

    @Override
    public void insertInvoice(Context context, JsonObject jsonObject) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        NetworkUtils.getUserApiInstance()
                .insertInvoice(jsonObject)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InsertPayment>() {
                    @Override
                    public void onCompleted() {
                        progressBar.dismiss();

                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                        e.printStackTrace();
                        progressBar.dismiss();
                    }

                    @Override
                    public void onNext(InsertPayment insertPayment) {
                        mView.insertInvoice(insertPayment);
                    }
                });
    }

    @Override
    public void getInvoice(Context context, String invId){

        NetworkUtils.getUserApiInstance()
                .getInvoice(invId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetInvoiceModel>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(GetInvoiceModel getInvoiceModel) {
                        mView.showInvoice(getInvoiceModel);
                    }
                });
    }

    @Override
    public void insertInvoice2(Context context, InsertInvoiceModel model) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        NetworkUtils.getUserApiInstance()
                .insertInvoice2(model)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InsertPayment>() {
                    @Override
                    public void onCompleted() {
                        progressBar.dismiss();

                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                        e.printStackTrace();
                        progressBar.dismiss();
                    }

                    @Override
                    public void onNext(InsertPayment insertPayment) {
                        mView.insertInvoice(insertPayment);
                    }
                });


    }

}
