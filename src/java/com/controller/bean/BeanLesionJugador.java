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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOG = LogManager.getLogger(BeanLesion.class);
    /**
     * Creates a new instance of BeanVehiculo
     */
    private Date fecha;
    private Jugador jugador;
    private Lesion lesion;

    public BeanLesionJugador() {
        jugador = new Jugador();
        lesion = new Lesion();

    }

    public void insertar() {
        if (fecha == null || jugador == null || lesion == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        LesionJugador lesionJugador = new LesionJugador();
        lesionJugador.setFecha(new java.sql.Date(fecha.getTime()));
        lesionJugador.setJugador(jugador);
        lesionJugador.setLesion(lesion);

        LesionJugadorDAO lesionJugadorDAO = new LesionJugadorDAO();
        int rta = lesionJugadorDAO.insertar(lesionJugador);
        System.out.println("rta " + rta);
        if (rta > 0) {
            LOG.info("SEVERITY_INFO: Se Inserto la lesionJugador " + rta);
            //this.mensaje = "eliminar "+r;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "inserto " + rta + " lesionJugador"));
        } else {
            LOG.info("SEVERITY_ERROR: No se inserto el lesionJugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No, inserto la lesionJugador"));
        }
    }

    public void modificar() {
        LesionJugador lesionJugador = new LesionJugador();
        lesionJugador.setLesion(lesion);

        lesionJugador.setFecha(new java.sql.Date(fecha.getTime()));
        lesionJugador.setJugador(jugador);
        lesionJugador.setLesion(lesion);
        LesionJugadorDAO lesionDAO = new LesionJugadorDAO();
        int rta = lesionDAO.modificar(lesionJugador);
        System.out.println("rta " + rta);
        if (rta > 0) {
            //this.mensaje = "actualizo "+r;
            LOG.info("SEVERITY_INFO: Se actualizo el lesionJugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Actualizo " + rta + " lesionJugador"));
        } else {
            LOG.error("SEVERITY_ERROR: No se actualizo el lesionJugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, Actualizo la lesionJugador"));
        }
    }

    public void eliminar() {
        LesionJugador lesionJugador = new LesionJugador();
        lesionJugador.setLesion(lesion);
        LesionJugadorDAO lesionDAO = new LesionJugadorDAO();
        int rta = lesionDAO.eliminar(lesionJugador);
        System.out.println("rta " + rta);
        if (rta > 0) {
            //this.mensaje = "eliminar "+r;
            LOG.info("SEVERITY_INFO: Se elimino el lesionJugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "elimino " + rta + " lesionJugador"));
        } else {
            LOG.error("SEVERITY_ERROR:No se elimino el lesionJugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, elimino la lesionJugador"));
        }
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

    public String regresar() {
        return "index";
    }

}
