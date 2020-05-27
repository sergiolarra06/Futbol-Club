/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.primefaces.dao.PaseGolDAO;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.PaseGol;
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
public class BeanPaseGol implements Serializable{
    private Jugador jugador;
    private Date fecha;
    private int pase_gol;

    public BeanPaseGol() {
    }

    public void insertar() {
        if (fecha == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos vacios"));
            return;
        }
        PaseGol paseGol = new PaseGol();
        paseGol.setJugador(jugador);
        paseGol.setFecha(new java.sql.Date(fecha.getTime()));
        paseGol.setPase_gol(pase_gol);
   
        PaseGolDAO pasegolDAO = new PaseGolDAO();
        int rta = pasegolDAO.insertar(paseGol);
        if(rta==1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro con exito"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "No se pudo realizar el registro"));
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

    public int getPase_gol() {
        return pase_gol;
    }

    public void setPase_gol(int pase_gol) {
        this.pase_gol = pase_gol;
    }
    public String regresar() {
        return "index";
    }
}
