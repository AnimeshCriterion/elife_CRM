package com.elifeindia.crm.view.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
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
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

public class PaymentDetailsFragment extends Fragment implements PaymentListContract.View {

    PaymentListContract.Presenter presenter;
    ViewUtils viewUtils;
    RecyclerView rv_payment_list;
    PaymentListAdapter paymentListAdapter;
    ExpandableLayout expandableLayout;
    CardView cv_filter;
    String receiptDateTime, no, amount, dateTime, areaId = "", roleType, companyId, empName, empMob, empId = "0", custId = "0", fromDate, toDate, triplePlayId = "0", value = "";
    SearchableSpinner searchableSpinner;
    Spinner spinnerArea;
    EditText paymentsearch_edit;
    ArrayAdapter<String> adapterEmployeeList;
    ArrayAdapter<String> adapterAreaList;
    ImageView iv_calendar;
    ProgressBar pb_searching;
    File imagePath;
    TextView txt_not_found, all, today, yesterday, thismonth, datepicker, from_date, to_date, txt_total_balance, txt_total_collection;
    FragmentManager mFragmentManager;
    private HashSet<Integer> mSelectedWeekdays;
    File filePath;
    String selectedPhoto = "filename";
    Bitmap image;
    List<PaymentRecieptList.PaymentReciept> paymentReciepts;
    Button btn_share;
    ProgressDialog progressBar;


    private static final String TAG = "PdfCreatorActivity";
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private File pdfFile;
    Context context;

    public PaymentDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_payment_details, container, false);

        searchableSpinner = v.findViewById(R.id.spn_employee);
        spinnerArea = v.findViewById(R.id.spn_area2);
        iv_calendar = v.findViewById(R.id.iv_calendar);
        paymentsearch_edit = v.findViewById(R.id.custmersearch_edit);
        rv_payment_list = v.findViewById(R.id.rv_payment_list);
        expandableLayout = v.findViewById(R.id.expandableLayout);
        txt_total_collection = v.findViewById(R.id.txt_total_collection);
        txt_total_balance = v.findViewById(R.id.txt_total_balance);
        btn_share = v.findViewById(R.id.btn_share);

        all = v.findViewById(R.id.all);
        today = v.findViewById(R.id.today);
        yesterday = v.findViewById(R.id.yesterday);
        thismonth = v.findViewById(R.id.thismonth);
        datepicker = v.findViewById(R.id.datepicker);
        from_date = v.findViewById(R.id.from_date);
        to_date = v.findViewById(R.id.to_date);
        pb_searching = v.findViewById(R.id.pb_searching);
        txt_not_found = v.findViewById(R.id.txt_not_found);
        cv_filter = v.findViewById(R.id.cv_filter);

        all.setVisibility(View.GONE);

        today.setBackgroundResource(R.color.colorPrimaryDark);
        today.setTextColor(getResources().getColor(R.color.white));

        companyId = SharedPrefsData.getString(getActivity(), Constants.CompanyID, Constants.PREF_NAME);
        roleType = SharedPrefsData.getString(getActivity(), Constants.RoleType, Constants.PREF_NAME);
        empName = SharedPrefsData.getString(getActivity(), Constants.EmployeeName, Constants.PREF_NAME);
        empMob = SharedPrefsData.getString(getActivity(), Constants.EmployeeMob, Constants.PREF_NAME);
        empId = String.valueOf(SharedPrefsData.getInt(getActivity(), Constants.EmpId, Constants.PREF_NAME));

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US);
        Date date = new Date();
        receiptDateTime = sdf1.format(date);

        context = getActivity();
        mFragmentManager = getFragmentManager();

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Bitmap receiptBitmap;
                receiptBitmap = takeScreenshot(v);
                saveBitmap(receiptBitmap);
                shareItOnWhatsApp();*/
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });


        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                all.setBackgroundResource(R.color.colorPrimaryDark);
                all.setTextColor(getResources().getColor(R.color.white));

                today.setBackgroundResource(R.color.white);
                today.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                yesterday.setBackgroundResource(R.color.white);
                yesterday.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                thismonth.setBackgroundResource(R.color.white);
                thismonth.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                datepicker.setBackgroundResource(R.color.white);
                datepicker.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                fromDate = todayDateString();
                toDate = todayDateString();

                to_date.setVisibility(View.GONE);
                from_date.setVisibility(View.GONE);
                from_date.setText((todayDateString()));
                progressBar.show();
                presenter.loadPaymentListDateWise(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);


            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                today.setBackgroundResource(R.color.colorPrimaryDark);
                today.setTextColor(getResources().getColor(R.color.white));

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
                progressBar.show();
                presenter.loadPaymentListDateWise(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);

            }
        });

        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                today.setBackgroundResource(R.color.white);
                today.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

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
                progressBar.show();
                presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);

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
                from_date.setText((startDateStr));
                to_date.setVisibility(View.VISIBLE);
                to_date.setText((endDateStr));

                fromDate = startDateStr;
                toDate = endDateStr;
                progressBar.show();
                presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);

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
        progressBar = new ProgressDialog(getContext());
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        presenter.loadArea(getContext(), companyId, empId);
        presenter.loadEmployeeList(getContext(), companyId, empId, roleType);
        paymentsearch_edit.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    progressBar.show();
                    presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);
                    pb_searching.setVisibility(View.VISIBLE);

                    return true;
                }
                return false;
            }
        });


        cv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roleType.equals("Admin")) {

                    if (expandableLayout.isExpanded()) {
                        expandableLayout.collapse();
                    } else {
                        expandableLayout.expand();
                    }
                    cv_filter.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getActivity(), "Access Denied", Toast.LENGTH_SHORT).show();
                    cv_filter.setEnabled(false);
                }

            }
        });


        fromDate = todayDateString();
        toDate = todayDateString();
        progressBar.show();
        presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);


