package mx.edu.ittepic.aldairarriola.tpdm_u2_practica2_aldair;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {

    EditText nombre, domicilio, fecha, telefono;
    Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nombre = findViewById(R.id.nomb);
        domicilio = findViewById(R.id.domi);
        fecha = findViewById(R.id.fech);
        telefono = findViewById(R.id.tele);
        agregar = findViewById(R.id.btninsertar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
    }


    private void insertar(){
        //Validar campos vacios
        String mensaje="";
        Propietario propietario = new Propietario(this);
        boolean respuesta = propietario.insertarPropietario(new Propietario(telefono.getText().toString(),
                nombre.getText().toString(),domicilio.getText().toString(),fecha.getText().toString()));

        if(respuesta){
            mensaje = "Se pudo insertar al Propietario " + nombre.getText() ;
        }else {
            mensaje = "Error! no se pudo crear el proyecto, respuesta de SQLITE: " + propietario.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();
    }
}
