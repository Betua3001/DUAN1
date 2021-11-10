package com.example.sellapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sellapp.R;
import com.example.sellapp.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView price, rating, description, quantity;
    //Số lượng mới vô = 1
    int totalQuantity = 1;
    //Tổng tiền
    int totalPrice = 0;
    Button addToCart;
    ImageView addItem, removeItem;
    Toolbar toolbar;
    ViewAllModel vaModel = null;

    //Lấy FirebaseFireStore
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //Add toolbar
        toolbar = findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel) {
            vaModel = (ViewAllModel) object;
        }

        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.detailed_img);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.detailed_des);
        addToCart = findViewById(R.id.add_to_cart);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        if (vaModel != null) {

            Glide.with(getApplicationContext()).load(vaModel.getImg_url()).into(detailedImg);
            rating.setText(vaModel.getRating());
            price.setText(getString(R.string.price) + vaModel.getPrice() + "/kg");
            description.setText(vaModel.getDescription());
            //Tổng tiền
            totalPrice = vaModel.getPrice() * totalQuantity;

            //Chuyển đơn vị tùy ý
//        if(vaModel.getType().equalsIgnoreCase("fruits")){
//            holder.price.setText(vaList.get(position).getPrice()+"/kg");
//            totalPrice = vaModel.getPrice() * totalQuantity;
//        }
//        if(vaModel.getType().equalsIgnoreCase("fruits")){
//            holder.price.setText(vaList.get(position).getPrice()+"/kg");
//            totalPrice = vaModel.getPrice() * totalQuantity;
//        }

        }
        //Thêm vào giỏ hàng
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                added();
            }
        });
        //Thêm số lượng
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity < 100) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = vaModel.getPrice() * totalQuantity;
                }

            }
        });
        //Bỏ số lượng
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = vaModel.getPrice() * totalQuantity;
                }

            }
        });
    }

    //Hàm thêm vào giỏ hàng
    private void added() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();
        //Format là Thứ, ngày tháng năm
        SimpleDateFormat currentDate = new SimpleDateFormat("E, dd MMM yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());
        //Format là h:p:s am(pm)
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();
        //Tạo bảng trên FirebaseFirestore
        cartMap.put("productName", vaModel.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);
        //Lấy thông tin từ FireStore
        db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this, R.string.added_to_cart, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}