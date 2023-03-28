package com.elifeindia.crm.adapters.generate_invoice_module;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import com.elifeindia.crm.model.CableBoxWithSubscription;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.model.InternetBoxWithSubscription;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.activities.BillShareActivity;
import com.elifeindia.crm.view.activities.UpdateCableSubscriptionActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.newTotalAmount;

public class CableBoxDetailsBillShareAdapter extends RecyclerView.Adapter<CableBoxDetailsBillShareAdapter.MyViewHolder> {
    public static String cable_Box_ID, box_ID = "", boxType_ID, vcno, stbno, cafno,ip,mac,strBillType, bill_Type_ID, connection_Status_ID, activation_Date, expiry_Date, noofMonth, noofDays, alacarte_Amount, bouquet_Amount, tax_Amount, box_Amount;
    Calendar mcurrentDate;

    Context context;

    List<GetInvoiceModel.CableBoxwithSubscriptionDTO> cableBoxList;

    List<GetInvoiceModel.InternetBoxwithSubscriptionDTO> internetBoxList;

    AlacarteListBillShareAdapter alacarteListAdapter;
    BouquetListBillShareAdapter bouquetListAdapter;
    InternetListBillShareAdapter internetListAdapter;


    public CableBoxDetailsBillShareAdapter(Context context, List<GetInvoiceModel.CableBoxwithSubscriptionDTO> cableBoxList ,List<GetInvoiceModel.InternetBoxwithSubscriptionDTO> internetBoxList) {
        this.context = context;
        this.cableBoxList = cableBoxList;
        this.internetBoxList = internetBoxList;
    }

