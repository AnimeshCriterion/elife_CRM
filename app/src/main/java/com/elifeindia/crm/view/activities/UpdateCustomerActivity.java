package com.elifeindia.crm.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elifeindia.crm.R;
import com.elifeindia.crm.Retrofit.RetrofitClient;
import com.elifeindia.crm.UpdateCustomerPojo;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomerStatusResponse;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;
import com.elifeindia.crm.networking.RetrofitAdapter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdateCustomerActivity extends AppCompatActivity {

    Button update_details_btn;
    EditText acc_number, subscriber_id, cust_name, sodowo,cust_gst_number,cust_address, cust_city,
            cust_email_id, cust_aadhar_number, cust_area, cust_area_id, cust_subarea,
            cust_remarks;
    Spinner cust_connection_status;
    CheckBox renew_checkbox, cable_checkbox;
    EditText cust_number, cust_whtsap_number;
    ArrayAdapter adapter;
    int connection_statusId;
    List<CustomerStatusResponse.ConnectionStatus> connectionList;

    String CustID, UserId, CompanyId, AccNo, SubsId, CustName, SoDoWo, Address, City, ContactNumber, WhatsappNumber, EmailId, Aadhar, GST, Area, AreaCustomerId, SubArea, Remarks, ConnectionStatus, currentDateTime;

    ProgressDialog progressDialog;

    boolean  renewParam;
    String Renew;
    Intent intent;

    CustomerStatusResponse customerStatusResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        acc_number = findViewById(R.id.acc_number);
        subscriber_id = findViewById(R.id.subscriber_id);
        cust_name = findViewById(R.id.cust_name);
        sodowo = findViewById(R.id.sodowo);
        cust_address = findViewById(R.id.cust_address);
        cust_city = findViewById(R.id.cust_city);
        cust_email_id = findViewById(R.id.cust_email_id);
        cust_aadhar_number = findViewById(R.id.cust_aadhar_number);
        cust_gst_number = findViewById(R.id.cust_gst_number);
        cust_area = findViewById(R.id.cust_area);
        cust_area_id = findViewById(R.id.cust_area_id);
        cust_subarea = findViewById(R.id.cust_subarea);
        cust_remarks = findViewById(R.id.cust_remarks);
        cust_connection_status = findViewById(R.id.cust_connection_status);
        renew_checkbox = findViewById(R.id.renew_checkbox);
        cable_checkbox = findViewById(R.id.cable_checkbox);
        connectionList=new ArrayList<>();
        intent = getIntent();
        getCustomerConnectionStatus();
        Renew = intent.getStringExtra("Renew");
        Log.d("TAG", "onCreate: " + Renew);





        if (Renew.equals("true")) {
            renew_checkbox.setChecked(true);
            renewParam=true;
        }


        Log.d("TAG", "onCreate: " + SharedPrefsData.getString(this, Constants.RoleName, Constants.PREF_NAME));
        if (SharedPrefsData.getString(this, Constants.RoleType, Constants.PREF_NAME).equals("Admin") || SharedPrefsData.getString(this, Constants.RoleType, Constants.PREF_NAME).equals("Manager")) {
           renew_checkbox.setEnabled(true);
            cust_connection_status.setEnabled(true);
            renew_checkbox.setOnCheckedChangeListener((compoundButton, b) -> renewParam = b);
        }
        else {
            renew_checkbox.setEnabled(false);
            cust_connection_status.setEnabled(false);
        }

        cust_number = findViewById(R.id.cust_number);
        cust_whtsap_number = findViewById(R.id.cust_whtsap_number);

        update_details_btn = findViewById(R.id.update_details_btn);

        progressDialog = new ProgressDialog(this);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDateTime = dateFormat.format(new Date());

        AccNo = intent.getStringExtra("AccNo");
        SubsId = intent.getStringExtra("SubsId");
        CustName = intent.getStringExtra("CustName");
        SoDoWo = intent.getStringExtra("SoDoWo");
        Address = intent.getStringExtra("Address");
        City = intent.getStringExtra("City");
        ContactNumber = intent.getStringExtra("ContactNumber");
        WhatsappNumber = intent.getStringExtra("WhatsappNumber");
        EmailId = intent.getStringExtra("EmailId");
        Aadhar = intent.getStringExtra("Aadhar");
        GST = intent.getStringExtra("GST");
        Area = intent.getStringExtra("Area");
        AreaCustomerId = intent.getStringExtra("AreaCustomerId");
        SubArea = intent.getStringExtra("SubArea");
        Remarks = intent.getStringExtra("Remarks");
        ConnectionStatus = intent.getStringExtra("ConnectionStatus");
         //cust_connection_status.setSelection(adapter.);
      //  cust_connection_status.setSelection(getIndex(cust_connection_status, ConnectionStatus));
      //  cust_connection_status.setSelection((((ArrayAdapter<String>)cust_connection_status.getAdapter()).getPosition(ConnectionStatus)));

        CustID = intent.getStringExtra("CustID");
        UserId = intent.getStringExtra("UserId");
        CompanyId = intent.getStringExtra("CompanyId");


        acc_number.setText(AccNo);
        subscriber_id.setText(SubsId);
        cust_name.setText(CustName);
        sodowo.setText("NA");
        cust_address.setText(Address);
        cust_city.setText(City);

        cust_number.setText(ContactNumber);
        cust_whtsap_number.setText(WhatsappNumber);

        cust_email_id.setText(EmailId);
        cust_aadhar_number.setText(Aadhar);
        cust_gst_number.setText(GST);
        cust_area.setText(Area);
        cust_area_id.setText(AreaCustomerId);
        cust_subarea.setText(SubArea);
        cust_remarks.setText(Remarks);
        // cust_connection_status.setText(ConnectionStatus);

        renew_checkbox.isChecked();
        cable_checkbox.isChecked();



        update_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateCustomerPojo updateCustomerPojo=new UpdateCustomerPojo();
//                final ProgressDialog progressBar;
//                progressBar = new ProgressDialog(getApplicationContext());
//                progressBar.setCancelable(false);//you can cancel it by pressing back button
//                progressBar.setMessage("Please wait...");
//                progressBar.show();

//                params.put("customer_ID", CustID);
//                params.put("company_ID", CompanyId);
//                params.put("account_No", acc_number.getText().toString());
//                params.put("subscriber_ID", subscriber_id.getText().toString());
//                params.put("name", cust_name.getText().toString());
//                params.put("soDoWo","NA");
//                params.put("address", "");
//                params.put("contact_No", cust_number.getText().toString());
//                params.put("whatsup_No", cust_whtsap_number.getText().toString());
//                params.put("emailId", "NA");
//                params.put("aadhar_No", "NA");
//                params.put("gsT_Number", "NA");
//                params.put("area_Customer_ID", "1201-1");
//                params.put("user_ID", UserId);
//                params.put("is_Auto_Renew", String.valueOf(renewParam));
//                params.put("connection_Status_ID", String.valueOf(connection_statusId));
//                params.put("date", currentDateTime);
//
                updateCustomerPojo.setCustomerID(CustID);
                updateCustomerPojo.setAreaCustomerID(cust_area_id.getText().toString());
                updateCustomerPojo.setAadharNo(cust_aadhar_number.getText().toString());
                updateCustomerPojo.setGsTNumber(cust_gst_number.getText().toString());
                updateCustomerPojo.setAddress( cust_address.getText().toString());
                updateCustomerPojo.setCompanyID(CompanyId);
                updateCustomerPojo.setDate(currentDateTime);
                updateCustomerPojo.setContactNo( cust_number.getText().toString());
                updateCustomerPojo.setEmailId("NA");
                updateCustomerPojo.setConnectionStatusID(String.valueOf(connection_statusId));
                updateCustomerPojo.setIsAutoRenew( String.valueOf(renewParam));
                updateCustomerPojo.setUserID(UserId);
                updateCustomerPojo.setAccountNo(acc_number.getText().toString());
                updateCustomerPojo.setName(cust_name.getText().toString());
                updateCustomerPojo.setWhatsupNo(cust_whtsap_number.getText().toString());
                updateCustomerPojo.setSoDoWo("NA");
                updateCustomerPojo.setSubscriberID(subscriber_id.getText().toString());


                if(NetCheck.isInternetConnection(getApplicationContext())){
                    NetworkUtils.getUserApiInstance()
                            .updateCustomer(updateCustomerPojo)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber() {
                                @Override
                                public void onCompleted() {
                                   // progressBar.dismiss();
                                    Toast.makeText(UpdateCustomerActivity.this, "Details Updated", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UpdateCustomerActivity.this, CustomersDetailsActivity.class));
                                   finish();
                                }
                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(UpdateCustomerActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                                  //  mView.showError(e.toString());
                                }

                                @Override
                                public void onNext(Object o) {

                                }


                            });
                }else{
                  //  progressBar.dismiss();
                   // mView.showError("Connection Error");
                }

