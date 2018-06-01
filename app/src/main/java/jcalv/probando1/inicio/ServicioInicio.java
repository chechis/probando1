package jcalv.probando1.inicio;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import jcalv.probando1.almacenamiento.BaseDatos;

public class ServicioInicio {

    private String nombre;
    private String contrasena;
    private Activity activity;
    private Context context;

    BaseDatos baseDatos;

    public ServicioInicio(String nombre, String contrasena, Activity activity) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.activity = activity;
    }

    public ServicioInicio(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public void toast (){
        Toast.makeText(activity, "nombre : "+nombre + " contrasena : " + contrasena, Toast.LENGTH_SHORT).show();
    }

    public void agregarUsuario (BaseDatos baseDatos){
        int aux = 0;
        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        

    }



}
