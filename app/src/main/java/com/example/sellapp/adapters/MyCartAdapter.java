package com.example.sellapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellapp.R;
import com.example.sellapp.models.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> mcList;
    int totalAmount = 0;

    public MyCartAdapter(Context context, List<MyCartModel> mcList) {
        this.context = context;
        this.mcList = mcList;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {

        holder.name.setText(mcList.get(position).getProductName());
        holder.price.setText(mcList.get(position).getProductPrice());
        holder.date.setText(mcList.get(position).getCurrentDate());
        holder.time.setText(mcList.get(position).getCurrentTime());
        holder.totalPrice.setText(String.valueOf(mcList.get(position).getTotalPrice()));
        holder.totalQuantity.setText(mcList.get(position).getTotalQuantity());

        //Pass total amount to My Cart Fragment
        totalAmount = totalAmount + mcList.get(position).getTotalPrice();
        Intent i = new Intent("MyTotalAmount");
        i.putExtra("totalAmount", totalAmount);
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);

    }

    @Override
    public int getItemCount() {
        return mcList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, date, time, totalPrice, totalQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            totalQuantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);


        }
    }

}
