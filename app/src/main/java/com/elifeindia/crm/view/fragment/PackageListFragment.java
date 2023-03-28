package com.elifeindia.crm.view.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.BouquetSubscriptionListAdapter;
import com.elifeindia.crm.contract.activities.PackageListFragContract;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.presenter.fragment.PakcageListFragPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;

import java.util.LinkedList;
import java.util.List;

import static com.elifeindia.crm.adapters.BouquetSubscriptionListAdapter.pkgPrice;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.bouquetModelBouquet;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.bouquetModela;
import static com.elifeindia.crm.view.activities.UpdateCableSubscriptionActivity.total_Bouquet_amount;

public class PackageListFragment extends Fragment implements PackageListFragContract.View, AdapterCallback {

    RecyclerView rv_channel_list;
    PackageListFragContract.Presenter presenter;
    TextView txt_total_amount;
    Button btn_save;
    EditText edt_search;
    BouquetSubscriptionListAdapter bouquetSubscriptionListAdapter;
    AdapterCallback adapterCallback;
    String companyId, custId, cableBoxID, userId;

    public PackageListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_channel_list, container, false);

        rv_channel_list = v.findViewById(R.id.rv_package_list);
        txt_total_amount = v.findViewById(R.id.txt_total_amount);
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

        boolean isLoadBouquet = SharedPrefsData.getBool(getActivity(), Constants.isLoadBouquet, Constants.PREF_NAME);

        //Toast.makeText(getActivity(), ""+isLoadBouquet, Toast.LENGTH_SHORT).show();
        presenter.loadBouquet(getActivity(), companyId, custId);
        if (isLoadBouquet) {
            presenter.loadBouquet(getActivity(), companyId, custId);
        } else {
            try {
                txt_total_amount.setText(bouquetModela.getTotal_Bouquet());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                rv_channel_list.setLayoutManager(mLayoutManager);
                bouquetSubscriptionListAdapter = new BouquetSubscriptionListAdapter(getActivity(), bouquetModelBouquet, bouquetModelBouquet, adapterCallback);
                rv_channel_list.setAdapter(bouquetSubscriptionListAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return v;
    }


    @Override
    public void onClickCallback(View view, int position, String id) {

        if (id.equals("Add")) {

            bouquetModelBouquet.get(position).setIs_select(true);

            List<Float> additionalQueAmounts = new LinkedList<>();
            additionalQueAmounts.add(Float.parseFloat(pkgPrice));

            double sum = Double.parseDouble(txt_total_amount.getText().toString());

            for (int i = 0; i < additionalQueAmounts.size(); i++)
                sum += additionalQueAmounts.get(i);
            total_Bouquet_amount = String.valueOf(sum);
            txt_total_amount.setText(total_Bouquet_amount);

        } else if (id.equals("Remove")) {

            bouquetModelBouquet.get(position).setIs_select(false);

            String s = txt_total_amount.getText().toString();
            Log.i("amount", s);

            float f = Float.parseFloat(s) - Float.parseFloat(pkgPrice);

            total_Bouquet_amount = String.valueOf(f);

            try {
                bouquetModela.setTotal_Bouquet(total_Bouquet_amount);
            } catch (Exception e) {
                e.printStackTrace();
            }
            txt_total_amount.setText(total_Bouquet_amount);

        }

    }

    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showBouquet(BouquetModel bouquetModel) {
        bouquetModelBouquet = bouquetModel.getBouquet();
        bouquetModela = bouquetModel;
        txt_total_amount.setText(bouquetModel.getTotal_Bouquet());
        SharedPrefsData.putBool(getActivity(), Constants.isLoadBouquet, false, Constants.PREF_NAME);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rv_channel_list.setLayoutManager(mLayoutManager);
        bouquetSubscriptionListAdapter = new BouquetSubscriptionListAdapter(getActivity(), bouquetModelBouquet, bouquetModelBouquet, adapterCallback);
        rv_channel_list.setAdapter(bouquetSubscriptionListAdapter);

    }


}
