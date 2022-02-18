package com.elifeindia.crm.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.Pager;
import com.elifeindia.crm.model.CableBoxWithSubscription;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.view.fragment.AlacarteListFragment;
import com.elifeindia.crm.view.fragment.PackageListFragment;
import com.google.android.material.tabs.TabLayout;

import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.alacarteModelAlacarte;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.alacarteModela;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.bouquetModelBouquet;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.bouquetModela;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.box_position;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.cableBoxWithSubscription;

public class UpdateCableSubscriptionActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager viewPager;
    Button btn_save;
    public static String total_Bouquet_amount="0";
    public static String total_alacarte_amount="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subscription);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        btn_save = findViewById(R.id.btn_save);

        //here we set fragment in viewpager
        setupViewPager(viewPager);
        //here we set tablayout in viewpager
        tabLayout.setupWithViewPager(viewPager);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Alacarte
                cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxAlacareteList().getBoxAlacarete().clear();

                total_alacarte_amount = "0";
                float sum = Float.parseFloat(total_alacarte_amount);

                for(int i=0; i<alacarteModelAlacarte.size(); i++){
                    CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO.BoxAlacareteDTO boxAlacareteDTO = new  CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO.BoxAlacareteDTO();
                    if(alacarteModelAlacarte.get(i).isIs_select()){

                        sum += Double.valueOf(alacarteModelAlacarte.get(i).getPrice());
                        total_alacarte_amount = String.valueOf(sum);

                        try {

                            boxAlacareteDTO.setAlacarte_ID(alacarteModelAlacarte.get(i).getAlacarte_ID());
                            boxAlacareteDTO.setCable_Box_ID(0);
                            boxAlacareteDTO.setBox_Alacarte_ID(0);
                            boxAlacareteDTO.setPrice(alacarteModelAlacarte.get(i).getPrice());
                            boxAlacareteDTO.setTax_per(alacarteModelAlacarte.get(i).getTax());
                            boxAlacareteDTO.setTax(Double.valueOf(alacarteModelAlacarte.get(i).getTax_Amount()));
                            boxAlacareteDTO.setTotal_Price(Double.valueOf(alacarteModelAlacarte.get(i).getPrice()));
                            boxAlacareteDTO.setAlacarte_Name(alacarteModelAlacarte.get(i).getAlacarte_Name());

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxAlacareteList().getBoxAlacarete().add(boxAlacareteDTO);
                    }
                }
                alacarteModela.setTotal_Alacarte(total_alacarte_amount);

                //Bouquet

                cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxBouquetList().getBoxBouquet().clear();

                total_Bouquet_amount = "0";
                float sum1 = Float.parseFloat(total_Bouquet_amount);

                for(int i=0; i<bouquetModelBouquet.size(); i++){
                    CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxBouquetListDTO.BoxBouquetDTO boxBouquetDTO = new  CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxBouquetListDTO.BoxBouquetDTO();

                    if(bouquetModelBouquet.get(i).isIs_select()){

                        sum1 += Double.valueOf(bouquetModelBouquet.get(i).getPrice());
                        total_Bouquet_amount = String.valueOf(sum1);

                        try {
                            boxBouquetDTO.setBouquet_ID(bouquetModelBouquet.get(i).getBouquet_ID());
                            boxBouquetDTO.setCable_Box_ID(0);
                            boxBouquetDTO.setBox_Bouquet_ID(0);
                            boxBouquetDTO.setPrice(bouquetModelBouquet.get(i).getPrice());
                            boxBouquetDTO.setTax_per(bouquetModelBouquet.get(i).getTax());
                            boxBouquetDTO.setTax(Double.valueOf(bouquetModelBouquet.get(i).getTax_Amount()));
                            boxBouquetDTO.setTotal_Price(Double.valueOf(bouquetModelBouquet.get(i).getPrice()));
                            boxBouquetDTO.setBouquet_Name(bouquetModelBouquet.get(i).getBouquet_Name());

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxBouquetList().getBoxBouquet().add(boxBouquetDTO);
                    }
                }

                bouquetModela.setTotal_Bouquet(total_Bouquet_amount);


                finish();
            }

        });

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Alacarte
                cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxAlacareteList().getBoxAlacarete().clear();

                total_alacarte_amount = "0";
                float sum = Float.parseFloat(total_alacarte_amount);

                for(int i=0; i<alacarteModelAlacarte.size(); i++){
                    CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO.BoxAlacareteDTO boxAlacareteDTO = new  CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO.BoxAlacareteDTO();
                    if(alacarteModelAlacarte.get(i).isIs_select()){

                        sum += Double.valueOf(alacarteModelAlacarte.get(i).getPrice());
                        total_alacarte_amount = String.valueOf(sum);

                        try {
                            boxAlacareteDTO.setAlacarte_ID(alacarteModelAlacarte.get(i).getAlacarte_ID());
                            boxAlacareteDTO.setCable_Box_ID(0);
                            boxAlacareteDTO.setBox_Alacarte_ID(0);
                            boxAlacareteDTO.setPrice(alacarteModelAlacarte.get(i).getPrice());
                            boxAlacareteDTO.setTax_per(alacarteModelAlacarte.get(i).getTax());
                            boxAlacareteDTO.setTax(Double.valueOf(alacarteModelAlacarte.get(i).getTax_Amount()));
                            boxAlacareteDTO.setTotal_Price(Double.valueOf(alacarteModelAlacarte.get(i).getPrice()));
                            boxAlacareteDTO.setAlacarte_Name(alacarteModelAlacarte.get(i).getAlacarte_Name());

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxAlacareteList().getBoxAlacarete().add(boxAlacareteDTO);
                    }
                }
                alacarteModela.setTotal_Alacarte(total_alacarte_amount);

                //Bouquet

                cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxBouquetList().getBoxBouquet().clear();

                total_Bouquet_amount = "0";
                float sum1 = Float.parseFloat(total_Bouquet_amount);

                for(int i=0; i<bouquetModelBouquet.size(); i++){
                    CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxBouquetListDTO.BoxBouquetDTO boxBouquetDTO = new  CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxBouquetListDTO.BoxBouquetDTO();

                    if(bouquetModelBouquet.get(i).isIs_select()){

                        sum1 += Double.valueOf(bouquetModelBouquet.get(i).getPrice());
                        total_Bouquet_amount = String.valueOf(sum1);

                        try {
                            boxBouquetDTO.setBouquet_ID(bouquetModelBouquet.get(i).getBouquet_ID());
                            boxBouquetDTO.setCable_Box_ID(0);
                            boxBouquetDTO.setBox_Bouquet_ID(0);
                            boxBouquetDTO.setPrice(bouquetModelBouquet.get(i).getPrice());
                            boxBouquetDTO.setTax_per(bouquetModelBouquet.get(i).getTax());
                            boxBouquetDTO.setTax(Double.valueOf(bouquetModelBouquet.get(i).getTax_Amount()));
                            boxBouquetDTO.setTotal_Price(Double.valueOf(bouquetModelBouquet.get(i).getPrice()));
                            boxBouquetDTO.setBouquet_Name(bouquetModelBouquet.get(i).getBouquet_Name());

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxBouquetList().getBoxBouquet().add(boxBouquetDTO);
                    }
                }

                bouquetModela.setTotal_Bouquet(total_Bouquet_amount);


                finish();
            }
        });

    }
    private void setupViewPager(ViewPager viewPager) {
        Pager pager = new Pager(getSupportFragmentManager());
        pager.addFrag(new PackageListFragment(),"Package List");
        pager.addFrag(new AlacarteListFragment(),"Alacarte List");

        viewPager.setAdapter(pager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Alacarte
        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxAlacareteList().getBoxAlacarete().clear();

        total_alacarte_amount = "0";
        float sum = Float.parseFloat(total_alacarte_amount);

        for(int i=0; i<alacarteModelAlacarte.size(); i++){
            CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO.BoxAlacareteDTO boxAlacareteDTO = new  CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO.BoxAlacareteDTO();
            if(alacarteModelAlacarte.get(i).isIs_select()){

                sum += Double.valueOf(alacarteModelAlacarte.get(i).getPrice());
                total_alacarte_amount = String.valueOf(sum);

                try {
                    boxAlacareteDTO.setAlacarte_ID(alacarteModelAlacarte.get(i).getAlacarte_ID());
                    boxAlacareteDTO.setCable_Box_ID(0);
                    boxAlacareteDTO.setBox_Alacarte_ID(0);
                    boxAlacareteDTO.setPrice(alacarteModelAlacarte.get(i).getPrice());
                    boxAlacareteDTO.setTax_per(alacarteModelAlacarte.get(i).getTax());
                    boxAlacareteDTO.setTax(Double.valueOf(alacarteModelAlacarte.get(i).getTax_Amount()));
                    boxAlacareteDTO.setTotal_Price(Double.valueOf(alacarteModelAlacarte.get(i).getPrice()));
                    boxAlacareteDTO.setAlacarte_Name(alacarteModelAlacarte.get(i).getAlacarte_Name());

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxAlacareteList().getBoxAlacarete().add(boxAlacareteDTO);
            }
        }
        alacarteModela.setTotal_Alacarte(total_alacarte_amount);

        //Bouquet

        cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxBouquetList().getBoxBouquet().clear();

        total_Bouquet_amount = "0";
        float sum1 = Float.parseFloat(total_Bouquet_amount);

        for(int i=0; i<bouquetModelBouquet.size(); i++){
            CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxBouquetListDTO.BoxBouquetDTO boxBouquetDTO = new  CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxBouquetListDTO.BoxBouquetDTO();

            if(bouquetModelBouquet.get(i).isIs_select()){

                sum1 += Double.valueOf(bouquetModelBouquet.get(i).getPrice());
                total_Bouquet_amount = String.valueOf(sum1);

                try {
                    boxBouquetDTO.setBouquet_ID(bouquetModelBouquet.get(i).getBouquet_ID());
                    boxBouquetDTO.setCable_Box_ID(0);
                    boxBouquetDTO.setBox_Bouquet_ID(0);
                    boxBouquetDTO.setPrice(bouquetModelBouquet.get(i).getPrice());
                    boxBouquetDTO.setTax_per(bouquetModelBouquet.get(i).getTax());
                    boxBouquetDTO.setTax(Double.valueOf(bouquetModelBouquet.get(i).getTax_Amount()));
                    boxBouquetDTO.setTotal_Price(Double.valueOf(bouquetModelBouquet.get(i).getPrice()));
                    boxBouquetDTO.setBouquet_Name(bouquetModelBouquet.get(i).getBouquet_Name());

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxBouquetList().getBoxBouquet().add(boxBouquetDTO);
            }
        }

        bouquetModela.setTotal_Bouquet(total_Bouquet_amount);


        finish();
    }
}