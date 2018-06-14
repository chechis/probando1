package jcalv.probando1.almacenamiento;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatosDonantes extends SQLiteOpenHelper {

    private static final String tipo = " TEXT";
    private static final String coma = ",";

    private static final String Sentencia =
            "CREATE TABLE " + Estructura.EstructuraDonante.TABLE_NAME + " ("
                    + Estructura.EstructuraDonante.COLUMN_NAME_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Estructura.EstructuraDonante.COLUMN_NAME_IDENTI + tipo + coma +
                      Estructura.EstructuraDonante.COLUMN_NAME_DONANTE + tipo + coma +
                      Estructura.EstructuraDonante.COLUMN_NAME_APELLIDO + tipo + coma +
                      Estructura.EstructuraDonante.COLUMN_NAME_EDAD + tipo + coma +
                      Estructura.EstructuraDonante.COLUMN_NAME_TIPO + tipo + coma +
                      Estructura.EstructuraDonante.COLUMN_NAME_RH + tipo + coma +
                      Estructura.EstructuraDonante.COLUMN_NAME_PESO + tipo + coma +
                    Estructura.EstructuraDonante.COLUMN_NAME_ESTATURA + tipo + " )";

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "ListaDonantes.sqLite";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Estructura.EstructuraDonante.TABLE_NAME;

    public BaseDatosDonantes (Context context){

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
