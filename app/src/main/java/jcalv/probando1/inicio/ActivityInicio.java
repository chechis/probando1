package jcalv.probando1.inicio;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jcalv.probando1.MainActivity;
import jcalv.probando1.R;
import jcalv.probando1.almacenamiento.BaseDatos;
import jcalv.probando1.modelo.Usuario;

public class ActivityInicio extends AppCompatActivity implements AlertaInicio.UsuariosListener {

    Button btnRegistrar, btnIniciar;
    private TextInputLayout usuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnRegistrar = (Button) findViewById(R.id.btn_nuevo);
        btnIniciar = (Button) findViewById(R.id.btn_login);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertaInicio alertaInicio = new AlertaInicio();
                alertaInicio.show(getSupportFragmentManager(), "alertaNuevo");
            }
        });

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar(view);
            }
        });

    }
    public void iniciar (View view){
        boolean login = true;

        usuario = (TextInputLayout) findViewById(R.id.edit_username);
        contrasena = (TextInputLayout) findViewById(R.id.edit_password);

        if (usuario.getEditText().getText().toString() != null && contrasena.getEditText().getText().toString()!= null){

            String nombreUsuario = usuario.getEditText().getText().toString();
            String nombreContrasena = contrasena.getEditText().getText().toString();

            if (nombreUsuario.equals("")){
                usuario.setError("El usuario es requerido");
                login = false;
            }
            if (nombreContrasena.equals("")){
                contrasena.setError("La contraseña es requerida");
                login = false;
            }
            if (login){
                BaseDatos baseDatos = new BaseDatos(this);

                ServicioInicio servicioInicio = new ServicioInicio(nombreUsuario, nombreContrasena, this);

                if (servicioInicio.confirmarUsuario(nombreUsuario, nombreContrasena, baseDatos) == 2){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }

        }

    }


    @Override
    public void agregarUsuario(Usuario usuario) {
        String nombre = usuario.getNombre();
        String contrasena = usuario.getContrasena();
        BaseDatos baseDatos = new BaseDatos(this);

        ServicioInicio servicioInicio = new ServicioInicio(nombre, contrasena, this);

        servicioInicio.almacenarUsuario(nombre, contrasena, baseDatos);
    }


}
