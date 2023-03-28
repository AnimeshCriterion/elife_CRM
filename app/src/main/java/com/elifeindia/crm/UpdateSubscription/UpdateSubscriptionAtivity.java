package com.elifeindia.crm.UpdateSubscription;


import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.MyViewHolder.amountUpdateCableBox;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.activation_Dateupdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.bill_Type_IDupdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.boxType_IDupdatecable;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.box_Amountupdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.cable_Box_IDupdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.cafnoupdateCable;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.connection_Status_IDupdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.expiry_Dateupdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.noofDaysupdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.noofMonthupdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.stbnoupdateCable;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.strBillTypeupdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate.vcnoupdateCable;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.MyViewHolder.amountInternetupdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.activation_DateInternetUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.bill_Type_IDInternetUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.boxType_IDInternetUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.box_AmountInternetUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.cafnoUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.connection_Status_IDInternetUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.expiry_DateInternetUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.internet_Box_IDUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.ipnoUpdateInternet;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.macaddUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.noofDaysInternetUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.noofMonthInternetUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.stbnoUpdate;
import static com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate.strBillTypeUpdateInternet;
import static com.elifeindia.crm.UpdateSubscription.Fragment.AlcarteFragementA.txt_total_amountupdate;
import static com.elifeindia.crm.UpdateSubscription.Fragment.PackageFragmentA.txt_total_amountbouquet;
import static com.elifeindia.crm.UpdateSubscription.UpdateINternetSubscriptionActivityForUpdateSubscription.txt_total_amountForInternetSubscription;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.activation_Date;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.bill_Type_ID;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.box_Amount;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.cafno;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.expiry_Date;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.noofMonth;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsAdapter.strBillType;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.activation_DateInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.bill_Type_IDInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.boxType_IDInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.box_AmountInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.expiry_DateInternet;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.internet_Box_ID;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.macadd;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter.noofMonthInternet;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.UpdateSubscription.Adapter.CableBoxAdapterForSubScriptionUpdate;
import com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetCableBoxAdapterUpdate;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.AdapterCallbackTextView;
import com.elifeindia.crm.contract.activities.GenerateInvoiceContract;
import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.BillTypeModel;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.BoxAlacarteList;
import com.elifeindia.crm.model.BoxBouquetList;
import com.elifeindia.crm.model.BoxTypeModel;
import com.elifeindia.crm.model.CableBoxWithSubscription;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.model.CustomerData;
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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateSubscriptionAtivity extends AppCompatActivity implements GenerateInvoiceContract.View, AdapterCallbackTextView, AdapterCallback {

    private LinearLayout ll_noofdays;
    public CableBoxWithSubscription cableBoxWithSubscriptionUpdate;
    public static InternetBoxWithSubscription internetBoxWithSubscriptionUpdate;

    public GetInvoiceModel getInvoiceModelInvoiceUpdate = new GetInvoiceModel();

    public InternetBoxWithSubscription getInternetBoxModelUpdate = new InternetBoxWithSubscription();

    public static List<AlacarteModel.AlacarteDTO> alacarteModelAlacarteUpdateforSubscription;
    public static List<InternetPackageModel.PackagesDTO> internetPackageModelInternetUpdateSubscription;
    public static List<BouquetModel.BouquetDTO> bouquetModelBouquetUpdate;

    public static BouquetModel bouquetModelaUpdateSubscription;
    public static AlacarteModel alacarteModelaUpdateSubscription;
    public static InternetPackageModel internetPackageModelaUpdateSubscription;

    public String paidAmountUpdate = "";
    int noofMonth_multiplyfactor = 1;
    int noofMonth_multiplyfactorInternet = 1;

    public int box_positionUpdate;
    GenerateInvoiceContract.Presenter presenter;
    CableBoxAdapterForSubScriptionUpdate cableBoxDetailsAdapter;
    InternetCableBoxAdapterUpdate internetBoxDetailsAdapter;
    Spinner spn_paymentmode, spn_triple_pay, spn_bill_type, spn_no_of_months;
    RecyclerView rv_box_details;
    ArrayAdapter<String> adapter_state_adj1, adapter_state_adj2, adapter_state_adj3, adapter_state_adj4;
    String strCableExpiry = "", strCableBoxAmnt = "", companyID, customerID, empId, userId, InvType, strNoMonths, pTypeId, tripleId, triplePlay, strActivationDate = "", strExpiryDate = "DD MM YYYY";
    AdapterCallbackTextView adapterCallback;
    AdapterCallback adapterCall;
    String strApiCallType = "";
    public float ansUpdate, totalAmntUpdate;
    Button btn_generate_invoice;
    public boolean flag_view_hide = false;
    public TextView txt_final_amntUpdate, txt_new_balUpdate, txt_total_amountUpdate, txt_prev_balUpdate, txt_subscription_amntUpdate, txt_activation_dateUpdate, txt_expiry_dateUpdate;
    public EditText edt_no_of_daysUpdate, edt_payingamountUpdate, edt_discountUpdate;
    String[] no_of_months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    List<BillTypeModel.BillTypeDTO> bill_type;
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

    public float newBoxAmountUpdate;

    public String newTotalAmountUpdate;

    public String newInternetActDateUpdate;
    public String newInternetExpDateUpdate;
    private TextView txt_activation_date1;
    private int customerIDUpdate;
    public static CustemersList.Customer dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subscription_ativity);
        customerID = SharedPrefsData.getString(this, Constants.CustomerID, Constants.PREF_NAME);

        //     dataModel = (CustemersList.Customer) getIntent().getSerializableExtra("CustomerID");
