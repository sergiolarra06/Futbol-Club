package com.controller.bean;

import com.primefaces.dao.PersonaDAO;
import com.primefaces.dto.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.*;

/**
 *
 * @author
 */
@Named
@RequestScoped
@ManagedBean
@ViewScoped
public class ListaBeanPersonas {

//    @EJB
    private List<Persona> Personas;
    private PersonaDAO personaDAO;
    private Persona persona;
//
//    private BeanPersona beanPersona;

    public ListaBeanPersonas() {
        personaDAO = new PersonaDAO();
//        beanPersona = new BeanPersona();
    }

    @PostConstruct
    public void init() {
        persona = new Persona();
    }

    public List<Persona> getPersonas() {
        this.Personas = personaDAO.obtenerTodos();
        return Personas;
    }

    public void setPersonas(List<Persona> Personas) {
        this.Personas = Personas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String salir() {
        return "index";
    }

}
