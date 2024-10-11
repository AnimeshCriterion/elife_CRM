package com.elifeindia.crm.adapters;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.elifeindia.crm.R;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.networking.AdapterInterface;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.view.activities.CustomersDetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyviewHolder>{

    public CustomerListAdapter(Context context, List<CustemersList.Customer> custemersLists, AdapterInterface adapterInterface) {
        this.context = context;
        this.custemersLists = custemersLists;
        this.adapterInterface = adapterInterface;

    }

    Context context;
    List<CustemersList.Customer> custemersLists;  //original data list
    AdapterInterface adapterInterface;







    @NonNull
    @Override
    public CustomerListAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_customer_list, viewGroup, false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int position) {

        if(custemersLists.get(position).getColorCode()!=null && !custemersLists.get(position).getColorCode().isEmpty() ){
            holder.cv_customer.setCardBackgroundColor(Color.parseColor(custemersLists.get(position).getColorCode().toString()));
            holder.sideview.setBackgroundColor(Color.parseColor(custemersLists.get(position).getColorCode().toString()));
        }

        String acno = custemersLists.get(position).getAccountNo().toString();
        String subbid = null;
        subbid = custemersLists.get(position).getSubscriberID().toString();

        String statusName = custemersLists.get(position).getPaymentStatus().toString();

        String custstatusName = custemersLists.get(position).getStatusName().toString();

        // String pdate = custemersLists.get(position).getPaymentDate().toString();
        String cname = custemersLists.get(position).getName().toString();
        SharedPrefsData.putString(context, Constants.CustomerName, cname, Constants.PREF_NAME);
        holder.txt_cust_name.setText(cname);

        holder.txt_balance.setText(custemersLists.get(position).getBalance().toString());

        if (SharedPrefsData.getString(context, Constants.RoleType, Constants.PREF_NAME).equals("Technician")) {

            holder.txt_balance.setVisibility(View.INVISIBLE);
        }
        holder.imageActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterInterface.onItemClicked(custemersLists.get(position),holder.imageActionButton);
            }
        });
        holder.setIsRecyclable(false);
        holder.acno.setText("  A/C : "+ acno);
        try {
            holder.areaid.setText("A Id : "+ custemersLists.get(position).getAreaCustomerID().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        holder.address.setText(address);
      //  holder.edate.setText(pdate);
        String address = custemersLists.get(position).getAddress().toString();
        holder.subarea.setText(address);
        holder.subid.setText("S ID : "+subbid);
        holder.nob_no.setText(custemersLists.get(position).getContactNo());
        SharedPrefsData.putString(context, Constants.MobNo, custemersLists.get(position).getContactNo().toString(), Constants.PREF_NAME);
        holder.whatsup_no.setText(custemersLists.get(position).getWhatsupNo().toString());
        holder.areaname.setText(custemersLists.get(position).getAreaName());
        holder.status.setText(statusName);
        holder.customer_status.setText(custstatusName);

        if(custstatusName.equals("Active")){
            holder.customer_status.setBackgroundResource(R.color.Active);

        }else if(custstatusName.equals("Free")){
            holder.customer_status.setBackgroundResource(R.color.InProgress);

        }else if(custstatusName.equals("Hold")){
            holder.customer_status.setBackgroundResource(R.color.PartialPaid_PartialForwarded);

        }else{
            holder.customer_status.setBackgroundResource(R.color.UnPaid_UnpaidForwarded);

        }

        switch (statusName) {
            case "Paid":
                holder.status.setBackgroundResource(R.color.Paid);
                break;
            case "UnPaid":
                holder.status.setBackgroundResource(R.color.UnPaid_UnpaidForwarded);
                break;
            case "Forwarded":
                holder.status.setBackgroundResource(R.color.UnPaid_UnpaidForwarded);
                break;
            case "Partial Paid":
                holder.status.setBackgroundResource(R.color.PartialPaid_PartialForwarded);
                break;
            case "Partial Forwarded":
                holder.status.setBackgroundResource(R.color.PartialPaid_PartialForwarded);
                break;
            case "Inactive":
                holder.status.setBackgroundResource(R.color.Inactive);
                break;
        }


        holder.txt_cust_name.setText(custemersLists.get(position).getName());


        holder.cv_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = custemersLists.get(position).getAddress().toString();
                SharedPrefsData.putString(context, Constants.CustomerAddress, address, Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.CustomerID, custemersLists.get(position).getCustomerID().toString(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.WhatsupNo, holder.whatsup_no.getText().toString(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.CustMob, holder.nob_no.getText().toString(), Constants.PREF_NAME);
                Intent intent = new Intent(context, CustomersDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCountAnime's: "+custemersLists.size());
        return custemersLists.size();
    }



    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView customer_status, txt_cust_name, areaname, subid, acno, edate, address, subarea, nob_no, status, txt_balance, areaid, whatsup_no;
        CardView cv_customer;
        ImageView imageActionButton;
        View sideview;
     //   LinearLayout layout;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            areaid = itemView.findViewById(R.id.areaid);
            nob_no = itemView.findViewById(R.id.nob_no);
            imageActionButton=itemView.findViewById(R.id.imageActionButton);
            subarea = itemView.findViewById(R.id.subarea);
            address = itemView.findViewById(R.id.address);
            edate = itemView.findViewById(R.id.edate);
            status = itemView.findViewById(R.id.status);
         //   layout=itemView.findViewById(R.id.linearback);
            subid = itemView.findViewById(R.id.subid);
            acno = itemView.findViewById(R.id.acno);
            sideview = itemView.findViewById(R.id.sideview);
            txt_cust_name = itemView.findViewById(R.id.txt_cust_name);
            cv_customer = itemView.findViewById(R.id.cv_customer);
            txt_balance = itemView.findViewById(R.id.txt_balance);
            whatsup_no = itemView.findViewById(R.id.whatsup_no);
            areaname = itemView.findViewById(R.id.areaname);
            customer_status = itemView.findViewById(R.id.customer_status);


        }

    }

}
