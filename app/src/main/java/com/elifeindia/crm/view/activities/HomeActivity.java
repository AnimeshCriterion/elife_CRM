package com.elifeindia.crm.view.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.SliderAdapterExample;
import com.elifeindia.crm.contract.activities.HomeContract;
import com.elifeindia.crm.model.RolewiseAccess;
import com.elifeindia.crm.model.SliderItem;
import com.elifeindia.crm.presenter.activities.HomePresenter;
import com.elifeindia.crm.printersdk.PrinterSettingsActivity;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.Duration;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    HomeContract.Presenter presenter;
    private DrawerLayout drawer_layout;
    SliderView sliderView;
    private SliderAdapterExample adapter;
    TextView txt_empname, txt_empmob, txt_networkname;
    LinearLayout ll_dashboard;
    public static  Boolean hideReport=false, hideCollectPayment=false, hideInvoice=false,hideUpdateSubscription=false;
    private AppUpdateManager mUpdateManager;
    private static final int RC_APP_UPDATE = 100;
    private  DrawerLayout drawerLayout;
      ArrayList<SliderItem> imagesList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        new AppUpdater(HomeActivity.this).setUpdateFrom(UpdateFrom.GOOGLE_PLAY).setDisplay(Display.DIALOG).
//                setDuration(Duration.INDEFINITE).start();

        ll_dashboard = findViewById(R.id.ll_dashboard);
        drawer_layout=findViewById(R.id.drawer_layout);
        imagesList=new ArrayList<>();
        SliderItem slider1=new SliderItem();
        slider1.setImageUrl("http://ecableapi.elifeindia.in/Content/slider/s1.jpg");
        imagesList.add(slider1);
        SliderItem slider2=new SliderItem();
        slider2.setImageUrl("http://ecableapi.elifeindia.in/Content/slider/s2.jpg");
        imagesList.add(slider2);
        SliderItem slider3=new SliderItem();
        slider3.setImageUrl("http://ecableapi.elifeindia.in/Content/slider/s3.jpg");
        imagesList.add(slider3);
        SliderItem slider4=new SliderItem();
        slider4.setImageUrl("http://ecableapi.elifeindia.in/Content/slider/s4.jpg");
        imagesList.add(slider4);


        presenter = new HomePresenter(this);
        presenter.start();


        mUpdateManager = AppUpdateManagerFactory.create(this);
        mUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {

                try {
                    Log.d("TAG", "onSuccessUp[date: " + UpdateAvailability.UPDATE_AVAILABLE);
                    mUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, HomeActivity.this, RC_APP_UPDATE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }


            }

        }).addOnFailureListener(e -> {
            //Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        });

        try {
            mUpdateManager.registerListener(installStateUpdatedListener);
        } catch (Exception e) {
            e.printStackTrace();
        }


        findViewById(R.id.btn_customerList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, CustomerListActivity.class);
                i.putExtra("activity", "Dashboard");
                startActivity(i);
            }
        });

        findViewById(R.id.collectiondetails_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CollectionDetailsActivity.class));
            }
        });

        findViewById(R.id.compliantslist_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, ComplaintDetailsActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.cardViewResetPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(GravityCompat.START);
                Intent i = new Intent(HomeActivity.this, ResetPasswordActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.ll_compl_summary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(HomeActivity.this, R.style.ListRow));

                builder.setTitle("Stay Connected With Us")
                        .setMessage("This functionality is coming soon")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        }).show();
            }
        });

        findViewById(R.id.ll_invoice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(new Intent(HomeActivity.this, GenerateInvoiceActivity.class));
                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(HomeActivity.this, R.style.ListRow));

                builder.setTitle("Stay Connected With Us")
                        .setMessage("This functionality is coming soon")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        }).show();

            }
        });

        findViewById(R.id.addcomplaint_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(HomeActivity.this, R.style.ListRow));

                builder.setTitle("Stay Connected With Us")
                        .setMessage("This functionality is coming soon")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        }).show();


                // startActivity(new Intent(HomeActivity.this, RegisterComplaintActivity.class));
            }
        });

        findViewById(R.id.ll_customerList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, CustomerListActivity.class);
                i.putExtra("activity", "Dashboard");
                startActivity(i);
            }
        });

        findViewById(R.id.ll_payments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CollectionDetailsActivity.class));
            }
        });

        findViewById(R.id.ll_complaints).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ComplaintDetailsActivity.class));
            }
        });

        findViewById(R.id.ll_invoices).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(HomeActivity.this, InvoiceHistoryActivity.class));
                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(HomeActivity.this, R.style.ListRow));

                builder.setTitle("Stay Connected With Us")
                        .setMessage("This functionality is coming soon")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        }).show();
            }
        });

        findViewById(R.id.rl_dashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);
            }
        });
        findViewById(R.id.rl_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out our eLife CRM app at: https://play.google.com/store/apps/details?id=" + "elifeIndia.CRMS");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                // startActivity(new Intent(HomeActivity.this, ComplaintDetailsActivity.class));
            }
        });
        findViewById(R.id.rl_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = "+91 8099346155";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
             //   startActivity(new Intent(HomeActivity.this, ComplaintDetailsActivity.class));
            }
        });
        findViewById(R.id.rl_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.elifeindia.in")));
                //startActivity(new Intent(HomeActivity.this, AboutAppActivity.class));
            }
        });findViewById(R.id.rl_notifications).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PrinterSettingsActivity.class));
            }
        });

        findViewById(R.id.rl_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                    getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                } else {
                    drawer_layout.openDrawer(Gravity.LEFT);

                }
            }
        });

        sliderView = findViewById(R.id.imageSlider);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        adapter = new SliderAdapterExample(this,imagesList);
        sliderView.setSliderAdapter(adapter);
        adapter.notifyDataSetChanged();


