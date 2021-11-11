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
import com.example.sellapp.activities.DetailedRecommendActivity;
import com.example.sellapp.models.RecommendedModel;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {

    Context context;
    List<RecommendedModel> recList;

    public RecommendedAdapter(Context context, List<RecommendedModel> reList) {
        this.context = context;
        this.recList = reList;
    }

    @NonNull
    @Override
    public RecommendedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(recList.get(position).getImg_url()).into(holder.recImg);
        holder.name.setText(recList.get(position).getName());
        holder.description.setText(recList.get(position).getDescription());
        holder.rating.setText(recList.get(position).getRating());
        //khai báo cái item ấy
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailedRecommendActivity.class);
                //Lấy miêu tả đặt tên đóng gói
                i.putExtra("detail", recList.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView recImg;
        TextView name, description, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recImg = itemView.findViewById(R.id.recom_img);
            name = itemView.findViewById(R.id.recom_name);
            description = itemView.findViewById(R.id.recom_des);
            rating = itemView.findViewById(R.id.recom_rating);
        }
    }
}
