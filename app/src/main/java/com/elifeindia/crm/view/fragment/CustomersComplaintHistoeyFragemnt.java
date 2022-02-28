package com.elifeindia.crm.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.ComplaintListAdapter;
import com.elifeindia.crm.contract.activities.ComplaintListContract;
import com.elifeindia.crm.model.ComplaintList;
import com.elifeindia.crm.model.ComplaintStatusList;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.presenter.activities.ComplaintListPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.github.aakira.expandablelayout.ExpandableLayout;
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

public class CustomersComplaintHistoeyFragemnt extends Fragment implements ComplaintListContract.View, AdapterCallback {

    ComplaintListContract.Presenter presenter;
    ViewUtils viewUtils;
    RecyclerView rv_payment_list;
    ComplaintListAdapter complaintListAdapter;
    ExpandableLayout expandableLayout;
    CardView cv_filter;
    String areaId = "", companyId, empId="0", custId="0", fromDate, toDate, statusId="0", value="";
    EditText paymentsearch_edit;
    Spinner spn_emp, spn_status;
    ArrayAdapter<String> adapter_emp, adapter_status;
    ImageView iv_calendar;
    TextView all,today, yesterday, thismonth, datepicker, from_date, to_date, txt_total_balance, txt_total_collection;
    FragmentManager mFragmentManager;
    AdapterCallback adapterCallback;
    LinearLayout layoutEmployee;
    ProgressDialog progressDialog;



