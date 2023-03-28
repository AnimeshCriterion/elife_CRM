package com.elifeindia.crm.UpdateSubscription.Fragment;

import static com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity.alacarteModelAlacarteUpdateforSubscription;
import static com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity.alacarteModelaUpdateSubscription;
import static com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionAtivity.dataModel;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.alacarteModelAlacarte;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.alacarteModela;

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

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.UpdateSubscription.Adapter.AlacarteSubscriptionListAdapterUpdate;
import com.elifeindia.crm.UpdateSubscription.AdapterCallbackForUpdate;
import com.elifeindia.crm.UpdateSubscription.RetrofitClientUpdateNew;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.contract.activities.AddOnFragmentContract;
import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.presenter.fragment.AddOnFragmentPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlcarteFragementA extends Fragment implements AddOnFragmentContract.View, AdapterCallbackForUpdate {

    RecyclerView rv_channel_list; EditText edt_search;
    AddOnFragmentContract.Presenter presenter;
    public  static TextView txt_total_amountupdate;
    AlacarteSubscriptionListAdapterUpdate alacarteSubscriptionListAdapter;
    AdapterCallbackForUpdate adapterCallback; String companyId, userId, custId, cableBoxID;

    Button btn_save;

    public AlcarteFragementA() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_alcarte_fragement_a, container, false);
        rv_channel_list = v.findViewById(R.id.rv_channel_list);
        txt_total_amountupdate =v.findViewById(R.id.txt_total_amount);
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


            presenter.loadAlacarte(getActivity(), companyId, SharedPrefsData.getString(requireActivity(), Constants.CustomerID, Constants.PREF_NAME));


//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
//                rv_channel_list.setLayoutManager(mLayoutManager);
//                alacarteSubscriptionListAdapter = new AlacarteSubscriptionListAdapterUpdate(getActivity(), alacarteModelAlacarte, alacarteModelAlacarte, adapterCallback);
//                rv_channel_list.setAdapter(alacarteSubscriptionListAdapter);


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

        alacarteModelAlacarteUpdateforSubscription = alacarteModel.getAlacarte();
//        txt_total_amountupdate.setText(alacarteModelaUpdateSubscription.getTotal_Alacarte());

        alacarteModelAlacarte = alacarteModel.getAlacarte();

        alacarteModelaUpdateSubscription = alacarteModel;

        txt_total_amountupdate.setText(alacarteModelaUpdateSubscription.getTotal_Alacarte());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rv_channel_list.setLayoutManager(mLayoutManager);
        alacarteSubscriptionListAdapter = new AlacarteSubscriptionListAdapterUpdate(getActivity(), alacarteModelAlacarteUpdateforSubscription, alacarteModelAlacarteUpdateforSubscription, adapterCallback);
        rv_channel_list.setAdapter(alacarteSubscriptionListAdapter);

    }


    @Override
    public void onClickCallback(View view, int position, String id, Object o) {
        AlacarteModel.AlacarteDTO alacarteDTO=(AlacarteModel.AlacarteDTO)o;
        if(id.equals("Add")){
   addAlacartePackage(alacarteDTO);
        }else if(id.equals("Remove")){
removeAlacartePackage(alacarteDTO);

        }
    }

    private void removeAlacartePackage(AlacarteModel.AlacarteDTO alacarteDTO) {
        Call<InsertPayment> call = RetrofitClientUpdateNew.getInstance().getApi().deleteCablePackage(userId,companyId,cableBoxID,alacarteDTO.getAlacarte_ID(),"Alacarte","2020-12-09");
        call.enqueue(new Callback<InsertPayment>() {
            @Override
            public void onResponse(Call<InsertPayment> call, Response<InsertPayment> response) {
                if(response.isSuccessful()){
                    presenter.loadAlacarte(getActivity(), companyId,SharedPrefsData.getString(requireActivity(), Constants.CustomerID, Constants.PREF_NAME));
                    Toast.makeText(requireActivity(), "Alacarte Deleted !", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<InsertPayment> call, Throwable t) {
                Toast.makeText(requireActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void addAlacartePackage(AlacarteModel.AlacarteDTO alacarteDTO) {
        Call<InsertPayment> call = RetrofitClientUpdateNew.getInstance().getApi().insertCablePackage(userId,companyId,cableBoxID,alacarteDTO.getAlacarte_ID(),"Alacarte","2020-12-09");
        call.enqueue(new Callback<InsertPayment>() {
            @Override
            public void onResponse(Call<InsertPayment> call, Response<InsertPayment> response) {
                if(response.isSuccessful()){
                    presenter.loadAlacarte(getActivity(), companyId, SharedPrefsData.getString(requireActivity(), Constants.CustomerID, Constants.PREF_NAME));
                    Toast.makeText(requireActivity(), "Alacarte Added !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertPayment> call, Throwable t) {
                Toast.makeText(requireActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
