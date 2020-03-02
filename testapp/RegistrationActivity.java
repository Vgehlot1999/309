package com.example.visma.testapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private EditText course;
    private EditText section;
    private String user;
    private Button regNext, cal;
    String url = "http://coms-309-sd-3.misc.iastate.edu:8080/students/register_courses";
    private RequestQueue rQueue;

    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegistrationActivity.this, HomeActivity.class);
        i.putExtra("full_name", user);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Intent i = getIntent();
        user = i.getStringExtra("full_name");

        rQueue = Volley.newRequestQueue(this);

        course = (EditText) findViewById(R.id.etClass);
        section = (EditText) findViewById(R.id.etSect);
        regNext = findViewById(R.id.btnRegNext);
        cal = findViewById(R.id.btnCal);

        regNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> params = new HashMap<String, String>();
                params.put("user", user);
                params.put("className", course.getText().toString().toUpperCase().trim());
                params.put("sect", section.getText().toString().trim());
                //course.setText("");
                //section.setText("");
                final JSONObject parameter = new JSONObject(params);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameter,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Intent i = new Intent(RegistrationActivity.this, RegistrationActivity.class);
                                i.putExtra("full_name", user);
                                startActivity(i);
                                course.setText("");
                                section.setText("");
                                Log.d("JSONResp",response.toString());
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                //rQueue.stop();
                            }
                        }

                );
                rQueue.add(request);


            }

        });

        cal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", user);
                params.put("className", course.getText().toString().toUpperCase().trim());
                params.put("sect", section.getText().toString().trim());
                //course.setText("");
                //section.setText("");
                final JSONObject parameter = new JSONObject(params);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameter,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Intent i = new Intent(RegistrationActivity.this, MonActivity.class);
                                i.putExtra("full_name", user);
                                startActivity(i);
                                course.setText("");
                                section.setText("");
                                Log.d("JSONResp",response.toString());
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                //rQueue.stop();
                            }
                        }

                );
                rQueue.add(request);
            }
        });


    }


}