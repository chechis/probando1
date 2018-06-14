package jcalv.probando1.modelo;

import java.io.Serializable;

public class Donante {

    public enum Tipo {
        A, B, O, AB
    }


    public enum Rh {
        Positivo, Negativo

    }

    private String identificacion;
    private String nombre;
    private String apellido;
    private String edad;
    private Tipo tipo;
    private Rh rh;
    private String peso;
    private String estatura;


    public Donante(int id, String identificacion, String nombre, String apellido, String edad, String tipoTexto, String rhTexto, String peso, String estatura) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
        this.id = id;
        this.rhTexto = rhTexto;
        this.tipoTexto = tipoTexto;
    }

    private int id;
    private String rhTexto;
    private String tipoTexto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRhTexto() {
        return rhTexto;
    }

    public void setRhTexto(String rhTexto) {
        this.rhTexto = rhTexto;
    }

    public String getTipoTexto() {
        return tipoTexto;
    }

    public void setTipoTexto(String tipoTexto) {
        this.tipoTexto = tipoTexto;
    }

    public Donante(String identificacion, String nombre, String apellido, String edad, Tipo tipo, Rh rh, String peso, String estatura) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.tipo = tipo;
        this.rh = rh;
        this.peso = peso;
        this.estatura = estatura;
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


