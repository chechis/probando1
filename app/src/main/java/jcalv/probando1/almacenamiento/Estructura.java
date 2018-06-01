package jcalv.probando1.almacenamiento;

import android.provider.BaseColumns;

public class Estructura {

    public Estructura (){

    }


    public static abstract class EstructuraBase implements BaseColumns{
        public static final String TABLE_INICIO = "Usuario";
        public static final String COLUMN_INICIO_CONTRASENA = "contrasena";
        public static final String COLUMN_INICIO_CLIENTE = "nombre";
    }

}
