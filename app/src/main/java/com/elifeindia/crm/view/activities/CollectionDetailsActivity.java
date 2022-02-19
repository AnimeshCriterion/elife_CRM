package com.elifeindia.crm.view.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.Pager;
import com.elifeindia.crm.adapters.PaymentListAdapter;
import com.elifeindia.crm.contract.activities.PaymentListContract;
import com.elifeindia.crm.model.PaymentRecieptList;
import com.elifeindia.crm.presenter.activities.PaymentListPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.fragment.PaymentDetailsFragment;
import com.elifeindia.crm.view.fragment.PaymentSummaryFragment;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CollectionDetailsActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list);
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
        pager.addFrag(new PaymentDetailsFragment(),"Details");
      //  pager.addFrag(new PaymentSummaryFragment(),"Summary");
        viewPager.setAdapter(pager);
    }



}