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

    private Lesion lesion;
    private List<Lesion> lesiones;
    private LesionDAO lesionDAO;

    @PostConstruct
    public void init() {
        lesionDAO = new LesionDAO();
        lesion = new Lesion();
    }

    public List<Lesion> getLesion() {
        this.lesiones = lesionDAO.obtenerTodos();
        return lesiones;
    }

    public void setLesion(List<Lesion> Lesion) {
        this.lesiones = Lesion;
    }

    public ListaBeanLesion() {
        lesionDAO = new LesionDAO();
    }

//    private List<Lesion> cargarLesiones() {
//        LesionDAO personaDAO = new LesionDAO();
//        return personaDAO.obtenerTodos();
//    }
    public String regresar() {
        return "index";
    }
}
