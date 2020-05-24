package com.primefaces.dto;

import java.sql.*;

/**
 *
 * @author jean.cortes
 */
public class LesionJugador {

    private Date fecha;
    private Jugador jugador;
    private Lesion lesion;

    public LesionJugador() {
    }

    public LesionJugador(Date fecha, Jugador jugador, Lesion lesion) {
        this.fecha = fecha;
        this.jugador = jugador;
        this.lesion = lesion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Jugador getJugador() {

        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Lesion getLesion() {
        return lesion;
    }

    public void setLesion(Lesion lesion) {
        this.lesion = lesion;
    }

    @Override
    public String toString() {
        return "LesionJugador{" + "fecha=" + fecha + ", jugador=" + jugador.getNombre() + ", lesion=" + lesion.getId() + '}';
    }

}