//                progressDialog.setMessage("Please Wait");
//                progressDialog.show();
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, RetrofitAdapter.UPDATE_CUSTOMER,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                //Toast.makeText(c, response, Toast.LENGTH_LONG).show();
//                                Log.d("TAG", "onResponse: "+response);
//                                Toast.makeText(UpdateCustomerActivity.this, "Details Updated"+response.toString(), Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(UpdateCustomerActivity.this, CustomersDetailsActivity.class));
//                                finish();
//                                progressDialog.hide();
//
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(UpdateCustomerActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
//                                progressDialog.hide();
//                            }
//                        }) {
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> params = new HashMap<>();
//                        params.put("customer_ID", CustID);
//                        params.put("company_ID", CompanyId);
//                        params.put("account_No", acc_number.getText().toString());
//                        params.put("subscriber_ID", subscriber_id.getText().toString());
//                        params.put("name", cust_name.getText().toString());
//                        params.put("soDoWo","NA");
//                        params.put("address", "");
//                        params.put("contact_No", cust_number.getText().toString());
//                        params.put("whatsup_No", cust_whtsap_number.getText().toString());
//                        params.put("emailId", "NA");
//                        params.put("aadhar_No", "NA");
//                        params.put("gsT_Number", "NA");
//                        params.put("area_Customer_ID", "1201-1");
//                        params.put("user_ID", UserId);
//                        params.put("is_Auto_Renew", String.valueOf(renewParam));
//                        params.put("connection_Status_ID", String.valueOf(connection_statusId));
//                        params.put("date", currentDateTime);
//                        Log.d("TAG", "getParams: " + params.toString());
//                        Log.d("TAG", "getParams: "+params.toString());
//                        return params;
//                    }
//                };
//                RequestQueue requestQueue = Volley.newRequestQueue(UpdateCustomerActivity.this);
//                requestQueue.add(stringRequest);

            }
        });

    }

    private void getCustomerConnectionStatus() {
        Call<CustomerStatusResponse> call = RetrofitClient.getInstance().getApi().getCustomerStatus();

        call.enqueue(new Callback<CustomerStatusResponse>() {
            @Override
            public void onResponse(Call<CustomerStatusResponse> call, retrofit2.Response<CustomerStatusResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG", "onResponse: " + response.body().getConnectionStatus());
                    customerStatusResponse=response.body();
                    connectionList=customerStatusResponse.getConnectionStatus();

                    ArrayList<String> connectionName = new ArrayList<>();
                    for (int i = 0; i < connectionList.size(); i++) {
                        connectionName.add(connectionList.get(i).getStatusName());
                    }
                    adapter = new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_spinner_item, connectionName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    cust_connection_status.setAdapter(adapter);
                    Log.d("TAG", "onCreatecghgjh: "+adapter.getPosition(ConnectionStatus));
    cust_connection_status.setSelection(adapter.getPosition(ConnectionStatus));
                    cust_connection_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            connection_statusId = response.body().getConnectionStatus().get(i).getConnectionStatusID();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<CustomerStatusResponse> call, Throwable t) {

            }
        });


    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equals(myString)){
                return i;
            }
        }

        return 0;
    }
}