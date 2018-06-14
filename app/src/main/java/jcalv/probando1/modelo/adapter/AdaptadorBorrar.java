package jcalv.probando1.modelo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import jcalv.probando1.R;
import jcalv.probando1.modelo.Donante;

public class AdaptadorBorrar extends RecyclerView.Adapter<AdaptadorBorrar.AdaptadorViewHolder>{


    private List<Donante> listaDonantes;
    private DonanteListener donanteListener;
    public interface DonanteListener{
        void deleteDonante(int position);
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtNombre, txtApellido, txtIdenti, txtEdad, txtTipo, txtRh, txtPeso, txtEstatura;
        ImageButton btnBorrar;

        public AdaptadorViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.txt_nombre11);
            txtApellido = (TextView) itemView.findViewById(R.id.txt_apellido12);
            txtIdenti = (TextView) itemView.findViewById(R.id.txt_id2);
            txtEdad = (TextView) itemView.findViewById(R.id.txt_edad3);
            txtTipo = (TextView) itemView.findViewById(R.id.txt_sangre_tipo41);
            txtRh = (TextView) itemView.findViewById(R.id.txt_sangre_rh42);
            txtPeso = (TextView) itemView.findViewById(R.id.txt_peso51);
            txtEstatura = (TextView) itemView.findViewById(R.id.txt_altura52);

            btnBorrar = (ImageButton) itemView.findViewById(R.id.btn_delete);
            btnBorrar.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (donanteListener != null){
                donanteListener.deleteDonante(getAdapterPosition());
            }
        }
    }

    public AdaptadorBorrar(List<Donante> listaDonantes) {
        this.listaDonantes = listaDonantes;
    }

    @Override
    public AdaptadorBorrar.AdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.modelo_adapter, parent, false);
        return new AdaptadorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdaptadorBorrar.AdaptadorViewHolder holder, int position) {

        Donante donante = listaDonantes.get(position);

        holder.txtNombre.setText(donante.getNombre());
        holder.txtApellido.setText(donante.getApellido());
        holder.txtIdenti.setText(donante.getIdentificacion());
        holder.txtEdad.setText(donante.getEdad());
        holder.txtPeso.setText(donante.getPeso());
        holder.txtEstatura.setText(donante.getEstatura());
        holder.txtRh.setText(donante.getRhTexto());
        holder.txtTipo.setText(donante.getTipoTexto());
    }

    @Override
    public int getItemCount() {
        return listaDonantes.size();
    }


    public DonanteListener getDonanteListener() {
        return donanteListener;
    }
    public void setDonanteListener(DonanteListener donanteListener){
        this.donanteListener = donanteListener;
    }
}
