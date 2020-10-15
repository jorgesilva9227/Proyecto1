package co.edu.sena.oftalmologia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity<locationManager> extends AppCompatActivity implements LocationListener {
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
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, VerOftalmologiaActivity.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });

        String url = "https://jasilva.000webhostapp.com/index.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arreglo = response.getJSONArray("oftalmologias");
                    for (int i = 0; i < arreglo.length(); i++) {
                        JSONObject value = arreglo.getJSONObject(i);
                        JSONObject ub = value.getJSONObject("ubicacion");
                        Ubicacion ubicacion = new Ubicacion(ub.getDouble("lat"), ub.getDouble("lon"));
                        Oftalmologia o = new Oftalmologia(value.getString("nombre"), value.getString("direccion"), ubicacion, value.getString("horario"), value.getString("foto"));
                        //Toast.makeText(MainActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
                        Informacion.data.add(o);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Informacion.datatemp = Informacion.data;
                llenar_lista();
                //Log.d("TAG", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.getMessage());
            }
        });

        /*StringRequest request =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Informacion.data.clear();
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray arreglo = obj.getJSONArray("oftalmologias");
                    for (int i = 0; i < arreglo.length(); i++) {
                        JSONObject value = arreglo.getJSONObject(i);
                        JSONObject ub = value.getJSONObject("ubicacion");
                        Ubicacion ubicacion = new Ubicacion(ub.getDouble("lat"), ub.getDouble("lon"));
                        Oftalmologia o = new Oftalmologia(value.getString("nombre"), value.getString("direccion"), value.getString("horario"), ubicacion, value.getString("foto"));
                        Informacion.data.add(o);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Informacion.datatemp = Informacion.data;
                llenar_lista();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
        Volley.newRequestQueue(MainActivity.this).add(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                break;
        }
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

   /* public void ordenar_distancia(View view) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Collections.sort(Informacion.data);
        llenar_lista();
    }*/

    private void llenar_lista() {
            ArrayList<Oftalmologia> data= Informacion.data;
            ListView lista = findViewById(R.id.lista);
            OftalmologiaAdapter adapter =new OftalmologiaAdapter(MainActivity.this,data);
            lista.setAdapter(adapter);
    }

    public void salir(View view) {
          finish();
    }

    public void listar_all(View view) {
            Informacion.data= Informacion.datatemp;
            llenar_lista();
    }
        /*public void buscar (View view) {
            Informacion.data=Informacion.datatemp;

        }*/
    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}