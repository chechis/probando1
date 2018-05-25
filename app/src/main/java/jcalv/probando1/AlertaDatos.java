package jcalv.probando1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import jcalv.probando1.R;
import jcalv.probando1.modelo.Donante;

public class AlertaDatos extends DialogFragment{

    private DatosListener listener;

    public interface DatosListener {
        void agregarDonante (Donante donante);
    }

    private Button btnRegistrar, btnCancelar;
    private TextInputLayout editIdentificacion;
    private TextInputLayout editNombre;
    private TextInputLayout editApellido;
    private TextInputLayout editEdad;
    private TextInputLayout editPeso;
    private TextInputLayout editEstatura;
    private Spinner spnTipo, spnRh;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DatosListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException("El contexto debe implementar la interfaz Datos");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogo = inflater.inflate(R.layout.dialogo_alerta, null);

        builder.setView(dialogo);

        btnRegistrar = (Button) dialogo.findViewById(R.id.btn_registrar);
        btnCancelar = (Button) dialogo.findViewById(R.id.btn_cancelar);
        editIdentificacion= (TextInputLayout) dialogo.findViewById(R.id.edit_identi1);
        editNombre= (TextInputLayout) dialogo.findViewById(R.id.edit_nombre2);
        editApellido= (TextInputLayout) dialogo.findViewById(R.id.edit_apellido3);
        editEdad= (TextInputLayout) dialogo.findViewById(R.id.edit_edad4);
        spnTipo = (Spinner) dialogo.findViewById(R.id.spinner_tipo);
        spnRh = (Spinner) dialogo.findViewById(R.id.spinner_rh);
        editPeso= (TextInputLayout) dialogo.findViewById(R.id.edit_peso51);
        editEstatura= (TextInputLayout) dialogo.findViewById(R.id.edit_estatura52);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }

    private void agregarDonante (){
        if (!editIdentificacion.getEditText().getText().toString().equals("")){

        }

    }
}
