package com.elifeindia.crm.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.elifeindia.crm.networking.RetrofitAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateCustomerActivity extends AppCompatActivity {

    Button update_details_btn;
    TextView acc_number, subscriber_id, cust_name, sodowo, cust_address, cust_city,
            cust_email_id, cust_aadhar_number, cust_gst_number, cust_area, cust_area_id, cust_subarea,
            cust_remarks, cust_connection_status;
    CheckBox renew_checkbox, cable_checkbox;
    EditText cust_number, cust_whtsap_number;

    String CustID, UserId, CompanyId,AccNo, SubsId, CustName, SoDoWo,Address,City,ContactNumber,WhatsappNumber,EmailId,Aadhar,GST,Area,AreaCustomerId,SubArea,Remarks,ConnectionStatus, currentDateTime;

    ProgressDialog progressDialog;

    boolean Renew;

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

        cust_number = findViewById(R.id.cust_number);
        cust_whtsap_number = findViewById(R.id.cust_whtsap_number);

        update_details_btn = findViewById(R.id.update_details_btn);

        progressDialog = new ProgressDialog(this);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDateTime = dateFormat.format(new Date());

        Intent intent = getIntent();

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
        Renew = intent.getBooleanExtra("Renew", false);
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
        cust_connection_status.setText(ConnectionStatus);

        renew_checkbox.isChecked();
        cable_checkbox.isChecked();

        update_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Please Wait");
                progressDialog.show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, RetrofitAdapter.UPDATE_CUSTOMER,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(c, response, Toast.LENGTH_LONG).show();
                                Toast.makeText(UpdateCustomerActivity.this, "Details Updated", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(UpdateCustomerActivity.this, CustomersDetailsActivity.class));

                                finish();
                                progressDialog.hide();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(UpdateCustomerActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                progressDialog.hide();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("customer_ID", CustID);
                        params.put("company_ID", CompanyId);
                        params.put("account_No", acc_number.getText().toString());
                        params.put("subscriber_ID", subscriber_id.getText().toString());
                        params.put("name",cust_name.getText().toString());
                        params.put("soDoWo", sodowo.getText().toString());
                        params.put("address", cust_address.getText().toString());
                        params.put("contact_No", cust_number.getText().toString());
                        params.put("whatsup_No", cust_whtsap_number.getText().toString());
                        params.put("emailId", cust_email_id.getText().toString());
                        params.put("aadhar_No", cust_aadhar_number.getText().toString());
                        params.put("gsT_Number", cust_gst_number.getText().toString());
                        params.put("area_Customer_ID", "1201-1");
                        params.put("user_ID", UserId);
                        params.put("date", currentDateTime);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(UpdateCustomerActivity.this);
                requestQueue.add(stringRequest);

            }
        });

    }
}