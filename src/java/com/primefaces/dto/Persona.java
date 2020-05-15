package com.primefaces.dto;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author jean.cortes
 */
public class Persona {

    private long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String correo;

    public Persona() {
    }

//Para eliminar por id nada mas
    public Persona(long id) {
        this.id = id;
    }

//Para Jugador
    public Persona(long id, String nombre, String apellido, Date fechaNacimiento, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
    }

//Para Contacto de emergencia
    public Persona(long id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", correo=" + correo + '}';
    }

}
