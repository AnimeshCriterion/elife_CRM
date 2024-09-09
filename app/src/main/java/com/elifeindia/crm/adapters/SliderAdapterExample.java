//package com.elifeindia.crm.adapters;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import com.squareup.picasso.Picasso;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.bumptech.glide.Glide;
//import com.elifeindia.crm.R;
//import com.elifeindia.crm.model.SliderItem;
////import com.smarteist.autoimageslider.SliderViewAdapter;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SliderAdapterExample extends
//        SliderViewAdapter<SliderAdapterVH> {
//
//    public SliderAdapterExample(Context context, List<SliderItem> mSliderItems) {
//        this.context = context;
//        this.mSliderItems = mSliderItems;
//    }
//
//    private Context context;
//    private List<SliderItem> mSliderItems = new ArrayList<>();
//
////    public SliderAdapterExample(Context context) {
////        this.context = context;
////    }
//
//    public void renewItems(List<SliderItem> sliderItems) {
//        this.mSliderItems = sliderItems;
//        notifyDataSetChanged();
//    }
//
//    public void deleteItem(int position) {
//        this.mSliderItems.remove(position);
//        notifyDataSetChanged();
//    }
//
//    public void addItem(SliderItem sliderItem) {
//        this.mSliderItems.add(sliderItem);
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
//        return new SliderAdapterVH(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
//
//        SliderItem sliderItem = mSliderItems.get(position);
//
//        viewHolder.textViewDescription.setText(sliderItem.getDescription());
//        viewHolder.textViewDescription.setTextSize(16);
//        viewHolder.textViewDescription.setTextColor(Color.WHITE);
//        Glide.with(context)
//                .load(sliderItem.getImageUrl())
//                .error(R.color.colorPrimaryDark)
//                .placeholder(R.color.colorPrimaryDark)
//                .into(viewHolder.imageViewBackground);
//      //  Picasso.with(context).load(sliderItem.getImageUrl()).into(viewHolder.imageViewBackground);
//      //  Picasso.with(context).load(sliderItem.getImageUrl())
//               // .placeholder(R.color.colorPrimaryDark)
//               // .error(R.color.colorPrimaryDark)
//               // .into(viewHolder.imageViewBackground);
//
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              //  Toast.makeText(context, "This is item in position " + sliderItem.getImageUrl(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    public int getCount() {
//        //slider view count could be dynamic size
//        return mSliderItems.size();
//    }
//
//
//
//}
