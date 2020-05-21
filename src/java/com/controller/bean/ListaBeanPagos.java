package com.controller.bean;

import com.primefaces.dao.ContactoEmergenciaDAO;
import com.primefaces.dao.PagoDAO;

import com.primefaces.dto.Pago;
import com.primefaces.dto.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author
 */
@ManagedBean
@ViewScoped
public class ListaBeanPagos {

    private List<Pago> Pagos;
    private PagoDAO pagoDAO;

    public List<Pago> getPagos() {
        return Pagos;
    }

    public void setPagos(List<Pago> Pagos) {
        this.Pagos = Pagos;
    }

    

    public ListaBeanPagos() {
        pagoDAO = new PagoDAO();
    }

    @PostConstruct
    public void init() {
        Pagos = cargarPersonas();
    }

    private List<Pago> cargarPersonas() {
        PagoDAO pagoDAO = new PagoDAO();
        return pagoDAO.obtenerTodos();
    }

    private Pago cargarPersona(Long id) {
        PagoDAO pagoDAO = new PagoDAO();
        return pagoDAO.obtener(id);
    }

    public String salir() {
        return "index";
    }

}
