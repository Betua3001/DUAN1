package com.example.sellapp.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellapp.R;
import com.example.sellapp.adapters.NavCategoryAdapters;
import com.example.sellapp.adapters.RecommendedAdapters;
import com.example.sellapp.models.NavCategoryModel;
import com.example.sellapp.models.RecommendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    FirebaseFirestore db;

    List<NavCategoryModel> ncList;
    NavCategoryAdapters ncAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.cat_rec);

        db = FirebaseFirestore.getInstance();

        //NavigationView Category Items
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        ncList = new ArrayList<>();
        ncAdapter = new NavCategoryAdapters(getActivity(), ncList);
        recyclerView.setAdapter(ncAdapter);

        //Lấy Firebase của NavigationView Category
        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NavCategoryModel ncModel = document.toObject(NavCategoryModel.class);
                                ncList.add(ncModel);
                                ncAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return view;
    }
}
