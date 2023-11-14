package com.elifeindia.crm.contract.activities;


import android.content.Context;
import android.widget.TextView;

import com.elifeindia.crm.model.BillTypeModel;
import com.elifeindia.crm.model.BoxAlacarteList;
import com.elifeindia.crm.model.BoxBouquetList;
import com.elifeindia.crm.model.BoxTypeModel;
import com.elifeindia.crm.model.CableBoxWithSubscription;
import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustomerSubscribeList;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.InternetBoxWithSubscription;
import com.elifeindia.crm.model.InternetSubscriptionDetails;
import com.elifeindia.crm.model.Login;
import com.elifeindia.crm.model.PaymentTypeList;
import com.elifeindia.crm.model.UpdateBox;
import com.elifeindia.crm.model.generateinvoice.InsertInvoiceModel;
import com.elifeindia.crm.view.activities.GenerateInvoiceActivity;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.w3c.dom.Text;

import retrofit2.http.Field;

public interface GenerateInvoiceContract {
    interface View {
        void init();

        void showError(String message);
        void showCustomerSubscribeList(CustomerSubscribeList customerSubscribeList);

        void showPaymentTypeList(PaymentTypeList paymentTypeList);

        void showCableBoxList(CableBoxWithSubscription cableBoxWithSubscription);
        void showInernetBoxList(InternetBoxWithSubscription customersInternetBoxData);
        void showBillType(BillTypeModel billTypeModel);
        void showBoxTypeList(BoxTypeModel boxTypeModel);
        void showCableBox(UpdateBox boxTypeModel, TextView textView, TextView textView2);
        void showInternetBox(UpdateBox boxTypeModel, TextView textView, TextView textView2);
        void generate_Invoice(InsertPayment insertPayment);
        void insertInvoice(InsertPayment insertPayment);

        void showInvoice(GetInvoiceModel getInvoiceModel);



    }

    interface Presenter {
        void start();
        void loadCustomerSubscribeList(String Customer_ID);
        void loadCableBoxListApi(String Customer_ID);
        void loadInternetBoxListApi(String Customer_ID);
        void getBillType(String ID);
        void getBoxTypeList(String Company_ID, String ID);
        void loadPaymentTypeList(String Company_ID);

        void updateCableBox(Context context, TextView view, TextView view1, String user_Id, String customer_Id,
                            String cable_Box_ID, String box_ID, String boxType_ID, String vcno,
                            String stbno, String cafno, String bill_Type_ID, String connection_Status_ID,
                            String activation_Date, String expiry_Date, String noofMonth,
                            String noofDays, String alacarte_Amount, String bouquet_Amount,
                            String tax_Amount, String box_Amount, String date);

        void updateCableBoxSubscription(Context context, TextView view, TextView view1, String user_Id, String customer_Id,
                            String cable_Box_ID, String box_ID, String boxType_ID, String vcno,
                            String stbno, String cafno, String bill_Type_ID, String connection_Status_ID,
                            String activation_Date, String expiry_Date, String noofMonth,
                            String noofDays, String alacarte_Amount, String bouquet_Amount,
                            String tax_Amount, String box_Amount, String date);



        void updateInternetBox(Context context, TextView view, TextView view1, String user_Id, String customer_Id,
                               String internet_Box_ID, String box_ID, String boxType_ID,
                               String ip, String mac, String bill_Type_ID, String connection_Status_ID,
                               String activation_Date, String expiry_Date, String noofMonth, String noofDays,
                               String package_Amount, String tax_Amount, String box_Amount, String date);

        void updateInternetBoxForSubscription(Context context, String user_Id, String customer_Id,
                                              String internet_Box_ID, String box_ID, String boxType_ID,
                                              String ip, String mac, String bill_Type_ID, String connection_Status_ID,
                                              String activation_Date, String expiry_Date, String noofMonth, String noofDays,
                                              String package_Amount, String tax_Amount, String box_Amount, String date);



        void generate_Invoice(Context context, String user_Id, String customer_Id, String triple_play_ID, String date,
                              String triple_play, String paid_Amount, String employee_ID, String paymentType_ID,
                              String payment_Date, String transaction_No);

        void insertInvoice(Context context, JsonObject jsonObject);
        void getInvoice(Context context, String invId);
        void insertInvoice2(Context context, InsertInvoiceModel model);


      }


}
