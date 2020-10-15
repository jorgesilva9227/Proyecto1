package co.edu.sena.oftalmologia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CitaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);
    }

    public void Cita(View view){
        EditText nom = findViewById(R.id.txtNom);
        EditText txtDoc = findViewById(R.id.txtDoc);
        EditText txtCel = findViewById(R.id.txtCel);
        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtFecha = findViewById(R.id.txtFecha);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> cita = new HashMap<>();
        cita.put("documento", txtDoc.getText().toString());
        cita.put("nombre", nom.getText().toString());
        cita.put("email", txtEmail.getText().toString());
        cita.put("telefono", txtCel.getText().toString());
        cita.put("fecha", txtFecha.getText().toString());
        db.collection("citas")
                .add(cita)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        CitaActivity.this.finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CitaActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}