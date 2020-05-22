package com.controller.bean;

import com.primefaces.dao.JugadorDAO;
import com.primefaces.dao.LesionJugadorDAO;
import com.primefaces.dao.PersonaDAO;
import com.primefaces.dto.ContactoEmergencia;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.Lesion;
import com.primefaces.dto.LesionJugador;
import com.primefaces.dto.Persona;
import com.primefaces.dto.Posicion;
import java.io.Serializable;
import java.util.ArrayList;
//import java.sql.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Persistence;
//import javax.validation.constraints.Email;

/**
 *
 * @author Jean Cortes
 */
@Named(value = "beanJugador")
//@Dependent
@ManagedBean
@ViewScoped
@SessionScoped

public class BeanLesionJugador implements Serializable {

    /**
     * Creates a new instance of BeanVehiculo
     */
    private java.sql.Date fecha;
    private Jugador jugador;
    private Lesion lesion;

    public BeanLesionJugador() {
        jugador = new Jugador();
        lesion = new Lesion();

    }

    public void insertar() {
        if (fecha == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        LesionJugador lesionJugador = new LesionJugador();
        lesionJugador.setFecha(fecha);
        lesionJugador.setJugador(jugador);
        lesionJugador.setLesion(lesion);

        LesionJugadorDAO lesionJugadorDAO = new LesionJugadorDAO();
        int rta = lesionJugadorDAO.insertar(lesionJugador);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro el asocio entre lesion y jugador"));

        System.out.println("rta " + rta);
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
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

    public String salir() {
        return "index";
    }

}
