package com.elifeindia.crm.view.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.CustomerListAdapter;
import com.elifeindia.crm.adapters.PagingAdapter;
import com.elifeindia.crm.contract.activities.CustomerListContract;
import com.elifeindia.crm.model.AreaResponse;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.model.PaymentStatusModel;
import com.elifeindia.crm.presenter.activities.CustomerListPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.github.aakira.expandablelayout.ExpandableLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class  CustomerListActivity extends AppCompatActivity implements CustomerListContract.View, AdapterCallback {
    CustomerListContract.Presenter presenter;
    ViewUtils viewUtils;
    RecyclerView rv_customer_list, rv_paging;
    PagingAdapter pagingAdapter;
    CustomerListAdapter customerListAdapter;
    ExpandableLayout expandableLayout;
    CardView cv_filter;
    ImageButton ivbtn_search_submit;
    String areaId = "0", companyId, empId, userId, StatusId = "0", selectCont = "100", pageNo = "1", Value = "";
    Spinner spn_area, spn_status;
    EditText custmersearch_edit;
    ArrayAdapter<String> areaListAdapter, adapterStatus;
    ImageView iv_calendar, iv_search;
    AdapterCallback adapterCallback;
    TextView txt_not_found, tool_bar_text, txt_req_date;
    float result, i = 1, j = 1;

    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        spn_area = findViewById(R.id.spn_area);
        iv_calendar = findViewById(R.id.iv_calendar);
        custmersearch_edit = findViewById(R.id.custmersearch_edit);
        ivbtn_search_submit = findViewById(R.id.ivbtn_search_submit);
//        iv_search = findViewById(R.id.iv_search);
        spn_status = findViewById(R.id.spn_status);
        tool_bar_text = findViewById(R.id.tool_bar_text);
        rv_paging = findViewById(R.id.rv_paging);
        txt_req_date = findViewById(R.id.txt_req_date);
        txt_not_found = findViewById(R.id.txt_not_found);

        viewUtils = new ViewUtils();
        presenter = new CustomerListPresenter(this);
        presenter.start();
        adapterCallback = this;
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");

        Bundle b = getIntent().getExtras();


//        iv_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                presenter.loadApi(CustomerListActivity.this, companyId, userId, empId, custmersearch_edit.getText().toString());
//            }
//        });

//        custmersearch_edit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                String s = charSequence.toString();
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                Value=charSequence.toString();
//                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//
//            }
//        });

        ivbtn_search_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Value = custmersearch_edit.getText().toString();
                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
                progressBar.show();

            }
        });

        custmersearch_edit.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Value = custmersearch_edit.getText().toString();
                    presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
                    progressBar.show();

                    return true;
                }
                return false;
            }
        });


        iv_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // To show current date in the datepicker
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(
                        CustomerListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker,
                                          int selectedyear, int selectedmonth,
                                          int selectedday) {

                        mcurrentDate.set(Calendar.YEAR, selectedyear);
                        mcurrentDate.set(Calendar.MONTH, selectedmonth);
                        mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.US);
                        txt_req_date.setText(sdf.format(mcurrentDate.getTime()));
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                        String date = sdf1.format(mcurrentDate.getTime());
                        presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, date, StatusId, selectCont, pageNo, Value);
                        //SharedPrefsData.putString(CustomerListActivity.this, Constants.RequestDate, txt_req_date.getText().toString(), Constants.PREF_NAME);

                    }
                }, mYear, mMonth, mDay);
                // mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDatePicker.setTitle("Select Expiry Date");
                mDatePicker.show();
            }

        });

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        cv_filter = findViewById(R.id.cv_filter);
        cv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableLayout.isExpanded()) {
                    expandableLayout.collapse();
                } else {
                    expandableLayout.expand();
                }
            }
        });

        companyId = SharedPrefsData.getString(this, Constants.CompanyID, Constants.PREF_NAME);
        userId = SharedPrefsData.getString(this, Constants.UserId, Constants.PREF_NAME);
        empId = String.valueOf(SharedPrefsData.getInt(this, Constants.EmpId, Constants.PREF_NAME));
        areaId = SharedPrefsData.getString(this, Constants.CustAreaId, Constants.PREF_NAME);
        SharedPrefsData.putString(this, Constants.PageNo, "1", Constants.PREF_NAME);
        SharedPrefsData.putString(this, Constants.ReceiptFlag, "false", Constants.ReceiptFlag);
        presenter.loadArea(CustomerListActivity.this, companyId, empId);
        presenter.getPaymentStatus(CustomerListActivity.this, "0");


        //presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "");

        presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);


    }

    @Override
    public void init() {
        rv_customer_list = findViewById(R.id.rv_customer_list);
        expandableLayout = findViewById(R.id.expandableLayout);


    }

    @Override
    public void showError(String message) {
        // viewUtils.toast(this, message);
    }

    @Override
    public void showResult(CustemersList custemersList) {
        List<CustemersList.Customer> custemersLists = custemersList.getCustomer();

        tool_bar_text.setText("Customer List (" + custemersList.getRowcount() + ")");

        if (custemersLists.size() == 0) {
            rv_customer_list.setVisibility(View.GONE);
            txt_not_found.setVisibility(View.VISIBLE);
            //viewUtils.showAlertDialog(this, "Data not found", "There is no data you are searching for");
        } else {
            txt_not_found.setVisibility(View.GONE);
            rv_customer_list.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_customer_list.setLayoutManager(mLayoutManager);
            customerListAdapter = new CustomerListAdapter(this, custemersLists);
            rv_customer_list.setAdapter(customerListAdapter);
        }

    }

    @Override
    public void showSearchResult(CustemersList custemersList) {
        List<CustemersList.Customer> custemersLists = custemersList.getCustomer();
        tool_bar_text.setText("Customer List (" + custemersList.getRowcount() + ")");

        if (custemersLists.size() == 0) {
            rv_customer_list.setVisibility(View.GONE);
            txt_not_found.setVisibility(View.VISIBLE);

            //viewUtils.showAlertDialog(this, "Data not found", "There is no data you are searching for");
        } else {
            txt_not_found.setVisibility(View.GONE);
            rv_customer_list.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_customer_list.setLayoutManager(mLayoutManager);
            customerListAdapter = new CustomerListAdapter(this, custemersLists);
            rv_customer_list.setAdapter(customerListAdapter);
        }
    }

    @Override
    public void showArea(final AreaResponse areaResponse) {
        final List<AreaResponse.Area> area = areaResponse.getArea();
        ArrayList<String> areaNames = new ArrayList<String>();
        final ArrayList<String> areaIds = new ArrayList<String>();
        areaNames.add("All Areas");
        areaIds.add("0");
        for (int i = 0; i < area.size(); i++) {
            areaNames.add(area.get(i).getAreaName());
            areaIds.add(area.get(i).getAreaID().toString());
        }
        areaListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, areaNames);
        areaListAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_area.setAdapter(areaListAdapter);
        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if (position > 0) {
                            Value = "";
                            custmersearch_edit.setText("");
                            areaId = areaIds.get(position);
                            SharedPrefsData.putString(CustomerListActivity.this, Constants.CustAreaId, areaId, Constants.PREF_NAME);
                            areaId = SharedPrefsData.getString(CustomerListActivity.this, Constants.CustAreaId, Constants.PREF_NAME);
                            presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);

                            if (i != 1) {
                                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
                                progressBar.show();

                            }

                        } else {
                            areaId = "0";
                            SharedPrefsData.putString(CustomerListActivity.this, Constants.CustAreaId, areaId, Constants.PREF_NAME);
                            Value = "";
                            custmersearch_edit.setText("");
                            // presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);

                            if (i != 1) {
                                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
                                progressBar.show();

                            }
                        }
