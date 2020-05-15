/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.primefaces.dto.Posicion;
import java.io.Serializable;
import java.sql.Array;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author jean.cortes
 */
@Named(value = "beanPosicion")
@Dependent
@ManagedBean
@ViewScoped
@ApplicationScoped

public class BeanPosicion implements Serializable {

    private Posicion posicion;

    /**
     * Creates a new instance of PosicionController
     */
    public BeanPosicion() {
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Posicion[] getPosicionOptions() {
        return Posicion.values();
    }

//    public SelectItem[] ObtenerValoresPosicion() {
//        SelectItem[] items = new SelectItem[Posicion.values().length];
//        int i = 0;
//        for (Posicion p : Posicion.values()) {//Variable de tipo BeanPosicion,: Array de mi enum Posicicion
//            items[i++] = new SelectItem(p, p.toString());
//        }
//        return items;
//    }
//    public Posicion[] getPosicion(String string) {
//        return Posicion.values();
//    }
    public Posicion[] getPosicionOptions(int findColumn) {
        return Posicion.values();
    }

    
}

//<converter> <converter-for-class
//
//>java.lang.Enum</converter-for-class
//
//> <converter-class
//
//>javax.faces.convert.EnumConverter</converter-class
//> </converter>
//
//Fuente: https://www.iteramos.com/pregunta/61658/jsf-20-el-uso-de-valores-de-enumeracion-para-selectonemenu


//http://www.catgovind.com/jsf/how-to-access-enum-variables-in-jsf-using-expression-language/
