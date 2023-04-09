package com.elifeindia.crm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.CableSubscriptionAdapter;
import com.elifeindia.crm.adapters.InternetSuscriptionAdapter;
import com.elifeindia.crm.contract.activities.CustomerDetailsContract;
import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.presenter.activities.CustomerDetailsPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;

import java.util.List;

import static com.elifeindia.crm.view.activities.HomeActivity.hideCollectPayment;
import static com.elifeindia.crm.view.activities.HomeActivity.hideInvoice;
import static com.elifeindia.crm.view.activities.HomeActivity.hideReport;

public class CustomersDetailsActivity extends AppCompatActivity implements CustomerDetailsContract.View, View.OnClickListener {

    CustomerDetailsContract.Presenter presenter;
    ViewUtils viewUtils;
    TextView accountnumber, subscriberid, areaid, customername, address, txt_status,
            txt_whatsapp_no, balance, txt_mob_no, editCustDetails,tool_bar_text;
    RecyclerView rv_cable_subscription, rv_internet_subscription;
    CableSubscriptionAdapter cableSubscriptionAdapter;
    InternetSuscriptionAdapter internetSuscriptionAdapter;
    LinearLayout cablelayout, internetlayout, ll_pay, ll_bill_share, ll_invoice, ll_report;

    public  static String customerBalance;

