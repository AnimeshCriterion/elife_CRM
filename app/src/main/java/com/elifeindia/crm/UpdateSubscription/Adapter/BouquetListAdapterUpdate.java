package com.elifeindia.crm.UpdateSubscription.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.model.CableBoxWithSubscription;

import java.util.List;

public class BouquetListAdapterUpdate extends RecyclerView.Adapter<BouquetListAdapterUpdate.MyViewHolder> {

    Context context;
    static List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxBouquetListDTO.BoxBouquetDTO> bouquetDTOS;
    AdapterCallback adapterCallback;

    public    double totalBoquet=0.0;


    public BouquetListAdapterUpdate(Context context,  List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxBouquetListDTO.BoxBouquetDTO> boxBouquets, AdapterCallback adapterCallback) {
        this.context = context;
        this.bouquetDTOS = boxBouquets;
        this.adapterCallback=adapterCallback;

    }

    @NonNull
    @Override
    public BouquetListAdapterUpdate.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscription_update, parent, false);
        return new BouquetListAdapterUpdate.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final BouquetListAdapterUpdate.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_mrp.setText(bouquetDTOS.get(position).getPrice().toString());
        holder.txt_gst.setText(bouquetDTOS.get(position).getTax().toString());
        holder.txt_amount.setText(bouquetDTOS.get(position).getTotal_Price().toString());
        holder.txt_subscription.setText(bouquetDTOS.get(position).getBouquet_Name().toString());
        totalBoquet+=  bouquetDTOS.get(position).getPrice();

        Log.d("TAG", "onBindViewHolder: "+totalBoquet);
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String boxId = bouquetDTOS.get(position).getBox_Bouquet_ID().toString();
                //SharedPrefsData.putString(context, Constants.AlacarteId, boxId, Constants.PREF_NAME);
            }
        });

//        holder.txt_amount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxBouquetList().getBoxBouquet().remove(position);
//
//                adapterCallback.onClickCallback(holder.txt_amount, position, "deletesubscription");
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return bouquetDTOS.size();
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
