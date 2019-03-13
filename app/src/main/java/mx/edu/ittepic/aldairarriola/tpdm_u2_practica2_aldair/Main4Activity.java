package mx.edu.ittepic.aldairarriola.tpdm_u2_practica2_aldair;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Properties;

public class Main4Activity extends AppCompatActivity {
    Button search;
    Button upda;
    EditText s,n,d;
    String t,f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        search = findViewById(R.id.btnbuscar);
        upda = findViewById(R.id.btnactualizar);
        s = findViewById(R.id.buscactualiza);
        n = findViewById(R.id.actnom);
        d = findViewById(R.id.actdom);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });

        upda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualiza();
            }
        });
    }

    public void buscar(){
        Propietario a = new Propietario(this);
        String mensaje ="";

        Propietario[] res = a.consultar(s.getText().toString());
        if(res!=null){
            d.setText(res[0].getDomicilio());
            n.setText(res[0].getNombre());
            mensaje = "Datos encontrados" ;

        }else{
            mensaje = "Error! algo salió mal:\n" + a.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();

    }


    private void actualiza(){
        Propietario a = new Propietario(this);
        String mensaje;

        boolean respuesta = a.actualizarpropietatrio(s.getText().toString(),
                n.getText().toString(),d.getText().toString());

        if (respuesta){
            mensaje = "Se actualizó correctamente el propietario " + n.getText().toString();
        }else{
            mensaje = "Error! algo salió mal: "+ a.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();

    }
}
