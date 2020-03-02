package com.example.visma.testapp.support;

import android.content.Intent;
import android.os.Bundle;

import com.example.visma.testapp.ChatFeature.GroupActivity;
import com.example.visma.testapp.MonActivity;
import com.example.visma.testapp.R;
import com.example.visma.testapp.RegistrationActivity;
import com.example.visma.testapp.yeetActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class navigation extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private String uName;
    private TextView Greeting;
    private Button food, calendar, Register, chat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Complete Class Registration and Profile Information first", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.yeetActivity)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Intent i = getIntent();
        uName = i.getStringExtra("full_name");

        Greeting = findViewById(R.id.twUser);
        Greeting.setText("Welcome, " + uName + "!");

        food = findViewById(R.id.fdbtn);
        calendar = findViewById(R.id.ttbl);
        Register = findViewById(R.id.register);
        chat = findViewById(R.id.btnGroup);

        food.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = uName;
                Intent i = new Intent(navigation.this, yeetActivity.class);
                i.putExtra("full_name", user);
                startActivity(i);

            }
        });

        chat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(navigation.this, GroupActivity.class);
                i.putExtra("full_name", uName);
                Log.d("PUT NAME", uName);
                startActivity(i);

            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = uName;
                Intent i = new Intent(navigation.this, MonActivity.class);
                i.putExtra("full_name", user);
                startActivity(i);
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = uName;
                Intent i = new Intent(navigation.this, RegistrationActivity.class);
                i.putExtra("full_name", user);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }




    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
