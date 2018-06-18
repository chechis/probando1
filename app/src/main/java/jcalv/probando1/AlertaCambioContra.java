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
import android.widget.TextView;

import org.w3c.dom.Text;

public class AlertaCambioContra extends DialogFragment{

    private CambiarContrasena listener;

    public interface CambiarContrasena{
        void cambiarContrasena(String contrasena, String nuevaContrasena);
    }

    private Button btnCancelar, btnActualizar;
    private TextInputLayout editContraNueva, editContraVerificar;
    private TextView textView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (CambiarContrasena) context;
        }catch (ClassCastException ex){
            throw new ClassCastException("El contexto debe implementar la interfaz cambiarContrasena");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogo = inflater.inflate(R.layout.alerta_cambio_contrasena, null);

        builder.setView(dialogo);

        textView = (TextView) dialogo.findViewById(R.id.txt_cambiar_contra1);
        editContraNueva = (TextInputLayout) dialogo.findViewById(R.id.edit_cambio_contra2);
        editContraVerificar = (TextInputLayout) dialogo.findViewById(R.id.edit_cambio_contra3);
        textView.setText("La contraseña actual es "+this.txtContrasena);

        btnCancelar = (Button) dialogo.findViewById(R.id.btn_cambiar_cancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnActualizar = (Button) dialogo.findViewById(R.id.btn_cambiar_actualizar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            cambiarContra();

            }
        });

        return builder.create();
    }

    private void cambiarContra(){
        boolean login = true;

        if (editContraVerificar != null && editContraNueva != null) {
            String contrasenaNueva = editContraNueva.getEditText().getText().toString();
            String contrasenaVerificar = editContraVerificar.getEditText().getText().toString();

            if (editContraNueva.getEditText().getText().toString().equals("")) {
                editContraNueva.setError("La contraseña nueva es requerida");
                login = false;
            }
            if (editContraVerificar.getEditText().getText().toString().equals("")) {
                editContraVerificar.setError("La contraseña ha verificar es requerida");
                login = false;
            }
            if (!editContraNueva.getEditText().getText().toString().equals(editContraVerificar.getEditText().getText().toString())) {
                editContraVerificar.setError("Las contraseñas deben ser iguales");
                login = false;
            }
            if (login) {
                listener.cambiarContrasena(this.txtContrasena, contrasenaNueva);
                dismiss();
            }
        }

    }

    private String txtContrasena;
    public String getContrasena (String contrasena){ return txtContrasena = contrasena;}
}
