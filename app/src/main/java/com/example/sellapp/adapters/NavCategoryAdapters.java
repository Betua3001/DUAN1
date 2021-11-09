package com.example.sellapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sellapp.R;
import com.example.sellapp.models.NavCategoryModel;

import java.util.List;

public class NavCategoryAdapters extends RecyclerView.Adapter<NavCategoryAdapters.ViewHolder>{

    Context context;
    List<NavCategoryModel> ncList;

    public NavCategoryAdapters(Context context, List<NavCategoryModel> ncList) {
        this.context = context;
        this.ncList = ncList;
    }

    @NonNull
    @Override
    public NavCategoryAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryAdapters.ViewHolder holder, int position) {

        Glide.with(context).load(ncList.get(position).getImg_url()).into(holder.ncImg);
        holder.name.setText(ncList.get(position).getName());
        holder.description.setText(ncList.get(position).getDescription());
        holder.discount.setText(ncList.get(position).getDiscount());

    }

    @Override
    public int getItemCount() {
        return ncList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView  ncImg;
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
