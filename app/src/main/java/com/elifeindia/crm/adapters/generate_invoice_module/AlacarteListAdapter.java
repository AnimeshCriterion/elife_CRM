package com.elifeindia.crm.adapters.generate_invoice_module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.adapters.AdapterCallbackTextView;
import com.elifeindia.crm.model.CableBoxWithSubscription;

import java.util.List;

import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.box_position;
import static com.elifeindia.crm.view.activities.GenerateInvoiceActivity.cableBoxWithSubscription;

public class AlacarteListAdapter extends RecyclerView.Adapter<AlacarteListAdapter.MyViewHolder> {

    Context context;
    AdapterCallback adapterCallback;
    public static List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO.BoxAlacareteDTO> alacaretes;

    public AlacarteListAdapter(Context context, List<CableBoxWithSubscription.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO.BoxAlacareteDTO> boxAlacaretes, AdapterCallback adapterCallback) {
        this.context = context;
        this.alacaretes = boxAlacaretes;
        this.adapterCallback=adapterCallback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscription_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


            holder.txt_mrp.setText(alacaretes.get(position).getPrice().toString());
            holder.txt_gst.setText(alacaretes.get(position).getTax().toString());
            holder.txt_amount.setText(alacaretes.get(position).getTotal_Price().toString());
            holder.txt_subscription.setText(alacaretes.get(position).getAlacarte_Name().toString());

            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String boxId = alacaretes.get(position).getBox_Alacarte_ID().toString();
                    //SharedPrefsData.putString(context, Constants.AlacarteId, boxId, Constants.PREF_NAME);
                }
            });

//            holder.txt_amount.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    cableBoxWithSubscription.getCableBoxwithSubscription().get(box_position).getBoxAlacareteList().getBoxAlacarete().remove(position);
//
//                    adapterCallback.onClickCallback(holder.txt_amount, position, "deletesubscription");
//
//                }
//            });


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
