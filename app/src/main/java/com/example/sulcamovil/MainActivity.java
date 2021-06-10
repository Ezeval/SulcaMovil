package com.example.sulcamovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    private static String ip = "181.198.7.147";
    private static String port = "63356";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "BaseHouse";
    private static String username = "sa";
    private static String password = "housesa";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            Log.d("SUCCESS","SUCCESS");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.d("ERROR", "ERROR");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("FAILURE", "FAILURE");
        }
        sqlButton();
    }


    public void sqlButton(){
        if (connection!=null){
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * from seg_usuario;");
                while (resultSet.next()){
                    Log.d("data", resultSet.getString(1)); ;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            Log.d("error2", "Connection is null");
        }
    }
}