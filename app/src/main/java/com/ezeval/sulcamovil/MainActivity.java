package com.ezeval.sulcamovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ListElement> elements;
    DrawerLayout drawerLayout;
    TextView textViewLink;
    TextView textViewUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer_layout);
        textViewLink = findViewById(R.id.txtEmpresaWebLink);
        textViewUsuario = findViewById(R.id.txtNombreUsuario);
        textViewUsuario.setText(GlobalInfo.GS_NOMBRE_USUARIO);
        textViewLink.setMovementMethod(LinkMovementMethod.getInstance());
        init();
    }

    public void init(){
        elements=new ArrayList<>();
        elements.add(new ListElement("#775447","Pedro","mexico","Activo"));
        elements.add(new ListElement("#775447","Juan","mexico","Activo"));
        elements.add(new ListElement("#775447","Luis","mexico","Activo"));
        elements.add(new ListElement("#775447","Jose","mexico","Activo"));

        ListAdapter listAdapter=new ListAdapter(elements,this);
        RecyclerView recyclerView=findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout){

        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        recreate();
    }
    public void ClickDashboard(View view){
        redirectActivity(this,Dashboard.class);
    }
    public void ClickAboutUs(View view){
        redirectActivity(this,AboutUs.class);
    }
    public void ClickLogout(View view){
        logout(this);
    }
    public static void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog= builder.create();
        dialog.show();
    }
    public static void redirectActivity(Activity activity,Class aClass){
        Intent intent=new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }
}