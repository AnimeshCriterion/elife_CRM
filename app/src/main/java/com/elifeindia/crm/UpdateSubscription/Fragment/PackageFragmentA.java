package com.elifeindia.crm.UpdateSubscription.Fragment;

import static com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity.bouquetModelBouquetUpdate;
import static com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity.bouquetModelaUpdateSubscription;
import static com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity.dataModel;
import static com.elifeindia.crm.adapters.generate_invoice_module.CableBoxDetailsBillShareAdapter.cable_Box_ID;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.UpdateSubscription.Adapter.BouquetSubscriptionListAdapterUpdate;
import com.elifeindia.crm.UpdateSubscription.AdapterCallbackForUpdate;
import com.elifeindia.crm.UpdateSubscription.RetrofitClientUpdateNew;
import com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.contract.activities.PackageListFragContract;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.presenter.fragment.PakcageListFragPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PackageFragmentA extends Fragment implements PackageListFragContract.View, AdapterCallbackForUpdate {

    RecyclerView rv_channel_list;
    PackageListFragContract.Presenter presenter;
  public  static   TextView txt_total_amountbouquet;
    Button btn_save;
    EditText edt_search;
    BouquetSubscriptionListAdapterUpdate bouquetSubscriptionListAdapter;
    AdapterCallbackForUpdate adapterCallback;
    String companyId, custId, cableBoxID, userId;

    public PackageFragmentA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_package_a, container, false);
        rv_channel_list = v.findViewById(R.id.rv_package_list);
        txt_total_amountbouquet = v.findViewById(R.id.txt_total_amount);
        btn_save = v.findViewById(R.id.btn_save);
        edt_search = v.findViewById(R.id.edt_search);

        adapterCallback = this;
        presenter = new PakcageListFragPresenter(this);
        presenter.start();


        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                bouquetSubscriptionListAdapter.getFilter().filter(s);
                bouquetSubscriptionListAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        companyId = SharedPrefsData.getString(getActivity(), Constants.CompanyID, Constants.PREF_NAME);
        custId = SharedPrefsData.getString(getActivity(), Constants.CustomerID, Constants.PREF_NAME);
        userId = SharedPrefsData.getString(getActivity(), Constants.UserId, Constants.PREF_NAME);
        cableBoxID = SharedPrefsData.getString(getActivity(), Constants.CableBoxID, Constants.PREF_NAME);

        presenter.loadBouquet(getActivity(), companyId, SharedPrefsData.getString(requireActivity(), Constants.CustomerID, Constants.PREF_NAME));


        return v;
    }


    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showBouquet(BouquetModel bouquetModel) {
        bouquetModelBouquetUpdate = bouquetModel.getBouquet();
        bouquetModelaUpdateSubscription = bouquetModel;
        txt_total_amountbouquet.setText(bouquetModel.getTotal_Bouquet());
        SharedPrefsData.putBool(getActivity(), Constants.isLoadBouquet, false, Constants.PREF_NAME);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rv_channel_list.setLayoutManager(mLayoutManager);
        bouquetSubscriptionListAdapter = new BouquetSubscriptionListAdapterUpdate(getActivity(), bouquetModelBouquetUpdate, bouquetModelBouquetUpdate, adapterCallback,cable_Box_ID);
        rv_channel_list.setAdapter(bouquetSubscriptionListAdapter);


    }


    @Override
    public void onClickCallback(View view, int position, String id, Object o) {
        BouquetModel.BouquetDTO bouquetDTO=(BouquetModel.BouquetDTO)o;
        if (id.equals("Add")) {
            AddCablePackage(bouquetDTO);
        } else if (id.equals("Remove")) {
         RemoveBouquestPackage(bouquetDTO);
        }
    }

    private void RemoveBouquestPackage(BouquetModel.BouquetDTO bouquetDTO) {
        Call<InsertPayment> call = RetrofitClientUpdateNew.getInstance().getApi().deleteCablePackage(userId,companyId,cableBoxID,bouquetDTO.getBouquet_ID(),"Bouquet","2020-12-09");
        call.enqueue(new Callback<InsertPayment>() {
            @Override
            public void onResponse(Call<InsertPayment> call, Response<InsertPayment> response) {
                if(response.isSuccessful()){
                    presenter.loadBouquet(getActivity(), companyId, SharedPrefsData.getString(requireActivity(), Constants.CustomerID, Constants.PREF_NAME));

                    Toast.makeText(requireActivity(), "Bouquet Deleted !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertPayment> call, Throwable t) {
                Toast.makeText(requireActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void AddCablePackage(BouquetModel.BouquetDTO bouquetDTO) {
        Call<InsertPayment> call = RetrofitClientUpdateNew.getInstance().getApi().insertCablePackage(userId,companyId,cableBoxID,bouquetDTO.getBouquet_ID(),"Bouquet","2020-12-09");
        call.enqueue(new Callback<InsertPayment>() {
            @Override
            public void onResponse(Call<InsertPayment> call, Response<InsertPayment> response) {
                if(response.isSuccessful()){
                    presenter.loadBouquet(getActivity(), companyId, SharedPrefsData.getString(requireActivity(), Constants.CustomerID, Constants.PREF_NAME));

                    Toast.makeText(requireActivity(), "Bouquet Added !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertPayment> call, Throwable t) {
                Toast.makeText(requireActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
