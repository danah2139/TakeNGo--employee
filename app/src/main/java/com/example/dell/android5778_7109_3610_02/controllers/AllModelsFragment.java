package com.example.dell.android5778_7109_3610_02.controllers;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.android5778_7109_3610_02.R;
import com.example.dell.android5778_7109_3610_02.models.beckend.DBManagerFactory;
import com.example.dell.android5778_7109_3610_02.models.beckend.DB_manager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllModelsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllModelsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllModelsFragment extends Fragment {

    public AllModelsFragment() {
        // Required empty public constructor
    }

    DB_manager manager = DBManagerFactory.getManager();
    List<Car> listCar = new ArrayList<Car>();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_, container, false);
        //  View view =inflater.inflate(R.layout.junk, container,false);

        ListView listView = (ListView) view.findViewById(R.id.listViewCar);
        try {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        listCar = manager.GetCarsList();
                    } catch (Exception e) {
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return listCar.size();
            }

            @Override
            public Object getItem(int i) {
                return listCar.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View getclients = LayoutInflater.from(getContext()).inflate(R.layout.getclients, container, false);
                TextView clients = (TextView) getclients.findViewById(R.id.clients);
                clients.setText(listCar.get(i).toString());
                return getclients;
            }
        });
        return view;
    }
}
