package com.controller.bean;

import com.primefaces.dao.LesionDAO;
import com.primefaces.dto.Lesion;
import com.primefaces.dto.Lesion;
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
public class BeanLesion implements Serializable {

    private static final Logger LOG = LogManager.getLogger(BeanLesion.class);

    private long id;
    private String descripcion;

    public BeanLesion() {
    }

    public void insertar() {
        if (descripcion == null || descripcion.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        Lesion lesion = new Lesion();
        lesion.setId(id);
        lesion.setDescripcion(descripcion);

        LesionDAO estaturaDAO = new LesionDAO();
        int rta = estaturaDAO.insertar(lesion);
        System.out.println("rta " + rta);
        if (rta > 0) {
            LOG.info("SEVERITY_INFO: Se Inserto la lesion " + rta);
            //this.mensaje = "eliminar "+r;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "inserto " + rta + " lesion"));
        } else {
            LOG.info("SEVERITY_ERROR: No se inserto el lesion" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No, inserto la lesion"));
        }
    }

    public void modificar() {
        Lesion lesion = new Lesion();
        lesion.setDescripcion(descripcion);
        lesion.setId(id);
        LesionDAO lesionDAO = new LesionDAO();
        int rta = lesionDAO.modificar(lesion);
        System.out.println("rta " + rta);
        if (rta > 0) {
            //this.mensaje = "actualizo "+r;
            LOG.info("SEVERITY_INFO: Se actualizo el lesion" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Actualizo " + rta + " lesion"));
        } else {
            LOG.error("SEVERITY_ERROR:No se modifico el lesion" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, Actualizo la lesion"));
        }
    }

    public void eliminar() {
        Lesion lesion = new Lesion();
        lesion.setId(id);
        LesionDAO lesionDAO = new LesionDAO();
        int rta = lesionDAO.eliminar(lesion);
        System.out.println("rta " + rta);
        if (rta > 0) {
            //this.mensaje = "eliminar "+r;
            LOG.info("SEVERITY_INFO: Se elimino el lesion" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "elimino " + rta + " lesion"));
        } else {
            LOG.error("SEVERITY_ERROR:No se elimino el lesion" + rta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, elimino la lesion"));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String regresar() {
        return "index";
    }

}
