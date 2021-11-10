package com.example.sellapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sellapp.R;
import com.example.sellapp.activities.ViewAllActivity;
import com.example.sellapp.models.HomeCategoryModel;

import java.util.List;

public class HomeCategoryAdapters extends RecyclerView.Adapter<HomeCategoryAdapters.ViewHolder> {

    Context context;
    List<HomeCategoryModel> hcList;

    public HomeCategoryAdapters(Context context, List<HomeCategoryModel> hcList) {
        this.context = context;
        this.hcList = hcList;
    }

    @NonNull
    @Override
    public HomeCategoryAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryAdapters.ViewHolder holder, int position) {

        Glide.with(context).load(hcList.get(position).getImg_url()).into(holder.hcImg);
        holder.name.setText(hcList.get(position).getName());

        //Chuyển sang Activity View All
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewAllActivity.class);
                //Lấy thể loại
                i.putExtra("type",hcList.get(position).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hcList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView hcImg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hcImg = itemView.findViewById(R.id.home_cat_img);
            name = itemView.findViewById(R.id.home_cat_name);
        }
    }
}
