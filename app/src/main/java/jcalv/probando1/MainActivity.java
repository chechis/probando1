package jcalv.probando1;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import jcalv.probando1.modelo.Donante;

public class MainActivity extends AppCompatActivity implements AlertaDatos.DatosListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton btnflotatante= (FloatingActionButton) findViewById(R.id.btn_flotatne);
        btnflotatante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertaDatos alertaDatos= new AlertaDatos();
                alertaDatos.show(getSupportFragmentManager(), "alertaDatos");
            }
        });

    }

    @Override
    public void agregarDonante(Donante donante) {
        TextView textView= (TextView) findViewById(R.id.txt_probando);
        textView.setText((CharSequence) donante);
    }
}
