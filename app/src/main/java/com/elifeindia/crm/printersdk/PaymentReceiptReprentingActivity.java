package com.elifeindia.crm.printersdk;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.calon.thermalprinter.CalonThermalPrinter;
import com.elifeindia.crm.BuildConfig;
import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter;
import com.elifeindia.crm.adapters.generate_invoice_module.InternetBoxDetailsAdapter;
import com.elifeindia.crm.contract.activities.CustomerDetailsContract;
import com.elifeindia.crm.contract.activities.PaymentReceiptContract;
import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.model.HeaderModel;
import com.elifeindia.crm.model.PaymentReciept;
import com.elifeindia.crm.presenter.activities.CustomerDetailsPresenter;
import com.elifeindia.crm.presenter.activities.PaymentReceiptPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.activities.BillShareActivity;
import com.elifeindia.crm.view.activities.CustomerListActivity;
import com.elifeindia.crm.view.activities.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.printer.command.EscCommand;
import com.printer.command.LabelCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.elifeindia.crm.adapters.generate_invoice_module.AlacarteListBillShareAdapter.TotalAlacarteRecords;
import static com.elifeindia.crm.adapters.generate_invoice_module.BouquetListBillShareAdapter.TotalBouquetsRecords;
import static com.elifeindia.crm.adapters.generate_invoice_module.InternetPkgListAdapter.TotalInternetRecords;
import static com.elifeindia.crm.printersdk.Constant.MESSAGE_UPDATE_PARAMETER;
import static com.elifeindia.crm.view.activities.BillShareActivity.BLUETOOTH_PERMISSIONS_S;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.getInvoiceModelInvoice;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.txt_activation_date;

import pub.devrel.easypermissions.EasyPermissions;

public class PaymentReceiptReprentingActivity extends AppCompatActivity implements PaymentReceiptContract.View, CustomerDetailsContract.View {
    PaymentReceiptContract.Presenter presenter;
    CustomerDetailsContract.Presenter custommerPresenter;
    TextView txt_subid, txt_accountno, txt_header, custmername_pay, billdate_pay, invoicenumber_pay, txt_rec_time, txt_prev_bal, paidamount_pay;
    TextView btn_send, txt_discount, balance_pay, paymentmode_text, txt_collected_by, txt_emp_mob_no;
    ImageView iv_all, iv_whatsappshare;
    RecyclerView rv_box_details;
    TextView btn_done, btn_next, txt_bill_type_rep;
    String invno, accNo, name, subId, dateTime, totalAmnt, empMobNo, collectedBy, payMode, remainBal, paidAmnt, footer, discount;
    public static String invoiceNumber;
    File imagePath;
    private int id = 0;
    private ThreadPool threadPool;
    private static final int CONN_STATE_DISCONN = 0x007;
    private static final int CONN_PRINTER = 0x12;
    private static final int PRINTER_COMMAND_ERROR = 0x008;
    private static final int CONN_MOST_DEVICES = 0x11;
    ListView lv_footer;
    TextView mobileTv, addressTv;
    String activationdate, billtype, noOfMonths, expiryDate;


    CableBoxDetailsBillShareAdapter cableBoxDetailsBillShareAdapter;
    InternetBoxDetailsAdapter internetBoxDetailsAdapter;
    BluetoothAdapter mBluetoothAdapter;
    CalonThermalPrinter calOnPrinter = new CalonThermalPrinter();

    String receipt;
    String CustMob, WhatsupNo, InvType, WhatsappNo, ContactNo;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt_reprinting);

        btn_send = findViewById(R.id.btn_send);
        txt_accountno = findViewById(R.id.txt_accountno);
        txt_subid = findViewById(R.id.txt_subid);
        txt_header = findViewById(R.id.txt_header);
        custmername_pay = findViewById(R.id.custmername_pay);
        invoicenumber_pay = findViewById(R.id.invoicenumber_pay);
        billdate_pay = findViewById(R.id.billdate_pay);
        txt_rec_time = findViewById(R.id.txt_rec_time);
        mobileTv = findViewById(R.id.customer_mobile);
        addressTv = findViewById(R.id.customer_address);
        txt_prev_bal = findViewById(R.id.txt_prev_bal);
        rv_box_details = findViewById(R.id.rv_box_details);
        paidamount_pay = findViewById(R.id.paidamount_pay);
        txt_discount = findViewById(R.id.txt_discount);
        balance_pay = findViewById(R.id.balance_pay);
        paymentmode_text = findViewById(R.id.paymentmode_text);
        txt_collected_by = findViewById(R.id.txt_collected_by);
        txt_emp_mob_no = findViewById(R.id.txt_emp_mob_no);

        txt_bill_type_rep = findViewById(R.id.txt_bill_type_rep);

        btn_done = findViewById(R.id.btn_done);
        iv_all = findViewById(R.id.iv_all);
        iv_whatsappshare = findViewById(R.id.iv_whatsappshare);
        btn_next = findViewById(R.id.btn_next);
        lv_footer = findViewById(R.id.lv_footer);

