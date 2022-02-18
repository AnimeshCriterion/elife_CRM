package com.elifeindia.crm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;


public class PagingAdapter extends RecyclerView.Adapter<PagingAdapter.MyviewHolder>{

    Context context;
    int count;
    AdapterCallback adapterCallback;
    int selectedPosition=0;

    public PagingAdapter(Context context, int count, AdapterCallback adapterCallback) {
        this.context = context;
        this.count = count;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public PagingAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_page_count, viewGroup, false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyviewHolder holder, final int position) {


        String ans = String.valueOf(position+1);
        holder.txt_page_no.setText(ans);

        if(selectedPosition==position){
            holder.txt_page_no.setBackgroundResource(R.color.colorPrimary);
            holder.txt_page_no.setTextColor(context.getResources().getColor(R.color.white));
        }

        else{
            holder.txt_page_no.setBackgroundResource(R.color.white);
            holder.txt_page_no.setTextColor(context.getResources().getColor(R.color.colorPrimary));

        }

        holder.txt_page_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;
                notifyDataSetChanged();
//
//                String catId = (groceryCategoryModel.getResponse().get(position).getId());
                adapterCallback.onClickCallback(holder.txt_page_no, position, String.valueOf(position+1));

            }
        });



    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relative_children;
        TextView txt_page_no;



        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            txt_page_no = itemView.findViewById(R.id.txt_page_no);


        }

    }

}
