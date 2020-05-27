/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.primefaces.dao.GolDAO;
import com.primefaces.dto.Gol;
import com.primefaces.dto.Jugador;
import java.io.Serializable;
import java.sql.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sergio
 */
@ManagedBean
@ViewScoped
public class BeanGol implements Serializable{
    
    private Jugador jugador;
    private Date fecha;
    private int gol;

    public BeanGol() {
    }
    
    public void insertar() {
        if (fecha == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos vacios"));
            return;
        }
        Gol goles = new Gol();
        goles.setJugador(jugador);
        goles.setFecha(new java.sql.Date(fecha.getTime()));
        goles.setGol(gol);
   
        GolDAO golDAO = new GolDAO();
        int rta = golDAO.insertar(goles);
        if(rta==1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro el gol"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "No se pudo realizarel registro"));
        }
        

        System.out.println("rta " + rta);
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
    
    public String regresar() {
        return "index";
    }
    
}
