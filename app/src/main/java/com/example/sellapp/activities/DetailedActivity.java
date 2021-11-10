package com.example.sellapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sellapp.R;
import com.example.sellapp.models.ViewAllModel;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView price,rating,description;
    Button addToCart;
    ImageView addItem,removeItem;
    Toolbar toolbar;
    ViewAllModel vaModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        //Add toolbar
        toolbar = findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final  Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            vaModel = (ViewAllModel) object;
        }

        detailedImg = findViewById(R.id.detailed_img);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.detailed_des);
        addToCart = findViewById(R.id.add_to_cart);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        if(vaModel != null){

            Glide.with(getApplicationContext()).load(vaModel.getImg_url()).into(detailedImg);
            rating.setText(vaModel.getRating());
            price.setText(vaModel.getPrice());
            description.setText("Price(VNĐ) : "+vaModel.getDescription()+"/kg");

            //Chuyển đơn vị tùy ý
//        if(vaModel.getType().equalsIgnoreCase("fruits")){
//            holder.price.setText(vaList.get(position).getPrice()+"/kg");
//        }
//        if(vaModel.getType().equalsIgnoreCase("fruits")){
//            holder.price.setText(vaList.get(position).getPrice()+"/kg");
//        }

        }

    }
}