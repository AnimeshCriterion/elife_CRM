package com.elifeindia.crm.view.fragment;

import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.getInvoiceModelInvoice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
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

//import com.androidnetworking.BuildConfig;
import com.elifeindia.crm.OnClickForPaymentReceiptNew;
import com.elifeindia.crm.R;
import com.elifeindia.crm.TestActivity;
import com.elifeindia.crm.adapters.PaymentListAdapter;
import com.elifeindia.crm.contract.activities.CustomerDetailsContract;
import com.elifeindia.crm.contract.activities.PaymentListContract;
import com.elifeindia.crm.model.AreaResponse;
import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.model.PaymentRecieptList;
import com.elifeindia.crm.presenter.activities.CustomerDetailsPresenter;
import com.elifeindia.crm.presenter.activities.PaymentListPresenter;
import com.elifeindia.crm.printersdk.PaymentReceiptReprentingActivity;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.activities.BillShareActivity;
import com.elifeindia.crm.view.activities.CustomersDetailsActivity;
//import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import java.util.Random;
import java.util.TimeZone;

public class PaymentDetailsFragment extends Fragment implements PaymentListContract.View, OnClickForPaymentReceiptNew,CustomerDetailsContract.View {

    private static final int ACTIVITY_VIEW_ATTACHMENT = 101;
    PaymentListContract.Presenter presenter;
    CustomerDetailsContract.Presenter custommerPresenter;
    PaymentRecieptList.PaymentReciept dataSend;
    ViewUtils viewUtils;
    RecyclerView rv_payment_list;
    PaymentListAdapter paymentListAdapter;
    LinearLayout expandableLayout;
    CardView cv_filter;
    String no, amount, dateTime, areaId = "0", roleType, companyId, empName, empMob, empId = "0", custId = "0", fromDate, toDate, triplePlayId = "0", value = "";
    Spinner searchableSpinner;
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
    ImageView filter_image;
    ProgressDialog progressBar;

    private static final String TAG = "PdfCreatorActivity";
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private File pdfFile;
    Context context;

    public PaymentDetailsFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_payment_details, container, false);

        searchableSpinner = v.findViewById(R.id.spn_employee);
        spinnerArea = v.findViewById(R.id.spn_area2);
        iv_calendar = v.findViewById(R.id.iv_calendar);
        paymentsearch_edit = v.findViewById(R.id.custmersearch_edit);
        rv_payment_list = v.findViewById(R.id.rv_payment_list);
        expandableLayout = v.findViewById(R.id.fillterLayoutPayment);
        txt_total_collection = v.findViewById(R.id.txt_total_collection);
        txt_total_balance = v.findViewById(R.id.txt_total_balance);
        btn_share = v.findViewById(R.id.btn_share);
filter_image=v.findViewById(R.id.filter_image);
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
        dataSend= new  PaymentRecieptList.PaymentReciept();
        companyId = SharedPrefsData.getString(getActivity(), Constants.CompanyID, Constants.PREF_NAME);
        roleType = SharedPrefsData.getString(getActivity(), Constants.RoleType, Constants.PREF_NAME);
        empName = SharedPrefsData.getString(getActivity(), Constants.EmployeeName, Constants.PREF_NAME);
        empMob = SharedPrefsData.getString(getActivity(), Constants.EmployeeMob, Constants.PREF_NAME);
        Log.d(TAG, "onCreateView: " + roleType);

        if (roleType.equals("Admin")) {
            empId = "0";
        } else {
            empId = SharedPrefsData.getString(getActivity(), Constants.EmpId, Constants.PREF_NAME);

        }


        context = getActivity();
        mFragmentManager = getFragmentManager();

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

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Bitmap receiptBitmap;
                receiptBitmap = takeScreenshot(v);
                saveBitmap(receiptBitmap);
                shareItOnWhatsApp();*/
                //  startActivity(new Intent(getContext(), TestActivity.class));
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });



        paymentsearch_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                paymentListAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
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
        custommerPresenter = new CustomerDetailsPresenter(this);
        presenter.start();
        custommerPresenter.start();

        progressBar = new ProgressDialog(getContext());
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        presenter.loadArea(getContext(), companyId, empId);
        presenter.loadEmployeeList(getContext(), companyId, empId, roleType);
