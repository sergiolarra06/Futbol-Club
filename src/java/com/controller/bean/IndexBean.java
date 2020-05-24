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

    public String irCrearVehiculo() {
//      System.out.println("Ingreso a Vehiculo ..");
        return "Persona";
    }

    public String irListaVehiculo() {
        System.out.println("Ingreso a Lista Vehiculo ..");
        return "listavehiculos";
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

    public String irCrearPersona() {
        return "crear";
    }

    public String irEditarPersona() {
        return "index";
    }

}
