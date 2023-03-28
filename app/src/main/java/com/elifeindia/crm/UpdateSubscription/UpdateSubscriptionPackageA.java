package com.elifeindia.crm.UpdateSubscription;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.Pager;
import com.elifeindia.crm.model.CableBoxWithSubscription;
import com.elifeindia.crm.UpdateSubscription.Fragment.AlcarteFragementA;
import com.elifeindia.crm.UpdateSubscription.Fragment.PackageFragmentA;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.view.activities.CustomerListActivity;
import com.google.android.material.tabs.TabLayout;

public class UpdateSubscriptionPackageA extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Button btn_save;
    public static String total_Bouquet_amountupdate="0";
    public static String total_alacarte_amountupdate="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subscription_package);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(UpdateSubscriptionPackageA.this, UpdateSubscriptionAtivity.class);
                s.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(s);
            }
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        btn_save = findViewById(R.id.btn_save);

        //here we set fragment in viewpager
        setupViewPager(viewPager);
        //here we set tablayout in viewpager
        tabLayout.setupWithViewPager(viewPager);



    }
    private void setupViewPager(ViewPager viewPager) {
        Pager pager = new Pager(getSupportFragmentManager());
        pager.addFrag(new PackageFragmentA(),"Package List");
        pager.addFrag(new AlcarteFragementA(),"Alacarte List");

        viewPager.setAdapter(pager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(UpdateSubscriptionPackageA.this, UpdateSubscriptionAtivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);



    }
}