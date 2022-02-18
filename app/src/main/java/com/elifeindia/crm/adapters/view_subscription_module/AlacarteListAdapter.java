package com.elifeindia.crm.adapters.view_subscription_module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.model.BoxAlacarteList;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;

import java.util.List;

public class AlacarteListAdapter extends RecyclerView.Adapter<AlacarteListAdapter.MyViewHolder> {

    Context context;
    List<BoxAlacarteList.BoxAlacarete> alacaretes;

    public AlacarteListAdapter(Context context, List<BoxAlacarteList.BoxAlacarete> boxAlacaretes) {
        this.context = context;
        this.alacaretes = boxAlacaretes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscription_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.txt_mrp.setText(alacaretes.get(position).getPrice().toString());
        holder.txt_gst.setText(alacaretes.get(position).getTax().toString());
        holder.txt_amount.setText(alacaretes.get(position).getTotalPrice().toString());
        holder.txt_subscription.setText(alacaretes.get(position).getAlacarteName().toString());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String boxId = alacaretes.get(position).getBoxAlacarteID().toString();
                //SharedPrefsData.putString(context, Constants.AlacarteId, boxId, Constants.PREF_NAME);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alacaretes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_subscription, txt_mrp, txt_gst, txt_amount;
        LinearLayout ll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            txt_subscription = itemView.findViewById(R.id.txt_subscription);
            txt_mrp = itemView.findViewById(R.id.txt_mrp);
            txt_gst = itemView.findViewById(R.id.txt_gst);
            txt_amount = itemView.findViewById(R.id.txt_amount);
        }
    }
}