package com.example.visma.testapp;

        import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
import android.widget.Button;
import android.widget.TextView;

        import com.example.visma.testapp.ChatFeature.GroupActivity;

public class HomeActivity extends AppCompatActivity {

    private Button Food1, Calendar, register, chat, Logout, Profile;
    private String uName;
    private TextView Greeting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        uName = i.getStringExtra("full_name");

        Greeting = findViewById(R.id.twUser);
        Greeting.setText("Welcome, " + uName + "!");

        Food1 = findViewById(R.id.btnFood);
        Calendar = findViewById(R.id.btnCal);
        register = findViewById(R.id.btnReg);
        chat = findViewById(R.id.btnGroup);
        Logout = findViewById(R.id.logout);
        Profile = findViewById(R.id.profile);


        Food1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = uName;
                Intent i = new Intent(HomeActivity.this, yeetActivity.class);
                i.putExtra("full_name", user);
                startActivity(i);
            }
        });

        Calendar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = uName;
                Intent i = new Intent(HomeActivity.this, MonActivity.class);
                i.putExtra("full_name", user);
                startActivity(i);
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = uName;
                Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
                i.putExtra("full_name", user);
                startActivity(i);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = uName;
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                i.putExtra("full_name", user);
                startActivity(i);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, GroupActivity.class);
                i.putExtra("full_name", uName);
                Log.d("PUT NAME", uName);
                startActivity(i);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = uName;
                Intent i = new Intent(HomeActivity.this, RegistrationActivity.class);
                i.putExtra("full_name", user);
                startActivity(i);
            }
        });
    }
}