//        paymentsearch_edit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                presenter.loadPaymentListForSearch(getActivity(), companyId, custId, fromDate, toDate, triplePlayId, charSequence.toString(), empId);
//                pb_searching.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });


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

    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {
        viewUtils.toast(getActivity(), message);

    }

    @Override
    public void showResult(PaymentRecieptList paymentRecieptList) {
        progressBar.dismiss();
        txt_total_balance.setText("Payments\n" + paymentRecieptList.getPaymentReciept().size());
        txt_total_collection.setText("Total Amount\n" + paymentRecieptList.getTotal_Paid_amount());
        no = String.valueOf(paymentRecieptList.getPaymentReciept().size());
        amount = paymentRecieptList.getTotal_Paid_amount();
        paymentReciepts = paymentRecieptList.getPaymentReciept();
        pb_searching.setVisibility(View.GONE);

        if (paymentReciepts.size() == 0) {
            rv_payment_list.setVisibility(View.GONE);
            txt_not_found.setVisibility(View.VISIBLE);
            //viewUtils.showAlertDialog(getActivity(), "Data not found", "There is no data you are searching for");
        } else {
            txt_not_found.setVisibility(View.GONE);
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

        final ArrayList<String> empNames = new ArrayList<>();
        final ArrayList<String> empIds = new ArrayList<>();
        final ArrayList<String> empMobNos = new ArrayList<>();

        empNames.add("--Select--");
        empMobNos.add("All Contacts");
        empIds.add("0");

        for (int i = 0; i < employee.size(); i++) {
            empNames.add(employee.get(i).getEmployeeName());
            empIds.add(employee.get(i).getEmployeeID().toString());
            empMobNos.add(employee.get(i).getContactNo());

        }

        adapterEmployeeList = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, empNames);
        adapterEmployeeList.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        searchableSpinner.setAdapter(adapterEmployeeList);
        AdapterView.OnItemSelectedListener onItemSelectedListener2 = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    empId = empIds.get(position);
                    empName = empNames.get(position);
                    empMob = empMobNos.get(position);
                    paymentsearch_edit.setText(empName);
                    progressBar.show();
                    presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);

                } else {
                    // presenter.loadEmployeeList(getContext(), companyId, empId, roleType);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        searchableSpinner.setOnItemSelectedListener(onItemSelectedListener2);
    }

    @Override
    public void showArea(AreaResponse areaResponse) {
        final List<AreaResponse.Area> area = areaResponse.getArea();
        ArrayList<String> areaNames = new ArrayList<String>();
        final ArrayList<String> areaIds = new ArrayList<String>();
        areaNames.add("All Areas");
        areaIds.add("0");
        for (int i = 0; i < area.size(); i++) {
            areaNames.add(area.get(i).getAreaName());
            areaIds.add(area.get(i).getAreaID().toString());
        }
        adapterAreaList = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, areaNames);
        adapterAreaList.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerArea.setAdapter(adapterAreaList);
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    value = "";
                    paymentsearch_edit.setText("");
                    areaId = areaIds.get(position);
                    SharedPrefsData.putString(getContext(), Constants.CustAreaId, areaId, Constants.PREF_NAME);
                    areaId = SharedPrefsData.getString(getContext(), Constants.CustAreaId, Constants.PREF_NAME);
                    progressBar.show();
                    presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);

                          /*  if (i != 1) {
                                presenter.loadCustomersDateWise(getContext(), companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
                                progressBar.show();

                            }*/

                } else {
                    areaId = "0";
                    SharedPrefsData.putString(getContext(), Constants.CustAreaId, areaId, Constants.PREF_NAME);
                    value = "";
                    paymentsearch_edit.setText("");
                    progressBar.show();
                    presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);

                           /* if (i != 1) {
                                presenter.loadCustomersDateWise(getContext(), companyId, userId, empId, areaId, "", StatusId, selectCont, pageNo, Value);
                                progressBar.show();

                            }*/
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
        spinnerArea.setOnItemSelectedListener(onItemSelectedListener);

    }

    public void loadRangeDatePickerMultipleBooking() {
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        long today = MaterialDatePicker.todayInUtcMilliseconds();


        int dialogTheme = 0;
        TypedValue typedValue = new TypedValue();
        Log.d("mActivityReference:", getActivity().toString());
        if (getActivity().getTheme().resolveAttribute(R.attr.materialCalendarTheme, typedValue, true)) {
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


//        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
//        constraintsBuilder.setStart(today);
//        constraintsBuilder.setEnd(oneYearForward);
//        constraintsBuilder.setValidator(DateValidatorPointForward.now());

        // builder.setCalendarConstraints(constraintsBuilder.build());

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

                from_date.setText((selectedDateStart));
                to_date.setVisibility(View.VISIBLE);
                to_date.setText((selectedDateEnd));

                fromDate = selectedDateStart;
                toDate = selectedDateEnd;
                progressBar.show();
                presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);


                //showSlotSelectionCustomDialog(datepair.first,datepair.second);
                //startRecurringBedSelectionFragment(datepair.first,datepair.second);

            }
        });
        picker.show(mFragmentManager, "");
    }

    public Bitmap takeScreenshot(View v) {
        LinearLayout rootView = v.findViewById(R.id.root);
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "eLife CRM Reports");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US);
            Date date = new Date();
            dateTime = sdf1.format(date);
            imagePath = new File(Environment.getExternalStorageDirectory() + "/eLife CRM Reports" + "/eLife_crm_invoice_" + dateTime + ".jpg");
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(imagePath);
                // Toast.makeText(getActivity(), imagePath.toString(), Toast.LENGTH_SHORT).show();
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
        Uri uri = FileProvider.getUriForFile(Objects.requireNonNull(getActivity()),
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
        Uri uri = FileProvider.getUriForFile(Objects.requireNonNull(getActivity()),
                BuildConfig.APPLICATION_ID + ".provider", imagePath);

        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("image/*");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
        }

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

    private void createPdfWrapper() throws FileNotFoundException, DocumentException {
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            REQUEST_CODE_ASK_PERMISSIONS);
                                }
                            });
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        } else {
            createPdf();
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {
        File docsFolder = new File(Environment.getDataDirectory() + "/eLife CRM Receipts");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }
        String pdfname = "elife crm reports" + receiptDateTime + ".pdf";
        pdfFile = new File(docsFolder.getAbsolutePath(), pdfname);
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document(PageSize.A4);
        PdfPTable table = new PdfPTable(new float[]{3, 3, 3, 3, 3});
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setFixedHeight(25);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell("A/C No");
        table.addCell("Sub ID");
        table.addCell("Paid Amnt");
        table.addCell("Bal Amnt");
        table.addCell("Date");
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j = 0; j < cells.length; j++) {
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        for (int i = 0; i < paymentReciepts.size(); i++) {

            table.addCell(String.valueOf(paymentReciepts.get(i).getAccountNo()));
            table.addCell(String.valueOf(paymentReciepts.get(i).getSubscriberID()));
            table.addCell(String.valueOf(paymentReciepts.get(i).getPaidAmount()));
            table.addCell(String.valueOf(paymentReciepts.get(i).getBalance()));
            table.addCell(String.valueOf(paymentReciepts.get(i).getPaymentDate().substring(0, 10)));

        }
//        System.out.println("Done");
        PdfWriter.getInstance(document, output);
        document.open();
        Font f = new Font(Font.FontFamily.TIMES_ROMAN, 30.0f, Font.UNDERLINE, BaseColor.BLUE);
        Font g = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.NORMAL, BaseColor.BLUE);

        document.add(new Paragraph("Collected By : " + empName, f));
        document.add(new Paragraph("Mob No : " + empMob, f));
        document.add(new Paragraph("From Date : " + fromDate, f));
        document.add(new Paragraph("To Date : " + toDate, f));
        document.add(new Paragraph("Total Amount Rs " + amount + "/-", f));
        document.add(new Paragraph("No of Payments " + no + "\n", f));
        document.add(new Paragraph("\n"));
        document.add(table);
//        for (int i = 0; i < MyList1.size(); i++) {
//            document.add(new Paragraph(String.valueOf(MyList1.get(i))));
//        }
        document.close();
        Log.e("osman", paymentReciepts.toString());
        previewPdf();
    }

    private void previewPdf() {
        PackageManager packageManager = context.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", pdfFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/pdf");
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Download a PDF Viewer to see the generated PDF", Toast.LENGTH_SHORT).show();
        }
    }


}
