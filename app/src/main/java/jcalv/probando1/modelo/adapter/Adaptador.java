package jcalv.probando1.modelo.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.media.MediaDrm;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jcalv.probando1.R;
import jcalv.probando1.almacenamiento.BaseDatos;
import jcalv.probando1.almacenamiento.BaseDatosDonantes;
import jcalv.probando1.almacenamiento.Estructura;
import jcalv.probando1.almacenamiento.ServicioDonante;
import jcalv.probando1.modelo.Donante;

public class Adaptador extends RecyclerView.Adapter<DonanteViewHolder> implements CompoundButton.OnClickListener{

    private BaseDatosDonantes baseDatos;
    private ArrayList<String> identificacion = new ArrayList<String>();
    private ArrayList<String> donantes = new ArrayList<String>();
    private ArrayList<String> apellidos = new ArrayList<String>();
    private ArrayList<String> edad = new ArrayList<String>();
    private ArrayList<String> estaturas = new ArrayList<String>();
    private ArrayList<String> pesos = new ArrayList<String>();
    private ArrayList<String> rh = new ArrayList<String>();
    private ArrayList<String> tipos = new ArrayList<String>();
    private ArrayList<Integer> auxi = new ArrayList<Integer>();
    private LayoutInflater inflater;
    private Context context;

    private List<Donante> donantess;
    private OnEventDonanteListener onEventDonanteListener;
    public interface OnEventDonanteListener {

        void deleteDonante (int position);
        void editarDonante (int position);

    }


    public Adaptador(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);

        baseDatos = new BaseDatosDonantes(context);
        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        Cursor c = sq.rawQuery("SELECT * FROM " + Estructura.EstructuraDonante.TABLE_NAME, null);

        if (c.moveToFirst()){
            do {
                identificacion.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_IDENTI)));
                donantes.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_DONANTE)));
                apellidos.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_APELLIDO)));
                edad.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_EDAD)));
                estaturas.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_ESTATURA)));
                pesos.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_PESO)));
                rh.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_RH)));
                tipos.add(c.getString(c.getColumnIndex(Estructura.EstructuraDonante.COLUMN_NAME_TIPO)));

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
    public void onBindViewHolder(DonanteViewHolder holder, int position) {

        Donante donante = donantess.get(position);

        holder.txtNombre.setText(donantes.get(position));
        holder.txtApellido.setText(apellidos.get(position));
        holder.txtId.setText("Identificacion  " + identificacion.get(position));
        holder.txtEdad.setText("Edad  "+ edad.get(position));
        holder.txtPeso.setText("Peso  " + pesos.get(position));
        holder.txtEstatura.setText("Estatura  " + estaturas.get(position));
        holder.txtRh.setText("Rh " +rh.get(position));
        holder.txtTipo.setText("Tipo " +tipos.get(position));



        ImageButton img = (ImageButton) holder.itemView.findViewById(R.id.btn_delete);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ServicioDonante servicioDonante = new ServicioDonante(position);
                //servicioDonante.eliminarDonante(position, auxi.get(position), baseDatos, context);
                //Toast.makeText(context, "hola"+posicion, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return identificacion.size();
    }

    @Override
    public void onClick(View view) {
        if (onEventDonanteListener != null){

            onEventDonanteListener.deleteDonante(getAdapterPosition());
            onEventDonanteListener.editarDonante(getAdapterPosition());

        }
    }

    public Adaptador(List<Donante> donantess) {
        this.donantess = donantess;
    }

    public OnEventDonanteListener getOnEventDonanteListener() {
        return onEventDonanteListener;
    }

    public void setOnEventDonanteListener (OnEventDonanteListener onEventDonanteListener){
        this.onEventDonanteListener = onEventDonanteListener;
    }
}
