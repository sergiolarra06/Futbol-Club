package com.controller.bean;

import com.primefaces.dao.PersonaDAO;
import com.primefaces.dto.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author OscarEsteban
 */
@ManagedBean
@ViewScoped
public class ListaBeanPersonas {

    private List<Persona> Personas;
    private PersonaDAO personaDAO;

    public List<Persona> getPersonas() {
        return Personas;
    }

    public void setPersonas(List<Persona> Personas) {
        this.Personas = Personas;
    }

    public ListaBeanPersonas() {
        personaDAO = new PersonaDAO();
    }

    @PostConstruct
    public void init() {
        Personas = cargarPersonas();
    }

    private List<Persona> cargarPersonas() {
        PersonaDAO personaDAO = new PersonaDAO();
        return personaDAO.obtenerTodos();
    }

    private Persona cargarPersona(Long id) {
        PersonaDAO personaDAO = new PersonaDAO();
        return personaDAO.obtener(id);
    }

    
    //Pantalla dialog averiguar 
    public void editar(Persona dato) {
        return ;
    }

    public String salir() {
        return "index";
    }

}
