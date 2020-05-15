package com.controller.bean;

import com.primefaces.dao.JugadorDAO;
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
public class BeanJugador implements Serializable {

    /**
     * Creates a new instance of BeanVehiculo
     */
    private long id;
    private ContactoEmergencia contactosEmergencia;
    private /*Posicion */ String posicion;
    private Map<String, String> posiciones = new HashMap<String, String>();

    public BeanJugador() {
        contactosEmergencia = new ContactoEmergencia();
    }

    @PostConstruct
    public void init() {

        //posiciones
        posiciones = new HashMap<String, String>();
        posiciones.put("PORTERO", "PORTERO");
        posiciones.put("DEFENSA", "DEFENSA");
        posiciones.put("CENTROCAMPISTA", "CENTROCAMPISTA");
        posiciones.put("DELANTERO", "DELANTERO");
    }

    public void insertar() {
        if (contactosEmergencia == null || posicion == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        Jugador jugador = new Jugador();
        jugador.setId(id);
        jugador.setContactoEmergencia(contactosEmergencia);
        jugador.setPosicion(posicion);

        JugadorDAO jugadorDAO = new JugadorDAO();
        int rta = jugadorDAO.insertar(jugador);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro la el jugador"));

        System.out.println("rta " + rta);
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

    public String salir() {
        return "index";
    }

}
