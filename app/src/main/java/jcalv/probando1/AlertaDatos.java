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
                agregarDonante();
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

        boolean login = true;

        if (editIdentificacion != null && editNombre != null && editApellido != null && editEdad != null
                && editPeso != null && editEstatura != null){

            String identificacion= editIdentificacion.getEditText().getText().toString();
            String nombre= editNombre.getEditText().getText().toString();
            String apellido= editApellido.getEditText().getText().toString();
            String edad= editEdad.getEditText().getText().toString();
            String peso= editPeso.getEditText().getText().toString();
            String estatura= editEstatura.getEditText().getText().toString();

            Donante.Tipo tipo = Donante.Tipo.A;
            switch (spnTipo.getSelectedItemPosition()){
                case 0:
                    tipo = Donante.Tipo.A;
                    break;
                case 1:
                    tipo = Donante.Tipo.B;
                    break;
                case 2:
                    tipo = Donante.Tipo.O;
                    break;
                case 3:
                    tipo = Donante.Tipo.AB;
                    break;
            }

            Donante.Rh rh = Donante.Rh.positivo;
            switch (spnTipo.getSelectedItemPosition()){
                case 0:
                    rh = Donante.Rh.positivo;
                    break;
                case 1:
                    rh = Donante.Rh.negativo;
                    break;
            }

            if (editIdentificacion.getEditText().getText().toString().equals("")){
                editIdentificacion.setError("La identificación es requerida");
                login = false;
            }
            if (editNombre.getEditText().getText().toString().equals("")){
                editNombre.setError("El nombre es requerida");
                login = false;
            }
            if (editApellido.getEditText().getText().toString().equals("")){
                editApellido.setError("El apellido es requerido");
                login = false;
            }
            if (editEdad.getEditText().getText().toString().equals("")){
                editEdad.setError("La edad es requerida");
                login = false;
            }
            if (editPeso.getEditText().getText().toString().equals("")){
                editPeso.setError("El peso es requerido");
                login = false;
            }
            if (editEstatura.getEditText().getText().toString().equals("")){
                editEstatura.setError("La estatura es requerida");
                login = false;
            }

            if (login){
                Donante donante = new Donante(identificacion, nombre, apellido, edad, tipo, rh, peso, estatura);
                listener.agregarDonante(donante);
                dismiss();
            }

        }


    }
}
