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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.AdapterCallbackTextView;
import com.elifeindia.crm.model.InternetBoxWithSubscription;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.activities.InternetSubscriptionActivity;
import com.elifeindia.crm.view.activities.UpdateInternetSubscriptionActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class InternetBoxDetailsAdapter extends RecyclerView.Adapter<InternetBoxDetailsAdapter.MyViewHolder> {

    Context context;   public static String internet_Box_ID, ipno, package_Amount, macadd, box_ID="", boxType_IDInternet, vcno, stbno, cafno, strBillType, bill_Type_IDInternet, connection_Status_IDInternet, activation_DateInternet, expiry_DateInternet, noofMonthInternet, noofDaysInternet, alacarte_Amount, bouquet_Amount, tax_Amount, box_AmountInternet;
    List<InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO> internetBoxes;
    InternetPkgListAdapter internetPkgListAdapter;
    AdapterCallbackTextView adapterCallback;
    AdapterCallback adapterCall;
    Calendar mcurrentDate;

    public InternetBoxDetailsAdapter(Context context, List<InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO> internetBoxes, AdapterCallbackTextView adapterCallback, AdapterCallback adapterCall) {
        this.context = context;
        this.internetBoxes = internetBoxes;
        this.adapterCallback = adapterCallback;
        this.adapterCall = adapterCall;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_internet_box_details, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        if (internetBoxes.get(position).getInternetBox().getStatus_Name().equals("Active")){
            holder.cb_box_status.setChecked(true);

        }else{
            holder.cb_box_status.setChecked(false);

        }

        holder.cb_box_status.setEnabled(false);
        holder.cb_box_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                box_AmountInternet = String.valueOf(internetBoxes.get(position).getInternetBox().getBox_Amount());
//
//                if (holder.cb_box_status.isChecked()){
//                    adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amountInternet,holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "ADDInternet");
//
//                }else{
//                    adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amountInternet,holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "SUBInternet");
//
//                }


            }
        });
        holder.ipaddress.setText(internetBoxes.get(position).getInternetBox().getIp().toString());
        holder.macadress.setText(internetBoxes.get(position).getInternetBox().getMac().toString());
        String date;

        date = ViewUtils.changeDateFormat(internetBoxes.get(position).getInternetBox().getExpiry_Date().toString());
        try {
            holder.expirtydate.setText(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.amountInternet.setText(internetBoxes.get(position).getInternetBox().getBox_Amount().toString());
        holder.txt_expiry_date.setText(ViewUtils.changeDateFormat(internetBoxes.get(position).getInternetBox().getExpiry_Date().substring(0,10)));
        holder.spn_no_of_months.setText(internetBoxes.get(position).getInternetBox().getNoofMonth().toString());
        holder.txt_activation_date.setText(ViewUtils.changeDateFormat(internetBoxes.get(position).getInternetBox().getActivation_Date().substring(0,10)));

        internet_Box_ID = internetBoxes.get(position).getInternetBox().getInternet_Box_ID().toString();
//                    box_ID = cableBoxList.get(position).getCableBox().getBox_ID().toString();
        ipno = internetBoxes.get(position).getInternetBox().getIp().toString();
        macadd = internetBoxes.get(position).getInternetBox().getMac().toString();
        package_Amount = internetBoxes.get(position).getInternetBox().getPackage_Amount().toString();

        bill_Type_IDInternet = internetBoxes.get(position).getInternetBox().getBill_Type_ID().toString();
        connection_Status_IDInternet = internetBoxes.get(position).getInternetBox().getConnection_Status_ID().toString();
        activation_DateInternet = internetBoxes.get(position).getInternetBox().getActivation_Date().substring(0,10);

        //Toast.makeText(context, "Activation Date "+activation_DateInternet, Toast.LENGTH_SHORT).show();

        expiry_DateInternet = internetBoxes.get(position).getInternetBox().getExpiry_Date().substring(0,10);

        //Toast.makeText(context, "Expiry Date "+expiry_DateInternet, Toast.LENGTH_SHORT).show();


        noofMonthInternet = internetBoxes.get(position).getInternetBox().getNoofMonth().toString();
//                    alacarte_Amount = cableBoxList.get(position).getCableBox().getAlacarte_Amount().toString();
//                    bouquet_Amount = cableBoxList.get(position).getCableBox().getBouquet_Amount().toString();
        box_AmountInternet = internetBoxes.get(position).getInternetBox().getBox_Amount().toString();
        adapterCallback.onClickCallback(holder.txt_expiry_date, holder.txt_activation_date,holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "calculateExpiryInternet");

        try {
            if(internetBoxes.get(position).getBoxpackageList().getBoxpackage().size()==0){
                holder.amountInternet.setEnabled(true);

                holder.ll_subscription.setVisibility(View.GONE);


            }else {
                holder.amountInternet.setEnabled(false);
                holder.ll_subscription.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.amountInternet.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    box_AmountInternet = holder.amountInternet.getText().toString();
                    adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amountInternet,holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "strBoxAmountInternet");

                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),0);

                    return true;
                }
                return false;
            }
        });


        try {
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

                                activation_DateInternet = sdf2.format(mcurrentDate.getTime());
                                adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amountInternet,holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "strActivationDateInternet");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }, mYear, mMonth, mDay);
                    mDatePicker.setTitle("Select Activation Date");
                    mDatePicker.show();
                }

            });


            holder.spn_no_of_months.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    noofMonthInternet = internetBoxes.get(position).getInternetBox().getNoofMonth().toString();
                    adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amountInternet,holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "spn_no_of_monthsInternet");

                }
            });
            holder.spn_bill_type.setText(internetBoxes.get(position).getInternetBox().getBill_Type());
            holder.spn_bill_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    bill_Type_IDInternet = internetBoxes.get(position).getInternetBox().getBill_Type_ID().toString();
                    adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amountInternet, holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "spn_bill_typeInternet");

                }
            });
            holder.spn_no_of_days.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {


                        noofDaysInternet = holder.spn_no_of_days.getText().toString();
                        adapterCallback.onClickCallback(holder.txt_expiry_date, holder.amountInternet,holder.spn_no_of_months, holder.spn_bill_type, holder.ll_noofdays, position, "strNoOfDaysInternet");


                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }



        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.rv_bouquet_list.setLayoutManager(mLayoutManager1);
        internetPkgListAdapter = new InternetPkgListAdapter(context, internetBoxes.get(position).getBoxpackageList().getBoxpackage(),adapterCallback);
        holder.rv_bouquet_list.setAdapter(internetPkgListAdapter);

        holder.txt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String boxId = String.valueOf(internetBoxes.get(position).getInternetBox().getInternet_Box_ID());
                SharedPrefsData.putString(context, Constants.InternetBoxID, boxId, Constants.PREF_NAME);
                context.startActivity(new Intent(context, UpdateInternetSubscriptionActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return internetBoxes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_edit, ipaddress, macadress, expirtydate, cable_lpamount, cable_lpdate, cable_status;


        TextView txt_edit_box;
        public static EditText amountInternet, spn_no_of_days;
        LinearLayout ll_noofdays, ll_cable, ll_subscription; RecyclerView rv_bouquet_list, rv_alacarte_list;
        CheckBox cb_box_status;
        TextView txt_activation_date, txt_expiry_date;
        TextView spn_bill_type, spn_no_of_months;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cb_box_status = itemView.findViewById(R.id.cb_box_status);

            txt_edit = itemView.findViewById(R.id.txt_edit);
            ipaddress = itemView.findViewById(R.id.ipaddress);
            macadress = itemView.findViewById(R.id.macadress);
            expirtydate = itemView.findViewById(R.id.expiryaddress);
            amountInternet = itemView.findViewById(R.id.amount);
            rv_bouquet_list = itemView.findViewById(R.id.rv_bouquet_list);


            ll_noofdays = itemView.findViewById(R.id.ll_noofdays);

            ll_cable = itemView.findViewById(R.id.ll_cable);
            ll_subscription = itemView.findViewById(R.id.ll_subscription);
            rv_alacarte_list = itemView.findViewById(R.id.rv_alacarte_list);
            rv_bouquet_list = itemView.findViewById(R.id.rv_bouquet_list);

            spn_bill_type = itemView.findViewById(R.id.spn_bill_type);

            spn_no_of_months = itemView.findViewById(R.id.spn_no_of_months);
            txt_activation_date = itemView.findViewById(R.id.txt_activation_date);
            txt_expiry_date = itemView.findViewById(R.id.txt_expiry_date);

            spn_no_of_days = itemView.findViewById(R.id.spn_no_of_days);


        }
    }
}
