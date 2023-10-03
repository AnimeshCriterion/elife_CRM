package com.elifeindia.crm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.OnClickForPaymentReceiptNew;
import com.elifeindia.crm.R;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.model.PaymentRecieptList;
import com.elifeindia.crm.utils.ViewUtils;

import java.util.List;

public class PaymentMasterForBillShareAdapter extends RecyclerView.Adapter<PaymentMasterForBillShareAdapter.MyViewHolder> {

    Context context;

    public PaymentMasterForBillShareAdapter(Context context, List<GetInvoiceModel.PaymentMasterDTO> paymentReciepts) {
        this.context = context;
        this.paymentReciepts = paymentReciepts;
    }

    List<GetInvoiceModel.PaymentMasterDTO> paymentReciepts;

    @NonNull
    @Override
    public PaymentMasterForBillShareAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.paymentmasterlayout, parent, false);
        return new PaymentMasterForBillShareAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMasterForBillShareAdapter.MyViewHolder holder, int position) {
        try {
            holder.payMode.setText(paymentReciepts.get(position).getPaymentType());
            holder.paidAmount.setText(paymentReciepts.get(position).getPaid_Amount());
            holder.date.setText( ViewUtils.changeDateTimeFormat(paymentReciepts.get(position).getPayment_Date().toString()));

            holder.collectedBy.setText(paymentReciepts.get(position).getEmployee_Name());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return paymentReciepts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView payMode,paidAmount,date,collectedBy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            payMode=itemView.findViewById(R.id.payMode);
            paidAmount=itemView.findViewById(R.id.paidamountTV);
            date=itemView.findViewById(R.id.cbytvfg);
            collectedBy=itemView.findViewById(R.id.trsnNotv);
        }
    }
}
