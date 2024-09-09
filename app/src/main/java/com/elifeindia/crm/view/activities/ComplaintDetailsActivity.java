package com.elifeindia.crm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.ComplaintListAdapter;
import com.elifeindia.crm.contract.activities.ComplaintListContract;
import com.elifeindia.crm.model.ComplaintList;
import com.elifeindia.crm.model.ComplaintStatusList;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.presenter.activities.ComplaintListPresenter;
import com.elifeindia.crm.printersdk.ComplaintReceiptActivity;
import com.elifeindia.crm.printersdk.Constant;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
//import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import static com.elifeindia.crm.adapters.ComplaintListAdapter.spn_complaint_status;

public class ComplaintDetailsActivity extends AppCompatActivity implements ComplaintListContract.View, AdapterCallback {

    ComplaintListContract.Presenter presenter;
    ViewUtils viewUtils;
    RecyclerView rv_payment_list;
    ImageView filter_image;
    ComplaintListAdapter complaintListAdapter;
    LinearLayout expandableLayout;
    CardView cv_filter;
    String areaId = "", companyId, empId="0", custId="0", fromDate, toDate, statusId="0", value="", roleType="";
    Spinner spn_emp, spn_status;
    EditText paymentsearch_edit;
    ArrayAdapter<String> adapter_emp, adapter_status;
    ImageView iv_calendar; LinearLayout ll_employee;
    TextView txt_not_found, all ,today, yesterday, thismonth, datepicker, from_date, to_date, txt_total_balance, txt_total_collection;
    FragmentManager mFragmentManager;
    AdapterCallback adapterCallback;
   // ProgressBar pb_searching;
    List<ComplaintStatusList.Complaintstatus> complaintstatuses;
    ArrayList<String> statusNames, statusIds;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);
        filter_image=findViewById(R.id.filter_imageMatch);
        viewUtils = new ViewUtils();
        presenter = new ComplaintListPresenter(this);
        presenter.start();
        adapterCallback=this;


        from_date.setText((todayDateString()));
        from_date.setVisibility(View.GONE);
        mFragmentManager=getSupportFragmentManager();

        companyId = SharedPrefsData.getString(ComplaintDetailsActivity.this, Constants.CompanyID, Constants.PREF_NAME);

        //Toast.makeText(this, "Company Id "+companyId, Toast.LENGTH_SHORT).show();

        custId = SharedPrefsData.getString(ComplaintDetailsActivity.this, Constants.CustomerID, Constants.PREF_NAME);

        //Toast.makeText(this, "Customer Id "+custId, Toast.LENGTH_SHORT).show();

        roleType = SharedPrefsData.getString(this, Constants.RoleType, Constants.PREF_NAME);
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

        if(roleType.equals("Admin")){

//            empId="0";
            empId =SharedPrefsData.getString(this, Constants.EmpId, Constants.PREF_NAME);
            ll_employee.setVisibility(View.VISIBLE);

        }else{

            ll_employee.setVisibility(View.GONE);
            empId = SharedPrefsData.getString(ComplaintDetailsActivity.this, Constants.EmpId, Constants.PREF_NAME);

        }

        //Toast.makeText(this, "Employee id "+empId, Toast.LENGTH_SHORT).show();

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                today.setBackgroundResource(R.color.white);
                today.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                all.setBackgroundResource(R.color.colorPrimaryDark);
                all.setTextColor(getResources().getColor(R.color.white));

                yesterday.setBackgroundResource(R.color.white);
                yesterday.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                thismonth.setBackgroundResource(R.color.white);
                thismonth.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                datepicker.setBackgroundResource(R.color.white);
                datepicker.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                //fromDate = getYesterdayDateString();
                //toDate = getYesterdayDateString();

                from_date.setVisibility(View.GONE);
                to_date.setVisibility(View.GONE);
                //from_date.setText(ViewUtils.changeDateFormat(todayDateString()));
                presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  null, null, "");


            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                today.setBackgroundResource(R.color.colorPrimaryDark);
                today.setTextColor(getResources().getColor(R.color.white));

                all.setBackgroundResource(R.color.white);
                all.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                yesterday.setBackgroundResource(R.color.white);
                yesterday.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                thismonth.setBackgroundResource(R.color.white);
                thismonth.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                datepicker.setBackgroundResource(R.color.white);
                datepicker.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                fromDate = todayDateString();
                toDate = todayDateString();

                to_date.setVisibility(View.GONE);
                from_date.setVisibility(View.VISIBLE);
                from_date.setText(ViewUtils.changeDateFormat(todayDateString()));
                presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  fromDate, toDate, "");

            }
        });

        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                today.setBackgroundResource(R.color.white);
                today.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                all.setBackgroundResource(R.color.white);
                all.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                yesterday.setBackgroundResource(R.color.colorPrimaryDark);
                yesterday.setTextColor(getResources().getColor(R.color.white));

                thismonth.setBackgroundResource(R.color.white);
                thismonth.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                datepicker.setBackgroundResource(R.color.white);
                datepicker.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                fromDate = getYesterdayDateString();
                toDate = getYesterdayDateString();

                to_date.setVisibility(View.GONE);
                from_date.setVisibility(View.VISIBLE);
                from_date.setText(ViewUtils.changeDateFormat(getYesterdayDateString()));

                presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  fromDate, toDate, "");
            }
        });
        thismonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                today.setBackgroundResource(R.color.white);
                today.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                all.setBackgroundResource(R.color.white);
                all.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                yesterday.setBackgroundResource(R.color.white);
                yesterday.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                thismonth.setBackgroundResource(R.color.colorPrimaryDark);
                thismonth.setTextColor(getResources().getColor(R.color.white));

                datepicker.setBackgroundResource(R.color.white);
                datepicker.setTextColor(getResources().getColor(R.color.colorPrimaryDark));


                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, 0);
                calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                Date monthFirstDay = calendar.getTime();
                calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                Date monthLastDay = calendar.getTime();

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String startDateStr = df.format(monthFirstDay);
                String endDateStr = df.format(monthLastDay);

                from_date.setVisibility(View.VISIBLE);
                from_date.setText(ViewUtils.changeDateFormat(startDateStr));
                to_date.setVisibility(View.VISIBLE);
                to_date.setText(ViewUtils.changeDateFormat(endDateStr));

                fromDate = startDateStr;
                toDate = endDateStr;

                presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  fromDate, toDate, "");
            }
        });
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                today.setBackgroundResource(R.color.white);
                today.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                yesterday.setBackgroundResource(R.color.white);
                yesterday.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                thismonth.setBackgroundResource(R.color.white);
                thismonth.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                datepicker.setBackgroundResource(R.color.colorPrimaryDark);
                datepicker.setTextColor(getResources().getColor(R.color.white));


                loadRangeDatePickerMultipleBooking();

            }
        });



        paymentsearch_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.loadComplaintListForSearch(ComplaintDetailsActivity.this, companyId, empId, custId, statusId,  fromDate, toDate, charSequence.toString());
              //  pb_searching.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        cv_filter = findViewById(R.id.cv_filter);
        cv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (expandableLayout.isExpanded()) {
//                    expandableLayout.collapse();
//                } else {
//                    expandableLayout.expand();
//                }
            }
        });

        //fromDate = getYesterdayDateString();
        //toDate = getYesterdayDateString();

        presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  null, null, "");
        presenter.loadEmployeeList(ComplaintDetailsActivity.this, companyId, empId, roleType);
        presenter.loadComplaintStatus(ComplaintDetailsActivity.this, companyId, "0");


    }

    private String todayDateString() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());

    }
    private String getYesterdayDateString() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());

    }
    public void loadRangeDatePickerMultipleBooking() {
        MaterialDatePicker.Builder<Pair<Long,Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        long today = MaterialDatePicker.todayInUtcMilliseconds();


        int dialogTheme=0;
        TypedValue typedValue = new TypedValue();
        Log.d("mActivityReference:",ComplaintDetailsActivity.this.toString());
        if (ComplaintDetailsActivity.this.getTheme().resolveAttribute(com.google.android.material.R.attr.materialCalendarTheme, typedValue, true)) {
            dialogTheme=typedValue.data;
        }

        Calendar calendarForPair=new GregorianCalendar();
        calendarForPair.add(Calendar.DATE,0);
        long after=calendarForPair.getTimeInMillis();
        Pair<Long, Long> todayPair = new Pair<>(today, after);
        builder.setSelection(todayPair);
        builder.setTheme(dialogTheme);
        builder.setTitleText("Select Range of Dates");


        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.setTimeInMillis(today);
        calendar.roll(Calendar.YEAR, 1);
        long oneYearForward = calendar.getTimeInMillis();


//        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
//        constraintsBuilder.setStart(today);
//        constraintsBuilder.setEnd(oneYearForward);
//        constraintsBuilder.setValidator(DateValidatorPointForward.now());

        // builder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker<?> picker=builder.build();

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {

                Pair<Long,Long> datepair=(Pair<Long, Long>) selection;
                Log.d("Calendar: ",selection.toString());
                Date dateObjectStart = new Date(datepair.first);
                Date dateObjectEnd = new Date(datepair.second);

                //Set Date in Required Format
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
                String selectedDateStart = dateTimeFormat.format(dateObjectStart);
                String selectedDateEnd = dateTimeFormat.format(dateObjectEnd);
                Log.d("Calendar: "," "+selectedDateStart+" "+selectedDateEnd);

                from_date.setText(ViewUtils.changeDateFormat(selectedDateStart));
                to_date.setVisibility(View.VISIBLE);
                to_date.setText(ViewUtils.changeDateFormat(selectedDateEnd));

                fromDate = selectedDateStart;
                toDate = selectedDateEnd;

                presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, custId, statusId,  fromDate, toDate, "");


                //showSlotSelectionCustomDialog(datepair.first,datepair.second);
                //startRecurringBedSelectionFragment(datepair.first,datepair.second);
            }
        });
        picker.show(mFragmentManager,"");


    }


    @Override
    public void init() {
        spn_emp = findViewById(R.id.spn_employee);
        iv_calendar = findViewById(R.id.iv_calendar);
        paymentsearch_edit = findViewById(R.id.custmersearch_edit);
        rv_payment_list = findViewById(R.id.rv_payment_list);
        expandableLayout = findViewById(R.id.expandableLayoutComplaint);
        txt_total_collection = findViewById(R.id.txt_total_collection);
        txt_total_balance = findViewById(R.id.txt_total_balance);
        spn_status = findViewById(R.id.spn_status);
        today = findViewById(R.id.today);

        all = findViewById(R.id.complaint_list_all);
        yesterday = findViewById(R.id.yesterday);
        thismonth = findViewById(R.id.thismonth);
        datepicker = findViewById(R.id.datepicker);
        from_date = findViewById(R.id.from_date);
        to_date = findViewById(R.id.to_date);
        cv_filter= findViewById(R.id.cv_filter);
       // pb_searching = findViewById(R.id.pb_searching);
        txt_not_found = findViewById(R.id.txt_not_found);
        ll_employee = findViewById(R.id.ll_employee);



        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(ComplaintList paymentRecieptList) {


//        txt_total_collection.setText("Closed : "+paymentRecieptList.getTotal_Paid_amount());
        List<ComplaintList.Complaint> complaintList = paymentRecieptList.getComplaint();

        txt_total_balance.setText("Count : "+complaintList.size());
        SharedPrefsData.putString(this, Constants.ComplaintFlag, "activity", Constants.PREF_NAME);
        if(complaintList.size()==0){
            rv_payment_list.setVisibility(View.GONE);
            txt_not_found.setVisibility(View.VISIBLE);
            // viewUtils.showAlertDialog(getActivity(), "Data not found", "There is no data you are searching for");
        }else {
           // pb_searching.setVisibility(View.GONE);
            rv_payment_list.setVisibility(View.VISIBLE);
            txt_not_found.setVisibility(View.GONE);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ComplaintDetailsActivity.this, RecyclerView.VERTICAL, false);
            rv_payment_list.setLayoutManager(mLayoutManager);
            complaintListAdapter = new ComplaintListAdapter(ComplaintDetailsActivity.this, complaintList, adapterCallback);
            rv_payment_list.setAdapter(complaintListAdapter);
        }

    }

    @Override
    public void showEmployeeList(EmployeeList employeeList) {
        final List<EmployeeList.Employee> employee = employeeList.getEmployee();

        ArrayList<String> empNames = new ArrayList<>();
        final ArrayList<String> empIds = new ArrayList<String>();

        empNames.add("All Employees");
        empIds.add("0");
        for (int i = 0; i < employee.size(); i++) {

            empNames.add(employee.get(i).getEmployeeName());
            empIds.add(employee.get(i).getEmployeeID().toString());
        }

        adapter_emp = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, empNames);
        adapter_emp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_emp.setAdapter(adapter_emp);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if (position == 0){
                            empId = String.valueOf(0);
                            presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  null, null, "");

                        }

                        if(position>0){
                            empId = empIds.get(position);
                            presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  fromDate, toDate, "");

                        }else{

                            if(roleType.equals("Admin")){
                                presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId
                                        , "0", statusId,  null, null, "");
                            }else{
                                presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  fromDate, toDate, "");

                            }


                        }

