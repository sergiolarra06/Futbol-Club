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
import org.primefaces.event.RowEditEvent;
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

    /**
     * Creates a new instance of BeanVehiculo
     */
    private long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
//    @Email(message = "must be a valid email")
    private String correo = "example@gmail.com";

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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro la persona"));

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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Actualizo " + rta + " Vehiculo"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, Actualizo"));

        }
        System.out.println("rta " + rta);

    }

    public void eliminar() {

        Persona persona = new Persona();
        persona.setId(id);
//        persona.setNombre(nombre);
//        persona.setApellido(apellido);
//        persona.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime()));
//        persona.setCorreo(correo);

        PersonaDAO personaDAO = new PersonaDAO();
        int rta = personaDAO.eliminar(persona);
        if (rta > 0) {
            //this.mensaje = "eliminar "+r;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Eliminado " + rta + " Persona"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No, Elimino"));

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
