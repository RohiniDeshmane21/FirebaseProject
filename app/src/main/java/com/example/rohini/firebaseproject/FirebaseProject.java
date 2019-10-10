package com.example.rohini.firebaseproject;

import android.app.Application;

import com.firebase.client.Firebase;

public class FirebaseProject extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
