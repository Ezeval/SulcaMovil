package com.example.sulcamovil;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;


import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionHelper {
  Connection con;
  String ip ;
  String port ;
  String Classes ;
  String database;
  String username;
  String password ;
  String url ;
  @SuppressLint("NewApi")
public Connection connectionClass(){
    ip = "181.198.7.147";
    port = "63356";
    Classes = "net.sourceforge.jtds.jdbc.Driver";
    database = "BaseHouse";
    username = "sa";
    password = "housesa";
    url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    Connection connection=null;
    String ConnectionURL=null;
    try{
        Class.forName(Classes);
        connection = DriverManager.getConnection(url, username,password);
    }
    catch (Exception ex){
        Log.e("Error",ex.getMessage());
    }
    return connection;
}
}
