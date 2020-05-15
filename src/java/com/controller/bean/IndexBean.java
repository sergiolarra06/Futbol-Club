package com.controller.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jean Cortes
 */
@ManagedBean
@ViewScoped
public class IndexBean implements Serializable {

    public String irCrearPersona() {
      System.out.println("Ingreso a Persona ..");
        return "Persona";
    }

    public String irListaPersona() {
        System.out.println("Ingreso a Lista Personas ..");
        return "listarPersonas";
    }

    public String irCrearPropietario() {
//      System.out.println("Ingreso a Propietario..");
        return "Propietario";
    }

    public String irListaPropietario() {
        System.out.println("Ingreso a Lista Propietario ..");
        return "listapropietarios";
    }

    public String irCrearEnsambladora() {
//      System.out.println("Ingreso a Propietario..");
        return "Ensambladora";
    }

    public String irListaEnsambladora() {
        System.out.println("Ingreso a Lista Ensambladora ..");
        return "listaensambladoras";
    }

}
