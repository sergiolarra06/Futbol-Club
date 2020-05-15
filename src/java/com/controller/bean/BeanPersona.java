package com.controller.bean;

import com.primefaces.dao.PersonaDAO;
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
public class BeanPersona implements Serializable {

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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro la persona"));

        System.out.println("rta " + rta);
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

//    public java.sql.Date convertir(java.util.Date fechaUtilDate) {
//        return new java.sql.Date(fechaUtilDate.getTime());
//    }
    public String salir() {
        return "index";
    }

}
