package mx.edu.ittepic.aldairarriola.tpdm_u2_practica2_aldair;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Main8Activity extends AppCompatActivity {
    EditText descripcion;
    Spinner tipo;
    Button regresar,actualizar, eliminar;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        descripcion = findViewById(R.id.actdescripcion);
        tipo = findViewById(R.id.acttiposeguros);
        regresar = findViewById(R.id.regresarseguro);
        actualizar = findViewById(R.id.actualizarseguro);
        eliminar = findViewById(R.id.eliminarseguro);

        Bundle parametros = getIntent().getExtras();
        descripcion.setText(parametros.getString("descripcion"));
        id = parametros.getString("id");
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarseg();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarseg();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void actualizarseg(){
        Seguro a = new Seguro(this);
        String mensaje;
        int tp = 0;

        if(tipo.getSelectedItemPosition() == 1){
            tp = 1;
        }
        if(tipo.getSelectedItemPosition() == 2){
            tp = 2;
        }


        boolean respuesta = a.actualizar(id,descripcion.getText().toString(),tp);

        if (respuesta){
            mensaje = "Se actualizó correctamente el seguro";
        }else{
            mensaje = "Error! algo salió mal: "+ a.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();

    }

    private void eliminarseg(){
        Seguro a = new Seguro(this);
        String mensaje;

        boolean respuesta = a.eliminarSeguro(id);

        if(respuesta){
            mensaje = "Se Eliminó exitosamente el seguro";

        }else{
            mensaje = "Error! algo salió mal:\n" + a.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();

    }
}
