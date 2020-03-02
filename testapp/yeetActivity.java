package com.example.visma.testapp;

import android.os.Bundle;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.visma.testapp.support.Locationhelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class yeetActivity extends AppCompatActivity {
    //
    double longitude;
    double latitude;
    private TextView textView2;
    private RequestQueue rQueue;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeet);

        textView2 = findViewById(R.id.TVClosest);
        rQueue = Volley.newRequestQueue(this);

        Button buttonChoose = findViewById(R.id.buttonfind);
        Button buttonStudy = findViewById(R.id.buttonstudy);
        Button buttonDorm = findViewById(R.id.buttondorm);

        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);



        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ActivityCompat.checkSelfPermission(yeetActivity.this, ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                client.getLastLocation().addOnSuccessListener(yeetActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if(location!=null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }

                    }
                });
                //getLastLocation();
                Locationhelper loc = new Locationhelper();
                loc.setLatitude(latitude);
                loc.setLongitude(longitude);

                Gson gson = new Gson();
                String jsonInString = gson.toJson(loc);

                JSONObject parameter = new JSONObject();
                try {
                    parameter = new JSONObject(jsonInString);
                }
                catch (JSONException e) {
                    //;
                }

                String url = "http://coms-309-sd-3.misc.iastate.edu:8080/info_page/toeat";
                //Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameter,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                try {//textView2.setText(response.getString("Response"));
                                    Toast.makeText(getApplicationContext(), response.getString("Response"), Toast.LENGTH_LONG).show();

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

        buttonStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ActivityCompat.checkSelfPermission(yeetActivity.this, ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                client.getLastLocation().addOnSuccessListener(yeetActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if(location!=null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }

                    }
                });
                //getLastLocation();
                Locationhelper loc = new Locationhelper();
                loc.setLatitude(latitude);
                loc.setLongitude(longitude);

                Gson gson = new Gson();
                String jsonInString = gson.toJson(loc);

                JSONObject parameter = new JSONObject();
                try {
                    parameter = new JSONObject(jsonInString);
                }
                catch (JSONException e) {
                    //;
                }

                String url = "http://coms-309-sd-3.misc.iastate.edu:8080/info_page/tostudy";
                //Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameter,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                try {//textView2.setText(response.getString("Response"));
                                    Toast.makeText(getApplicationContext(), response.getString("Response"), Toast.LENGTH_LONG).show();

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


        buttonDorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ActivityCompat.checkSelfPermission(yeetActivity.this, ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                client.getLastLocation().addOnSuccessListener(yeetActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if(location!=null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }

                    }
                });
                //getLastLocation();
                Locationhelper loc = new Locationhelper();
                loc.setLatitude(latitude);
                loc.setLongitude(longitude);

                Gson gson = new Gson();
                String jsonInString = gson.toJson(loc);

                JSONObject parameter = new JSONObject();
                try {
                    parameter = new JSONObject(jsonInString);
                }
                catch (JSONException e) {
                    //;
                }

                String url = "http://coms-309-sd-3.misc.iastate.edu:8080/info_page/todorm";
                //Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameter,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                try {//textView2.setText(response.getString("Response"));
                                    Toast.makeText(getApplicationContext(), response.getString("Response"), Toast.LENGTH_LONG).show();

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

    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
}
