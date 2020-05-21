package com.controller.bean;

import com.primefaces.dao.LesionDAO;
import com.primefaces.dto.Lesion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jean.cortes
 */
@ManagedBean
@ViewScoped
public class ListaBeanLesion {

    private List<Lesion> Lesiones;
    private LesionDAO lesionDAO;

    public List<Lesion> getLesion() {
        return Lesiones;
    }

    public void setLesion(List<Lesion> Lesion) {
        this.Lesiones = Lesion;
    }

    public ListaBeanLesion() {
        lesionDAO = new LesionDAO();
    }

    @PostConstruct
    public void init() {
        Lesiones = cargarLesiones();
    }

    private List<Lesion> cargarLesiones() {
        LesionDAO personaDAO = new LesionDAO();
        return personaDAO.obtenerTodos();
    }

    public String salir() {
        return "index";
    }
}
