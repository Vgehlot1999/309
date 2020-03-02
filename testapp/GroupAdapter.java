package com.example.visma.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.visma.testapp.ChatFeature.Group;
import com.example.visma.testapp.ChatFeature.GroupChatActivity;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder>{



    private Context mCtx;
    private List<Group> groupList;


    public GroupAdapter(Context mCtx, List<Group> groupList) {
        this.mCtx = mCtx;
        this.groupList = groupList;
    }


    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.group_list_layout, null);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder groupViewHolder, int i) {
        Group group = groupList.get(i);

        groupViewHolder.groupName.setText(group.getGroupName());

        groupViewHolder.groupID = group.getGroupID();

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView groupName;
        Button chatButton, checkNumUsers;
        WebSocketClient cc;
        String groupID;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            groupName = itemView.findViewById(R.id.groupName);
            chatButton = itemView.findViewById(R.id.chatButton);
            checkNumUsers = itemView.findViewById(R.id.checkNumUsers);



            chatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String groupUsername = MainActivity.username;


                    Intent i = new Intent(mCtx, GroupChatActivity.class);
                    i.putExtra("full_name", groupUsername);
                    i.putExtra("groupID", groupID);
                    Log.d("Group ID", groupID);

                    mCtx.startActivity(i);
                }
            });

            checkNumUsers.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                   String  url = "ws://coms-309-sd-3.misc.iastate.edu:8080/notification/" + groupID;
                    Draft[] drafts = {new Draft_6455()};
                    Log.d("CLICKED",groupID);
                    try {

                        cc = new WebSocketClient(new URI(url),(Draft) drafts[0]) {
                            @Override
                            public void onMessage(String message){
                                Looper.prepare();
                                Toast.makeText(mCtx.getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                Looper.loop();




                                Log.d("CONTEXT", message);
                                cc.close();
                            }
                            @Override
                            public void onOpen(ServerHandshake handshake) {
                                Log.d("OPEN", "run() returned: " + "is connecting");
                            }

                            @Override
                            public void onClose(int code, String reason, boolean remote) {
                                Log.d("CLOSE", "onClose() returned: " + reason);
                            }

                            @Override
                            public void onError(Exception e)
                            {
                                Log.d("Exception:", e.toString());
                            }
                        };
                    }
                    catch (URISyntaxException e) {
                        Log.d("Exception:", e.getMessage().toString());
                        e.printStackTrace();
                    }
                    cc.connect();
                }
            });
        }
    }
}
