package com.elifeindia.crm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;


import java.util.List;

public class FilterTypeAdapter extends RecyclerView.Adapter<FilterTypeAdapter.MyViewHolder> {
    Context context;
    List<String> filterDataList;
    int isSelectedItem=0;
    FilterTypeListener listener;


    public interface FilterTypeListener{
        void onClickFilterBtn(int position);
    }

    public  void setListener(FilterTypeListener listener){
        this.listener = listener;
    }

    public FilterTypeAdapter(Context context, List<String> filterDataList) {
        this.context = context;
        this.filterDataList = filterDataList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_filter_type,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(filterDataList.get(position));
        if (isSelectedItem ==position){
            holder.layout.setSelected(true);
            holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
            holder.textView.setTextColor(ContextCompat.getColor(context,R.color.white));

        }else {
            holder.layout.setSelected(false);
            holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
            holder.textView.setTextColor(ContextCompat.getColor(context,R.color.Black));

        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              isSelectedItem = holder.getLayoutPosition();
              listener.onClickFilterBtn(holder.getAbsoluteAdapterPosition());
              notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return filterDataList != null ? filterDataList.size() : 0;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView ;
        ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.filterType);
            layout= itemView.findViewById(R.id.ConstraintLayout);
        }
    }
}
