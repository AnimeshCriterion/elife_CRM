package com.elifeindia.crm.view.activities;

import static com.elifeindia.crm.view.activities.HomeActivity.hideUpdateSubscription;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import com.elifeindia.crm.Capture;
import android.view.MenuItem;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.CustomerListAdapter;
import com.elifeindia.crm.adapters.FilterTypeAdapter;
import com.elifeindia.crm.adapters.PagingAdapter;
import com.elifeindia.crm.contract.activities.CustomerListContract;
import com.elifeindia.crm.model.AreaResponse;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.model.PaymentStatusModel;
import com.elifeindia.crm.networking.AdapterInterface;
import com.elifeindia.crm.presenter.activities.CustomerListPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.StaticAppData;
import com.elifeindia.crm.utils.ViewUtils;
//import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class CustomerListActivity extends AppCompatActivity implements CustomerListContract.View, AdapterCallback , AdapterInterface {
    CustomerListContract.Presenter presenter;
    ViewUtils viewUtils;
    RecyclerView rv_customer_list, rv_paging;
    PagingAdapter pagingAdapter;
    CustomerListAdapter customerListAdapter;
   LinearLayout expandableLayout;
    CardView cv_filter;
    ImageButton ivbtn_search_submit,iv_scan_button;
    String areaId = "0", companyId, empId, userId, StatusId = "0", selectCont = "100", pageNo = "1", Value = "", field_value = "", field_name = "";
    Spinner spn_area, spn_status;
    EditText custmersearch_edit;
    ArrayAdapter<String> areaListAdapter, adapterStatus;
    ImageView iv_calendar, iv_search;
    AdapterCallback adapterCallback;
    TextView txt_not_found, tool_bar_text, txt_req_date;
    float result, i = 1, j = 1;
    RecyclerView recyclerViewFilterType;
    FilterTypeAdapter filterTypeAdapter;

    ProgressDialog progressBar;
    ImageView filter_image;
    View view;
    String customerID;
    LinearLayout daterange;
    TextView fromDate,toDate;
    String fromDateData,toDataData;
    FragmentManager mFragmentManager;
    ImageView calenderDateRange;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        Log.d("TAG", "onCreate: Anmesh pratatp singh");

        spn_area = findViewById(R.id.spn_area);
        iv_calendar = findViewById(R.id.iv_calendar);
        fromDate=findViewById(R.id.from_date);
        toDate=findViewById(R.id.to_date);
        iv_scan_button=findViewById(R.id.iv_scan_button);
        calenderDateRange=findViewById(R.id.calenderDateRange);
        custmersearch_edit = findViewById(R.id.custmersearch_edit);
        filter_image=findViewById(R.id.filter_image);
        ivbtn_search_submit = findViewById(R.id.ivbtn_search_submit);
        daterange=findViewById(R.id.daterange);
        expandableLayout=findViewById(R.id.fillterLayout);
