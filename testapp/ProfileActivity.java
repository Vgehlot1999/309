package com.example.visma.testapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

public class ProfileActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Address;
    private EditText Major;
    private EditText Year;
    private EditText Dorm;
    private Button Go;
    private String User;

    String url = "http://coms-309-sd-3.misc.iastate.edu:8080/students/register";
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i = getIntent();
        User = i.getStringExtra("full_name");

        rQueue = Volley.newRequestQueue(this);

        Name = (EditText)findViewById(R.id.etName);
        Address = (EditText)findViewById(R.id.etAddress);
        Major = (EditText) findViewById(R.id.etMajor);
        Year = (EditText) findViewById(R.id.etYear);
        Dorm = (EditText) findViewById(R.id.etDorm);
        Go = (Button) findViewById(R.id.btnGo);

        Go.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Map<String, String> params = new HashMap<String,String>();
                params.put("user", User);
                params.put("address", Address.getText().toString().trim());
                params.put("major", Major.getText().toString().trim());
                params.put("year", Year.getText().toString().trim());
                params.put("name", Name.getText().toString().trim());
                params.put("dorm_loc", Dorm.getText().toString().trim());

                final JSONObject parameter = new JSONObject(params);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameter,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
                                i.putExtra("full_name", User);
                                startActivity(i);
                                try {
// Parsing json object response
                                    String status = response.getString("Status");
                                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();

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
}