//        WhatsappNo = SharedPrefsData.getString(PaymentReceiptReprentingActivity.this, Constants.WhatsupNo, Constants.PREF_NAME);
        CustMob = SharedPrefsData.getString(PaymentReceiptReprentingActivity.this, Constants.CustMob, Constants.PREF_NAME);
        InvType = getIntent().getStringExtra("BillType");
        //WhatsappNo = getIntent().getStringExtra("WhatsappNo");
        ContactNo = getIntent().getStringExtra("ContactNo");


        try {
            if (!getInvoiceModelInvoice.getPaymentMaster().isEmpty() || getInvoiceModelInvoice.getPaymentMaster() != null) {

                for (int i = 0; i < getInvoiceModelInvoice.getPaymentMaster().size(); i++) {
                    if (getInvoiceModelInvoice.getPaymentMaster().get(i).getPayment_Id() == SharedPrefsData.getInt(PaymentReceiptReprentingActivity.this, Constants.PaymentId, Constants.PREF_NAME)) {
//                        txt_paid_amnt.setText(getInvoiceModelInvoice.getPaymentMaster().get(i).getPaid_Amount());
                        paidAmnt = getInvoiceModelInvoice.getPaymentMaster().get(i).getPaid_Amount();
                        // payment_Mode.setText(getInvoiceModelInvoice.getPaymentMaster().get(i).getPaymentType());

                    }

                }
//                discountTextView.setText(String.valueOf(getInvoiceModelInvoice.getDiscount()));
//                collectBy.setText(SharedPrefsData.getString(BillShareActivity.this, Constants.EmployeeName, Constants.PREF_NAME));
                //  collectBy.setText(getInvoiceModelInvoice.getPaymentMaster().get(0).getEmployee_Name());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PaymentReceiptReprentingActivity.this, CustomerListActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.putExtra("activity", "PaymentReceipt");
                startActivity(i);
            }
        });


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(PaymentReceiptReprentingActivity.this, MainActivity.class));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + ContactNo));
                intent.putExtra("sms_body", "Dear " + name + ",\n\nWe have received the amount of Rs " + paidAmnt + "/- for cable bill and the balance is Rs " + remainBal + "/-" + "\n\n" + txt_header.getText().toString());
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
//                shareItOnWhatsApp();

                Log.d("TAG", "onClick2: "+isAppInstalled("com.whatsapp"));
                Log.d("TAG", "onClick1: "+isBusinessAppInstalled("com.whatsapp.w4b"));
                if (isBusinessAppInstalled("com.whatsapp.w4b") && isAppInstalled("com.whatsapp")) {
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PaymentReceiptReprentingActivity.this);
                    bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
                    LinearLayout option1 = bottomSheetDialog.findViewById(R.id.option1);
                    LinearLayout option2 = bottomSheetDialog.findViewById(R.id.option2);
                    bottomSheetDialog.show();
                    // Handle clicks on bottom sheet options
                    option1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle Option 1 click
                            shareItOnWhatsApp("com.whatsapp");
                        }
                    });

                    option2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle Option 2 click
                            shareItOnWhatsApp("com.whatsapp.w4b");
                        }
                    });


                } else if (isAppInstalled("com.whatsapp.w4b")) {
                    shareItOnWhatsApp("com.whatsapp.w4b");
                } else {
                    shareItOnWhatsApp("com.whatsapp");
                }

            }
        });

        presenter = new PaymentReceiptPresenter(this);
        custommerPresenter = new CustomerDetailsPresenter(this);
        presenter.start();
        custommerPresenter.start();
        String pId = SharedPrefsData.getString(this, Constants.PaymentId, Constants.PREF_NAME);
        String customerID = SharedPrefsData.getString(this, Constants.CustomerID, Constants.PREF_NAME);
        presenter.loadPaymentReceipt(this, pId);
        custommerPresenter.loadApi(this, customerID);

        String compId = SharedPrefsData.getString(this, Constants.CompanyID, Constants.PREF_NAME);

        presenter.loadHeader(this, compId);
        presenter.loadFooter(this, compId);


        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (!EasyPermissions.hasPermissions(PaymentReceiptReprentingActivity.this, BLUETOOTH_PERMISSIONS_S)) {
                        EasyPermissions.requestPermissions(PaymentReceiptReprentingActivity.this, "message", 5, BLUETOOTH_PERMISSIONS_S);
                    } else {
                        findBTprint();
                    }
                } else {
                    findBTprint();
                }

                //findBTprint();

                /*if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] == null ||
                        !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState() )
                {
                    //startActivityForResult( new Intent( PaymentReceiptReprentingActivity.this, BluetoothDeviceList.class ), Constant.BLUETOOTH_REQUEST_CODE );

                    Toast.makeText(PaymentReceiptReprentingActivity.this,  getString( R.string.str_cann_printer ), Toast.LENGTH_SHORT).show();
                    return;
                }
                threadPool = ThreadPool.getInstantiation();
                threadPool.addTask( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getCurrentPrinterCommand() == PrinterCommand.ESC )
                        {
                            sendReceiptText();
                        } else {
                            mHandler.obtainMessage( PRINTER_COMMAND_ERROR ).sendToTarget();
                        }
                    }
                } );*/
            }

        });

        findViewById(R.id.btn_conn).setOnClickListener(view -> startActivityForResult(new Intent(PaymentReceiptReprentingActivity.this, BluetoothDeviceList.class), Constant.BLUETOOTH_REQUEST_CODE));


        findViewById(R.id.iv_back).setOnClickListener(view -> onBackPressed());


        List<GetInvoiceModel.CableBoxwithSubscriptionDTO> cableBoxwithSubscriptionDTOS = getInvoiceModelInvoice.getCableBoxwithSubscription();
        List<GetInvoiceModel.InternetBoxwithSubscriptionDTO> internetBoxwithSubscriptionDTOS = getInvoiceModelInvoice.getInternetBoxwithSubscription();

        if (internetBoxwithSubscriptionDTOS.size() == 0) {
            TotalInternetRecords = "";
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_box_details.setLayoutManager(mLayoutManager);
            cableBoxDetailsBillShareAdapter = new CableBoxDetailsBillShareAdapter(PaymentReceiptReprentingActivity.this, cableBoxwithSubscriptionDTOS, internetBoxwithSubscriptionDTOS);
            rv_box_details.setAdapter(cableBoxDetailsBillShareAdapter);
            if(getInvoiceModelInvoice.getCableBoxwithSubscription().get(0).getCableBox().getActivation_Date()!=null){
                activationdate = getInvoiceModelInvoice.getCableBoxwithSubscription().get(0).getCableBox().getActivation_Date().toString();
            }
            billtype = getInvoiceModelInvoice.getCableBoxwithSubscription().get(0).getCableBox().getBill_Type().toString();
            noOfMonths = String.valueOf(getInvoiceModelInvoice.getCableBoxwithSubscription().get(0).getCableBox().getNoofMonth());
            if(getInvoiceModelInvoice.getCableBoxwithSubscription().get(0).getCableBox().getExpiry_Date()!=null){
                expiryDate = getInvoiceModelInvoice.getCableBoxwithSubscription().get(0).getCableBox().getExpiry_Date().toString();
            }

        } else {
            if(getInvoiceModelInvoice.getInternetBoxwithSubscription().get(0).getInternetBox().getActivation_Date()!=null){
                activationdate = getInvoiceModelInvoice.getInternetBoxwithSubscription().get(0).getInternetBox().getActivation_Date().toString();

            }
            billtype = getInvoiceModelInvoice.getInternetBoxwithSubscription().get(0).getInternetBox().getBill_Type().toString();
            noOfMonths = String.valueOf(getInvoiceModelInvoice.getInternetBoxwithSubscription().get(0).getInternetBox().getNoofMonth());
            if(getInvoiceModelInvoice.getInternetBoxwithSubscription().get(0).getInternetBox().getExpiry_Date()!=null){
                expiryDate = getInvoiceModelInvoice.getInternetBoxwithSubscription().get(0).getInternetBox().getExpiry_Date().toString();

            }

            TotalAlacarteRecords = "";
            TotalBouquetsRecords = "";
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_box_details.setLayoutManager(mLayoutManager);
            cableBoxDetailsBillShareAdapter = new CableBoxDetailsBillShareAdapter(PaymentReceiptReprentingActivity.this, internetBoxwithSubscriptionDTOS);
            rv_box_details.setAdapter(cableBoxDetailsBillShareAdapter);
        }
    }

    private void findBTprint() {

        //Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();

        try {
            this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!this.mBluetoothAdapter.isEnabled()) {
                this.mBluetoothAdapter.enable();
            }

            Set e = this.mBluetoothAdapter.getBondedDevices();
            BluetoothDevice device1;
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

                        /*receipt =

                                txt_header.getText().toString().trim() +"\n" +
                                        "*Customer Details*\n" +
                                        "------------------------\n" +
                                        "Name: "+name+"\n" +
                                        "Account No: "+accNo+"\n" +
                                        "Subscriber ID: "+subId+"\n" +
                                        //"Bill Type: "+BillType+"\n" +
                                        //"Mobile No: "+CustMob+"\n" +
                                        "\n" +
                                        "*Payment Details*\n" +
                                        "------------------------\n" +
                                        "Total Amount: "+totalAmnt+"\n" +
                                        "Paid Amount: "+paidAmnt+"\n" +
                                        "Discount: 0\n"+
                                        "Remaining Amount: "+remainBal+"\n" +
                                        "\n" +
                                        "------------------------\n" +
                                        "Payment Mode: "+payMode+"\n" +
                                        "Collected By: "+collectedBy+"\n" +
                                        "Mobile Number: "+empMobNo+"\n" +
                                        "------------------------\n" +
                                        footer;*/

                        receipt =
                                txt_header.getText().toString() + "\n" +

                                        "-------------------------------" + "\n" +
                                        "            Receipt        " + "\n" +
                                        "-------------------------------" + "\n" +

                                        "Name      : " + name + "\n" +
                                        "Ac No     : " + accNo + "\n" +
                                        "Sub Id    : " + subId + "\n" +
                                        "Bill Date : " + billdate_pay.getText().toString() + "\n" +
                                        "Bill Type : " + InvType + "\n" +
                                        "Cust No   : " + CustMob + "\n" +
                                        "Rpt No    : " + invoicenumber_pay.getText().toString() + "\n" +

                                        "------------------------------" + "\n" +
                                        "Total Amt : " + totalAmnt + "\n" +
                                        "Paid Amt  : " + paidAmnt + "\n" +
                                        "Discount  :" + discount + "\n" +
                                        "Remaining : " + remainBal + "\n" +

                                        "-------------------------------" + "\n" +
                                        "Pay Mode  : " + payMode + "\n" +
                                        "Collected : " + collectedBy + "\n" +
                                        "Emp No    : " + empMobNo + "\n" +

                                        "-------------------------------" + "\n" +
                                        "          THANK YOU            " + "\n" +
                                        "-------------------------------" + "\n" +
                                        "                                " + "\n" +
                                        "                                " + "\n"

                        ;


                        boolean b1 = calOnPrinter.printData(receipt);

                    } catch (Exception ex) {
                        ex.getMessage();
                        boolean b1 = calOnPrinter.printData(receipt);
                        Toast.makeText(PaymentReceiptReprentingActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();

                    }

                }
            } else {
                Toast.makeText(PaymentReceiptReprentingActivity.this, "Please Pair Device", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException ex1) {
            ex1.getMessage();
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
    private Bitmap captureView(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    private void shareScreenshot(File screenshotFile) {


        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        Uri screenshotUri = Uri.fromFile(screenshotFile);
        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        startActivity(Intent.createChooser(shareIntent, "Share screenshot using"));
    }

    private File saveScreenshot(Bitmap bitmap) {
        File screenshotFile = new File(getExternalFilesDir(null), "screenshot.png");
        try {
            FileOutputStream outputStream = new FileOutputStream(screenshotFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotFile;

    }


    private void shareItOnWhatsApp(String packageName) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Uri uri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                BuildConfig.APPLICATION_ID + ".provider", imagePath);
        String customerPhoneNumber = WhatsappNo;
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setPackage(packageName);

        String message =
                "*Payment Receipt*\n" +

                        "*Customer Details*\n" +
                        "Name: " + custmername_pay.getText().toString() + "\n" +
                        "Account Number: " + txt_accountno.getText().toString() + "\n" +
                        "Subscriber ID: " + txt_subid.getText().toString() + "\n" +
                        "Bill Date: " + billdate_pay.getText().toString() + "\n" +
                        "Receipt Number: " + invoicenumber_pay.getText().toString() + "\n" +
                        "\n" +
                        "\n" +
                        "*Subscription Details*\n" +
                        "------------------------\n" +
                        "Activation Date : " + ViewUtils.changeDateTimeFormat(activationdate) + "\n" +
                        "Bill Type: " + billtype + "\n" +
                        "No of Months : " + noOfMonths + "\n" +
                        "Inactive Date: " + ViewUtils.changeDateTimeFormat(expiryDate) + "\n" +
                        "\n" +
                        "\n" +

                        "*Payment Details*\n" +
                        "------------------------\n" +
                        "Total Amount: " + txt_prev_bal.getText().toString() + "\n" +
                        "Paid Amount: " + paidamount_pay.getText().toString() + "\n" +
                        "Discount: " + discount + "\n" +
                        "Remaining Amount: " + balance_pay.getText().toString() + "\n" +
                        "Payment Mode: " + paymentmode_text.getText().toString() + "\n" +
                        "Collected By: " + txt_collected_by.getText().toString() + "\n" +
                        "Mobile Number: " + txt_emp_mob_no.getText().toString() + "\n" +


                        "------------------------\n" +
                        "" + txt_header.getText().toString().trim();

//        Log.d("TAG", "shareItOnWhatsApp1: "+customerPhoneNumber.toString());
        String url = "https://api.whatsapp.com/send?phone=" + customerPhoneNumber + "&text=" + message;
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.setData(Uri.parse(url));

        if (sendIntent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Please Install Whatsapp", Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(sendIntent);



        /*String custWhatsAppNo = SharedPrefsData.getString(PaymentReceiptReprentingActivity.this, Constants.WhatsupNo, Constants.PREF_NAME);

        String smsNumber = "91" + custWhatsAppNo; // E164 format without '+' sign
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "*Payment Receipt*\n" +
                        "*Customer Details*\n" +
                        "Name: " + custmername_pay.getText().toString() + "\n" +
                        "Account Number: " + txt_accountno.getText().toString() + "\n" +
                        "Subscriber ID: " + txt_subid.getText().toString() + "\n" +
                        "Bill Date: " + billdate_pay.getText().toString() + "\n" +
                        "Receipt Number: " + invoicenumber_pay.getText().toString() + "\n" +
                        "\n" +
                        "*Payment Details*\n" +
                        "------------------------\n" +
                        "Total Amount: " + txt_prev_bal.getText().toString() + "\n" +
                        "Paid Amount: " + paidamount_pay.getText().toString() + "\n" +
                        "Discount: " + txt_discount.getText().toString() + "\n" +
                        "Remaining Amount: " + balance_pay.getText().toString() + "\n" +
                        "Payment Mode: " + paymentmode_text.getText().toString() + "\n" +
                        "Collected By: " + txt_collected_by.getText().toString() + "\n" +
                        "Mobile Number: " + txt_emp_mob_no.getText().toString() + "\n" +

                        "------------------------\n" +
                        "" + txt_header.getText().toString().trim()
        );

        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Error/n", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(sendIntent);*/


//        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
//        whatsappIntent.setType("image/*");
//        whatsappIntent.setPackage("com.whatsapp");
//        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
//        try {
//            startActivity(whatsappIntent);
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
//        }

        /*String smsNumber = "918999567375"; // E164 format without '+' sign
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("image/*");
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Payment Receipt");
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Error/n", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(sendIntent);*/
    }

    public Bitmap takeScreenshot() {
        ScrollView rootView = findViewById(R.id.svprint);
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
                File.separator + "ECRM Receipts");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            imagePath = new File(getExternalFilesDir(null) + "/ECRM Receipts" + "/ecrm_invoice_" + dateTime + ".jpg");
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
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Uri uri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                BuildConfig.APPLICATION_ID + ".provider", imagePath);
        Uri data = Uri.parse(imagePath.toString());


        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "Payment Receipt";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Payment Receipt");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            Intent intent = new Intent(Intent.createChooser(sharingIntent, "Share via"));
            intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            startActivity(intent);
            //   startActivity(sharingIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {
    }

    @Override
    public void showResult(CustomerData customerData) {
        WhatsappNo = customerData.getWhatsupNo();
    }

    @Override
    public void showCableBoxList(CustemersCableBoxData custemersCableBoxData) {

    }

    @Override
    public void showInernetBoxList(CustomersInternetBoxData customersInternetBoxData) {

    }

    @Override
    public void showInvoice(GetInvoiceModel getInvoiceModel) {

    }


    @Override
    public void showReceipt(PaymentReciept paymentReciept) {
        Log.d("TAG", "showReceiptAnimesh: " + paymentReciept.toString());
        name = paymentReciept.getName();
        accNo = paymentReciept.getAccountNo().toString();
        subId = paymentReciept.getSubscriberID();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US);
        dateTime = ViewUtils.changeDateTimeFormat(paymentReciept.getPaymentDate());
        //Date date = new Date();
        //dateTime = sdf1.format(date);
        totalAmnt = paymentReciept.getTotalAmount().toString();
        paidAmnt = paymentReciept.getPaidAmount().toString();
        txt_discount.setText(paymentReciept.getDiscount());
        discount = paymentReciept.getDiscount();
        remainBal = paymentReciept.getBalance().toString();
        payMode = paymentReciept.getPaymentType();
        collectedBy = paymentReciept.getEmployee_Name();
        empMobNo = paymentReciept.getEmployee_Contact();
        mobileTv.setText(paymentReciept.getContact_No());
        addressTv.setText(paymentReciept.getAddress());

        custmername_pay.setText(name);
        txt_accountno.setText(accNo);
        txt_subid.setText(subId);
        invoicenumber_pay.setText(paymentReciept.getInvoiceNumber());

        SharedPrefsData.putString(this, Constants.ReceiptFlag, "true", Constants.ReceiptFlag);


        billdate_pay.setText(dateTime);
        txt_prev_bal.setText(totalAmnt);
        paidamount_pay.setText(paidAmnt);
        balance_pay.setText(remainBal);
        paymentmode_text.setText(payMode);
        txt_collected_by.setText(collectedBy);
        txt_emp_mob_no.setText(empMobNo);

        txt_bill_type_rep.setText(InvType);

        paymentReciept.getAreaCustomerID().toString();
        paymentReciept.getAreaName().toString();

        invno = paymentReciept.getInvoiceNumber();
        txt_rec_time.setText(paymentReciept.getPaymentDate());
        paymentReciept.getSoDoWo();


        // txt_network_name.setText(paymentReciept.getTitle());
        paymentReciept.getTotalAmount().toString();
        paymentReciept.getTriplePlayID().toString();


    }

    @Override
    public void showHeader(HeaderModel headerModel) {
        try {
            txt_header.setText(headerModel.getHeader().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFooter(FooterModel footerModel) {

        List<FooterModel.HeaderfooterDTO> footerModels = footerModel.getHeaderfooter();
        footer = footerModel.getHeaderfooter().get(0).getDisplay_Content();
        ArrayList<String> content = new ArrayList<String>();
        for (int i = 0; i < footerModels.size(); i++) {
            content.add(footerModels.get(i).getDisplay_Content());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, content);
        lv_footer.setAdapter(arrayAdapter);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(PaymentReceiptReprentingActivity.this, CustomerListActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra("activity", "PaymentReceipt");
        startActivity(i);
    }

    void sendReceiptImage() {
        Bitmap receiptBitmap;

        receiptBitmap = takeScreenshot();

        EscCommand esc = new EscCommand();
        esc.addInitializePrinter();

        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);

        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);

        esc.addRastBitImage(receiptBitmap, 380, 0);

        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);

        // esc.addText( "Thank You!\r\n" );


        esc.addGeneratePlus(LabelCommand.FOOT.F5, (byte) 255, (byte) 255);
        esc.addPrintAndFeedLines((byte) 1);

        byte[] bytes = {29, 114, 1};
        esc.addUserCommand(bytes);
        Vector<Byte> datas = esc.getCommand();

        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);
    }

    void sendReceiptText() {
        EscCommand esc = new EscCommand();
        esc.addInitializePrinter();


        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText(txt_header.getText().toString() + "\n");

        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText("--------------------------------\n");

        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        esc.addSelectPrintModes(EscCommand.FONT.FONTB, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText("Receipt\n");

        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText("--------------------------------\n");
        esc.addText("Name    : " + name + "\n");
        esc.addText("Ac No   : " + accNo + "\n");
        esc.addText("Sub Id  : " + subId + "\n");
        esc.addText("Date    : " + dateTime + "\n");
        esc.addText("Rpt No  : " + invno + "\n");
        esc.addText("--------------------------------");
        esc.addText("TotalAmt  : " + totalAmnt + "\n");
        esc.addText("Paid Amt  : " + paidAmnt + "\n");
        esc.addText("Discount  : " + "0" + "\n");
        esc.addText("Remaining : " + remainBal + "\n");
        esc.addText("--------------------------------");
        esc.addText("PayMode   : " + payMode + "\n");
        esc.addText("Collected : " + collectedBy + "\n");
        esc.addText("Emp No    : " + empMobNo + "\n");
        esc.addText("--------------------------------");
//        esc.addSelectJustification( EscCommand.JUSTIFICATION.CENTER );
//        esc.addSelectPrintModes( EscCommand.FONT.FONTB, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF );
//        esc.addText( "Thank You(FONTB, OFF, OFF, OFF, OFF)\n" );

        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText("Thank You\n");

//        esc.addSelectJustification( EscCommand.JUSTIFICATION.CENTER );
//        esc.addSelectPrintModes( EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF );
//        esc.addText( "Thank You(FONTA, OFF, ON, OFF, OFF)\n" );
//
//        esc.addSelectJustification( EscCommand.JUSTIFICATION.CENTER );
//        esc.addSelectPrintModes( EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF );
//        esc.addText( "Thank You(FONTA, OFF, OFF, ON, OFF)\n" );
//
//        esc.addSelectJustification( EscCommand.JUSTIFICATION.CENTER );
//        esc.addSelectPrintModes( EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON );
//        esc.addText( "Thank You(FONTA, OFF, OFF, OFF, ON)\n" );

        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        esc.addSelectPrintModes(EscCommand.FONT.FONTB, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText(footer + "\n");


        esc.addGeneratePlus(LabelCommand.FOOT.F5, (byte) 255, (byte) 255);
        esc.addPrintAndFeedLines((byte) 2);

        byte[] bytes = {29, 114, 1};
        esc.addUserCommand(bytes);
        Vector<Byte> datas = esc.getCommand();
        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CONN_STATE_DISCONN:
                    if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null || !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].closePort(id);

                        Toast.makeText(PaymentReceiptReprentingActivity.this, getString(R.string.str_disconnect_success), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case PRINTER_COMMAND_ERROR:

                    Toast.makeText(PaymentReceiptReprentingActivity.this, getString(R.string.str_choice_printer_command), Toast.LENGTH_SHORT).show();
                    break;
                case CONN_PRINTER:
                    Toast.makeText(PaymentReceiptReprentingActivity.this, getString(R.string.str_cann_printer), Toast.LENGTH_SHORT).show();

                    break;
                case MESSAGE_UPDATE_PARAMETER:
                    String strIp = msg.getData().getString("Ip");
                    String strPort = msg.getData().getString("Port");
                    new DeviceConnFactoryManager.Build()
                            .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.WIFI)
                            .setIp(strIp)
                            .setId(id)
                            .setPort(Integer.parseInt(strPort))
                            .build();
                    threadPool = ThreadPool.getInstantiation();
                    threadPool.addTask(new Runnable() {
                        @Override
                        public void run() {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
                        }
                    });
                    break;
                default:
                    new DeviceConnFactoryManager.Build()
                            .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.WIFI)
                            .setIp("192.168.2.227")
                            .setId(id)
                            .setPort(9100)
                            .build();
                    threadPool.addTask(new Runnable() {
                        @Override
                        public void run() {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case Constant.BLUETOOTH_REQUEST_CODE: {
                    closeport();
                    String macAddress = data.getStringExtra(BluetoothDeviceList.EXTRA_DEVICE_ADDRESS);
                    new DeviceConnFactoryManager.Build()
                            .setId(id)
                            .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.BLUETOOTH)
                            .setMacAddress(macAddress)
                            .build();

                    Log.d("TAG", "onActivityResult: 连接蓝牙" + id);
                    threadPool = ThreadPool.getInstantiation();
                    threadPool.addTask(new Runnable() {
                        @Override
                        public void run() {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();

                            if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getCurrentPrinterCommand() == PrinterCommand.ESC) {
                                sendReceiptText();
                            } else {
                                mHandler.obtainMessage(PRINTER_COMMAND_ERROR).sendToTarget();
                            }
                        }
                    });

                    break;
                }
//                /*USB连接*/
//                case Constant.USB_REQUEST_CODE: {
//                    closeport();
//                    /* 获取USB设备名 */
//                    usbName = data.getStringExtra( UsbDeviceList.USB_NAME );
//                    /* 通过USB设备名找到USB设备 */
//                    UsbDevice usbDevice = Utils.getUsbDeviceFromName( MainActivity.this, usbName );
//                    /* 判断USB设备是否有权限 */
//                    if ( usbManager.hasPermission( usbDevice ) )
//                    {
//                        usbConn( usbDevice );
//                    } else {        /* 请求权限 */
//                        mPermissionIntent = PendingIntent.getBroadcast( this, 0, new Intent( ACTION_USB_PERMISSION ), 0 );
//                        usbManager.requestPermission( usbDevice, mPermissionIntent );
//                    }
//                    break;
//                }
//                /*串口连接*/
//                case Constant.SERIALPORT_REQUEST_CODE:
//                    closeport();
//                    /* 获取波特率 */
//                    int baudrate = data.getIntExtra( Constant.SERIALPORTBAUDRATE, 0 );
//                    /* 获取串口号 */
//                    String path = data.getStringExtra( Constant.SERIALPORTPATH );
//
//                    if ( baudrate != 0 && !TextUtils.isEmpty( path ) )
//                    {
//                        /* 初始化DeviceConnFactoryManager */
//                        new DeviceConnFactoryManager.Build()
//                                /* 设置连接方式 */
//                                .setConnMethod( DeviceConnFactoryManager.CONN_METHOD.SERIAL_PORT )
//                                .setId( id )
//                                /* 设置波特率 */
//                                .setBaudrate( baudrate )
//                                /* 设置串口号 */
//                                .setSerialPort( path )
//                                .build();
//                        /* 打开端口 */
//                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
//                    }
//                    break;
                case CONN_MOST_DEVICES:
                    id = data.getIntExtra("id", -1);
                    if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null &&
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
                        Toast.makeText(this, getString(R.string.str_conn_state_connected) + "\n" + getConnDeviceInfo(), Toast.LENGTH_SHORT).show();
                        //tvConnState.setText( g );
                    } else {
                        Toast.makeText(this, getString(R.string.str_conn_state_disconnect), Toast.LENGTH_SHORT).show();
                        //tvConnState.setText( getString( R.string.str_conn_state_disconnect ) );
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void
    closeport() {
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null && DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].mPort != null) {
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].reader.cancel();
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].mPort.closePort();
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].mPort = null;
        }
    }

    private String getConnDeviceInfo() {
        String str = "";
        DeviceConnFactoryManager deviceConnFactoryManager = DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id];
        if (deviceConnFactoryManager != null
                && deviceConnFactoryManager.getConnState()) {
            if ("USB".equals(deviceConnFactoryManager.getConnMethod().toString())) {
                str += "USB\n";
                str += "USB Name: " + deviceConnFactoryManager.usbDevice().getDeviceName();
            } else if ("WIFI".equals(deviceConnFactoryManager.getConnMethod().toString())) {
                str += "WIFI\n";
                str += "IP: " + deviceConnFactoryManager.getIp() + "\t";
                str += "Port: " + deviceConnFactoryManager.getPort();
            } else if ("BLUETOOTH".equals(deviceConnFactoryManager.getConnMethod().toString())) {
                str += "BLUETOOTH\n";
                str += "MacAddress: " + deviceConnFactoryManager.getMacAddress();
            } else if ("SERIAL_PORT".equals(deviceConnFactoryManager.getConnMethod().toString())) {
                str += "SERIAL_PORT\n";
                str += "Path: " + deviceConnFactoryManager.getSerialPortPath() + "\t";
                str += "Baudrate: " + deviceConnFactoryManager.getBaudrate();
            }
        }
        return (str);
    }


}