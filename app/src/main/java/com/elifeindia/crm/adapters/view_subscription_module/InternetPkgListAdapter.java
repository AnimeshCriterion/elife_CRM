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
import com.elifeindia.crm.model.InternetSubscriptionDetails;

import java.util.List;

public class InternetPkgListAdapter extends RecyclerView.Adapter<InternetPkgListAdapter.MyViewHolder> {

    Context context;
    List<InternetSubscriptionDetails.Boxpackage> boxpackages;

    public InternetPkgListAdapter(Context context, List<InternetSubscriptionDetails.Boxpackage> boxAlacaretes) {
        this.context = context;
        this.boxpackages = boxAlacaretes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscription_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.txt_mrp.setText(boxpackages.get(position).getPricewithouttax().toString());
        holder.txt_gst.setText(boxpackages.get(position).getTaxAmount().toString());
        holder.txt_amount.setText(boxpackages.get(position).getPrice().toString());
        holder.txt_subscription.setText(boxpackages.get(position).getPackage().toString());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String boxId = boxpackages.get(position).getPackageID().toString();
                //SharedPrefsData.putString(context, Constants.AlacarteId, boxId, Constants.PREF_NAME);
            }
        });
    }

    @Override
    public int getItemCount() {
        return boxpackages.size();
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
