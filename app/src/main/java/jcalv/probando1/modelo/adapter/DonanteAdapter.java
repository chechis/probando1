package jcalv.probando1.modelo.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import jcalv.probando1.MainActivity;
import jcalv.probando1.R;
import jcalv.probando1.modelo.Donante;

public class DonanteAdapter extends RecyclerView.Adapter<DonanteAdapter.DonanteViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(DonanteViewHolder holder, int position);
    }

    private OnItemClickListener listener;


    public class DonanteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtNombre, txtApellido, txtId, txtEdad, txtTipo, txtRh, txtPeso, txtEstatura;

        public DonanteViewHolder (View itemView){
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.txt_nombre11);
            txtApellido = (TextView) itemView.findViewById(R.id.txt_apellido12);
            txtId  = (TextView) itemView.findViewById(R.id.txt_id2);
            txtEdad  = (TextView) itemView.findViewById(R.id.txt_edad3);
            txtTipo = (TextView) itemView.findViewById(R.id.txt_sangre_tipo41);
            txtRh  = (TextView) itemView.findViewById(R.id.txt_sangre_rh42);
            txtPeso = (TextView) itemView.findViewById(R.id.txt_peso51);
            txtEstatura = (TextView) itemView.findViewById(R.id.txt_altura52);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(this, getAdapterPosition());
        }
    }
    private List<Donante> donantes;
    private Activity activity;


    public DonanteAdapter(Activity activity, List<Donante> donantes, OnItemClickListener listener) {
        this.listener = listener;
        this.donantes = donantes;
        this.activity = activity;
    }

    public List <Donante> getDonantes() { return donantes;}

    @Override
    public DonanteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_adapter, parent, false);

        ImageButton imageButton= (ImageButton) view.findViewById(R.id.btn_edit);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "hola", Toast.LENGTH_SHORT).show();
            }
        });

        return new DonanteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DonanteViewHolder holder, final int position) {
        holder.txtNombre.setText(getDonantes().get(position).getNombre());
        holder.txtApellido.setText(getDonantes().get(position).getApellido());
        holder.txtId.setText("Identificacion  " + getDonantes().get(position).getIdentificacion());
        holder.txtEdad.setText("Edad  "+ getDonantes().get(position).getEdad());
        holder.txtPeso.setText("Peso  " + getDonantes().get(position).getPeso());
        holder.txtEstatura.setText("Estatura  " + getDonantes().get(position).getEstatura());
        CardView cardView= (CardView) holder.itemView.findViewById(R.id.cartd_view);

        switch (getDonantes().get(position).getTipo()){
            case A:
                cardView.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorTipoA));
                holder.txtTipo.setText("Tipo  A");
                break;
            case B:
                cardView.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorTipoB));
                holder.txtTipo.setText("Tipo  B");
                break;
            case AB:
                cardView.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorTipoAB));
                holder.txtTipo.setText("Tipo  AB");
                break;
            case O:
                cardView.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorTipoO));
                holder.txtTipo.setText("Tipo  O");
                break;
        }

        switch (getDonantes().get(position).getRh()){
            case positivo:
                holder.txtRh.setText("Rh +");
                break;
            case negativo:
                holder.txtRh.setText("Rh -");
                break;
        }


        ImageButton img = (ImageButton) holder.itemView.findViewById(R.id.btn_delete);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "hola"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return donantes.size();
    }
}
