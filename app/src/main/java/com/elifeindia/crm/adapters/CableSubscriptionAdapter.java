package com.elifeindia.crm.adapters;

import android.content.ClipboardManager;
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
import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.activities.CableSubscriptionActivity;
import com.elifeindia.crm.view.activities.CustomersDetailsActivity;

import java.util.List;

public class CableSubscriptionAdapter extends RecyclerView.Adapter<CableSubscriptionAdapter.MyViewHolder> {

    Context context;
    List<CustemersCableBoxData.CableBox> cableBoxList;

    public CableSubscriptionAdapter(Context context, List<CustemersCableBoxData.CableBox> cableBoxList) {
        this.context = context;
        this.cableBoxList = cableBoxList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cable_box, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.cable_status.setText("Status : "+cableBoxList.get(position).getStatusName());
        holder.cable_lpamount.setText("LP Amount : " + cableBoxList.get(position).getPaidAmount().toString());

        String lpdate = null;
        try {
            lpdate = ViewUtils.changeDateFormat(cableBoxList.get(position).getPaymentDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.cable_lpdate.setText("LP Date : "+lpdate);

        if(cableBoxList.size()==0){

        }

        holder.stbno.setText(cableBoxList.get(position).getStbno().toString());
        holder.stbno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = holder.stbno.getText().toString();
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(text);
                } else {
                    android.content.ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(context, text+" Copied", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.cafnum.setText(cableBoxList.get(position).getCafno().toString());
        holder.cafnum.setOnClickListener(view -> {
            String text = holder.cafnum.getText().toString();
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(text);
            } else {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, text+" Copied", Toast.LENGTH_SHORT).show();
            }
        });
        holder.vcnum.setText(cableBoxList.get(position).getVcno().toString());
        holder.vcnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = holder.vcnum.getText().toString();
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(text);
                } else {
                    android.content.ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(context, text+" Copied", Toast.LENGTH_SHORT).show();
                }
            }
        });
        String date = ViewUtils.changeDateFormat(cableBoxList.get(position).getExpiryDate().toString());

        holder.expirtydate.setText(date);
        holder.amount.setText(cableBoxList.get(position).getBoxAmount().toString());
        String boxId = cableBoxList.get(position).getCableBoxID().toString();
        SharedPrefsData.putString(context, Constants.CableBoxAmount, cableBoxList.get(position).getBoxAmount().toString(), Constants.PREF_NAME);
        SharedPrefsData.putString(context, Constants.CableBoxID, boxId, Constants.PREF_NAME);
        holder.ll_cable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String boxId = cableBoxList.get(position).getCableBoxID().toString();
                SharedPrefsData.putString(context, Constants.CableBoxAmount, cableBoxList.get(position).getBoxAmount().toString(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.CableBoxID, boxId, Constants.PREF_NAME);

                context.startActivity(new Intent(context, CableSubscriptionActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cableBoxList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView vcnum, stbno, cafnum, expirtydate, amount, cable_lpamount, cable_lpdate, cable_status;;
        LinearLayout ll_cable;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cable_status = itemView.findViewById(R.id.cable_status);
            cable_lpdate = itemView.findViewById(R.id.cable_lpdate);
            cable_lpamount = itemView.findViewById(R.id.cable_lpamount);

            ll_cable = itemView.findViewById(R.id.ll_cable);
            vcnum = itemView.findViewById(R.id.vcnum);
            stbno = itemView.findViewById(R.id.stbno);
            cafnum = itemView.findViewById(R.id.cafnum);
            expirtydate = itemView.findViewById(R.id.expirtydate);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
