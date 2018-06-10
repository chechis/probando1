package jcalv.probando1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import jcalv.probando1.almacenamiento.BaseDatosDonantes;
import jcalv.probando1.almacenamiento.Estructura;
import jcalv.probando1.almacenamiento.ServicioDonante;
import jcalv.probando1.modelo.Donante;
import jcalv.probando1.modelo.adapter.Adaptador;

public class MainActivity extends AppCompatActivity implements AlertaDatos.DatosListener {

    private Adaptador adapter;
    private ServicioDonante servicioDonante;
    private BaseDatosDonantes baseDatosDonantes;
    Context context;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

                Adaptador adapter = new Adaptador(getApplicationContext());
                recyclerView = (RecyclerView) findViewById(R.id.recycler_donante);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


            }
        });





    }



    @Override
    public void agregarDonante(Donante donante) {

        BaseDatosDonantes baseDatosDonantes= new BaseDatosDonantes(context);

        servicioDonante= new ServicioDonante(donante, this);

        servicioDonante.guardarDonante(donante, baseDatosDonantes, this);




    }
}
