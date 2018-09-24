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
import com.example.dell.android5778_7109_3610_02.models.entities.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JunkFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JunkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JunkFragment extends Fragment {


    public JunkFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    DB_manager manager = DBManagerFactory.getManager();
    List<Client> listClient = new ArrayList<Client>();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_clients, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listViewClient);
        try {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        listClient = manager.getClientsList();
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
                return listClient.size();
            }

            @Override
            public Object getItem(int i) {
                return listClient.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View getclients = LayoutInflater.from(getContext()).inflate(R.layout.getclients, container, false);
                TextView clients = (TextView) getclients.findViewById(R.id.clients);
                clients.setText(listClient.get(i).toString());
                return getclients;
            }
        });
        return view;
    }
}
