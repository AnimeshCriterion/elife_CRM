package com.elifeindia.crm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.Pager;
import com.elifeindia.crm.view.fragment.CustomersComplaintHistoeyFragemnt;
import com.elifeindia.crm.view.fragment.RegisterComplaintFragment;
import com.google.android.material.tabs.TabLayout;

public class RegisterComplaintActivity2 extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint2);

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

        pager.addFrag(new RegisterComplaintFragment(),"Register");
        pager.addFrag(new CustomersComplaintHistoeyFragemnt(),"History");


        viewPager.setAdapter(pager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        this.finish();

    }
}