    public CableBoxDetailsBillShareAdapter(Context context, List<GetInvoiceModel.InternetBoxwithSubscriptionDTO> internetBoxList) {
        this.context = context;
        this.internetBoxList = internetBoxList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cable_box_details_bill_share, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

//        holder.cable_status.setText("Status : "+cableBoxList.get(position).getCableBox().getStatus_Name());
//        try {
//
//            holder.cable_lpamount.setText("LP Amount : " + cableBoxList.get(position).getCableBox().getPaid_Amount().toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        String lpdate = null;
//        try {
//            lpdate = ViewUtils.changeDateFormat(String.valueOf(cableBoxList.get(position).getCableBox().getPayment_Date()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        holder.cable_lpdate.setText("LP Date : "+lpdate);

        viewsetter(position,holder);


//        if (internetBoxList.get(position).getBoxpackageList().getBoxpackage().size() == 0)
//            holder.txt_inter.setVisibility(View.GONE);
//        else {
//
//            RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
//            holder.rv_internet_list.setLayoutManager(mLayoutManager2);
//            internetListAdapter = new InternetListBillShareAdapter(context, internetBoxList.get(position).getBoxpackageList().getBoxpackage());
//            holder.rv_internet_list.setAdapter(internetListAdapter);
//
//        }




        holder.txt_edit_box.setVisibility(View.GONE);
        holder.cb_box_status.setVisibility(View.GONE);
        holder.txt_edit_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String boxId = cableBoxList.get(position).getCableBox().getCable_Box_ID().toString();
                SharedPrefsData.putString(context, Constants.CableBoxID, boxId, Constants.PREF_NAME);

                context.startActivity(new Intent(context, UpdateCableSubscriptionActivity.class));

            }
        });


        //Toast.makeText(context, "cable box "+cableBoxList, Toast.LENGTH_SHORT).show();
        //Toast.makeText(context, "internet box "+internetBoxList, Toast.LENGTH_SHORT).show();


    }

    private void viewsetter(int position, MyViewHolder holder) {

        if (internetBoxList.size() == 0){
            holder.table_layout.setVisibility(View.VISIBLE);
            holder.cable_text.setText("CABLE BOX");
            holder.txt_ala.setVisibility(View.VISIBLE);

            holder.vcno_text.setText("VcNo");
            holder.stbno_text.setText("STB No");
            holder.stbno.setText(cableBoxList.get(position).getCableBox().getStbno().toString());
            holder.cafnum.setText(cableBoxList.get(position).getCableBox().getCafno().toString());
            holder.vcnum.setText(cableBoxList.get(position).getCableBox().getVcno().toString());
            String date = null;
            date = ViewUtils.changeDateFormat(cableBoxList.get(position).getCableBox().getExpiry_Date().toString());
          //  holder.expirtydate.setText(date);
            holder.expirtydate.setText(ViewUtils.changeDateFormat(SharedPrefsData.getString(context,"ExpiryDate",Constants.PREF_NAME)));
            // holder.txt_expiry_date.setText(date);


            if (cableBoxList.get(position).getBoxAlacareteList().getBoxAlacarete().size() == 0 &&
                    cableBoxList.get(position).getBoxBouquetList().getBoxBouquet().size() == 0 ) {
                holder.amount.setEnabled(true);
                holder.ll_subscription.setVisibility(View.GONE);
            } else {
                holder.amount.setEnabled(false);
                holder.ll_subscription.setVisibility(View.VISIBLE);
            }


            activation_Date = cableBoxList.get(position).getCableBox().getActivation_Date().substring(0, 10);
            holder.txt_activation_date.setText(ViewUtils.changeDateFormat(activation_Date));
            expiry_Date = cableBoxList.get(position).getCableBox().getExpiry_Date().substring(0, 10);
            //      cable_Box_ID = cableBoxList.get(position).getCableBox().getCable_Box_ID().toString();
            //      box_ID = cableBoxList.get(position).getCableBox().getBox_ID().toString();
            //      boxType_ID = String.valueOf(cableBoxList.get(position).getCableBox().getBoxType_ID());
            vcno = cableBoxList.get(position).getCableBox().getVcno().toString();
            stbno = cableBoxList.get(position).getCableBox().getStbno().toString();
            cafno = cableBoxList.get(position).getCableBox().getCafno().toString();
            //    bill_Type_ID = String.valueOf(cableBoxList.get(position).getCableBox().getBill_Type_ID());

            strBillType = cableBoxList.get(position).getCableBox().getBill_Type().toString();
            holder.spn_bill_type.setText(strBillType);
            //      connection_Status_ID = cableBoxList.get(position).getCableBox().getConnection_Status_ID().toString();
            noofMonth = String.valueOf(cableBoxList.get(position).getCableBox().getNoofMonth());

            //double newAmount = cableBoxList.get(position).getCableBox().getBox_Amount();

            //holder.amount.setText(String.valueOf(newAmount * Double.parseDouble(noofMonth)));

            holder.amount.setText(String.valueOf(cableBoxList.get(position).getCableBox().getBox_Amount()));


            holder.spn_no_of_months.setText(noofMonth);
            //      alacarte_Amount = cableBoxList.get(position).getCableBox().getAlacarte_Amount().toString();
            //      bouquet_Amount = cableBoxList.get(position).getCableBox().getBouquet_Amount().toString();

            box_Amount = String.valueOf(cableBoxList.get(position).getCableBox().getBox_Amount());


            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            holder.rv_alacarte_list.setLayoutManager(mLayoutManager);
            alacarteListAdapter = new AlacarteListBillShareAdapter(context, cableBoxList.get(position).getBoxAlacareteList().getBoxAlacarete());
            holder.rv_alacarte_list.setAdapter(alacarteListAdapter);

            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            holder.rv_bouquet_list.setLayoutManager(mLayoutManager1);
            bouquetListAdapter = new BouquetListBillShareAdapter(context, cableBoxList.get(position).getBoxBouquetList().getBoxBouquet());
            holder.rv_bouquet_list.setAdapter(bouquetListAdapter);

        }else{

            //holder.table_layout.setVisibility(View.GONE);
            holder.cable_text.setText("INTERNET BOX");
            holder.txt_ala.setVisibility(View.GONE);
            holder.txt_boq.setText("Internet Package");

            holder.vcno_text.setText("IP");
            holder.vcnum.setText(internetBoxList.get(position).getInternetBox().getIp());

            holder.stbno_text.setText("MAC");
            holder.stbno.setText(internetBoxList.get(position).getInternetBox().getMac());

            holder.cafno_text.setVisibility(View.GONE);
            holder.cafnum.setVisibility(View.GONE);

            activation_Date = internetBoxList.get(position).getInternetBox().getActivation_Date().substring(0, 10);
            holder.txt_activation_date.setText(ViewUtils.changeDateFormat(activation_Date));

            expiry_Date = internetBoxList.get(position).getInternetBox().getExpiry_Date().substring(0, 10);

            strBillType = internetBoxList.get(position).getInternetBox().getBill_Type().toString();
            holder.spn_bill_type.setText(strBillType);

            noofMonth = String.valueOf(internetBoxList.get(position).getInternetBox().getNoofMonth());
            holder.spn_no_of_months.setText(noofMonth);

            //holder.spn_no_of_months.setText(SharedPrefsData.getString(context, Constants.NoofMonths, Constants.PREF_NAME));

            String date = null;
            date = ViewUtils.changeDateFormat(internetBoxList.get(position).getInternetBox().getExpiry_Date().toString());
            //holder.expirtydate.setText(date);
            holder.expirtydate.setText(ViewUtils.changeDateFormat(SharedPrefsData.getString(context,"ExpiryDate",Constants.PREF_NAME)));

            ip = internetBoxList.get(position).getInternetBox().getIp();

            mac = internetBoxList.get(position).getInternetBox().getMac();

            //double newAmount = internetBoxList.get(position).getInternetBox().getBox_Amount();

            //holder.amount.setText(String.valueOf(newAmount * Double.parseDouble(noofMonth)));

            holder.amount.setText(String.valueOf(newTotalAmount));

            RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            holder.rv_bouquet_list.setLayoutManager(mLayoutManager2);
            internetListAdapter = new InternetListBillShareAdapter(context, internetBoxList.get(position).getBoxpackageList().getBoxpackage());
            holder.rv_bouquet_list.setAdapter(internetListAdapter);

        }
    }

    @Override
    public int getItemCount() {

       return  newmethod();
    }

    private int newmethod() {
        if (internetBoxList.size()==0){
            return cableBoxList.size();
        }
        return internetBoxList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_edit_box, vcnum, stbno, cafnum, expirtydate, cable_lpamount, cable_lpdate, cable_status;
        TextView spn_no_of_days;
        public TextView amount;
        LinearLayout ll_noofdays, ll_cable, ll_subscription,table_layout;
        RecyclerView rv_bouquet_list, rv_alacarte_list, rv_internet_list;
        CheckBox cb_box_status;
        TextView txt_activation_date, txt_ala, txt_boq, txt_inter;
        TextView spn_bill_type, spn_no_of_months, cable_text, vcno_text, stbno_text, cafno_text;

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
            table_layout = itemView.findViewById(R.id.table_layout);

            rv_alacarte_list = itemView.findViewById(R.id.rv_alacarte_list);
            rv_bouquet_list = itemView.findViewById(R.id.rv_bouquet_list);
            //rv_internet_list = itemView.findViewById(R.id.rv_internet_list);


            txt_edit_box = itemView.findViewById(R.id.txt_edit_box);

            spn_bill_type = itemView.findViewById(R.id.spn_bill_type);

            spn_no_of_months = itemView.findViewById(R.id.spn_no_of_months);
            txt_activation_date = itemView.findViewById(R.id.txt_activation_date);

            cable_text = itemView.findViewById(R.id.cable_text);
            vcno_text = itemView.findViewById(R.id.vcno_text);
            stbno_text = itemView.findViewById(R.id.stbno_text);
            cafno_text = itemView.findViewById(R.id.cafno_text);

            spn_no_of_days = itemView.findViewById(R.id.spn_no_of_days);

            txt_ala = itemView.findViewById(R.id.txt_ala);
            txt_boq = itemView.findViewById(R.id.txt_boq);
            //txt_inter = itemView.findViewById(R.id.txt_inter);


        }
    }
}
