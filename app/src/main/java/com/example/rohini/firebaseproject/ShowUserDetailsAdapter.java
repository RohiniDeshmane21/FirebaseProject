package com.example.rohini.firebaseproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.List;
import java.util.Map;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ShowUserDetailsAdapter extends ArrayAdapter<User> {

    private Activity context;
    private List<User> users;
    Firebase ref;

    private static class ViewHolder {
        TextView txtUserName;
        TextView txtEmail;
        TextView txtPhone;
    }

    public ShowUserDetailsAdapter(Activity context, List<User> user1) {
        super(context, R.layout.show_user_details_custome_layout, user1);
        this.context = context;
        users = user1;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listviewitem = inflater.inflate(R.layout.show_user_details_custome_layout, null, true);

        TextView username = (TextView)listviewitem.findViewById(R.id.textViewUserName);
        TextView phone = (TextView)listviewitem.findViewById(R.id.textViewPhone);
        TextView email = (TextView)listviewitem.findViewById(R.id.textViewEmail);

        User user = users.get(position);
        username.setText(user.getUserName());
        phone.setText(user.getPhone());
        email.setText(user.getEmail());


        listviewitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String clickedid = user.getKeyId();
               // Toast.makeText(parent.getContext(), clickedid, Toast.LENGTH_SHORT).show();
                final CharSequence[] items = { "Delete", "Update"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Do you want to delete or update");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(parent.getContext(), String.valueOf(which), Toast.LENGTH_SHORT).show();

                        dialog.cancel();;

                        //int operation = which;
                        if(which==0){
                            ref =new Firebase("https://fir-project-61be0.firebaseio.com");
                            ref.child(user.getKeyId()).removeValue();
                        }

                        else if(which==1){
                             Intent intent= new Intent(context, MainActivity.class);
                            intent.putExtra("Key",user.getKeyId());
                            context.startActivity(intent);
                        }

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });



        return listviewitem;
    }
}