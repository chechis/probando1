package jcalv.probando1.inicio;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import jcalv.probando1.MainActivity;
import jcalv.probando1.almacenamiento.BaseDatos;
import jcalv.probando1.almacenamiento.Estructura;

public class ServicioInicio {

    private String nombre;
    private String contrasena;
    private Activity activity;
    private Context context;
    private MainActivity mainActivity;

    public ServicioInicio(String nombre, String contrasena, Activity activity) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.activity = activity;
    }

    public void almacenarUsuario (String nombre, String contrasena, BaseDatos baseDatos){
        int aux = 0;
        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        ContentValues content = new ContentValues();
        if (nombre != null && contrasena != null) {
            String comparador1;
            Cursor cursor= sq.rawQuery("SELECT * FROM "+ Estructura.EstructuraBase.TABLE_NAME, null);
            if (cursor.moveToFirst()){

                do {
                    Log.i("AG", cursor.getString(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_CLIENTE)));

                    comparador1 = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_CLIENTE));
                    if (comparador1.equals(nombre)){
                        aux = 1;
                    }
                }while (cursor.moveToNext());
            }

            if (aux==1){
                Toast.makeText(activity, "El usuario ya existe", Toast.LENGTH_LONG).show();
                
            }else {
                content.put(Estructura.EstructuraBase.COLUMN_NAME_CLIENTE, nombre);
                content.put(Estructura.EstructuraBase.COLUMN_NAME_CONTRASENA, contrasena);
                sq.insert(Estructura.EstructuraBase.TABLE_NAME, null, content);
                Toast.makeText(activity, "Usuario " + nombre + " ha sido guardado", Toast.LENGTH_SHORT).show();

            }
        }
        sq.close();
    }

    public int confirmarUsuario (String nombre, String contrasena, BaseDatos baseDatos){
        int aux = 0;
        int aux2 = 0;

        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        ContentValues content = new ContentValues();
        if (nombre != null && contrasena != null) {
            String comparador1;
            String comparador2;
            Cursor cursor= sq.rawQuery("SELECT * FROM "+ Estructura.EstructuraBase.TABLE_NAME, null);
            if (cursor.moveToFirst()){

                do {
                    Log.i("Usuario", cursor.getString(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_CLIENTE)));

                    Log.i("Contrasena", cursor.getString(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_CONTRASENA)));

                    comparador1 = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_CLIENTE));

                    comparador2 = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_CONTRASENA));


                    if (comparador1.equals(nombre)){
                        aux = 1;
                    }
                    if (comparador2.equals(contrasena)){
                        aux2 = 1;
                    }
                }while (cursor.moveToNext());
            }


            if (aux != 1){
                Toast.makeText(activity, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
            if (aux2 !=1){
                Toast.makeText(activity, "Contraseña no encontrada", Toast.LENGTH_SHORT).show();
            }

            if (aux==1 && aux2==1){
                aux = 2;

            }else {
                Toast.makeText(activity, "Crea una sesión", Toast.LENGTH_SHORT).show();
            }
        }
        sq.close();
        return aux;

    }




}
