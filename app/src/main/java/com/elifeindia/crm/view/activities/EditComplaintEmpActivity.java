package com.elifeindia.crm.view.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elifeindia.crm.R;
import com.elifeindia.crm.contract.activities.EditReportContract;
import com.elifeindia.crm.model.ComplaintStatusList;
import com.elifeindia.crm.model.ComplaintTypeList;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.PriorityList;
import com.elifeindia.crm.model.ProductList;
import com.elifeindia.crm.networking.RetrofitAdapter;
import com.elifeindia.crm.presenter.activities.EditReportPresenter;
import com.elifeindia.crm.printersdk.ComplaintReceiptActivity;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.elifeindia.crm.adapters.ComplaintListAdapter.complaintCode;
import static com.elifeindia.crm.adapters.ComplaintListAdapter.complaintId;

public class EditComplaintEmpActivity extends AppCompatActivity implements EditReportContract.View {
    EditReportContract.Presenter presenter;
    TextView complaint_date, complaint_assign_date, customer_name, custmer_location;
    EditText edt_comment;
    Button addcompalintbtn;

    DateTimeFormatter comDateformatter, compAssignformatter;
    LocalDateTime compDateTime, compAssignTime;

    public static String complaintIdEdit, complaintDateEdit, complaintAssignDate, roleType = "", compId = "0", userId, assignToEdit, assignToID = "0", custId = "0", productEdit, prodId = "0", complaintTypeId = "0", compStatusEdit, statusId = "0", priorityEdit, priorityId = "0", empId = "0", complaintTypeEdit = "", complaintID = "";
    Spinner spn_complaint, spn_Product, spn_assigned_to, spn_status, spn_priority, spn_emp;
    ArrayAdapter<String> adapter_emp, adapter_Product, adapter_complaint, adapter_status, adapter_priority;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_report);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        presenter = new EditReportPresenter(this);
        presenter.start();

        progressDialog = new ProgressDialog(this);

        compId = SharedPrefsData.getString(this, Constants.CompanyID, Constants.PREF_NAME);
        userId = SharedPrefsData.getString(this, Constants.UserId, Constants.PREF_NAME);
        custId = SharedPrefsData.getString(this, Constants.CustomerID, Constants.PREF_NAME);
        empId = SharedPrefsData.getString(this, Constants.EmpId, Constants.PREF_NAME);

        complaintID = SharedPrefsData.getString(this, Constants.ComplaintID, Constants.PREF_NAME);

        customer_name.setText(SharedPrefsData.getString(this, Constants.CustomerName, Constants.PREF_NAME));
        custmer_location.setText(SharedPrefsData.getString(this, Constants.CustomerAddress, Constants.PREF_NAME));


        complaintDateEdit = todayDateString();
        complaintAssignDate = todayDateString();

        complaint_date.setText(getIntent().getStringExtra("DateClose"));
        complaint_assign_date.setText(getIntent().getStringExtra("DateClose"));


        addcompalintbtn = findViewById(R.id.addcompalintbtn);
        addcompalintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValid()) {

                    progressDialog.setMessage("Please Wait");
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, RetrofitAdapter.OPEN_CLOSE,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //Toast.makeText(c, response, Toast.LENGTH_LONG).show();
                                    Toast.makeText(EditComplaintEmpActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(EditComplaintEmpActivity.this, ComplaintDetailsActivity.class));

                                    finish();
                                    progressDialog.hide();

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(EditComplaintEmpActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                    progressDialog.hide();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();


                            params.put("complaint_ID", complaintID);

                            if (spn_status.getSelectedItem().toString().equals("Open")) {

                                params.put("open_Date", todayDateShow());
                                params.put("open_By", empId);

                                params.put("closed_Date", "");
                                params.put("closed_By", "");

                            }

                            if (spn_status.getSelectedItem().toString().equals("Closed")) {

                                params.put("open_Date", "");
                                params.put("open_By", "");

                                params.put("closed_Date", todayDateShow());
                                params.put("closed_By", empId);
                            }

                            params.put("complaint_Status_ID", statusId);
                            params.put("comment", edt_comment.getText().toString());

                            params.put("user_ID", userId);
                            params.put("date", todayDateShow());

                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(EditComplaintEmpActivity.this);
                    requestQueue.add(stringRequest);

//                    try {
//                        presenter.updateComplaint(EditComplaintEmpActivity.this, complaintId, compId, custId, complaintCode, complaintDateEdit, complaintTypeId, "",prodId, todayDateString(), assignToID, statusId, edt_comment.getText().toString(), priorityId, userId, complaintAssignDate);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                }
            }
        });


        String PriorityID = SharedPrefsData.getString(this, Constants.PriorityID, Constants.PREF_NAME);
        String ProductID = SharedPrefsData.getString(this, Constants.ProductID, Constants.PREF_NAME);
        String ComplaintTypeID = SharedPrefsData.getString(this, Constants.ComplaintTypeID, Constants.PREF_NAME);
        String StatusID = SharedPrefsData.getString(this, Constants.StatusID, Constants.PREF_NAME);
        String AssignToID = SharedPrefsData.getString(this, Constants.AssignToID, Constants.PREF_NAME);

        roleType = SharedPrefsData.getString(this, Constants.RoleType, Constants.PREF_NAME);

        presenter.getProductList(this, compId, ProductID);
        presenter.getComplaintTypeList(this, compId, ComplaintTypeID);
        presenter.getComplaintStatusList(this, compId, StatusID);
        presenter.getPriorityList(this, compId, PriorityID);
        presenter.loadEmployeeList(this, compId, AssignToID, roleType);

    }

    @Override
    public void init() {

        spn_Product = findViewById(R.id.spn_Product);
        spn_priority = findViewById(R.id.spn_priority);
        spn_emp = findViewById(R.id.spn_assigned_to);
        spn_status = findViewById(R.id.spn_status);
        spn_complaint = findViewById(R.id.spn_complaint);
        complaint_date = findViewById(R.id.complaint_date);
        complaint_assign_date = findViewById(R.id.complaint_assign_date);
        customer_name = findViewById(R.id.customer_name);
        custmer_location = findViewById(R.id.custmer_location);
        edt_comment = findViewById(R.id.edt_comment);
        edt_comment.setText(SharedPrefsData.getString(this, Constants.Remark, Constants.PREF_NAME));

        spn_Product.setEnabled(false);
        spn_complaint.setEnabled(false);
        complaint_date.setEnabled(false);
        spn_priority.setEnabled(false);
        spn_emp.setEnabled(false);
        complaint_assign_date.setEnabled(false);

        complaint_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // To show current date in the datepicker
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(
                        EditComplaintEmpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker,
                                          int selectedyear, int selectedmonth,
                                          int selectedday) {

                        mcurrentDate.set(Calendar.YEAR, selectedyear);
                        mcurrentDate.set(Calendar.MONTH, selectedmonth);
                        mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US);
                        //txt_req_date.setText(sdf.format(mcurrentDate.getTime()));
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            comDateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
                        }

                        String newDate = getIntent().getStringExtra("DateClose");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            compDateTime = LocalDateTime.parse(newDate,comDateformatter);
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            complaintDateEdit = String.valueOf(compDateTime);
                        }
                        complaint_date.setText(sdf.format(mcurrentDate.getTime()));


                        //SharedPrefsData.putString(CustomerListActivity.this, Constants.RequestDate, txt_req_date.getText().toString(), Constants.PREF_NAME);

                    }
                }, mYear, mMonth, mDay);
                // mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDatePicker.setTitle("Select Expiry Date");
                mDatePicker.show();
            }
        });

        complaint_assign_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(
                        EditComplaintEmpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker,
                                          int selectedyear, int selectedmonth,
                                          int selectedday) {

                        mcurrentDate.set(Calendar.YEAR, selectedyear);
                        mcurrentDate.set(Calendar.MONTH, selectedmonth);
                        mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US);
                        //txt_req_date.setText(sdf.format(mcurrentDate.getTime()));
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            compAssignformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
                        }

                        String newDate = getIntent().getStringExtra("DateClose");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            compAssignTime = LocalDateTime.parse(newDate,compAssignformatter);
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            complaintAssignDate = String.valueOf(compAssignTime);
                        }

                        //complaintAssignDate = getIntent().getStringExtra("DateClose");
                        complaint_assign_date.setText(sdf.format(mcurrentDate.getTime()));

                        //SharedPrefsData.putString(CustomerListActivity.this, Constants.RequestDate, txt_req_date.getText().toString(), Constants.PREF_NAME);

                    }
                }, mYear, mMonth, mDay);
                // mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDatePicker.setTitle("Select Expiry Date");
                mDatePicker.show();

            }
        });

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showComplaintTypeList(ComplaintTypeList complaintTypeList) {

        final List<ComplaintTypeList.ComplaintType> complaintTypes = complaintTypeList.getComplaintType();

        ArrayList statesList = new ArrayList();
        //   statesList.add("Not selected");
        for (int i = 0; i < complaintTypes.size(); i++) {
            statesList.add(complaintTypes.get(i).getComplaintType());
        }

        adapter_complaint = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_complaint.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_complaint.setAdapter(adapter_complaint);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                        if(position==0){
//                            complaintTypeId="0";
//                        }else{
//                            complaintTypeId = complaintTypes.get(position).getComplaintTypeID().toString();
//                            complaintType = complaintTypes.get(position).getComplaintType();                        }

                        complaintTypeId = complaintTypes.get(position).getComplaintTypeID().toString();
                        complaintTypeEdit = complaintTypes.get(position).getComplaintType();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
        spn_complaint.setOnItemSelectedListener(onItemSelectedListener2);

    }

    @Override
    public void showProductList(ProductList productList) {

        final List<ProductList.Product> products = productList.getProduct();

        ArrayList statesList = new ArrayList();
        // statesList.add("Not selected");
        for (int i = 0; i < products.size(); i++) {
            statesList.add(products.get(i).getProductName());
        }

        adapter_Product = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_Product.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_Product.setAdapter(adapter_Product);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                         if(position==0){
//                             prodId="0";
//                        }else{
//                             prodId = products.get(position).getProductID().toString();
//                         }
                        prodId = products.get(position).getProductID().toString();
                        productEdit = products.get(position).getProductName().toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
        spn_Product.setOnItemSelectedListener(onItemSelectedListener2);

    }

    @Override
    public void showComplaintStatusList(ComplaintStatusList complaintStatusList) {
        final List<ComplaintStatusList.Complaintstatus> complaintstatuses = complaintStatusList.getComplaintStatus();

        ArrayList statesList = new ArrayList();
        //statesList.add("Not selected");
        for (int i = 0; i < complaintstatuses.size(); i++) {
            statesList.add(complaintstatuses.get(i).getComplaintStatus());
        }

        adapter_status = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_status.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_status.setAdapter(adapter_status);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        if(position==0){
//                            statusId="0";
//                        }else{
//                            statusId = complaintstatuses.get(position).getComplaintStatusID().toString();
//                        }
                        statusId = complaintstatuses.get(position).getComplaintStatusID().toString();
                        compStatusEdit = complaintstatuses.get(position).getComplaintStatus().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
        spn_status.setOnItemSelectedListener(onItemSelectedListener2);
    }

    @Override
    public void showPriorityList(PriorityList priorityList) {
        final List<PriorityList.Priority> priorityList1 = priorityList.getPriority();

        ArrayList statesList = new ArrayList();
        //statesList.add("Not selected");
        for (int i = 0; i < priorityList1.size(); i++) {
            statesList.add(priorityList1.get(i).getPriorityName());
        }

        adapter_priority = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_priority.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_priority.setAdapter(adapter_priority);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        if(position==0){
//                            priorityId="0";
//                        }else{
//                            priorityId = priorityList1.get(position).getPriorityID().toString();
//                        }
                        priorityId = priorityList1.get(position).getPriorityID().toString();
                        priorityEdit = priorityList1.get(position).getPriorityName().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
        spn_priority.setOnItemSelectedListener(onItemSelectedListener2);
    }

    @Override
    public void showEmployeeList(EmployeeList employeeList) {
        final List<EmployeeList.Employee> employee = employeeList.getEmployee();

        ArrayList statesList = new ArrayList();
        //statesList.add("Not selected");
        for (int i = 0; i < employee.size(); i++) {
            statesList.add(employee.get(i).getEmployeeName());
        }

        adapter_emp = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_emp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_emp.setAdapter(adapter_emp);

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        if(position==0){
//                            assignToID="0";
//                        }else{
//                            assignToID = employee.get(position).getEmployeeID().toString();
//                        }
                        assignToID = employee.get(position).getEmployeeID().toString();
                        assignToEdit = employee.get(position).getEmployeeName().toString();
//                        presenter.loadComplaintList(ComplaintDetailsActivity.this, companyId, empId, "0", "0",  fromDate, toDate);
//                        viewUtils.showProgressBar(ComplaintDetailsActivity.this);

//                        SharedPrefsData.putString(getActivity(), Constants.EmpId, empId, Constants.PREF_NAME);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
        spn_emp.setOnItemSelectedListener(onItemSelectedListener2);
    }

    @Override
    public void updateResponse(InsertPayment insertPayment) {

        try {
            complaintIdEdit = insertPayment.getId().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Complaint updated successfully", Toast.LENGTH_SHORT).show();
        String flag = SharedPrefsData.getString(this, Constants.ComplaintFlag, Constants.PREF_NAME);

        if (flag.equals("fragment")) {
            finish();
        } else {
            Intent i = new Intent(this, ComplaintDetailsActivity.class);
            i.putExtra("activity_name", "UpdateComplaint");
            startActivity(i);
            finish();
        }


    }

    private String todayDateString() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());

    }

    private String todayDateShow() {

        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());

    }

    private boolean isValid() {

        if (prodId.equalsIgnoreCase("0")) {
            Toast.makeText(this, "Please Select product", Toast.LENGTH_SHORT).show();
            return false;
        } else if (complaintTypeId.equalsIgnoreCase("0")) {
            Toast.makeText(this, "Please Select complaint type", Toast.LENGTH_SHORT).show();
            return false;
        } else if (priorityId.equalsIgnoreCase("0")) {
            Toast.makeText(this, "Please Select priority", Toast.LENGTH_SHORT).show();
            return false;
        } else if (statusId.equalsIgnoreCase("0")) {
            Toast.makeText(this, "Please Select complaint status", Toast.LENGTH_SHORT).show();
            return false;
        } else if (assignToID.equalsIgnoreCase("0")) {
            Toast.makeText(this, "Please Select employee to assign", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}