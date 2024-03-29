package com.example.sellapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sellapp.R;
import com.example.sellapp.adapters.NavCategoryDetailedAdapter;
import com.example.sellapp.models.NavCategoryDetailedModel;
import com.example.sellapp.models.RecommendedModel;
import com.example.sellapp.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> ncdList;
    NavCategoryDetailedAdapter ncdAdapter;
    FirebaseFirestore db;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");

        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.nav_cat_detailed_rec);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ncdList = new ArrayList<>();
        ncdAdapter = new NavCategoryDetailedAdapter(this,ncdList);
        recyclerView.setAdapter(ncdAdapter);

        //Getting Fruits
        if (type != null && type.equalsIgnoreCase("fruits")) {
            db.collection("NavCategoryDetailed").whereEqualTo("type", "fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        NavCategoryDetailedModel ncdModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        ncdList.add(ncdModel);
                        ncdAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                }
            });
        }
        //Getting Drink
        if (type != null && type.equalsIgnoreCase("drink")) {
            db.collection("NavCategoryDetailed").whereEqualTo("type", "drink").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        NavCategoryDetailedModel ncdModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        ncdList.add(ncdModel);
                        ncdAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                }
            });
        }

    }
}