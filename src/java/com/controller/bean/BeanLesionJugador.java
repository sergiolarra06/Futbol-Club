package com.controller.bean;

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
    private long id;
    private String nombre;
    private String apellido;
    private java.sql.Date fechaNacimiento;
    private String correo;
    private ContactoEmergencia contactosEmergencia;
    private List<ContactoEmergencia> contactoEmergencias;

//    @Inject
//    private ListaBeanContactoEmergencia beanContactoEmergencia;
    private /*Posicion */ String posicion;
    private Map<String, String> posiciones = new HashMap<String, String>();

    public BeanLesionJugador() {
        contactosEmergencia = new ContactoEmergencia();
//        beanContactoEmergencia = new ListaBeanContactoEmergencia();

    }

    @PostConstruct
    public void init() {

        //Posiciones
        posiciones = new HashMap<String, String>();
        posiciones.put("PORTERO", "PORTERO");
        posiciones.put("DEFENSA", "DEFENSA");
        posiciones.put("CENTROCAMPISTA", "CENTROCAMPISTA");
        posiciones.put("DELANTERO", "DELANTERO");
        contactoEmergencias = new ArrayList<>();
        for (ContactoEmergencia contactoEmergencia : contactoEmergencias) {
            List<ContactoEmergencia> opciones = contactoEmergencias;
        }
        //Contactos de emergencia
//        contactoEmergencias = beanContactoEmergencia.getContactosEmergencia();
    }

    public void insertar() {
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() || fechaNacimiento == null || correo.isEmpty() || correo == null || contactosEmergencia == null || posicion == null || posicion.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        Jugador jugador = new Jugador();

        jugador.setId(id);
        jugador.setNombre(nombre);
        jugador.setApellido(apellido);
        jugador.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime()));
        jugador.setCorreo(correo);
        jugador.setNombre(posicion);
        jugador.setContactoEmergencia(contactosEmergencia);

        PersonaDAO personaDAO = new PersonaDAO();

        int rta = personaDAO.insertar(jugador);
        if (rta == 1) {
            JugadorDAO jugadorDAO = new JugadorDAO();
            rta = jugadorDAO.insertar(jugador);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro la el jugador"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "No se registro la el jugador"));
        }

        System.out.println("rta " + rta);
    }

    public void actualizar() {
        Jugador jugador = new Jugador();
        jugador.setPosicion(posicion);
        jugador.setId(id);
        JugadorDAO personaDAO = new JugadorDAO();
        int rta = personaDAO.modificar(jugador);

        if (rta > 0) {
            //this.mensaje = "actualizo "+r;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Actualizo " + rta + " Persona"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, Actualizo"));

        }
        System.out.println("rta " + rta);

    }

    public void borrar() {
        System.out.println(" Placa " + this.id);
        System.out.println(" Marca " + this.contactosEmergencia);
        System.out.println("Precio " + this.posicion);

        Jugador jugador = new Jugador();
        jugador.setId(id);
        jugador.setContactoEmergencia(contactosEmergencia);
        jugador.setPosicion(posicion);

        JugadorDAO jugadorDAO = new JugadorDAO();
        int rta = jugadorDAO.eliminar(jugador);
        System.out.println("rta " + rta);
        if (rta > 0) {
            //this.mensaje = "eliminar "+r;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Eliminado " + rta + " Vehiculo"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, Elimino"));

        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public java.sql.Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public List<ContactoEmergencia> getContactoEmergencias() {
        return contactoEmergencias;
    }

    public void setContactoEmergencias(List<ContactoEmergencia> contactoEmergencias) {
        this.contactoEmergencias = contactoEmergencias;
    }
//
//    public ListaBeanContactoEmergencia getBeanContactoEmergencia() {
//        return beanContactoEmergencia;
//    }

//    public void setBeanContactoEmergencia(ListaBeanContactoEmergencia beanContactoEmergencia) {
//        this.beanContactoEmergencia = beanContactoEmergencia;
//    }
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
