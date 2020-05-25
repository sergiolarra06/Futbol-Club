package com.controller.bean;

import com.primefaces.dao.CategoriaDAO;
import com.primefaces.dao.CategoriaJugadorDAO;
import com.primefaces.dto.Categoria;
import com.primefaces.dto.Categoria;
import com.primefaces.dto.CategoriaJugador;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.Persona;
import java.io.Serializable;
import java.sql.Date;
import java.sql.*;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import javax.validation.constraints.Email;

/**
 *
 * @author Jean Cortes
 */
//@Named(value = "beanv")
//@Dependent
@ManagedBean
@ViewScoped
public class BeanCategoriaJugador implements Serializable {

    private static final Logger LOG = LogManager.getLogger(BeanCategoriaJugador.class);

    private Jugador jugador;
    private Categoria categoria;
    private Date fechaInicio;
    private Date fechaFin;

    public BeanCategoriaJugador() {
        jugador = new Jugador();
        categoria = new Categoria();
    }

    public void insertar() {
        if (jugador == null || categoria == null || fechaInicio == null || fechaFin == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        CategoriaJugador categoriaJugador = new CategoriaJugador();
        categoriaJugador.setJugador(jugador);
        categoriaJugador.setCategoria(categoria);
        categoriaJugador.setFechaInicio(new java.sql.Date(fechaInicio.getTime()));
        categoriaJugador.setFechaFin(new java.sql.Date(fechaFin.getTime()));

        CategoriaJugadorDAO categoriaJugadorDAO = new CategoriaJugadorDAO();
        int rta = categoriaJugadorDAO.insertar(categoriaJugador);
        System.out.println("rta " + rta);
        if (rta > 0) {
            LOG.info("SEVERITY_INFO: Se Inserto la categoria " + rta);
            //this.mensaje = "eliminar "+r;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "inserto " + rta + " categoria"));
        } else {
            LOG.info("SEVERITY_ERROR: No se inserto el categoria" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No, inserto la categoria"));
        }
    }

    public void modificar() {
        CategoriaJugador categoria = new CategoriaJugador();
//        categoria.setNombre(nombre);
//        categoria.setId(id);
        CategoriaJugadorDAO categoriaDAO = new CategoriaJugadorDAO();
        int rta = categoriaDAO.modificar(categoria);
        System.out.println("rta " + rta);
        if (rta > 0) {
            //this.mensaje = "actualizo "+r;
            LOG.info("SEVERITY_INFO: Se actualizo el categoria" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Actualizo " + rta + " categoria"));
        } else {
            LOG.error("SEVERITY_ERROR:No se modifico el categoria" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, Actualizo la categoria"));
        }
    }

    public void eliminar() {
        CategoriaJugador categoriaJugador = new CategoriaJugador();
        categoriaJugador.setJugador(jugador);
        categoriaJugador.setCategoria(categoria);
        CategoriaJugadorDAO categoriaDAO = new CategoriaJugadorDAO();
        int rta = categoriaDAO.eliminar(categoriaJugador);
        System.out.println("rta " + rta);
        if (rta > 0) {
            //this.mensaje = "eliminar "+r;
            LOG.info("SEVERITY_INFO: Se elimino el categoria" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "elimino " + rta + " categoria"));
        } else {
            LOG.error("SEVERITY_ERROR:No se elimino el categoria" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, elimino la categoria"));
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String regresar() {
        return "index";
    }

}
