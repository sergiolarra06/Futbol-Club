/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.dto;

import java.sql.Date;

/**
 *
 * @author jean.cortes
 */
public class Pago {

    private long id;
    private Jugador jugador;
    private Date fecha;
    private String periodoPago;
    private float valor; 

    public Pago() {
    }

    public Pago(long id, Jugador jugador, Date fecha, String periodoPago, float valor) {
        this.id = id;
        this.jugador = jugador;
        this.fecha = fecha;
        this.periodoPago = periodoPago;
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPeriodoPago() {
        return periodoPago;
    }

    public void setPeriodoPago(String periodoPago) {
        this.periodoPago = periodoPago;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Pago{" + "id=" + id + ", jugador=" + jugador.getId() + ", fecha=" + fecha + ", periodoPago=" + periodoPago + ", valor=" + valor + '}';
    }
     

}
