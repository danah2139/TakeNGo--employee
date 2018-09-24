package com.example.dell.android5778_7109_3610_02.controllers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.android5778_7109_3610_02.R;
import com.example.dell.android5778_7109_3610_02.models.beckend.DBManagerFactory;
import com.example.dell.android5778_7109_3610_02.models.beckend.DB_manager;

import java.util.ArrayList;
import java.util.List;

public class Branch extends AppCompatActivity {

    int i;
    DB_manager manager = DBManagerFactory.getManager();
    List<com.example.dell.android5778_7109_3610_02.models.entities.Branch> branchList;
    List<String> branchStrings= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        branchList=new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.listViewBranch);
        try {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        branchList=manager.getBranchesList();
                        for (com.example.dell.android5778_7109_3610_02.models.entities.Branch b: branchList) {
                            branchStrings.add(b.toString());
                        }
                    } catch (Exception e) {
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Toast.makeText(Branch.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {

                }

            }.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adp=new ArrayAdapter<String>(Branch.this,android.R.layout.simple_list_item_1,branchStrings);
        listView.setAdapter(adp);
//        listView.setAdapter(new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return branchList.size();
//            }
//
//            @Override
//            public Object getItem(int i) {
//                return branchList.get(i);
//            }
//
//            @Override
//            public long getItemId(int i) {
//                return i;
//            }
//
//            @Override
//            public View getView(int i, View view, ViewGroup viewGroup) {
////                if (view == null)
////                    view = View.inflate(Branch.this, R.layout.getclients, null);
//                LayoutInflater layoutInflater=LayoutInflater.from(Branch.this);
//                View view1=layoutInflater.inflate(R.layout.getclients,viewGroup,false);
//                TextView branches = (TextView) findViewById(R.id.clients);
//                branches.setText(branchList.get(i).toString());
//                return view1;
//            }
//        });
    }
}
