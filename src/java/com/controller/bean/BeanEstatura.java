package com.controller.bean;

import com.primefaces.dao.EstaturaDAO;
import com.primefaces.dto.Estatura;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.Persona;
import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
//import javax.validation.constraints.Email;

/**
 *
 * @author Jean Cortes
 */
//@Named(value = "beanv")
//@Dependent
@ManagedBean
@ViewScoped
public class BeanEstatura implements Serializable {

    private Jugador jugador;
    private Date fecha;
    private double estatura;

    public BeanEstatura() {
        jugador = new Jugador();

    }

    public void insertar() {
        if (jugador == null || fecha == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        Estatura estatura = new Estatura();
        estatura.setJugador(jugador);
        estatura.setFecha(new java.sql.Date(fecha.getTime()));
        estatura.setEstatura(this.estatura);

        EstaturaDAO estaturaDAO = new EstaturaDAO();
        int rta = estaturaDAO.insertar(estatura);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro la estatura"));

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

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public String salir() {
        return "index";
    }

}
