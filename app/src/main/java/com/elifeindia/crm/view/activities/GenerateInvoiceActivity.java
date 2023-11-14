package com.elifeindia.crm.view.activities;

import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.MyViewHolder.amount;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.activation_Date;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.bill_Type_ID;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.boxType_ID;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.box_Amount;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.cable_Box_ID;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.cafno;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.connection_Status_ID;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.expiry_Date;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.noofDays;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.noofMonth;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.stbno;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.strBillType;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.tax_Amount;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.vcno;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.MyViewHolder.amountInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.activation_DateInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.bill_Type_IDInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.boxType_IDInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.box_AmountInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.connection_Status_IDInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.expiry_DateInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.internet_Box_ID;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.ipno;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.macadd;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.noofDaysInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.noofMonthInternet;
import static com.elifeindia.crm.view.activities.CustomersDetailsActivity.customerBalance;
import static com.elifeindia.crm.view.activities.UpdateInternetSubscriptionActivity.total_pkg_amount;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.AdapterCallbackTextView;
import com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter;
import com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter;
import com.elifeindia.crm.contract.activities.GenerateInvoiceContract;
import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.BillTypeModel;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.BoxAlacarteList;
import com.elifeindia.crm.model.BoxBouquetList;
import com.elifeindia.crm.model.BoxTypeModel;
import com.elifeindia.crm.model.CableBoxWithSubscription;
import com.elifeindia.crm.model.CustomerSubscribeList;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.InternetBoxWithSubscription;
import com.elifeindia.crm.model.InternetPackageModel;
import com.elifeindia.crm.model.InternetSubscriptionDetails;
import com.elifeindia.crm.model.PaymentTypeList;
import com.elifeindia.crm.model.UpdateBox;
import com.elifeindia.crm.presenter.activities.GenerateInvoicePresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GenerateInvoiceActivity extends AppCompatActivity implements GenerateInvoiceContract.View, AdapterCallbackTextView, AdapterCallback {

    private LinearLayout ll_noofdays;
    public static CableBoxWithSubscription cableBoxWithSubscription;
    public static InternetBoxWithSubscription internetBoxWithSubscription;

    public static GetInvoiceModel getInvoiceModelInvoice = new GetInvoiceModel();

    public static InternetBoxWithSubscription getInternetBoxModel = new InternetBoxWithSubscription();

    public static List<BouquetModel.BouquetDTO> bouquetModelBouquet;
    public static List<AlacarteModel.AlacarteDTO> alacarteModelAlacarte;
    public static List<InternetPackageModel.PackagesDTO> internetPackageModelInternet;

    public static BouquetModel bouquetModela;
    public static AlacarteModel alacarteModela;
    public static InternetPackageModel internetPackageModela;


    public static String paidAmount = "";
    int noofMonth_multiplyfactor = 1;
    int noofMonth_multiplyfactorInternet = 1;

    public static int box_position;
    GenerateInvoiceContract.Presenter presenter;
    CableBoxDetailsAdapter cableBoxDetailsAdapter;
    InternetBoxDetailsAdapter internetBoxDetailsAdapter;
    Spinner spn_paymentmode, spn_triple_pay, spn_bill_type, spn_no_of_months;
    RecyclerView rv_box_details;
    ArrayAdapter<String> adapter_state_adj1, adapter_state_adj2, adapter_state_adj3, adapter_state_adj4;
    String strCableExpiry = "", strCableBoxAmnt = "", companyID, customerID, empId, userId, InvType, strNoMonths, pTypeId, tripleId, triplePlay, strActivationDate = "", strExpiryDate = "DD MM YYYY";
    AdapterCallbackTextView adapterCallback;
    AdapterCallback adapterCall;
    String strApiCallType = "";
    public static float ans, totalAmnt;
    Button btn_generate_invoice;
    public static boolean flag_view_hide = false;
    public static TextView txt_final_amnt, txt_new_bal, txt_total_amount, txt_subscription_amnt, txt_activation_date, txt_expiry_date;
    public static EditText edt_no_of_days, edt_payingamount, edt_discount;
    String[] no_of_months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    List<BillTypeModel.BillTypeDTO> bill_type;
    public static EditText txt_prev_bal;
    List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO> cableBoxList;
    List<InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO> internetBoxList;

    List<BoxAlacarteList.BoxAlacarete> boxAlacaretes;
    List<BoxBouquetList.BoxBouquet> boxBouquets;
    List<InternetSubscriptionDetails.Boxpackage> boxpackages;
    Calendar mcurrentDate;
    String CustomerBalance, cableBoxAmnt, internetBoxAmnt;
    ArrayList statesList = new ArrayList();
    int count = 1, countInternet = 1;

    ArrayList<String> billTypeName;
    ArrayList<String> billID;

    JsonObject json = null;

    public static float newBoxAmount;

    public static String newTotalAmount;

    public static String newInternetActDate;
    public static String newInternetExpDate;
    private TextView txt_activation_date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_invoice);
        customerID = SharedPrefsData.getString(this, Constants.CustomerID, Constants.PREF_NAME);
        companyID = SharedPrefsData.getString(this, Constants.CompanyID, Constants.PREF_NAME);

        adapterCallback = this;
        adapterCall = this;

        presenter = new GenerateInvoicePresenter(this);
        presenter.start();
        presenter.loadCustomerSubscribeList(customerID);
        presenter.loadPaymentTypeList(companyID);
        presenter.getBillType("");
        presenter.loadCableBoxListApi(customerID);




        InvType = SharedPrefsData.getString(this, Constants.InvoiceType, Constants.PREF_NAME);

        if (InvType.equals("Collective")) {
            spn_triple_pay.setVisibility(View.INVISIBLE);

        } else {
            spn_triple_pay.setVisibility(View.VISIBLE);
        }


    txt_prev_bal.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String b = null;
            String a = null;
            try {
                b = txt_prev_bal.getText().toString();
                a = txt_subscription_amnt.getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            if(txt_prev_bal.getText().toString().equals("")){
                edt_discount.setText("0");
                edt_payingamount.setText("0");
                txt_new_bal.setText(String.valueOf(txt_subscription_amnt.getText().toString()));
                     }
            else {
                float v = 0;
                try {
                    v = Float.parseFloat(a) + Float.parseFloat(b);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                txt_total_amount.setText(String.format("%.2f", v));
                edt_payingamount.setText("0");
                edt_discount.setText("0");
                txt_new_bal.setText(String.format("%.2f", v));

            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

        if (SharedPrefsData.getString(this, Constants.RoleType, Constants.PREF_NAME).equals("Admin") || SharedPrefsData.getString(this, Constants.RoleType, Constants.PREF_NAME).equals("Manager")) {
            edt_discount.setVisibility(View.VISIBLE);
            txt_new_bal.setEnabled(true);
            txt_prev_bal.setEnabled(true);
        } else {
            edt_discount.setVisibility(View.GONE);
            txt_new_bal.setEnabled(false);
            txt_prev_bal.setEnabled(false);
        }


        edt_payingamount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
                String b = edt_payingamount.getText().toString();
                String a = String.valueOf(txt_total_amount.getText().toString().trim());
                String c = edt_discount.getText().toString();
                if(c.equals(""))
                    c="0";
                if(edt_payingamount.getText().toString().equals("")){
                    txt_new_bal.setText(String.valueOf(txt_total_amount.getText().toString().trim()));
                }else {
                    try {
                        ans =(Float.parseFloat(a) - Float.parseFloat(b) + Float.parseFloat(c));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    txt_new_bal.setText(String.format("%.2f",ans));
                    SharedPrefsData.putString(GenerateInvoiceActivity.this, Constants.CustomerBalance, String.valueOf(ans), Constants.PREF_NAME);

                }

//                String b = edt_payingamount.getText().toString();
//                String a = String.valueOf(totalAmnt);
//                String c = edt_discount.getText().toString();
//
//                txt_final_amnt.setText(b);
//
//                if (c.equals(""))
//                    c = "0";
//
//                if (edt_payingamount.getText().toString().equals("")) {
//                    try {
//                        txt_new_bal.setText(String.valueOf(Float.parseFloat(a)-Float.parseFloat(b)+Float.parseFloat(c)));
//                        txt_final_amnt.setText("0");
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
//
//                } else {
//                    try {
//                   //     ans = (Float.parseFloat(b) - Float.parseFloat(c));
//                        ans = (Float.parseFloat(a)-Float.parseFloat(b)+Float.parseFloat(c));
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
//                    txt_new_bal.setText(String.valueOf(ans));
//                    SharedPrefsData.putString(GenerateInvoiceActivity.this, Constants.CustomerBalance, String.valueOf(ans), Constants.PREF_NAME);
//
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_discount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String b = edt_discount.getText().toString();
                String c =edt_payingamount.getText().toString();
                String a = String.valueOf(totalAmnt);



                if(edt_discount.getText().toString().equals("")){
                  //  txt_new_bal.setText(SharedPrefsData.getString(GenerateInvoiceActivity.this, Constants.CableBoxAmount, Constants.PREF_NAME));
                 //   txt_new_bal.setText(String.valueOf(totalAmnt-Float.valueOf(c)));
                }else {
                    float ans = 0;
                    try {
                        ans = (Float.parseFloat(txt_total_amount.getText().toString()) - Float.parseFloat(c)-Float.parseFloat(b));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    txt_new_bal.setText(String.format("%.2f",(ans)));
                }


//                String b = edt_discount.getText().toString();
//            //   String a = SharedPrefsData.getString(GenerateInvoiceActivity.this, Constants.CustomerBalance, Constants.PREF_NAME);
//                String a = txt_new_bal.getText().toString();
//
//
//                if (edt_discount.getText().toString().equals("")) {
//                    //txt_new_bal.setText("");
//                } else {
//                    float ans = 0;
//                    try {
//                        ans = (Float.parseFloat(a) - Float.parseFloat(b));
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
//                    txt_new_bal.setText(String.valueOf(ans));
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_generate_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txt_prev_bal.getText().toString().equals("")) {
                    Toast.makeText(GenerateInvoiceActivity.this, "previous  balance can not blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                Gson gson = new Gson();
                String jsonStr = null;
                JsonParser parser = new JsonParser();
                if (triplePlay.equals("Cable")) {
                    jsonStr = gson.toJson(cableBoxWithSubscription);
                    json = (JsonObject) parser.parse(jsonStr);
                    JsonArray jsonArray = new JsonArray();
                    json.add("internetBoxwithSubscription", jsonArray);
                    json.remove("nameValuePairs");
                    json.addProperty("company_ID", companyID);
                    json.addProperty("invoice_Date", todayDateString());
                    json.addProperty("tax_Amount", tax_Amount);
                    json.addProperty("amount", box_Amount);
                    json.addProperty("previous_Balance", txt_prev_bal.getText().toString());
                    json.addProperty("discount", edt_discount.getText().toString());

                    json.addProperty("inv_Amount", String.valueOf(Float.parseFloat(cableBoxAmnt) + Float.parseFloat(txt_prev_bal.getText().toString())));
                    json.addProperty("balance", txt_new_bal.getText().toString());
                    json.addProperty("user_Id", userId);
                    json.addProperty("customer_Id", customerID);
                    json.addProperty("triple_play_ID", tripleId);
                    json.addProperty("date", todayDateString());
                    json.addProperty("triple_play", triplePlay);
                    json.addProperty("paid_Amount", edt_payingamount.getText().toString());
                    json.addProperty("employee_ID", empId);
                    json.addProperty("paymentType_ID", pTypeId);
                    json.addProperty("payment_Date", todayDateString());
                    json.addProperty("transaction_No", "null");

                } else if (triplePlay.equals("Internet")) {
                    jsonStr = gson.toJson(internetBoxWithSubscription);

                    //Toast.makeText(GenerateInvoiceActivity.this, "Internet box "+ jsonStr, Toast.LENGTH_SHORT).show();

                    json = (JsonObject) parser.parse(jsonStr);
                    JsonArray jsonArray = new JsonArray();
                    json.add("cableBoxwithSubscription", jsonArray);
                    json.remove("nameValuePairs");
                    json.addProperty("company_ID", companyID);
                    json.addProperty("invoice_Date", todayDateString());
                    json.addProperty("tax_Amount", tax_Amount);
                    json.addProperty("amount", box_AmountInternet);
                    json.addProperty("previous_Balance", txt_prev_bal.getText().toString());
                    json.addProperty("discount", edt_discount.getText().toString());

//                    Float prevBalance = Float.valueOf(txt_prev_bal.getText().toString());
//
//                    Float BoxAmount = Float.valueOf(box_Amount);
//
//                    Float invAmount = BoxAmount + prevBalance;

                    //json.addProperty("inv_Amount", String.valueOf(Float.parseFloat(box_Amount) + Float.parseFloat(txt_prev_bal.getText().toString())));

                    json.addProperty("inv_Amount", txt_total_amount.getText().toString());
                    json.addProperty("balance", txt_new_bal.getText().toString());
                    json.addProperty("user_Id", userId);
                    json.addProperty("customer_Id", customerID);
                    json.addProperty("triple_play_ID", tripleId);
                    json.addProperty("date", todayDateString());
                    json.addProperty("triple_play", triplePlay);
                    json.addProperty("paid_Amount", edt_payingamount.getText().toString());
                    json.addProperty("employee_ID", empId);
                    json.addProperty("paymentType_ID", pTypeId);
                    json.addProperty("payment_Date", todayDateString());
                    json.addProperty("transaction_No", "null");

                }

                //Log.i("json object", json.toString());

                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(GenerateInvoiceActivity.this, R.style.ListRow));
                builder.setIcon(android.R.drawable.ic_dialog_info).setTitle("Generate Invoice Confirmation")
                        .setMessage("Are you sure you want to generate the invoice?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("TAG", "onClickAnimesh: "+json.toString());
                                presenter.insertInvoice(GenerateInvoiceActivity.this, json);

                            }
                        }).setNegativeButton("N0", null)
                        .show();


//                Log.i("invoice details", userId+" "+ customerID+" "+ tripleId+" "+ todayDateString()+ " "+triplePlay+" "+ edt_payingamount.getText().toString()+" "+ empId+ pTypeId+" "+ todayDateString()+" "+ "null");
//                presenter.generate_Invoice(GenerateInvoiceActivity.this, userId, customerID, tripleId, todayDateString(), triplePlay, edt_payingamount.getText().toString(), empId, pTypeId, todayDateString(), "null");


            }
        });


        for (int i = 1; i <= 12; i++) {
            statesList.add(i);
        }
        adapter_state_adj4 = new ArrayAdapter<String>(GenerateInvoiceActivity.this, android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_state_adj4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_no_of_months.setAdapter(adapter_state_adj4);

    }


    @Override
    public void init() {

        spn_triple_pay = findViewById(R.id.spn_triple_pay);
        spn_paymentmode = findViewById(R.id.spn_paymentmode);
        rv_box_details = findViewById(R.id.rv_box_details);
        edt_no_of_days = findViewById(R.id.spn_no_of_days);
        spn_bill_type = findViewById(R.id.spn_bill_type);
        spn_no_of_months = findViewById(R.id.spn_no_of_months);
        txt_activation_date1 = findViewById(R.id.txt_activation_date);
        txt_expiry_date = findViewById(R.id.txt_expiry_date);
        ll_noofdays = findViewById(R.id.ll_noofdays);
        txt_expiry_date.setText(strExpiryDate);
        txt_subscription_amnt = findViewById(R.id.txt_subscription_amnt);
        txt_prev_bal = findViewById(R.id.txt_prev_bal);
        txt_total_amount = findViewById(R.id.txt_total_amount);
        txt_new_bal = findViewById(R.id.txt_new_bal);
        txt_final_amnt = findViewById(R.id.txt_final_amnt);
        edt_payingamount = findViewById(R.id.payingamount);
        edt_discount = findViewById(R.id.edt_discount);
        btn_generate_invoice = findViewById(R.id.btn_generate_invoice);

        CustomerBalance = SharedPrefsData.getString(this, Constants.CustomerBalance, Constants.PREF_NAME);
        userId = SharedPrefsData.getString(this, Constants.UserId, Constants.PREF_NAME);
        empId = SharedPrefsData.getString(this, Constants.EmpId, Constants.PREF_NAME);

        //Me
        Log.e("CustomerBalance", CustomerBalance);
        Log.e("userId", userId);
        Log.e("empId", empId);


        txt_prev_bal.setText(String.valueOf(customerBalance));


        edt_no_of_days.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String no_of_days = charSequence.toString();

                //Me
                Log.e("no_of_days", no_of_days);

                txt_expiry_date.setText(strExpiryDate);

                //Me
                Log.d("strExpiryDate", strExpiryDate);

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void showError(String message) {
        //    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(GenerateInvoiceActivity.this,CustomerListActivity.class));
    }

    @Override
    public void showCustomerSubscribeList(CustomerSubscribeList customerSubscribeList) {
        final List<CustomerSubscribeList.CusSubscribeType> cusSubscribeTypeList = customerSubscribeList.getCusSubscribeType();

        ArrayList statesList = new ArrayList();
        for (int i = 0; i < cusSubscribeTypeList.size(); i++) {
            statesList.add(cusSubscribeTypeList.get(i).getTriplePlay());
        }

        adapter_state_adj1 = new ArrayAdapter<String>(GenerateInvoiceActivity.this, android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_state_adj1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_triple_pay.setAdapter(adapter_state_adj1);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tripleId = cusSubscribeTypeList.get(position).getTriplePlayID().toString();
                        triplePlay = cusSubscribeTypeList.get(position).getTriplePlay().toString();

                        //Me
                        Log.e("tripleId", tripleId);
                        Log.e("triplePlay", triplePlay);

                        if (InvType.equals("Collective")) {
                            presenter.loadCableBoxListApi(customerID);
                            presenter.loadInternetBoxListApi(customerID);

                        } else {
                            if (triplePlay.equals("Cable")) {
                                presenter.loadCableBoxListApi(customerID);

                            } else if (triplePlay.equals("Internet")) {
                                presenter.loadInternetBoxListApi(customerID);

                            }
                            SharedPrefsData.putString(GenerateInvoiceActivity.this, Constants.TriplePlay, triplePlay, Constants.PREF_NAME);

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
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

        adapter_state_adj2 = new ArrayAdapter<String>(GenerateInvoiceActivity.this, android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_state_adj2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_paymentmode.setAdapter(adapter_state_adj2);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        pTypeId = paymentTypes.get(position).getPaymentTypeID().toString();

                        //Me
                        Log.e("pTypeId", pTypeId);

                        SharedPrefsData.putString(GenerateInvoiceActivity.this, Constants.PaymentTypeId, pTypeId, Constants.PREF_NAME);

                        SharedPrefsData.putString(GenerateInvoiceActivity.this, Constants.PaymentMode, spn_paymentmode.getSelectedItem().toString(), Constants.PREF_NAME);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
        spn_paymentmode.setOnItemSelectedListener(onItemSelectedListener2);


    }

    @Override
    public void showCableBoxList(CableBoxWithSubscription cableBoxWithSubscription1) {
        cableBoxWithSubscription = cableBoxWithSubscription1;
        cableBoxList = cableBoxWithSubscription.getCableBoxwithSubscription();
        cableBoxAmnt = cableBoxWithSubscription.geTotal_CableBox_amount();
        newBoxAmount = Float.parseFloat(cableBoxWithSubscription.geTotal_CableBox_amount());
        totalAmnt = Float.parseFloat(cableBoxAmnt) + Float.parseFloat(CustomerBalance);
        txt_subscription_amnt.setText(cableBoxWithSubscription.geTotal_CableBox_amount());
        cableBoxWithSubscription = cableBoxWithSubscription1;
        txt_total_amount.setText("Rs. " + String.valueOf(Math.round(totalAmnt*100)/100));
        edt_payingamount.setText(String.valueOf(totalAmnt));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_box_details.setLayoutManager(mLayoutManager);
        cableBoxDetailsAdapter = new CableBoxDetailsAdapter(GenerateInvoiceActivity.this, cableBoxList, adapterCallback, adapterCall, "B");
        rv_box_details.setAdapter(cableBoxDetailsAdapter);
    }

    @Override
    public void showInernetBoxList(InternetBoxWithSubscription internetBoxWithSubscription1) {
        internetBoxWithSubscription = internetBoxWithSubscription1;
        internetBoxList = internetBoxWithSubscription.getInternetBoxwithSubscription();
        internetBoxAmnt = internetBoxWithSubscription.getTotal_InternetBox_amount();
       total_pkg_amount =internetBoxAmnt;
        Log.d("TAG", "showInernetBoxList1: "+internetBoxAmnt.toString());

        newBoxAmount = Float.parseFloat(internetBoxWithSubscription.getTotal_InternetBox_amount());
        Log.d("TAG", "showInernetBoxList2: "+internetBoxAmnt.toString());

        newTotalAmount = String.valueOf(internetBoxList.get(box_position).getInternetBox().getBox_Amount());
        Log.d("TAG", "showInernetBoxList3: "+internetBoxAmnt.toString());

        totalAmnt = Float.parseFloat(internetBoxAmnt) + Float.parseFloat(CustomerBalance);
        txt_subscription_amnt.setText(internetBoxWithSubscription.getTotal_InternetBox_amount());


        internetBoxWithSubscription = internetBoxWithSubscription1;

        txt_total_amount.setText("Rs. " + String.valueOf(totalAmnt));
        edt_payingamount.setText(String.valueOf(totalAmnt));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_box_details.setLayoutManager(mLayoutManager);
        internetBoxDetailsAdapter = new InternetBoxDetailsAdapter(GenerateInvoiceActivity.this, internetBoxList, adapterCallback, adapterCall);
        rv_box_details.setAdapter(internetBoxDetailsAdapter);

    }


    @Override
    public void showBillType(BillTypeModel billTypeModel) {

        bill_type = billTypeModel.getBillType();

        //ArrayList statesList = new ArrayList();

//        for (int i = 0; i < bill_type.size(); i++) {
//            statesList.add(bill_type.get(i).getBill_Type());
//        }

        billTypeName = new ArrayList<>();
        billID = new ArrayList<String>();

        for (int i = 0; i < bill_type.size(); i++) {
            billTypeName.add(bill_type.get(i).getBill_Type());
            billID.add(bill_type.get(i).getBill_Type_ID().toString());
//            empMobNos.add(employee.get(i).getContactNo().toString());
            //Me
            Log.e("billTypeName", billTypeName.toString());
            Log.e("billID", billID.toString());

        }

        adapter_state_adj3 = new ArrayAdapter<String>(GenerateInvoiceActivity.this, android.R.layout.simple_dropdown_item_1line, billTypeName);
        adapter_state_adj3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_bill_type.setAdapter(adapter_state_adj3);


    }

    @Override
    public void showBoxTypeList(BoxTypeModel boxTypeModel) {

    }

    @Override
    public void showCableBox(UpdateBox boxTypeModel, TextView view, TextView view1) {


        Log.d("TAG", "showCableBoxANNU: "+strApiCallType.toString());

        SharedPrefsData.putString(getApplicationContext(), "ExpiryDate", boxTypeModel.getExpiry_Date(), Constants.PREF_NAME);

        if (strApiCallType.equals("calculateExpiry")) {
            view1.setText(box_Amount);


            float box_Amount = Float.parseFloat(boxTypeModel.getBox_Amount());

            //Toast.makeText(this, "cable box amount"+box_Amount, Toast.LENGTH_SHORT).show();

            cableBoxAmnt = String.valueOf(box_Amount);
            cableBoxList.get(box_position).getCableBox().setBox_Amount(Double.parseDouble(String.valueOf(box_Amount)));
//            float ans = Float.parseFloat(alacarteModela.getTotal_Alacarte()) + Float.parseFloat(bouquetModela.getTotal_Bouquet());
//
//
           //txt_subscription_amnt.setText("Rs. " + boxTypeModel.getBox_Amount().toString());
//            //amount.setText(String.valueOf(box_Amount));
            totalAmnt = (Float.parseFloat(CustomerBalance) + Float.parseFloat(txt_subscription_amnt.getText().toString()));

            txt_total_amount.setText(String.valueOf(totalAmnt));
            edt_payingamount.setText(String.valueOf(totalAmnt));
            expiry_Date = boxTypeModel.getExpiry_Date().substring(0, 10);

            //activation_Date = boxTypeModel.getActivation_Date().substring(0 , 10);

            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));
            view1.setText(ViewUtils.changeDateFormat(boxTypeModel.getActivation_Date()));

        } else if (strApiCallType.equals("strActivationDate")) {
            activation_Date = boxTypeModel.getActivation_Date().substring(0, 10);
            expiry_Date = boxTypeModel.getExpiry_Date().substring(0, 10);
            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));

            //Me
            Log.e("activation_Date_1", activation_Date);
            Log.e("expiry_Date_1", expiry_Date);


        } else if (strApiCallType.equals("spn_bill_type")) {
            activation_Date = boxTypeModel.getActivation_Date().substring(0, 10);
            expiry_Date = boxTypeModel.getExpiry_Date().substring(0, 10);
            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));

            //Me
            Log.e("activation_Date_2", activation_Date);
            Log.e("expiry_Date", expiry_Date);}
