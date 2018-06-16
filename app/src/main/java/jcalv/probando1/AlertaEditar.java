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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jcalv.probando1.modelo.Donante;

public class AlertaEditar extends DialogFragment {

    private EditarListener listener;

    public interface EditarListener {
        void editarDonante (Donante donante);
    }

    private Button btnRegistrar, btnCancelar;
    private TextView editIdentificacion;
    private TextInputLayout editNombre ,editApellido, editEdad, editPeso, editEstatura;
    private Spinner spnTipo, spnRh;
    EditText nombreEdit, apellidoEdit, edadEdit, pesoEdit, estaturaEdit;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (EditarListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException("El contexto debe implementar la interfaz Datos");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogo = inflater.inflate(R.layout.alerta_editar, null);

        builder.setView(dialogo);

        nombreEdit= (EditText) dialogo.findViewById(R.id.txt_editar_nombre);
        apellidoEdit = (EditText) dialogo.findViewById(R.id.txt_editar_apellido);
        edadEdit = (EditText) dialogo.findViewById(R.id.txt_editar_edad);
        pesoEdit = (EditText) dialogo.findViewById(R.id.txt_editar_peso);
        estaturaEdit = (EditText) dialogo.findViewById(R.id.txt_editar_estatura);

        btnRegistrar = (Button) dialogo.findViewById(R.id.btn_registrar_editar);
        btnCancelar = (Button) dialogo.findViewById(R.id.btn_cancelar_editar);
        editIdentificacion= (TextView) dialogo.findViewById(R.id.text_editar_identificacion);
        editNombre= (TextInputLayout) dialogo.findViewById(R.id.edit_nombre2_editar);
        editApellido= (TextInputLayout) dialogo.findViewById(R.id.edit_apellido3_editar);
        editEdad= (TextInputLayout) dialogo.findViewById(R.id.edit_edad4_editar);
        spnTipo = (Spinner) dialogo.findViewById(R.id.spinner_tipo_editar);
        spnRh = (Spinner) dialogo.findViewById(R.id.spinner_rh_editar);
        editPeso= (TextInputLayout) dialogo.findViewById(R.id.edit_peso51_editar);
        editEstatura= (TextInputLayout) dialogo.findViewById(R.id.edit_estatura52_editar);

        llenando();


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarDonante();
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

    private void editarDonante (){

        boolean login = true;

        if (editNombre != null && editApellido != null && editEdad != null
                && editPeso != null && editEstatura != null){

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


            Donante.Rh rh = Donante.Rh.Positivo;
            switch (spnRh.getSelectedItemPosition()){
                case 0:
                    rh = Donante.Rh.Positivo;
                    break;
                case 1:
                    rh = Donante.Rh.Negativo;
                    break;
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


                Donante donante = new Donante(this.txtID, this.txtIdentificacion, nombre, apellido, edad, tipo, rh, peso, estatura);
                listener.editarDonante(donante);
                dismiss();
            }

        }
    }

    private void llenando (){

        editIdentificacion.setText("Identificacion  "+this.txtIdentificacion);
        nombreEdit.setText(this.txtNombre);
        apellidoEdit.setText(this.txtApellido);
        edadEdit.setText(this.txtEdad);
        pesoEdit.setText(this.txtPeso);
        estaturaEdit.setText(this.txtEstatura);
    }

    private int txtID;
    private String txtIdentificacion;
    private String txtNombre;
    private String txtApellido;
    private String txtEdad;
    private String txtPeso;
    private String txtEstatura;
    private String txtRh;
    private String txtTipo;

    public int getTxtID (int id){
        return  txtID = id;
    }

    public String getTxtIdentificacion(String identificacion) {
        return txtIdentificacion = identificacion;
    }

    public String getTxtNombre (String nombre) {
        return txtNombre = nombre ;
    }
    public String getTxtApellido (String apellido) {
        return txtApellido = apellido ;
    }
    public String getTxtEdad (String edad) {
        return txtEdad = edad ;
    }
    public String getTxtPeso (String peso) {
        return txtPeso = peso ;
    }
    public String getTxtEstatura (String estatura) {
        return txtEstatura = estatura ;
    }
    public String getTxtRh (String rh) {
        return txtRh = rh ;
    }
    public String getTxtTipo(String tipo) {
        return txtTipo = tipo;
    }
}
