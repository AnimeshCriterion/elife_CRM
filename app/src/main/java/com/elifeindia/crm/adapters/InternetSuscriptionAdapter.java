package com.elifeindia.crm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.activities.CableSubscriptionActivity;
import com.elifeindia.crm.view.activities.InternetSubscriptionActivity;

import java.util.List;

public class InternetSuscriptionAdapter extends RecyclerView.Adapter<InternetSuscriptionAdapter.MyViewHolder> {

    Context context;
    List<CustomersInternetBoxData.InternetBox> internetBoxes;

    public InternetSuscriptionAdapter(Context context, List<CustomersInternetBoxData.InternetBox> internetBoxes) {
        this.context = context;
        this.internetBoxes = internetBoxes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_internet_box, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.cable_status.setText("Status : "+internetBoxes.get(position).getStatusName());
        holder.cable_lpamount.setText(String.valueOf("LP Amount : "+internetBoxes.get(position).getPaidAmount()));
        String lpdate = null;
        try {
            lpdate = ViewUtils.changeDateFormat(internetBoxes.get(position).getPaymentDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.cable_lpdate.setText("LP Date : "+lpdate);

        holder.ipaddress.setText(internetBoxes.get(position).getIp().toString());
        holder.macadress.setText(internetBoxes.get(position).getMac().toString());
        String date = "null";
        try {
            date = ViewUtils.changeDateFormat(internetBoxes.get(position).getExpiryDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.expirtydate.setText(date);
        holder.amount.setText(internetBoxes.get(position).getBoxAmount().toString());

        if (SharedPrefsData.getString(context, Constants.RoleId, Constants.PREF_NAME).equals("7")) {

            holder.amount.setVisibility(View.INVISIBLE);
        }
        String boxId = internetBoxes.get(position).getInternetBoxID().toString();
        SharedPrefsData.putString(context, Constants.InternetBoxID, boxId, Constants.PREF_NAME);
        SharedPrefsData.putString(context, Constants.InternetBoxAmount, internetBoxes.get(position).getBoxAmount().toString(), Constants.PREF_NAME);


        holder.ll_int.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPrefsData.getString(context, Constants.RoleId, Constants.PREF_NAME).equals("7")) {

                    Toast.makeText(context,"Not authorized",Toast.LENGTH_SHORT).show();
                }else
                {
                    String boxId = internetBoxes.get(position).getInternetBoxID().toString();
                    SharedPrefsData.putString(context, Constants.InternetBoxID, boxId, Constants.PREF_NAME);
                    context.startActivity(new Intent(context, InternetSubscriptionActivity.class));
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return internetBoxes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ipaddress, macadress, expirtydate, amount, cable_lpamount, cable_lpdate, cable_status;
        LinearLayout ll_int;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cable_status = itemView.findViewById(R.id.internet_status);
            cable_lpdate = itemView.findViewById(R.id.internet_lpdate);
            cable_lpamount = itemView.findViewById(R.id.internet_lpamount);

            ll_int = itemView.findViewById(R.id.ll_int);
            ipaddress = itemView.findViewById(R.id.ipaddress);
            macadress = itemView.findViewById(R.id.macadress);
            expirtydate = itemView.findViewById(R.id.expiry_date);
            amount = itemView.findViewById(R.id.balance);
        }
    }
}