//                        if(position==0){
//                            empId="0";
//                        }else{
//                            try {
//                                empId = empIds.get(position);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                empId = "1";
//                            }
//                        }


//                        SharedPrefsData.putString(getActivity(), Constants.EmpId, empId, Constants.PREF_NAME);

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                };
        spn_emp.setOnItemSelectedListener(onItemSelectedListener2);



    }

    @Override
    public void showComplaintStatus(ComplaintStatusList complaintStatusList) {

        complaintstatuses = complaintStatusList.getComplaintStatus();


        statusNames = new ArrayList<String>();
         statusIds = new ArrayList<String>();

        statusNames.add("All Status");
        statusIds.add("0");
        for (int i = 0; i < complaintstatuses.size(); i++) {
            statusNames.add(complaintstatuses.get(i).getComplaintStatus());
            statusIds.add(complaintstatuses.get(i).getComplaintStatusID().toString());
        }

        adapter_status = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, statusNames);
        adapter_status.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_status.setAdapter(adapter_status);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if(position>0){

                            statusId = statusIds.get(position);
                            presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  fromDate, toDate, "");

                        }else{
                            presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", "0",  fromDate, toDate, "");

                        }
//                        if(position==0){
//                            statusId="0";
//                        }else{
//                            try {
//                                statusId = complaintstatuses.get(position).getComplaintStatusID().toString();
//                            } catch (Exception e) {
//                                statusId="1";
//                                e.printStackTrace();
//                            }
//
//                        }

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                };
        spn_status.setOnItemSelectedListener(onItemSelectedListener2);



    }

    @Override
    public void showOpencloseComplaint(InsertPayment insertPayment) {
//        presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, custId, statusId,  fromDate, toDate, "");
//        presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, custId, statusId,  fromDate, toDate, "");
        presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  null, null, "");

        Toast.makeText(this, "Complaint closed successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickCallback(View view, int position, final String ComplaintID) {

        statusNames = new ArrayList<String>();
        statusIds = new ArrayList<String>();

        for (int i = 0; i < complaintstatuses.size(); i++) {
            statusNames.add(complaintstatuses.get(i).getComplaintStatus());
            statusIds.add(complaintstatuses.get(i).getComplaintStatusID().toString());
        }
        adapter_status = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, statusNames);
        adapter_status.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_complaint_status.setAdapter(adapter_status);

        AdapterView.OnItemSelectedListener onItemSelectedListener =
                new AdapterView.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


