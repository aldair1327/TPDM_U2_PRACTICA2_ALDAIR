package mx.edu.ittepic.aldairarriola.tpdm_u2_practica2_aldair;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.Properties;

public class Propietario {
    private BaseDatos base;
    private String telefono;
    private String nombre;
    private String domicilio;
    private String fecha;
    protected String error;

    public Propietario(Activity activity) {
        base = new BaseDatos(activity, "aseguradora", null, 1);
    }

    public Propietario(String telefono, String nombre, String domicilio, String fecha) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean insertarPropietario(Propietario propietario){
        try {
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("TELEFONO",propietario.getTelefono());
            datos.put("NOMBRE", propietario.getNombre());
            datos.put("DOMICILIO", propietario.getDomicilio());
            datos.put("FECHA",propietario.getFecha());

            long resultado = transaccionInsertar.insert("PROPIETARIO",null, datos);

            if(resultado == -1) return false;
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public  boolean eliminarPropietario(String desc){
        int resultado;
        int res2;
        try{
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            String[] claves={desc};
            res2 = transaccionEliminar.delete("SEGURO","TELEFONO=?",claves);
            resultado = transaccionEliminar.delete("PROPIETARIO","TELEFONO=?",claves);
            transaccionEliminar.close();

        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return resultado+res2 >0;
    }

    public Propietario[] selectall(){

        try{
            Propietario[] a = null;
            SQLiteDatabase busqueda = base.getReadableDatabase();
            String sql = "SELECT * FROM PROPIETARIO";
            Cursor c = busqueda.rawQuery(sql,null);

            if(c.moveToFirst()){
                a = new Propietario[c.getCount()];
                int pos = 0;
                do{
                    a[pos]=new Propietario(c.getString(0),c.getString(1),c.getString(2),
                            c.getString(3));
                    pos++;
                }while(c.moveToNext());
            }
            return a;
        }catch (SQLiteException e){
            return null;
        }

    }

    public Propietario[] consultar( String desc){

        try{
            Propietario[] a = null;
            SQLiteDatabase busqueda = base.getReadableDatabase();
            String sql = "SELECT * FROM PROPIETARIO WHERE TELEFONO ="+ "'" + desc + "'";
            Cursor c = busqueda.rawQuery(sql,null);

            if(c.moveToFirst()){
                a = new Propietario[c.getCount()];
                int pos = 0;
                do{
                    a[pos]=new Propietario(c.getString(0),c.getString(1),c.getString(2),
                            c.getString(3));
                    pos++;
                }while(c.moveToNext());
            }
            return a;
        }catch (SQLiteException e){
            return null;
        }
    }

    public boolean actualizarpropietatrio(String t,String a, String b){
        try{

            SQLiteDatabase actualizar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("NOMBRE", a);
            datos.put("DOMICILIO", b);

            long resultado = actualizar.update("PROPIETARIO",datos,"TELEFONO=?",
                    new String[]{t});

            actualizar.close();
            if(resultado == -1) return false;
        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }


}