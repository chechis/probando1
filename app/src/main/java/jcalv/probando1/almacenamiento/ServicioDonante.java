package jcalv.probando1.almacenamiento;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import jcalv.probando1.modelo.Donante;

public class ServicioDonante {

    private Donante donante;
    private Activity activity;
    private int posicion;

    public ServicioDonante(int posicion) {
        this.posicion = posicion;
    }


    public ServicioDonante(Donante donante, Activity activity) {
        this.donante = donante;
        this.activity = activity;
    }



    public void guardarDonante (String identificacion, String nombre, String apellido, String edad,
                                String tipo, String rh, String peso, String estatura, BaseDatosDonantes baseDatos, Activity activity){
        int aux = 0;
        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        ContentValues content = new ContentValues();
        if (identificacion != null && nombre != null && apellido != null && edad != null && tipo !=null
                && rh != null && peso != null && estatura != null){
            String comparador;
            Cursor cursor = sq.rawQuery("SELECT * FROM "+ Estructura.EstructuraDonante.TABLE_NAME, null);
            if (cursor.moveToFirst()){
                do {
                    Log.i("AG", cursor.getString(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_DONANTE)));

                    comparador = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_IDENTI));
                    if (comparador.equals(identificacion)){
                        aux = 1;
                    }
                }while (cursor.moveToNext());
            }

            if (aux==1){
                Toast.makeText(activity, "El donante ya existe", Toast.LENGTH_LONG).show();

            }else {
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_IDENTI, identificacion);
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_DONANTE, nombre);
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_APELLIDO, apellido);
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_EDAD, edad);
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_TIPO, tipo);
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_RH, rh);
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_PESO, peso);
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_ESTATURA, estatura);
                sq.insert(Estructura.EstructuraDonante.TABLE_NAME, null, content);
                Toast.makeText(activity, "Usuario " + nombre + " ha sido guardado", Toast.LENGTH_SHORT).show();

            }
        }
        sq.close();

    }

    public void modificarDonante (int posicion, BaseDatos baseDatos, Activity activity){


        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(Estructura.EstructuraDonante.COLUMN_NAME_IDENTI, donante.getIdentificacion());
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_DONANTE, donante.getNombre());
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_APELLIDO, donante.getApellido());
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_EDAD, donante.getEdad());
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_TIPO, donante.getTipo().toString());
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_RH, donante.getRh().toString());
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_PESO, donante.getPeso());
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_ESTATURA, donante.getEstatura());

        sq.update(Estructura.EstructuraDonante.TABLE_NAME, content, donante.getIdentificacion(), null);

        Toast.makeText(activity, "Se ha actualizado el donante" + donante.getNombre(), Toast.LENGTH_SHORT).show();

        sq.close();
    }

    public void eliminarDonante (int posicion, int comparador, BaseDatosDonantes baseDatos, Context context){

        SQLiteDatabase sq = baseDatos.getWritableDatabase();

        sq.execSQL("DELETE FROM "+Estructura.EstructuraDonante.TABLE_NAME+ " WHERE "+comparador+" = \""+posicion+"\"");

        Toast.makeText(context, "Se ha eliminado "+ Estructura.EstructuraDonante._ID, Toast.LENGTH_SHORT).show();
        sq.close();
    }





}
