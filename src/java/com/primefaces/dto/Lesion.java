package com.primefaces.dto;

/**
 *
 * @author jean.cortes
 */
public class Lesion {

    private long id;
    private String descripcion;

    public Lesion() {
    }

    public Lesion(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Lesion{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }

}
