package com.example.dell.android5778_7109_3610_02.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.android5778_7109_3610_02.R;
import com.example.dell.android5778_7109_3610_02.models.beckend.DBManagerFactory;
import com.example.dell.android5778_7109_3610_02.models.beckend.DB_manager;
import com.example.dell.android5778_7109_3610_02.models.entities.Worker;
import com.example.dell.android5778_7109_3610_02.models.datasources.DatabaseMySQL;

import java.util.ArrayList;
import java.util.List;


public class Login extends AppCompatActivity {
    private EditText userTxt;
    private EditText passTxt;
    private Button signInButton;
    //SharedPreferences prefs;
    //String prefName="savedPreferences";
    //String userVal="user";
    //String passVal="pass";
    DB_manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        manager= DBManagerFactory.getManager();

        userTxt= (EditText) findViewById(R.id.userName);
        passTxt= (EditText) findViewById(R.id.passName);

        signInButton = (Button) findViewById(R.id.signIn);
        TextView clearText = (TextView) findViewById(R.id.clear);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*prefs=getSharedPreferences(prefName,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                editor.putString(nameVal,userTxt.getText().toString());
                editor.putString(passVal,passTxt.getText().toString());
                editor.commit();*/

                /*
                //translating editable input text in the edittext to string
                 */
                String user= String.valueOf(userTxt.getText());
                String pass= String.valueOf(passTxt.getText());
                Intent myIntent;
                /*
                checking if the input is correct and suitable. if it does- continue to the menu activity
                 */
                try {
                    List<Worker> workers = manager.addAndGetWorkers();
                    for (Worker worker : workers) {
                        if (user.equals(worker.getName()) && pass.equals(worker.getPassword())) {
                            Toast.makeText(getBaseContext(), "Connecting", Toast.LENGTH_SHORT).show();
                            //this.wait(100000000000000000000);
                            myIntent = new Intent(Login.this, MainActivity.class);
                            startActivity(myIntent);
                            userTxt.setText("");
                            passTxt.setText("");
                            break;
                        }
                    }
                }
                catch (Exception e) {
                        e.printStackTrace();
                    }
                Toast.makeText(getBaseContext(),"Wrong username or password",Toast.LENGTH_LONG).show();

            }
        });
        //clearing the edit texts
        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userTxt.setText("");
                passTxt.setText("");
            }
        });

        //SharedPreferences prefs=getSharedPreferences (prefName,MODE_PRIVATE);
        //userTxt.setText(prefs.getString(userVal,""));
        //passTxt.setText(prefs.getString(passVal,""));
    }
}
