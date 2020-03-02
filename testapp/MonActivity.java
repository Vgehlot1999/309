package com.example.visma.testapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MonActivity extends AppCompatActivity {

    private Button populateMon, populateTues, populateWed, populateThurs, populateFri;
    private TextView JsonTextView, JsonTime, JsonDay;
    String url = "http://coms-309-sd-3.misc.iastate.edu:8080/students/enrolled/";
    private String uName;
    private RequestQueue rQueue;

    @Override
    public void onBackPressed() {
        String user = uName;
        Intent i = new Intent(MonActivity.this, HomeActivity.class);
        i.putExtra("full_name", user);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon);
        Intent i = getIntent();
        uName = i.getStringExtra("full_name");
        url = url + uName;

        populateMon = findViewById(R.id.btnMon);
        populateTues = findViewById(R.id.btnTues);
        populateWed = findViewById(R.id.btnWed);
        populateThurs = findViewById(R.id.btnThurs);
        populateFri = findViewById(R.id.btnFri);

        JsonTextView = findViewById(R.id.TWJson);
        JsonTime = findViewById(R.id.TWTime);
        JsonDay = findViewById(R.id.TWDay);
        rQueue = Volley.newRequestQueue(this);


        populateMon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                JsonArrayRequest JARequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray mJsonArray = response;
                        String course_name = "";
                        String time = "";
                        try {
                            for(int i = 0; i < mJsonArray.length(); i++) {
                                JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                                String x = mJsonObject.getString("day");
                                if(x.contains("M")) {
                                    course_name += mJsonObject.getString("course_name") + "\n";
                                    time += mJsonObject.getString("time") + "\n";
                                }
                            }
                            JsonTextView.setText(course_name);
                            JsonTime.setText(time);
                            JsonDay.setText("Monday");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                rQueue.add(JARequest);
            }
        });

        populateTues.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                JsonArrayRequest JARequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray mJsonArray = response;
                        String course_name = "";
                        String time = "";
                        try {
                            for(int i = 0; i < mJsonArray.length(); i++) {
                                JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                                String x = mJsonObject.getString("day");
                                if(x.contains("T")) {
                                    course_name += mJsonObject.getString("course_name") + "\n";
                                    time += mJsonObject.getString("time") + "\n";
                                }
                            }
                            JsonTextView.setText(course_name);
                            JsonTime.setText(time);
                            JsonDay.setText("Tuesday");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                rQueue.add(JARequest);
            }
        });

        populateWed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                JsonArrayRequest JARequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray mJsonArray = response;
                        String course_name = "";
                        String time = "";
                        try {
                            for(int i = 0; i < mJsonArray.length(); i++) {
                                JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                                String x = mJsonObject.getString("day");
                                if(x.contains("W")) {
                                    course_name += mJsonObject.getString("course_name") + "\n";
                                    time += mJsonObject.getString("time") + "\n";
                                }
                            }
                            JsonTextView.setText(course_name);
                            JsonTime.setText(time);
                            JsonDay.setText("Wednesday");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                rQueue.add(JARequest);
            }
        });

        populateThurs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                JsonArrayRequest JARequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray mJsonArray = response;
                        String course_name = "";
                        String time = "";
                        try {
                            for(int i = 0; i < mJsonArray.length(); i++) {
                                JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                                String x = mJsonObject.getString("day");
                                if(x.contains("R")) {
                                    course_name += mJsonObject.getString("course_name") + "\n";
                                    time += mJsonObject.getString("time") + "\n";
                                }
                            }
                            JsonTextView.setText(course_name);
                            JsonTime.setText(time);
                            JsonDay.setText("Thursday");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                rQueue.add(JARequest);
            }
        });

        populateFri.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                JsonArrayRequest JARequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray mJsonArray = response;
                        String course_name = "";
                        String time = "";
                        try {
                            for(int i = 0; i < mJsonArray.length(); i++) {
                                JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                                String x = mJsonObject.getString("day");
                                if(x.contains("F")) {
                                    course_name += mJsonObject.getString("course_name") + "\n";
                                    time += mJsonObject.getString("time") + "\n";
                                }
                            }
                            JsonTextView.setText(course_name);
                            JsonTime.setText(time);
                            JsonDay.setText("Friday");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                rQueue.add(JARequest);
            }
        });


    }
}