//        paymentsearch_edit.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    progressBar.show();
//                    presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);
//                    pb_searching.setVisibility(View.VISIBLE);
//
//                    return true;
//                }
//                return false;
//            }
//        });
        cv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roleType.equals("Admin")) {
//                    if (expandableLayout.isExpanded()) {
//                        expandableLayout.collapse();
//                    } else {
//                        expandableLayout.expand();
//                    }
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


        paymentsearch_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.loadPaymentListForSearch(getActivity(), companyId, custId, fromDate, toDate, triplePlayId, charSequence.toString(), empId,areaId);
                pb_searching.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });


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
        SharedPrefsData.putString(getActivity(), Constants.SubId, getInvoiceModel.getSubscriber_ID().toString(), Constants.PREF_NAME);
        getInvoiceModel.getTitle();
        getInvoiceModel.getTriple_play_ID();
      //  startActivity(new Intent(getActivity(), BillShareActivity.class));
        SharedPrefsData.putString(context, Constants.PaymentId, dataSend.getPayment_Id(), Constants.PREF_NAME);


        Intent intent = new Intent(context, PaymentReceiptReprentingActivity.class);
        intent.putExtra("BillType",dataSend.getPaymentType().toString());
        intent.putExtra("WhatsappNo",dataSend.getWhatsappNo());
        intent.putExtra("ContactNo", dataSend.getContactNo());
            intent.putExtra("activity", "payment details");
        //String address = paymentReciepts.get(position).getAddress().toString();
        // SharedPrefsData.putString(context, Constants.CustomerAddress, address, Constants.PREF_NAME);
        // SharedPrefsData.putString(context, Constants.CustomerID, paymentReciepts.get(position).getCustomerID().toString(), Constants.PREF_NAME);
        context.startActivity(intent);

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
            paymentListAdapter = new PaymentListAdapter(getActivity(), paymentReciepts,this);
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
                Log.d(TAG, "onItemSelected: "+String.valueOf(position));
                if (position > 0) {
                    empId = empIds.get(position);
                    Log.d(TAG, "onItemSelected: " + empIds.get(position));
                    empName = empNames.get(position);
                    empMob = empMobNos.get(position);
                    paymentsearch_edit.setText(empName);
                    progressBar.show();
                    presenter.loadArea(getContext(), companyId, empId);
                      //presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);

                } else if(position==0) {
                    empId="0";
                        presenter.loadPaymentList(getContext(), companyId, custId, fromDate, toDate, triplePlayId, value, empId, areaId);


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
        spinnerArea.setOnItemSelectedListener(onItemSelectedListener);

    }

    public void loadRangeDatePickerMultipleBooking() {
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        long today = MaterialDatePicker.todayInUtcMilliseconds();


        int dialogTheme = 0;
        TypedValue typedValue = new TypedValue();
        Log.d("mActivityReference:", getActivity().toString());
        if (getActivity().getTheme().resolveAttribute(com.google.android.material.R.attr.materialCalendarTheme, typedValue, true)) {
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
        Uri uri = FileProvider.getUriForFile(requireActivity(),
                "elifeIndia.CRMS" + ".provider", imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "";
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
                "elifeIndia.CRMS" + ".provider", imagePath);

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
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
//        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
//                    showMessageOKCancel("You need to allow access to Storage", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
//                        }
//                    });
//                    return;
//                }
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
//            }
//            return;
//        } else {
            createPdf();
        //}
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
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File directoryName = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/elifeCRM");
        if (!directoryName.isFile()) {
            if (!(directoryName.isDirectory())) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    try {
                        Files.createDirectory(Paths.get(directoryName.getAbsolutePath()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "IOException", Toast.LENGTH_LONG).show();
                    }
                } else {
                    directoryName.mkdir();
                    directoryName.setWritable(true);
                    directoryName.setExecutable(true);
                    directoryName.setReadable(true);

                }
            }

        }


        String pdfname = timeStamp + ".pdf";
        pdfFile = new File(directoryName.getAbsolutePath(), pdfname);
        pdfFile.setExecutable(true);
        pdfFile.setWritable(true);
        pdfFile.setReadable(true);
        FileOutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, output);
        document.open();
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
        Font fontStyling = new Font(Font.FontFamily.TIMES_ROMAN, 30.0f, Font.UNDERLINE, BaseColor.BLUE);
        Font g = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.NORMAL, BaseColor.BLUE);

        document.add(new Paragraph("Collected By : " + empName, fontStyling));
        document.add(new Paragraph("Mobile No : " + empMob, fontStyling));
        document.add(new Paragraph("WhatsApp No : " + empMob, fontStyling));
        document.add(new Paragraph("From Date : " + fromDate, fontStyling));
        document.add(new Paragraph("To Date : " + toDate, fontStyling));
        document.add(new Paragraph("Total Amount Rs " + amount + "/-", fontStyling));
        document.add(new Paragraph("No of Payments " + no + "\n", fontStyling));
        document.add(new Paragraph("\n"));
        document.add(table);
        document.close();
        previewPdf(pdfFile);
        Log.e("data", paymentReciepts.toString());
        Log.e("pdfFile", pdfFile.toString());


    }


    private void previewPdf(File pdfFile) {
        PackageManager packageManager = context.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", pdfFile);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/pdf");
        context.startActivity(intent);

       /* List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
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
*/



       /* try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(getContext(),  BuildConfig.APPLICATION_ID+".fileProvider", pdfFile);
                intent.setDataAndType(contentUri,"application/pdf");
            } else {
                intent.setDataAndType(Uri.fromFile(pdfFile), "application/pdf");
            }
            startActivityForResult(intent, ACTIVITY_VIEW_ATTACHMENT);
        } catch (ActivityNotFoundException anfe) {
            Toast.makeText(getContext(), "No activity found to open this attachment.", Toast.LENGTH_LONG).show();
        }*/

    }


    @Override
    public void onClickCollection(PaymentRecieptList.PaymentReciept obj,String data) {
        SharedPrefsData.putString(getContext(), Constants.CustomerID,obj.getCustomerID().toString(), Constants.PREF_NAME);
        dataSend=obj;
        SharedPrefsData.putInt(getActivity(), Constants.PaymentId, Integer.parseInt(obj.getPayment_Id()), Constants.PREF_NAME);
        custommerPresenter.getInvoice(getContext(), String.valueOf(obj.getInvoice_ID()));

    }



}
