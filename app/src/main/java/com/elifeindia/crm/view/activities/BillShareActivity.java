package com.elifeindia.crm.view.activities;

import static com.elifeindia.crm.adapters.generate_invoice_module.AlacarteListBillShareAdapter.TotalAlacarteRecords;
import static com.elifeindia.crm.adapters.generate_invoice_module.BouquetListBillShareAdapter.TotalBouquetsRecords;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter.activation_Date;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter.cafno;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter.expiry_Date;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter.ip;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter.mac;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter.noofMonth;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter.stbno;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter.vcno;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetPkgListAdapter.TotalInternetRecords;
import static com.elifeindia.crm.printersdk.BluetoothDeviceList.REQUEST_ENABLE_BT;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.getInvoiceModelInvoice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.calon.thermalprinter.CalonThermalPrinter;
import com.elifeindia.crm.BuildConfig;
import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.AdapterCallbackTextView;
import com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter;
import com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import pub.devrel.easypermissions.EasyPermissions;

public class BillShareActivity extends AppCompatActivity   {

    TextView txt_total, btn_next, btn_done, btn_send, txt_header, txt_cust_name, txt_accountno, invoicenumber, txt_mob_no, txt_invoice_date, txt_sub_id,
            txt_prev_bal, txt_total_amount, txt_paid_amnt, txt_balance, payment_Mode, collectBy, employeeNumber;
    RecyclerView rv_box_details;
    BluetoothAdapter mBluetoothAdapter;
    CalonThermalPrinter calOnPrinter = new CalonThermalPrinter();
    Button doneButton;
    CableBoxDetailsBillShareAdapter cableBoxDetailsBillShareAdapter;
    InternetBoxDetailsAdapter internetBoxDetailsAdapter;
    File imagePath;
    ImageView iv_all, iv_whatsappshare, iv_bill;
    NestedScrollView svprint;

    AdapterCallbackTextView adapterCallback;
    AdapterCallback adapterCall;

    String BillType = "", newBalance = "", prevBalance, subsAmount, receipt = "", invDate, CustName, CustMob, CustWhatsappNo, invno = "", accNo = "", name = "", subId = "", dateTime = "", totalAmnt = "", empMobNo = "", collectedBy = "", payMode = "", remainBal = "", footer = "";
    public String paidAmount = "";
    TextView discountTextView;
    public static final String[] BLUETOOTH_PERMISSIONS_S = {Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_share);

        txt_header = findViewById(R.id.txt_header);
        txt_cust_name = findViewById(R.id.txt_cust_name);
        txt_accountno = findViewById(R.id.txt_accountno);
        invoicenumber = findViewById(R.id.invoicenumber);
        txt_mob_no = findViewById(R.id.txt_mob_no);
        txt_invoice_date = findViewById(R.id.txt_invoice_date);
        txt_sub_id = findViewById(R.id.txt_sub_id);
        txt_total_amount = findViewById(R.id.txt_total_amount);
        txt_prev_bal = findViewById(R.id.txt_prev_bal);
        txt_paid_amnt = findViewById(R.id.txt_paid_amnt);
        txt_balance = findViewById(R.id.txt_balance);
        rv_box_details = findViewById(R.id.rv_box_details);
        discountTextView = findViewById(R.id.discount);
        btn_send = findViewById(R.id.btn_send);
        iv_all = findViewById(R.id.iv_all);
        iv_whatsappshare = findViewById(R.id.iv_whatsappshare);
        iv_bill = findViewById(R.id.iv_bill);
        svprint = findViewById(R.id.svprint);
        btn_done = findViewById(R.id.btn_done);
        btn_next = findViewById(R.id.btn_next);
        txt_total = findViewById(R.id.txt_total);
        payment_Mode = findViewById(R.id.payment_Mode);
        collectBy = findViewById(R.id.collectBy);
        employeeNumber = findViewById(R.id.employeeNumber);
          payment_Mode.setText(SharedPrefsData.getString(BillShareActivity.this, Constants.PaymentMode, Constants.PREF_NAME));