//        SliderItem sliderItem0 = new SliderItem();
//        //sliderItem0.setDescription("Slider Item Added Manually");
//        sliderItem0.setImageUrl("http://ecableapi.elifeindia.in/Content/slider/s1.jpg");
//        adapter.addItem(sliderItem0);
//
//        SliderItem sliderItem = new SliderItem();
//      //  sliderItem.setDescription("Slider Item Added Manually");
//        sliderItem.setImageUrl("http://ecableapi.elifeindia.in/Content/slider/s2.jpg");
//        adapter.addItem(sliderItem);
//
//
//        SliderItem sliderItem1 = new SliderItem();
//       // sliderItem1.setDescription("Slider Item Added Manually");
//        sliderItem1.setImageUrl("http://ecableapi.elifeindia.in/Content/slider/s3.jpg");
//        adapter.addItem(sliderItem1);
//
//        SliderItem sliderItem2 = new SliderItem();
//        //sliderItem2.setDescription("Slider Item Added Manually");
//        sliderItem2.setImageUrl("http://ecableapi.elifeindia.in/Content/slider/s4.jpg");
//        adapter.addItem(sliderItem2);
//
//        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
//            @Override
//            public void onIndicatorClicked(int position) {
//               // Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
//            }
//        });

        presenter.loadApi(this, SharedPrefsData.getString(this, Constants.RoleId, Constants.PREF_NAME));


    }

    @Override
    public void init() {
        drawer_layout = findViewById(R.id.drawer_layout);
        txt_networkname = findViewById(R.id.txt_networkname);
        txt_empname = findViewById(R.id.txt_empname);
        txt_empmob = findViewById(R.id.txt_empmob);

        txt_empmob.setText(SharedPrefsData.getString(this, Constants.EmployeeCode, Constants.PREF_NAME));
        txt_empname.setText(SharedPrefsData.getString(this, Constants.EmployeeName, Constants.PREF_NAME));
        txt_networkname.setText(SharedPrefsData.getString(this, Constants.CompanyName, Constants.PREF_NAME));

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private final InstallStateUpdatedListener installStateUpdatedListener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(@NonNull InstallState installState) {
            if (installState.installStatus() == InstallStatus.DOWNLOADED) {
                showCompletedUpdate();
            }
        }

        private void showCompletedUpdate() {
            Snackbar snackbar = Snackbar.make(drawer_layout, "New App is Ready", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("Install", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mUpdateManager.completeUpdate();
                }
            });
            snackbar.show();
        }
    };


    @Override
    public void showResult(RolewiseAccess rolewiseAccess) {

        ll_dashboard.setVisibility(View.VISIBLE);

        int size = rolewiseAccess.getAccess().size();
        for (int i=0; i<size-1; i++){
            String a = rolewiseAccess.getAccess().get(i).getScreenName();
            String m = rolewiseAccess.getAccess().get(i).getModule();
            Boolean b = rolewiseAccess.getAccess().get(i).getApprove();

            if(a.equals("Customer Search") && m.equals("Customer") && !b){
                //Customer Details
                findViewById(R.id.btn_customerList).setVisibility(View.GONE);
                findViewById(R.id.ll_customerList).setVisibility(View.GONE);

            } if(a.equals("Payment") && m.equals("Customer") && !b){
                //collect payment // Payment History
                hideCollectPayment = true;
                findViewById(R.id.collectiondetails_btn).setVisibility(View.GONE);
                findViewById(R.id.ll_payments).setVisibility(View.GONE);

            } if(a.equals("Complaint List") && m.equals("Complaint") && !b){
                //Complaint History //Complaint Summary
                findViewById(R.id.ll_compl_summary).setVisibility(View.GONE);
                findViewById(R.id.compliantslist_btn).setVisibility(View.GONE);

            } if(a.equals("Complaint") && m.equals("Complaint") && !b){
                //Add complaint //Report
                hideReport = true;
                findViewById(R.id.addcomplaint_btn).setVisibility(View.GONE);
                findViewById(R.id.ll_complaints).setVisibility(View.GONE);

            } if(a.equals("Invoice") && m.equals("Customer") && !b){
                //Generate Invoice // Invoice
                hideInvoice = true;
                findViewById(R.id.ll_invoice).setVisibility(View.GONE);
                findViewById(R.id.ll_invoices).setVisibility(View.GONE);

            }
            //check for true
             if(a.equals("Customer Search") && m.equals("Customer") && b){
                //Customer Details
                findViewById(R.id.btn_customerList).setVisibility(View.VISIBLE);
                findViewById(R.id.ll_customerList).setVisibility(View.VISIBLE);

            } if(a.equals("Payment") && m.equals("Customer") && b){
                //collect payment // Payment History
                hideCollectPayment = false;
                findViewById(R.id.collectiondetails_btn).setVisibility(View.VISIBLE);
                findViewById(R.id.ll_payments).setVisibility(View.VISIBLE);

            } if(a.equals("Complaint List") && m.equals("Complaint") && b){
                //Complaint History //Complaint Summary
                findViewById(R.id.ll_compl_summary).setVisibility(View.VISIBLE);
                findViewById(R.id.compliantslist_btn).setVisibility(View.VISIBLE);

            } if(a.equals("Complaint") && m.equals("Complaint") && b){
                //Add complaint //Report
                hideReport = false;
                findViewById(R.id.addcomplaint_btn).setVisibility(View.GONE);
                findViewById(R.id.ll_complaints).setVisibility(View.VISIBLE);

            } if(a.equals("Invoice") && m.equals("Customer") && b){
                //Generate Invoice // Invoice
                hideInvoice = false;
                findViewById(R.id.ll_invoice).setVisibility(View.VISIBLE);
                findViewById(R.id.ll_invoices).setVisibility(View.GONE);

            }
            if(a.equals("Subscription")&& m.equals("Customer") && b==true){
                hideUpdateSubscription=true;
            }
        }
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.ListRow));
        builder.setIcon(android.R.drawable.ic_dialog_info).setTitle("Exit App Confirmation")
                .setMessage("Are you sure you want to close this app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finishAffinity();
                    }
                }).setNegativeButton("N0",null)
                .show();

    }
}