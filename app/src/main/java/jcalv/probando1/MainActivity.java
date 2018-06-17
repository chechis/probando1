package jcalv.probando1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


import jcalv.probando1.almacenamiento.BaseDatosDonantes;
import jcalv.probando1.almacenamiento.Estructura;
import jcalv.probando1.almacenamiento.ServicioDonante;
import jcalv.probando1.intento.Main2Activity;
import jcalv.probando1.modelo.Donante;

import jcalv.probando1.modelo.adapter.AdaptadorBorrar;

public class MainActivity extends AppCompatActivity implements AlertaDatos.DatosListener, AdaptadorBorrar.DonanteListener,
                                                        AlertaEditar.EditarListener{

    private ServicioDonante servicioDonante;
    private SQLiteDatabase myDatabase;
    RecyclerView recyclerView;
    private List<Donante> listaDonantes;
    private AdaptadorBorrar adaptadorBorrar;
    int auxiliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDonantes = new ArrayList<>();
        final EditText editText = (EditText) findViewById(R.id.txt_buscar);

        FloatingActionButton btnflotatante = (FloatingActionButton) findViewById(R.id.btn_flotatne);
        btnflotatante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertaDatos alertaDatos = new AlertaDatos();
                alertaDatos.show(getSupportFragmentManager(), "alertaDatos");
            }
        });

        actualizarLista();
        llenandoAdapter(listaDonantes);

        ImageButton button = (ImageButton) findViewById(R.id.btn_buscar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarDonante(editText);
                Toast.makeText(MainActivity.this, "Presiona en la X para regresar a la lista completa", Toast.LENGTH_SHORT).show();
                editText.setText("");
            }
        });
        ImageButton buttonLimpiar = (ImageButton) findViewById(R.id.btn_limpiar_busqueda);
        buttonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresar();
            }
        });


    }

    private void actualizarLista (){
        BaseDatosDonantes baseDatosDonantes = new BaseDatosDonantes(this);
        myDatabase = baseDatosDonantes.getWritableDatabase();

        Cursor cursor = myDatabase.rawQuery("SELECT * FROM "+ Estructura.EstructuraDonante.TABLE_NAME+";", null);
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
        cursor.close();
    }

    private void buscarDonante (EditText buscar){
        BaseDatosDonantes baseDatosDonantes = new BaseDatosDonantes(this);
        ServicioDonante servicioDonante = new ServicioDonante(this);
        List<Donante> listaCompleta= servicioDonante.buscarDonante(buscar, listaDonantes, adaptadorBorrar,baseDatosDonantes, this);

        llenandoAdapter(listaCompleta);
    }
    private void regresar (){
        listaDonantes = new ArrayList<>();
        actualizarLista();
        llenandoAdapter(listaDonantes);
    }

    private void llenandoAdapter(List<Donante> lista){
        adaptadorBorrar = new AdaptadorBorrar(lista);
        adaptadorBorrar.setDonanteListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_donante);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorBorrar);
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
        actualizarLista();
        adaptadorBorrar.notifyDataSetChanged();

    }

    @Override
    public void deleteDonante(int position) {
        BaseDatosDonantes baseDatosDonantes = new BaseDatosDonantes(this);
        Donante donante = listaDonantes.get(position);
        ServicioDonante servicioDonante = new ServicioDonante(donante, this);
        servicioDonante.eliminarDonante(donante, baseDatosDonantes, this);
        actualizarLista();
        adaptadorBorrar.notifyDataSetChanged();

    }

    @Override
    public void editarDonante(int position) {
        Donante donante = listaDonantes.get(position);
        int id = donante.getId();
        String identificacion = donante.getIdentificacion();
        String nombre = donante.getNombre();
        String apellido = donante.getApellido();
        String edad = donante.getEdad();
        String tipo = donante.getTipoTexto();
        String rh = donante.getRhTexto();
        String peso = donante.getPeso();
        String estatura = donante.getEstatura();

        AlertaEditar alertaEditar = new AlertaEditar();
        alertaEditar.show(getSupportFragmentManager(), "Editar donante");

        alertaEditar.getTxtID(id);
        alertaEditar.getTxtIdentificacion(identificacion);
        alertaEditar.getTxtNombre(nombre);
        alertaEditar.getTxtApellido(apellido);
        alertaEditar.getTxtEdad(edad);
        alertaEditar.getTxtTipo(tipo);
        alertaEditar.getTxtRh(rh);
        alertaEditar.getTxtPeso(peso);
        alertaEditar.getTxtEstatura(estatura);
    }


    @Override
    public void editarDonante(Donante donante) {

        int id = donante.getId();
        String identificacion = donante.getIdentificacion();
        String nombre = donante.getNombre();
        String apellido = donante.getApellido();
        String edad = donante.getEdad();
        String tipo = donante.getTipo().toString();
        String rh = donante.getRh().toString();
        String peso = donante.getPeso();
        String estatura = donante.getEstatura();

        BaseDatosDonantes baseDatosDonantes = new BaseDatosDonantes(this);
        ServicioDonante servicioDonante = new ServicioDonante(donante, this);
        servicioDonante.modificarDonante(id, nombre, apellido, edad, tipo, rh, peso, estatura,
                baseDatosDonantes, this);

        actualizarLista();
        adaptadorBorrar.notifyDataSetChanged();

    }
}
