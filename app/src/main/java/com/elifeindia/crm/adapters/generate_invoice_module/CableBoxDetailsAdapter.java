package com.elifeindia.crm.adapters.generate_invoice_module;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import com.elifeindia.crm.view.activities.LoginActivity;
import com.elifeindia.crm.view.activities.UpdateCableSubscriptionActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class CableBoxDetailsAdapter extends RecyclerView.Adapter<CableBoxDetailsAdapter.MyViewHolder> {
    Context context;
    public static String cable_Box_ID, box_ID = "", boxType_ID, vcno, stbno, cafno, strBillType, bill_Type_ID, connection_Status_ID, activation_Date, expiry_Date, noofMonth, noofDays, alacarte_Amount, bouquet_Amount, tax_Amount, box_Amount;
    public static List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO> cableBoxList;
    List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO> alacareteDTOS;
    AdapterCallbackTextView adapterCallback;
    AdapterCallback adapterCall;
    AlacarteListAdapter alacarteListAdapter;
    BouquetListAdapter bouquetListAdapter;
    Calendar mcurrentDate;

    GenerateInvoiceActivity generateInvoiceActivity;

    public CableBoxDetailsAdapter(Context context, List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO> cableBoxList, AdapterCallbackTextView adapterCallback, AdapterCallback adapterCall) {
        this.context = context;
        this.cableBoxList = cableBoxList;
        this.adapterCallback = adapterCallback;
        this.adapterCall = adapterCall;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cable_box_details, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.cable_status.setText("Status : " + cableBoxList.get(position).getCableBox().getStatus_Name());
        try {

            holder.cable_lpamount.setText("LP Amount : " + cableBoxList.get(position).getCableBox().getPaid_Amount().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String lpdate = null;
        try {
            lpdate = ViewUtils.changeDateFormat(cableBoxList.get(position).getCableBox().getPayment_Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.cable_lpdate.setText("LP Date : " + lpdate);

        if (cableBoxList.get(position).getCableBox().getStatus_Name().equals("Active")) {
            holder.cb_box_status.setChecked(true);

        } else {
            holder.cb_box_status.setChecked(false);

        }
        holder.cb_box_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                box_Amount = String.valueOf(cableBoxList.get(position).getCableBox().getBox_Amount());

                if (holder.cb_box_status.isChecked()) {
                    adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amount, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "ADD");

                } else {
                    adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amount, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "SUB");

                }


            }
        });
        holder.stbno.setText(cableBoxList.get(position).getCableBox().getStbno().toString());
        holder.cafnum.setText(cableBoxList.get(position).getCableBox().getCafno().toString());
        holder.vcnum.setText(cableBoxList.get(position).getCableBox().getVcno().toString());
        String date = null;
        try {

            date = ViewUtils.changeDateFormat(cableBoxList.get(position).getCableBox().getExpiry_Date().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.expirtydate.setText(date);

        holder.amount.setText(cableBoxList.get(position).getCableBox().getBox_Amount().toString());
        if (cableBoxList.get(position).getBoxAlacareteList().getBoxAlacarete().size() == 0 && cableBoxList.get(position).getBoxBouquetList().getBoxBouquet().size() == 0) {

            holder.amount.setEnabled(true);
            holder.ll_subscription.setVisibility(View.GONE);

        } else {
            holder.amount.setEnabled(false);
            holder.ll_subscription.setVisibility(View.VISIBLE);
        }
        holder.amount.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    box_Amount = holder.amount.getText().toString();

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

                    adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amount, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "strBoxAmount");

                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });


        try {
            activation_Date = cableBoxList.get(position).getCableBox().getActivation_Date().substring(0, 10);
            expiry_Date = cableBoxList.get(position).getCableBox().getExpiry_Date().substring(0, 10);
            cable_Box_ID = cableBoxList.get(position).getCableBox().getCable_Box_ID().toString();
//                                box_ID = cableBoxList.get(position).getCableBox().getBox_ID().toString();
            boxType_ID = cableBoxList.get(position).getCableBox().getBoxType_ID().toString();
            vcno = cableBoxList.get(position).getCableBox().getVcno().toString();
            stbno = cableBoxList.get(position).getCableBox().getStbno().toString();
            cafno = cableBoxList.get(position).getCableBox().getCafno().toString();
            bill_Type_ID = cableBoxList.get(position).getCableBox().getBill_Type_ID().toString();
            strBillType = cableBoxList.get(position).getCableBox().getBill_Type().toString();
            connection_Status_ID = cableBoxList.get(position).getCableBox().getConnection_Status_ID().toString();
            noofMonth = cableBoxList.get(position).getCableBox().getNoofMonth().toString();
//                                alacarte_Amount = cableBoxList.get(position).getCableBox().getAlacarte_Amount().toString();
//                                bouquet_Amount = cableBoxList.get(position).getCableBox().getBouquet_Amount().toString();
//
            box_Amount = cableBoxList.get(position).getCableBox().getBox_Amount().toString();

            adapterCallback.onClickCallback(holder.txt_expiry_date, holder.txt_activation_date, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "calculateExpiry");

            holder.txt_activation_date.setOnClickListener(new View.OnClickListener() {
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

                            holder.txt_activation_date.setText(sdf.format(mcurrentDate.getTime()));

                            try {

                                activation_Date = sdf2.format(mcurrentDate.getTime());
                                box_Amount = cableBoxList.get(position).getCableBox().getBox_Amount().toString();
                                adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amount, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "strActivationDate");
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

            holder.spn_no_of_months.setText(cableBoxList.get(position).getCableBox().getNoofMonth().toString());
            holder.spn_no_of_months.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    noofMonth = cableBoxList.get(position).getCableBox().getNoofMonth().toString();
                    adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amount, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "spn_no_of_months");

                }
            });
            holder.spn_bill_type.setText(cableBoxList.get(position).getCableBox().getBill_Type());

            holder.spn_bill_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bill_Type_ID = cableBoxList.get(position).getCableBox().getBill_Type_ID().toString();
                    adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amount, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "spn_bill_type");

                }
            });
            holder.spn_no_of_days.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {

                        noofDays = holder.spn_no_of_days.getText().toString();
                        adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amount, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "strNoOfDays");

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
        alacarteListAdapter = new AlacarteListAdapter(context, cableBoxList.get(position).getBoxAlacareteList().getBoxAlacarete(), adapterCall);
        holder.rv_alacarte_list.setAdapter(alacarteListAdapter);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.rv_bouquet_list.setLayoutManager(mLayoutManager1);
        bouquetListAdapter = new BouquetListAdapter(context, cableBoxList.get(position).getBoxBouquetList().getBoxBouquet(), adapterCall);
        holder.rv_bouquet_list.setAdapter(bouquetListAdapter);

        holder.txt_edit_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String boxId = cableBoxList.get(position).getCableBox().getCable_Box_ID().toString();
                SharedPrefsData.putString(context, Constants.CableBoxID, boxId, Constants.PREF_NAME);

                context.startActivity(new Intent(context, UpdateCableSubscriptionActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return cableBoxList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_edit_box, vcnum, stbno, cafnum, expirtydate, cable_lpamount, cable_lpdate, cable_status;
        EditText spn_no_of_days;
        public static EditText amount;
        LinearLayout ll_noofdays, ll_cable, ll_subscription;
        RecyclerView rv_bouquet_list, rv_alacarte_list;
        CheckBox cb_box_status;
        TextView txt_activation_date, txt_expiry_date;
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
            expirtydate = itemView.findViewById(R.id.expirtydate);
            amount = itemView.findViewById(R.id.amount);
            ll_subscription = itemView.findViewById(R.id.ll_subscription);
            rv_alacarte_list = itemView.findViewById(R.id.rv_alacarte_list);
            rv_bouquet_list = itemView.findViewById(R.id.rv_bouquet_list);
            txt_edit_box = itemView.findViewById(R.id.txt_edit_box);

            spn_bill_type = itemView.findViewById(R.id.spn_bill_type);

            spn_no_of_months = itemView.findViewById(R.id.spn_no_of_months);
            txt_activation_date = itemView.findViewById(R.id.txt_activation_date);
            txt_expiry_date = itemView.findViewById(R.id.txt_expiry_date);

            spn_no_of_days = itemView.findViewById(R.id.spn_no_of_days);



        }
    }
}
