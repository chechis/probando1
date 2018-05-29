package jcalv.probando1.modelo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import jcalv.probando1.R;

public class DonanteViewHolder extends RecyclerView.ViewHolder {

    TextView txtNombre, txtApellido, txtId, txtEdad, txtTipo, txtRh, txtPeso, txtEstatura;

    public DonanteViewHolder(View itemView) {
        super(itemView);

        txtNombre = (TextView) itemView.findViewById(R.id.txt_nombre11);
        txtApellido = (TextView) itemView.findViewById(R.id.txt_apellido12);
        txtId  = (TextView) itemView.findViewById(R.id.txt_id2);
        txtEdad  = (TextView) itemView.findViewById(R.id.txt_edad3);
        txtTipo = (TextView) itemView.findViewById(R.id.txt_sangre_tipo41);
        txtRh  = (TextView) itemView.findViewById(R.id.txt_sangre_rh42);
        txtPeso = (TextView) itemView.findViewById(R.id.txt_peso51);
        txtEstatura = (TextView) itemView.findViewById(R.id.txt_altura52);



    }
}