    String sodowo, city, email, areaName, subArea, renew, custId, userId, companyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_details);
        accountnumber = findViewById(R.id.accountnumber);
        subscriberid = findViewById(R.id.subscriberid);
        areaid = findViewById(R.id.areaid);
        address = findViewById(R.id.address);
        customername = findViewById(R.id.customername);
        txt_status = findViewById(R.id.txt_status);
        balance = findViewById(R.id.balance);
        ll_pay = findViewById(R.id.ll_pay);
        rv_cable_subscription = findViewById(R.id.rv_cable_subscription);
        rv_internet_subscription = findViewById(R.id.rv_internet_subscription);
        cablelayout = findViewById(R.id.cablelayout);
        internetlayout = findViewById(R.id.internetlayout);
        ll_bill_share = findViewById(R.id.ll_bill_share);
        ll_invoice = findViewById(R.id.ll_invoice);

        ll_report = findViewById(R.id.ll_report);
        txt_whatsapp_no = findViewById(R.id.txt_whatsapp_no);
        txt_mob_no = findViewById(R.id.txt_mob_no);

        tool_bar_text = findViewById(R.id.tool_bar_text);

        editCustDetails = findViewById(R.id.edit_cust_details);

        editCustDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomersDetailsActivity.this, UpdateCustomerActivity.class);
                intent.putExtra("AccNo", accountnumber.getText().toString());
                intent.putExtra("SubsId", subscriberid.getText().toString());
                intent.putExtra("CustName", customername.getText().toString());
                intent.putExtra("SoDoWo", sodowo);
                intent.putExtra("Address", address.getText().toString());
                intent.putExtra("City", city);
                intent.putExtra("ContactNumber", txt_mob_no.getText().toString());
                intent.putExtra("WhatsappNumber", txt_whatsapp_no.getText().toString());
                intent.putExtra("EmailId", "NA");
                intent.putExtra("Aadhar", "NA");
                intent.putExtra("GST", "NA");
                intent.putExtra("Area", areaName);
                intent.putExtra("AreaCustomerId", areaid.getText().toString());
                intent.putExtra("SubArea", subArea);
                intent.putExtra("Remarks", "No Remarks");
                intent.putExtra("ConnectionStatus", txt_status.getText().toString());
                intent.putExtra("Renew", renew);
                intent.putExtra("CustID", custId);
                intent.putExtra("UserId", userId);
                intent.putExtra("CompanyId", companyId);
                startActivity(intent);
            }
        });

        subscriberid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = subscriberid.getText().toString();
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(text);
                } else {
                    android.content.ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(CustomersDetailsActivity.this, text + " Copied", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_whatsapp_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String contact = "+91" + txt_mob_no.getText().toString(); // use country code with your phone number
                String url = "https://api.whatsapp.com/send?phone=" + contact;
                try {
                    PackageManager pm = getPackageManager();
                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(CustomersDetailsActivity.this, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        txt_mob_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + txt_mob_no.getText().toString()));
                startActivity(intent);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        if (hideReport) {
            ll_report.setVisibility(View.GONE);

        } else {
            ll_report.setVisibility(View.VISIBLE);
        }
        if (hideCollectPayment) {
            ll_pay.setVisibility(View.GONE);
        } else {
            ll_pay.setVisibility(View.VISIBLE);
        }
        if (hideInvoice) {
            ll_invoice.setVisibility(View.GONE);
        } else {
            ll_invoice.setVisibility(View.VISIBLE);
        }


        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        ll_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomersDetailsActivity.this, CollectPaymentActivity2.class));
            }
        });

        ll_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("CustomerDetails",tool_bar_text.getText().toString());

                myEdit.commit();

                startActivity(new Intent(CustomersDetailsActivity.this, RegisterComplaintActivity2.class));
            }
        });

        ll_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(CustomersDetailsActivity.this, R.style.ListRow));

                /*builder.setTitle("Stay Connected With Us")
                        .setMessage("This functionality is coming soon")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();*/

                SharedPrefsData.putBool(CustomersDetailsActivity.this, Constants.isLoadAlacarte, true, Constants.PREF_NAME);
                SharedPrefsData.putBool(CustomersDetailsActivity.this, Constants.isLoadInternetPkg, true, Constants.PREF_NAME);
                SharedPrefsData.putBool(CustomersDetailsActivity.this, Constants.isLoadBouquet, true, Constants.PREF_NAME);
                   startActivity(new Intent(CustomersDetailsActivity.this, GenerateInvoiceActivity.class));
            }
        });


        ll_bill_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(CustomersDetailsActivity.this, R.style.ListRow));

                builder.setTitle("Stay Connected With Us")
                        .setMessage("This functionality is coming soon")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                // startActivity(new Intent(CustomersDetailsActivity.this, BillShareActivity.class));
            }
        });


        viewUtils = new ViewUtils();
        presenter = new CustomerDetailsPresenter(this);
        presenter.start();
        presenter.loadApi(this, SharedPrefsData.getString(this, Constants.CustomerID, Constants.PREF_NAME));
        presenter.loadCableBoxListApi(SharedPrefsData.getString(this, Constants.CustomerID, Constants.PREF_NAME));
        presenter.loadInternetBoxListApi(SharedPrefsData.getString(this, Constants.CustomerID, Constants.PREF_NAME));

    }

    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {
        viewUtils.toast(this, message);

    }

    @Override
    public void showResult(CustomerData customerData) {
        customerBalance= String.valueOf(customerData.getBalance());
        address.setText(customerData.getAddress());
        areaid.setText(customerData.getAreaCustomerID().toString());
        txt_mob_no.setText(customerData.getContactNo());
        txt_whatsapp_no.setText(customerData.getWhatsupNo());
        SharedPrefsData.putString(CustomersDetailsActivity.this, Constants.WhatsupNo, customerData.getWhatsupNo(), Constants.PREF_NAME);
        SharedPrefsData.putString(CustomersDetailsActivity.this, Constants.CustMob, customerData.getContactNo(), Constants.PREF_NAME);
        SharedPrefsData.putString(CustomersDetailsActivity.this, Constants.CustomerBalance, customerData.getBalance(), Constants.PREF_NAME);
        balance.setText(customerData.getBalance());
        if (SharedPrefsData.getString(getApplicationContext(), Constants.RoleId, Constants.PREF_NAME).equals("7")) {

            balance.setVisibility(View.INVISIBLE);
        }
        // SharedPrefsData.putString(CustomersDetailsActivity.this, Constants.CustomerBalance, customerData.getBalance().toString(), Constants.PREF_NAME);
        subscriberid.setText(customerData.getSubscriberID());
        accountnumber.setText(customerData.getAccountNo().toString());
        customername.setText(customerData.getName());

        try {
            sodowo = customerData.getSoDoWo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            city = customerData.getCity().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            areaName = customerData.getAreaName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            subArea = customerData.getSubareaName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            renew = customerData.getIsAutoRenew().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            custId = customerData.getCustomerID().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }


        userId = SharedPrefsData.getString(CustomersDetailsActivity.this, Constants.UserId, Constants.PREF_NAME);
        companyId = SharedPrefsData.getString(CustomersDetailsActivity.this, Constants.CompanyID, Constants.PREF_NAME);


        SharedPrefsData.putString(CustomersDetailsActivity.this, Constants.InvoiceType, customerData.getInvoiceType(), Constants.PREF_NAME);
        SharedPrefsData.putString(CustomersDetailsActivity.this, Constants.SubId, subscriberid.getText().toString(), Constants.PREF_NAME);
        SharedPrefsData.putString(CustomersDetailsActivity.this, Constants.CustomerName, customername.getText().toString(), Constants.PREF_NAME);

        Log.e("CustomerDetails", "showResult:  "+customerData.getInvoiceType()+ subscriberid.getText().toString()+customername.getText().toString());

        try {
            txt_status.setText(customerData.getStatusName().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showCableBoxList(CustemersCableBoxData custemersCableBoxData) {
        List<CustemersCableBoxData.CableBox> cableBoxList = custemersCableBoxData.getCableBox();

        if (cableBoxList.size() == 0) {
            cablelayout.setVisibility(View.GONE);
        } else {
            cablelayout.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_cable_subscription.setLayoutManager(mLayoutManager);
            cableSubscriptionAdapter = new CableSubscriptionAdapter(this, cableBoxList);
            rv_cable_subscription.setAdapter(cableSubscriptionAdapter);

        }

    }

    @Override
    public void showInernetBoxList(CustomersInternetBoxData customersInternetBoxData) {

        List<CustomersInternetBoxData.InternetBox> internetBoxList = customersInternetBoxData.getInternetBox();

        if (internetBoxList.size() == 0) {
            internetlayout.setVisibility(View.GONE);
        } else {
            internetlayout.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_internet_subscription.setLayoutManager(mLayoutManager);
            internetSuscriptionAdapter = new InternetSuscriptionAdapter(this, internetBoxList);
            rv_internet_subscription.setAdapter(internetSuscriptionAdapter);

        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CustomersDetailsActivity.this, CustomerListActivity.class));
        finish();

    }
}