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
        public static final String COLUMN_NAME_ID = "Identificacion";
        public static final String COLUMN_NAME_DONANTE = "Nombre";
        public static final String COLUMN_NAME_APELLIDO = "Apellido";
        public static final String COLUMN_NAME_EDAD = "Edad";
        public static final String COLUMN_NAME_TIPO = "Tipo";
        public static final String COLUMN_NAME_RH = "Rh";
        public static final String COLUMN_NAME_PESO = "Peso";
        public static final String COLUMN_NAME_ESTATURA = "Estatura";
    }


}
