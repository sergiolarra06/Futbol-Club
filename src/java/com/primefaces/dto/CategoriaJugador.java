package com.primefaces.dto;

import java.sql.Date;

/**
 *
 * @author jean.cortes
 */
public class CategoriaJugador {

    private Jugador jugador;
    private Categoria categoria;
    private Date fechaInicio;
    private Date fechaFin;

    public CategoriaJugador() {
    }

    public CategoriaJugador(Jugador jugador, Categoria categoria, Date fechaInicio, Date fechaFin) {
        this.jugador = jugador;
        this.categoria = categoria;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "CategoriaJugador{" + "categoria=" + categoria + ", jugador=" + jugador + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }

}
