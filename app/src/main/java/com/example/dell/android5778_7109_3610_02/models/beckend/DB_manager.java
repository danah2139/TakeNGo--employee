package com.example.dell.android5778_7109_3610_02.models.beckend;

import android.content.ContentValues;

import com.example.dell.android5778_7109_3610_02.controllers.User;
import com.example.dell.android5778_7109_3610_02.models.entities.Branch;
import com.example.dell.android5778_7109_3610_02.models.entities.Car;
import com.example.dell.android5778_7109_3610_02.models.entities.CarModel;
import com.example.dell.android5778_7109_3610_02.models.entities.Client;
import com.example.dell.android5778_7109_3610_02.models.entities.Worker;

import java.util.List;

/**
 * Created by DELL on 17 דצמבר 2017.
 */

public interface DB_manager {
    public boolean isExist(int id) throws Exception;
    public boolean addClient (ContentValues client) throws Exception;
    public void addModel(ContentValues model) throws Exception;
    public void addCar(ContentValues car) throws Exception;
    public List<CarModel> getCarModelsList () throws Exception;
    public List<Client> getClientsList () throws Exception;
    public List<Branch> getBranchesList () throws Exception;
    public List<Car> GetCarsList () throws Exception;
    public List<Worker> addAndGetWorkers ()throws Exception;
}
