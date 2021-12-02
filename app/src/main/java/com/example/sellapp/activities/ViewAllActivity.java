package com.example.sellapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sellapp.R;
import com.example.sellapp.adapters.ViewAllAdapter;
import com.example.sellapp.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    RecyclerView rv;
    ViewAllAdapter vaAdapter;
    List<ViewAllModel> vaModelList;
    Toolbar toolbar;
    ProgressBar progressBar;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        db = FirebaseFirestore.getInstance();
        //Add toolbar
        toolbar = findViewById(R.id.view_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);


        String type = getIntent().getStringExtra("type");
        rv = findViewById(R.id.view_all_rec);
        rv.setVisibility(View.GONE);
        rv.setLayoutManager(new LinearLayoutManager(this));

        vaModelList = new ArrayList<>();
        vaAdapter = new ViewAllAdapter(this, vaModelList);
        rv.setAdapter(vaAdapter);

        //Getting Fruits
        if (type != null && type.equalsIgnoreCase("fruits")) {
            db.collection("AllProducts").whereEqualTo("type", "fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModel vaModel = documentSnapshot.toObject(ViewAllModel.class);
                        vaModelList.add(vaModel);
                        vaAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        //Getting Rice
        if (type != null && type.equalsIgnoreCase("com")) {
            db.collection("AllProducts").whereEqualTo("type", "com").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModel vaModel = documentSnapshot.toObject(ViewAllModel.class);
                        vaModelList.add(vaModel);
                        vaAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        //Getting Hutieu
        if (type != null && type.equalsIgnoreCase("monnuoc")) {
            db.collection("AllProducts").whereEqualTo("type", "monnuoc").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModel vaModel = documentSnapshot.toObject(ViewAllModel.class);
                        vaModelList.add(vaModel);
                        vaAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        //Getting Sữa
        if (type != null && type.equalsIgnoreCase("milk")) {
            db.collection("AllProducts").whereEqualTo("type", "milk").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModel vaModel = documentSnapshot.toObject(ViewAllModel.class);
                        vaModelList.add(vaModel);
                        vaAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        //Getting Fast Food
        if (type != null && type.equalsIgnoreCase("fastfood")) {
            db.collection("AllProducts").whereEqualTo("type", "fastfood").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModel vaModel = documentSnapshot.toObject(ViewAllModel.class);
                        vaModelList.add(vaModel);
                        vaAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        //Getting Meat
        if (type != null && type.equalsIgnoreCase("meat")) {
            db.collection("AllProducts").whereEqualTo("type", "meat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModel vaModel = documentSnapshot.toObject(ViewAllModel.class);
                        vaModelList.add(vaModel);
                        vaAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}