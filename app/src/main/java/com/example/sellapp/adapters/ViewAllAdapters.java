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
import com.example.sellapp.activities.ViewAllActivity;
import com.example.sellapp.models.HomeCategoryModel;
import com.example.sellapp.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapters extends RecyclerView.Adapter<ViewAllAdapters.ViewHolder>{

    Context context;
    List<ViewAllModel> vaList;

    public ViewAllAdapters(Context context, List<ViewAllModel> vaList) {
        this.context = context;
        this.vaList = vaList;
    }

    @NonNull
    @Override
    public ViewAllAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapters.ViewHolder holder, int position) {

        Glide.with(context).load(vaList.get(position).getImg_url()).into(holder.vaImg);
        holder.name.setText(vaList.get(position).getName());
        holder.description.setText(vaList.get(position).getDescription());
        holder.rating.setText(vaList.get(position).getDescription());
        holder.price.setText(vaList.get(position).getPrice()+"/kg");
        //Chuyển đơn vị tùy ý
//        if(vaList.get(position).getType().equalsIgnoreCase("fruits")){
//            holder.price.setText(vaList.get(position).getPrice()+"/kg");
//        }
//        if(vaList.get(position).getType().equalsIgnoreCase("fruits")){
//            holder.price.setText(vaList.get(position).getPrice()+"/kg");
//        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailedActivity.class);
                //Lấy miêu tả
                i.putExtra("detail",vaList.get(position).getType());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView vaImg;
        TextView name,description,rating,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vaImg = itemView.findViewById(R.id.view_img);
            name = itemView.findViewById(R.id.view_name);
            description = itemView.findViewById(R.id.view_des);
            rating = itemView.findViewById(R.id.view_rating);
            price = itemView.findViewById(R.id.view_price);
        }
    }
}
