package com.example.sellapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sellapp.R;
import com.example.sellapp.activities.DetailedActivity;
import com.example.sellapp.activities.NavCategoryActivity;
import com.example.sellapp.models.NavCategoryDetailedModel;
import com.example.sellapp.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class NavCategoryDetailedAdapter extends RecyclerView.Adapter<NavCategoryDetailedAdapter.ViewHolder>{

    Context context;
    List<NavCategoryDetailedModel> ncdList;
    //Số lượng mới vô = 1
    int totalQuantity = 1;
    //Tổng tiền
    int totalPrice = 0;
    FirebaseFirestore db;
    FirebaseAuth auth;

    public NavCategoryDetailedAdapter(Context context, List<NavCategoryDetailedModel> ncdList) {
        this.context = context;
        this.ncdList = ncdList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_detailed_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryDetailedAdapter.ViewHolder holder, int position) {

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Glide.with(context).load(ncdList.get(position).getImg_url()).into(holder.ncdImg);
        holder.name.setText(ncdList.get(position).getName());
        holder.price.setText(ncdList.get(position).getPrice()+ "/kg");
        totalPrice = ncdList.get(position).getPrice() * totalQuantity;
        //Chuyển đơn vị tùy ý
//        if(vaList.get(position).getType().equalsIgnoreCase("fruits")){
//            holder.price.setText(vaList.get(position).getPrice()+"/kg");
//        }
//        if(vaList.get(position).getType().equalsIgnoreCase("fruits")){
//            holder.price.setText(vaList.get(position).getPrice()+"/kg");
//        }
        //Bỏ vào giỏ hàng
//        holder.ncdAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                added();
//            }
//        });
        //Thêm số lượng
        holder.ncdAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity < 100) {
                    totalQuantity++;
                    holder.quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = ncdList.get(position).getPrice() * totalQuantity;
                }

            }
        });
        //Bỏ số lượng
        holder.ncdRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity > 1) {
                    totalQuantity--;
                    holder.quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = ncdList.get(position).getPrice() * totalQuantity;
                }

            }
        });
        //Hàm add vào giỏ hàng
//        private void added(NavCategoryDetailedModel ncdModel){
//            String saveCurrentDate, saveCurrentTime;
//            Calendar calForDate = Calendar.getInstance();
//            //Format là Thứ, ngày tháng năm
//            SimpleDateFormat currentDate = new SimpleDateFormat("E, dd MMM yyyy");
//            saveCurrentDate = currentDate.format(calForDate.getTime());
//            //Format là h:p:s am(pm)
//            SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");
//            saveCurrentTime = currentTime.format(calForDate.getTime());
//
//            final HashMap<String, Object> cartMap = new HashMap<>();
//            //Tạo bảng trên FirebaseFirestore
//            cartMap.put("productName", ncdList.get(position).getName());
//            cartMap.put("productPrice", holder.price.getText().toString());
//            cartMap.put("currentDate", saveCurrentDate);
//            cartMap.put("currentTime", saveCurrentTime);
//            cartMap.put("totalQuantity", holder.quantity.getText().toString());
//            cartMap.put("totalPrice", totalPrice);
//            //Lấy thông tin từ FireStore
//            db.collection("AddToCart").document(auth.getCurrentUser().getUid())
//                    .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentReference> task) {
//                    Toast.makeText(context, R.string.added_to_cart, Toast.LENGTH_SHORT).show();
//                    ((Activity)context).finish();
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return ncdList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ncdImg,ncdAdd,ncdRemove;
        TextView name,price,quantity;
        Button ncdBuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ncdImg = itemView.findViewById(R.id.nav_cat_detailed_img);
            name = itemView.findViewById(R.id.nav_cat_detailed_name);
            price = itemView.findViewById(R.id.nav_cat_detailed_price);
            ncdAdd = itemView.findViewById(R.id.nav_cat_detailed_add);
            ncdRemove = itemView.findViewById(R.id.nav_cat_detailed_remove);
            ncdBuy = itemView.findViewById(R.id.nav_cat_detailed_buy);
            quantity = itemView.findViewById(R.id.nav_cat_detailed_quantity);

        }
    }
}
