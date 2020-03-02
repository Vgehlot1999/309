package com.example.visma.testapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Register, Login;
    String url = "http://coms-309-sd-3.misc.iastate.edu:8080/login/register";
    String url2 = "http://coms-309-sd-3.misc.iastate.edu:8080/login/";
    private RequestQueue rQueue;
static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rQueue = Volley.newRequestQueue(this);
        //lQueue = Volley.newRequestQueue(this);

        Name = (EditText) findViewById(R.id.etUser);
        Password = (EditText) findViewById(R.id.etPassword);
        Register = (Button) findViewById(R.id.btnCal);
        Login = (Button) findViewById(R.id.btnLog);


        Register.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Map<String, String> params = new HashMap<String, String>();
                params.put("username", Name.getText().toString().trim());
                params.put("password", Password.getText().toString().trim());

                final JSONObject parameter = new JSONObject(params);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameter,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                String user = Name.getText().toString().trim();
                                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                                i.putExtra("full_name", user);
                                try {
// Parsing json object response
                                    String status = response.getString("Status");

                                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();


                                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();

                                    if (status.equals("Successful")) {
                                        startActivity(i);
                                    }


                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                                }




                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        //rQueue.stop();
                    }
                });
                rQueue.add(request);


            }
        });

        Login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Map<String, String> params = new HashMap<String, String>();
                params.put("username", Name.getText().toString().trim());
                params.put("password", Password.getText().toString().trim());

                final JSONObject parameter = new JSONObject(params);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url2, parameter,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                //Intent i = new Intent(MainActivity.this, HomeActivity.class);
                                String user = Name.getText().toString().trim();
                                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                                username = user;
                                i.putExtra("full_name", username);
                                try {
// Parsing json object response
                                    String status = response.getString("Status");
                                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();

                                    if (status.equals("Login Success")) {
                                        startActivity(i);
                                    }

                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();


                    }
                });
                rQueue.add(request);


            }
        });


    }

    public String tryLogin(String usernameCorrect, String passwordCorrect, ProfileHandler test) {

        return null;
    }
}
