package com.primefaces.dto;

import java.sql.Date;

/**
 *
 * @author jean.cortes
 */
public class Estatura {

    private Jugador jugador;
    private Date fecha;
    private double estatura;

    public Estatura() {
    }

    public Estatura(Jugador jugador, Date fecha, double estatura) {
        this.jugador = jugador;
        this.fecha = fecha;
        this.estatura = estatura;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    @Override
    public String toString() {
        return "Estatura{" + "jugador=" + jugador + ", fecha=" + fecha + ", estatura=" + estatura + '}';
    }

}
