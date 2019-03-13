package mx.edu.ittepic.aldairarriola.tpdm_u2_practica2_aldair;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main5Activity extends AppCompatActivity {

    Button borrar;
    EditText aborrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        borrar = findViewById(R.id.btneliminar);
        aborrar = findViewById(R.id.eliminarprop);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });

    }

    private void eliminar(){
        Propietario a = new Propietario(this);
        String mensaje;

        boolean respuesta = a.eliminarPropietario(aborrar.getText().toString());

        if(respuesta){
            mensaje = "Se Eliminó exitosamente el propietario " + aborrar.getText().toString();
        }else{
            mensaje = "Error! algo salió mal:\n" + a.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();
    }

}
