package mx.edu.ittepic.aldairarriola.tpdm_u2_practica2_aldair;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main6Activity extends AppCompatActivity {
    ListView lists;
    Seguro vector[];
    String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        lists = findViewById(R.id.listaseguros);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarAlerta(position);
            }
        });

    }

    private void mostrarAlerta(final int pos){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Atención").setMessage("Deseas modificar/eliminar el seguro " + vector[pos].getIdseguro()+"?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        invocarEliminarActualizar(pos);
                    }
                })
                .setNegativeButton("NO",null)
                .show();
    }

    private void invocarEliminarActualizar(int pos){
        Intent eliminactualiza = new Intent(this, Main8Activity.class );
        eliminactualiza.putExtra("id",vector[pos].getIdseguro());
        eliminactualiza.putExtra("descripcion",vector[pos].getDescripcion());
        eliminactualiza.putExtra("fecha",vector[pos].getFechaseguro());
        eliminactualiza.putExtra("telefono",vector[pos].getTelefono());
        eliminactualiza.putExtra("tipo",vector[pos].getTipo());

        startActivity(eliminactualiza);
    }


    @Override
    protected void onStart() {
        Intent myIntent = getIntent();
        tel = myIntent.getStringExtra("telbuscar");

        Seguro seguro = new Seguro(this);
        vector = seguro.selectall(tel);
        String[] desfecSeguro = null;

        if(vector == null){
            desfecSeguro = new String[1];
            desfecSeguro[0] = "No hay seguros capturados"  ;
        }else {
            desfecSeguro = new String[vector.length];
            for(int i= 0; i<vector.length; i++){
                Seguro temp = vector[i];
                int type = temp.getTipo();
                String tipotexto="Auto";
                if(type==1){
                    tipotexto="Casa";
                }
                if(type==2){
                    tipotexto="Medico";
                }


                desfecSeguro[i] = temp.getDescripcion()+"\n Tipo: "+tipotexto;
            }
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, desfecSeguro);
        lists.setAdapter(adaptador);


        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuseguro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.agregarseguro:
                Intent a1 =new Intent(Main6Activity.this,Main7Activity.class);
                a1.putExtra("telpropietario",tel);
                startActivity(a1);
                break;
            case R.id.atras:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