        try {
            if (!getInvoiceModelInvoice.getPaymentMaster().isEmpty() || getInvoiceModelInvoice.getPaymentMaster() != null) {

                for(int i=0;i<getInvoiceModelInvoice.getPaymentMaster().size();i++) {
                    if (getInvoiceModelInvoice.getPaymentMaster().get(i).getPayment_Id() == SharedPrefsData.getInt(BillShareActivity.this, Constants.PaymentId, Constants.PREF_NAME)) {
                        txt_paid_amnt.setText(getInvoiceModelInvoice.getPaymentMaster().get(i).getPaid_Amount().toString());
                        paidAmount = getInvoiceModelInvoice.getPaymentMaster().get(i).getPaid_Amount();
                        payment_Mode.setText(getInvoiceModelInvoice.getPaymentMaster().get(i).getPaymentType());
                        Log.d("TAG", "onCreatePaymentAnimesh: "+paidAmount);

                    }

                }
                discountTextView.setText(String.valueOf(getInvoiceModelInvoice.getDiscount()));
                collectBy.setText(SharedPrefsData.getString(BillShareActivity.this, Constants.EmployeeName, Constants.PREF_NAME));
                //  collectBy.setText(getInvoiceModelInvoice.getPaymentMaster().get(0).getEmployee_Name());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // employeeNumber.setText(SharedPrefsData.getString(BillShareActivity.this, Constants.EmployeeMob, Constants.PREF_NAME));
        //  CustMob = SharedPrefsData.getString(this, Constants.CustMob, Constants.PREF_NAME);
        CustMob = getInvoiceModelInvoice.getContact_No();
        CustWhatsappNo = SharedPrefsData.getString(this, Constants.WhatsupNo, Constants.PREF_NAME);
        //   CustWhatsappNo = getInvoiceModelInvoice.getContact_No();
        //   CustName = SharedPrefsData.getString(this, Constants.CustomerName, Constants.PREF_NAME);
        CustName = getInvoiceModelInvoice.getName();
        // accNo = SharedPrefsData.getString(this, Constants.AccNo, Constants.PREF_NAME);
        accNo = String.valueOf(getInvoiceModelInvoice.getAccount_No());
        //    invno = SharedPrefsData.getString(this, Constants.InvoiceNo, Constants.PREF_NAME);
        invno = getInvoiceModelInvoice.getInvoice_Number();
        // invDate = ViewUtils.changeDateFormat(SharedPrefsData.getString(this, Constants.InvoiceDate, Constants.PREF_NAME));
//        invDate = ViewUtils.changeDateFormat(getInvoiceModelInvoice.getInvoice_Date());
        //   subId = SharedPrefsData.getString(this, Constants.SubId, Constants.PREF_NAME);
        subId = getInvoiceModelInvoice.getSubscriber_ID();
        // subsAmount = SharedPrefsData.getString(this, Constants.TotalAmount, Constants.PREF_NAME);
        subsAmount = String.valueOf(getInvoiceModelInvoice.getAmount());
        //  prevBalance = SharedPrefsData.getString(this, Constants.PrevBal, Constants.PREF_NAME);
        prevBalance = String.valueOf(getInvoiceModelInvoice.getPrevious_Balance());
        remainBal = newBalance;
        //   BillType = SharedPrefsData.getString(this, Constants.TriplePlay, Constants.PREF_NAME);
        BillType = getInvoiceModelInvoice.getTriple_play();


        //SharedPrefsData.putString(this, Constants.ReceiptFlag, "true",Constants.PREF_NAME);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(BillShareActivity.this, CustomerListActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.putExtra("activity", "PaymentReceipt");
                startActivity(i);

                SharedPrefsData.putString(BillShareActivity.this, Constants.ReceiptFlag, "true", Constants.ReceiptFlag);

            }
        });

        findViewById(R.id.donebutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BillShareActivity.this, CustomerListActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.putExtra("activity", "PaymentReceipt");
                startActivity(i);
                SharedPrefsData.putString(BillShareActivity.this, Constants.ReceiptFlag, "true", Constants.ReceiptFlag);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BillShareActivity.this, CustomerListActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.putExtra("activity", "PaymentReceipt");
                startActivity(i);

                SharedPrefsData.putString(BillShareActivity.this, Constants.ReceiptFlag, "true", Constants.ReceiptFlag);
                // SharedPrefsData.putString(BillShareActivity.this, Constants.ReceiptFlag, "true",Constants.PREF_NAME);


            }
        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.Isabelline)));
                    Back.setTextColor((getResources().getColor(R.color.EnglishGreen)));
                }*/

               /* Bitmap bitmap = getBitmapFromView(svprint, svprint.getChildAt(0).getHeight(),
                        svprint.getChildAt(0).getWidth());
                if (null != bitmap) {
                    iv_bill.setImageBitmap(bitmap);
                }*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (!EasyPermissions.hasPermissions(BillShareActivity.this, BLUETOOTH_PERMISSIONS_S)) {
                        EasyPermissions.requestPermissions(BillShareActivity.this, "message", 5, BLUETOOTH_PERMISSIONS_S);
                    } else {
                        findBTprint();
                    }
                } else {
                    findBTprint();
                }


            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(PaymentReceiptActivity.this, MainActivity.class));

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + CustMob));
                intent.putExtra("sms_body", "Dear " + CustName + ",\n\nWe have received the amount of Rs " + paidAmount + "/- for " + BillType + " Bill and the balance is Rs " + newBalance + "/-" + "\n\n" + txt_header.getText().toString());
                startActivity(intent);
            }
        });

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            Dexter.withContext(this)
//                    .withPermission(WRITE_EXTERNAL_STORAGE)
//                    .withListener(new PermissionListener() {
//                        @Override
//                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                        }
//
//                        @Override
//                        public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//                            permissionDeniedResponse.getRequestedPermission();
//                        }
//
//                        @Override
//                        public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                        }
//                    })
//                    .check();
//        }

        iv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap receiptBitmap;
                receiptBitmap = takeScreenshot();
                saveBitmap(receiptBitmap);
                shareIt();
            }
        });

        iv_whatsappshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap receiptBitmap;
                receiptBitmap = takeScreenshot();
                saveBitmap(receiptBitmap);

                Log.d("TAG", "onClick2: "+isAppInstalled("com.whatsapp"));
                Log.d("TAG", "onClick1: "+isBusinessAppInstalled("com.whatsapp.w4b"));
                if (isBusinessAppInstalled("com.whatsapp.w4b") && isAppInstalled("com.whatsapp")) {
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BillShareActivity.this);
                    bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
                    LinearLayout option1 = bottomSheetDialog.findViewById(R.id.option1);
                    LinearLayout option2 = bottomSheetDialog.findViewById(R.id.option2);
                    bottomSheetDialog.show();
                    // Handle clicks on bottom sheet options
                    option1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle Option 1 click
                            openWhatsApp("com.whatsapp.w4b");
                        }
                    });

                    option2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle Option 2 click

                            openWhatsApp("com.whatsapp.w4b");
                        }
                    });


                } else if (isAppInstalled("com.whatsapp.w4b")) {
                    openWhatsApp("com.whatsapp.w4b");
                } else {
                    openWhatsApp("com.whatsapp");
                }

            }
        });


        txt_header.setText(SharedPrefsData.getString(this, Constants.HEADER, Constants.PREF_NAME));
        txt_cust_name.setText(CustName);
        txt_accountno.setText(accNo);
        invoicenumber.setText(invno);
        txt_mob_no.setText(CustMob);
        txt_invoice_date.setText(todayDateString());
        txt_sub_id.setText(subId);
        txt_total_amount.setText(subsAmount);
        txt_balance.setText(getInvoiceModelInvoice.getBalance() + "");
        try {
            txt_prev_bal.setText(prevBalance);
            String strTotal = String.valueOf(Float.parseFloat(subsAmount) + Float.parseFloat(prevBalance));
            txt_total.setText(strTotal);

            newBalance = String.valueOf(Float.parseFloat(strTotal) - Float.parseFloat(paidAmount));
            // newBalance = "10";
            txt_balance.setText(getInvoiceModelInvoice.getBalance() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }


        List<GetInvoiceModel.CableBoxwithSubscriptionDTO> cableBoxwithSubscriptionDTOS = getInvoiceModelInvoice.getCableBoxwithSubscription();
        List<GetInvoiceModel.InternetBoxwithSubscriptionDTO> internetBoxwithSubscriptionDTOS = getInvoiceModelInvoice.getInternetBoxwithSubscription();

        if (internetBoxwithSubscriptionDTOS.size() == 0) {
            TotalInternetRecords = "";
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_box_details.setLayoutManager(mLayoutManager);
            cableBoxDetailsBillShareAdapter = new CableBoxDetailsBillShareAdapter(BillShareActivity.this, cableBoxwithSubscriptionDTOS, internetBoxwithSubscriptionDTOS);
            rv_box_details.setAdapter(cableBoxDetailsBillShareAdapter);
        } else {

            TotalAlacarteRecords = "";
            TotalBouquetsRecords = "";
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_box_details.setLayoutManager(mLayoutManager);
            cableBoxDetailsBillShareAdapter = new CableBoxDetailsBillShareAdapter(BillShareActivity.this, internetBoxwithSubscriptionDTOS);
            rv_box_details.setAdapter(cableBoxDetailsBillShareAdapter);
        }


    }


    public Bitmap takeScreenshot() {
        NestedScrollView rootView = findViewById(R.id.svprint);
        rootView.setDrawingCacheEnabled(true);
        return getBitmapFromView(rootView, rootView.getChildAt(0).getHeight(), rootView.getChildAt(0).getWidth());
    }

    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }


    public void saveBitmap(Bitmap bitmap) {
        File folder = new File(getExternalFilesDir(null) +
                File.separator + "eLife CRM Receipts");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            imagePath = new File(getExternalFilesDir(null) + "/eLife CRM Receipts" + "/eLife_crm_invoice_" + dateTime + ".jpg");
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(imagePath);
                Toast.makeText(this, imagePath.toString(), Toast.LENGTH_SHORT).show();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                Log.e("GREC", e.getMessage(), e);
            } catch (IOException e) {
                Log.e("GREC", e.getMessage(), e);
            }
        } else {
            Toast.makeText(this, "Failure in making directory", Toast.LENGTH_SHORT).show();
        }


    }

    private void shareIt() {
        Uri uri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
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
    private boolean isAppInstalled(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    private boolean isBusinessAppInstalled(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void openWhatsApp(String s) {
        /*String smsNumber = CustWhatsappNo; // E164 format without '+' sign
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "*Payment Received*\n" +
                        "*Customer Details*\n" +
                        "Name: "+CustName+"\n" +
                        "Account No: "+accNo+"\n" +
                        "Subscriber ID: "+subId+"\n" +
                        "Bill Type: "+BillType+"\n" +
                        "Mobile No: "+CustMob+"\n" +
                        "\n" +
                        "*Payment Details*\n" +
                        "------------------------\n" +
                        "Total Amount: "+String.valueOf(Float.parseFloat(subsAmount)+Float.parseFloat(prevBalance))+"\n" +
                        "Paid Amount: "+paidAmount+"\n" +
                        "Discount: 0\n"+
                        "Remaining Amount: "+newBalance+"\n" +
                        "------------------------\n" +
                        "*"+txt_header.getText().toString().trim()
                        +"*\n" +
                        footer);

        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Error/n" , Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(sendIntent);*/


        String customerPhoneNumber = CustWhatsappNo;
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setPackage(s);


        String message = "*Payment Received*\n" +
                "*Customer Details*\n" +
                "Name: " + CustName + "\n" +
                "Account No: " + accNo + "\n" +
                "Subscriber ID: " + subId + "\n" +
                "Bill Type: " + BillType + "\n" +
                "Mobile No: " + CustMob + "\n" +
                "\n" +
                "*Payment Details*\n" +
                "------------------------\n" +
                "Total Amount: " + String.valueOf(Float.parseFloat(subsAmount) + Float.parseFloat(prevBalance)) + "\n" +
                "Paid Amount: " + paidAmount + "\n" +
                "Discount:" + String.valueOf(getInvoiceModelInvoice.getDiscount()) + "\n" +
                "Remaining Amount: " + newBalance + "\n" +
                "------------------------\n" +
                "*" + txt_header.getText().toString().trim()
                + "*\n" +
                footer;


        String url = "https://api.whatsapp.com/send?phone=" + customerPhoneNumber + "&text=" + message;
        sendIntent.setData(Uri.parse(url));

        if (sendIntent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Error/n", Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(sendIntent);


    }

    @SuppressLint("MissingPermission")
    public void findBTprint() {
        BluetoothManager bluetoothManager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            bluetoothManager = getSystemService(BluetoothManager.class);
        }
        try {

            this.mBluetoothAdapter = bluetoothManager.getAdapter();
            if (!this.mBluetoothAdapter.isEnabled()) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    EasyPermissions.requestPermissions(BillShareActivity.this, "message", 5, BLUETOOTH_PERMISSIONS_S);

                    return;
                }
                this.mBluetoothAdapter.enable();
            }
            Set e = mBluetoothAdapter.getBondedDevices();
            BluetoothDevice device1;
            Log.d("TAG", "findBTprint: " + e.toString());
            if (e.size() > 0) {
                Iterator var4 = e.iterator();

                while (var4.hasNext()) {
                    device1 = (BluetoothDevice) var4.next();
                    String ID = device1.toString();
                    try {

                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                    try {
                        calOnPrinter.openBT(ID);
                        if (BillType.equals("Cable")) {

                            receipt =
                                    txt_header.getText().toString().trim() + "\n" +
                                            "-------------------------------\n" +
                                            "\tReceipt\n" +
                                            "-------------------------------\n" +
                                            "Name          : " + CustName + "\n" +
                                            "Ac No         : " + accNo + "\n" +
                                            "Subs ID       : " + subId + "\n" +
                                            "Inv No        : " + invno + "\n" +
                                            "Mobile No     : " + CustMob + "\n" +
                                            "Inv Date      : " + todayDateString() + "\n" +
                                            "\n" +
                                            "BOX DETAILS\n" +
                                            "-------------------------------\n" +

                                            "CAF NO.       : " + cafno + "\n" +
                                            "STB NO.       : " + stbno + "\n" +
                                            "VC NO.        : " + vcno + "\n" +
                                            "NO OF MONTHS  : " + noofMonth + "\n" +
                                            "RENEW DATE    : " + activation_Date + "\n" +
                                            "EXPIRY DATE   : " + expiry_Date + "\n" +
                                            "\n" +

                                            "SUBSCRIPTION DETAILS\n" +
                                            "-------------------------------\n" +
                                            TotalBouquetsRecords + "    " + TotalAlacarteRecords + "\n" +


                                            "-------------------------------\n" +
                                            "PAYMENT DETAILS\n" +
                                            "-------------------------------\n" +
                                            "Total Amount  : " + String.valueOf(Float.parseFloat(subsAmount) + Float.parseFloat(prevBalance)) + "\n" +
                                            "Paid Amount   : " + paidAmount + "\n" +
                                            "Discount      : " + getInvoiceModelInvoice.getDiscount() + "\n" +
                                            "Remaining Amt : " + newBalance + "\n" +
                                            "\n" +
                                            "-------------------------------\n" +
                                            "\tTHANK YOU\n" +
                                            "-------------------------------\n \n" +
                                            footer + "\n";

                        } else if (BillType.equals("Internet")) {
                            receipt =
                                    txt_header.getText().toString().trim() + "\n" +
                                            "-------------------------------\n" +
                                            "\tReceipt\n" +
                                            "-------------------------------\n" +
                                            "Name          : " + CustName + "\n" +
                                            "Ac No         : " + accNo + "\n" +
                                            "Subs ID       : " + subId + "\n" +
                                            "Inv No        : " + invno + "\n" +
                                            "Mobile No     : " + CustMob + "\n" +
                                            "Inv Date      : " + todayDateString() + "\n" +
                                            "\n" +
                                            "BOX DETAILS\n" +
                                            "-------------------------------\n" +

                                            "IP            : " + ip + "\n" +
                                            "MAC           : " + mac + "\n" +
                                            "NO OF MONTHS  : " + noofMonth + "\n" +
                                            "RENEW DATE    : " + activation_Date + "\n" +
                                            "EXPIRY DATE   : " + expiry_Date + "\n" +
                                            "\n" +

                                            "SUBSCRIPTION DETAILS\n" +
                                            "-------------------------------\n" +

                                            TotalInternetRecords + "\n" +

                                            "-------------------------------\n" +
                                            "PAYMENT DETAILS\n" +
                                            "-------------------------------\n" +
                                            "Total Amount  : " + String.valueOf(Float.parseFloat(subsAmount) + Float.parseFloat(prevBalance)) + "\n" +
                                            "Paid Amount   : " + paidAmount + "\n" +
                                            "Discount      : 0\n" +
                                            "Remaining Amt : " + newBalance + "\n" +
                                            "\n" +
                                            "-------------------------------\n" +
                                            "\tTHANK YOU\n" +
                                            "-------------------------------\n \n" +
                                            footer + "\n";

                        }

                        boolean b1 = calOnPrinter.printData(receipt);

//                        Bitmap mBitmap = ((BitmapDrawable) iv_bill.getDrawable()).getBitmap();
//                        int nMode = 0;
//                        int nPaperWidth = 384 ;
//
//                        byte[] data = PrintPicture.POS_PrintBMP(mBitmap, nPaperWidth, nMode);
//                        boolean b1 = calOnPrinter.printData(data);


                    } catch (Exception ex) {
                        ex.getMessage();
                        boolean b1 = calOnPrinter.printData(receipt);
                        Toast.makeText(BillShareActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();

                    }

                }
            } else {
                Toast.makeText(BillShareActivity.this, "Please Pair Device", Toast.LENGTH_SHORT).show();
                Log.i("receipt3", receipt);
            }
        } catch (NullPointerException ex1) {
            //  ex1.getMessage();
        }

        Log.i("receipt2", receipt);

    }


    public void newPrintBT() {
        BluetoothManager bluetoothManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            bluetoothManager = getSystemService(BluetoothManager.class);
        }
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(BillShareActivity.this, CustomerListActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra("activity", "PaymentReceipt");
        startActivity(i);

        SharedPrefsData.putString(this, Constants.ReceiptFlag, "true", Constants.ReceiptFlag);
        //SharedPrefsData.putString(this, Constants.ReceiptFlag, "true",Constants.PREF_NAME);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private String todayDateString() {

        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());

    }
}