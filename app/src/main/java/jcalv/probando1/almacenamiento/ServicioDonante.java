package jcalv.probando1.almacenamiento;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jcalv.probando1.modelo.Donante;
import jcalv.probando1.modelo.adapter.AdaptadorBorrar;

public class ServicioDonante {

    private Donante donante;
    private Activity activity;


    public ServicioDonante(Donante donante, Activity activity) {
        this.donante = donante;
        this.activity = activity;
    }

    public ServicioDonante(Activity activity) {
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

    public List<Donante> buscarDonante (EditText editIdentificacion, List<Donante> listaDonantes, AdaptadorBorrar adaptador, BaseDatosDonantes baseDatos, Activity activity){

        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        listaDonantes = new ArrayList<>();

        if (editIdentificacion!=null && !editIdentificacion.getText().toString().equals("")){

            String comparador= "%" +editIdentificacion.getText().toString()+ "%";

            Cursor cursor = sq.rawQuery("SELECT * FROM "+ Estructura.EstructuraDonante.TABLE_NAME+" WHERE "+ Estructura.EstructuraDonante.COLUMN_NAME_IDENTI+" LIKE ? OR "+ Estructura.EstructuraDonante.COLUMN_NAME_APELLIDO+" LIKE ?;",
                    new String[]{comparador, comparador});

            if (cursor.moveToFirst()){
                listaDonantes.clear();
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_ID));
                    String identificacion = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_IDENTI));
                    String nombre = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_DONANTE));
                    String apellido = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_APELLIDO));
                    String edad = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_EDAD));
                    String tipo = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_TIPO));
                    String rh = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_RH));
                    String peso = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_PESO));
                    String estatura = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_ESTATURA));
                    listaDonantes.add(new Donante(id, identificacion, nombre, apellido, edad, tipo, rh, peso, estatura));
                }while (cursor.moveToNext());

            }
        }else {
            Toast.makeText(activity, "Debes escribir la identificación ", Toast.LENGTH_SHORT).show();
        }
        sq.close();
        return listaDonantes;
    }
}
