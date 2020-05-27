/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.primefaces.dao.EstaturaDAO;
import com.primefaces.dao.PaseGolDAO;
import com.primefaces.dto.Estatura;
import com.primefaces.dto.PaseGol;
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
public class ListaBeanPaseGol {
    private List<PaseGol> Pasegol;
    private PaseGolDAO pasegolDAO;

    public List<PaseGol> getPasegol() {
        return Pasegol;
    }

    public void setPasegol(List<PaseGol> Pasegol) {
        this.Pasegol = Pasegol;
    }

    public ListaBeanPaseGol(PaseGolDAO pasegolDAO) {
        this.pasegolDAO = pasegolDAO;
    }

    @PostConstruct
    public void init() {
        Pasegol = cargarPasegol();
    }

    private List<PaseGol> cargarPasegol() {
        PaseGolDAO pasegolDAO = new PaseGolDAO();
        return pasegolDAO.obtenerTodos();
    }

    public String salir() {
        return "index";
    }

}
