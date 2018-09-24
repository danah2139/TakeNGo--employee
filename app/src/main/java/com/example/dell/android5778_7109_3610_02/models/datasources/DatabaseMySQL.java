package com.example.dell.android5778_7109_3610_02.models.datasources;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dell.android5778_7109_3610_02.models.beckend.DB_manager;
import com.example.dell.android5778_7109_3610_02.models.entities.Branch;
import com.example.dell.android5778_7109_3610_02.models.entities.Car;
import com.example.dell.android5778_7109_3610_02.models.entities.CarModel;
import com.example.dell.android5778_7109_3610_02.models.entities.Client;
import com.example.dell.android5778_7109_3610_02.models.entities.Colors;
import com.example.dell.android5778_7109_3610_02.models.entities.Worker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.dell.android5778_7109_3610_02.models.datasources.PHPtools.POST;


/**
 * Created by DELL on 17 דצמבר 2017.
 */



//Log can do problems!
public class DatabaseMySQL implements DB_manager {
    //private final String UserName="goldfarb";
    private final String WEB_URL = "http://goldfarb.vlab.jct.ac.il/";

    public void printLog(String message)
    {
        Log.d(this.getClass().getName(),"\n"+message);
    }
    public void printLog(Exception message)
    {
        Log.d(this.getClass().getName(),"Exception-->\n"+message);
    }
boolean result=false;
    @Override
    public boolean isExist(int id)throws Exception {
        try {
            //final List<Client> clientsList = new ArrayList<Client>();
            int ID=id;
            new AsyncTask<Integer,Void,Boolean>(){
                @Override
                protected Boolean doInBackground(Integer... integers) {
                    try {
                        List<Client> clientsListTemp=new ArrayList<Client>();
                        clientsListTemp = getClientsList();
                        for (Client c: clientsListTemp ) {
                            //clientsList.add(c);

                            if (integers[0] == c.getId())
                                result= true;
                            //throw new Exception(integers[0].toString());
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                    try{
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    return result;
                }
            }.execute(ID);
            /*for (Client client : clientsList) {
                if (client.getId() == (id))
                    return true;
            }*/
            return result;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    //critical to call the string in parms.put(String, Object) will be exactly the same as the string in the REQUEST in our PHP
   @Override
    public boolean addClient(ContentValues client) throws Exception {
        try {
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("_id",client.getAsInteger("_id"));
            params.put("lastName",client.getAsString("lastName"));
            params.put("firstName",client.getAsString("firstName"));
            params.put("phoneNum",client.getAsInteger("phoneNum"));
            params.put("eMail",client.getAsString("eMail"));
            params.put("creditCard",client.getAsInteger("creditCard"));

            String results = POST(WEB_URL + "addClient.php", params);
            return true;
            //printLog("add client:\n" + results);

        } catch (IOException e) {
            return false;
            //printLog("add client Exception:\n" + e);

        }
    }
    @Override
    public void addModel(ContentValues model) throws Exception {
        try {
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("_id",model.getAsInteger("_id"));
            params.put("compName",model.getAsString("compName"));
            params.put("modelName",model.getAsString("modelName"));
            params.put("engineCapacity",model.getAsInteger("modelName"));
            params.put("Gearbox",model.getAsString("Gearbox"));
            params.put("seatsNum",model.getAsInteger("seatsNum"));
            params.put("isBluetooth",model.getAsInteger("isBluetooth"));

            String results = POST(WEB_URL + "addModel.php", params);
            printLog("add model:\n" + results);

        } catch (IOException e) {
            printLog("add model Exception:\n" + e);

        }
    }
    public void addCar(ContentValues car) throws Exception{
        try {
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("_id",car.getAsInteger("_id"));
            params.put("branch",car.getAsInteger("branch"));
            params.put("model",car.getAsInteger("model"));
            params.put("mile",car.getAsInteger("mile"));
            params.put("color",car.getAsString("color"));

            String results = POST(WEB_URL + "addCar.php", params);
            printLog("add car:\n" + results);

        } catch (IOException e) {
            printLog("add car Exception:\n" + e);

        }
    }
    public List<CarModel> getCarModelsList ()throws Exception{
        List<CarModel> result = new ArrayList<CarModel>();
        try {
            String str = PHPtools.GET(WEB_URL + "getAllModels.php");
            JSONArray array = new JSONObject(str).getJSONArray("models");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                CarModel carModel= Tools.ContentValuesToCarModel(contentValues);
                result.add(carModel);
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Client> getClientsList ()throws Exception {
        List<Client> result = new ArrayList<Client>();
        try {
            String str = PHPtools.GET(WEB_URL + "getAllClients.php");
            JSONArray array = new JSONObject(str).getJSONArray("clients");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                Client client = new Client();
                client.setId(jsonObject.getInt("_id"));
                client.setLastName(jsonObject.getString("lastName"));
                client.setFirstName(jsonObject.getString("firstName"));
                client.setPhoneNum(jsonObject.getInt("phoneNum"));
                client.seteMail(jsonObject.getString("eMail"));
                client.setCreditCard(jsonObject.getInt("creditCard"));
                result.add(client);
            }
            return result;
        }
        catch (Exception e) {
           throw e;
            // e.printStackTrace();
        }
        //return null;
    }
    public List<Branch> getBranchesList ()throws Exception{
        List<Branch> result = new ArrayList<Branch>();
        try {
            String str = PHPtools.GET(WEB_URL + "getAllBranchs.php");
            JSONArray array = new JSONObject(str).getJSONArray("branchs");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                Branch branch= new Branch();
                branch.setBranchNum(jsonObject.getInt("_id"));
                branch.setCity(jsonObject.getString("city"));
                branch.setStreet(jsonObject.getString("street"));
                branch.setNumber(jsonObject.getInt("number"));
                branch.setParkingNum(jsonObject.getInt("parkingNum"));
                result.add(branch);
            }
            return result;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public List<Car> GetCarsList ()throws Exception{
        List<Car> result = new ArrayList<Car>();
        try {
            String str = PHPtools.GET(WEB_URL + "getAllCars.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                Car car = new Car();
                car.setCarNum(jsonObject.getInt("_id"));
                car.setBranchNum(jsonObject.getInt("branchNum"));
                car.setCarModel(jsonObject.getInt("carModel"));
                car.setMile(jsonObject.getInt("mile"));
                car.setColor(Colors.valueOf(jsonObject.getString("color")));
                result.add(car);         }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

        public List<Worker> addAndGetWorkers ()throws Exception {
        try {
            List<Worker> workers = new ArrayList<Worker>();
            workers.add(0, new Worker("dana", "1234"));
            workers.add(1, new Worker("noy", "12345"));
            return workers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
