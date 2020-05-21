package com.controller.bean;

import com.primefaces.dao.LesionDAO;
import com.primefaces.dto.Lesion;
import com.primefaces.dto.Jugador;
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro la estatura"));

        System.out.println("rta " + rta);
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

  
    public String salir() {
        return "index";
    }

}
