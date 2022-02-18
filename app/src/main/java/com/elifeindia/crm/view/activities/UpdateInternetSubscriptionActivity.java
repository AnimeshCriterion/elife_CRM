package com.elifeindia.crm.view.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.generate_invoice_module.InternetSuscriptionListAdapter;
import com.elifeindia.crm.contract.activities.InternetPackageListContract;
import com.elifeindia.crm.model.InternetBoxWithSubscription;
import com.elifeindia.crm.model.InternetPackageModel;
import com.elifeindia.crm.presenter.activities.InternetPackageListPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;

import java.util.LinkedList;
import java.util.List;

import static com.elifeindia.crm.adapters.generate_invoice_module.InternetSuscriptionListAdapter.internetPkgPrice;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.alacarteModelAlacarte;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.box_position;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.internetBoxWithSubscription;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.internetPackageModelInternet;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.internetPackageModela;

public class UpdateInternetSubscriptionActivity extends AppCompatActivity implements InternetPackageListContract.View, AdapterCallback {

    RecyclerView rv_pkg_list;
    InternetPackageListContract.Presenter presenter;
    TextView txt_total_amount;
    EditText edt_search;
    Button btn_save;
    public static String total_pkg_amount = "0";
    InternetSuscriptionListAdapter internetSuscriptionListAdapter;
    AdapterCallback adapterCallback;
    String companyId, custId, cableBoxID, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_internet_subscription);

        rv_pkg_list = findViewById(R.id.rv_pkg_list);
        txt_total_amount = findViewById(R.id.txt_total_amount);
        btn_save = findViewById(R.id.btn_save);
        edt_search = findViewById(R.id.edt_search);


        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                internetSuscriptionListAdapter.getFilter().filter(s);
                internetSuscriptionListAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        adapterCallback = this;
        presenter = new InternetPackageListPresenter(this);
        presenter.start();

        companyId = SharedPrefsData.getString(this, Constants.CompanyID, Constants.PREF_NAME);

        custId = SharedPrefsData.getString(this, Constants.CustomerID, Constants.PREF_NAME);

        userId = SharedPrefsData.getString(this, Constants.UserId, Constants.PREF_NAME);
        cableBoxID = SharedPrefsData.getString(this, Constants.InternetBoxID, Constants.PREF_NAME);

        boolean isLoadInternetPkg = SharedPrefsData.getBool(UpdateInternetSubscriptionActivity.this, Constants.isLoadInternetPkg, Constants.PREF_NAME);

        if (isLoadInternetPkg) {
            presenter.loadPackage(this, companyId, custId);

        } else {

            txt_total_amount.setText(String.valueOf(internetPackageModela.getTotal_Package()));

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv_pkg_list.setLayoutManager(mLayoutManager);
            internetSuscriptionListAdapter = new InternetSuscriptionListAdapter(this, internetPackageModelInternet, internetPackageModelInternet, adapterCallback);
            rv_pkg_list.setAdapter(internetSuscriptionListAdapter);

        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getBoxpackageList().getBoxpackage().clear();

                    total_pkg_amount = "0";
                    float sum = Float.parseFloat(total_pkg_amount);

                    for (int i = 0; i < internetPackageModelInternet.size(); i++) {


                        InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO.BoxpackageListDTO.BoxpackageDTO boxpackageDTO = new InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO.BoxpackageListDTO.BoxpackageDTO();
                        if (internetPackageModelInternet.get(i).isIs_select()) {

                            sum += Double.valueOf(internetPackageModelInternet.get(i).getPrice());
                            total_pkg_amount = String.valueOf(sum);

                            try {
                                boxpackageDTO.setPackage_ID(internetPackageModelInternet.get(i).getPackage_ID());
                                boxpackageDTO.setBox_Package_ID(0);
                                boxpackageDTO.setInternet_Box_ID(0);
                                boxpackageDTO.setPrice(internetPackageModelInternet.get(i).getPrice());
                                boxpackageDTO.setTax_Percent(0.0);
                                boxpackageDTO.setTax_Amount(Double.valueOf(internetPackageModelInternet.get(i).getTax_Amount()));
                                boxpackageDTO.setPrice(Double.valueOf(internetPackageModelInternet.get(i).getPrice()));
                                boxpackageDTO.setPackageX(internetPackageModelInternet.get(i).getPackageX());

                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getBoxpackageList().getBoxpackage().add(boxpackageDTO);

                        }
                    }
                    internetPackageModela.setTotal_Package(Double.parseDouble(total_pkg_amount));

                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getBoxpackageList().getBoxpackage().clear();

                    total_pkg_amount = "0";
                    float sum = Float.parseFloat(total_pkg_amount);

                    for (int i = 0; i < internetPackageModelInternet.size(); i++) {


                        InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO.BoxpackageListDTO.BoxpackageDTO boxpackageDTO = new InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO.BoxpackageListDTO.BoxpackageDTO();
                        if (internetPackageModelInternet.get(i).isIs_select()) {

                            sum += Double.valueOf(internetPackageModelInternet.get(i).getPrice());
                            total_pkg_amount = String.valueOf(sum);

                            try {
                                boxpackageDTO.setPackage_ID(internetPackageModelInternet.get(i).getPackage_ID());
                                boxpackageDTO.setBox_Package_ID(0);
                                boxpackageDTO.setInternet_Box_ID(0);
                                boxpackageDTO.setPrice(internetPackageModelInternet.get(i).getPrice());
                                boxpackageDTO.setTax_Percent(0.0);
                                boxpackageDTO.setTax_Amount(Double.valueOf(internetPackageModelInternet.get(i).getTax_Amount()));
                                boxpackageDTO.setPrice(Double.valueOf(internetPackageModelInternet.get(i).getPrice()));
                                boxpackageDTO.setPackageX(internetPackageModelInternet.get(i).getPackageX());

                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getBoxpackageList().getBoxpackage().add(boxpackageDTO);

                        }
                    }
                    internetPackageModela.setTotal_Package(Double.parseDouble(total_pkg_amount));

                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


    @Override
    public void onClickCallback(View view, int position, String id) {

        if (id.equals("Add")) {

            internetPackageModelInternet.get(position).setIs_select(true);

            List<Float> additionalQueAmounts = new LinkedList<>();
            additionalQueAmounts.add(Float.parseFloat(internetPkgPrice));
            float sum = Float.parseFloat(txt_total_amount.getText().toString());
            for (int i = 0; i < additionalQueAmounts.size(); i++)
                sum += additionalQueAmounts.get(i);
            total_pkg_amount = String.valueOf(sum);
            txt_total_amount.setText(total_pkg_amount);

        } else if (id.equals("Remove")) {

            internetPackageModelInternet.get(position).setIs_select(false);

            String s = txt_total_amount.getText().toString();
            float f = Float.parseFloat(s) - Float.parseFloat(internetPkgPrice);
            total_pkg_amount = String.valueOf(f);
            txt_total_amount.setText(total_pkg_amount);

        }

    }

    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showPackage(InternetPackageModel internetPackageModel) {

        internetPackageModelInternet = internetPackageModel.getPackages();
        internetPackageModela = internetPackageModel;

        List<InternetPackageModel.PackagesDTO> packagesDTOS = internetPackageModel.getPackages();

        txt_total_amount.setText(internetPackageModel.getTotal_Package() + "");

        SharedPrefsData.putBool(UpdateInternetSubscriptionActivity.this, Constants.isLoadInternetPkg, false, Constants.PREF_NAME);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_pkg_list.setLayoutManager(mLayoutManager);
        internetSuscriptionListAdapter = new InternetSuscriptionListAdapter(this, packagesDTOS, packagesDTOS, adapterCallback);
        rv_pkg_list.setAdapter(internetSuscriptionListAdapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {

            internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getBoxpackageList().getBoxpackage().clear();

            total_pkg_amount = "0";
            float sum = Float.parseFloat(total_pkg_amount);

            for (int i = 0; i < internetPackageModelInternet.size(); i++) {

                InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO.BoxpackageListDTO.BoxpackageDTO boxpackageDTO = new InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO.BoxpackageListDTO.BoxpackageDTO();
                if (internetPackageModelInternet.get(i).isIs_select()) {


                    sum += Double.valueOf(internetPackageModelInternet.get(i).getPrice());
                    total_pkg_amount = String.valueOf(sum);


                    try {
                        boxpackageDTO.setPackage_ID(internetPackageModelInternet.get(i).getPackage_ID());
                        boxpackageDTO.setBox_Package_ID(0);
                        boxpackageDTO.setInternet_Box_ID(0);
                        boxpackageDTO.setPrice(internetPackageModelInternet.get(i).getPrice());
                        boxpackageDTO.setTax_Percent(0.0);
                        boxpackageDTO.setTax_Amount(Double.valueOf(internetPackageModelInternet.get(i).getTax_Amount()));
                        boxpackageDTO.setPrice(Double.valueOf(internetPackageModelInternet.get(i).getPrice()));
                        boxpackageDTO.setPackageX(internetPackageModelInternet.get(i).getPackageX());

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    internetBoxWithSubscription.getInternetBoxwithSubscription().get(box_position).getBoxpackageList().getBoxpackage().add(boxpackageDTO);

                }
            }
            internetPackageModela.setTotal_Package(Double.parseDouble(total_pkg_amount));

            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
