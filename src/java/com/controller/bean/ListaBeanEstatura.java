package com.controller.bean;

import com.primefaces.dao.EstaturaDAO;
import com.primefaces.dto.Estatura;
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
public class ListaBeanEstatura {

    private List<Estatura> Estatura;
    private EstaturaDAO estaturaDAO;

    public List<Estatura> getEstatura() {
        return Estatura;
    }

    public void setEstatura(List<Estatura> Estatura) {
        this.Estatura = Estatura;
    }

    public ListaBeanEstatura() {
        estaturaDAO = new EstaturaDAO();
    }

    @PostConstruct
    public void init() {
        Estatura = cargarEstaturas();
    }

    private List<Estatura> cargarEstaturas() {
        EstaturaDAO personaDAO = new EstaturaDAO();
        return personaDAO.obtenerTodos();
    }

    public String salir() {
        return "index";
    }

}
