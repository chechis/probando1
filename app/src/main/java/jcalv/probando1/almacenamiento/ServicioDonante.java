package jcalv.probando1.almacenamiento;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import jcalv.probando1.modelo.Donante;

public class ServicioDonante {

    private Donante donante;
    private Activity activity;


    public ServicioDonante(Donante donante, Activity activity) {
        this.donante = donante;
        this.activity = activity;
    }



    public void guardarDonante (String identificacion, String nombre,
            String apellido, String edad, String tipo, String rh, String peso, String estatura,
                                BaseDatosDonantes baseDatos, Activity activity){
        int aux = 0;
        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        ContentValues content = new ContentValues();
        if (identificacion != null){
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

    public void modificarDonante (int id, String nombre, String apellido, String edad, String tipo,
                                  String rh, String peso, String estatura, BaseDatosDonantes baseDatos, Activity activity){


        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        ContentValues content = new ContentValues();
        String comparador = Estructura.EstructuraDonante.COLUMN_NAME_ID +" LIKE "+ id;
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_DONANTE, nombre);
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_APELLIDO, apellido);
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_EDAD, edad);
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_TIPO, tipo);
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_RH, rh);
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_PESO, peso);
        content.put(Estructura.EstructuraDonante.COLUMN_NAME_ESTATURA, estatura);

        sq.update(Estructura.EstructuraDonante.TABLE_NAME, content, comparador, null);

        Toast.makeText(activity, "Se ha actualizado el donante  " + nombre, Toast.LENGTH_SHORT).show();

        sq.close();
    }

    public void eliminarDonante (Donante donante, BaseDatosDonantes baseDatos, Activity activity){

        SQLiteDatabase sq = baseDatos.getWritableDatabase();

        sq.execSQL("DELETE FROM "+ Estructura.EstructuraDonante.TABLE_NAME+" WHERE ID=?;",
                new Object[]{donante.getId()});

        Toast.makeText(activity, "Se ha eliminado el donante", Toast.LENGTH_SHORT).show();
        sq.close();
    }

    public void buscarDonante (String identificacion, BaseDatosDonantes baseDatos, Activity activity){

    }

}
