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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.AlacarteSubscriptionListAdapter;
import com.elifeindia.crm.contract.activities.AddOnFragmentContract;
import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.presenter.fragment.AddOnFragmentPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;

import java.util.LinkedList;
import java.util.List;

import static com.elifeindia.crm.adapters.AlacarteSubscriptionListAdapter.pkgPrice;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.alacarteModelAlacarte;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.alacarteModela;
import static com.elifeindia.crm.view.activities.UpdateCableSubscriptionActivity.total_alacarte_amount;

public class AlacarteListFragment extends Fragment implements AddOnFragmentContract.View, AdapterCallback {

    RecyclerView rv_channel_list; EditText edt_search;
    AddOnFragmentContract.Presenter presenter; TextView txt_total_amount;
    AlacarteSubscriptionListAdapter alacarteSubscriptionListAdapter;
    AdapterCallback adapterCallback; String companyId, userId, custId, cableBoxID;

    Button btn_save;

    public AlacarteListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_on, container, false);
        rv_channel_list = v.findViewById(R.id.rv_channel_list);
        txt_total_amount =v.findViewById(R.id.txt_total_amount);
        edt_search = v.findViewById(R.id.edt_search);


        adapterCallback=this;
        presenter = new AddOnFragmentPresenter(this);
        presenter.start();


        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                alacarteSubscriptionListAdapter.getFilter().filter(s);
                alacarteSubscriptionListAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        companyId = SharedPrefsData.getString(getActivity(), Constants.CompanyID, Constants.PREF_NAME);
        custId = SharedPrefsData.getString(getActivity(), Constants.CustomerID, Constants.PREF_NAME);
        userId = SharedPrefsData.getString(getActivity(), Constants.UserId, Constants.PREF_NAME);
        cableBoxID = SharedPrefsData.getString(getActivity(), Constants.CableBoxID, Constants.PREF_NAME);

        boolean isLoadAlacarte = SharedPrefsData.getBool(getActivity(), Constants.isLoadAlacarte, Constants.PREF_NAME);

        if(isLoadAlacarte){
            presenter.loadAlacarte(getActivity(), companyId,custId);

        }else {

            txt_total_amount.setText(alacarteModela.getTotal_Alacarte());

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            rv_channel_list.setLayoutManager(mLayoutManager);
            alacarteSubscriptionListAdapter = new AlacarteSubscriptionListAdapter(getActivity(), alacarteModelAlacarte, alacarteModelAlacarte, adapterCallback);
            rv_channel_list.setAdapter(alacarteSubscriptionListAdapter);
        }

        return v;
    }


    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {

        Log.i("exception", message);
    }

    @Override
    public void showAlacarte(AlacarteModel alacarteModel) {

        alacarteModelAlacarte = alacarteModel.getAlacarte();

        alacarteModela = alacarteModel;

        txt_total_amount.setText(alacarteModela.getTotal_Alacarte());
        SharedPrefsData.putBool(getActivity(), Constants.isLoadAlacarte, false, Constants.PREF_NAME);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rv_channel_list.setLayoutManager(mLayoutManager);
        alacarteSubscriptionListAdapter = new AlacarteSubscriptionListAdapter(getActivity(), alacarteModelAlacarte, alacarteModelAlacarte, adapterCallback);
        rv_channel_list.setAdapter(alacarteSubscriptionListAdapter);


    }


    @Override
    public void onClickCallback(View view, int position, String id) {
        if(id.equals("Add")){

            alacarteModelAlacarte.get(position).setIs_select(true);

            List<Float> additionalQueAmounts = new LinkedList<>();
            additionalQueAmounts.add(Float.parseFloat(pkgPrice));

            Log.i("pkgPrice", pkgPrice);
            float sum = Float.parseFloat(txt_total_amount.getText().toString());
            for(int i = 0; i < additionalQueAmounts.size(); i++)
                sum += additionalQueAmounts.get(i);
            total_alacarte_amount = String.valueOf(sum);

            if(total_alacarte_amount.contains("E") || total_alacarte_amount.contains("e")){
                total_alacarte_amount = "0.0";
            }

            Log.i("sum", String.valueOf(sum));
            txt_total_amount.setText(total_alacarte_amount);
            Log.i("total_alacarte_amount", total_alacarte_amount);

        }else if(id.equals("Remove")){



            alacarteModelAlacarte.get(position).setIs_select(false);

            String s = txt_total_amount.getText().toString();

            float f = Float.parseFloat(s)-Float.parseFloat(pkgPrice);

            total_alacarte_amount = String.valueOf(f);

            if(total_alacarte_amount.contains("E") || total_alacarte_amount.contains("e")){
                total_alacarte_amount = "0.0";
            }

            txt_total_amount.setText(total_alacarte_amount);

            Log.i("s", s);
            Log.i("f", String.valueOf(f));
            Log.i("total_alacarte_amount", total_alacarte_amount);

        }
    }

}
