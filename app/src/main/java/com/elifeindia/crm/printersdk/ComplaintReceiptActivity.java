package com.elifeindia.crm.printersdk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elifeindia.crm.BuildConfig;
import com.elifeindia.crm.R;
import com.elifeindia.crm.contract.activities.ComplaintReceiptContract;
import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.HeaderModel;
import com.elifeindia.crm.networking.RetrofitAdapter;
import com.elifeindia.crm.presenter.activities.ComplaintReceiptPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.view.activities.ComplaintDetailsActivity;
import com.elifeindia.crm.view.activities.CustomerListActivity;
import com.elifeindia.crm.view.activities.CustomersDetailsActivity;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.paidAmount;
import static com.elifeindia.crm.view.fragment.RegisterComplaintFragment.assignTo;
import static com.elifeindia.crm.view.fragment.RegisterComplaintFragment.compStatus;
import static com.elifeindia.crm.view.fragment.RegisterComplaintFragment.complaintDate;
import static com.elifeindia.crm.view.fragment.RegisterComplaintFragment.complaintId;
import static com.elifeindia.crm.view.fragment.RegisterComplaintFragment.product;
import static com.elifeindia.crm.view.fragment.RegisterComplaintFragment.priority;
import static com.elifeindia.crm.view.fragment.RegisterComplaintFragment.complaintType;

import static com.elifeindia.crm.view.activities.EditComplaintActivity.assignToEdit;
import static com.elifeindia.crm.view.activities.EditComplaintActivity.compStatusEdit;
import static com.elifeindia.crm.view.activities.EditComplaintActivity.complaintDateEdit;
import static com.elifeindia.crm.view.activities.EditComplaintActivity.complaintIdEdit;
import static com.elifeindia.crm.view.activities.EditComplaintActivity.productEdit;
import static com.elifeindia.crm.view.activities.EditComplaintActivity.priorityEdit;
import static com.elifeindia.crm.view.activities.EditComplaintActivity.complaintTypeEdit;

