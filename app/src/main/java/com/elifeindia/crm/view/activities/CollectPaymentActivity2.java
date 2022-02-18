package com.elifeindia.crm.view.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.Pager;
import com.elifeindia.crm.view.fragment.CollectPaymentFragment;
import com.elifeindia.crm.view.fragment.CustomerPaymentHistoryFragment;
import com.google.android.material.tabs.TabLayout;

public class CollectPaymentActivity2 extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_payment2);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        //here we set fragment in viewpager
        setupViewPager(viewPager);

        //here we set tablayout in viewpager
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager) {
        Pager pager = new Pager(getSupportFragmentManager());
        pager.addFrag(new CollectPaymentFragment(),"Collection");
        pager.addFrag(new CustomerPaymentHistoryFragment(),"History");
        viewPager.setAdapter(pager);
    }



}