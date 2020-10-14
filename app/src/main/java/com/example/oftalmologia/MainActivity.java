package com.example.oftalmologia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity<locationManager> extends AppCompatActivity {
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            verifyPermission();
        }
        final ListView lista = findViewById(R.id.lista);
        lista.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, VerOftalmologiaActivity.class);
                intent.putExtra("pos", pos);
                startActivity(intent);
            }
        });
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
}