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
import com.elifeindia.crm.model.BoxBouquetList;

import java.util.List;

public class BouquetListAdapter extends RecyclerView.Adapter<BouquetListAdapter.MyViewHolder> {

    Context context;
    List<BoxBouquetList.BoxBouquet> bouquets;

    public BouquetListAdapter(Context context, List<BoxBouquetList.BoxBouquet> boxBouquets) {
        this.context = context;
        this.bouquets = boxBouquets;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscription_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.txt_mrp.setText(bouquets.get(position).getPrice().toString());
        holder.txt_gst.setText(bouquets.get(position).getTax().toString());
        holder.txt_amount.setText(bouquets.get(position).getTotalPrice().toString());
        holder.txt_subscription.setText(bouquets.get(position).getBouquetName().toString());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String boxId = bouquets.get(position).getBoxBouquetID().toString();
                //SharedPrefsData.putString(context, Constants.AlacarteId, boxId, Constants.PREF_NAME);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bouquets.size();
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