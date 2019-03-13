package mx.edu.ittepic.aldairarriola.tpdm_u2_practica2_aldair;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Seguro {
    private BaseDatos base;
    private String idseguro;
    private String descripcion;
    private String fechaseguro;
    private int tipo;
    private String telefono;
    protected String error;

    public Seguro(Activity activity) {
        base = new BaseDatos(activity, "aseguradora", null, 1);
    }

    public Seguro(String idseguro, String descripcion, String fechaseguro,
                       int tipo, String telefono) {
        this.idseguro = idseguro;
        this.descripcion = descripcion;
        this.fechaseguro = fechaseguro;
        this.tipo = tipo;
        this.telefono = telefono;
    }

    public String getIdseguro() {
        return idseguro;
    }

    public void setIdseguro(String idseguro) {
        this.idseguro = idseguro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaseguro() {
        return fechaseguro;
    }

    public void setFechaseguro(String fechaseguro) {
        this.fechaseguro = fechaseguro;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean insertarSeguro(Seguro seguro){
        try {

            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("IDSEGURO",seguro.getIdseguro());
            datos.put("TELEFONO",seguro.getTelefono());
            datos.put("DESCRIPCION", seguro.getDescripcion());
            datos.put("FECHA", seguro.getFechaseguro());
            datos.put("TIPO",seguro.getTipo());

            long resultado = transaccionInsertar.insert("SEGURO",   null, datos);
            transaccionInsertar.close();
            if(resultado == -1) return false;

        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public  boolean eliminarSeguro(String desc){
        int resultado;
        try{
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            String[] claves={desc};
            resultado = transaccionEliminar.delete("SEGURO","IDSEGURO=?",claves);
            transaccionEliminar.close();
        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return resultado >0;
    }

    public Seguro[] selectall(String t){

        try{
            Seguro[] a = null;
            SQLiteDatabase busqueda = base.getReadableDatabase();
            String sql = "SELECT * FROM SEGURO WHERE TELEFONO = "+t;
            Cursor c = busqueda.rawQuery(sql,null);

            if(c.moveToFirst()){
                a = new Seguro[c.getCount()];
                int pos = 0;
                do{
                    a[pos]=new Seguro(c.getString(0),c.getString(1),c.getString(2),
                            c.getInt(4),c.getString(2));
                    pos++;
                }while(c.moveToNext());
            }
            return a;
        }catch (SQLiteException e){
            return null;
        }

    }

    public Seguro[] consultar( String desc){

        try{
            Seguro[] a = null;
            SQLiteDatabase busqueda = base.getReadableDatabase();
            String sql = "SELECT * FROM SEGURO WHERE IDSEGURO ="+ "'" + desc + "'";
            Cursor c = busqueda.rawQuery(sql,null);

            if(c.moveToFirst()){
                a = new Seguro[c.getCount()];
                int pos = 0;
                do{
                    a[pos]=new Seguro(c.getString(0),c.getString(1),c.getString(2),
                            c.getInt(3),c.getString(4));
                    pos++;
                }while(c.moveToNext());
            }
            return a;
        }catch (SQLiteException e){
            return null;
        }
    }

    public boolean actualizar(String i, String d, int t ){
        try{

            SQLiteDatabase actualizar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", d);
            datos.put("TIPO",t);

            long resultado = actualizar.update("SEGURO",datos,"IDSEGURO=?",
                    new String[]{i});
            actualizar.close();
            if(resultado == -1) return false;
        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }



}
