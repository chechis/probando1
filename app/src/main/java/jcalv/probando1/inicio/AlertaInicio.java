package jcalv.probando1.inicio;

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

import jcalv.probando1.R;
import jcalv.probando1.modelo.Usuario;

public class AlertaInicio extends DialogFragment {

    private UsuariosListener listener;

    public interface UsuariosListener {
        void agregarUsuario (Usuario usuario);
    }


    private Button btnRegistrar, btnCancelar;
    private TextInputLayout editUsuario, editContrasena, editConfirmar;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (UsuariosListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException("El contexto debe implementar la interfaz usuario");
        }
    }

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
                agregarUsuario();
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

    private void agregarUsuario (){

        boolean login = true;

        if (editUsuario !=null && editContrasena != null && editConfirmar != null){

            String nombre = editUsuario.getEditText().getText().toString();
            String contrasena = editContrasena.getEditText().getText().toString();
            String confirmar = editConfirmar.getEditText().getText().toString();

            if (editUsuario.getEditText().getText().toString().equals("")){
                editUsuario.setError("El nombre es requerido");
                login = false;
            }
            if (editContrasena.getEditText().getText().toString().equals("")){
                editContrasena.setError("La contraseña es requerida");
                login = false;
            }
            if (editConfirmar.getEditText().getText().toString().equals("")){
                editConfirmar.setError("Vuelve a escribir tu contraseña");
                login = false;
            }
            if (!editContrasena.getEditText().getText().toString().
                    equals(editConfirmar.getEditText().getText().toString())){
                editConfirmar.setError("Las contraseñas no coinciden");
                login = false;
            }

            if (login){
                Usuario usuario = new Usuario(nombre, contrasena);
                listener.agregarUsuario(usuario);
                dismiss();
            }
        }
    }
}
