package com.example.dell.android5778_7109_3610_02.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.android5778_7109_3610_02.R;
import com.example.dell.android5778_7109_3610_02.models.beckend.DBManagerFactory;
import com.example.dell.android5778_7109_3610_02.models.beckend.DB_manager;
import com.example.dell.android5778_7109_3610_02.models.datasources.Tools;
import com.example.dell.android5778_7109_3610_02.models.entities.Client;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddClientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddClientFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DB_manager manager= DBManagerFactory.getManager();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddClientFragment newInstance(String param1, String param2) {
        AddClientFragment fragment = new AddClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ContentValues clientCV;
    View view;
    boolean re;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_client, container, false);
        Button addB = (Button) view.findViewById(R.id.add_client_Button);

        final EditText fnET = (EditText) view.findViewById(R.id.fnBox);
        final EditText lnET = (EditText) view.findViewById(R.id.lnBox);
        final EditText idET = (EditText) view.findViewById(R.id.idBox);
        final EditText phoneET = (EditText) view.findViewById(R.id.phoneBox);
        final EditText emailET = (EditText) view.findViewById(R.id.emailBox);
        final EditText cardET = (EditText) view.findViewById(R.id.cardBox);


        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (fnET.getText().toString().isEmpty() || lnET.getText().toString().isEmpty() || idET.getText().toString().isEmpty() || phoneET.getText().toString().isEmpty() ||
                            emailET.getText().toString().isEmpty() || cardET.getText().toString().isEmpty())
                        throw new Exception("INSERT MISSING VALUE");
                    if (idET.getText().length() != 9)
                        throw new Exception("WRONG ID FORMAT");
                    boolean b = manager.isExist(Integer.parseInt(idET.getText().toString()));
                    if (b)
                        Toast.makeText(getContext(), "Client is already exists", Toast.LENGTH_LONG).show();
                    else {
                        final Client client = new Client(lnET.getText().toString(), fnET.getText().toString(), Integer.parseInt(idET.getText().toString()), Integer.parseInt(phoneET.getText().toString()), emailET.getText().toString(), Integer.parseInt(cardET.getText().toString()));
                        new AsyncTask<Client, Void, Void>() {
                            @Override
                            protected Void doInBackground(Client... clients) {
                                try {
                                    clientCV = Tools.ClientToContentValues(clients[0]);
                                    if (manager.addClient(clientCV)) re = true;
                                    else re = false;
                                    // Log.d(users[0].getFirstName(),result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                if (re)
                                    Toast.makeText(getContext(), "Added succesfuly", Toast.LENGTH_LONG).show();
                            }
                        }.execute(client);
                    }
                } catch(Exception e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
              //      + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