statusId = statusIds.get(position);


                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                };
        spn_complaint_status.setOnItemSelectedListener(onItemSelectedListener);


        String custId = SharedPrefsData.getString(ComplaintDetailsActivity.this, Constants.ComplaintID, Constants.PREF_NAME);
        String Complaint_Status_ID = SharedPrefsData.getString(ComplaintDetailsActivity.this, Constants.Complaint_Status_ID, Constants.PREF_NAME);
        String userId = SharedPrefsData.getString(ComplaintDetailsActivity.this, Constants.UserId, Constants.PREF_NAME);
        String empId = SharedPrefsData.getString(ComplaintDetailsActivity.this, Constants.EmpId, Constants.PREF_NAME);

        //Toast.makeText(this, Complaint_Status_ID, Toast.LENGTH_SHORT).show();


        presenter.loadOpencloseComplaint(ComplaintDetailsActivity.this, ComplaintID, "", "", todayDateString(), "5", "", empId, userId, todayDateString());
        Intent i =new  Intent(this, ComplaintReceiptActivity.class);
        i.putExtra("activity_name", "UpdateComplaint");
        startActivity(i);
        finish();


    }

    @Override
    protected void onResume() {
        super.onResume();
        //presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  fromDate, toDate, "");
        presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", statusId,  null, null, "");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(ComplaintDetailsActivity.this, HomeActivity.class));

    }
}