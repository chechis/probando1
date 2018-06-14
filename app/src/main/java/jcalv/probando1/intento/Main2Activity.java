package jcalv.probando1.intento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jcalv.probando1.MainActivity;
import jcalv.probando1.R;
import jcalv.probando1.almacenamiento.BaseDatosDonantes;
import jcalv.probando1.almacenamiento.Estructura;
import jcalv.probando1.almacenamiento.ServicioDonante;
import jcalv.probando1.modelo.Donante;
import jcalv.probando1.modelo.adapter.AdaptadorBorrar;

public class Main2Activity extends AppCompatActivity implements AdaptadorBorrar.DonanteListener{

    private BaseDatosDonantes baseDatosDonantes;
    private SQLiteDatabase myDatabase;
    Context context;
    RecyclerView recyclerView;
    private List<Donante> listaDonantes;
    private AdaptadorBorrar adaptadorBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listaDonantes = new ArrayList<>();

        myDatabase = openOrCreateDatabase("MyIntentando", MODE_PRIVATE, null);
        myDatabase.execSQL("DROP TABLE IF EXISTS INTENTANDO;");
        myDatabase.execSQL("CREATE TABLE INTENTANDO ("+
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "IDENTIFICACION VARCHAR(100) NOT NULL, "+
                "NOMBRE VARCHAR(100) NOT NULL, "+
                "APELLIDO VARCHAR(100) NOT NULL, "+
                "EDAD VARCHAR(100) NOT NULL, "+
                "TIPO VARCHAR(100) NOT NULL, "+
                "RH VARCHAR(100) NOT NULL, "+
                "PESO VARCHAR(100) NOT NULL, "+
                "ESTATURA VARCHAR(100) NOT NULL);");

        insertarDatos();
        actualizarLista();


        adaptadorBorrar = new AdaptadorBorrar(listaDonantes);
        adaptadorBorrar.setDonanteListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_itento);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorBorrar);
    }

    private void insertarDatos(){

        final String INSERT_DONANTE = "INSERT INTO INTENTANDO (IDENTIFICACION, NOMBRE, APELLIDO, EDAD, TIPO, RH, PESO, ESTATURA) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        myDatabase.execSQL(INSERT_DONANTE, new Object[]{"12", "cesar", "alvarez", "26", "O", "negativo", "70", "168"});
        myDatabase.execSQL(INSERT_DONANTE, new Object[]{"13", "julio", "guillen", "27", "O", "negativo", "70", "168"});


        Toast.makeText(Main2Activity.this, "Usuario ha sido guardado", Toast.LENGTH_SHORT).show();
    }

    private void actualizarLista (){
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM INTENTANDO;", null);
        listaDonantes.clear();
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex("ID"));
                String identificacion = cursor.getString(cursor.getColumnIndex("IDENTIFICACION"));
                String nombre = cursor.getString(cursor.getColumnIndex("NOMBRE"));
                String apellido = cursor.getString(cursor.getColumnIndex("APELLIDO"));
                String edad = cursor.getString(cursor.getColumnIndex("EDAD"));
                String tipo = cursor.getString(cursor.getColumnIndex("TIPO"));
                String rh = cursor.getString(cursor.getColumnIndex("RH"));
                String peso = cursor.getString(cursor.getColumnIndex("PESO"));
                String estatura = cursor.getString(cursor.getColumnIndex("ESTATURA"));
                listaDonantes.add(new Donante(id, identificacion, nombre, apellido, edad, tipo, rh, peso, estatura));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void deleteDonante(int position) {
        Donante donante = listaDonantes.get(position);
        myDatabase.execSQL("DELETE FROM INTENTANDO WHERE ID=?;", new Object[]{donante.getId()});
        actualizarLista();
        adaptadorBorrar.notifyDataSetChanged();
        Toast.makeText(this, "Se ha eliminado el donante", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editarDonante(int position) {
        Donante donante = listaDonantes.get(position);
        myDatabase.execSQL("DELETE FROM INTENTANDO WHERE ID=?;", new Object[]{donante.getId()});
        actualizarLista();
        adaptadorBorrar.notifyDataSetChanged();
        Toast.makeText(this, "Se ha editado el donante", Toast.LENGTH_SHORT).show();
    }
}
