package mx.edu.ittepic.aldairarriola.tpdm_u2_practica2_aldair;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button consultar;
    EditText bus;
    TextView nom;
    TextView dom;
    TextView fec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        consultar = findViewById(R.id.btnconsultar);
        bus = findViewById(R.id.txttelefono);
        nom = findViewById(R.id.txtnombre);
        dom = findViewById(R.id.txtdomicilio);
        fec = findViewById(R.id.txtfecha);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });
    }

    private void buscar(){
        Propietario a = new Propietario(this);
        String mensaje ="";

        Propietario[] res = a.consultar(bus.getText().toString());
        if(res!=null){
            nom.setText(res[0].getNombre());
            dom.setText(res[0].getDomicilio());
            fec.setText(res[0].getFecha());
            mensaje = "Datos encontrados";

        }else{
            mensaje = "Error! algo salió mal:\n" + a.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();


    }

}
