package com.elifeindia.crm.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import androidx.core.content.FileProvider;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.BuildConfig;
import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.PaymentListAdapter;
import com.elifeindia.crm.contract.activities.PaymentListContract;
import com.elifeindia.crm.model.AreaResponse;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.PaymentRecieptList;
import com.elifeindia.crm.presenter.activities.PaymentListPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.activities.ComplaintDetailsActivity;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class CustomerPaymentHistoryFragment extends Fragment  implements PaymentListContract.View {

    PaymentListContract.Presenter presenter;
    ViewUtils viewUtils; LinearLayout root;
    RecyclerView rv_payment_list;
    PaymentListAdapter paymentListAdapter;
    ExpandableLayout expandableLayout;
    CardView cv_filter;
    String areaId = "", dateTime, companyId, empId="0", custId="0", fromDate, toDate, roleType, triplePlayId="0", value="";
    Spinner spn_emp;    File imagePath;
    EditText paymentsearch_edit;
    ArrayAdapter<String> adapter_state_adj1;
    ImageView iv_calendar;
    TextView all, today, yesterday, thismonth, datepicker, from_date, to_date, txt_total_balance, txt_total_collection;
    FragmentManager mFragmentManager;
    private HashSet<Integer> mSelectedWeekdays;

    public CustomerPaymentHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_payment_details, container, false);

        spn_emp = v.findViewById(R.id.spn_employee);
        iv_calendar = v.findViewById(R.id.iv_calendar);
        paymentsearch_edit = v.findViewById(R.id.custmersearch_edit);
        rv_payment_list = v.findViewById(R.id.rv_payment_list);
        expandableLayout = v.findViewById(R.id.expandableLayout);
        txt_total_collection = v.findViewById(R.id.txt_total_collection);
        txt_total_balance = v.findViewById(R.id.txt_total_balance);
        root = v.findViewById(R.id.root);

        all = v.findViewById(R.id.all);
        today = v.findViewById(R.id.today);
        yesterday = v.findViewById(R.id.yesterday);
        thismonth = v.findViewById(R.id.thismonth);
        datepicker = v.findViewById(R.id.datepicker);
        from_date = v.findViewById(R.id.from_date);
        to_date = v.findViewById(R.id.to_date);
        cv_filter= v.findViewById(R.id.cv_filter);

        //from_date.setText("");
        from_date.setVisibility(View.GONE);

        mFragmentManager=getFragmentManager();

        v.findViewById(R.id.btn_share).setVisibility(View.GONE);

        companyId = SharedPrefsData.getString(getActivity(), Constants.CompanyID, Constants.PREF_NAME);
        custId = SharedPrefsData.getString(getActivity(), Constants.CustomerID, Constants.PREF_NAME);
        roleType = SharedPrefsData.getString(getActivity(), Constants.RoleType, Constants.PREF_NAME);
        //empId = SharedPrefsData.getString(getActivity(), Constants.EmpId, Constants.PREF_NAME);

        cv_filter.setVisibility(View.GONE);


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

                to_date.setVisibility(View.GONE);
                from_date.setVisibility(View.GONE);

                presenter.loadPaymentList(getContext(),companyId,custId,"","",triplePlayId,value,empId,areaId);


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
                from_date.setText((todayDateString()));
                presenter.loadPaymentList(getContext(),companyId,custId,fromDate,toDate,triplePlayId,value,empId,areaId);


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
                from_date.setText((getYesterdayDateString()));
                presenter.loadPaymentList(getContext(),companyId,custId,fromDate,toDate,triplePlayId,value,empId,areaId);

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

                from_date.setText((startDateStr));
                to_date.setVisibility(View.VISIBLE);
                to_date.setText((endDateStr));

                fromDate = startDateStr;
                toDate = endDateStr;

                presenter.loadPaymentList(getContext(),companyId,custId,fromDate,toDate,triplePlayId,value,empId,areaId);

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

        viewUtils = new ViewUtils();
        presenter = new PaymentListPresenter(this);
        presenter.start();

        paymentsearch_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.loadPaymentList(getContext(),companyId,custId,fromDate,toDate,triplePlayId,value,empId,areaId);

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
                    presenter.loadPaymentList(getContext(),companyId,custId,fromDate,toDate,triplePlayId,value,empId,areaId);
                    expandableLayout.expand();
                }
            }
        });


        fromDate = todayDateString();
        toDate = todayDateString();

        presenter.loadPaymentList(getContext(),companyId,custId,"","",triplePlayId,value,empId,areaId);



        return v;
    }

    public Bitmap takeScreenshot(View v) {
        LinearLayout rootView = v.findViewById(R.id.root);
        rv_payment_list.setDrawingCacheEnabled(true);
        return rv_payment_list.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "eLife CRM Receipts");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US);
            Date date = new Date();
            dateTime = sdf1.format(date);
            imagePath = new File(Environment.getExternalStorageDirectory() + "/eLife CRM Receipts"+"/eLife_crm_invoice_"+dateTime+".jpg");
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(imagePath);
                Toast.makeText(getActivity(), imagePath.toString(), Toast.LENGTH_SHORT).show();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                Log.e("GREC", e.getMessage(), e);
            } catch (IOException e) {
                Log.e("GREC", e.getMessage(), e);
            }
        } else {
            Toast.makeText(getActivity(), "Failure in making directory", Toast.LENGTH_SHORT).show();
        }

    }

    private void shareIt() {
        Uri uri = FileProvider.getUriForFile(requireActivity(),
                BuildConfig.APPLICATION_ID + ".provider", imagePath);

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "Hey check out eLife CRM Payment Receipt";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Payment Receipt");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            Intent intent = new Intent(Intent.createChooser(sharingIntent, "Share via"));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shareItOnWhatsApp() {
        Uri uri = FileProvider.getUriForFile(requireActivity(),
                BuildConfig.APPLICATION_ID + ".provider", imagePath);

//        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
//        whatsappIntent.setType("image/*");
//        whatsappIntent.setPackage("com.whatsapp");
//        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
//        try {
//            startActivity(whatsappIntent);
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
//        }

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        sharingIntent.setPackage("com.whatsapp");
        String shareBody = "Payment Receipt";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Payment Receipt");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(sharingIntent);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {
        viewUtils.toast(getActivity(), message);

    }

    @Override
    public void showResult(PaymentRecieptList paymentRecieptList) {

        txt_total_balance.setText("No Of Payments\n"+paymentRecieptList.getPaymentReciept().size());
        txt_total_collection.setText("Total Amount\n"+paymentRecieptList.getTotal_Paid_amount());

        List<PaymentRecieptList.PaymentReciept> paymentReciepts = paymentRecieptList.getPaymentReciept();

        if(paymentReciepts.size()==0){
            rv_payment_list.setVisibility(View.GONE);
           // viewUtils.showAlertDialog(getActivity(), "Data not found", "There is no data you are searching for");
        }else {
            rv_payment_list.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            rv_payment_list.setLayoutManager(mLayoutManager);
            paymentListAdapter = new PaymentListAdapter(getActivity(), paymentReciepts);
            rv_payment_list.setAdapter(paymentListAdapter);

        }

    }

    @Override
    public void showEmployeeList(EmployeeList employeeList) {
        final List<EmployeeList.Employee> employee = employeeList.getEmployee();

        ArrayList statesList = new ArrayList();
        statesList.add("All Employees");

        for (int i = 0; i < employee.size()-1; i++) {
            statesList.add(employee.get(i).getEmployeeName());
        }

        adapter_state_adj1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_state_adj1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_emp.setAdapter(adapter_state_adj1);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position==0){
                            empId="0";
                        }else{
                            empId = employee.get(position).getEmployeeID().toString();
                        }
                        presenter.loadPaymentList(getContext(),companyId,custId,fromDate,toDate,triplePlayId,value,empId,areaId);

                        //SharedPrefsData.putString(getActivity(), Constants.EmpId, empId, Constants.PREF_NAME);

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                };
        spn_emp.setOnItemSelectedListener(onItemSelectedListener2);
    }

    @Override
    public void showArea(AreaResponse areaResponse) {

    }

    public void loadRangeDatePickerMultipleBooking() {
        MaterialDatePicker.Builder<Pair<Long,Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        long today = MaterialDatePicker.todayInUtcMilliseconds();


        int dialogTheme=0;
        TypedValue typedValue = new TypedValue();
        Log.d("mActivityReference:",getActivity().toString());
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

                from_date.setText((selectedDateStart));
                to_date.setVisibility(View.VISIBLE);
                to_date.setText((selectedDateEnd));

                fromDate = selectedDateStart;
                toDate = selectedDateEnd;

                presenter.loadPaymentList(getContext(),companyId,custId,fromDate,toDate,triplePlayId,value,empId,areaId);


                //showSlotSelectionCustomDialog(datepair.first,datepair.second);
                //startRecurringBedSelectionFragment(datepair.first,datepair.second);
            }
        });
        picker.show(mFragmentManager,"");
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

}
