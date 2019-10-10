package com.example.rohini.firebaseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button sendData;
    EditText etUserName, etPhone, etEmail;
    Firebase mRootRef;
    DatabaseReference mDatabaseReference;
    FirebaseDatabase firebaseDatabase;
    String key;
   // Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootRef = new Firebase("https://fir-project-61be0.firebaseio.com/");
        sendData = (Button)findViewById(R.id.btnSendData);
        etUserName = (EditText)findViewById(R.id.editTextUserName);
        etPhone = (EditText)findViewById(R.id.editTextPhone);
        etEmail =  (EditText)findViewById(R.id.editTextEmail);

        Intent intent = getIntent();
      //  key="NoKey";
        key = intent.getStringExtra("Key");
        Firebase m_objFireBaseRef1;
        if(key != null){

          //  mRef = new Firebase("https://fir-project-61be0.firebaseio.com");

            mRootRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                  //  userDetails.clear();

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        String key1 = child.getKey();
                        User user = child.getValue(User.class);
                        if(key1.equals(key)){
                            etUserName.setText(user.getUserName().toString());
                            etEmail.setText(user.getEmail().toString());
                            etPhone.setText(user.getPhone().toString());
                        }
                       
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            // DataSnapshot contactSnapshot = dataSnapshot.child("contacts");
           // m_objFireBaseRef1 = new Firebase("https://fir-project-61be0.firebaseio.com/");
           // Toast.makeText(this, m_objFireBaseRef1.toString(),
           //         Toast.LENGTH_LONG).show();
          // etUserName.setText(m_objFireBaseRef.child(key).child("UserName"));
        }

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(key!=null){

                    //Update User
                   Firebase m_objFireBaseRef = new Firebase("https://fir-project-61be0.firebaseio.com/");
                  //  User user = new User(etUserName.getText().toString(),etPhone.getText().toString(),etEmail.getText().toString());


                    m_objFireBaseRef.child(key).child("UserName").setValue(etUserName.getText().toString());
                    m_objFireBaseRef.child(key).child("Phone").setValue(etPhone.getText().toString());
                    m_objFireBaseRef.child(key).child("Email").setValue(etEmail.getText().toString());

                    Intent intent= new Intent(MainActivity.this, ShowUserDetails.class);
                    startActivity(intent);

                }
                else if(key==null) {

                    //Add new user
                    Firebase refChild = mRootRef.push();

                    Firebase mRefChild = refChild.child("UserName");
                    mRefChild.setValue(etUserName.getText().toString());

                    Firebase mRefChild1 = refChild.child("Phone");
                    mRefChild1.setValue(etPhone.getText().toString());

                    Firebase mRefChild2 = refChild.child("Email");
                    mRefChild2.setValue(etEmail.getText().toString());

                    Intent intent= new Intent(MainActivity.this, ShowUserDetails.class);
                    startActivity(intent);
                }

            }
        });

    }
}
