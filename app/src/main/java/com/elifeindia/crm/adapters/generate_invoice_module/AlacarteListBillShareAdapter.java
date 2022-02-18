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
import com.elifeindia.crm.model.CableBoxWithSubscription;
import com.elifeindia.crm.model.GetInvoiceModel;

import java.util.ArrayList;
import java.util.List;


public class AlacarteListBillShareAdapter extends RecyclerView.Adapter<AlacarteListBillShareAdapter.MyViewHolder> {

    Context context;
    public static List<GetInvoiceModel.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO.BoxAlacareteDTO> alacaretes;
    String space, ace;
    ArrayList<String> names = new ArrayList<>();
    public static String TotalAlacarteRecords="";
    int count=0;
    ArrayList<String> Amts = new ArrayList<>();

    public AlacarteListBillShareAdapter(Context context, List<GetInvoiceModel.CableBoxwithSubscriptionDTO.BoxAlacareteListDTO.BoxAlacareteDTO> boxAlacaretes) {
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


            holder.txt_mrp.setText(String.valueOf(alacaretes.get(position).getPrice()));
            holder.txt_gst.setText(String.valueOf(alacaretes.get(position).getTax()));
//        try {
//            holder.txt_amount.setText(alacaretes.get(position).getTotal_Price().toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        holder.txt_subscription.setText(alacaretes.get(position).getAlacarte_Name().toString());

        String namenamename = alacaretes.get(position).getAlacarte_Name();

        String Amt = String.valueOf(alacaretes.get(position).getPrice());

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


            names.add(namenamename );
            Amts.add(Amt);

            if (TotalAlacarteRecords.trim().equals("")) {
                TotalAlacarteRecords =
                        parsedStr + space + ace + Amt + "\n";
            } else {
                TotalAlacarteRecords = TotalAlacarteRecords +
                        parsedStr+space +ace+ Amt + "\n";
            }




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
