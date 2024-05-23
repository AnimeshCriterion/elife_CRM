package com.elifeindia.crm.view.fragment;

import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.getInvoiceModelInvoice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.elifeindia.crm.R;
import com.elifeindia.crm.contract.activities.CollectPaymentContract;
import com.elifeindia.crm.contract.activities.CustomerDetailsContract;
import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomerInvoice;
import com.elifeindia.crm.model.CustomerSubscribeList;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.PaymentTypeList;
import com.elifeindia.crm.presenter.activities.CollectPaymentPresenter;
import com.elifeindia.crm.presenter.activities.CustomerDetailsPresenter;
import com.elifeindia.crm.printersdk.PaymentReceiptReprentingActivity;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.printersdk.PaymentReceiptActivity;
import com.elifeindia.crm.view.activities.BillShareActivity;
import com.elifeindia.crm.view.activities.CustomersDetailsActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class CollectPaymentFragment extends Fragment implements CollectPaymentContract.View,CustomerDetailsContract.View {
    CollectPaymentContract.Presenter presenter;
    ViewUtils viewUtils; LinearLayout ll_pay_for;
    String invoiceId, pTypeId, tripleId;
    CustomerDetailsContract.Presenter custommerPresenter;
    public static String triplePlay;
    Spinner spn_paymentmode, spn_triple_pay;
    ArrayAdapter<String> adapter_state_adj1, adapter_state_adj2;
    EditText edt_payingamount, discount_editext;
    TextView customer_name, custmer_address, subs_amount, total_amount, balance_amount, today_date;
    float ans = 0;
    String customerWhatsappNo;
    String balance="", InvType;
    RelativeLayout tool_bar;
    private  LinearLayout discount;
    private  EditText commentbox;

    public CollectPaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.activity_collect_payment, container, false);

        ll_pay_for = v.findViewById(R.id.ll_pay_for);
        spn_triple_pay = v.findViewById(R.id.spn_triple_pay);
        spn_paymentmode = v.findViewById(R.id.spn_paymentmode);
        edt_payingamount = v.findViewById(R.id.edt_payingamount);
        customer_name = v.findViewById(R.id.customer_name);
        custmer_address = v.findViewById(R.id.custmer_address);
        subs_amount = v.findViewById(R.id.subs_amount);
        discount=v.findViewById(R.id.discount);
        commentbox=v.findViewById(R.id.commentbox);
        total_amount = v.findViewById(R.id.total_amount);
        balance_amount = v.findViewById(R.id.balance_amount);
        today_date = v.findViewById(R.id.today_date);
        discount_editext = v.findViewById(R.id.discount_editext);
        tool_bar = v.findViewById(R.id.tool_bar);
        tool_bar.setVisibility(View.GONE);

        custommerPresenter = new CustomerDetailsPresenter(this);
        custommerPresenter.start();

        InvType = SharedPrefsData.getString(getActivity(), Constants.InvoiceType, Constants.PREF_NAME);

        if (SharedPrefsData.getString(requireActivity(), Constants.RoleType, Constants.PREF_NAME).equals("Admin") || SharedPrefsData.getString(requireActivity(), Constants.RoleType, Constants.PREF_NAME).equals("Manager")) {
            discount.setVisibility(View.VISIBLE);
        }
        else {
            discount.setVisibility(View.GONE);
        }


        edt_payingamount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String b = edt_payingamount.getText().toString();
                String a = total_amount.getText().toString();
                String c = discount_editext.getText().toString();

                if(c.equals(""))
                    c="0";
                if(edt_payingamount.getText().toString().equals("")){
                    balance_amount.setText(balance);
                }else {
                    try {
                        ans =(Float.parseFloat(a) - Float.parseFloat(b) + Float.parseFloat(c));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    balance_amount.setText(String.valueOf(ans));
                    SharedPrefsData.putString(getActivity(), Constants.CustomerBalance, String.valueOf(ans), Constants.PREF_NAME);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        discount_editext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String b = discount_editext.getText().toString();
                String a = SharedPrefsData.getString(getActivity(), Constants.CustomerBalance, Constants.PREF_NAME);



                if(discount_editext.getText().toString().equals("")){
                    balance_amount.setText(total_amount.getText().toString());
                }else {
                    float ans = 0;
                    try {
                        ans = (Float.parseFloat(a) - Float.parseFloat(b));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    balance_amount.setText(String.valueOf(ans));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        customer_name.setText(SharedPrefsData.getString(getActivity(), Constants.CustomerName, Constants.PREF_NAME));
        custmer_address.setText(SharedPrefsData.getString(getActivity(), Constants.CustomerAddress, Constants.PREF_NAME));

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.US);
        Date date = new Date();
        String fdate = sdf1.format(date);
        today_date.setText(fdate);

        viewUtils = new ViewUtils();
        presenter = new CollectPaymentPresenter(this);
        presenter.start();

        presenter.loadCustomerSubscribeList(SharedPrefsData.getString(getActivity(), Constants.CustomerID, Constants.PREF_NAME));
        presenter.loadPaymentTypeList(SharedPrefsData.getString(getActivity(), Constants.CompanyID, Constants.PREF_NAME));

        v.findViewById(R.id.btn_insert_payment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(edt_payingamount.length()==0){
                    edt_payingamount.setError("Enter the paying amount");
                    edt_payingamount.requestFocus();
                }
                else if(discount_editext.length()==0){
                    discount_editext.setError("Enter the Discount amount");
                    discount_editext.requestFocus();
                }
                else {
                    AlertDialog.Builder alertDialog;
                    alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setCancelable(false);//you can cancel it by pressing back button
                    alertDialog.setTitle("Payment Confirmation");
                    alertDialog.setMessage("Are you sure you want to collect this amount?");

                    alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String customer_ID = SharedPrefsData.getString(getActivity(), Constants.CustomerID, Constants.PREF_NAME);
                            String company_ID = SharedPrefsData.getString(getActivity(), Constants.CompanyID, Constants.PREF_NAME);
                            String user_ID = SharedPrefsData.getString(getActivity(), Constants.UserId, Constants.PREF_NAME);

                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                            Date date = new Date();
                            String fdate = sdf1.format(date);

                            presenter.loadInsertPayment( customer_ID, pTypeId, invoiceId,
                                    fdate, total_amount.getText().toString(), edt_payingamount.getText().toString(),
                                    balance_amount.getText().toString(), commentbox.getText().toString().trim(), company_ID, user_ID,discount_editext.getText().toString());

                        }
                    });
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();


                }

            }
        });

        if(InvType.equals("Collective")) {

            ll_pay_for.setVisibility(View.INVISIBLE);

        }else{
            ll_pay_for.setVisibility(View.VISIBLE);

        }

        return v;
    }

    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {
        viewUtils.toast(getActivity(), message);
        startActivity(new Intent(getActivity(), PaymentReceiptActivity.class));


    }

    @Override
    public void showResult(CustomerData customerData) {

    }

    @Override
    public void showCableBoxList(CustemersCableBoxData custemersCableBoxData) {

    }

    @Override
    public void showInernetBoxList(CustomersInternetBoxData customersInternetBoxData) {

    }

    @Override
    public void showInvoice(GetInvoiceModel getInvoiceModel) {
        Log.d("TAG", "showInvoice1: ");
        getInvoiceModelInvoice = getInvoiceModel;
//        SharedPrefsData.putString(this, Constants.AccNo, String.valueOf(getInvoiceModel.getAccount_No()), Constants.PREF_NAME);
//        triplePlay = SharedPrefsData.getString(CustomersDetailsActivity.this, Constants.TriplePlay, Constants.PREF_NAME);
//        if (triplePlay.equals("Cable")) {
//            SharedPrefsData.putString(this, Constants.TotalAmount, cableBoxWithSubscription.geTotal_CableBox_amount(), Constants.PREF_NAME);
//        } else if (triplePlay.equals("Internet")) {
//            SharedPrefsData.putString(this, Constants.TotalAmount, internetBoxWithSubscription.getTotal_InternetBox_amount(), Constants.PREF_NAME);
//            //SharedPrefsData.putString(this, Constants.TotalAmount, String.valueOf(internetBoxList.get(box_position).getInternetBox().getBox_Amount()), Constants.PREF_NAME);
//        }
//        SharedPrefsData.putString(this, Constants.InvoiceNo, String.valueOf(getInvoiceModel.getInvoice_Number()), Constants.PREF_NAME);
//

        Log.d("TAG", "showInvoice2: ");
        //getInvoiceModel.getArea_Customer_ID();
        //getInvoiceModel.getAreaName();
        SharedPrefsData.putString(getActivity(), Constants.CustomerBalance, String.valueOf(getInvoiceModel.getBalance()), Constants.PREF_NAME);
        getInvoiceModel.getCustomer_ID();
        getInvoiceModel.getDiscount();
        getInvoiceModel.getInv_Amount();
        getInvoiceModel.getInvoice_Date();
        SharedPrefsData.putString(getActivity(), Constants.InvoiceDate, getInvoiceModel.getInvoice_Date().toString(), Constants.PREF_NAME);
        //getInvoiceModel.getInvoice_ID();
        //SharedPrefsData.putString(this, Constants.InvoiceNo, getInvoiceModel.get().toString(), Constants.PREF_NAME);
        SharedPrefsData.putString(getActivity(), Constants.CustomerName, getInvoiceModel.getName().toString(), Constants.PREF_NAME);
        try {
            SharedPrefsData.putString(getActivity(), Constants.PrevBal, String.valueOf(getInvoiceModel.getPrevious_Balance()), Constants.PREF_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "showInvoice3: "+e.toString());
        }
//        SharedPrefsData.putString(getActivity(), Constants.SubId, getInvoiceModel.getSubscriber_ID().toString(), Constants.PREF_NAME);
//        getInvoiceModel.getTitle();
//        getInvoiceModel.getTriple_play_ID();
//        startActivity(new Intent(getActivity(), BillShareActivity.class));


        Intent intent = new Intent(getContext(), PaymentReceiptReprentingActivity.class);
//        intent.putExtra("BillType",getInvoiceModel.getPaymentType().toString());
        intent.putExtra("WhatsappNo",requireActivity().getIntent().getStringExtra("WhatsappNo"));
        intent.putExtra("activity","CollectPayment1");
//        intent.putExtra("ContactNo", dataSend.getContactNo());
//        String address = paymentReciepts.get(position).getAddress().toString();
        // SharedPrefsData.putString(context, Constants.CustomerAddress, address, Constants.PREF_NAME);
        // SharedPrefsData.putString(context, Constants.CustomerID, paymentReciepts.get(position).getCustomerID().toString(), Constants.PREF_NAME);
        startActivity(intent);

    }

    @Override
    public void showCustomerSubscribeList(CustomerSubscribeList customerSubscribeList) {
        final List<CustomerSubscribeList.CusSubscribeType> cusSubscribeTypeList = customerSubscribeList.getCusSubscribeType();



        ArrayList statesList = new ArrayList();
        for (int i = 0; i < cusSubscribeTypeList.size(); i++) {
            statesList.add(cusSubscribeTypeList.get(i).getTriplePlay());
        }

        adapter_state_adj1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_state_adj1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_triple_pay.setAdapter(adapter_state_adj1);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tripleId = cusSubscribeTypeList.get(position).getTriplePlayID().toString();
                        triplePlay = cusSubscribeTypeList.get(position).getTriplePlay().toString();

                        SharedPrefsData.putString(getActivity(), Constants.TriplePlayId, tripleId, Constants.PREF_NAME);

                        if(InvType.equals("Collective")) {
                            tripleId = "";
                        }
                        presenter.loadCustomerInvoice(SharedPrefsData.getString(getActivity(), Constants.CustomerID, Constants.PREF_NAME), tripleId);


                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                };
        spn_triple_pay.setOnItemSelectedListener(onItemSelectedListener2);

    }

    @Override
    public void showPaymentTypeList(PaymentTypeList paymentTypeList) {

        final List<PaymentTypeList.PaymentType> paymentTypes = paymentTypeList.getPaymentType();

        ArrayList statesList = new ArrayList();
        for (int i = 0; i < paymentTypes.size(); i++) {
            statesList.add(paymentTypes.get(i).getPaymentType());
        }

        adapter_state_adj2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_state_adj2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_paymentmode.setAdapter(adapter_state_adj2);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        pTypeId = paymentTypes.get(position).getPaymentTypeID().toString();
                        SharedPrefsData.putString(getActivity(), Constants.PaymentTypeId, pTypeId, Constants.PREF_NAME);

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                };
        spn_paymentmode.setOnItemSelectedListener(onItemSelectedListener2);



    }

    @Override
    public void showCustomerInvoice(CustomerInvoice customerInvoice) {
        Log.d("TAG", "showCustomerInvoice: "+customerInvoice.getCustomerID());
        SharedPrefsData.putString(getContext(), Constants.CustomerID,customerInvoice.getCustomerID().toString(), Constants.PREF_NAME);
        if(customerInvoice.getInvoiceID() != null){
            balance = customerInvoice.getBalance().toString();
            invoiceId = customerInvoice.getInvoiceID().toString();
            subs_amount.setText(customerInvoice.getAmount().toString());
            total_amount.setText(balance);
            balance_amount.setText(balance);

        }else{
            Toast.makeText(getActivity(), "Your Invoice is not generated", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void showInsertPayment(InsertPayment insertPayment) {

        String paymentId = insertPayment.id;
        SharedPrefsData.putString(getActivity(), Constants.PaymentId, paymentId, Constants.PREF_NAME);
        viewUtils.toast(getActivity(), "Payment inserted successfully");
       custommerPresenter.getInvoice(getContext(), String.valueOf(invoiceId));
//        Intent intent=new Intent(getActivity(), BillShareActivity.class);
//        intent.putExtra("paymentId",paymentId);
//        startActivity(intent);

    }
}
