package jcalv.probando1.modelo.adapter;

import android.app.Activity;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import jcalv.probando1.R;
import jcalv.probando1.modelo.Donante;

public class DonanteAdapter extends RecyclerView.Adapter<DonanteAdapter.DonanteViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(DonanteViewHolder holder, int position);
    }

    private OnItemClickListener listener;


    public class DonanteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtNombre, txtId, txtEdad, txtTipo, txtRh, txtPeso, txtEstatura;

        public DonanteViewHolder (View itemView){
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.txt_nombre1);
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
        return new DonanteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DonanteViewHolder holder, int position) {
        holder.txtNombre.setText(getDonantes().get(position).getNombre());
        holder.txtId.setText(getDonantes().get(position).getIdentificacion());
        holder.txtEdad.setText(getDonantes().get(position).getEdad());
        holder.txtPeso.setText(getDonantes().get(position).getPeso());
        holder.txtEstatura.setText(getDonantes().get(position).getEstatura());
        CardView cardView= (CardView) holder.itemView.findViewById(R.id.cartd_view);

        switch ()
    }

    @Override
    public int getItemCount() {
        return donantes.size();
    }
}
