package jcalv.probando1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import jcalv.probando1.almacenamiento.BaseDatosDonantes;
import jcalv.probando1.almacenamiento.Estructura;
import jcalv.probando1.almacenamiento.ServicioDonante;
import jcalv.probando1.modelo.Donante;
import jcalv.probando1.modelo.adapter.Adaptador;
import jcalv.probando1.modelo.adapter.AdaptadorBorrar;

public class MainActivity extends AppCompatActivity implements AlertaDatos.DatosListener, AdaptadorBorrar.DonanteListener{

    private ServicioDonante servicioDonante;
    private BaseDatosDonantes baseDatosDonantes;
    private SQLiteDatabase myDatabase;
    Context context;
    RecyclerView recyclerView;
    private List<Donante> listaDonantes;
    private AdaptadorBorrar adaptadorBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDonantes = new ArrayList<>();
        actualizarLista();

        adaptadorBorrar = new AdaptadorBorrar(listaDonantes);
        adaptadorBorrar.setDonanteListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_donante);
        recyclerView.setAdapter(adaptadorBorrar);


        FloatingActionButton btnflotatante = (FloatingActionButton) findViewById(R.id.btn_flotatne);
        btnflotatante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertaDatos alertaDatos = new AlertaDatos();
                alertaDatos.show(getSupportFragmentManager(), "alertaDatos");
            }
        });

        Button boton = (Button) findViewById(R.id.probando);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDatosDonantes = new BaseDatosDonantes(getApplicationContext());
                SQLiteDatabase sq = baseDatosDonantes.getWritableDatabase();
                ContentValues content = new ContentValues();

                content.put(Estructura.EstructuraDonante.COLUMN_NAME_IDENTI, "2");
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_DONANTE, "Julio");
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_APELLIDO, "Alvarez");
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_EDAD, "25");
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_TIPO, "O");
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_RH, "-");
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_PESO, "70");
                content.put(Estructura.EstructuraDonante.COLUMN_NAME_ESTATURA, "168");
                sq.insert(Estructura.EstructuraDonante.TABLE_NAME, null, content);

                Toast.makeText(MainActivity.this, "Usuario ha sido guardado", Toast.LENGTH_SHORT).show();

                //Adaptador adapter = new Adaptador(getApplicationContext());
                //adaptadorBorrar = new AdaptadorBorrar(listaDonantes);
                //adaptadorBorrar.setDonanteListener();
                //recyclerView = (RecyclerView) findViewById(R.id.recycler_donante);
                //recyclerView.setAdapter(adaptadorBorrar);
                //recyclerView.setHasFixedSize(true);
                //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        });

    }

    private void actualizarLista (){
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM "+ Estructura.EstructuraBase.TABLE_NAME, null);
        listaDonantes.clear();
        if (cursor.moveToFirst()){
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
    }


    @Override
    public void agregarDonante(Donante donante) {

        String identificacion = donante.getIdentificacion();
        String nombre = donante.getNombre();
        String apellido = donante.getApellido();
        String edad = donante.getEdad();
        String tipo = donante.getTipo().toString();
        String rh = donante.getRh().toString();
        String peso = donante.getPeso();
        String estatura = donante.getEstatura();

        BaseDatosDonantes baseDatosDonantes = new BaseDatosDonantes(this);
        servicioDonante = new ServicioDonante(donante, this);
        servicioDonante.guardarDonante(identificacion, nombre, apellido,
                edad, tipo, rh, peso, estatura, baseDatosDonantes, this);

        Adaptador adapter = new Adaptador(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_donante);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void deleteDonante(int position) {
        Donante donante = listaDonantes.get(position);
        myDatabase.execSQL("DELETE FROM "+ Estructura.EstructuraDonante.TABLE_NAME+" WHERE ID=?;", new Object[]{donante.getId()});
        actualizarLista();
        adaptadorBorrar.notifyDataSetChanged();
        Toast.makeText(this, "Se ha eliminado el donante", Toast.LENGTH_SHORT).show();
    }
}
