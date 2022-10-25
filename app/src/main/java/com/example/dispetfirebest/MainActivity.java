package com.example.dispetfirebest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dispetfirebest.modelo.Alumno;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Button btregistrar;
    EditText etnombre, etapellido, etedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llamadobtregistrar();
        llamadoBD();
        etnombre = (EditText) findViewById(R.id.etnombre);
        etapellido = (EditText) findViewById(R.id.etapellido);
        etedad = (EditText) findViewById(R.id.etedad);

    }

    private void llamadoBD() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    public void llamadobtregistrar(){
        btregistrar = (Button) findViewById(R.id.btregistrar);
        btregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etnombre = (EditText) findViewById(R.id.etnombre);
                etapellido = (EditText) findViewById(R.id.etapellido);
                etedad = (EditText) findViewById(R.id.etedad);
                String nombre = etnombre.getText().toString();
                String apellido = etapellido.getText().toString();
                try {
                    int edad = Integer.parseInt(etedad.getText().toString());
                    String key = UUID.randomUUID().toString();
                    Alumno a = new Alumno(key,nombre,apellido,edad);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("alumno").child(key);
                    myRef.setValue(a);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "debe ser un numero", Toast.LENGTH_SHORT).show();
                }





            }
        });

    }
}