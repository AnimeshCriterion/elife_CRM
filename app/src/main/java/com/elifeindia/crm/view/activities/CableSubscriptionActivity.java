package com.elifeindia.crm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.view_subscription_module.AlacarteListAdapter;
import com.elifeindia.crm.adapters.view_subscription_module.BouquetListAdapter;
import com.elifeindia.crm.contract.activities.SubscriptionDetailsContract;
import com.elifeindia.crm.model.BoxAlacarteList;
import com.elifeindia.crm.model.BoxBouquetList;
import com.elifeindia.crm.model.InternetSubscriptionDetails;
import com.elifeindia.crm.presenter.activities.SubscriptionDetailsPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;

import java.util.List;

public class CableSubscriptionActivity extends AppCompatActivity implements SubscriptionDetailsContract.View {
    SubscriptionDetailsContract.Presenter presenter;
    ViewUtils viewUtils;
    RecyclerView rv_alacarte_list, rv_bouquet_list;
    AlacarteListAdapter alacarteListAdapter;
    BouquetListAdapter bouquetListAdapter;
    TextView txt_subid, txt_name, txt_name_letter, txt_mob_no, txt_boq, txt_ala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        rv_alacarte_list = findViewById(R.id.rv_alacarte_list);
        rv_bouquet_list = findViewById(R.id.rv_bouquet_list);
        txt_subid = findViewById(R.id.txt_subid);
        txt_name = findViewById(R.id.txt_name);
        txt_name_letter = findViewById(R.id.txt_name_letter);
        txt_mob_no = findViewById(R.id.txt_mob_no);
        txt_boq = findViewById(R.id.txt_boq);
        txt_ala = findViewById(R.id.txt_ala);

        txt_name.setText("NAME : "+SharedPrefsData.getString(this, Constants.CustomerName, Constants.PREF_NAME));
        txt_subid.setText("SUB ID : "+SharedPrefsData.getString(this, Constants.SubId, Constants.PREF_NAME));
        txt_mob_no.setText(SharedPrefsData.getString(this, Constants.CustMob, Constants.PREF_NAME));

        String custname = SharedPrefsData.getString(this, Constants.CustomerName, Constants.PREF_NAME);
        String firstLetter = custname.substring(0, 1);
        txt_name_letter.setText(firstLetter);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        viewUtils = new ViewUtils();
        presenter = new SubscriptionDetailsPresenter(this);
        presenter.start();

        String cBoxId = SharedPrefsData.getString(this, Constants.CableBoxID, Constants.PREF_NAME);
        String iBoxId = SharedPrefsData.getString(this, Constants.InternetBoxID, Constants.PREF_NAME);
        presenter.loadBoxAlacarteList(cBoxId);
        presenter.loadBoxBouquetList(cBoxId);

    }

    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {
        viewUtils.toast(this, message);
    }

    @Override
    public void showAlacarte(BoxAlacarteList boxAlacarteList) {

        List<BoxAlacarteList.BoxAlacarete> boxAlacaretes = boxAlacarteList.getBoxAlacarete();

        if(boxAlacaretes.size()==0){
            txt_ala.setText("Alacarte not found");
        }
//
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_alacarte_list.setLayoutManager(mLayoutManager);
        alacarteListAdapter = new AlacarteListAdapter(this, boxAlacaretes);
        rv_alacarte_list.setAdapter(alacarteListAdapter);

    }

    @Override
    public void showBouquets(BoxBouquetList boxBouquetList) {

        List<BoxBouquetList.BoxBouquet> boxBouquets = boxBouquetList.getBoxBouquet();
        if(boxBouquets.size()==0){
            txt_ala.setText("Bouquet / Package not found");
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_bouquet_list.setLayoutManager(mLayoutManager);
        bouquetListAdapter = new BouquetListAdapter(this, boxBouquets);
        rv_bouquet_list.setAdapter(bouquetListAdapter);


    }

    @Override
    public void showInternetPkg(InternetSubscriptionDetails internetSubscriptionDetails) {
       // viewUtils.toast(this, internetSubscriptionDetails.getBoxpackage().get(0).getPackage().toString());
    }
}