package com.ezeval.sulcamovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ezeval.sulcamovil.interfaces.UsuarioApi;
import com.ezeval.sulcamovil.models.Usuario;

//import java.sql.Connection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
/*    private static String ip = "181.198.7.147";
    private static String port = "63356";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "BaseHouse";
    private static String username = "sa";
    private static String password = "housesa";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    Connection connect;
    String ConnectionResult="";*/

    Button btnAceptar;
    EditText txtUsuario;
    EditText txtClave;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        btnAceptar = findViewById(R.id.btnAceptar);
        txtClave = findViewById(R.id.editTextClave);
        txtUsuario = findViewById(R.id.editTextUsuario);
        context = this;
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(txtUsuario.getText().toString(), txtClave.getText().toString());
            }
        });
    }

    private void find(String idUsuario, String clave) {
        final LoadingDialog loadingDialog=new LoadingDialog(LoginActivity.this);
        loadingDialog.startLoadingDialog();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.8:8082/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UsuarioApi usuarioApi = retrofit.create(UsuarioApi.class);
        Call<Usuario> call = usuarioApi.find(idUsuario, clave);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                try {
                    Usuario us = response.body();
                    loadingDialog.dismissDialog();
                    if (us== null) {
                        showAlert("El usuario o contraseña son incorrectas.");
                    } else {
                        //showAlert("Usuario ok");
                        GlobalInfo.GS_USUARIO=idUsuario;
                        Intent intent= new Intent(context,MainActivity.class);
                        startActivity(intent);

                    }
                } catch (Exception ex) {
                    loadingDialog.dismissDialog();
                    Toast.makeText(LoginActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error en conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAlert(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage(mensaje)
                .setTitle("Sulca")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        //dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog dialog= builder.create();
        dialog.show();

    }

    /*public void VerificarUsuario(){
        try{
            ConnectionHelper connectionHelper=new ConnectionHelper();
            connect= connectionHelper.connectionClass();
            if(connect!=null)
            {
                String query ="select * from seg_usuario where us_codigo=";
                Statement st= connect.createStatement();
                ResultSet rs=st.executeQuery(query);
                while (rs.next()){
                    //Log.d("data", rs.getString(1)); ;
                }
            }
        }catch (Exception ex){

        }

    }*/

/*    public void sqlButton(){
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
    }*/
}