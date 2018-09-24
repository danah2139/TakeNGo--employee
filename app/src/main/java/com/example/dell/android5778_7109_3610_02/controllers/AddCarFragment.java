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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.android5778_7109_3610_02.R;
import com.example.dell.android5778_7109_3610_02.models.beckend.DBManagerFactory;
import com.example.dell.android5778_7109_3610_02.models.beckend.DB_manager;
import com.example.dell.android5778_7109_3610_02.models.datasources.Tools;
import com.example.dell.android5778_7109_3610_02.models.entities.Colors;
import com.example.dell.android5778_7109_3610_02.models.entities.Car;
import com.example.dell.android5778_7109_3610_02.models.entities.Branch;
import com.example.dell.android5778_7109_3610_02.models.entities.CarModel;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddCarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DB_manager manager= DBManagerFactory.getManager();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddCarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCarFragment newInstance(String param1, String param2) {
        AddCarFragment fragment = new AddCarFragment();
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


    ContentValues carCV;
    View view;
    boolean re;

    List<Branch> branchList = new ArrayList<>();
    List<CarModel> carModelList= new ArrayList<>();
    List<Integer> branchesNumber = new ArrayList<>();
    List<Integer> modelCode = new ArrayList<>();
    //List <Colors> colors= new ArrayList<>();
    Spinner branchSpinner;
    Spinner carModelSpinner;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_car, container, false);
        Button addB = (Button) view.findViewById(R.id.add_car_Button);

        final EditText mileET = (EditText) view.findViewById(R.id.mileBox);
        final EditText cnET = (EditText) view.findViewById(R.id.cnBox);
        branchSpinner = (Spinner) view.findViewById(R.id.branchSpinner);
        carModelSpinner = (Spinner) view.findViewById(R.id.modelSpinner);
        final Spinner colorSpinner = (Spinner) view.findViewById(R.id.colorSpinner);
        colorSpinner.setAdapter(new ArrayAdapter<Colors>(getContext(), android.R.layout.simple_spinner_dropdown_item, Colors.values()));

        try{
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    try{
                        branchList = manager.getBranchesList();
                        carModelList = manager.getCarModelsList();
                    }
                    catch(Exception e){
                    }
                    try{
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e){
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(Void aVoid) {

                }

            }.execute();

        }
        catch (Exception e){

        }

        for (Branch branch: branchList) {
           branchesNumber.add(branch.getBranchNum());
       }

        for(CarModel carModel:carModelList) {
            modelCode.add(carModel.getModelCode());
        }

        ArrayAdapter<Integer> branchAdapter = new ArrayAdapter<Integer>(getContext(),android.R.layout.simple_spinner_item,branchesNumber);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);

        ArrayAdapter<Integer> modelAdapter = new ArrayAdapter<Integer>(getContext(),android.R.layout.simple_spinner_item,modelCode);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carModelSpinner.setAdapter(modelAdapter);







        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (mileET.getText().toString().isEmpty() || cnET.getText().toString().isEmpty() || branchSpinner.getSelectedItem().toString().isEmpty() || carModelSpinner.getSelectedItem().toString().isEmpty() ||
                            colorSpinner.getSelectedItem().toString().isEmpty())
                        throw new Exception("INSERT MISSING VALUE");
                    int branchNumber =Integer.parseInt(branchSpinner.getSelectedItem().toString());
                    int codeModel = Integer.parseInt(carModelSpinner.getSelectedItem().toString());
                    Colors colors = Colors.valueOf(colorSpinner.getSelectedItem().toString());
                   final Car car = new Car(branchNumber,codeModel,Integer.parseInt(mileET.getText().toString()),Integer.parseInt(cnET.getText().toString()),colors);

                    new AsyncTask<Car, Void, Void>() {
                            @Override
                            protected Void doInBackground(Car... cars) {
                                try {
                                    carCV = Tools.CarToContentValues(cars[0]);
                                    manager.addCar(carCV);
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
                        }.execute(car);
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
