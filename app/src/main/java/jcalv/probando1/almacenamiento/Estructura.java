package jcalv.probando1.almacenamiento;

import android.provider.BaseColumns;

import jcalv.probando1.modelo.Donante;

public class Estructura {

    public Estructura (){

    }


    public static abstract class EstructuraBase implements BaseColumns{
        public static final String TABLE_NAME = "Usuario";
        public static final String COLUMN_NAME_CONTRASENA = "contrasena";
        public static final String COLUMN_NAME_CLIENTE = "nombre";
    }

    public static abstract class EstructuraDonante implements BaseColumns{
        public static final String TABLE_NAME = "Donante";
        public static final String COLUMN_NAME_IDENTI = "identificacion";
        public static final String COLUMN_NAME_DONANTE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_EDAD = "edad";
        public static final String COLUMN_NAME_TIPO = "tipo";
        public static final String COLUMN_NAME_RH = "rh";
        public static final String COLUMN_NAME_PESO = "peso";
        public static final String COLUMN_NAME_ESTATURA = "estatura";
    }


}
