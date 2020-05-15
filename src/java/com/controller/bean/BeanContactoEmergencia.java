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
        ContactoEmergencia persona = new ContactoEmergencia();
        persona.setId(id);
        persona.setTelefono(telefono);

        ContactoEmergenciaDAO personaDAO = new ContactoEmergenciaDAO();
        int rta = personaDAO.insertar(persona);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro la el contacto de emergencia"));

        System.out.println("rta " + rta);
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

    public String salir() {
        return "index";
    }

}
