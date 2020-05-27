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
public class Gol {
    
private Jugador jugador;
    private Date fecha;
    private int gol;

    public Gol() {
    }
    
    //Para goles por jugador

    public Gol(Jugador jugador, Date fecha, int gol) {
        this.jugador = jugador;
        this.fecha = fecha;
        this.gol = gol;
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

    public int getGol() {
        return gol;
    }

    public void setGol(int gol) {
        this.gol = gol;
    }

    @Override
    public String toString() {
        return "Gol{" + "jugador=" + jugador + ", fecha=" + fecha + ", gol=" + gol + '}';
    }
    
    
}
