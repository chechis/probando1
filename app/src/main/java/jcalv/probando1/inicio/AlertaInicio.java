package jcalv.probando1.inicio;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import jcalv.probando1.R;

public class AlertaInicio extends DialogFragment {

    private Button btnRegistrar, btnCancelar;
    private TextInputLayout editUsuario, editContrasena, editConfirmar;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogo = inflater.inflate(R.layout.alerta_inicio, null);

        builder.setView(dialogo);

        btnRegistrar = (Button) dialogo.findViewById(R.id.btn_inicio_registrar);
        btnCancelar = (Button) dialogo.findViewById(R.id.btn_inicio_cancelar);
        editUsuario= (TextInputLayout) dialogo.findViewById(R.id.edit_inicio_1);
        editContrasena= (TextInputLayout) dialogo.findViewById(R.id.edit_inicio_2);
        editConfirmar= (TextInputLayout) dialogo.findViewById(R.id.edit_inicio_3);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return builder.create();
    }
}
