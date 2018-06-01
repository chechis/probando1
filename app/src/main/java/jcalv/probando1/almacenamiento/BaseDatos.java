package jcalv.probando1.almacenamiento;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    private static final String tipo = " TEXT";
    private static final String coma = ",";

    private static final String Sentencia =
            "CREATE TABLE " + Estructura.EstructuraBase.TABLE_INICIO + " ("
            + Estructura.EstructuraBase._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Estructura.EstructuraBase.COLUMN_INICIO_CLIENTE + tipo + coma +
            Estructura.EstructuraBase.COLUMN_INICIO_CONTRASENA + tipo + " )";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Donantes.sqLite";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Estructura.EstructuraBase.TABLE_INICIO;

    public BaseDatos (Context context){

        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Sentencia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
