package com.example.visma.testapp.ChatFeature;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.visma.testapp.R;
import com.example.visma.testapp.support.ChatHandler;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class GroupChatActivity extends AppCompatActivity {
    private Button start;
    private TextView output;
    private WebSocketClient cc;
    private EditText message;
    String groupID;
    String username;
    String url = "ws://coms-309-sd-3.misc.iastate.edu:8080/websocket/";

    @Override
    public void onBackPressed(){
        cc.close();
        String user = username;
        Intent i = new Intent(GroupChatActivity.this, GroupActivity.class);
        i.putExtra("full_name", user);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        if(getIntent().getExtras() != null){

            username = getIntent().getStringExtra("full_name");
            groupID = getIntent().getStringExtra("groupID");

        }

        start = (Button) findViewById(R.id.sendMessage);
        output = (TextView) findViewById(R.id.output);
        message = (EditText) findViewById(R.id.message);

        Draft[] drafts = {new Draft_6455()};

        url = "ws://coms-309-sd-3.misc.iastate.edu:8080/websocket/" + username + "/" + groupID;

        Log.d("Chat URL", url);

        try {

            cc = new WebSocketClient(new URI(url),(Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s=output.getText().toString();
                    output.setText(s+"\n\n"+message);
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

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cc.send(message.getText().toString());
                    message.setText("");
                }
                catch (Exception e)
                {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }



            }
        });



    }

    public int join(String usernameCorrect, int eventID, ChatHandler handler) {
        return 0;
    }
}
