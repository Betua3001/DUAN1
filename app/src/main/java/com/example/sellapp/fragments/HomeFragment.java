package com.example.sellapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sellapp.R;
import com.example.sellapp.adapters.HomeCategoryAdapter;
import com.example.sellapp.adapters.PopularAdapter;
import com.example.sellapp.adapters.RecommendedAdapter;
import com.example.sellapp.models.HomeCategoryModel;
import com.example.sellapp.models.PopularModel;
import com.example.sellapp.models.RecommendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    View view;
    ProgressBar progressBar;
    ScrollView scrollView;

    RecyclerView popRec, homeCatRec, recomRec;
    FirebaseFirestore db;
    //Popular Items
    List<PopularModel> popList;
    PopularAdapter popAdapters;

    //Home Catgory Items
    List<HomeCategoryModel> hcList;
    HomeCategoryAdapter hcAdapters;

    //Recommended Items
    List<RecommendedModel> recomList;
    RecommendedAdapter recomAdapters;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        //Lấy dữ liệu
        db = FirebaseFirestore.getInstance();

        popRec = view.findViewById(R.id.pop_rec);
        homeCatRec = view.findViewById(R.id.explore_rec);
        recomRec = view.findViewById(R.id.recommended_rec);
        scrollView = view.findViewById(R.id.scroll_view);
        progressBar = view.findViewById(R.id.progressbar);

        //
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        //Popular Items
        popRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popList = new ArrayList<>();
        popAdapters = new PopularAdapter(getActivity(), popList);
        popRec.setAdapter(popAdapters);

        //Lấy Firebase của Popular Items
        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popModel = document.toObject(PopularModel.class);
                                popList.add(popModel);
                                popAdapters.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Home Category Items
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        hcList = new ArrayList<>();
        hcAdapters = new HomeCategoryAdapter(getActivity(), hcList);
        homeCatRec.setAdapter(hcAdapters);

        //Lấy Firebase của Home Category Items
        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategoryModel hcModel = document.toObject(HomeCategoryModel.class);
                                hcList.add(hcModel);
                                hcAdapters.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Home Recommended Items
        recomRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recomList = new ArrayList<>();
        recomAdapters = new RecommendedAdapter(getActivity(), recomList);
        recomRec.setAdapter(recomAdapters);

        //Lấy Firebase của Recommended Items
        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recomModel = document.toObject(RecommendedModel.class);
                                recomList.add(recomModel);
                                recomAdapters.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return view;
    }
}
