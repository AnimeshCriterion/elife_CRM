package com.elifeindia.crm.UpdateSubscription.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.AdapterCallbackTextView;
import com.elifeindia.crm.model.CableBoxWithSubscription;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.activities.GenerateInvoiceActivity;
import com.elifeindia.crm.UpdateSubscription.UpdateSubscriptionPackageA;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CableBoxAdapterForSubScriptionUpdate extends RecyclerView.Adapter<CableBoxAdapterForSubScriptionUpdate.MyViewHolder> {
    Context context;
    public static String cable_Box_IDupdate, box_IDupdate = "", boxType_IDupdatecable, vcnoupdateCable, stbnoupdateCable, cafnoupdateCable, strBillTypeupdate, bill_Type_IDupdate, connection_Status_IDupdate, activation_Dateupdate, expiry_Dateupdate, noofMonthupdate, noofDaysupdate, alacarte_Amountupdate, bouquet_Amountupdate, tax_Amountupdate, box_Amountupdate;
    public  List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO> cableBoxListupdate;
    List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO> alacareteDTOS;
    AdapterCallbackTextView adapterCallback;
    AdapterCallback adapterCall;
    AlacarteListAdapterUpdate alacarteListAdapter;
    BouquetListAdapterUpdate bouquetListAdapter;
    Calendar mcurrentDate;
    String s;
    Activity activity;
    private double total = 0.0;


    GenerateInvoiceActivity generateInvoiceActivity;

    public CableBoxAdapterForSubScriptionUpdate(Context context, List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO> cableBoxList, AdapterCallbackTextView adapterCallback, AdapterCallback adapterCall, String a, Activity activity) {
        this.context = context;
        this.cableBoxListupdate = cableBoxList;
        this.adapterCallback = adapterCallback;
        this.adapterCall = adapterCall;
        this.s = a;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CableBoxAdapterForSubScriptionUpdate.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_box_details_update, parent, false);
        return new CableBoxAdapterForSubScriptionUpdate.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CableBoxAdapterForSubScriptionUpdate.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.cable_status.setText("Status : " + cableBoxListupdate.get(position).getCableBox().getStatus_Name());
        Log.d("TAG", "onBindViewHolderAnimesh: " + cableBoxListupdate.get(position).getCableBox().getActivation_Date().toString());
        holder.txt_activation_dateupdate.setText(cableBoxListupdate.get(position).getCableBox().getActivation_Date());
        try {
            holder.cable_lpamount.setText("LP Amount : " + cableBoxListupdate.get(position).getCableBox().getPaid_Amount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String lpdate = null;
        try {
            lpdate = ViewUtils.changeDateFormat(cableBoxListupdate.get(position).getCableBox().getPayment_Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.cable_lpdate.setText("LP Date : " + lpdate);
        if (cableBoxListupdate.get(position).getCableBox().getStatus_Name().equals("Active")) {
            holder.cb_box_status.setChecked(true);
        } else {
            holder.cb_box_status.setChecked(false);
        }
        holder.cb_box_status.setOnClickListener(view -> {
            box_Amountupdate = String.valueOf(cableBoxListupdate.get(position).getCableBox().getBox_Amount());
            if (holder.cb_box_status.isChecked()) {
                adapterCallback.onClickCallback(holder.txt_expiry_dateupdate, holder.amountUpdateCableBox, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "ADD");
            } else {
                adapterCallback.onClickCallback(holder.txt_expiry_dateupdate, holder.amountUpdateCableBox, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "SUB");
            }


        });
        holder.stbno.setText(cableBoxListupdate.get(position).getCableBox().getStbno());
        holder.cafnum.setText(cableBoxListupdate.get(position).getCableBox().getCafno());
        holder.vcnum.setText(cableBoxListupdate.get(position).getCableBox().getVcno());

        holder.vcnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                vcnoupdateCable = holder.vcnum.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        holder.stbno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                stbnoupdateCable = holder.stbno.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        holder.cafnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cafnoupdateCable = holder.cafnum.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        String date = null;
        //   try {
        date = ViewUtils.changeDateFormat(cableBoxListupdate.get(position).getCableBox().getExpiry_Date());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        holder.expirtydateUpdate.setText(date);
        holder.amountUpdateCableBox.setText(cableBoxListupdate.get(position).getCableBox().getBox_Amount().toString());
        if (cableBoxListupdate.get(position).getBoxAlacareteList().getBoxAlacarete().size() == 0 && cableBoxListupdate.get(position).getBoxBouquetList().getBoxBouquet().size() == 0) {
            holder.amountUpdateCableBox.setEnabled(true);
            holder.ll_subscription.setVisibility(View.GONE);

        } else {
            holder.amountUpdateCableBox.setEnabled(false);
            holder.ll_subscription.setVisibility(View.VISIBLE);
        }

        holder.amountUpdateCableBox.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                box_Amountupdate = holder.amountUpdateCableBox.getText().toString();
//                    generateInvoiceActivity.txt_subscription_amnt.setText(box_Amount);
//
//                    float prevBal = Float.parseFloat(generateInvoiceActivity.txt_prev_bal.getText().toString());
//                    float newAmount = Float.parseFloat(generateInvoiceActivity.txt_subscription_amnt.getText().toString());
//
//                    generateInvoiceActivity.txt_total_amount.setText(String.valueOf(newAmount + prevBal));
//
//                    generateInvoiceActivity.edt_payingamount.setText(generateInvoiceActivity.txt_total_amount.getText().toString());
//
//                    generateInvoiceActivity.txt_new_bal.setText("0.0");

                adapterCallback.onClickCallback(holder.txt_expiry_dateupdate, holder.amountUpdateCableBox, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "strBoxAmount");

                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                return true;
            }
            return false;
        });


        try {
            activation_Dateupdate = cableBoxListupdate.get(position).getCableBox().getActivation_Date().substring(0, 10);
            expiry_Dateupdate = cableBoxListupdate.get(position).getCableBox().getExpiry_Date().substring(0, 10);
            cable_Box_IDupdate = cableBoxListupdate.get(position).getCableBox().getCable_Box_ID().toString();
//                                box_ID = cableBoxList.get(position).getCableBox().getBox_ID().toString();
            boxType_IDupdatecable = cableBoxListupdate.get(position).getCableBox().getBoxType_ID().toString();
            vcnoupdateCable = cableBoxListupdate.get(position).getCableBox().getVcno().toString();
            stbnoupdateCable = cableBoxListupdate.get(position).getCableBox().getStbno().toString();
            cafnoupdateCable = cableBoxListupdate.get(position).getCableBox().getCafno().toString();
            bill_Type_IDupdate = cableBoxListupdate.get(position).getCableBox().getBill_Type_ID().toString();
            strBillTypeupdate = cableBoxListupdate.get(position).getCableBox().getBill_Type().toString();
            connection_Status_IDupdate = cableBoxListupdate.get(position).getCableBox().getConnection_Status_ID().toString();
            noofMonthupdate = cableBoxListupdate.get(position).getCableBox().getNoofMonth().toString();
//                                alacarte_Amount = cableBoxList.get(position).getCableBox().getAlacarte_Amount().toString();
//                                bouquet_Amount = cableBoxList.get(position).getCableBox().getBouquet_Amount().toString();
//
            box_Amountupdate = cableBoxListupdate.get(position).getCableBox().getBox_Amount().toString();

            adapterCallback.onClickCallback(holder.txt_expiry_dateupdate, holder.txt_activation_dateupdate, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "calculateExpiry");

            holder.txt_activation_dateupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    mcurrentDate = Calendar.getInstance();
                    final int mYear = mcurrentDate.get(Calendar.YEAR);
                    final int mMonth = mcurrentDate.get(Calendar.MONTH);
                    final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker = new DatePickerDialog(
                            context, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                            mcurrentDate.set(Calendar.YEAR, selectedyear);
                            mcurrentDate.set(Calendar.MONTH, selectedmonth);
                            mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.US);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

                            holder.txt_activation_dateupdate.setText(sdf.format(mcurrentDate.getTime()));

                            try {

                                activation_Dateupdate = sdf2.format(mcurrentDate.getTime());
                                box_Amountupdate = cableBoxListupdate.get(position).getCableBox().getBox_Amount().toString();
                                adapterCallback.onClickCallback(holder.txt_expiry_dateupdate, holder.amountUpdateCableBox, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "strActivationDate");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }, mYear, mMonth, mDay);
                    // mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    mDatePicker.setTitle("Select Activation Date");
                    mDatePicker.show();
                }

            });

            holder.spn_no_of_months.setText(cableBoxListupdate.get(position).getCableBox().getNoofMonth().toString());
            holder.spn_no_of_months.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    noofMonthupdate = cableBoxListupdate.get(position).getCableBox().getNoofMonth().toString();
                    adapterCallback.onClickCallback(holder.txt_expiry_dateupdate, holder.amountUpdateCableBox, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "spn_no_of_months");

                }
            });
            holder.spn_bill_type.setText(cableBoxListupdate.get(position).getCableBox().getBill_Type());

            holder.spn_bill_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bill_Type_IDupdate = cableBoxListupdate.get(position).getCableBox().getBill_Type_ID().toString();
                    adapterCallback.onClickCallback(holder.txt_expiry_dateupdate, holder.amountUpdateCableBox, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "spn_bill_type");

                }
            });
            holder.spn_no_of_days.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        noofDaysupdate = holder.spn_no_of_days.getText().toString();
                        adapterCallback.onClickCallback(holder.txt_expiry_dateupdate, holder.amountUpdateCableBox, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "strNoOfDays");

                        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.rv_alacarte_list.setLayoutManager(mLayoutManager);
        alacarteListAdapter = new AlacarteListAdapterUpdate(context, cableBoxListupdate.get(position).getBoxAlacareteList().getBoxAlacarete(), adapterCall);
        holder.rv_alacarte_list.setAdapter(alacarteListAdapter);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.rv_bouquet_list.setLayoutManager(mLayoutManager1);
        bouquetListAdapter = new BouquetListAdapterUpdate(context, cableBoxListupdate.get(position).getBoxBouquetList().getBoxBouquet(), adapterCall);
        holder.rv_bouquet_list.setAdapter(bouquetListAdapter);

        holder.txt_edit_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s.equals("A")) {
                    try {
                        String boxId = cableBoxListupdate.get(position).getCableBox().getCable_Box_ID().toString();
                        SharedPrefsData.putString(context, Constants.CableBoxID, boxId, Constants.PREF_NAME);
                        Intent in = new Intent(activity, UpdateSubscriptionPackageA.class);
                        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(in);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String boxId = cableBoxListupdate.get(position).getCableBox().getCable_Box_ID().toString();
                        SharedPrefsData.putString(context, Constants.CableBoxID, boxId, Constants.PREF_NAME);
                        context.startActivity(new Intent(context, UpdateSubscriptionPackageA.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return cableBoxListupdate.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_edit_box, expirtydateUpdate, cable_lpamount, cable_lpdate, cable_status;
        EditText spn_no_of_days, vcnum, stbno, cafnum;
        public static EditText amountUpdateCableBox;
        LinearLayout ll_noofdays, ll_cable, ll_subscription;
        RecyclerView rv_bouquet_list, rv_alacarte_list;
        CheckBox cb_box_status;
        TextView txt_activation_dateupdate, txt_expiry_dateupdate;
        TextView spn_bill_type, spn_no_of_months;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cb_box_status = itemView.findViewById(R.id.cb_box_status);
            cable_status = itemView.findViewById(R.id.cable_status);
            cable_lpdate = itemView.findViewById(R.id.cable_lpdate);
            cable_lpamount = itemView.findViewById(R.id.cable_lpamount);
            ll_noofdays = itemView.findViewById(R.id.ll_noofdays);
            ll_cable = itemView.findViewById(R.id.ll_cable);
            vcnum = itemView.findViewById(R.id.vcnum);
            stbno = itemView.findViewById(R.id.stbno);
            cafnum = itemView.findViewById(R.id.cafnum);
            expirtydateUpdate = itemView.findViewById(R.id.expirtydate);
            amountUpdateCableBox = itemView.findViewById(R.id.amount);
            ll_subscription = itemView.findViewById(R.id.ll_subscription);
            rv_alacarte_list = itemView.findViewById(R.id.rv_alacarte_list);
            rv_bouquet_list = itemView.findViewById(R.id.rv_bouquet_list);
            txt_edit_box = itemView.findViewById(R.id.txt_edit_box);
            spn_bill_type = itemView.findViewById(R.id.spn_bill_type);
            spn_no_of_months = itemView.findViewById(R.id.spn_no_of_months);
            txt_activation_dateupdate = itemView.findViewById(R.id.txt_activation_dateUpdateSubscription);
            txt_expiry_dateupdate = itemView.findViewById(R.id.txt_expiry_date);
            spn_no_of_days = itemView.findViewById(R.id.spn_no_of_days);


        }
    }



}

