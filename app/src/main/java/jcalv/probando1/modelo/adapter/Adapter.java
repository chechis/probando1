package jcalv.probando1.modelo.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import jcalv.probando1.R;
import jcalv.probando1.almacenamiento.BaseDatos;
import jcalv.probando1.almacenamiento.BaseDatosDonantes;
import jcalv.probando1.almacenamiento.Estructura;
import jcalv.probando1.modelo.Donante;

public class Adapter extends RecyclerView.Adapter<DonanteViewHolder>{

    BaseDatosDonantes baseDatos;
    ArrayList<String> id, donantes, apellidos, edad, estaturas, pesos, rh, tipos;
    LayoutInflater inflater;
    Context context;

    public Adapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);

        baseDatos = new BaseDatosDonantes(context);
        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        Cursor c = sq.rawQuery("SELECT * FROM " + Estructura.EstructuraDonante.TABLE_NAME, null);
        int a = 0;

        if (c.moveToFirst()){
            do {
                id.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_ID)));
                donantes.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_DONANTE)));
                apellidos.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_APELLIDO)));
                edad.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_EDAD)));
                estaturas.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_ESTATURA)));
                pesos.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_PESO)));
                rh.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_RH)));
                tipos.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_TIPO)));

                //a++;
            }while (c.moveToNext());
        }
    }

    @Override
    public DonanteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.modelo_adapter, parent, false);
        DonanteViewHolder viewHolder = new DonanteViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DonanteViewHolder holder, final int position) {

        holder.txtNombre.setText(donantes.get(position));
        holder.txtApellido.setText(apellidos.get(position));
        holder.txtId.setText("Identificacion  " + id.get(position));
        holder.txtEdad.setText("Edad  "+ edad.get(position));
        holder.txtPeso.setText("Peso  " + pesos.get(position));
        holder.txtEstatura.setText("Estatura  " + estaturas.get(position));
        holder.txtRh.setText("Rh " +rh.get(position));
        holder.txtTipo.setText("Tipo " +tipos.get(position));



        ImageButton img = (ImageButton) holder.itemView.findViewById(R.id.btn_delete);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "hola"+position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }
}