//      iv_search = findViewById(R.id.iv_search);
        spn_status = findViewById(R.id.spn_status);
        tool_bar_text = findViewById(R.id.tool_bar_text);
        rv_paging = findViewById(R.id.rv_paging);
        txt_req_date = findViewById(R.id.txt_req_date);
        txt_not_found = findViewById(R.id.txt_not_found);
        recyclerViewFilterType = findViewById(R.id.rvFilterSelection);
        recyclerViewFilterType.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        filterTypeAdapter = new FilterTypeAdapter(this, StaticAppData.filterDataList());
        recyclerViewFilterType.setAdapter(filterTypeAdapter);
       // areaId = SharedPrefsData.getString(this, Constants.CustAreaId, Constants.PREF_NAME);
        expandableLayout.setVisibility(View.GONE);
        calenderDateRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadRangeDatePickerMultipleBooking();

            }
        });

        filter_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expandableLayout.getVisibility()==View.VISIBLE){
                    expandableLayout.setVisibility(View.GONE);
                }else{
                    expandableLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        iv_scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(CustomerListActivity.this);
                //set prompt text
                intentIntegrator.setPrompt("for flash volume up key");
                //locked orientation
                intentIntegrator.setOrientationLocked(true);
                //set beep enabled
                intentIntegrator.setBeepEnabled(true);
                //set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
                //initiate scann prcess
                intentIntegrator.initiateScan();
            }
        });


        viewUtils = new ViewUtils();
        presenter = new CustomerListPresenter(this);
        presenter.start();
        adapterCallback = this;
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        Bundle b = getIntent().getExtras();

        filterTypeAdapter.setListener(position -> {
            if (StaticAppData.filterDataList().get(position).matches("All")) {
                field_name = "";
                daterange.setVisibility(View.GONE);
                progressBar.show();
                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, "0",  StatusId, selectCont, pageNo, "", "", "","","");
            } else if (StaticAppData.filterDataList().get(position).matches("Name")) {
                field_name = "name";
                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, StatusId, selectCont, pageNo, "", "", field_name,"","");
            } else if (StaticAppData.filterDataList().get(position).matches("A/c No")) {
                field_name = "account_no";
                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, "", "", field_name,"","");

            } else if (StaticAppData.filterDataList().get(position).matches("Mobile")) {
                field_name = "contact_no";
                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, "", "", field_name,"","");

            } else if (StaticAppData.filterDataList().get(position).matches("Sub ID")) {
                field_name = "Subscriber_id";
                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, "", "", field_name,"","");

            } else if (StaticAppData.filterDataList().get(position).matches("Box No")) {
                field_name = "Box_no";
                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, "", "", field_name,"","");

            }

        });


        ivbtn_search_submit.setOnClickListener(view -> {
            Value = custmersearch_edit.getText().toString();
            if (field_name.isEmpty()) {
                progressBar.show();
                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, " ", " "," "," ");
            } else {
                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, StatusId, selectCont, pageNo, " ", " ", field_name," "," ");

            }
        });




        iv_calendar.setOnClickListener(view -> {
            // To show current date in the datepicker
            final Calendar mcurrentDate = Calendar.getInstance();
            int mYear = mcurrentDate.get(Calendar.YEAR);
            int mMonth = mcurrentDate.get(Calendar.MONTH);
            int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog mDatePicker = new DatePickerDialog(
                    CustomerListActivity.this, (datepicker, selectedyear, selectedmonth, selectedday) -> {

                mcurrentDate.set(Calendar.YEAR, selectedyear);
                mcurrentDate.set(Calendar.MONTH, selectedmonth);
                mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.US);
                txt_req_date.setText(sdf.format(mcurrentDate.getTime()));
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                String date = sdf1.format(mcurrentDate.getTime());
                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, StatusId, selectCont, pageNo, Value, field_value, field_name,fromDateData,toDataData);
                Log.d("TAG", "onCreateStep3: ");

                //SharedPrefsData.putString(CustomerListActivity.this, Constants.RequestDate, txt_req_date.getText().toString(), Constants.PREF_NAME);

            }, mYear, mMonth, mDay);
            // mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            mDatePicker.setTitle("Select Expiry Date");
            mDatePicker.show();
        });

        findViewById(R.id.iv_back).setOnClickListener(view -> {
            onBackPressed();
            finish();
        });
        cv_filter = findViewById(R.id.cv_filter);
        cv_filter.setOnClickListener(view -> {
//            if (expandableLayout.isExpanded()) {
//                expandableLayout.collapse();
//            } else {
//                expandableLayout.expand();
//            }
        });

        companyId = SharedPrefsData.getString(this, Constants.CompanyID, Constants.PREF_NAME);
        userId = SharedPrefsData.getString(this, Constants.UserId, Constants.PREF_NAME);
        empId = SharedPrefsData.getString(this, Constants.EmpId, Constants.PREF_NAME);
      //  areaId = SharedPrefsData.getString(this, Constants.CustAreaId, Constants.PREF_NAME);
        SharedPrefsData.putString(this, Constants.PageNo, "1", Constants.PREF_NAME);
        SharedPrefsData.putString(this, Constants.ReceiptFlag, "false", Constants.ReceiptFlag);
        presenter.loadArea(CustomerListActivity.this, companyId, empId);
        presenter.getPaymentStatus(CustomerListActivity.this, "0");
       // presenter.loadApi(CustomerListActivity.this, companyId, userId, empId, Value, "100", "1");

         presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, "0", StatusId, selectCont, pageNo, Value, field_value, field_name,"","");
        Log.d("TAG", "onCreateStep4: ");
        RxTextView.textChanges(custmersearch_edit)
                .debounce(1, TimeUnit.SECONDS)
                .subscribe(textChanged -> {
                    Value = custmersearch_edit.getText().toString();
                    if (field_name.isEmpty()) {
                        try {
                            if(fromDateData==null && toDataData==null){
                                Log.d("TAG", "onnnnnn2");
                                Log.d("TAG", "onCreate: "+Value.toString());
                           //     presenter.loadApiSearch(CustomerListActivity.this, companyId, userId, empId, Value, selectCont, pageNo);
                                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, StatusId, selectCont, pageNo, Value, field_value, field_name,"","");

                            }
                            else {
                                Log.d("TAG", "onnnnnn1");
                                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, " ", " ",fromDateData,toDataData);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, StatusId, selectCont, pageNo, Value, field_value, field_name,"","");
                        Log.d("TAG", "onCreateStep2: ");
                     //   presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, StatusId, selectCont, pageNo, Value, "", field_name,"","");

                    }
                });


    }

    private void openAlertScanner() {

    }

    private void loadRangeDatePickerMultipleBooking() {
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        long today = MaterialDatePicker.todayInUtcMilliseconds();


        int dialogTheme = 0;
        TypedValue typedValue = new TypedValue();
        if (CustomerListActivity.this.getTheme().resolveAttribute(com.google.android.material.R.attr.materialCalendarTheme, typedValue, true)) {
            dialogTheme = typedValue.data;
        }

        Calendar calendarForPair = new GregorianCalendar();
        calendarForPair.add(Calendar.DATE, 0);
        long after = calendarForPair.getTimeInMillis();
        Pair<Long, Long> todayPair = new Pair<>(today, after);
        builder.setSelection(todayPair);
        builder.setTheme(dialogTheme);
        builder.setTitleText("Select Range of Dates");


        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.setTimeInMillis(today);
        calendar.roll(Calendar.YEAR, 1);
        long oneYearForward = calendar.getTimeInMillis();


        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setStart(today);
        constraintsBuilder.setEnd(oneYearForward);
        constraintsBuilder.setValidator(DateValidatorPointForward.now());

         builder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker<?> picker = builder.build();

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {

                Pair<Long, Long> datepair = (Pair<Long, Long>) selection;
                Log.d("Calendar: ", selection.toString());
                Date dateObjectStart = new Date(datepair.first);
                Date dateObjectEnd = new Date(datepair.second);

                //Set Date in Required Format
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
                String selectedDateStart = dateTimeFormat.format(dateObjectStart);
                String selectedDateEnd = dateTimeFormat.format(dateObjectEnd);
                Log.d("Calendar: ", " " + selectedDateStart + " " + selectedDateEnd);

                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, "", "",selectedDateStart,selectedDateEnd);
                daterange.setVisibility(View.VISIBLE);
                fromDate.setText(("From Date :"+selectedDateStart));
                toDate.setText(("To Date :"+selectedDateEnd));

                fromDateData = selectedDateStart;
                toDataData = selectedDateEnd;


                progressBar.show();


                //showSlotSelectionCustomDialog(datepair.first,datepair.second);
                //startRecurringBedSelectionFragment(datepair.first,datepair.second);

            }
        });
        picker.show(getSupportFragmentManager(), "");
    }

    @Override
    public void init() {
        rv_customer_list = findViewById(R.id.rv_customer_list);
//        expandableLayout = findViewById(R.id.expandableLayout);


    }

    @Override
    public void showError(String message) {
        // viewUtils.toast(this, message);
        progressBar.dismiss();
    }

    @Override
    public void showResult(CustemersList custemersList) {
        progressBar.dismiss();
        List<CustemersList.Customer> custemersLists = custemersList.getCustomer();
        tool_bar_text.setText("Customer List (" + custemersList.getRowcount() + ")");

        if (custemersLists.size() == 0) {
            rv_customer_list.setVisibility(View.GONE);
            txt_not_found.setVisibility(View.VISIBLE);
            //viewUtils.showAlertDialog(this, "Data not found", "There is no data you are searching for");
        } else {
            txt_not_found.setVisibility(View.GONE);
            rv_customer_list.setVisibility(View.VISIBLE);
            rv_customer_list.setItemViewCacheSize(custemersLists.size());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_customer_list.setLayoutManager(mLayoutManager);
            customerListAdapter = new CustomerListAdapter(this, custemersLists,this);
            rv_customer_list.setAdapter(customerListAdapter);
            customerListAdapter.notifyDataSetChanged();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //initiale intent result
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check condition
        if (intentResult.getContents() != null) {
            //when result content is not null
            //initialze alert dialog


            if (intentResult.getContents() != null) {
                Toast.makeText(CustomerListActivity.this, "Sub Id: "+intentResult.getContents(), Toast.LENGTH_SHORT).show();
                Value=intentResult.getContents();
                if (field_name.isEmpty()) {
                    progressBar.show();
                    presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, "", ""," "," ");
                } else {
                    presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, "", Value, field_name," "," ");

                }
            } else {
                Toast.makeText(CustomerListActivity.this, "Please Scan QR code again!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(CustomerListActivity.this, "OOPs ... You did not scan any QR", Toast.LENGTH_SHORT).show();
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
            customerListAdapter = new CustomerListAdapter(this, custemersLists,this);
            rv_customer_list.setAdapter(customerListAdapter);
            customerListAdapter.notifyDataSetChanged();
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
                         //   areaId = SharedPrefsData.getString(CustomerListActivity.this, Constants.CustAreaId, Constants.PREF_NAME);
                            presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, field_value, field_name," "," ");

                            if (i != 1) {
                                progressBar.show();
                                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, field_value, field_name, " "," ");

                            }

                        } else {
                            areaId = "0";
                            SharedPrefsData.putString(CustomerListActivity.this, Constants.CustAreaId, areaId, Constants.PREF_NAME);
                            Value = "";
                            custmersearch_edit.setText("");
                            presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, StatusId, selectCont, pageNo, Value, field_value, field_name," "," ");

                            if (i != 1) {
                                progressBar.show();
                                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, field_value, field_name," "," ");


                            }
                        }
                        if(position==0){
                            areaId="0";

                        }else{
                            try {
                                areaId = area.get(position).getAreaID().toString();
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        }


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
                            progressBar.show();
                            presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, field_value, field_name," "," ");
                            if (i != 1) {
                                progressBar.show();
                                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, field_value, field_name," "," ");

                            }
                        } else {

                            StatusId = "0";
                            SharedPrefsData.putString(CustomerListActivity.this, Constants.StatusId, StatusId, Constants.PREF_NAME);
                            Value = "";
                            custmersearch_edit.setText("");
                            progressBar.show();
                            presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, field_value, field_name,fromDateData,toDataData);
                            if (i != 1) {
                                progressBar.show();
                                presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId,  StatusId, selectCont, pageNo, Value, field_value, field_name,fromDateData,toDataData);


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
            customerListAdapter = new CustomerListAdapter(this, custemersLists,this);
            rv_customer_list.setAdapter(customerListAdapter);
            customerListAdapter.notifyDataSetChanged();
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
            presenter.loadArea(this,companyId,empId);
            presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, StatusId, selectCont, pageNo, Value, field_value, field_name,fromDateData,toDataData);
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
        presenter.loadCustomersDateWise(CustomerListActivity.this, companyId, userId, empId, areaId, StatusId, selectCont, pageNo, Value, field_value, field_name,fromDateData,toDataData);
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
    }

    @Override
    public void onItemClicked(Object custemersLists, ImageView imageActionButton) {
        CustemersList.Customer customer=(CustemersList.Customer)custemersLists;
        customerID=String.valueOf(customer.getCustomerID());
        PopupMenu popup = new PopupMenu(CustomerListActivity.this,imageActionButton);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.customeractionmenu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                if ("Update Subscription".equals(item.getTitle().toString())) {

                    if(hideUpdateSubscription==true) {
                        Log.d("TAG", "onMenuItemClick: " + customer.getCustomerID());
                        SharedPrefsData.putBool(CustomerListActivity.this, Constants.isLoadAlacarte, true, Constants.PREF_NAME);
                        SharedPrefsData.putBool(CustomerListActivity.this, Constants.isLoadInternetPkg, true, Constants.PREF_NAME);
                        SharedPrefsData.putBool(CustomerListActivity.this, Constants.isLoadBouquet, true, Constants.PREF_NAME);
                        SharedPrefsData.putString(CustomerListActivity.this, Constants.CustomerID, String.valueOf(customer.getCustomerID()), Constants.PREF_NAME);
                        Intent intent = new Intent(CustomerListActivity.this, UpdateSubscriptionAtivity.class);
                        intent.putExtra("CustomerID", customer);
                        startActivity(intent);
                    }
                    else {
                           Toast.makeText(CustomerListActivity.this, "You are not Authorize to access this features !", Toast.LENGTH_SHORT).show();
                    }

                }


                else {
                    Toast.makeText(CustomerListActivity.this, "Coming Soon !", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        popup.show();//showing popup menu
    }
}