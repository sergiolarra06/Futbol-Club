/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.primefaces.dao.PesoDAO;
import com.primefaces.dto.Peso;
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
public class ListaBeanPeso {
    private List<Peso> Peso;
    private PesoDAO pesoDAO;

    public List<Peso> getPeso() {
        return Peso;
    }

    public void setPeso(List<Peso> Peso) {
        this.Peso = Peso;
    }

    public ListaBeanPeso() {
        pesoDAO = new PesoDAO();
    }

    @PostConstruct
    public void init() {
        Peso = cargarPesos();
    }

    private List<Peso> cargarPesos() {
        PesoDAO pesoDAO = new PesoDAO();
        return pesoDAO.obtenerTodos();
    }

    public String salir() {
        return "index";
    }

}
