package com.controller.bean;

import com.primefaces.dao.ContactoEmergenciaDAO;
import com.primefaces.dao.JugadorDAO;
import com.primefaces.dao.PersonaDAO;
import com.primefaces.dto.ContactoEmergencia;
import com.primefaces.dto.Jugador;
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

public class BeanJugador implements Serializable {

    private static final Logger LOG = LogManager.getLogger(BeanJugador.class);
    /**
     * Creates a new instance of BeanVehiculo
     */
    private long id;
    private ContactoEmergencia contactosEmergencia;

    private /*Posicion */ String posicion;
    private Map<String, String> posiciones = new HashMap<String, String>();
    private String contrasena;

    public BeanJugador() {
        contactosEmergencia = new ContactoEmergencia();

    }

    @PostConstruct
    public void init() {
        contactosEmergencia = new ContactoEmergencia();
        //Posiciones
        posiciones = new HashMap<String, String>();
        posiciones.put("PORTERO", "PORTERO");
        posiciones.put("DEFENSA", "DEFENSA");
        posiciones.put("CENTROCAMPISTA", "CENTROCAMPISTA");
        posiciones.put("DELANTERO", "DELANTERO");

    }

    public void insertar() {
        if (contactosEmergencia == null || posicion == null) {
            LOG.warn("SEVERITY_WARN: Datos Nulos Revise");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        Jugador jugador = new Jugador();
        jugador.setId(id);
        jugador.setContactoEmergencia(contactosEmergencia);
        jugador.setPosicion(posicion);

        JugadorDAO jugadorDAO = new JugadorDAO();
        int rta = jugadorDAO.insertar(jugador);
        if (rta == 1) {
            LOG.info("SEVERITY_INFO: Se registro el jugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro el jugador"));
        } else {
            LOG.info("SEVERITY_INFO: No Se registro el jugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No Se registro el jugador"));
        }
        System.out.println("rta " + rta);
    }

    public void modificar() {
        Jugador jugador = new Jugador();
        jugador.setPosicion(posicion);
        jugador.setId(id);
        JugadorDAO personaDAO = new JugadorDAO();
        int rta = personaDAO.modificar(jugador);

        if (rta > 0) {
            //this.mensaje = "actualizo "+r;
            LOG.info("SEVERITY_INFO: Se actualizo el jugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Actualizo " + rta + " Persona"));
        } else {
            LOG.error("SEVERITY_ERROR:No se modifico el jugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, Actualizo"));
        }
        System.out.println("rta " + rta);

    }

    public void eliminar() {
        Jugador jugador = new Jugador();
        jugador.setId(id);
//        jugador.setContactoEmergencia(contactosEmergencia);

        JugadorDAO jugadorDAO = new JugadorDAO();
        int rta = jugadorDAO.eliminar(jugador);
        System.out.println("rta " + rta);
        if (rta > 0) {
            //this.mensaje = "eliminar "+r;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Eliminado " + rta + " Jugador"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, elimino el jugador"));

        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String /*Posicion */ getPosicion() {
        return posicion;
    }

    public void setPosicion(String/*Posicion */ posicion) {
        this.posicion = posicion;
    }

    public ContactoEmergencia getContactosEmergencia() {

        return contactosEmergencia;
    }

    public void setContactosEmergencia(ContactoEmergencia contactosEmergencia) {
        this.contactosEmergencia = contactosEmergencia;
    }

    public Map<String, String> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(Map<String, String> posiciones) {
        this.posiciones = posiciones;
    }

    public String regresar() {
        return "index";
    }

}
