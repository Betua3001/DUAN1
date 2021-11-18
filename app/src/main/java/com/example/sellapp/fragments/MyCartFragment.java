package com.example.sellapp.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sellapp.R;
import com.example.sellapp.activities.PlacedOrderActivity;
import com.example.sellapp.adapters.MyCartAdapter;
import com.example.sellapp.models.MyCartModel;;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    MyCartAdapter mcAdapter;
    List<MyCartModel> mcList;
    TextView totalAmount;
    ProgressBar progressBar;
    Button buyNow;

    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_my_cart, container, false);
        totalAmount = view.findViewById(R.id.total_amount);
        buyNow = view.findViewById(R.id.buy_now);
        //Lấy firebase (auth là Authentication)
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        progressBar = view.findViewById(R.id.progressbar);
        recyclerView = view.findViewById(R.id.my_carts_rec);

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mcList = new ArrayList<>();
        mcAdapter = new MyCartAdapter(getActivity(),mcList);
        recyclerView.setAdapter(mcAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()) {

                        String documentId = document.getId();

                        MyCartModel mcModel = document.toObject(MyCartModel.class);

                        mcModel.setDocumentId(documentId);
                        mcList.add(mcModel);
                        mcAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PlacedOrderActivity.class);
                i.putExtra("itemList", (Serializable) mcList);
                startActivity(i);
            }
        });

        //Truyền dữ liệu
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mReceiver, new IntentFilter("MyTotalAmount"));


        return view;
    }

    //Hiện tổng tiền
    public BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount", 0);
            totalAmount.setText("Total Bill: "+totalBill+" VNĐ");
        }
    };

}