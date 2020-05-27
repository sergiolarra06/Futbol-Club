package com.controller.bean;

import com.primefaces.dao.PersonaDAO;
import com.primefaces.dto.ContactoEmergencia;
import com.primefaces.dto.Persona;
import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
//import org.primefaces.event.RowEditEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import javax.validation.constraints.Email;
/**
 *
 * @author Jean Cortes
 */
@Named
@RequestScoped
@ManagedBean
@ViewScoped
public class BeanPersona implements Serializable {

    private static final Logger LOG = LogManager.getLogger(BeanJugador.class);

    /**
     * Creates a new instance of BeanVehiculo
     */
    private long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
//    @Email(message = "must be a valid email")
    private String correo;

    public BeanPersona() {

    }

    public void insertar() {
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() || fechaNacimiento == null || correo.isEmpty() || correo == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        Persona persona = new Persona();
        persona.setId(id);
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime()));
        persona.setCorreo(correo);

        PersonaDAO personaDAO = new PersonaDAO();
        int rta = personaDAO.insertar(persona);
        if (rta == 1) {
            LOG.info("SEVERITY_INFO: Se registro la persona" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro la persona"));
        } else {
            LOG.info("SEVERITY_INFO: No Se registro el persona" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Se registro la persona"));
        }
        System.out.println("rta " + rta);
    }

    public void modificar() {
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime()));
        persona.setCorreo(correo);
        persona.setId(id);

        PersonaDAO personaDAO = new PersonaDAO();
        int rta = personaDAO.modificar(persona);

        if (rta > 0) {
            //this.mensaje = "actualizo "+r;
            LOG.info("SEVERITY_INFO: No Se registro el jugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Actualizo " + rta + " persona"));
        } else {
            LOG.error("SEVERITY_INFO: No Se registro el jugador" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, Actualizo la persona"));
        }
        System.out.println("rta " + rta);

    }

    public void eliminar() {
        Persona persona = new Persona();
        persona.setId(id);
        PersonaDAO personaDAO = new PersonaDAO();
        int rta = personaDAO.eliminar(persona);
        if (rta > 0) {
            LOG.info("SEVERITY_INFO: Se eliminado la persona " + rta);
            //this.mensaje = "eliminar "+r;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Eliminado " + rta + " persona"));
        } else {
            LOG.info("SEVERITY_INFO: No se eliminado el persona" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No, Elimino la persona"));

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String regresar() {
        return "index";
    }

}
