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

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Propietario vector[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listapropietarios);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoseguros(position);
            }
        });
    }

    private void gotoseguros(final int pos){
        Intent myIntent = new Intent(MainActivity.this,Main6Activity.class);
        myIntent.putExtra("telbuscar",""+vector[pos].getTelefono());
        startActivity(myIntent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.crud, menu);
        return true;
    }



    @Override
    protected void onStart() {
        Propietario propietario = new Propietario(this);
        vector = propietario.selectall();
        String[] nombreTelefono = null;

        if(vector == null){
            nombreTelefono = new String[1];
            nombreTelefono[0] = "No hay propietarios capturados";
        }else {
            nombreTelefono = new String[vector.length];
            for(int i= 0; i<vector.length; i++){
                Propietario temp = vector[i];
                nombreTelefono[i] = temp.getNombre()+"\n"+temp.getTelefono();
            }
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, nombreTelefono);
        lista.setAdapter(adaptador);


        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.consult:
                Intent a1 =new Intent(MainActivity.this,Main2Activity.class);
                startActivity(a1);
                break;
            case R.id.insert:
                startActivity(new Intent(MainActivity.this,Main3Activity.class));

                break;
            case R.id.update:
                startActivity(new Intent(MainActivity.this,Main4Activity.class));
                break;
            case R.id.delete:
                startActivity(new Intent(MainActivity.this,Main5Activity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
