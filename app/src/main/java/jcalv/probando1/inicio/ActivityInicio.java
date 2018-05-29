package jcalv.probando1.inicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jcalv.probando1.MainActivity;
import jcalv.probando1.R;

public class ActivityInicio extends AppCompatActivity {

    Button btnRegistrar, btnIniciar;

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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
