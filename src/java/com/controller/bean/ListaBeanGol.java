/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.primefaces.dao.EstaturaDAO;
import com.primefaces.dao.GolDAO;
import com.primefaces.dto.Estatura;
import com.primefaces.dto.Gol;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Sergio
 */
@ManagedBean
@ViewScoped
public class ListaBeanGol {
    private List<Gol> Gol;
    private GolDAO golDAO;

    public ListaBeanGol(GolDAO golDAO) {
        this.golDAO = golDAO;
    }

    public List<Gol> getGol() {
        return Gol;
    }

    public void setGol(List<Gol> Gol) {
        this.Gol = Gol;
    }

      

    @PostConstruct
    public void init() {
        Gol = cargarGoles();
    }

    private List<Gol> cargarGoles() {
        GolDAO golDAO = new GolDAO();
        return golDAO.obtenerTodos();
    }

    public String salir() {
        return "index";
    }

}
