package jcalv.probando1;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import jcalv.probando1.almacenamiento.Servicio;
import jcalv.probando1.modelo.Donante;
import jcalv.probando1.modelo.adapter.DonanteAdapter;

public class MainActivity extends AppCompatActivity implements AlertaDatos.DatosListener {

    private DonanteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton btnflotatante= (FloatingActionButton) findViewById(R.id.btn_flotatne);
        btnflotatante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertaDatos alertaDatos= new AlertaDatos();
                alertaDatos.show(getSupportFragmentManager(), "alertaDatos");
            }
        });


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        try {
            adapter = new DonanteAdapter(this, Servicio.getInstance(this).cargarDatos(),
                    new DonanteAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(DonanteAdapter.DonanteViewHolder holder, int position) {
                            confirmacion(position);
                        }
                    });
        }catch (IOException e){
            Toast.makeText(this, "Error al cargar el archivo 1", Toast.LENGTH_SHORT).show();
        }catch (ClassNotFoundException e){
            Toast.makeText(this, "Error al cargar la lista 2", Toast.LENGTH_SHORT).show();
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void confirmacion(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("¿Está seguro de que desea eliminar el elemento?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Servicio.getInstance(MainActivity.this).eliminar(position);
                        } catch (IOException e) {
                            Toast.makeText(MainActivity.this, "Error al actualizar el archivo", Toast.LENGTH_SHORT).show();
                        } catch (ClassNotFoundException e) {
                            Toast.makeText(MainActivity.this, "Error al eliminar el elemento", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
    }

    @Override
    public void agregarDonante(Donante donante) {
        try{
            Servicio.getInstance(this).guardarDonante(donante);
        }catch (IOException e){
            Toast.makeText(this, "Error al actualizar el archivo 3", Toast.LENGTH_SHORT).show();
        }catch (ClassNotFoundException e){
            Toast.makeText(this, "Error al guardar elemento en la lista 4", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();


    }
}
