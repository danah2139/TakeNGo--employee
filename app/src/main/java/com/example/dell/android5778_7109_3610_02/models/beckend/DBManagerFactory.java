package com.example.dell.android5778_7109_3610_02.models.beckend;

import com.example.dell.android5778_7109_3610_02.models.datasources.DatabaseMySQL;

/**
 * Created by DELL on 25 דצמבר 2017.
 */

public class DBManagerFactory {
    static DB_manager manager=null;

    public static DB_manager getManager(){
        if (manager== null)
            manager =new DatabaseMySQL();
        return manager;
    }

}
