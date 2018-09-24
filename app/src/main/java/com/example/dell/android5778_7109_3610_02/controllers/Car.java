package com.example.dell.android5778_7109_3610_02.controllers;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dell.android5778_7109_3610_02.R;

public class Car extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);


        Button addCar=(Button) findViewById(R.id.add_car_Button);
        //making fragment by dynamic and changable
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        AllCarsFragment allCarsFragment=new AllCarsFragment();
        //JunkFragment allClientsFragment=new JunkFragment();
        fragmentTransaction.add(R.id.fragment_container1,allCarsFragment); //show all clients
        fragmentTransaction.commit();

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                AddCarFragment addCarFragment = new AddCarFragment();
                fragmentTransaction.replace(R.id.fragment_container1, addCarFragment); //show all clients
                fragmentTransaction.commit();
            }
        });

    }
}