//                        if(position==0){
//                            areaId="";
//
//                        }else{
//                            try {
//                                areaId = area.get(position).getAreaID().toString();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//
//                            }
//                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
        spn_area.setOnItemSelectedListener(onItemSelectedListener2);

    }

    @Override
    public void showPaymentStatus(PaymentStatusModel areaResponse) {

        final List<PaymentStatusModel.PaymentstatusDTO> paymentstatus = areaResponse.getPaymentstatus();

        ArrayList<String> StatusNames = new ArrayList<String>();
        final ArrayList<String> statusIds = new ArrayList<String>();
        StatusNames.add("All Status");
        statusIds.add("0");
        for (int i = 0; i < paymentstatus.size(); i++) {
            StatusNames.add(paymentstatus.get(i).getPayment_Status());
            statusIds.add((paymentstatus.get(i).getStatus_ID()));
        }

        adapterStatus = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, StatusNames);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_status.setAdapter(adapterStatus);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if (position > 0) {
                            Value = "";
                            custmersearch_edit.setText("");
                            StatusId = statusIds.get(position);
                            SharedPrefsData.putString(CustomerListActivity.this, Constants.StatusId, StatusId, Constants.PREF_NAME);
                            StatusId = SharedPrefsData.getString(CustomerListActivity.this, Constants.StatusId, Constants.PREF_NAME);
                            presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
                            if (i != 1) {
                                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
                                progressBar.show();

                            }
                        } else {

                            StatusId = "0";
                            SharedPrefsData.putString(CustomerListActivity.this, Constants.StatusId, StatusId, Constants.PREF_NAME);
                            Value = "";
                            custmersearch_edit.setText("");
                            presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
                            if (i != 1) {
                                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
                                progressBar.show();

                            }
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
        spn_status.setOnItemSelectedListener(onItemSelectedListener2);

    }

    @Override
    public void showCustomersDateWise(CustemersList custemersList) {
        progressBar.dismiss();
        List<CustemersList.Customer> custemersLists = custemersList.getCustomer();
        tool_bar_text.setText("Customer List (" + custemersList.getRowcount() + ")");

        float f = custemersList.getRowcount();
        result = f / 100;
        if (custemersList.getRowcount() < 100) {
            result = 1;
        } else {
            String s = String.valueOf(result);
            char c1 = '0';
            char c2 = '.';
            boolean resul = true;
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != c1 || s.charAt(i) != c2) {
                    resul = false;
                }
            }
            if (!resul)
                result = result + 1;

        }


        if (j == 1) {
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            rv_paging.setLayoutManager(mLayoutManager1);
            pagingAdapter = new PagingAdapter(this, (int) result, adapterCallback);
            rv_paging.setAdapter(pagingAdapter);
            j = j + 1;
        }


        if (custemersLists.size() == 0) {
            rv_customer_list.setVisibility(View.GONE);
            txt_not_found.setVisibility(View.VISIBLE);

            //viewUtils.showAlertDialog(this, "Data not found", "There is no data with the filter you applied");
        } else {
            txt_not_found.setVisibility(View.GONE);
            i = i + 1;
            rv_customer_list.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_customer_list.setLayoutManager(mLayoutManager);
            customerListAdapter = new CustomerListAdapter(this, custemersLists);
            rv_customer_list.setAdapter(customerListAdapter);
        }

    }

    @Override

    protected void onResume() {
        super.onResume();

        if (SharedPrefsData.getString(this, Constants.ReceiptFlag, Constants.ReceiptFlag).equals("true")) {
            SharedPrefsData.putString(this, Constants.ReceiptFlag, "false", Constants.ReceiptFlag);
            pageNo = SharedPrefsData.getString(this, Constants.PageNo, Constants.PREF_NAME);
            Value = "";
            custmersearch_edit.setText("");
            presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
            progressBar = new ProgressDialog(this);
            progressBar.setCancelable(false);//you can cancel it by pressing back button
            progressBar.setMessage("Please wait...");
            progressBar.show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(CustomerListActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void onClickCallback(View view, int position, String id) {
        SharedPrefsData.putString(this, Constants.PageNo, id, Constants.PREF_NAME);
        pageNo = id;
        Value = "";
        custmersearch_edit.setText("");
        presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
    }

}