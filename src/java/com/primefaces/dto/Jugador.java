package com.primefaces.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jean.cortes
 */
public class Jugador extends Persona {

    private ContactoEmergencia contactoEmergencia;
    private String posicion;
//   private Posicion posicion;

    public Jugador() {
    }

    public Jugador(long id, ContactoEmergencia contactoEmergencia, /*Posicion */ String posicion) {
        super(id);
        this.contactoEmergencia = contactoEmergencia;
        this.posicion = posicion;

    }

//    public Jugador(long id, String nombre, String apellido, Date fechaNacimiento, String correo, List<ContactoEmergencia> contactoEmergencias, String posicion) {
//        super(id, nombre, apellido, fechaNacimiento, correo);
//        this.contactoEmergencias = contactoEmergencias;
//        this.posicion = posicion;
//    }
    public Jugador(long id, String nombre, String apellido, Date fechaNacimiento, String correo, /*Posicion */ String posicion, ContactoEmergencia contactoEmergencia) {
        super(id, nombre, apellido, fechaNacimiento, correo);
        this.posicion = posicion;
        this.contactoEmergencia = contactoEmergencia;
    }

    public String /*Posicion */ getPosicion() {
        return posicion;
    }

    public void setPosicion(String/*Posicion */ posicion) {
        this.posicion = posicion;
    }

    public ContactoEmergencia getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(ContactoEmergencia contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    @Override
    public String toString() {
        return "Jugador{" + "id=" + super.getId() + ", nombre=" + super.getNombre() + ", apellido=" + super.getApellido() + ", fecha nacimiento=" + super.getFechaNacimiento() + ", correo=" + super.getCorreo() + ", contactoEmergencia=" + contactoEmergencia.getNombre() + ", posicion=" + posicion + '}';
    }
//    @Override
//    public String toString() {
//        return "Jugador{" + "id=" + super.getId() + ", nombre=" + getNombre() + ", apellido=" + getApellido() + ", fecha nacimiento=" + getFechaNacimiento() + ", correo=" + getCorreo() + "contactoEmergencias=" + contactoEmergencias + ", posicion=" + posicion + '}';
//    }
}