    public CustomersComplaintHistoeyFragemnt() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_complaint_details, container, false);

        spn_emp = v.findViewById(R.id.spn_employee);
        iv_calendar = v.findViewById(R.id.iv_calendar);
        paymentsearch_edit = v.findViewById(R.id.custmersearch_edit);
        rv_payment_list = v.findViewById(R.id.rv_payment_list);
        expandableLayout = v.findViewById(R.id.expandableLayout);
        txt_total_collection = v.findViewById(R.id.txt_total_collection);
        txt_total_balance = v.findViewById(R.id.txt_total_balance);
        spn_status = v.findViewById(R.id.spn_status);
        all = v.findViewById(R.id.complaint_list_all);
        today = v.findViewById(R.id.today);
        yesterday = v.findViewById(R.id.yesterday);
        thismonth = v.findViewById(R.id.thismonth);
        datepicker = v.findViewById(R.id.datepicker);
        from_date = v.findViewById(R.id.from_date);
        to_date = v.findViewById(R.id.to_date);
        cv_filter= v.findViewById(R.id.cv_filter);
        layoutEmployee = v.findViewById(R.id.ll_employee);

        viewUtils = new ViewUtils();
        presenter = new ComplaintListPresenter(this);
        presenter.start();
        adapterCallback=this;
        //from_date.setText((todayDateString()));
        from_date.setVisibility(View.GONE);
        layoutEmployee.setVisibility(View.GONE);
        v.findViewById(R.id.tool_bar).setVisibility(View.GONE);

        mFragmentManager= getFragmentManager();
        companyId = SharedPrefsData.getString(getActivity(), Constants.CompanyID, Constants.PREF_NAME);
        custId = SharedPrefsData.getString(getActivity(), Constants.CustomerID, Constants.PREF_NAME);
        empId = SharedPrefsData.getString(getActivity(), Constants.EmpId, Constants.PREF_NAME);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");

        progressDialog.show();
        presenter.loadComplaintList(getActivity(), companyId, empId, custId, "0",  null, null, "");
        presenter.loadComplaintStatus(getActivity(),companyId,"0");


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

                //fromDate = todayDateString();
                //toDate = todayDateString();

                from_date.setVisibility(View.GONE);

                to_date.setVisibility(View.GONE);
                from_date.setText(ViewUtils.changeDateFormat(todayDateString()));
                progressDialog.show();
                presenter.loadComplaintList(getActivity(), companyId, empId, custId, "0",  null, null, "");


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
                progressDialog.show();
                presenter.loadComplaintList(getActivity(), companyId, empId, custId, "0",  fromDate, toDate, "");

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

                progressDialog.show();
                presenter.loadComplaintList(getActivity(), companyId, empId, custId, "0",  fromDate, toDate, "");
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
                progressDialog.show();
                presenter.loadComplaintList(getActivity(), companyId, empId, custId, "0",  fromDate, toDate, "");
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
                progressDialog.show();
                presenter.loadComplaintList(getActivity(), companyId, empId, custId, "0",  fromDate, toDate, charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cv_filter = v.findViewById(R.id.cv_filter);

        cv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableLayout.isExpanded()) {
                    expandableLayout.collapse();
                } else {
//                    presenter.loadEmployeeList(getActivity(), companyId);
//                    presenter.loadComplaintStatus(getActivity(), companyId);
                    expandableLayout.expand();
                }
            }
        });


        //fromDate = getYesterdayDateString();
        //toDate = getYesterdayDateString();


        return v;
    }

    private String todayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
        Log.d("mActivityReference:", CustomersComplaintHistoeyFragemnt.this.toString());
        if (getActivity().getTheme().resolveAttribute(R.attr.materialCalendarTheme, typedValue, true)) {
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

                presenter.loadComplaintList(getActivity(), companyId, "0", custId, "0",  fromDate, toDate, "");


                //showSlotSelectionCustomDialog(datepair.first,datepair.second);
                //startRecurringBedSelectionFragment(datepair.first,datepair.second);
            }
        });
        picker.show(mFragmentManager,"");


    }

    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(ComplaintList paymentRecieptList) {
        progressDialog.dismiss();
        txt_total_balance.setText("Count : "+paymentRecieptList.getCount());
//        txt_total_collection.setText("Closed : "+paymentRecieptList.getTotal_Paid_amount());

        SharedPrefsData.putString(getActivity(), Constants.ComplaintFlag, "fragment", Constants.PREF_NAME);
        List<ComplaintList.Complaint> complaintList = paymentRecieptList.getComplaint();

        if(complaintList.size()==0){
            rv_payment_list.setVisibility(View.GONE);
            // viewUtils.showAlertDialog(getActivity(), "Data not found", "There is no data you are searching for");
        }else {
            rv_payment_list.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            rv_payment_list.setLayoutManager(mLayoutManager);
            complaintListAdapter = new ComplaintListAdapter(getActivity(), complaintList, adapterCallback);
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

        adapter_emp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, empNames);
        adapter_emp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_emp.setAdapter(adapter_emp);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        if(position>0){
                            empId = empIds.get(position);
                            presenter.loadComplaintList(getActivity(), companyId, empId, "0", "0",  fromDate, toDate, "");

                        }else{
                            presenter.loadComplaintList(getActivity(), companyId, empId, "0", "0",  fromDate, toDate, "");

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

        final List<ComplaintStatusList.Complaintstatus> complaintstatuses = complaintStatusList.getComplaintStatus();


        ArrayList<String> statusNames = new ArrayList<String>();
        final ArrayList<String> statusIds = new ArrayList<String>();

        statusNames.add("All Status");
        statusIds.add("0");
        for (int i = 0; i < complaintstatuses.size(); i++) {
            statusNames.add(complaintstatuses.get(i).getComplaintStatus());
            statusIds.add(complaintstatuses.get(i).getComplaintStatusID().toString());
        }

        adapter_status = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, statusNames);
        adapter_status.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_status.setAdapter(adapter_status);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if(position>0){

                            statusId = statusIds.get(position);
                            progressDialog.show();
                            presenter.loadComplaintList(getActivity(), companyId, empId, custId, statusId,  fromDate, toDate, "");

                        }else{
                            progressDialog.show();
                            presenter.loadComplaintList(getActivity(), companyId, empId, custId, "0",  null, null, "");

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
        presenter.loadComplaintList(getActivity(), companyId, "0", custId, "0",  fromDate, toDate, "");

        Toast.makeText(getActivity(), "Complaint closed "+insertPayment.getMessage()+", id is "+insertPayment.getId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickCallback(View view, int position, String id) {
        String custId = SharedPrefsData.getString(getActivity(), Constants.CustomerID, Constants.PREF_NAME);
        String Complaint_Status_ID = SharedPrefsData.getString(getActivity(), Constants.Complaint_Status_ID, Constants.PREF_NAME);
        String userId = SharedPrefsData.getString(getActivity(), Constants.UserId, Constants.PREF_NAME);
        String empId = SharedPrefsData.getString(getActivity(), Constants.EmpId, Constants.PREF_NAME);

        Toast.makeText(getActivity(), Complaint_Status_ID, Toast.LENGTH_SHORT).show();
        presenter.loadOpencloseComplaint(getActivity(), id, "", "",
                todayDateString(), "8"
                , "", empId, userId, todayDateString());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadComplaintList(getActivity(), companyId, "0", custId, "0",  fromDate, toDate, "");


    }
}