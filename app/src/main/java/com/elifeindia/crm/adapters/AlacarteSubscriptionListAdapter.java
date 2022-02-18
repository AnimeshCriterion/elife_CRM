package com.elifeindia.crm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.BoxAlacarteList;

import java.util.ArrayList;
import java.util.List;

public class AlacarteSubscriptionListAdapter extends RecyclerView.Adapter<AlacarteSubscriptionListAdapter.MyViewHolder> {

    Context context;
    public static String packageId, pkgName, pkgPrice, taxPer, taxAmnt;

    List<AlacarteModel.AlacarteDTO> alacaretes;
    List<AlacarteModel.AlacarteDTO> temp;
    AdapterCallback adapterCallback;

    public AlacarteSubscriptionListAdapter(Context context, List<AlacarteModel.AlacarteDTO> alacaretes, List<AlacarteModel.AlacarteDTO> temp, AdapterCallback adapterCallback) {
        this.context = context;
        this.alacaretes = alacaretes;
        this.temp = temp;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.txt_channel_name.setText(alacaretes.get(position).getAlacarte_Name());
        holder.txt_channel_price.setText(String.valueOf(alacaretes.get(position).getPrice()));

        holder.rb_insert_delete_channel.setChecked(alacaretes.get(position).isIs_select());


        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.rb_insert_delete_channel.performClick();
//                packageId = String.valueOf(bouquets.get(position).getBouquet_ID());
//
//                if(bouquets.get(position).isIs_select()){
//                    //remove
//                    adapterCallback.onClickCallback(holder.ll, position, "Remove");
//
//                }else{
//                    //insert
//                    adapterCallback.onClickCallback(holder.ll, position, "Add");
//
//                }


            }
        });

        holder.rb_insert_delete_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                packageId = String.valueOf(alacaretes.get(position).getAlacarte_ID());
                pkgName = String.valueOf(alacaretes.get(position).getAlacarte_Name());
                pkgPrice = String.valueOf(alacaretes.get(position).getPrice());
                taxPer = String.valueOf(alacaretes.get(position).getTax());
                taxAmnt = String.valueOf(alacaretes.get(position).getTax_Amount());

                if (alacaretes.get(position).isIs_select()) {
                    //remove
                    adapterCallback.onClickCallback(holder.ll, position, "Remove");

                } else {
                    //insert
                    adapterCallback.onClickCallback(holder.ll, position, "Add");

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return alacaretes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_channel_name, txt_channel_price;
        LinearLayout ll;
        CheckBox rb_insert_delete_channel;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            rb_insert_delete_channel = itemView.findViewById(R.id.rb_insert_delete_channel);
            txt_channel_price = itemView.findViewById(R.id.txt_channel_price);
            txt_channel_name = itemView.findViewById(R.id.txt_channel_name);
        }
    }


    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString().toLowerCase();

                System.out.println("charString" + charString);

                if (charString.isEmpty()) {

                    alacaretes = temp;

                } else {
                    List<AlacarteModel.AlacarteDTO> filteredListNew = new ArrayList<>();

                    for (AlacarteModel.AlacarteDTO listitem : temp) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (String.valueOf(listitem.getAlacarte_Name()) != null) {

                            if (String.valueOf(listitem.getAlacarte_Name().toLowerCase()).contains(charString)) {
                                filteredListNew.add(listitem);

                            } else {
                                //   Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    alacaretes = filteredListNew;

                }
                FilterResults results = new FilterResults();
                results.values = alacaretes;
                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                alacaretes = (List<AlacarteModel.AlacarteDTO>) results.values;
                notifyDataSetChanged();

                if (alacaretes.size() == 0) {
                    Toast.makeText(context, "No Data Available", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}
