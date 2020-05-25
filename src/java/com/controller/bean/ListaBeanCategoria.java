package com.controller.bean;

import com.primefaces.dao.CategoriaDAO;
import com.primefaces.dto.Categoria;
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
public class ListaBeanCategoria {

    private Categoria categoria;
    private List<Categoria> categorias;
    private CategoriaDAO categoriaDAO;

    @PostConstruct
    public void init() {
        categoriaDAO = new CategoriaDAO();
        categoria = new Categoria();
    }

    public List<Categoria> getCategoria() {
        this.categorias = categoriaDAO.obtenerTodos();
        return categorias;
    }

    public void setCategoria(List<Categoria> Categoria) {
        this.categorias = Categoria;
    }

    public ListaBeanCategoria() {
        categoriaDAO = new CategoriaDAO();
    }

//    private List<Categoria> cargarCategoriaes() {
//        CategoriaDAO personaDAO = new CategoriaDAO();
//        return personaDAO.obtenerTodos();
//    }
    public String regresar() {
        return "index";
    }
}