//       Intent intent=getIntent();
//        customerIDUpdate=intent.getIntExtra("CustomerID",0);
        //  customerID = String.valueOf(intent.getStringExtra("CustomerID"));


        companyID = SharedPrefsData.getString(this, Constants.CompanyID, Constants.PREF_NAME);
        adapterCallback = this;
        adapterCall = this;
        presenter = new GenerateInvoicePresenter(this);
        presenter.start();
        presenter.loadCustomerSubscribeList(String.valueOf(customerID));
        presenter.loadPaymentTypeList(companyID);
        presenter.getBillType("");
        presenter.loadCableBoxListApi(String.valueOf(customerID));


        edt_payingamountUpdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String b = edt_payingamountUpdate.getText().toString();
                String a = String.valueOf(totalAmntUpdate);
                String c = edt_discountUpdate.getText().toString();

                txt_final_amntUpdate.setText(b);
                if (c.equals(""))
                    c = "0";

                if (edt_payingamountUpdate.getText().toString().equals("")) {
                    txt_new_balUpdate.setText(String.valueOf(totalAmntUpdate));
                    txt_final_amntUpdate.setText("0");

                } else {
                    try {
                        ansUpdate = (Float.parseFloat(a) - Float.parseFloat(b) + Float.parseFloat(c));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    txt_new_balUpdate.setText(String.valueOf(ansUpdate));
                    SharedPrefsData.putString(UpdateSubscriptionAtivity.this, Constants.CustomerBalance, String.valueOf(ansUpdate), Constants.PREF_NAME);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_discountUpdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String b = edt_discountUpdate.getText().toString();
                String a = SharedPrefsData.getString(UpdateSubscriptionAtivity.this, Constants.CustomerBalance, Constants.PREF_NAME);


                if (edt_discountUpdate.getText().toString().equals("")) {
                    //txt_new_bal.setText("");
                } else {
                    float ans = 0;
                    try {
                        ans = (Float.parseFloat(a) - Float.parseFloat(b));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    txt_new_balUpdate.setText(String.valueOf(ans));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_generate_invoice.setOnClickListener(v -> {
            Gson gson = new Gson();
            String jsonStr = null;
            JsonParser parser = new JsonParser();
            if (triplePlay.equals("Cable")) {

                try {

                    if (vcnoupdateCable.isEmpty() || vcnoupdateCable == null) {
                        Toast.makeText(UpdateSubscriptionAtivity.this, "Please enter VC Number", Toast.LENGTH_SHORT).show();
                    } else if (stbnoupdateCable.isEmpty() || stbnoupdateCable == null) {
                        Toast.makeText(UpdateSubscriptionAtivity.this, "Please enter STB Number", Toast.LENGTH_SHORT).show();
                    } else if (cafnoupdateCable.isEmpty() || cafnoupdateCable == null) {
                        Toast.makeText(UpdateSubscriptionAtivity.this, "Please enter Caf Number", Toast.LENGTH_SHORT).show();
                    } else {
                        presenter.updateCableBoxSubscription(UpdateSubscriptionAtivity.this, userId, String.valueOf(customerID), cable_Box_IDupdate, "null", boxType_IDupdatecable, vcnoupdateCable, stbnoupdateCable, cafnoupdateCable, bill_Type_IDupdate, connection_Status_IDupdate, activation_Dateupdate, expiry_Dateupdate, noofMonthupdate, noofDaysupdate,box_Amountupdate, box_Amountupdate, "null", box_Amountupdate, todayDateString());
                        Toast.makeText(UpdateSubscriptionAtivity.this, "Cable Box Updated !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (triplePlay.equals("Internet")) {
                try {
                    if (ipnoUpdateInternet.isEmpty() || ipnoUpdateInternet == null) {
                        Toast.makeText(UpdateSubscriptionAtivity.this, "Please enter IP Number", Toast.LENGTH_SHORT).show();
                    } else if (macaddUpdate.isEmpty() || macaddUpdate == null) {
                        Toast.makeText(UpdateSubscriptionAtivity.this, "Please enter Mac Number", Toast.LENGTH_SHORT).show();
                    } else {
                        if(noofDaysInternetUpdate==null || noofDaysInternetUpdate.equals("null")){
                            noofDaysInternetUpdate="0";
                        }
                        Log.d("TAG", "Animesh: "+noofDaysInternetUpdate);
                        Log.d("TAG", "Animesh: "+noofMonthInternetUpdate);
                        presenter.updateInternetBoxForSubscription(UpdateSubscriptionAtivity.this, userId, String.valueOf(customerID), internet_Box_IDUpdate, "null", boxType_IDInternetUpdate, ipnoUpdateInternet, macaddUpdate, bill_Type_IDInternetUpdate, connection_Status_IDInternetUpdate, activation_DateInternetUpdate, expiry_DateInternetUpdate, noofMonthInternetUpdate, noofDaysInternetUpdate, box_AmountInternetUpdate, box_AmountInternetUpdate, box_AmountInternetUpdate, todayDateString());
                        Toast.makeText(UpdateSubscriptionAtivity.this, "Internet Box Updated !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            //Log.i("json object", json.toString());

//                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(UpdateSubscriptionAtivity.this, R.style.ListRow));
//                builder.setIcon(android.R.drawable.ic_dialog_info).setTitle("Generate Invoice Confirmation")
//                        .setMessage("Are you sure you want to generate the invoice?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                presenter.insertInvoice(UpdateSubscriptionAtivity.this, json);
//
//                            }
//                        }).setNegativeButton("N0", null)
//                        .show();


//                Log.i("invoice details", userId+" "+ customerID+" "+ tripleId+" "+ todayDateString()+ " "+triplePlay+" "+ edt_payingamount.getText().toString()+" "+ empId+ pTypeId+" "+ todayDateString()+" "+ "null");
//                presenter.generate_Invoice(GenerateInvoiceActivity.this, userId, customerID, tripleId, todayDateString(), triplePlay, edt_payingamount.getText().toString(), empId, pTypeId, todayDateString(), "null");


        });


        for (int i = 1; i <= 12; i++) {
            statesList.add(i);
        }
        adapter_state_adj4 = new ArrayAdapter<String>(UpdateSubscriptionAtivity.this, android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_state_adj4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_no_of_months.setAdapter(adapter_state_adj4);

    }

    private void loadCustomerDetails(CustemersList.Customer dataModel) {
        Call<CustomerData> call = RetrofitClientUpdateNew.getInstance().getApi().getCustomerDataForUpdate(String.valueOf(customerID));
        call.enqueue(new Callback<CustomerData>() {
            @Override
            public void onResponse(Call<CustomerData> call, Response<CustomerData> response) {
                if (response.isSuccessful()) {
                    InvType = response.body().getInvoiceType();
                    CustomerBalance = response.body().getBalance();
                    if (InvType.equals("Collective")) {
                        spn_triple_pay.setVisibility(View.INVISIBLE);
                    } else {
                        spn_triple_pay.setVisibility(View.VISIBLE);
                    }


                } else {
                    Toast.makeText(UpdateSubscriptionAtivity.this, "Try Again  !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerData> call, Throwable t) {
                Toast.makeText(UpdateSubscriptionAtivity.this, "Error :" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void init() {
        spn_triple_pay = findViewById(R.id.spn_triple_pay);
        spn_paymentmode = findViewById(R.id.spn_paymentmode);
        rv_box_details = findViewById(R.id.rv_box_details);
        edt_no_of_daysUpdate = findViewById(R.id.spn_no_of_days);
        spn_bill_type = findViewById(R.id.spn_bill_type);
        spn_no_of_months = findViewById(R.id.spn_no_of_months);
        txt_activation_date1 = findViewById(R.id.txt_activation_date);
        txt_expiry_dateUpdate = findViewById(R.id.txt_expiry_date);
        ll_noofdays = findViewById(R.id.ll_noofdays);
        txt_expiry_dateUpdate.setText(strExpiryDate);
        txt_subscription_amntUpdate = findViewById(R.id.txt_subscription_amnt);
        txt_prev_balUpdate = findViewById(R.id.txt_prev_bal);
        txt_total_amountUpdate = findViewById(R.id.txt_total_amount);
        txt_new_balUpdate = findViewById(R.id.txt_new_bal);
        txt_final_amntUpdate = findViewById(R.id.txt_final_amnt);
        edt_payingamountUpdate = findViewById(R.id.payingamount);
        edt_discountUpdate = findViewById(R.id.edt_discount);
        btn_generate_invoice = findViewById(R.id.btn_generate_invoice);
        CustomerBalance = SharedPrefsData.getString(this, Constants.CustomerBalance, Constants.PREF_NAME);
        userId = SharedPrefsData.getString(this, Constants.UserId, Constants.PREF_NAME);
        empId = SharedPrefsData.getString(this, Constants.EmpId, Constants.PREF_NAME);

        //Me

        txt_prev_balUpdate.setText(CustomerBalance);


        edt_no_of_daysUpdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String no_of_days = charSequence.toString();

                //Me
                Log.e("no_of_days", no_of_days);

                txt_expiry_dateUpdate.setText(strExpiryDate);

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
    public void showCustomerSubscribeList(CustomerSubscribeList customerSubscribeList) {
        loadCustomerDetails(dataModel);
        final List<CustomerSubscribeList.CusSubscribeType> cusSubscribeTypeList = customerSubscribeList.getCusSubscribeType();

        ArrayList statesList = new ArrayList();
        for (int i = 0; i < cusSubscribeTypeList.size(); i++) {
            statesList.add(cusSubscribeTypeList.get(i).getTriplePlay());
        }

        adapter_state_adj1 = new ArrayAdapter<String>(UpdateSubscriptionAtivity.this, android.R.layout.simple_dropdown_item_1line, statesList);
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
//                            if (InvType.equals("Collective")) {
//                                presenter.loadCableBoxListApi(customerID);
//                                presenter.loadInternetBoxListApi(customerID);
//                            } else {
                        if (triplePlay.equals("Cable")) {
                            presenter.loadCableBoxListApi(String.valueOf(customerID));

                        }
                        if (triplePlay.equals("Internet")) {
                            presenter.loadInternetBoxListApi(String.valueOf(customerID));

                        }
                        SharedPrefsData.putString(UpdateSubscriptionAtivity.this, Constants.TriplePlay, triplePlay, Constants.PREF_NAME);

                        //   }

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

        adapter_state_adj2 = new ArrayAdapter<String>(UpdateSubscriptionAtivity.this, android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_state_adj2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_paymentmode.setAdapter(adapter_state_adj2);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        pTypeId = paymentTypes.get(position).getPaymentTypeID().toString();

                        //Me
                        Log.e("pTypeId", pTypeId);

                        SharedPrefsData.putString(UpdateSubscriptionAtivity.this, Constants.PaymentTypeId, pTypeId, Constants.PREF_NAME);

                        SharedPrefsData.putString(UpdateSubscriptionAtivity.this, Constants.PaymentMode, spn_paymentmode.getSelectedItem().toString(), Constants.PREF_NAME);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
        spn_paymentmode.setOnItemSelectedListener(onItemSelectedListener2);


    }

    @Override
    public void showCableBoxList(CableBoxWithSubscription cableBoxWithSubscription1) {
        cableBoxWithSubscriptionUpdate = cableBoxWithSubscription1;
        cableBoxList = cableBoxWithSubscriptionUpdate.getCableBoxwithSubscription();
        cableBoxAmnt = cableBoxWithSubscriptionUpdate.geTotal_CableBox_amount();
        newBoxAmountUpdate = Float.parseFloat(cableBoxWithSubscriptionUpdate.geTotal_CableBox_amount());
        totalAmntUpdate = Float.parseFloat(cableBoxAmnt) + Float.parseFloat(CustomerBalance);
        txt_subscription_amntUpdate.setText(cableBoxWithSubscriptionUpdate.geTotal_CableBox_amount());

        cableBoxWithSubscriptionUpdate = cableBoxWithSubscription1;

        txt_total_amountUpdate.setText("Rs. " + String.valueOf(totalAmntUpdate));
        edt_payingamountUpdate.setText(String.valueOf(totalAmntUpdate));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_box_details.setLayoutManager(mLayoutManager);
        cableBoxDetailsAdapter = new CableBoxAdapterForSubScriptionUpdate(UpdateSubscriptionAtivity.this, cableBoxList, adapterCallback, adapterCall, "B", this);
        rv_box_details.setAdapter(cableBoxDetailsAdapter);
    }

    @Override
    public void showInernetBoxList(InternetBoxWithSubscription internetBoxWithSubscription1) {
        internetBoxWithSubscriptionUpdate = internetBoxWithSubscription1;
        internetBoxList = internetBoxWithSubscriptionUpdate.getInternetBoxwithSubscription();
        internetBoxAmnt = internetBoxWithSubscriptionUpdate.getTotal_InternetBox_amount();

        newBoxAmountUpdate = Float.parseFloat(internetBoxWithSubscriptionUpdate.getTotal_InternetBox_amount());

        newTotalAmountUpdate = String.valueOf(internetBoxList.get(box_positionUpdate).getInternetBox().getBox_Amount());

        totalAmntUpdate = Float.parseFloat(internetBoxAmnt) + Float.parseFloat(CustomerBalance);
        txt_subscription_amntUpdate.setText(internetBoxWithSubscription1.getTotal_InternetBox_amount());

        Log.d("TAG", "showInernetBoxList: " + internetBoxWithSubscription1.getTotal_InternetBox_amount().toString());
        internetBoxWithSubscriptionUpdate = internetBoxWithSubscription1;

        txt_total_amountUpdate.setText("Rs. " + String.valueOf(totalAmntUpdate));
        edt_payingamountUpdate.setText(String.valueOf(totalAmntUpdate));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_box_details.setLayoutManager(mLayoutManager);
        internetBoxDetailsAdapter = new InternetCableBoxAdapterUpdate(UpdateSubscriptionAtivity.this, internetBoxList, adapterCallback, adapterCall);
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

        adapter_state_adj3 = new ArrayAdapter<String>(UpdateSubscriptionAtivity.this, android.R.layout.simple_dropdown_item_1line, billTypeName);
        adapter_state_adj3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_bill_type.setAdapter(adapter_state_adj3);


    }

    @Override
    public void showBoxTypeList(BoxTypeModel boxTypeModel) {

    }

    @Override
    public void showCableBox(UpdateBox boxTypeModel, TextView view, TextView view1) {

        SharedPrefsData.putString(getApplicationContext(), "ExpiryDate", boxTypeModel.getExpiry_Date(), Constants.PREF_NAME);

        if (strApiCallType.equals("calculateExpiry")) {
            activation_Dateupdate = boxTypeModel.getActivation_Date().substring(0, 10);
            view1.setText(activation_Dateupdate);
            float box_Amount = Float.parseFloat(boxTypeModel.getBox_Amount());

            //Toast.makeText(this, "cable box amount"+box_Amount, Toast.LENGTH_SHORT).show();

            cableBoxAmnt = String.valueOf(box_Amount);
            cableBoxList.get(box_positionUpdate).getCableBox().setBox_Amount(Double.parseDouble(String.valueOf(box_Amount)));

            //txt_subscription_amnt.setText("Rs. " + box_Amount);
            //amount.setText(String.valueOf(box_Amount));
            totalAmntUpdate = (Float.parseFloat(CustomerBalance) + Float.parseFloat(txt_subscription_amntUpdate.getText().toString()));

            txt_total_amountUpdate.setText(String.valueOf(totalAmntUpdate));
            edt_payingamountUpdate.setText(String.valueOf(totalAmntUpdate));
            expiry_Dateupdate = boxTypeModel.getExpiry_Date().substring(0, 10);

            //activation_Date = boxTypeModel.getActivation_Date().substring(0 , 10);

            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));
            view1.setText(ViewUtils.changeDateFormat(boxTypeModel.getActivation_Date()));

        } else if (strApiCallType.equals("strActivationDate")) {
            activation_Dateupdate = boxTypeModel.getActivation_Date().substring(0, 10);
            expiry_Dateupdate = boxTypeModel.getExpiry_Date().substring(0, 10);
            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));

            //Me
            Log.e("activation_Date_1", activation_Date);
            Log.e("expiry_Date_1", expiry_Date);


        } else if (strApiCallType.equals("spn_bill_type")) {
            activation_Dateupdate = boxTypeModel.getActivation_Date().substring(0, 10);
            expiry_Dateupdate = boxTypeModel.getExpiry_Date().substring(0, 10);
            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));

            //Me
            Log.e("activation_Date_2", activation_Date);
            Log.e("expiry_Date", expiry_Date);


        } else {
            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));
            view1.setText(box_Amount);
            String box_Amount = boxTypeModel.getBox_Amount();
            box_Amountupdate = box_Amount;
            txt_subscription_amntUpdate.setText("Rs. " + box_Amount);
            cableBoxList.get(box_positionUpdate).getCableBox().setBox_Amount(Double.parseDouble(box_Amount));

            amountUpdateCableBox.setText(box_Amount);
            totalAmntUpdate = (Float.parseFloat(CustomerBalance) + Float.parseFloat(box_Amount));

            txt_total_amountUpdate.setText(String.valueOf(totalAmntUpdate));
            edt_payingamountUpdate.setText(String.valueOf(totalAmntUpdate));


            activation_Dateupdate = boxTypeModel.getActivation_Date().substring(0, 10);
            expiry_Dateupdate = boxTypeModel.getExpiry_Date().substring(0, 10);

            //Me
            Log.e("activation_Date", activation_Date);
            Log.e("expiry_Date", expiry_Date);
        }

        cableBoxWithSubscriptionUpdate.setTotal_CableBox_amount(boxTypeModel.getBox_Amount());
        cableBoxWithSubscriptionUpdate.getCableBoxwithSubscription().get(box_positionUpdate).getCableBox().setBox_Amount(Double.valueOf(box_Amount));
        cableBoxWithSubscriptionUpdate.getCableBoxwithSubscription().get(box_positionUpdate).getCableBox().setActivation_Date(activation_Date);
        cableBoxWithSubscriptionUpdate.getCableBoxwithSubscription().get(box_positionUpdate).getCableBox().setNoofMonth(Integer.valueOf(noofMonth));
        cableBoxWithSubscriptionUpdate.getCableBoxwithSubscription().get(box_positionUpdate).getCableBox().setBill_Type_ID(Integer.valueOf(bill_Type_ID));
        cableBoxWithSubscriptionUpdate.getCableBoxwithSubscription().get(box_positionUpdate).getCableBox().setBill_Type(strBillType);
        cableBoxWithSubscriptionUpdate.getCableBoxwithSubscription().get(box_positionUpdate).getCableBox().setExpiry_Date((expiry_Date));


    }

    @Override
    public void showInternetBox(UpdateBox boxTypeModel, TextView view, TextView view1) {
        SharedPrefsData.putString(getApplicationContext(), "ExpiryDate", boxTypeModel.getExpiry_Date(), Constants.PREF_NAME);

        if (strApiCallType.equals("calculateExpiryInternet")) {
            view1.setText(box_AmountInternetUpdate);
            float box_AmountInternet = Float.parseFloat(boxTypeModel.getBox_Amount());
            txt_subscription_amntUpdate.setText(boxTypeModel.getBox_Amount());

            //txt_subscription_amnt.setText("Rs. " + box_AmountInternet);
            internetBoxList.get(box_positionUpdate).getInternetBox().setBox_Amount(Double.parseDouble(String.valueOf(box_AmountInternet)));
            //amountInternet.setText(String.valueOf(box_AmountInternet));
            Log.d("TAG", "showInternetBox: " + boxTypeModel.getExpiry_Date());
            SharedPrefsData.putString(getApplicationContext(), "ExpiryDate", boxTypeModel.getExpiry_Date(), Constants.PREF_NAME);
            expiry_DateInternetUpdate = boxTypeModel.getExpiry_Date().substring(0, 10);
            //activation_Date = boxTypeModel.getActivation_Date().substring(0 ,10);
            newInternetActDateUpdate = boxTypeModel.getActivation_Date().substring(0, 10);
            newInternetExpDateUpdate = boxTypeModel.getExpiry_Date().substring(0, 10);
            totalAmntUpdate = (Float.parseFloat(CustomerBalance) + Float.parseFloat(txt_subscription_amntUpdate.getText().toString()));
            txt_total_amountUpdate.setText(String.valueOf(totalAmntUpdate));
            edt_payingamountUpdate.setText(String.valueOf(totalAmntUpdate));
            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));
            view1.setText(ViewUtils.changeDateFormat(boxTypeModel.getActivation_Date()));

            //Me
            Log.e("expiry_Date2", expiry_DateInternetUpdate);
            Log.e("newInternetActDate2", newInternetActDateUpdate);

        } else if (strApiCallType.equals("strActivationDateInternet")) {
            activation_DateInternetUpdate = boxTypeModel.getActivation_Date().substring(0, 10);
            expiry_DateInternetUpdate = boxTypeModel.getExpiry_Date().substring(0, 10);
            SharedPrefsData.putString(getApplicationContext(), "ExpiryDate", boxTypeModel.getExpiry_Date(), Constants.PREF_NAME);

            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));
            //Me
            Log.e("activation_Date3", activation_DateInternetUpdate);
            Log.e("expiry_Date3", expiry_DateInternetUpdate);


        } else {
            view.setText(ViewUtils.changeDateFormat(boxTypeModel.getExpiry_Date()));
            view1.setText(box_AmountInternetUpdate);
            String box_Amount = boxTypeModel.getBox_Amount();
            box_AmountInternet = box_Amount;
            txt_subscription_amntUpdate.setText("Rs. " + box_Amount);
            box_AmountInternetUpdate = boxTypeModel.getBox_Amount();
            txt_subscription_amntUpdate.setText("Rs. " + box_Amount);
            internetBoxList.get(box_positionUpdate).getInternetBox().setBox_Amount(Double.parseDouble(box_AmountInternet));
            amountInternetupdate.setText(box_AmountInternet);

            totalAmntUpdate = (Float.parseFloat(CustomerBalance) + Float.parseFloat(box_AmountInternet));
            txt_total_amountUpdate.setText(String.valueOf(totalAmntUpdate));
            edt_payingamountUpdate.setText(String.valueOf(totalAmntUpdate));
            activation_DateInternetUpdate = boxTypeModel.getActivation_Date().substring(0, 10);
            SharedPrefsData.putString(getApplicationContext(), "ExpiryDate", boxTypeModel.getExpiry_Date(), Constants.PREF_NAME);

            expiry_DateInternetUpdate = boxTypeModel.getExpiry_Date().substring(0, 10);

            //Me
            Log.e("activation_DateInterne4", activation_DateInternetUpdate);
            Log.e("expiry_DateInternet4", expiry_DateInternetUpdate);

        }


        //Log.e("strBillType23", strBillType);

        internetBoxWithSubscriptionUpdate.setTotal_InternetBox_amount(boxTypeModel.getBox_Amount());
        internetBoxWithSubscriptionUpdate.getInternetBoxwithSubscription().get(box_positionUpdate).getInternetBox().setBox_Amount(Double.valueOf(box_AmountInternet));
        internetBoxWithSubscriptionUpdate.getInternetBoxwithSubscription().get(box_positionUpdate).getInternetBox().setActivation_Date(activation_DateInternet);
        internetBoxWithSubscriptionUpdate.getInternetBoxwithSubscription().get(box_positionUpdate).getInternetBox().setNoofMonth(Integer.valueOf(noofMonthInternet));
        internetBoxWithSubscriptionUpdate.getInternetBoxwithSubscription().get(box_positionUpdate).getInternetBox().setBill_Type_ID(Integer.valueOf(bill_Type_IDInternet));
        internetBoxWithSubscriptionUpdate.getInternetBoxwithSubscription().get(box_positionUpdate).getInternetBox().setBill_Type(strBillType);
        internetBoxWithSubscriptionUpdate.getInternetBoxwithSubscription().get(box_positionUpdate).getInternetBox().setExpiry_Date((expiry_DateInternet));


    }

    @Override
    public void generate_Invoice(InsertPayment insertPayment) {
        //Toast.makeText(this, "Invoice Generated Successfully.\n INV ID : " + insertPayment.getId(), Toast.LENGTH_LONG).show();
        //presenter.getInvoice(this, insertPayment.getId());

    }

    @Override
    public void insertInvoice(InsertPayment insertPayment) {
        //Toast.makeText(this, insertPayment.getId(), Toast.LENGTH_SHORT).show();
        // presenter.getInvoice(this, insertPayment.getId());

    }


    @Override
    public void showInvoice(GetInvoiceModel getInvoiceModel) {
//        startActivity(new Intent(this, BillShareActivity.class));
//        getInvoiceModelInvoiceUpdate = getInvoiceModel;
//        SharedPrefsData.putString(this, Constants.AccNo, String.valueOf(getInvoiceModel.getAccount_No()), Constants.PREF_NAME);
//        triplePlay = SharedPrefsData.getString(UpdateSubscriptionAtivity.this, Constants.TriplePlay, Constants.PREF_NAME);
//        if (triplePlay.equals("Cable")) {
//            SharedPrefsData.putString(this, Constants.TotalAmount, cableBoxWithSubscriptionUpdate.geTotal_CableBox_amount(), Constants.PREF_NAME);
//        } else if (triplePlay.equals("Internet")) {
//            SharedPrefsData.putString(this, Constants.TotalAmount, internetBoxWithSubscriptionUpdate.getTotal_InternetBox_amount(), Constants.PREF_NAME);
//            //SharedPrefsData.putString(this, Constants.TotalAmount, String.valueOf(internetBoxList.get(box_position).getInternetBox().getBox_Amount()), Constants.PREF_NAME);
//        }
//        SharedPrefsData.putString(this, Constants.InvoiceNo, String.valueOf(getInvoiceModel.getInvoice_Number()), Constants.PREF_NAME);
//        //getInvoiceModel.getArea_Customer_ID();
//        //getInvoiceModel.getAreaName();
//        SharedPrefsData.putString(this, Constants.CustomerBalance, String.valueOf(getInvoiceModel.getBalance()), Constants.PREF_NAME);
//        getInvoiceModel.getCustomer_ID();
//        getInvoiceModel.getDiscount();
//        getInvoiceModel.getInv_Amount();
//        getInvoiceModel.getInvoice_Date();
//        SharedPrefsData.putString(this, Constants.InvoiceDate, getInvoiceModel.getInvoice_Date().toString(), Constants.PREF_NAME);
//        //getInvoiceModel.getInvoice_ID();
//        //SharedPrefsData.putString(this, Constants.InvoiceNo, getInvoiceModel.get().toString(), Constants.PREF_NAME);
//        SharedPrefsData.putString(this, Constants.CustomerName, getInvoiceModel.getName().toString(), Constants.PREF_NAME);
//        try {
//            SharedPrefsData.putString(this, Constants.PrevBal, String.valueOf(getInvoiceModel.getPrevious_Balance()), Constants.PREF_NAME);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        SharedPrefsData.putString(this, Constants.SubId, getInvoiceModel.getSubscriber_ID().toString(), Constants.PREF_NAME);
//        getInvoiceModel.getTitle();
//        getInvoiceModel.getTriple_play_ID();


    }

    @Override
    public void onClickCallback(final TextView view, final TextView view1, final TextView view3, final TextView view4, final LinearLayout ll, int position, String id) {

        box_positionUpdate = position;
        strApiCallType = id;
        if (boxType_IDInternet == null) {
            boxType_IDInternet = "null";
        }
        switch (id) {
            case "strActivationDate":
                presenter.updateCableBox(this, view, view1, userId, String.valueOf(customerID), cable_Box_IDupdate, String.valueOf(1), boxType_IDupdatecable, vcnoupdateCable, stbnoupdateCable, cafnoupdateCable, bill_Type_IDupdate, connection_Status_IDupdate, activation_Dateupdate, expiry_Dateupdate, noofMonthupdate, noofDaysupdate, "null", "null", "null", box_Amountupdate, todayDateString());
                break;
            case "spn_no_of_months": {
                spn_no_of_months.performClick();
                AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view5, int position, long id) {
                                noofMonthupdate = spn_no_of_months.getSelectedItem().toString();
                                noofMonth_multiplyfactor = Integer.parseInt(noofMonthupdate);
                                view3.setText(noofMonthupdate);

                                //Me
                                Log.e("noofMonth", noofMonthupdate);

                                presenter.updateCableBox(UpdateSubscriptionAtivity.this, view, view1, userId, String.valueOf(customerID), cable_Box_IDupdate, String.valueOf(1), boxType_IDupdatecable, vcnoupdateCable, stbnoupdateCable, cafnoupdateCable, bill_Type_IDupdate, connection_Status_IDupdate, activation_Dateupdate, expiry_Dateupdate, noofMonthupdate, noofDaysupdate, "null", "null", "null", String.valueOf(newBoxAmountUpdate), todayDateString());
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
                                bill_Type_IDupdate = billID.get(position);
                                strBillTypeupdate = billTypeName.get(position);

                                view4.setText(strBillTypeupdate);

                                if (strBillTypeupdate.equals("NoofDays")) {
                                    ll.setVisibility(View.VISIBLE);

                                } else {
                                    ll.setVisibility(View.GONE);
                                    presenter.updateCableBox(UpdateSubscriptionAtivity.this, view, view1, userId, String.valueOf(customerID), cable_Box_IDupdate, String.valueOf(1), boxType_IDupdatecable, vcnoupdateCable, stbnoupdateCable, cafnoUpdate, bill_Type_IDupdate, connection_Status_IDupdate, activation_Dateupdate, expiry_Dateupdate.substring(0, 10), noofMonthupdate, noofDaysupdate, "null", "null", "null", box_Amountupdate, todayDateString());

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
                presenter.updateCableBox(this, view, view1, userId, String.valueOf(customerID), cable_Box_IDupdate, String.valueOf(1), boxType_IDupdatecable, vcnoupdateCable, stbnoUpdate, cafnoUpdate, bill_Type_IDupdate, connection_Status_IDupdate, activation_Dateupdate, expiry_Dateupdate.substring(0, 10), noofMonthupdate, noofDaysupdate, "null", "null", "null", box_Amountupdate, todayDateString());
                break;
            case "strNoOfDays":
                presenter.updateCableBox(this, view, view1, userId, String.valueOf(customerID), cable_Box_IDupdate, String.valueOf(1), boxType_IDupdatecable, vcnoupdateCable, stbnoupdateCable, cafnoUpdate, bill_Type_IDupdate, connection_Status_IDupdate, activation_Dateupdate, expiry_Dateupdate.substring(0, 10), noofMonthupdate, noofDaysupdate, "null", "null", "null", box_Amountupdate, todayDateString());
                break;
            case "calculateExpiry":
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(sdf.parse(expiry_Dateupdate)); // parsed date and setting to calendar
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (count == 1 || count == 2) {
                    count = count + 1;
                    calendar.add(Calendar.DATE, 1);  // number of days to add
                    activation_Dateupdate = sdf.format(calendar.getTime());
                    //Me
                    Log.e("activation_Date", activation_Dateupdate);
                }

                presenter.updateCableBox(this, view, view1, userId, String.valueOf(customerID), cable_Box_IDupdate, "null", boxType_IDupdatecable, vcnoupdateCable, stbnoupdateCable
                        , cafnoUpdate, bill_Type_IDupdate, connection_Status_IDupdate, activation_Dateupdate, expiry_Dateupdate, noofMonthupdate, noofDaysupdate, "null", "null", "null", box_Amountupdate, todayDateString());


                break;


            case "ADD":
            case "ADDInternet": {
                float ans = Float.parseFloat(cableBoxAmnt) + Float.parseFloat(box_Amount);
                Log.i("cableBoxAmnt", String.valueOf(cableBoxAmnt));
                Log.i("box_Amount", String.valueOf(box_Amountupdate));
                Log.i("ans", String.valueOf(ans));
                totalAmntUpdate = ans + Float.parseFloat(CustomerBalance);
                Log.i("totalAmnt", String.valueOf(totalAmntUpdate));
                txt_subscription_amntUpdate.setText("Rs. " + ans);
                cableBoxAmnt = String.valueOf(ans);
                txt_total_amountUpdate.setText("Rs. " + String.valueOf(totalAmntUpdate));
                edt_payingamountUpdate.setText(String.valueOf(totalAmntUpdate));

                break;
            }
            case "SUB":
            case "SUBInternet": {
                float ans = Float.parseFloat(cableBoxAmnt) - Float.parseFloat(box_Amountupdate);
                Log.i("cableBoxAmnt", String.valueOf(cableBoxAmnt));
                Log.i("box_Amount", String.valueOf(box_Amountupdate));
                Log.i("ans", String.valueOf(ans));
                totalAmntUpdate = ans + Float.parseFloat(CustomerBalance);
                Log.i("totalAmnt", String.valueOf(totalAmntUpdate));
                cableBoxAmnt = String.valueOf(ans);
                Log.i("cableBoxAmnt", String.valueOf(cableBoxAmnt));
                txt_subscription_amntUpdate.setText("Rs. " + ans);
                txt_total_amountUpdate.setText("Rs. " + String.valueOf(totalAmntUpdate));
                edt_payingamountUpdate.setText(String.valueOf(totalAmntUpdate));

                break;
            }
            /////////////////
            case "strActivationDateInternet":
                presenter.updateInternetBox(this, view, view1, userId, String.valueOf(customerID), internet_Box_IDUpdate, "null", boxType_IDInternetUpdate, ipnoUpdateInternet, macaddUpdate, bill_Type_IDInternetUpdate, connection_Status_IDInternetUpdate, activation_DateInternetUpdate, expiry_DateInternetUpdate, noofMonthInternetUpdate, noofDaysInternetUpdate, "0", "null", box_AmountInternetUpdate, todayDateString());
                break;
            case "spn_no_of_monthsInternet": {
                spn_no_of_months.performClick();

                AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view5, int position, long id) {
                                noofMonthInternetUpdate = spn_no_of_months.getSelectedItem().toString();
                                noofMonth_multiplyfactorInternet = Integer.parseInt(noofMonthInternetUpdate);

                                //Toast.makeText(GenerateInvoiceActivity.this, "No of Month "+noofMonthInternet, Toast.LENGTH_SHORT).show();

                                //SharedPrefsData.putString(GenerateInvoiceActivity.this, Constants.NoofMonths, noofMonthInternet, Constants.PREF_NAME);

                                internetBoxList.get(box_positionUpdate).getInternetBox().setNoofMonth(noofMonth_multiplyfactorInternet);

                                Log.e("noofMonthInternet", noofMonthInternetUpdate);

                                view3.setText(noofMonthInternetUpdate);
                                presenter.updateInternetBox(UpdateSubscriptionAtivity.this, view, view1, userId, String.valueOf(customerID), internet_Box_IDUpdate, "null", boxType_IDInternetUpdate, ipnoUpdateInternet, macaddUpdate, bill_Type_IDInternetUpdate, connection_Status_IDInternetUpdate, activation_DateInternetUpdate, newInternetExpDateUpdate, noofMonthInternetUpdate, noofDaysInternetUpdate, "0", "null", String.valueOf(newBoxAmountUpdate), todayDateString());

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
                                bill_Type_IDInternetUpdate = bill_type.get(position).getBill_Type_ID().toString();
                                strBillTypeUpdateInternet = bill_type.get(position).getBill_Type().toString();
                                view4.setText(strBillTypeUpdateInternet);
                                if (strBillTypeUpdateInternet.equals("NoofDays")) {
                                    ll.setVisibility(View.VISIBLE);
                                } else {
                                    ll.setVisibility(View.GONE);
                                    presenter.updateInternetBox(UpdateSubscriptionAtivity.this, view, view1, userId, String.valueOf(customerID), internet_Box_IDUpdate, "null", boxType_IDInternetUpdate, ipnoUpdateInternet, macadd, bill_Type_IDInternetUpdate, connection_Status_IDInternetUpdate, activation_DateInternetUpdate, newInternetExpDateUpdate, noofMonthInternetUpdate, noofDaysInternetUpdate, "0", "null", box_AmountInternetUpdate, todayDateString());
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
                presenter.updateInternetBox(UpdateSubscriptionAtivity.this, view, view1, userId, String.valueOf(customerID), internet_Box_IDUpdate, "null", boxType_IDInternetUpdate, ipnoUpdateInternet, macaddUpdate, bill_Type_IDInternetUpdate, connection_Status_IDInternetUpdate, activation_DateInternetUpdate, expiry_DateInternetUpdate, noofMonthInternetUpdate, noofDaysInternetUpdate, "0", "null", box_AmountInternetUpdate, todayDateString());
                break;
            case "strNoOfDaysInternet":
                presenter.updateInternetBox(UpdateSubscriptionAtivity.this, view, view1, userId, String.valueOf(customerID), internet_Box_IDUpdate, "null", boxType_IDInternetUpdate, ipnoUpdateInternet, macaddUpdate, bill_Type_IDInternetUpdate, connection_Status_IDInternetUpdate, activation_DateInternetUpdate, expiry_DateInternetUpdate, noofMonthInternetUpdate, noofDaysInternetUpdate, "0", "null", box_AmountInternetUpdate, todayDateString());
                break;
            case "calculateExpiryInternet":

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

                Calendar calendar1 = Calendar.getInstance();
                try {
                    calendar1.setTime(Objects.requireNonNull(sdf1.parse(expiry_DateInternetUpdate))); // parsed date and setting to calendar
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (countInternet == 1) {
                    countInternet = countInternet + 1;
                    calendar1.add(Calendar.DATE, 1);  // number of days to add
                    activation_DateInternetUpdate = sdf1.format(calendar1.getTime());
                    Log.e("activation_DateInternet", activation_DateInternetUpdate);
                }
                presenter.updateInternetBox(UpdateSubscriptionAtivity.this, view, view1, userId, String.valueOf(customerID), internet_Box_IDUpdate, "null", boxType_IDInternetUpdate, ipnoUpdateInternet, macadd, bill_Type_IDInternetUpdate, connection_Status_IDInternetUpdate, activation_DateInternetUpdate, expiry_DateInternetUpdate, noofMonthInternetUpdate, noofDaysInternetUpdate, "0", "null", box_AmountInternetUpdate, todayDateString());
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

//        try {
//            triplePlay = SharedPrefsData.getString(UpdateSubscriptionAtivity.this, Constants.TriplePlay, Constants.PREF_NAME);
//
//
//            if (triplePlay.equals("Cable")) {
//                // presenter.loadCableBoxListApi(SharedPrefsData.getString(GenerateInvoiceActivity.this,Constants.CustomerID,Constants.PREF_NAME));
//
//                cableBoxList = cableBoxWithSubscriptionUpdate.getCableBoxwithSubscription();
//
//                float ans = Float.parseFloat(alacarteModelaUpdate.getTotal_Alacarte()) + Float.parseFloat(bouquetModelaUpdate.getTotal_Bouquet());
//                String newbox_Amount = String.valueOf(ans * noofMonth_multiplyfactor);
//
//                box_Amount = newbox_Amount;
//                cableBoxAmnt = newbox_Amount;
//
//                newBoxAmountUpdate = ans;
//
//
//                totalAmntUpdate = Float.parseFloat(newbox_Amount) + Float.parseFloat(CustomerBalance);
//                txt_subscription_amntUpdate.setText(newbox_Amount);
//                // cableBoxAmnt = String.valueOf(ans);
//                txt_total_amountUpdate.setText(String.valueOf(totalAmntUpdate));
//                edt_payingamountUpdate.setText(String.valueOf(totalAmntUpdate));
//
//                cableBoxList.get(box_positionUpdate).getCableBox().setBox_Amount(Double.parseDouble(newbox_Amount));
//                amount.setText(newbox_Amount);
//
//                cableBoxList.get(box_positionUpdate).getCableBox().setActivation_Date(activation_Date);
//
//                //cableBoxList.get(box_position).getCableBox().setExpiry_Date(activation_Date);
//
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//                rv_box_details.setLayoutManager(mLayoutManager);
//                cableBoxDetailsAdapter = new CableBoxAdapterForSubScriptionUpdate(UpdateSubscriptionAtivity.this, cableBoxList, adapterCallback, adapterCall, "A");
//                rv_box_details.setAdapter(cableBoxDetailsAdapter);
//
//            } else if (triplePlay.equals("Internet")) {
//                //presenter.loadInternetBoxListApi(SharedPrefsData.getString(GenerateInvoiceActivity.this, Constants.CustomerID, Constants.PREF_NAME));
//
//                internetBoxList = internetBoxWithSubscriptionUpdate.getInternetBoxwithSubscription();
//
//                ansUpdate = Float.parseFloat(String.valueOf(internetPackageModelaUpdate.getTotal_Package()));
//
//                String newbox_AmountInternet = String.valueOf(ansUpdate * noofMonth_multiplyfactorInternet);
//
//                box_Amount = newbox_AmountInternet;
//                cableBoxAmnt = newbox_AmountInternet;
//
//                newBoxAmountUpdate = ansUpdate;
//                newTotalAmountUpdate = String.valueOf(ansUpdate);
//
//                totalAmntUpdate = Float.parseFloat(newbox_AmountInternet) + Float.parseFloat(CustomerBalance);
//                txt_subscription_amntUpdate.setText(newbox_AmountInternet);
//                //internetBoxAmnt = String.valueOf(newbox_AmountInternet);
//                txt_total_amountUpdate.setText(String.valueOf(totalAmntUpdate));
//                edt_payingamountUpdate.setText(String.valueOf(totalAmntUpdate));
//
//                internetBoxList.get(box_positionUpdate).getInternetBox().setBox_Amount(Double.parseDouble(newbox_AmountInternet));
//                amountInternet.setText(newbox_AmountInternet);
//
//                internetBoxList.get(box_positionUpdate).getInternetBox().setActivation_Date(newInternetActDateUpdate);
//
//                internetBoxList.get(box_positionUpdate).getInternetBox().setExpiry_Date(newInternetExpDateUpdate);
//
//                SharedPrefsData.putString(this, Constants.TotalAmount, String.valueOf(internetBoxList.get(box_positionUpdate).getInternetBox().getBox_Amount()), Constants.PREF_NAME);
//
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//                rv_box_details.setLayoutManager(mLayoutManager);
//                internetBoxDetailsAdapter = new InternetBoxDetailsAdapter(UpdateSubscriptionAtivity.this, internetBoxList, adapterCallback, adapterCall);
//                rv_box_details.setAdapter(internetBoxDetailsAdapter);
//
//            }
//        } catch (Exception e) {
//            triplePlay = "";
//            e.printStackTrace();
//        }
    }

    @Override
    public void onClickCallback(View view, int position, String id) {
//        cableBoxList = cableBoxWithSubscriptionUpdate.getCableBoxwithSubscription();
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        rv_box_details.setLayoutManager(mLayoutManager);
//        cableBoxDetailsAdapter = new CableBoxAdapterForSubScriptionUpdate(UpdateSubscriptionAtivity.this, cableBoxList, adapterCallback, adapterCall, "A");
//        rv_box_details.setAdapter(cableBoxDetailsAdapter);


    }

}