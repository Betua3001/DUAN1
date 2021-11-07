package com.example.sellapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sellapp.models.UserModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navView;
    AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Add toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Tham chiếu tới drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        //Lấy firebase data
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        //Tạo icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // Chuyển fragment
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_category,R.id.nav_offers,R.id.nav_new_products,
                R.id.nav_my_orders,R.id.nav_my_carts)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

//        View headerView = navigationView.getHeaderView(0);
//        TextView headerName = headerView.findViewById(R.id.nav_header_name);
//        TextView headerEmail = headerView.findViewById(R.id.nav_header_email);
//        CircleImageView headerImg = headerView.findViewById(R.id.nav_header_img);

//        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        UserModel userModel = snapshot.getValue(UserModel.class);
//
//                        headerName.setText(userModel.getName());
//                        headerEmail.setText(userModel.getEmail());
//                        Glide.with(MainActivity.this).load(userModel.getProfileImg()).into(headerImg);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Gán menu vào action bar
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.logout) {
//            auth.signOut();
//            startActivity(new Intent(MainActivity.this,HomeActivity.class));
//            finish();
//        }
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}