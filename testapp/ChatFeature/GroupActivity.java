package com.example.visma.testapp.ChatFeature;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.visma.testapp.GroupAdapter;
import com.example.visma.testapp.HomeActivity;
import com.example.visma.testapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GroupAdapter adapter;
    String username;
    String url = "http://coms-309-sd-3.misc.iastate.edu:8080/allChats/";
    List<Group> groupList;
    private RequestQueue rQueue;


    @Override
    public void onBackPressed() {
        String user = username;
        Intent i = new Intent(GroupActivity.this, HomeActivity.class);
        i.putExtra("full_name", user);
        startActivity(i);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        if(getIntent().getExtras() != null){
            username = getIntent().getStringExtra("full_name");
            // Log.d("USERNAME", username);
            //Log.d("EVENT ID",  String.valueOf(eventID));
        }

        groupList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.groupRecyclerView);

        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        url = "http://coms-309-sd-3.misc.iastate.edu:8080/students/enrolled/" + username;
        //Log.d("Username",username);
        rQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("URL", url);
                        for(int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject group = response.getJSONObject(i);
                                String groupName = group.getString("course_name");
                                int groupID = group.getInt("course_id");
                                String stringGroupID = String.valueOf(groupID);

                                groupList.add(
                                        new Group(groupName, stringGroupID)
                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        rQueue.add(jsonArrayRequest);





        adapter = new GroupAdapter(this, groupList);
        recyclerView.setAdapter(adapter);


    }
}
