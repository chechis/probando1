package jcalv.probando1.modelo;

import java.io.Serializable;

public class Donante implements Serializable {

    public enum Tipo {
        A, B, O, AB
    }
<<<<<<< HEAD
<<<<<<< HEAD

    public enum Rh {
        Positivo, Negativo
=======
    public enum Rh{
        positivo, negativo
>>>>>>> parent of 8f9f66d... adapter para borrar
=======
    public enum Rh{
        positivo, negativo
>>>>>>> parent of 8f9f66d... adapter para borrar
    }

    private int id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String edad;
    private Tipo tipo;
    private Rh rh;
    private String peso;
    private String estatura;

    public Donante(int id, String identificacion, String nombre, String apellido, String edad, Tipo tipo, Rh rh, String peso, String estatura) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.tipo = tipo;
        this.rh = rh;
        this.peso = peso;
        this.estatura = estatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Rh getRh() {
        return rh;
    }

    public void setRh(Rh rh) {
        this.rh = rh;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }
}


