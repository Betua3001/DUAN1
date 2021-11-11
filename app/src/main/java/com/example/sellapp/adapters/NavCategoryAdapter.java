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
import com.example.sellapp.activities.DetailedActivity;
import com.example.sellapp.activities.NavCategoryActivity;
import com.example.sellapp.models.NavCategoryModel;

import java.util.List;

public class NavCategoryAdapter extends RecyclerView.Adapter<NavCategoryAdapter.ViewHolder> {

    Context context;
    List<NavCategoryModel> ncList;

    public NavCategoryAdapter(Context context, List<NavCategoryModel> ncList) {
        this.context = context;
        this.ncList = ncList;
    }

    @NonNull
    @Override
    public NavCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(ncList.get(position).getImg_url()).into(holder.ncImg);
        holder.name.setText(ncList.get(position).getName());
        holder.description.setText(ncList.get(position).getDescription());
        holder.discount.setText(ncList.get(position).getDiscount());

        //Chuyển sang NavCategory Activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NavCategoryActivity.class);
                //Lấy miêu tả
                i.putExtra("type", ncList.get(position).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ncList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ncImg;
        TextView name, description, discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ncImg = itemView.findViewById(R.id.nav_cat_img);
            name = itemView.findViewById(R.id.nav_cat_name);
            description = itemView.findViewById(R.id.nav_cat_des);
            discount = itemView.findViewById(R.id.nav_cat_discount);
        }
    }
}
