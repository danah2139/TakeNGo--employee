package com.example.dell.android5778_7109_3610_02.controllers;

import android.app.Fragment;
import android.app.FragmentContainer;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dell.android5778_7109_3610_02.R;
public class User extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button addClient=(Button) findViewById(R.id.add_client_Button);
        //making fragment by dynamic and changable
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        AllClientsFragment allClientsFragment=new AllClientsFragment();
        //JunkFragment allClientsFragment=new JunkFragment();
        fragmentTransaction.add(R.id.fragment_container,allClientsFragment); //show all clients
        fragmentTransaction.commit();

        addClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                AddClientFragment addClientFragment = new AddClientFragment();
                fragmentTransaction.replace(R.id.fragment_container, addClientFragment); //show all clients
                fragmentTransaction.commit();
            }
        });
    }
}
