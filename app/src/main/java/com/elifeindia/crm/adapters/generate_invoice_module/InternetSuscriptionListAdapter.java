package com.elifeindia.crm.adapters.generate_invoice_module;

import android.content.Context;
import android.content.Intent;
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
import com.elifeindia.crm.adapters.AdapterCallback;
import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.model.InternetPackageModel;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.activities.InternetSubscriptionActivity;

import java.util.ArrayList;
import java.util.List;

public class InternetSuscriptionListAdapter extends RecyclerView.Adapter<InternetSuscriptionListAdapter.MyViewHolder> {

    Context context;
    List<InternetPackageModel.PackagesDTO> packagesDTOS;
    List<InternetPackageModel.PackagesDTO> temp;

    public static String packageId, internetPkgPrice;
    AdapterCallback adapterCallback;

    public InternetSuscriptionListAdapter(Context context, List<InternetPackageModel.PackagesDTO> packagesDTOS, List<InternetPackageModel.PackagesDTO> temp, AdapterCallback adapterCallback) {
        this.context = context;
        this.packagesDTOS = packagesDTOS;
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

        holder.txt_channel_name.setText(packagesDTOS.get(position).getPackageX());
        holder.txt_channel_price.setText(String.valueOf(packagesDTOS.get(position).getPrice()));

        holder.rb_insert_delete_channel.setChecked(packagesDTOS.get(position).isIs_select());


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

                packageId = String.valueOf(packagesDTOS.get(position).getPackage_ID());
                internetPkgPrice = String.valueOf(packagesDTOS.get(position).getPrice());

                if(packagesDTOS.get(position).isIs_select()){
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
        return packagesDTOS.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_channel_name, txt_channel_price;
        LinearLayout ll; CheckBox rb_insert_delete_channel;


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

                System.out.println("charString"+charString);

                if (charString.isEmpty()) {

                    packagesDTOS = temp;

                } else {
                    List<InternetPackageModel.PackagesDTO> filteredListNew = new ArrayList<>();

                    for (InternetPackageModel.PackagesDTO listitem : temp) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (String.valueOf(listitem.getPackageX()) != null) {

                            if (String.valueOf(listitem.getPackageX().toLowerCase()).contains(charString)) {
                                filteredListNew.add(listitem);

                            }else {
                                //   Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    packagesDTOS = filteredListNew;

                }
                FilterResults results = new FilterResults();
                results.values = packagesDTOS;
                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                packagesDTOS = (List<InternetPackageModel.PackagesDTO>) results.values;
                notifyDataSetChanged();

                if (packagesDTOS.size() == 0) {
                    Toast.makeText(context, "No Data Available", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}
