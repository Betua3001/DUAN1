package com.example.sellapp;

import androidx.appcompat.widget.PopupMenu;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sellapp.models.UserModel;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompat {

    Toolbar toolbar;
    NavigationView navView;
    AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ImageView imgLanguages;

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
                R.id.nav_home, R.id.nav_profile, R.id.nav_category, R.id.nav_offers, R.id.nav_new_products,
                R.id.nav_my_orders, R.id.nav_my_carts)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        //Chuyển ngôn ngữ
        imgLanguages = findViewById(R.id.img_languages);


        imgLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pop =new PopupMenu(MainActivity.this, imgLanguages);
                MenuInflater inflater = pop.getMenuInflater();
                inflater.inflate(R.menu.languages_menu, pop.getMenu());
                LanguageManager lang = new LanguageManager(MainActivity.this);
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.english:
                                lang.updateResource("en");
                                recreate();
                                Toast.makeText(MainActivity.this, "Change to English", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.vietnamese:
                                lang.updateResource("vi");
                                recreate();
                                Toast.makeText(MainActivity.this, "Change to Vietnamese", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.chinese:
                                lang.updateResource("zh");
                                recreate();
                                Toast.makeText(MainActivity.this, "Change to Chinese", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                        }
                        return false;
                    }
                });
                pop.show();
            }
        });

        View headerView = navView.getHeaderView(0);
        TextView headerName = headerView.findViewById(R.id.nav_header_name);
        TextView headerEmail = headerView.findViewById(R.id.nav_header_email);
        CircleImageView headerImg = headerView.findViewById(R.id.nav_header_img);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);

                        headerName.setText(userModel.getName());
                        headerEmail.setText(userModel.getEmail());
                        Glide.with(MainActivity.this).load(userModel.getProfileImg()).into(headerImg);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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