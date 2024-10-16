package com.elifeindia.crm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.OnClickForPaymentReceiptNew;
import com.elifeindia.crm.R;

import com.elifeindia.crm.model.PaymentRecieptList;

import com.elifeindia.crm.printersdk.PaymentReceiptReprentingActivity;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;



public class PaymentListAdapter extends RecyclerView.Adapter<PaymentListAdapter.MyviewHolder>{

    Context context;
    OnClickForPaymentReceiptNew onClickForPaymentReceiptNew;
    List<PaymentRecieptList.PaymentReciept> paymentReciepts;
    private List<PaymentRecieptList.PaymentReciept> itemList;

    public PaymentListAdapter(Context context, List<PaymentRecieptList.PaymentReciept> paymentReciepts,OnClickForPaymentReceiptNew onClickForPaymentReceiptNew) {
        this.context = context;
        this.paymentReciepts = paymentReciepts;
        this.onClickForPaymentReceiptNew=onClickForPaymentReceiptNew;
        this.itemList=new ArrayList<>(paymentReciepts);
    }

    @NonNull
    @Override
    public PaymentListAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_payment_list, viewGroup, false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, @SuppressLint("RecyclerView") int position) {

        try {
            holder.acno.setText("Ac No : "+paymentReciepts.get(position).getAccountNo());
            holder.areaid.setText("Area Id : "+paymentReciepts.get(position).getAreaCustomerID().toString());
            paymentReciepts.get(position).getAreaName();
            paymentReciepts.get(position).getCustomerID();

            holder.invoicenumber.setText("Inv No : "+paymentReciepts.get(position).getInvoiceNumber());
            holder.txt_cust_name.setText(paymentReciepts.get(position).getName());
            holder.edate.setText(ViewUtils.changeDateTimeFormat(paymentReciepts.get(position).getPaymentDate().toString()));

            holder.txt_paymode.setText(paymentReciepts.get(position).getPaymentType());

            holder.subid.setText("Sub Id : "+paymentReciepts.get(position).getSubscriberID());
            paymentReciepts.get(position).getTitle();
            holder.amount_to_pay.setText("T Amnt : "+paymentReciepts.get(position).getTotalAmount().toString());
            holder.balance.setText("B Amnt : "+paymentReciepts.get(position).getBalance().toString());
            holder.paidamount.setText("P Amnt : "+paymentReciepts.get(position).getPaidAmount().toString());
            holder.addressTv.setText(paymentReciepts.get(position).getAddress());

            holder.txt_triplePlay.setText(paymentReciepts.get(position).getTriplePlay());
            paymentReciepts.get(position).getTriplePlayID();

        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.cv_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pId = paymentReciepts.get(position).getPayment_Id();
             //   Toast.makeText(context,"CheckID"+paymentReciepts.get(position).getInvoiceNumber().toString(),Toast.LENGTH_LONG).show();



               onClickForPaymentReceiptNew.onClickCollection(paymentReciepts.get(position),holder.txt_triplePlay.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentReciepts.size();
    }

    public void filter(String text) {
        paymentReciepts.clear();
        if (text.isEmpty()) {
            paymentReciepts.addAll(itemList);
        } else {
            text = text.toLowerCase();
            for (PaymentRecieptList.PaymentReciept item : itemList) {
                // Search by name, category, or description
                if (item.getName().toLowerCase().contains(text) ||
                        item.getContactNo().toLowerCase().contains(text) ||item.getAccountNo().toString().contains(text) ||
                        item.getSubscriberID().toLowerCase().contains(text)) {
                    paymentReciepts.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView txt_triplePlay, txt_cust_name, txt_paymode, paidamount, invoicenumber,  balance,  areaname, subid, acno, edate, address, subarea, nob_no, status, amount_to_pay, areaid, whatsup_no;
        CardView cv_customer;
        TextView addressTv;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            //nob_no = itemView.findViewById(R.id.nob_no);
            //subarea = itemView.findViewById(R.id.subarea);
            //address = itemView.findViewById(R.id.address);

            txt_triplePlay = itemView.findViewById(R.id.txt_triplePlay);
            edate = itemView.findViewById(R.id.paymentdate);
            //status = itemView.findViewById(R.id.status);
            areaid = itemView.findViewById(R.id.areaid);
            subid = itemView.findViewById(R.id.subscriberid);
            acno = itemView.findViewById(R.id.accountnumber);
            addressTv=itemView.findViewById(R.id.subarea);
            txt_cust_name = itemView.findViewById(R.id.customername);
            cv_customer = itemView.findViewById(R.id.cv_customer);
            amount_to_pay = itemView.findViewById(R.id.amount_to_pay);
            paidamount = itemView.findViewById(R.id.paidamount);
            balance = itemView.findViewById(R.id.balance);
            txt_paymode = itemView.findViewById(R.id.txt_paymode);
            invoicenumber = itemView.findViewById(R.id.invoicenumber);

//            whatsup_no = itemView.findViewById(R.id.whatsup_no);
//            areaname = itemView.findViewById(R.id.areaname);


        }

    }

}
