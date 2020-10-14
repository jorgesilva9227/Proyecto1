package com.example.oftalmologia;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class VerOftalmologiaActivity extends AppCompatActivity implements LocationListener {
    Oftalmologia o;
    LocationManager locationManager;
    Location destino;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_oftalmologia);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            verifyPermission();
        }
        int pos =getIntent().getIntExtra("pos", -1);
        o = Informacion.data.get(pos);
        destino=new Location(o.getNombre());
        destino.getLatitude(o.getUbicacion().getLat());
        destino.getLongitude(o.getUbicacion().getLon());
        llenar_cajas();
    }

    private void verifyPermission(){
        int permsRequestCode =100;
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        int accessFinePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int accessCoarsePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (accessFinePermission == PackageManager.PERMISSION_GRANTED && accessCoarsePermission == PackageManager.PERMISSION_GRANTED) {

        }else {
            requestPermissions(perms,permsRequestCode);
        }
    }
    @Override
    public void onRequestPermissionResult(int requestCode, String permission[], int[] grantResults) {
        switch (requestCode){
            case 100:
            break;
        }
    }

    private void distancia(Location p_o, Location p_d){
        TextView lbDistancia = findViewById(R.id.lbDistancia);
        if(p_o!=null) {
            double dist= p_o.distanceTo(p_d);
            o.setDistancia(dist);
            lbDistancia.setText("Distancia="+dist);
        }else {
            lbDistancia.setText("Distancia= Desconocida");
        }
    }
    private void llenar_cajas(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED {
            return;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    Location punto_actual = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    distancia(punto_actual, destino);
}
