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
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.model.InternetBoxWithSubscription;

import java.util.ArrayList;
import java.util.List;

public class InternetPkgListAdapter extends RecyclerView.Adapter<InternetPkgListAdapter.MyViewHolder> {

    Context context;
    List<InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO.BoxpackageListDTO.BoxpackageDTO> boxpackages;

    String space, ace;
    ArrayList<String> names = new ArrayList<>();
    public static String TotalInternetRecords="";
    int count=0;
    ArrayList<String> Amts = new ArrayList<>();

    AdapterCallbackTextView adapterCallback;

    public InternetPkgListAdapter(Context context, List<InternetBoxWithSubscription.InternetBoxwithSubscriptionDTO.BoxpackageListDTO.BoxpackageDTO> boxpackages, AdapterCallbackTextView adapterCallback) {
        this.context = context;
        this.boxpackages = boxpackages;
        this.adapterCallback=adapterCallback;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscription_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        try {
            holder.txt_mrp.setText(String.valueOf(boxpackages.get(position).getPrice()));
            holder.txt_gst.setText(String.valueOf(boxpackages.get(position).getTax_Amount()));
            holder.txt_amount.setText(String.valueOf(boxpackages.get(position).getPrice()));
            holder.txt_subscription.setText(boxpackages.get(position).getPackageX().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String boxId = String.valueOf(boxpackages.get(position).getPackage_ID());
                //SharedPrefsData.putString(context, Constants.AlacarteId, boxId, Constants.PREF_NAME);
            }
        });

        String namenamename = boxpackages.get(position).getPackageX();

        String Amt = String.valueOf(boxpackages.get(position).getPrice());

        String str = namenamename;
        String parsedStr = str.replaceAll("(.{15})", "$1\n");

        if(namenamename.length()==1){
            space="           ";
        }else  if(namenamename.length()==2)
        {
            space="          ";
        }else  if(namenamename.length()==3)
        {
            space="         ";
        }else  if(namenamename.length()==4)
        {
            space="        ";
        }else  if(namenamename.length()==5)
        {
            space="       ";
        }
        if(namenamename.length()==6){
            space="      ";
        }else  if(namenamename.length()==7)
        {
            space="     ";
        }else  if(namenamename.length()==8)
        {
            space="    ";
        }else  if(namenamename.length()==9)
        {
            space="   ";
        }else  if(namenamename.length()==10)
        {
            space="  ";
        }else  if(namenamename.length()==11)
        {
            space=" ";
        }else  if(namenamename.length()==12)
        {
            space="";
        }else  if(namenamename.length()==13)
        {
            space="";
        }else  if(namenamename.length()==14)
        {
            space="";
        }else  if(namenamename.length()==15)
        {
            space="";
        }else  if(namenamename.length()==16)
        {
            space="";
        }
        else  if(namenamename.length()==17)
        {
            space="";
        }else  if(namenamename.length()==18)
        {
            space="";
        }else  if(namenamename.length()==19)
        {
            space="";
        }else  if(namenamename.length()==20)
        {
            space="";
        }
        else  if(namenamename.length()==21)
        {
            space="";
        }else  if(namenamename.length()==22)
        {
            space="";
        }

        if(Amt.length()==3){
            ace="    ";
        }else  if(Amt.length()==4)
        {
            ace="   ";
        }else  if(Amt.length()==5)
        {
            ace="  ";
        }else  if(Amt.length()==6)
        {
            ace=" ";
        }


//        names.add(namenamename );
//        Amts.add(Amt);

        if (TotalInternetRecords.trim().equals("")) {
            TotalInternetRecords = " Package       " + " " + "  " + "Amount" + "\n" +
                    "-------------------------------" + "\n" +
                    parsedStr + space + ace + Amt + "\n";
        } else {
            TotalInternetRecords = TotalInternetRecords +
                    parsedStr +space + ace + Amt + "\n";
        }
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
