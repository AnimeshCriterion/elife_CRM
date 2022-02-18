package com.elifeindia.crm.view.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.elifeindia.crm.R;
import com.elifeindia.crm.contract.activities.AddReportContract;
import com.elifeindia.crm.model.ComplaintStatusList;
import com.elifeindia.crm.model.ComplaintTypeList;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.PriorityList;
import com.elifeindia.crm.model.ProductList;
import com.elifeindia.crm.presenter.activities.AddReportPresenter;
import com.elifeindia.crm.printersdk.ComplaintReceiptActivity;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.view.activities.ComplaintDetailsActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RegisterComplaintFragment extends Fragment implements AddReportContract.View {
    AddReportContract.Presenter presenter;
    TextView complaint_date, complaint_assign_date, customer_name, custmer_location;
    EditText edt_comment;
    Button addcompalintbtn;
    public static String complaintId, complaintDate, complaintAssignDate, roleType = "", compId = "0", userId, assignTo, assignToID = "0", custId = "0", product, prodId = "0", complaintTypeId = "0", compStatus, statusId = "0", priority, priorityId = "0", empId = "0", complaintType = "";
    Spinner spn_complaint, spn_Product, spn_assigned_to, spn_status, spn_priority, spn_emp;
    ArrayAdapter<String> adapter_emp, adapter_Product, adapter_complaint, adapter_status, adapter_priority;

    public RegisterComplaintFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_add_report, container, false);

        spn_Product = v.findViewById(R.id.spn_Product);
        spn_priority = v.findViewById(R.id.spn_priority);
        spn_emp = v.findViewById(R.id.spn_assigned_to);
        spn_status = v.findViewById(R.id.spn_status);
        spn_complaint = v.findViewById(R.id.spn_complaint);
        complaint_date = v.findViewById(R.id.complaint_date);
        complaint_assign_date = v.findViewById(R.id.complaint_assign_date);
        customer_name = v.findViewById(R.id.customer_name);
        custmer_location = v.findViewById(R.id.custmer_location);
        edt_comment = v.findViewById(R.id.edt_comment);


        v.findViewById(R.id.tool_bar).setVisibility(View.GONE);

        complaint_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // To show current date in the datepicker
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker,
                                          int selectedyear, int selectedmonth,
                                          int selectedday) {

                        mcurrentDate.set(Calendar.YEAR, selectedyear);
                        mcurrentDate.set(Calendar.MONTH, selectedmonth);
                        mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US);
                        //txt_req_date.setText(sdf.format(mcurrentDate.getTime()));

                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

                        complaintDate = sdf1.format(mcurrentDate.getTime());
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
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker,
                                          int selectedyear, int selectedmonth,
                                          int selectedday) {

                        mcurrentDate.set(Calendar.YEAR, selectedyear);
                        mcurrentDate.set(Calendar.MONTH, selectedmonth);
                        mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US);
                        //txt_req_date.setText(sdf.format(mcurrentDate.getTime()));
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd HH:mm", Locale.US);

                        complaintAssignDate = sdf1.format(mcurrentDate.getTime());
                        complaint_assign_date.setText(sdf.format(mcurrentDate.getTime()));

                        //SharedPrefsData.putString(CustomerListActivity.this, Constants.RequestDate, txt_req_date.getText().toString(), Constants.PREF_NAME);

                    }
                }, mYear, mMonth, mDay);
                // mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDatePicker.setTitle("Select Expiry Date");
                mDatePicker.show();

            }
        });

        v.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        presenter = new AddReportPresenter(this);
        presenter.start();

        compId = SharedPrefsData.getString(getActivity(), Constants.CompanyID, Constants.PREF_NAME);

        //Toast.makeText(getActivity(), ""+compId, Toast.LENGTH_SHORT).show();

        userId = SharedPrefsData.getString(getActivity(), Constants.UserId, Constants.PREF_NAME);
        custId = SharedPrefsData.getString(getActivity(), Constants.CustomerID, Constants.PREF_NAME);

        //String custCode = SharedPrefsData.getString(getActivity(), Constants.CustomerID, Constants.PREF_NAME);

        customer_name.setText(SharedPrefsData.getString(getActivity(), Constants.CustomerName, Constants.PREF_NAME));
        custmer_location.setText(SharedPrefsData.getString(getActivity(), Constants.CustomerAddress, Constants.PREF_NAME));


        complaintDate = todayDateString();
        complaintAssignDate = todayDateString();

        complaint_date.setText(todayDateShow());
        complaint_assign_date.setText(todayDateShow());


        addcompalintbtn = v.findViewById(R.id.addcompalintbtn);
        addcompalintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = edt_comment.getText().toString();
                if (comment.equals("")) {
                    comment = "No Remark";
                }

                if (isValid()) {
                    presenter.insertComplaint(getActivity(), "0", compId, custId, "0",
                            complaintDate, complaintTypeId, "", prodId, todayDateString(),
                            assignToID, statusId, comment, priorityId, userId, complaintAssignDate);

                }
            }
        });

        roleType = SharedPrefsData.getString(getActivity(), Constants.RoleType, Constants.PREF_NAME);

        presenter.loadEmployeeList(getActivity(), compId, SharedPrefsData.getString(getActivity(), Constants.EmpId, Constants.PREF_NAME), roleType);
        presenter.getProductList(getActivity(), compId, "0");
        presenter.getComplaintTypeList(getActivity(), compId, "0");
        presenter.getComplaintStatusList(getActivity(), compId, "0");
        presenter.getPriorityList(getActivity(), compId, "0");

        return v;

    }

    @Override
    public void init() {


    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showComplaintTypeList(ComplaintTypeList complaintTypeList) {

        final List<ComplaintTypeList.ComplaintType> complaintTypes = complaintTypeList.getComplaintType();

        ArrayList statesList = new ArrayList();
        //   statesList.add("Not selected");
        for (int i = 0; i < complaintTypes.size(); i++) {
            statesList.add(complaintTypes.get(i).getComplaintType());
        }

        adapter_complaint = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, statesList);
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
                        complaintType = complaintTypes.get(position).getComplaintType();

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

        adapter_Product = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, statesList);
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
                        product = products.get(position).getProductName().toString();

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

        adapter_status = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, statesList);
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
                        compStatus = complaintstatuses.get(position).getComplaintStatus().toString();
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

        adapter_priority = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, statesList);
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
                        priority = priorityList1.get(position).getPriorityName().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
        spn_priority.setOnItemSelectedListener(onItemSelectedListener2);
    }

    @Override
    public void showEmployeeList(EmployeeList employeeList) {
       /* final List<EmployeeList.Employee> employee = employeeList.getEmployee();


        ArrayList<String> statesList = new ArrayList();

        //statesList.add(0,"--Select--");

        //statesList.add("Not selected");
        for (int i = 0; i < employee.size(); i++) {
            statesList.add(employee.get(i).getEmployeeName());
        }*/

        final List<EmployeeList.Employee> employee = employeeList.getEmployee();

        final ArrayList<String> statesList = new ArrayList<>();
        final ArrayList<String> empIds = new ArrayList<String>();
        final ArrayList<String> empMobNos = new ArrayList<String>();

        statesList.add("--Select--");
//        empMobNos.add("All Contacts");
          empIds.add("0");

        for (int i = 0; i < employee.size(); i++) {
            statesList.add(employee.get(i).getEmployeeName());
            empIds.add(employee.get(i).getEmployeeID().toString());
//            empMobNos.add(employee.get(i).getContactNo().toString());

        }

        adapter_emp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, statesList);
        adapter_emp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_emp.setAdapter(adapter_emp);

       spn_emp.setSelection(0);

        if (spn_emp.getSelectedItemId() == 0) {
            spn_status.setSelection(0);
        } else {
            spn_status.setSelection(1);
        }

        AdapterView.OnItemSelectedListener onItemSelectedListener2 =
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        if(position==0){
//                            assignToID="0";
//                        }else{
//                            assignToID = employee.get(position).getEmployeeID().toString();
//                        }
//                        assignToID = employee.get(position).getEmployeeID().toString();
//                        assignTo = employee.get(position).getEmployeeName().toString();

                        assignToID = empIds.get(position);
                        assignTo = statesList.get(position);


                        if (spn_emp.getSelectedItemId() == 0) {
                            spn_status.setSelection(0);
                        } else {
                            spn_status.setSelection(1);
                        }


//                        statusId = "1";
//                        compStatus = "Assigned";

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
    public void insertResponse(InsertPayment insertPayment) {

        complaintId = insertPayment.getId().toString();
        Toast.makeText(getActivity(), "Complaint inserted successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), ComplaintReceiptActivity.class);
        i.putExtra("activity_name", "InsertComplaint");
        startActivity(i);


    }

    private String todayDateString() {

        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
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
            Toast.makeText(getActivity(), "Please Select product", Toast.LENGTH_SHORT).show();
            return false;
        } else if (complaintTypeId.equalsIgnoreCase("0")) {
            Toast.makeText(getActivity(), "Please Select complaint type", Toast.LENGTH_SHORT).show();
            return false;
        } else if (priorityId.equalsIgnoreCase("0")) {
            Toast.makeText(getActivity(), "Please Select priority", Toast.LENGTH_SHORT).show();
            return false;
        } else if (statusId.equalsIgnoreCase("0")) {
            Toast.makeText(getActivity(), "Please Select complaint status", Toast.LENGTH_SHORT).show();
            return false;
        } else if (assignToID.equalsIgnoreCase("0")) {
            Toast.makeText(getActivity(), "Please Select employee to assign", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}