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
import com.example.sellapp.models.PopularModel;


import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private Context context;
    private List<PopularModel> pop_list;

    public PopularAdapter(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.pop_list = popularModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(pop_list.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(pop_list.get(position).getName());
        holder.rating.setText(pop_list.get(position).getRating());
        holder.description.setText(pop_list.get(position).getDescription());
        holder.discount.setText(pop_list.get(position).getDiscount());

        //Chuyển sang Activity View All
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewAllActivity.class);
                //Lấy thể loại
                i.putExtra("type", pop_list.get(position).getType());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pop_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView popImg;
        TextView name, description, rating, discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_des);
            discount = itemView.findViewById(R.id.pop_discount);
            rating = itemView.findViewById(R.id.pop_rating);
        }
    }
}