public class ComplaintReceiptActivity extends AppCompatActivity implements ComplaintReceiptContract.View {
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 10112;
    ComplaintReceiptContract.Presenter presenter;
    Button btn_next;
    ListView lv_footer;
    File imagePath;
    ImageView iv_back;
    String dateTime = "";
    File pdfFile;
    TextView txt_header, txt_cust_name, txt_complaint, txt_complaint_code, txt_accountno, txt_subid, txt_comp_date, txt_comp_code, txt_product, txt_priority, txt_comp_status, txt_assignto;
    TextView shareRecieptBtn, shareOnWhatsapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_receipt);


        txt_cust_name = findViewById(R.id.txt_cust_name);
        //txt_accountno = findViewById(R.id.txt_accountno);
        txt_subid = findViewById(R.id.txt_subid);
        txt_comp_date = findViewById(R.id.txt_comp_date);
        txt_product = findViewById(R.id.txt_product);
        txt_priority = findViewById(R.id.txt_priority);
        txt_comp_status = findViewById(R.id.txt_comp_status);
        txt_assignto = findViewById(R.id.txt_assignto);
        txt_complaint = findViewById(R.id.txt_complaint);
        btn_next = findViewById(R.id.btn_next);
        txt_header = findViewById(R.id.txt_header);
        lv_footer = findViewById(R.id.lv_footer);
        txt_complaint_code = findViewById(R.id.txt_complaint_code);

        iv_back = findViewById(R.id.iv_back);

        shareRecieptBtn = findViewById(R.id.share_receipt_txt);
        shareOnWhatsapp = findViewById(R.id.share_on_whatsapp);

        presenter = new ComplaintReceiptPresenter(this);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();

                String name = sh.getString("CustomerDetails", "");


                if (name.equals("Customer Details")) {
                    startActivity(new Intent(ComplaintReceiptActivity.this, CustomersDetailsActivity.class));
                    editor.clear();
                    editor.commit();
                    finish();
                } else {
                    startActivity(new Intent(ComplaintReceiptActivity.this, ComplaintDetailsActivity.class));
                    finish();
                }

            }
        });

        shareRecieptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap receiptBitmap;
                receiptBitmap = takeScreenshot();
                saveBitmap(receiptBitmap);
                shareIt();

            }
        });

        shareOnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap receiptBitmap;
                receiptBitmap = takeScreenshot();
                saveBitmap(receiptBitmap);
               // shareItOnWhatsApp();
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String flag = SharedPrefsData.getString(ComplaintReceiptActivity.this, Constants.ComplaintFlag, Constants.PREF_NAME);

                if (flag.equals("fragment")) {
                    Intent i = new Intent(ComplaintReceiptActivity.this, CustomerListActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(i);
                } else {
                    finish();
                }


            }
        });

        Bundle bundle = getIntent().getExtras();
        String activity = bundle.getString("activity_name");

        if (activity.equals("InsertComplaint")) {

            /*txt_cust_name.setText(SharedPrefsData.getString(this, Constants.CustomerName, Constants.PREF_NAME));
            //txt_accountno.setText(SharedPrefsData.getString(this, Constants.AccNo, Constants.PREF_NAME));
            txt_subid.setText(SharedPrefsData.getString(this, Constants.SubId, Constants.PREF_NAME));
            txt_comp_date.setText(complaintDate);
            txt_product.setText(product);
            txt_priority.setText(priority);
            txt_comp_status.setText(compStatus);
            txt_assignto.setText(assignTo);
            txt_complaint.setText(complaintType);*/

            final String url = RetrofitAdapter.GET_COMPLAINT + complaintId;

            getComplaintCode(url);


        } else {
            try {
                /*txt_cust_name.setText(SharedPrefsData.getString(this, Constants.CustomerName, Constants.PREF_NAME));
                //txt_accountno.setText(SharedPrefsData.getString(this, Constants.AccNo, Constants.PREF_NAME));
                txt_subid.setText(SharedPrefsData.getString(this, Constants.SubId, Constants.PREF_NAME));
                txt_comp_date.setText(complaintDateEdit);
                txt_product.setText(productEdit);
                txt_priority.setText(priorityEdit);
                txt_comp_status.setText(compStatusEdit);
                txt_assignto.setText(assignToEdit);
                txt_complaint.setText(complaintTypeEdit);
                //txt_complaint_code.setText(complaintIdEdit);*/

                final String url = RetrofitAdapter.GET_COMPLAINT + complaintIdEdit;

                getComplaintCode(url);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String compId = SharedPrefsData.getString(this, Constants.CompanyID, Constants.PREF_NAME);
        presenter.loadHeader(this, compId);
        presenter.loadFooter(this, compId);

    }


    private void shareItOnWhatsApp() {

        String custWhatsAppNo = SharedPrefsData.getString(ComplaintReceiptActivity.this, Constants.WhatsupNo, Constants.PREF_NAME);

        String smsNumber = "919030144534"; // E164 format without '+' sign
       // String smsNumber = "91" + custWhatsAppNo; // E164 format without '+' sign

        Intent sendIntent = new Intent(Intent.ACTION_SEND);

        //sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));

        sendIntent.setType("text/plain");


        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "*Complaint Receipt*\n" +
                        "*Customer Details*\n" +
                        "Name: " + txt_cust_name.getText().toString() + "\n" +
                        "Subscriber ID: " + txt_subid.getText().toString() + "\n" +
                        "Complain Code: " + txt_complaint_code.getText().toString() + "\n" +
                        "Complain Date: " + txt_comp_date.getText().toString() + "\n" +
                        "Complain: " + txt_complaint.getText().toString() + "\n" +
                        "\n" +
                        "*Complain Details*\n" +
                        "------------------------\n" +
                        "Product: " + txt_product.getText().toString() + "\n" +
                        "Priority: " + txt_priority.getText().toString() + "\n" +
                        "Complain Status: " + txt_comp_status.getText().toString() + "\n" +
                        "Assigned To: " + txt_assignto.getText().toString() + "\n" +

                        "------------------------\n" +
                        "" + txt_header.getText().toString().trim()
        );

        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix

        //sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Error/n", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(sendIntent);

    }


    private void createPdfWrapper() throws FileNotFoundException, DocumentException {
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
                        }
                    });
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        } else {
            createPdf();
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
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
                        Toast.makeText(getApplicationContext(), "IOException", Toast.LENGTH_LONG).show();
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
        Document document = new Document(PageSize.A4, 60, 60, 50, 50);
        PdfWriter.getInstance(document, output);
        document.open();
        Font fontStyling = new Font(Font.FontFamily.TIMES_ROMAN, 25.0f, Font.NORMAL, BaseColor.BLACK);
        Font fontStyling2 = new Font(Font.FontFamily.TIMES_ROMAN, 25.0f, Font.BOLD, BaseColor.BLACK);
        Font g = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.NORMAL, BaseColor.BLUE);
        document.add(new Paragraph("Complaint Receipt\nCustomer Details", fontStyling2));
        document.add(new Paragraph("Name: " + txt_cust_name.getText().toString(), fontStyling));
        document.add(new Paragraph("Subscriber ID: " + txt_subid.getText().toString(), fontStyling));
        document.add(new Paragraph("Complain Code: " + txt_complaint_code.getText().toString(), fontStyling));
        document.add(new Paragraph("Complain Date: " + txt_comp_date.getText().toString(), fontStyling));
        document.add(new Paragraph("Complain: " + txt_complaint.getText().toString() + " /-", fontStyling));
        document.add(new Paragraph("\nComplain Details", fontStyling2));
        document.add(new Paragraph("Product: " + txt_product.getText().toString(), fontStyling));
        document.add(new Paragraph("Priority: " + txt_priority.getText().toString(), fontStyling));
        document.add(new Paragraph("Complain Status: " + txt_comp_status.getText().toString(), fontStyling));
        document.add(new Paragraph("Assigned To: " + txt_assignto.getText().toString(), fontStyling));
        document.add(new Paragraph("\n-----------------------------------------\n" + txt_header.getText().toString().trim(), fontStyling));
        document.close();
        previewPdf(pdfFile);
    }


    private void previewPdf(File pdfFile) {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        List<ResolveInfo> resInfoList = getApplicationContext().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        Uri uri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getApplicationContext().getPackageName() + ".provider", pdfFile);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            getApplicationContext().grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(intent);

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
    public void onBackPressed() {
        super.onBackPressed();

        SharedPreferences sh = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();

        String name = sh.getString("CustomerDetails", "");


        if (name.equals("Customer Details")) {
            startActivity(new Intent(ComplaintReceiptActivity.this, CustomersDetailsActivity.class));
            editor.clear();
            editor.commit();
            finish();
        } else {
            startActivity(new Intent(ComplaintReceiptActivity.this, ComplaintDetailsActivity.class));
            finish();
        }

    }

    private void getComplaintCode(String url) {

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("complaint");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object1 = array.getJSONObject(i);
                        String compCode = object1.getString("complaint_Code");
                        txt_complaint_code.setText(compCode);

                        String custName = object1.getString("name");
                        txt_cust_name.setText(custName);
                        //txt_accountno.setText(SharedPrefsData.getString(this, Constants.AccNo, Constants.PREF_NAME));

                        String CustSubsId = object1.getString("subscriber_ID");
                        txt_subid.setText(CustSubsId);

                        //String CompDate = object1.getString("complaint_Date");
                        txt_comp_date.setText(complaintDate);

                        String Product = object1.getString("product_Name");
                        txt_product.setText(Product);

                        String Priority = object1.getString("priority_Name");
                        txt_priority.setText(Priority);

                        String CompStatus = object1.getString("complaint_Status");
                        txt_comp_status.setText(CompStatus);

                        String AssignedTo = object1.getString("assignTo");
                        txt_assignto.setText(AssignedTo);

                        String Complaint = object1.getString("complaint_Type");
                        txt_complaint.setText(Complaint);


                    }

                } catch (Exception w) {
                    Toast.makeText(ComplaintReceiptActivity.this, w.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ComplaintReceiptActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void shareIt() {
        Uri uri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                BuildConfig.APPLICATION_ID + ".provider", imagePath);

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "Complaint Receipt";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Complaint Receipt");
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

    public void saveBitmap(Bitmap bitmap) {
        File folder = new File(this.getExternalFilesDir(null) + File.separator + "ECRM Receipts");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            imagePath = new File(this.getExternalFilesDir(null) + "/ECRM Receipts" + "/ecrm_invoice_" + dateTime + ".jpg");
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

    public Bitmap takeScreenshot() {
        LinearLayout rootView = findViewById(R.id.root);
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showHeader(HeaderModel headerModel) {
        txt_header.setText(headerModel.getHeader().toString());
    }

    @Override
    public void showFooter(FooterModel footerModel) {
        List<FooterModel.HeaderfooterDTO> footerModels = footerModel.getHeaderfooter();
        ArrayList<String> content = new ArrayList<String>();
        for (int i = 0; i < footerModels.size(); i++) {
            content.add(footerModels.get(i).getDisplay_Content());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, content);
        lv_footer.setAdapter(arrayAdapter);
    }
}