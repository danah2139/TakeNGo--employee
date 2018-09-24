package com.example.dell.android5778_7109_3610_02.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dell.android5778_7109_3610_02.R;
import com.example.dell.android5778_7109_3610_02.models.entities.Car;
import com.example.dell.android5778_7109_3610_02.models.entities.CarModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button usersButton = (Button) findViewById(R.id.userButton);
        usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, User.class);
                startActivity(myIntent);
            }
        });
        Button branchesButton = (Button) findViewById(R.id.branchButton);
        branchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent1 = new Intent(MainActivity.this, Branch .class);
                startActivity(myIntent1);
            }
        });
        Button modelButton = (Button) findViewById(R.id.modelButton);
        modelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent2 = new Intent(MainActivity.this,    com.example.dell.android5778_7109_3610_02.controllers.CarModel.class);
                startActivity(myIntent2);
            }
        });
        Button carsButton = (Button) findViewById(R.id.carsButton);
        carsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent3 = new Intent(MainActivity.this, com.example.dell.android5778_7109_3610_02.controllers.Car.class);
                startActivity(myIntent3);
            }
        });
    }
}
