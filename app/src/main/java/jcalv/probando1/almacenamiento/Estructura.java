package jcalv.probando1.almacenamiento;

import android.provider.BaseColumns;

public class Estructura {

    public Estructura (){

    }


    public static abstract class EstructuraBase implements BaseColumns{
        public static final String TABLE_NAME = "Usuario";
        public static final String COLUMN_NAME_CONTRASENA = "contrasena";
        public static final String COLUMN_NAME_CLIENTE = "nombre";
    }

}
