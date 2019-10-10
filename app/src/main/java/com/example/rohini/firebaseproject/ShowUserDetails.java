package com.example.rohini.firebaseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShowUserDetails extends AppCompatActivity {

    private TextView textViewValues;
    Firebase mRef;
    String FullValueString = "";
    ListView listView;
    List<User> userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);

        textViewValues = (TextView)findViewById(R.id.textViewValue);
        listView = (ListView)findViewById(R.id.listView);

        userDetails = new ArrayList<>();

        mRef = new Firebase("https://fir-project-61be0.firebaseio.com");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userDetails.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                       String key = child.getKey();
                       User user = child.getValue(User.class);
                       user.setKeyId(key);
                       userDetails.add(user);
                }

                ShowUserDetailsAdapter adapter = new ShowUserDetailsAdapter(ShowUserDetails.this,userDetails);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}