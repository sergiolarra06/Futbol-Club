/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.dto;

//import com.oracle.wls.shaded.org.apache.xpath.operations.Equals;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import javax.persistence.*;
import javax.persistence.Enumerated;

/**
 * 
 *https://sqltemuco.wordpress.com/2017/02/03/usando-enum-con-sql/
 *
 * @author jean.cortes
 */
public enum Posicion {
    PORTERO("PORTERO"), DEFENSA("DEFENSA"), CENTROCAMPISTA("CENTROCAMPISTA"), DELANTERO("DELANTERO");
//    PORTERO, DEFENSA, CENTROCAMPISTA, DELANTERO;
    private final String posicion;

    
    private Posicion(String posicion) {
        this.posicion = posicion;
    }

    public String getPosicion() {
        return posicion;
    }

    public boolean equalsName(String nombre) {
        return nombre.equals(posicion);
    }

    @Override
    public String toString() {
        return this.posicion;
    }

    public String getString() {
        return this.posicion;
    }

}
