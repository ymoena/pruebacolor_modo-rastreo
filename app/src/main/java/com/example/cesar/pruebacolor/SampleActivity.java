package com.example.cesar.pruebacolor;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;


public class SampleActivity extends Activity {

    Intent sampleIntent;
    Button iniciar, eliminar;
    String TAG = "tag";

    //Función inicial que crea la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Permite que la actividad utilice la vista creada en layout/activity_samples.xml
        setContentView(R.layout.activity_sample);

        isWriteStoragePermissionGranted();
        isCameraPermissionGranted();
        isInternetPermissionGranted();

        final int resultado[] = {0,0,0};
        final RadioButton r1=(RadioButton)findViewById(R.id.r_rojo);
        final RadioButton r2=(RadioButton)findViewById(R.id.r_azul);
        final RadioButton r3=(RadioButton)findViewById(R.id.r_verde);

        eliminar = (Button) findViewById(R.id.eliminar);
        // Inicializa el texto editable, en donde el usuario ingresará nombre y direciion IP
        iniciar = (Button) findViewById(R.id.button);
        iniciar.setText("Siguiente");

        sampleIntent = new Intent(SampleActivity.this, Camara_activity.class);

        iniciar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Corroborar si algun radio button fue presionado

                resultado[0]=0;
                resultado[1]=0;
                resultado[2]=0;

                if(r1.isChecked()){
                    //Color Rojo
                    resultado[0]=3;
                }
                if(r2.isChecked()){
                    //Color Azul
                    resultado[1]=3;
                }
                if(r3.isChecked()){
                    //Color Verde
                    resultado[2]=3;
                }

                if(resultado[0]==0 && resultado[1]==0 && resultado[2]==0){
                    Toast toast = Toast.makeText(getApplicationContext(), "Seleccione una opcion", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    //Enviar datos a siguiente activity de camara
                    //Bundle b=new Bundle();
                    //b.putIntArray("resultado", resultado);
                    //Intent i=new Intent(getApplicationContext(), Camara_activity.class);
                    //i.putExtras(b);
                    sampleIntent.putExtra("resultado", resultado);
                    startActivity(sampleIntent);

                    r1.setChecked(false);
                    r2.setChecked(false);
                    r3.setChecked(false);
                    resultado[0]=0;
                    resultado[1]=0;
                    resultado[2]=0;
                }

            }
        });


        eliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Corroborar si algun radio button fue presionado
                    r1.setChecked(false);
                    r2.setChecked(false);
                    r3.setChecked(false);
                    resultado[0]=0;
                    resultado[1]=0;
                    resultado[2]=0;
                }

            }
        );}



    public  boolean isCameraPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    public  boolean isInternetPermissionGranted() {
        Log.v(TAG,"Inside");
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.INTERNET)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
}