else if(strApiCallType.equals("spn_no_of_months")){
            txt_subscription_amnt.setText("Rs. " + boxTypeModel.getBox_Amount());

        }

        else {
            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));
            view1.setText(box_Amount);


            String box_Amount = boxTypeModel.getBox_Amount();

            cableBoxAmnt = box_Amount;

            float anssd = Float.parseFloat(alacarteModela.getTotal_Alacarte()) + Float.parseFloat(bouquetModela.getTotal_Bouquet());

            Log.d("TAG", "onCreateAnimesh: "+noofMonth_multiplyfactorInternet);

             txt_subscription_amnt.setText("Rs. " + boxTypeModel.getBox_Amount());

           // txt_subscription_amnt.setText("Rs. " + anssd);
            cableBoxList.get(box_position).getCableBox().setBox_Amount(Double.parseDouble(box_Amount));

            amount.setText(box_Amount);
            totalAmnt = (Float.parseFloat(CustomerBalance) + Float.parseFloat(box_Amount));

            txt_total_amount.setText(String.valueOf(totalAmnt));
            edt_payingamount.setText(String.valueOf(totalAmnt));


            activation_Date = boxTypeModel.getActivation_Date().substring(0, 10);
            expiry_Date = boxTypeModel.getExpiry_Date().substring(0, 10);

            //Me
            Log.e("activation_Date", activation_Date);
            Log.e("expiry_Date", expiry_Date);
        }

        cableBoxWithSubscription.setTotal_CableBox_amount(boxTypeModel.getBox_Amount());
        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getCableBox().setBox_Amount(Double.valueOf(box_Amount));
        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getCableBox().setActivation_Date(activation_Date);
        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getCableBox().setNoofMonth(Integer.valueOf(noofMonth));
        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getCableBox().setBill_Type_ID(Integer.valueOf(bill_Type_ID));
        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getCableBox().setBill_Type(strBillType);
        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getCableBox().setExpiry_Date((expiry_Date));


    }

    @Override
    public void showInternetBox(UpdateBox boxTypeModel, TextView view, TextView view1) {
        SharedPrefsData.putString(getApplicationContext(), "ExpiryDate", boxTypeModel.getExpiry_Date(), Constants.PREF_NAME);
        expiry_DateInternet = boxTypeModel.getExpiry_Date().substring(0, 10);

        if (strApiCallType.equals("calculateExpiryInternet")) {
            view1.setText(box_AmountInternet);

            float box_AmountInternet = Float.parseFloat(boxTypeModel.getBox_Amount());

            //Toast.makeText(this, "box amount "+box_AmountInternet, Toast.LENGTH_SHORT).show();

            //txt_subscription_amnt.setText("Rs. " + box_AmountInternet);
            internetBoxList.get(box_position).getInternetBox().setBox_Amount(Double.parseDouble(String.valueOf(box_AmountInternet)));
            //amountInternet.setText(String.valueOf(box_AmountInternet));
            Log.d("TAG", "showInternetBox: " + boxTypeModel.getExpiry_Date());
            SharedPrefsData.putString(getApplicationContext(), "ExpiryDate", boxTypeModel.getExpiry_Date(), Constants.PREF_NAME);
            expiry_Date = boxTypeModel.getExpiry_Date().substring(0, 10);
            //activation_Date = boxTypeModel.getActivation_Date().substring(0 ,10);
            newInternetActDate = boxTypeModel.getActivation_Date().substring(0, 10);
            newInternetExpDate = boxTypeModel.getExpiry_Date().substring(0, 10);
            totalAmnt = (Float.parseFloat(CustomerBalance) + Float.parseFloat(txt_subscription_amnt.getText().toString()));
            txt_total_amount.setText(String.valueOf(totalAmnt));
            edt_payingamount.setText(String.valueOf(totalAmnt));
            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));
            view1.setText(ViewUtils.changeDateFormat(boxTypeModel.getActivation_Date()));

            //Me
            Log.e("expiry_Date2", expiry_Date);
            Log.e("newInternetActDate2", newInternetActDate);

        } else if (strApiCallType.equals("strActivationDateInternet")) {
            activation_DateInternet = boxTypeModel.getActivation_Date().substring(0, 10);
            expiry_DateInternet = boxTypeModel.getExpiry_Date().substring(0, 10);
            SharedPrefsData.putString(getApplicationContext(), "ExpiryDate", boxTypeModel.getExpiry_Date(), Constants.PREF_NAME);

            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));
            //Me
            Log.e("activation_Date3", activation_DateInternet);
            Log.e("expiry_Date3", expiry_DateInternet);


        } else {
            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));
            view1.setText(box_AmountInternet);

            box_AmountInternet = boxTypeModel.getBox_Amount();
            txt_subscription_amnt.setText("Rs. " + boxTypeModel.getBox_Amount());
            internetBoxList.get(box_position).getInternetBox().setBox_Amount(Double.parseDouble(box_AmountInternet));
            amountInternet.setText(box_AmountInternet);
            totalAmnt = (Float.parseFloat(CustomerBalance) + Float.parseFloat(box_AmountInternet));
            txt_total_amount.setText(String.valueOf(totalAmnt));
            edt_payingamount.setText(String.valueOf(totalAmnt));
            activation_DateInternet = boxTypeModel.getActivation_Date().substring(0, 10);
            SharedPrefsData.putString(getApplicationContext(), "ExpiryDate", boxTypeModel.getExpiry_Date(), Constants.PREF_NAME);


            //Me
            Log.e("activation_DateInterne4", activation_DateInternet);
            Log.e("expiry_DateInternet4Ani", expiry_DateInternet);
            Log.e("expiry_DateInternet4Ani", boxTypeModel.getExpiry_Date().substring(0, 10).toString());

        }


        //Log.e("strBillType23", strBillType);

        internetBoxWithSubscription.setTotal_InternetBox_amount(boxTypeModel.getBox_Amount());
        internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getInternetBox().setBox_Amount(Double.valueOf(box_AmountInternet));
        internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getInternetBox().setActivation_Date(activation_DateInternet);
        internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getInternetBox().setNoofMonth(Integer.valueOf(noofMonthInternet));
        internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getInternetBox().setBill_Type_ID(Integer.valueOf(bill_Type_IDInternet));
        internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getInternetBox().setBill_Type(strBillType);
        internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getInternetBox().setExpiry_Date((expiry_DateInternet));


    }

    @Override
    public void generate_Invoice(InsertPayment insertPayment) {
        //Toast.makeText(this, "Invoice Generated Successfully.\n INV ID : " + insertPayment.getId(), Toast.LENGTH_LONG).show();
        presenter.getInvoice(this, insertPayment.getId());

    }

    @Override
    public void insertInvoice(InsertPayment insertPayment) {
        //Toast.makeText(this, insertPayment.getId(), Toast.LENGTH_SHORT).show();
        presenter.getInvoice(this, insertPayment.getId());

    }


    @Override
    public void showInvoice(GetInvoiceModel getInvoiceModel) {
        Log.d("TAG", "showInvoice: 1223456");

        try {
            getInvoiceModelInvoice = getInvoiceModel;

            SharedPrefsData.putString(this, Constants.AccNo, String.valueOf(getInvoiceModel.getAccount_No()), Constants.PREF_NAME);
            triplePlay = SharedPrefsData.getString(GenerateInvoiceActivity.this, Constants.TriplePlay, Constants.PREF_NAME);
            if (triplePlay.equals("Cable")) {
                SharedPrefsData.putString(this, Constants.TotalAmount, cableBoxWithSubscription.geTotal_CableBox_amount(), Constants.PREF_NAME);
            } else if (triplePlay.equals("Internet")) {
                SharedPrefsData.putString(this, Constants.TotalAmount, internetBoxWithSubscription.getTotal_InternetBox_amount(), Constants.PREF_NAME);
                //SharedPrefsData.putString(this, Constants.TotalAmount, String.valueOf(internetBoxList.get(box_position).getInternetBox().getBox_Amount()), Constants.PREF_NAME);
            }
            SharedPrefsData.putString(this, Constants.InvoiceNo, String.valueOf(getInvoiceModel.getInvoice_Number()), Constants.PREF_NAME);

            //getInvoiceModel.getArea_Customer_ID();
            //getInvoiceModel.getAreaName();
            SharedPrefsData.putString(this, Constants.CustomerBalance, String.valueOf(getInvoiceModel.getBalance()), Constants.PREF_NAME);
            getInvoiceModel.getCustomer_ID();
            getInvoiceModel.getDiscount();
            getInvoiceModel.getInv_Amount();
            getInvoiceModel.getInvoice_Date();
            SharedPrefsData.putString(this, Constants.InvoiceDate, getInvoiceModel.getInvoice_Date().toString(), Constants.PREF_NAME);
            //getInvoiceModel.getInvoice_ID();
            //SharedPrefsData.putString(this, Constants.InvoiceNo, getInvoiceModel.get().toString(), Constants.PREF_NAME);
            SharedPrefsData.putString(this, Constants.CustomerName, getInvoiceModel.getName().toString(), Constants.PREF_NAME);
            try {
                SharedPrefsData.putString(this, Constants.PrevBal, String.valueOf(getInvoiceModel.getPrevious_Balance()), Constants.PREF_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SharedPrefsData.putString(this, Constants.SubId, getInvoiceModel.getSubscriber_ID().toString(), Constants.PREF_NAME);
            getInvoiceModel.getTitle();
            getInvoiceModel.getTriple_play_ID();
        }catch (Exception e){

        }


        Log.d("TAG", "showInvoice: 12");
        startActivity(new Intent(this, BillShareActivity.class));
        Log.d("TAG", "showInvoice:3456");

    }

    @Override
    public void onClickCallback(final TextView view, final TextView view1, final TextView view3, final TextView view4, final LinearLayout ll, int position, String id) {

        box_position = position;
        strApiCallType = id;
        Log.d("TAG", "showCableBoxANNU: "+strApiCallType.toString());

        if (boxType_IDInternet == null) {
            boxType_IDInternet = "null";
        }

        switch (id) {
            case "strActivationDate":
                presenter.updateCableBoxSubscription(this, view, view1, userId, customerID, cable_Box_ID, "null", boxType_ID, vcno, stbno, cafno, bill_Type_ID, connection_Status_ID, activation_Date, expiry_Date.substring(0, 10), noofMonth, noofDays, "null", "null", "null", String.valueOf(newBoxAmount), todayDateString());
                break;
            case "spn_no_of_months": {
                spn_no_of_months.performClick();
                AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view5, int position, long id) {
                                noofMonth = spn_no_of_months.getSelectedItem().toString();
                                noofMonth_multiplyfactor = Integer.parseInt(noofMonth);
                                view3.setText(noofMonth);

                                //Me
                                Log.e("noofMonth", noofMonth);

//                                if (noofMonth != "1"){
//                                    prevMonthValue = noofMonth;
//                                }
//
//                                if (noofMonth == "1"){
//                                    amount.setText(String.valueOf(Float.parseFloat(box_Amount)/Float.parseFloat(prevMonthValue)));
//                                }
//
//                                Toast.makeText(GenerateInvoiceActivity.this, ""+Float.parseFloat(box_Amount)/Float.parseFloat(prevMonthValue), Toast.LENGTH_SHORT).show();

                                presenter.updateCableBoxSubscription(GenerateInvoiceActivity.this, view, view1, userId, customerID, cable_Box_ID, "null", boxType_ID, vcno, stbno, cafno, bill_Type_ID, connection_Status_ID, activation_Date, expiry_Date.substring(0, 10), noofMonth, noofDays, "null", "null", "null", String.valueOf(newBoxAmount), todayDateString());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        };
                spn_no_of_months.setOnItemSelectedListener(onItemSelectedListener2);

                break;
            }
            case "spn_bill_type": {
                spn_bill_type.performClick();

                AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view5, int position, long id) {
                                bill_Type_ID = billID.get(position);
                                strBillType = billTypeName.get(position);

                                view4.setText(strBillType);

                                if (strBillType.equals("NoofDays")) {
                                    ll.setVisibility(View.VISIBLE);

                                } else {
                                    ll.setVisibility(View.GONE);
                                    presenter.updateCableBoxSubscription(GenerateInvoiceActivity.this, view, view1, userId, customerID, cable_Box_ID, "null", boxType_ID, vcno, stbno, cafno, bill_Type_ID, connection_Status_ID, activation_Date, expiry_Date.substring(0, 10), noofMonth, noofDays, "null", "null", "null", String.valueOf(newBoxAmount), todayDateString());

                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        };
                spn_bill_type.setOnItemSelectedListener(onItemSelectedListener2);
                break;
            }
            case "strBoxAmount":
                presenter.updateCableBoxSubscription(this, view, view1, userId, customerID, cable_Box_ID, "null", boxType_ID, vcno, stbno, cafno, bill_Type_ID, connection_Status_ID, activation_Date, expiry_Date.substring(0, 10), noofMonth, noofDays, "null", "null", "null", String.valueOf(box_Amount), todayDateString());
                break;
            case "strNoOfDays":
                presenter.updateCableBoxSubscription(this, view, view1, userId, customerID, cable_Box_ID, "null", boxType_ID, vcno, stbno, cafno, bill_Type_ID, connection_Status_ID, activation_Date, expiry_Date.substring(0, 10), noofMonth, noofDays, "null", "null", "null", String.valueOf(box_Amount), todayDateString());
                break;
            case "calculateExpiry":

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(sdf.parse(expiry_Date)); // parsed date and setting to calendar
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (count == 1 || count == 2) {
                    count = count + 1;
                    calendar.add(Calendar.DATE, 1);  // number of days to add
                    activation_Date = sdf.format(calendar.getTime());
                    //Me
                    Log.e("activation_Date", activation_Date);
                }

                presenter.updateCableBoxSubscription(this, view, view1, userId, customerID, cable_Box_ID, "null", boxType_ID, vcno, stbno, cafno, bill_Type_ID, connection_Status_ID, activation_Date, expiry_Date, noofMonth, noofDays, "null", "null", "null", String.valueOf(box_Amount), todayDateString());


                break;


            case "ADD":
            case "ADDInternet": {

                float ans = Float.parseFloat(cableBoxAmnt) + Float.parseFloat(box_Amount);
                Log.i("cableBoxAmnt", String.valueOf(cableBoxAmnt));
                Log.i("box_Amount", String.valueOf(box_Amount));
                Log.i("ans", String.valueOf(ans));
                totalAmnt = ans + Float.parseFloat(CustomerBalance);
                Log.i("totalAmnt", String.valueOf(totalAmnt));
                txt_subscription_amnt.setText("Rs. " + ans);
                cableBoxAmnt = String.valueOf(ans);
                txt_total_amount.setText("Rs. " + String.valueOf(totalAmnt));
                edt_payingamount.setText(String.valueOf(totalAmnt));

                break;
            }
            case "SUB":
            case "SUBInternet": {

                float ans = Float.parseFloat(cableBoxAmnt) - Float.parseFloat(box_Amount);
                Log.i("cableBoxAmnt", String.valueOf(cableBoxAmnt));
                Log.i("box_Amount", String.valueOf(box_Amount));
                Log.i("ans", String.valueOf(ans));
                totalAmnt = ans + Float.parseFloat(CustomerBalance);
                Log.i("totalAmnt", String.valueOf(totalAmnt));
                cableBoxAmnt = String.valueOf(ans);
                Log.i("cableBoxAmnt", String.valueOf(cableBoxAmnt));
                txt_subscription_amnt.setText("Rs. " + ans);
                txt_total_amount.setText("Rs. " + String.valueOf(totalAmnt));
                edt_payingamount.setText(String.valueOf(totalAmnt));

                break;
            }
            /////////////////
            case "strActivationDateInternet":
                presenter.updateInternetBox(this, view, view1, userId, customerID, internet_Box_ID, "null", boxType_IDInternet, ipno, macadd, bill_Type_IDInternet, connection_Status_IDInternet, activation_DateInternet, expiry_DateInternet, noofMonthInternet, noofDaysInternet, "0", "null", box_AmountInternet, todayDateString());
                break;
            case "spn_no_of_monthsInternet": {

                spn_no_of_months.performClick();

                AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view5, int position, long id) {
                                noofMonthInternet = spn_no_of_months.getSelectedItem().toString();
                                noofMonth_multiplyfactorInternet = Integer.parseInt(noofMonthInternet);

//                                float anssd = Float.parseFloat(alacarteModela.getTotal_Alacarte()) + Float.parseFloat(bouquetModela.getTotal_Bouquet());
//
//                                Log.d("TAG", "onCreateAnimesh: "+noofMonthInternet);
//                                float data=Float.parseFloat(String.valueOf(noofMonthInternet))*anssd;
//                                txt_subscription_amnt.setText(String.valueOf(data));

                                //Toast.makeText(GenerateInvoiceActivity.this, "No of Month "+noofMonthInternet, Toast.LENGTH_SHORT).show();

                                //SharedPrefsData.putString(GenerateInvoiceActivity.this, Constants.NoofMonths, noofMonthInternet, Constants.PREF_NAME);

//                                internetBoxList.get(box_position).getInternetBox().setNoofMonth(noofMonth_multiplyfactorInternet);
//
//                                Log.e("noofMonthInternet", noofMonthInternet);
                                view3.setText(noofMonthInternet);
                                presenter.updateInternetBox(GenerateInvoiceActivity.this, view, view1, userId, customerID, internet_Box_ID, "null", boxType_IDInternet, ipno, macadd, bill_Type_IDInternet, connection_Status_IDInternet, activation_DateInternet, newInternetExpDate, noofMonthInternet, noofDaysInternet, "null", "null", String.valueOf(newBoxAmount), todayDateString());

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        };
                spn_no_of_months.setOnItemSelectedListener(onItemSelectedListener2);

                break;
            }
            case "spn_bill_typeInternet": {
                spn_bill_type.performClick();
                AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view5, int position, long id) {
                                bill_Type_IDInternet = bill_type.get(position).getBill_Type_ID().toString();
                                strBillType = bill_type.get(position).getBill_Type().toString();
                                view4.setText(strBillType);
                                if (strBillType.equals("NoofDays")) {
                                    ll.setVisibility(View.VISIBLE);
                                } else {
                                    ll.setVisibility(View.GONE);
                                    presenter.updateInternetBox(GenerateInvoiceActivity.this,  view, view1, userId, customerID, internet_Box_ID, "null", boxType_IDInternet, ipno, macadd, bill_Type_IDInternet, connection_Status_IDInternet, activation_DateInternet, newInternetExpDate, noofMonthInternet, noofDaysInternet, "0", "null", total_pkg_amount, todayDateString());
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        };
                spn_bill_type.setOnItemSelectedListener(onItemSelectedListener2);
                break;
            }
            case "strBoxAmountInternet":
                presenter.updateInternetBox(GenerateInvoiceActivity.this, view, view1,  userId, customerID, internet_Box_ID, "null", boxType_IDInternet, ipno, macadd, bill_Type_IDInternet, connection_Status_IDInternet, activation_DateInternet, expiry_DateInternet, noofMonthInternet, noofDaysInternet, "0", "null", box_AmountInternet, todayDateString());
                break;
            case "strNoOfDaysInternet":
                presenter.updateInternetBox(GenerateInvoiceActivity.this,  view, view1,userId, customerID, internet_Box_ID, "null", boxType_IDInternet, ipno, macadd, bill_Type_IDInternet, connection_Status_IDInternet, activation_DateInternet, expiry_DateInternet, noofMonthInternet, noofDaysInternet, "0", "null", box_AmountInternet, todayDateString());
                break;
            case "calculateExpiryInternet":

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

                Calendar calendar1 = Calendar.getInstance();
                try {
                    calendar1.setTime(sdf1.parse(expiry_DateInternet)); // parsed date and setting to calendar
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (countInternet == 1) {
                    countInternet = countInternet + 1;
                    calendar1.add(Calendar.DATE, 1);  // number of days to add
                    activation_DateInternet = sdf1.format(calendar1.getTime());
                    Log.e("activation_DateInternet", activation_DateInternet);
                }

                presenter.updateInternetBox(GenerateInvoiceActivity.this,   view, view1,userId, customerID, internet_Box_ID, "null", boxType_IDInternet, ipno, macadd, bill_Type_IDInternet, connection_Status_IDInternet, activation_DateInternet, expiry_DateInternet, noofMonthInternet, noofDaysInternet, "0", "null", box_AmountInternet, todayDateString());
                break;


        }

    }


    private String todayDateString() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Log.e("cal", dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        try {
            triplePlay = SharedPrefsData.getString(GenerateInvoiceActivity.this, Constants.TriplePlay, Constants.PREF_NAME);


            if (triplePlay.equals("Cable")) {
                // presenter.loadCableBoxListApi(SharedPrefsData.getString(GenerateInvoiceActivity.this,Constants.CustomerID,Constants.PREF_NAME));

                cableBoxList = cableBoxWithSubscription.getCableBoxwithSubscription();

                float ans = Float.parseFloat(alacarteModela.getTotal_Alacarte()) + Float.parseFloat(bouquetModela.getTotal_Bouquet());
                String newbox_Amount = String.valueOf(ans * noofMonth_multiplyfactor);

                box_Amount = newbox_Amount;
                cableBoxAmnt = newbox_Amount;

                newBoxAmount = ans;


                totalAmnt = Float.parseFloat(newbox_Amount) + Float.parseFloat(CustomerBalance);
                txt_subscription_amnt.setText(newbox_Amount);
                // cableBoxAmnt = String.valueOf(ans);
                txt_total_amount.setText(String.valueOf(totalAmnt));
                edt_payingamount.setText(String.valueOf(totalAmnt));

                cableBoxList.get(box_position).getCableBox().setBox_Amount(Double.parseDouble(newbox_Amount));
                amount.setText(newbox_Amount);

                cableBoxList.get(box_position).getCableBox().setActivation_Date(activation_Date);

                //cableBoxList.get(box_position).getCableBox().setExpiry_Date(activation_Date);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
                rv_box_details.setLayoutManager(mLayoutManager);
                cableBoxDetailsAdapter = new CableBoxDetailsAdapter(GenerateInvoiceActivity.this, cableBoxList, adapterCallback, adapterCall, "B");
                rv_box_details.setAdapter(cableBoxDetailsAdapter);

            } else if (triplePlay.equals("Internet")) {
                //presenter.loadInternetBoxListApi(SharedPrefsData.getString(GenerateInvoiceActivity.this, Constants.CustomerID, Constants.PREF_NAME));

                internetBoxList = internetBoxWithSubscription.getInternetBoxwithSubscription();

                ans = Float.parseFloat(String.valueOf(internetPackageModela.getTotal_Package()));

                String newbox_AmountInternet = String.valueOf(ans * noofMonth_multiplyfactorInternet);

                box_Amount = newbox_AmountInternet;
                cableBoxAmnt = newbox_AmountInternet;

                newBoxAmount = ans;
                newTotalAmount = String.valueOf(ans);

                totalAmnt = Float.parseFloat(newbox_AmountInternet) + Float.parseFloat(CustomerBalance);
                txt_subscription_amnt.setText(newbox_AmountInternet);
                //internetBoxAmnt = String.valueOf(newbox_AmountInternet);
                txt_total_amount.setText(String.valueOf(totalAmnt));
                edt_payingamount.setText(String.valueOf(totalAmnt));

                internetBoxList.get(box_position).getInternetBox().setBox_Amount(Double.parseDouble(newbox_AmountInternet));
                amountInternet.setText(newbox_AmountInternet);

                internetBoxList.get(box_position).getInternetBox().setActivation_Date(newInternetActDate);

                internetBoxList.get(box_position).getInternetBox().setExpiry_Date(newInternetExpDate);

                SharedPrefsData.putString(this, Constants.TotalAmount, String.valueOf(internetBoxList.get(box_position).getInternetBox().getBox_Amount()), Constants.PREF_NAME);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
                rv_box_details.setLayoutManager(mLayoutManager);
                internetBoxDetailsAdapter = new InternetBoxDetailsAdapter(GenerateInvoiceActivity.this, internetBoxList, adapterCallback, adapterCall);
                rv_box_details.setAdapter(internetBoxDetailsAdapter);

            }
        } catch (Exception e) {
            triplePlay = "";
            e.printStackTrace();
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        try {
//            triplePlay = SharedPrefsData.getString(GenerateInvoiceActivity.this, Constants.TriplePlay, Constants.PREF_NAME);
//
//
//            if (triplePlay.equals("Cable")) {
//                // presenter.loadCableBoxListApi(SharedPrefsData.getString(GenerateInvoiceActivity.this,Constants.CustomerID,Constants.PREF_NAME));
//
//                cableBoxList = cableBoxWithSubscription.getCableBoxwithSubscription();
//
//                float ans = Float.parseFloat(alacarteModela.getTotal_Alacarte()) + Float.parseFloat(bouquetModela.getTotal_Bouquet());
//                String newbox_Amount = String.valueOf(ans * noofMonth_multiplyfactor);
//
//                box_Amount = newbox_Amount;
//                cableBoxAmnt = newbox_Amount;
//
//                newBoxAmount = ans;
//
//
//                totalAmnt = Float.parseFloat(newbox_Amount) + Float.parseFloat(CustomerBalance);
//                txt_subscription_amnt.setText(newbox_Amount);
//                // cableBoxAmnt = String.valueOf(ans);
//                txt_total_amount.setText(String.valueOf(totalAmnt));
//                edt_payingamount.setText(String.valueOf(totalAmnt));
//
//                cableBoxList.get(box_position).getCableBox().setBox_Amount(Double.parseDouble(newbox_Amount));
//                amount.setText(newbox_Amount);
//
//                cableBoxList.get(box_position).getCableBox().setActivation_Date(activation_Date);
//
//                //cableBoxList.get(box_position).getCableBox().setExpiry_Date(activation_Date);
//
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//                rv_box_details.setLayoutManager(mLayoutManager);
//                cableBoxDetailsAdapter = new CableBoxDetailsAdapter(GenerateInvoiceActivity.this, cableBoxList, adapterCallback, adapterCall, "B");
//                rv_box_details.setAdapter(cableBoxDetailsAdapter);
//
//            } else if (triplePlay.equals("Internet")) {
//                //presenter.loadInternetBoxListApi(SharedPrefsData.getString(GenerateInvoiceActivity.this, Constants.CustomerID, Constants.PREF_NAME));
//
//                internetBoxList = internetBoxWithSubscription.getInternetBoxwithSubscription();
//
//                ans = Float.parseFloat(String.valueOf(internetPackageModela.getTotal_Package()));
//
//                String newbox_AmountInternet = String.valueOf(ans * noofMonth_multiplyfactorInternet);
//
//                box_Amount = newbox_AmountInternet;
//                cableBoxAmnt = newbox_AmountInternet;
//
//                newBoxAmount = ans;
//                newTotalAmount = String.valueOf(ans);
//
//                totalAmnt = Float.parseFloat(newbox_AmountInternet) + Float.parseFloat(CustomerBalance);
//                txt_subscription_amnt.setText(newbox_AmountInternet);
//                //internetBoxAmnt = String.valueOf(newbox_AmountInternet);
//                txt_total_amount.setText(String.valueOf(totalAmnt));
//                edt_payingamount.setText(String.valueOf(totalAmnt));
//
//                internetBoxList.get(box_position).getInternetBox().setBox_Amount(Double.parseDouble(newbox_AmountInternet));
//                amountInternet.setText(newbox_AmountInternet);
//
//                internetBoxList.get(box_position).getInternetBox().setActivation_Date(newInternetActDate);
//
//                internetBoxList.get(box_position).getInternetBox().setExpiry_Date(newInternetExpDate);
//
//                SharedPrefsData.putString(this, Constants.TotalAmount, String.valueOf(internetBoxList.get(box_position).getInternetBox().getBox_Amount()), Constants.PREF_NAME);
//
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//                rv_box_details.setLayoutManager(mLayoutManager);
//                internetBoxDetailsAdapter = new InternetBoxDetailsAdapter(GenerateInvoiceActivity.this, internetBoxList, adapterCallback, adapterCall);
//                rv_box_details.setAdapter(internetBoxDetailsAdapter);
//
//            }
//        } catch (Exception e) {
//            triplePlay = "";
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onClickCallback(View view, int position, String id) {

        cableBoxList = cableBoxWithSubscription.getCableBoxwithSubscription();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_box_details.setLayoutManager(mLayoutManager);
        cableBoxDetailsAdapter = new CableBoxDetailsAdapter(GenerateInvoiceActivity.this, cableBoxList, adapterCallback, adapterCall, "B");
        rv_box_details.setAdapter(cableBoxDetailsAdapter);


    }

}