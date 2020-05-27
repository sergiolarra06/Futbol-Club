/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.dto;

import java.sql.Date;

/**
 *
 * @author Sergio
 */
public class PaseGol {
  private Jugador jugador;
    private Date fecha;
    private int pase_gol;

    public PaseGol() {
    }

    public PaseGol(Jugador jugador, Date fecha, int pase_gol) {
        this.jugador = jugador;
        this.fecha = fecha;
        this.pase_gol = pase_gol;
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

    public int getPase_gol() {
        return pase_gol;
    }

    public void setPase_gol(int pase_gol) {
        this.pase_gol = pase_gol;
    }

    @Override
    public String toString() {
        return "PaseGol{" + "jugador_id=" + jugador + ", fecha=" + fecha + ", pase_gol=" + pase_gol + '}';
    }
    
    
    
    
}
