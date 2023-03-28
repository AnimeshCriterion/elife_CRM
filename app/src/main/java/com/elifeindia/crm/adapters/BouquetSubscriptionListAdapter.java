package com.elifeindia.crm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.BoxBouquetList;

import java.util.ArrayList;
import java.util.List;

public class BouquetSubscriptionListAdapter extends RecyclerView.Adapter<BouquetSubscriptionListAdapter.MyViewHolder> {

    Context context;
    List<BouquetModel.BouquetDTO> bouquets;
    List<BouquetModel.BouquetDTO> temp;
    AdapterCallback adapterCallback;
    public static String packageId, pkgName, pkgPrice, taxPer, taxAmnt;


    public BouquetSubscriptionListAdapter(Context context, List<BouquetModel.BouquetDTO> bouquets, List<BouquetModel.BouquetDTO> temp, AdapterCallback adapterCallback) {
        this.context = context;
        this.bouquets = bouquets;
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

        holder.txt_channel_price.setText(String.valueOf(bouquets.get(position).getPrice()));
        holder.txt_channel_name.setText(bouquets.get(position).getBouquet_Name().toString());

        holder.rb_insert_delete_channel.setChecked(bouquets.get(position).isIs_select());


        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.rb_insert_delete_channel.performClick();
            }
        });

        holder.rb_insert_delete_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                packageId = String.valueOf(bouquets.get(position).getBouquet_ID());
                pkgName = String.valueOf(bouquets.get(position).getBouquet_Name());
                pkgPrice = String.valueOf(bouquets.get(position).getPrice());
                taxPer = String.valueOf(bouquets.get(position).getTax());
                taxAmnt = String.valueOf(bouquets.get(position).getTax_Amount());



                if(bouquets.get(position).isIs_select()){
                    //remove
                    adapterCallback.onClickCallback(holder.ll, position, "Remove");

                }else{
                    //insert
                    adapterCallback.onClickCallback(holder.ll, position, "Add");

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return bouquets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_channel_name, txt_channel_price;
        CheckBox rb_insert_delete_channel;
        LinearLayout ll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            txt_channel_price = itemView.findViewById(R.id.txt_channel_price);
            txt_channel_name = itemView.findViewById(R.id.txt_channel_name);
            rb_insert_delete_channel = itemView.findViewById(R.id.rb_insert_delete_channel);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString().toLowerCase();

                System.out.println("charString"+charString);

                if (charString.isEmpty()) {

                    bouquets = temp;

                } else {
                    List<BouquetModel.BouquetDTO> filteredListNew = new ArrayList<>();

                    for (BouquetModel.BouquetDTO listitem : temp) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (String.valueOf(listitem.getBouquet_Name()) != null) {

                            if (String.valueOf(listitem.getBouquet_Name().toLowerCase()).contains(charString)) {
                                filteredListNew.add(listitem);

                            }else {
                                //   Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    bouquets = filteredListNew;

                }
                FilterResults results = new FilterResults();
                results.values = bouquets;
                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                bouquets = (List<BouquetModel.BouquetDTO>) results.values;
                notifyDataSetChanged();

                if (bouquets.size() == 0) {
                    Toast.makeText(context, "No Data Available", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }


}
