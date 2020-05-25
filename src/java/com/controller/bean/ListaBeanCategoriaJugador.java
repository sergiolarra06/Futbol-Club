package com.controller.bean;

import com.primefaces.dao.CategoriaJugadorDAO;
import com.primefaces.dto.CategoriaJugador;
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
public class ListaBeanCategoriaJugador {

    private CategoriaJugador categoriaJugador;
    private List<CategoriaJugador> categoriaJugadors;
    private CategoriaJugadorDAO categoriaJugadorDAO;

    @PostConstruct
    public void init() {
        categoriaJugadorDAO = new CategoriaJugadorDAO();
        categoriaJugador = new CategoriaJugador();
    }

    public List<CategoriaJugador> getCategoriaJugador() {
        this.categoriaJugadors = categoriaJugadorDAO.obtenerTodos();
        return categoriaJugadors;
    }

    public void setCategoriaJugador(List<CategoriaJugador> CategoriaJugador) {
        this.categoriaJugadors = CategoriaJugador;
    }

    public ListaBeanCategoriaJugador() {
        categoriaJugadorDAO = new CategoriaJugadorDAO();
    }

//    private List<CategoriaJugador> cargarCategoriaJugadores() {
//        CategoriaJugadorDAO personaDAO = new CategoriaJugadorDAO();
//        return personaDAO.obtenerTodos();
//    }
    public String regresar() {
        return "index";
    }
}
