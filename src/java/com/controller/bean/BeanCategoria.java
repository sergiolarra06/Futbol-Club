package com.controller.bean;

import com.primefaces.dao.CategoriaDAO;
import com.primefaces.dto.Categoria;
import com.primefaces.dto.Categoria;
import com.primefaces.dto.Persona;
import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;
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
public class BeanCategoria implements Serializable {

    private static final Logger LOG = LogManager.getLogger(BeanCategoria.class);

    private long id;
    private String nombre;

    public BeanCategoria() {
    }

    public void insertar() {
        if (nombre == null || nombre.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNombre(nombre);

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        int rta = categoriaDAO.insertar(categoria);
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
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setId(id);
        CategoriaDAO categoriaDAO = new CategoriaDAO();
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
        Categoria categoria = new Categoria();
        categoria.setId(id);
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        int rta = categoriaDAO.eliminar(categoria);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String regresar() {
        return "index";
    }

}
