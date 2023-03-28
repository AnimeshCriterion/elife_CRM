package com.elifeindia.crm.UpdateSubscription;

import static com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity.internetBoxWithSubscriptionUpdate;
import static com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity.internetPackageModelInternetUpdateSubscription;
import static com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity.internetPackageModelaUpdateSubscription;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.box_position;

import android.content.Intent;
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
import com.elifeindia.crm.UpdateSubscription.Adapter.InternetUpdateAdapter.InternetSuscriptionListAdapterUpdateSuscription;
import com.elifeindia.crm.contract.activities.InternetPackageListContract;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.InternetBoxWithSubscription;
import com.elifeindia.crm.model.InternetPackageModel;
import com.elifeindia.crm.presenter.activities.InternetPackageListPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.view.activities.CustomerListActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateINternetSubscriptionActivityForUpdateSubscription extends AppCompatActivity implements InternetPackageListContract.View, AdapterCallbackForUpdate {

    RecyclerView rv_pkg_list;
    InternetPackageListContract.Presenter presenter;
 public  static    TextView txt_total_amountForInternetSubscription;
    EditText edt_search;
    Button btn_save;
    public static String total_pkg_amount = "0";
    InternetSuscriptionListAdapterUpdateSuscription internetSuscriptionListAdapter;
    AdapterCallbackForUpdate adapterCallback;
    String companyId, custId, cableBoxID, userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_internet_subscription_for_update_subscription);
        rv_pkg_list = findViewById(R.id.rv_pkg_list);
        txt_total_amountForInternetSubscription = findViewById(R.id.txt_total_amount);
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

        boolean isLoadInternetPkg = SharedPrefsData.getBool(UpdateINternetSubscriptionActivityForUpdateSubscription.this, Constants.isLoadInternetPkg, Constants.PREF_NAME);

        if (isLoadInternetPkg) {
            presenter.loadPackage(this, companyId, custId);
        } else {

//            txt_total_amount.setText(String.valueOf(internetPackageModela.getTotal_Package()));
//
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//            rv_pkg_list.setLayoutManager(mLayoutManager);
//            internetSuscriptionListAdapter = new InternetSuscriptionListAdapterUpdateSuscription(this, internetPackageModelInternet, internetPackageModelInternet, adapterCallback);
//            rv_pkg_list.setAdapter(internetSuscriptionListAdapter);

        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    internetBoxWithSubscriptionUpdate.getInternetBoxwithSubscription().get(box_position).getBoxpackageList().getBoxpackage().clear();

                    total_pkg_amount = "0";
                    float sum = Float.parseFloat(total_pkg_amount);

                    for (int i = 0; i < internetPackageModelInternetUpdateSubscription.size(); i++) {


                        InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO.BoxpackageListDTO.BoxpackageDTO boxpackageDTO = new InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO.BoxpackageListDTO.BoxpackageDTO();
                        if (internetPackageModelInternetUpdateSubscription.get(i).isIs_select()) {

                            sum += Double.valueOf(internetPackageModelInternetUpdateSubscription.get(i).getPrice());
                            total_pkg_amount = String.valueOf(sum);

                            try {
                                boxpackageDTO.setPackage_ID(internetPackageModelInternetUpdateSubscription.get(i).getPackage_ID());
                                boxpackageDTO.setBox_Package_ID(0);
                                boxpackageDTO.setInternet_Box_ID(0);
                                boxpackageDTO.setPrice(internetPackageModelInternetUpdateSubscription.get(i).getPrice());
                                boxpackageDTO.setTax_Percent(0.0);
                                boxpackageDTO.setTax_Amount(Double.valueOf(internetPackageModelInternetUpdateSubscription.get(i).getTax_Amount()));
                                boxpackageDTO.setPrice(Double.valueOf(internetPackageModelInternetUpdateSubscription.get(i).getPrice()));
                                boxpackageDTO.setPackageX(internetPackageModelInternetUpdateSubscription.get(i).getPackageX());

                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            internetBoxWithSubscriptionUpdate.getInternetBoxwithSubscription().get(box_position).getBoxpackageList().getBoxpackage().add(boxpackageDTO);

                        }
                    }
                    internetPackageModelaUpdateSubscription.setTotal_Package(Double.parseDouble(total_pkg_amount));

                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(UpdateINternetSubscriptionActivityForUpdateSubscription.this, UpdateSubscriptionAtivity.class));

            }
        });

    }


    @Override
    public void init() {

    }



    @Override
    public void showError(String message) {

    }

    @Override
    public void showPackage(InternetPackageModel internetPackageModel) {

        internetPackageModelInternetUpdateSubscription = internetPackageModel.getPackages();
        internetPackageModelaUpdateSubscription = internetPackageModel;

        List<InternetPackageModel.PackagesDTO> packagesDTOS = internetPackageModel.getPackages();

        txt_total_amountForInternetSubscription.setText(internetPackageModel.getTotal_Package() + "");

        SharedPrefsData.putBool(UpdateINternetSubscriptionActivityForUpdateSubscription.this, Constants.isLoadInternetPkg, false, Constants.PREF_NAME);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_pkg_list.setLayoutManager(mLayoutManager);
        internetSuscriptionListAdapter = new InternetSuscriptionListAdapterUpdateSuscription(this, packagesDTOS, packagesDTOS, adapterCallback);
        rv_pkg_list.setAdapter(internetSuscriptionListAdapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(UpdateINternetSubscriptionActivityForUpdateSubscription.this, UpdateSubscriptionAtivity.class));


    }

    @Override
    public void onClickCallback(View view, int position, String id, Object o) {
        InternetPackageModel.PackagesDTO packagesDTO = (InternetPackageModel.PackagesDTO) o;
        if (id.equals("Add")) {

            Call<InsertPayment> call = RetrofitClientUpdateNew.getInstance().getApi().insert_InternetPackage(userId, companyId, cableBoxID, String.valueOf(packagesDTO.getPackage_ID()), "Pack", "2022-06-02");

            call.enqueue(new Callback<InsertPayment>() {
                @Override
                public void onResponse(Call<InsertPayment> call, Response<InsertPayment> response) {
                    if (response.isSuccessful()) {
                        presenter.loadPackage(UpdateINternetSubscriptionActivityForUpdateSubscription.this, companyId, custId);
                        Toast.makeText(UpdateINternetSubscriptionActivityForUpdateSubscription.this, "Internet Package Added !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateINternetSubscriptionActivityForUpdateSubscription.this, "try again !", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<InsertPayment> call, Throwable t) {
                    Toast.makeText(UpdateINternetSubscriptionActivityForUpdateSubscription.this, "error" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        } else if (id.equals("Remove")) {

            Call<InsertPayment> call = RetrofitClientUpdateNew.getInstance().getApi().delete_InternetPackage(userId, companyId, cableBoxID, String.valueOf(packagesDTO.getPackage_ID()), "Pack", "2022-06-02");
            call.enqueue(new Callback<InsertPayment>() {
                @Override
                public void onResponse(Call<InsertPayment> call, Response<InsertPayment> response) {
        if(response.isSuccessful()){
            presenter.loadPackage(UpdateINternetSubscriptionActivityForUpdateSubscription.this, companyId, custId);

            Toast.makeText(UpdateINternetSubscriptionActivityForUpdateSubscription.this, "Deleted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(UpdateINternetSubscriptionActivityForUpdateSubscription.this, "try again !", Toast.LENGTH_SHORT).show();
        }
                }

                @Override
                public void onFailure(Call<InsertPayment> call, Throwable t) {
                    Toast.makeText(UpdateINternetSubscriptionActivityForUpdateSubscription.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
