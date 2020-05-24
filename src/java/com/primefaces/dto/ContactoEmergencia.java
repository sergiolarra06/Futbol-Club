package com.primefaces.dto;

import java.sql.Date;

/**
 *
 * @author jean.cortes
 */
public class ContactoEmergencia extends Persona {

    private String telefono;

    public ContactoEmergencia() {
    }

    public ContactoEmergencia(long id, String celular) {
        super(id);
        this.telefono = celular;
    }

    public ContactoEmergencia(long id, String nombre, String apellido, String celular) {
        super(id, nombre, apellido);
        this.telefono = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return " => contactoEmergencia{" + "id=" + super.getId() + ", nombre=" + super.getNombre() + ", apellido=" + super.getApellido() + "telefono=" + telefono + '}';
    }

}
