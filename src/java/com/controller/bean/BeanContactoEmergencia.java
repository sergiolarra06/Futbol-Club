package com.controller.bean;

import com.primefaces.dao.ContactoEmergenciaDAO;
import com.primefaces.dto.ContactoEmergencia;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import javax.validation.constraints.Email;

/**
 *
 * @author Jean Cortes
 */
//@Named(value = "beanv")
//@Dependent
@ManagedBean
@ViewScoped
public class BeanContactoEmergencia implements Serializable {

    private static final Logger LOG = LogManager.getLogger(BeanJugador.class);

    /**
     * Creates a new instance of BeanVehiculo
     */
    private long id;
    private String telefono;

    public BeanContactoEmergencia() {

    }

    public void insertar() {
        if (telefono == null || telefono.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        ContactoEmergencia contactoEmergencia = new ContactoEmergencia();
        contactoEmergencia.setId(id);
        contactoEmergencia.setTelefono(telefono);

        ContactoEmergenciaDAO contactoEmergenciaDAO = new ContactoEmergenciaDAO();
        int rta = contactoEmergenciaDAO.insertar(contactoEmergencia);
        if (rta == 1) {
            LOG.info("SEVERITY_INFO: Se registro el contactoEmergencia" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro el contactoEmergencia"));
        } else {
            LOG.info("SEVERITY_INFO: No Se registro el contactoEmergencia" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No Se registro el contactoEmergencia"));
        }
        System.out.println("rta " + rta);
    }

    public void modificar() {
        ContactoEmergencia contactoEmergencia = new ContactoEmergencia();
        contactoEmergencia.setTelefono(telefono);
        contactoEmergencia.setId(id);
        ContactoEmergenciaDAO contactoEmergenciaDAO = new ContactoEmergenciaDAO();
        int rta = contactoEmergenciaDAO.modificar(contactoEmergencia);

        if (rta > 0) {
            //this.mensaje = "actualizo "+r;
            LOG.info("SEVERITY_INFO: Se actualizo el contactoEmergencia" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Actualizo " + rta + " Persona"));
        } else {
            LOG.error("SEVERITY_ERROR:No se modifico el contactoEmergencia" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, Actualizo"));
        }
        System.out.println("rta " + rta);

    }

    public void eliminar() {
        ContactoEmergencia contactoEmergencia = new ContactoEmergencia();
        contactoEmergencia.setTelefono(telefono);
        contactoEmergencia.setId(id);

        ContactoEmergenciaDAO contactoEmergenciaDAO = new ContactoEmergenciaDAO();
        int rta = contactoEmergenciaDAO.eliminar(contactoEmergencia);
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String regresar() {
        return "index";
    }

}
