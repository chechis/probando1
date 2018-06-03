package jcalv.probando1.almacenamiento;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import jcalv.probando1.modelo.Donante;

public class ServicioMain {

    private ArrayList<Donante> donantes;
    private final String nombreArchivo = "donante.txt";
    private static ServicioMain instance;
    private Context context;

    private ServicioMain (Context context) throws ClassNotFoundException, IOException {
        try {
            this.context= context;
            this.donantes = new ArrayList<>();
            cargarDatos();
        }catch (IOException e){
            guardarDonante(new Donante("201114001","Julio","Alvarez",
                    "25", Donante.Tipo.O, Donante.Rh.negativo, "67", "170"));
            this.context = context;
        }
    }

    public static ServicioMain getInstance(Context context) throws IOException, ClassNotFoundException {
        if (instance == null)
            instance = new ServicioMain(context);
        return instance;
    }

    public void guardarDonante(Donante donante) throws IOException {
        donantes.add(donante);
        ObjectOutputStream output =
                new ObjectOutputStream(context.openFileOutput(nombreArchivo,
                        Context.MODE_PRIVATE));
        output.writeObject(donantes);
        output.close();
    }

    public ArrayList<Donante> cargarDatos() throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(context.openFileInput(nombreArchivo));
        donantes = (ArrayList<Donante>) input.readObject();
        input.close();
        return donantes;
    }

    public void eliminar(int position) throws IOException {
        donantes.remove(position);
        ObjectOutputStream output = new ObjectOutputStream(context.openFileOutput(nombreArchivo
                , Context.MODE_PRIVATE));
        output.writeObject(donantes);
        output.close();
    }
}
