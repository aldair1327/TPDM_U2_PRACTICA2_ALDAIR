package mx.edu.ittepic.aldairarriola.tpdm_u2_practica2_aldair;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Main7Activity extends AppCompatActivity {

    EditText id, d, f;
    String t;
    Button insertarseguro;
    Spinner tipo;
    int tp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Intent myIntent = getIntent();
        t = myIntent.getStringExtra("telpropietario");

        insertarseguro = findViewById(R.id.btninsertarseguro);
        id = findViewById(R.id.idseguro);
        d = findViewById(R.id.descripcion);
        f = findViewById(R.id.fechaseguro);
        tipo = findViewById(R.id.tiposeguros);

        insertarseguro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertaS();
            }
        });


    }


    private void insertaS(){
        //Validar campos vacios
        String mensaje="";
        Seguro seguro = new Seguro(this);


        if(tipo.getSelectedItem().equals("Casa")){
            tp = 1;
        }
        if(tipo.getSelectedItem().equals("Medico")){
            tp = 2;
        }


        boolean respuesta = seguro.insertarSeguro(new Seguro(id.getText().toString(),
                d.getText().toString(),f.getText().toString(),tp,t));

        if(respuesta){
            mensaje = "Se pudo insertar el seguro " + d.getText() ;
        }else {
            mensaje = "Error! no se pudo crear el proyecto, respuesta de SQLITE: " + seguro.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();
    }
}
